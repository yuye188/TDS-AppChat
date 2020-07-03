package umu.tds.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Contacto;
import umu.tds.modelo.Grupo;
import umu.tds.modelo.Mensaje;
import umu.tds.modelo.Mensaje.MsgBuilder;

public class AdaptadorMensajeDAO implements IMensajeDAO {

	private ServicioPersistencia servPersistencia;
	private static AdaptadorMensajeDAO unicaInstancia = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	private AdaptadorMensajeDAO() {
		this.servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public static AdaptadorMensajeDAO getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorMensajeDAO();
		return unicaInstancia;
	}

	@Override
	public boolean registrarMensaje(Mensaje mensaje) {
		Entidad eMensaje;
		boolean existe = true;

		// Si la entidad está registrada no la registra de nuevo
		try {
			eMensaje = servPersistencia.recuperarEntidad(mensaje.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) {
			return false;
		}
		
		eMensaje = new Entidad();
		eMensaje.setNombre("Mensaje");
		eMensaje.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("texto", mensaje.getTexto()),
				new Propiedad("hora", sdf.format(mensaje.getHora())),
				new Propiedad("emoticon", String.valueOf(mensaje.getEmoticon())),
				new Propiedad("tlfEmisor", mensaje.getTlfEmisor()),
				new Propiedad("receptor", String.valueOf(mensaje.getReceptor().getCodigo())),
				new Propiedad("isGrupo",String.valueOf(mensaje.getReceptor().getClass() == Grupo.class)))));

		eMensaje = servPersistencia.registrarEntidad(eMensaje);
		mensaje.setCodigo(eMensaje.getId());
		return true;
	}

	@Override
	public void borrarMensaje(Mensaje mensaje) {
		Entidad eMensaje = servPersistencia.recuperarEntidad(mensaje.getCodigo());
		servPersistencia.borrarEntidad(eMensaje);
	}

	@Override
	public void modificarMensaje(Mensaje mensaje) {
		Entidad eMensaje = servPersistencia.recuperarEntidad(mensaje.getCodigo());

		servPersistencia.eliminarPropiedadEntidad(eMensaje, "texto");
		servPersistencia.anadirPropiedadEntidad(eMensaje, "texto", mensaje.getTexto());
		servPersistencia.eliminarPropiedadEntidad(eMensaje, "hora");
		servPersistencia.anadirPropiedadEntidad(eMensaje, "hora", sdf.format(mensaje.getHora()));
		servPersistencia.eliminarPropiedadEntidad(eMensaje, "emoticon");
		servPersistencia.anadirPropiedadEntidad(eMensaje, "emoticon", String.valueOf(mensaje.getEmoticon()));
		servPersistencia.eliminarPropiedadEntidad(eMensaje, "tlfEmisor");
		servPersistencia.anadirPropiedadEntidad(eMensaje, "tlfEmisor", mensaje.getTlfEmisor());
		servPersistencia.eliminarPropiedadEntidad(eMensaje, "receptor");
		servPersistencia.anadirPropiedadEntidad(eMensaje, "receptor",String.valueOf(mensaje.getReceptor().getCodigo()));
		servPersistencia.eliminarPropiedadEntidad(eMensaje, "isGrupo");
		servPersistencia.anadirPropiedadEntidad(eMensaje, "isGrupo",String.valueOf(mensaje.getReceptor().getClass() == Grupo.class));
	}

	@Override
	public Mensaje recuperarMensaje(int codigo) {
		// Si la entidad está en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Mensaje) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		// si no, la recupera de la base de datos
		Entidad eMensaje = servPersistencia.recuperarEntidad(codigo);
		String texto = servPersistencia.recuperarPropiedadEntidad(eMensaje, "texto");
		Date hora = null;
		try {
			hora = sdf.parse(servPersistencia.recuperarPropiedadEntidad(eMensaje, "hora"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int emoticon = Integer.valueOf(servPersistencia.recuperarPropiedadEntidad(eMensaje, "emoticon"));
		String tlfEmisor = servPersistencia.recuperarPropiedadEntidad(eMensaje, "tlfEmisor");
		
		Mensaje mensaje = MsgBuilder.createBuilder(tlfEmisor, null)
									.setEmoticon(emoticon)
									.setTexto(texto)
									.setHora(hora)
									.build();
		mensaje.setCodigo(codigo);
							
		// añadir el objeto al Pool
		PoolDAO.getUnicaInstancia().addObjeto(codigo, mensaje);
		
		Contacto receptor = null;
		int codigoReceptor = Integer.valueOf(servPersistencia.recuperarPropiedadEntidad(eMensaje, "receptor"));
		boolean isGrupo = Boolean.valueOf(servPersistencia.recuperarPropiedadEntidad(eMensaje, "isGrupo"));
		if (isGrupo) {
			receptor = AdaptadorGrupoDAO.getUnicaInstancia().recuperarContacto(codigoReceptor);
		} else {
			
			try {
				receptor = AdaptadorContactoIndividualDAO.getUnicaInstancia().recuperarContacto(codigoReceptor);
			} catch (NullPointerException e) {
				// puede ser que el contacto receptor ya ha sido eliminado por el otro usuario,
				// en este caso, se mantiene el mensaje con el campo receptor = null, no hay problema
				// ya que no se hace uso del este campo para otras funcionalidades
			}
		}
		
		mensaje.setReceptor(receptor);
		return mensaje;
	}

	@Override
	public List<Mensaje> recuperarTodosMensajes() {
		List<Entidad> eMensajes = servPersistencia.recuperarEntidades("Mensaje");
		List<Mensaje> mensajes = new LinkedList<>();

		for (Entidad eMensaje : eMensajes) {
			mensajes.add(recuperarMensaje(eMensaje.getId()));
		}
		return mensajes;
	}

}

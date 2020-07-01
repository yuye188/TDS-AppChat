package umu.tds.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Contacto;
import umu.tds.modelo.ContactoIndividual;
import umu.tds.modelo.Mensaje;
import umu.tds.modelo.Usuario;

public class AdaptadorContactoIndividualDAO implements IContactoDAO{
	
	private ServicioPersistencia servPersistencia;
	private static AdaptadorContactoIndividualDAO unicaInstancia = null;
	
	public AdaptadorContactoIndividualDAO() {
		this.servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public static AdaptadorContactoIndividualDAO getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorContactoIndividualDAO();
		return unicaInstancia;
	}

	@Override
	public boolean registrarContacto(Contacto contacto) {
		
		// comprobar si ya existe el Contacto
		Entidad eContactoIndividual;
		boolean existe = true; 
		
		ContactoIndividual contactoIndividual = (ContactoIndividual) contacto;
		try {
			eContactoIndividual = servPersistencia.recuperarEntidad(contactoIndividual.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) {
			return false;
		}
		
		// registrar mensajes 
		AdaptadorMensajeDAO adaptadorMensaje = AdaptadorMensajeDAO.getUnicaInstancia();
		for (Mensaje m: contactoIndividual.getMensajes()) {
			adaptadorMensaje.registrarMensaje(m);
		}
		
		// crear entidad ContactoIndividual
		eContactoIndividual = new Entidad();
		eContactoIndividual.setNombre("ContactoIndividual");
		eContactoIndividual.setPropiedades(new ArrayList<Propiedad>(Arrays.asList
					   (new Propiedad("nombre", contactoIndividual.getNombre()),
						new Propiedad("mensajes", this.obtenerCodigosMensajes(contactoIndividual.getMensajes())),
						new Propiedad("movil", contactoIndividual.getMovil()),
						new Propiedad("usuario", String.valueOf(contactoIndividual.getUsuario().getCodigo())))));
		
		eContactoIndividual = servPersistencia.registrarEntidad(eContactoIndividual);
		contactoIndividual.setCodigo(eContactoIndividual.getId()); 
		return true;
	}

	@Override
	public void borrarContacto(Contacto contacto) {
		//for (Mensaje m:contacto.getMensajes())
			//AdaptadorMensajeDAO.getUnicaInstancia().borrarMensaje(m);
		
		Entidad eContactoIndividual = servPersistencia.recuperarEntidad(contacto.getCodigo());
		servPersistencia.borrarEntidad(eContactoIndividual);
	}

	@Override
	public void modificarContacto(Contacto contacto) {
		Entidad eContactoIndividual = servPersistencia.recuperarEntidad(contacto.getCodigo());
		ContactoIndividual contactoIndividual = (ContactoIndividual) contacto;
		
		servPersistencia.eliminarPropiedadEntidad(eContactoIndividual, "nombre");
		servPersistencia.anadirPropiedadEntidad(eContactoIndividual, "nombre", contactoIndividual.getNombre());
		
		
		servPersistencia.eliminarPropiedadEntidad(eContactoIndividual, "mensajes");
		servPersistencia.anadirPropiedadEntidad(eContactoIndividual, "mensajes", this.obtenerCodigosMensajes(contactoIndividual.getMensajes()));
		
		servPersistencia.eliminarPropiedadEntidad(eContactoIndividual, "movil");
		servPersistencia.anadirPropiedadEntidad(eContactoIndividual, "movil",contactoIndividual.getMovil());
		
		servPersistencia.eliminarPropiedadEntidad(eContactoIndividual, "usuario");
		servPersistencia.anadirPropiedadEntidad(eContactoIndividual, "usuario", String.valueOf(contactoIndividual.getUsuario().getCodigo()));
	}
	
	public Contacto actualizarMensajes(Contacto c) {
		Entidad eContactoIndividual = servPersistencia.recuperarEntidad(c.getCodigo());
		
		// añadir ContactoIndividual al pool
		PoolDAO.getUnicaInstancia().addObjeto(c.getCodigo(), c);
		
		String mensajes = servPersistencia.recuperarPropiedadEntidad(eContactoIndividual, "mensajes");
		c.setMensajes(this.obtenerMensajesDesdeCodigos(mensajes));
		
		return c;
	}
	
	

	@Override
	public Contacto recuperarContacto(int codigo) {
		
		// Si la entidad está en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (ContactoIndividual) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		// si no, la recupera de la base de datos
		Entidad eContactoIndividual;
		String nombre;
		String movil;
		Usuario usuario;
		//List<Mensaje> mensajes = new LinkedList<>();
		
		eContactoIndividual = servPersistencia.recuperarEntidad(codigo);
		
		nombre = servPersistencia.recuperarPropiedadEntidad(eContactoIndividual, "nombre");
		movil = servPersistencia.recuperarPropiedadEntidad(eContactoIndividual, "movil");
		
		ContactoIndividual contacto = new ContactoIndividual(nombre, movil, null);
		contacto.setCodigo(codigo);
		
		// añadir ContactoIndividual al pool
		PoolDAO.getUnicaInstancia().addObjeto(codigo, contacto);
		
		int codigoUsuario = Integer.valueOf(servPersistencia.recuperarPropiedadEntidad(eContactoIndividual, "usuario"));
		usuario = AdaptadorUsuarioDAO.getUnicaInstancia().recuperarUsuario(codigoUsuario);
		contacto.setUsuario(usuario);
		
		String mensajes = servPersistencia.recuperarPropiedadEntidad(eContactoIndividual, "mensajes");
		contacto.setMensajes(this.obtenerMensajesDesdeCodigos(mensajes));
		
		return contacto;
		
	}

	@Override
	public List<Contacto> recuperarTodosContactos() {
		List<Entidad> eContactosIndividuales = servPersistencia.recuperarEntidades("ContactoIndividual");
		List<Contacto> contactoIndividuales = new LinkedList<>();

		for (Entidad eContactoIndividual : eContactosIndividuales) {
			contactoIndividuales.add(recuperarContacto(eContactoIndividual.getId()));
		}
		return contactoIndividuales;
	}
	
	
	// -------------------Funciones auxiliares-----------------------------
	private String obtenerCodigosMensajes(List<Mensaje> mensajes){
		String aux = "";
		for (Mensaje m : mensajes) {
			aux += m.getCodigo() + " ";
		}
		return aux.trim();
	}
	
	private List<Mensaje> obtenerMensajesDesdeCodigos(String mensajes) {

		List<Mensaje> listaContactos = new LinkedList<Mensaje>();
		StringTokenizer strTok = new StringTokenizer(mensajes, " ");
		AdaptadorMensajeDAO adaptadorM = AdaptadorMensajeDAO.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listaContactos.add(adaptadorM.recuperarMensaje(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaContactos;
	}
	
}

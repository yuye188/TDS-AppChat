package umu.tds.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Contacto;
import umu.tds.modelo.Grupo;
import umu.tds.modelo.Mensaje;
import umu.tds.modelo.Usuario;

public class AdaptadorGrupoDAO implements IContactoDAO {

	private ServicioPersistencia servPersistencia;
	private static AdaptadorGrupoDAO unicaInstancia = null;

	public static AdaptadorGrupoDAO getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorGrupoDAO();
		return unicaInstancia;
	}
	
	private AdaptadorGrupoDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public boolean registrarContacto(Contacto contacto) {
		Entidad eGrupo;
		boolean existe = true; 
		if(!(contacto instanceof Grupo)) {
			throw new ClassCastException ();
		}

		Grupo grupo = (Grupo) contacto;
		
		try {
			eGrupo = servPersistencia.recuperarEntidad(grupo.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) {
			return false;
		}

		AdaptadorMensajeDAO adaptadorMensaje = AdaptadorMensajeDAO.getUnicaInstancia();
		for (Mensaje mensaje: grupo.getMensajes()) {
			adaptadorMensaje.registrarMensaje(mensaje);
		}
		
		eGrupo = new Entidad();
		eGrupo.setNombre("Grupo");
		eGrupo.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("nombre", grupo.getNombre()),
						//TODO Mensaje
						//TODO Miembros
						new Propiedad("pathImg", grupo.getPathImg()),
						new Propiedad("admin", String.valueOf(grupo.getAdmin().getCodigo())))));
		

		eGrupo = servPersistencia.registrarEntidad(eGrupo);
		grupo.setCodigo(eGrupo.getId()); 
		
		return true;
	}

	@Override
	public void borrarContacto(Contacto contacto) {
		Entidad eGrupo = servPersistencia.recuperarEntidad(contacto.getCodigo());
		
		servPersistencia.borrarEntidad(eGrupo);

	}

	@Override
	public void modificarContacto(Contacto contacto) {
		Entidad eGrupo = servPersistencia.recuperarEntidad(contacto.getCodigo());

		Grupo grupo = (Grupo) contacto;
		servPersistencia.eliminarPropiedadEntidad(eGrupo, "nombre");
		servPersistencia.anadirPropiedadEntidad(eGrupo, "nombre", grupo.getNombre());
		
		//TODO MENSAJE
		//TODO MIEMBRO
		servPersistencia.eliminarPropiedadEntidad(eGrupo, "pathImg");
		servPersistencia.anadirPropiedadEntidad(eGrupo, "pathImg", grupo.getPathImg());
		servPersistencia.eliminarPropiedadEntidad(eGrupo, "admin");
		servPersistencia.anadirPropiedadEntidad(eGrupo, "admin", String.valueOf(grupo.getAdmin().getCodigo()));
	}

	@Override
	public Contacto recuperarContacto(int codigo) {

		// Si la entidad está en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Grupo) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		// si no, la recupera de la base de datos
		Entidad eGrupo;
		String nombre;
		List<Mensaje> mensajes = new LinkedList<>();
		List<Usuario> miembros = new LinkedList<>();
		String pathImg;
		
		eGrupo = servPersistencia.recuperarEntidad(codigo);
		nombre = servPersistencia.recuperarPropiedadEntidad(eGrupo, "nombre");
		//TODO MENSAJES
		//TODO MIEMBROS
		pathImg = servPersistencia.recuperarPropiedadEntidad(eGrupo, "pathImg");
		
		Grupo grupo = new Grupo(nombre, null);
		grupo.setCodigo(codigo);
		grupo.setPathImg(pathImg);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, grupo);
		
		int codigoAdmin = Integer.valueOf(servPersistencia.recuperarPropiedadEntidad(eGrupo, "admin"));
		grupo.setAdmin(AdaptadorUsuarioDAO.getUnicaInstancia().recuperarUsuario(codigoAdmin));
		grupo.setMensajes(mensajes);
		grupo.setMiembros(miembros);
		
		return grupo;
	}

	@Override
	public List<Contacto> recuperarTodosContactos() {
		List<Entidad> eGrupos = servPersistencia.recuperarEntidades("Grupo");
		List<Contacto> grupos = new LinkedList<>();

		for (Entidad eGrupo : eGrupos) {
			grupos.add(recuperarContacto(eGrupo.getId()));
		}
		return grupos;
	}

}

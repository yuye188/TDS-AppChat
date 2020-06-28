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
		
		// comprobar si ya existe el Contacto
		Entidad eGrupo;
		boolean existe = true; 

		Grupo grupo = (Grupo) contacto;
		
		try {
			eGrupo = servPersistencia.recuperarEntidad(grupo.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) {
			return false;
		}
		
		// registrar mensajes 
		AdaptadorMensajeDAO adaptadorMensaje = AdaptadorMensajeDAO.getUnicaInstancia();
		for (Mensaje mensaje: grupo.getMensajes()) {
			adaptadorMensaje.registrarMensaje(mensaje);
		}
		
		eGrupo = new Entidad();
		eGrupo.setNombre("Grupo");
		eGrupo.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("nombre", grupo.getNombre()),
						new Propiedad("mensajes", this.obtenerCodigosMensajes(grupo.getMensajes())),
						new Propiedad("miembros", this.obtenerCodigosMiembros(grupo.getMiembros())),
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
		
		servPersistencia.eliminarPropiedadEntidad(eGrupo, "mensajes");
		servPersistencia.anadirPropiedadEntidad(eGrupo, "mensajes", this.obtenerCodigosMensajes(grupo.getMensajes()));
		
		servPersistencia.eliminarPropiedadEntidad(eGrupo, "miembros");
		servPersistencia.anadirPropiedadEntidad(eGrupo, "miembros", this.obtenerCodigosMiembros(grupo.getMiembros()));
		
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
		//List<Mensaje> mensajes = new LinkedList<>();
		//List<Usuario> miembros = new LinkedList<>();
		String pathImg;
		
		eGrupo = servPersistencia.recuperarEntidad(codigo);
		nombre = servPersistencia.recuperarPropiedadEntidad(eGrupo, "nombre");
		//mensajes.addAll(this.obtenerMensajesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eGrupo, "mensajes")));
		//miembros.addAll(this.obtenerMiembrosDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eGrupo, "miembros")));
		pathImg = servPersistencia.recuperarPropiedadEntidad(eGrupo, "pathImg");
		
		Grupo grupo = new Grupo(nombre, null);
		grupo.setCodigo(codigo);
		grupo.setPathImg(pathImg);
		
		// añadir Grupo al pool
		PoolDAO.getUnicaInstancia().addObjeto(codigo, grupo);
		
		int codigoAdmin = Integer.valueOf(servPersistencia.recuperarPropiedadEntidad(eGrupo, "admin"));
		grupo.setAdmin(AdaptadorUsuarioDAO.getUnicaInstancia().recuperarUsuario(codigoAdmin));
		grupo.setMensajes(this.obtenerMensajesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eGrupo, "mensajes")));
		grupo.setMiembros(this.obtenerMiembrosDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eGrupo, "miembros")));
		
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
	
	// -------------------Funciones auxiliares-----------------------------
	private String obtenerCodigosMensajes(List<Mensaje> mensajes){
		String aux = "";
		for (Mensaje m : mensajes) {
			aux += m.getCodigo() + " ";
		}
		return aux.trim();
	}

	private String obtenerCodigosMiembros(List<Usuario> usuarios){
		String aux = "";
		for (Usuario m : usuarios) {
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
	
	private List<Usuario> obtenerMiembrosDesdeCodigos(String usuarios) {

		List<Usuario> listaContactos = new LinkedList<Usuario>();
		StringTokenizer strTok = new StringTokenizer(usuarios, " ");
		AdaptadorUsuarioDAO adaptadorU = AdaptadorUsuarioDAO.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listaContactos.add(adaptadorU.recuperarUsuario(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaContactos;
	}
}

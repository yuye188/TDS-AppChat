package umu.tds.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Contacto;
import umu.tds.modelo.ContactoIndividual;
import umu.tds.modelo.Usuario;

public class AdaptadorUsuarioDAO implements IUsuarioDAO{
	
	private ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioDAO unicaInstancia = null;
	private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	
	public static AdaptadorUsuarioDAO getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorUsuarioDAO();
		else
			return unicaInstancia;
	}
	
	public AdaptadorUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia(); 
	}
	
	public boolean registrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		boolean existe = true; 
		
		// Si la entidad está registrada no la registra de nuevo
		try {
			eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) return false;

		//registrar primero los atributos que son objetos
		if (usuario.getListaContacto().size() > 0 ) {
			AdaptadorContactoIndividualDAO adaptadorCI = AdaptadorContactoIndividualDAO.getUnicaInstancia();
			for (Contacto contacto : usuario.getListaContacto()) {
				adaptadorCI.registrarContacto(contacto);
			}
		}
		
		if (usuario.getListaGrupo().size() > 0 ) {
			AdaptadorGrupoDAO adaptadorG = AdaptadorGrupoDAO.getUnicaInstancia();
			for (Contacto contacto : usuario.getListaContacto()) {
				adaptadorG.registrarContacto(contacto);
			}
		}
		
		AdaptadorEstadoDAO.getUnicaInstancia().registrarEstado(usuario.getEstado());
		
		// crear entidad Usuario
		eUsuario = new Entidad();
		eUsuario.setNombre("Usuario"); 
	
		String f = sdf.format(usuario.getFechaNacimiento().getTime());
		
		eUsuario.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("nombre", usuario.getNombre()), 
						new Propiedad("fechaNacimiento", f),
						new Propiedad("movil", usuario.getMovil()),
						new Propiedad("usuario",usuario.getUsuario()),
						new Propiedad("contrasenia", usuario.getContrasenia()),
						new Propiedad("pathImg", usuario.getPathImg()),
						new Propiedad("email", usuario.getEmail()),
						new Propiedad("premium",Boolean.toString(usuario.isPremium())),
						new Propiedad("estado", String.valueOf(usuario.getEstado().getCodigo())),
						new Propiedad("msgSaludo", usuario.getMsgSaludo()),
						new Propiedad("listaContacto", this.obtenerCodigoContactos(usuario.getListaContacto())),
						new Propiedad("listaGrupo", this.obtenerCodigoContactos(usuario.getListaGrupo()))
						))
				);
		
		// registrar entidad usuario
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		usuario.setCodigo(eUsuario.getId()); 
		return true;
	}

	public void borrarUsuario(Usuario usuario) {
		// No se comprueban restricciones de integridad 
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		servPersistencia.borrarEntidad(eUsuario);
	}

	public void modificarUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());

		// NO se puede modificar los campos fechaNacimiento, movil, usuario
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "nombre");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "nombre", usuario.getNombre());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "contrasenia");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "contrasenia", usuario.getContrasenia());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "pathImg");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "pathImg", usuario.getPathImg());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "premium");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "premium", String.valueOf(usuario.isPremium()));
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "estado");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "estado", String.valueOf(usuario.getEstado().getCodigo()));
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "msgSaludo");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "msgSaludo", usuario.getMsgSaludo());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "email");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "email", usuario.getEmail());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "listaContacto");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "listaContacto", this.obtenerCodigoContactos(usuario.getListaContacto()));
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "listaGrupo");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "listaGrupo", this.obtenerCodigoContactos(usuario.getListaGrupo()));
	}
	
	

	public Usuario recuperarUsuario(int codigo) {

		// Si la entidad está en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(codigo);

		// si no, la recupera de la base de datos
		Entidad eUsuario;
		String nombre;
		Date fechaNacimiento = null;
		String movil;
		String email;
		String usuario;
		String contrasenia;
		String msgSaludo;
		boolean premium;
		String pathImg;
		
		// y los campos de objetos
		//List<Contacto> contactos = new LinkedList<Contacto>();
		//List<Contacto> listaGrupo = new LinkedList<Contacto>();
		
		// recuperar entidad
		eUsuario = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		
		try {
			fechaNacimiento = sdf.parse(servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechaNacimiento"));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		movil = servPersistencia.recuperarPropiedadEntidad(eUsuario, "movil");
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		usuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, "usuario");
		contrasenia = servPersistencia.recuperarPropiedadEntidad(eUsuario, "contrasenia");
		premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium").equals("true");
		msgSaludo =  servPersistencia.recuperarPropiedadEntidad(eUsuario, "msgSaludo");
		pathImg = servPersistencia.recuperarPropiedadEntidad(eUsuario, "pathImg");
	
		Usuario u = new Usuario(nombre, fechaNacimiento, movil, email ,usuario, contrasenia, msgSaludo);
		u.setCodigo(codigo);
		u.setPathImg(pathImg);
		u.setPremium(premium);

		// IMPORTANTE:añadir el usuario al pool antes de llamar a otros
		// adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(codigo, u);

		// recuperar propiedades que son objetos llamando a adaptadores
		int codigoEstado = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eUsuario, "estado"));
		u.setEstado(AdaptadorEstadoDAO.getUnicaInstancia().recuperarEstado(codigoEstado));
		
		String contactosIndividuales = servPersistencia.recuperarPropiedadEntidad(eUsuario, "listaContacto");
		u.setListaContacto(this.obtenerContactosIndividualesDesdeCodigos(contactosIndividuales,false));
		
		String listaGrupo = servPersistencia.recuperarPropiedadEntidad(eUsuario, "listaGrupo");
		u.setListaGrupo(this.obtenerGruposDesdeCodigos(listaGrupo,false));
		
		return u;
	}

	public List<Usuario> recuperarTodosoUsuarios() {
		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("Usuario");
		List<Usuario> usuarios = new LinkedList<Usuario>();

		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		return usuarios;
	}

	// -------------------Funciones auxiliares-----------------------------
	private String obtenerCodigoContactos(List<Contacto> listaContactos){
		String aux = "";
		for (Contacto c : listaContactos) {
			aux += c.getCodigo() + " ";
		}
		return aux.trim();
	}
	
	private List<Contacto> obtenerGruposDesdeCodigos(String contactos, boolean actualizar) {

		List<Contacto> listaContactos = new LinkedList<Contacto>();
		StringTokenizer strTok = new StringTokenizer(contactos, " ");
		AdaptadorGrupoDAO adaptadorG = AdaptadorGrupoDAO.getUnicaInstancia();
		
		if (actualizar) {
			while (strTok.hasMoreTokens()) {
				Contacto c = adaptadorG.recuperarContacto(Integer.valueOf((String) strTok.nextElement()));
				AdaptadorGrupoDAO.getUnicaInstancia().actualizarMensajes(c);
				listaContactos.add(c);
			}
		} else {
			while (strTok.hasMoreTokens()) {
				listaContactos.add(adaptadorG.recuperarContacto(Integer.valueOf((String) strTok.nextElement())));
			}
		}
		return listaContactos;
	}
	
	
	private List<Contacto> obtenerContactosIndividualesDesdeCodigos(String contactos, boolean actualizar) {

		List<Contacto> listaContactos = new LinkedList<Contacto>();
		StringTokenizer strTok = new StringTokenizer(contactos, " ");
		AdaptadorContactoIndividualDAO adaptadorCI = AdaptadorContactoIndividualDAO.getUnicaInstancia();
		
		if (actualizar) {
			while (strTok.hasMoreTokens()) {
				Contacto c = adaptadorCI.recuperarContacto(Integer.valueOf((String) strTok.nextElement()));
				AdaptadorContactoIndividualDAO.getUnicaInstancia().actualizarMensajes(c);
				listaContactos.add(c);
			}
		}
		else {
			while (strTok.hasMoreTokens()) {
				listaContactos.add(adaptadorCI.recuperarContacto(Integer.valueOf((String) strTok.nextElement())));
			}
		}
		return listaContactos;
	}
	
	
	// solo actualizará el de los mensajes de cada contacto (Individual o grupo)
	public Usuario actualizarUsuario(Usuario u) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(u.getCodigo());
		
		String contactosIndividuales = servPersistencia.recuperarPropiedadEntidad(eUsuario, "listaContacto");
		u.setListaContacto(this.obtenerContactosIndividualesDesdeCodigos(contactosIndividuales,true));
		
		String listaGrupo = servPersistencia.recuperarPropiedadEntidad(eUsuario, "listaGrupo");
		u.setListaGrupo(this.obtenerGruposDesdeCodigos(listaGrupo,true));
		
		return u;
	}
}

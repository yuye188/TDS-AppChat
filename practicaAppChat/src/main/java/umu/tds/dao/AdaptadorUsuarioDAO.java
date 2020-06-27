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
import umu.tds.modelo.Estado;
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

		// ******************** TODO *********************** 
		//registrar primero los atributos que son objetos

		// crear entidad Usuario
		eUsuario = new Entidad();
		eUsuario.setNombre("Usuario"); 
	
		String f = sdf.format(usuario.getFechaNacimiento().getTime());
		System.out.println(f);
		
		eUsuario.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("nombre", usuario.getNombre()), 
						new Propiedad("fechaNacimiento", f),
						new Propiedad("movil", usuario.getMovil()),
						new Propiedad("usuario",usuario.getUsuario()),
						new Propiedad("contrasenia", usuario.getContrasenia()),
						//new Propiedad("imagen", usuario.getImagen),
						new Propiedad("premium",Boolean.toString(usuario.isPremium()))
						//TODO listContacto, administrador
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

		servPersistencia.eliminarPropiedadEntidad(eUsuario, "nombre");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "nombre", usuario.getNombre());
		//**************************TODO***********************
		// modificar el resto de los campos
		
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
		
		// y los campos de objetos
		List<Contacto> contactos = new LinkedList<Contacto>();
		Estado estado;
		
		
		// recuperar entidad
		eUsuario = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		
		try {
			fechaNacimiento = sdf.parse(servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechaNacimiento"));
			System.out.println(fechaNacimiento.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		movil = servPersistencia.recuperarPropiedadEntidad(eUsuario, "movil");
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		usuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		contrasenia = servPersistencia.recuperarPropiedadEntidad(eUsuario, "contrasenia");
		premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium").equals("true");
		msgSaludo =  servPersistencia.recuperarPropiedadEntidad(eUsuario, "msgSaludo");
		
		// TODO falta recuperar Estado y contactos
		

		Usuario u = new Usuario(nombre, fechaNacimiento, movil, email ,usuario, contrasenia, msgSaludo);
		u.setCodigo(codigo);
		u.setPathImg( servPersistencia.recuperarPropiedadEntidad(eUsuario, "pathImg"));
		u.setPremium(premium);
		u.setListaContacto(contactos);

		// IMPORTANTE:añadir el usuario al pool antes de llamar a otros
		// adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(codigo, u);

		//**************************TODO***********************
		// recuperar propiedades que son objetos llamando a adaptadores
		// ventas

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

	
}

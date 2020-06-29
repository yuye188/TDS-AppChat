package umu.tds.controlador;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.IContactoDAO;
import umu.tds.dao.IUsuarioDAO;
import umu.tds.modelo.Contacto;
import umu.tds.modelo.ContactoIndividual;
import umu.tds.modelo.Grupo;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.CatalogoUsuario;

public class ControladorAppChat {

	private static ControladorAppChat unicaInstancia;

	private IUsuarioDAO adaptadorUsuario;
	private IContactoDAO adaptadorContactoIndividual;
	private IContactoDAO adaptadorGrupo;
	private CatalogoUsuario catalogoUsuarios;
	private Usuario usuarioActual = null;
	private Contacto contactoActual = null;

	private ControladorAppChat() {
		inicializarAdaptadores(); // debe ser la primera linea para evitar error
									// de sincronizaci髇
		inicializarCatalogos();
	}

	public static ControladorAppChat getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new ControladorAppChat();
		return unicaInstancia;
	}

	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuario.getUnicaInstancia();
		
	}

	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorContactoIndividual = factoria.getContactoIndividualDAO();
		adaptadorGrupo = factoria.getGrupoDAO();
	}
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	public void setUsuarioActual(Usuario u) {
			usuarioActual = u;
			System.out.println("El usuario actual para controlador es: "+ usuarioActual.getNombre());
	}

	public Contacto getContactoActual() {
		return contactoActual;
	}
	
	public void setContactoActual(Contacto u) {
			contactoActual = u;
			System.out.println("El contacto actual para controlador es: "+ contactoActual.getNombre());
	}
	
	// TODO
	public Usuario registrarUsuario(String nombre, Date fechaNacimiento, String movil, String email, String usuario,
			String contrasenia, String msgSaludo) {

		if (esUsuarioRegisrado(movil)) {
			System.out.println("Telefono Registrado");
			return null;
		}

		Usuario u = new Usuario(nombre, fechaNacimiento, movil, email, usuario, contrasenia, msgSaludo);

		if (adaptadorUsuario.registrarUsuario(u)) {
			catalogoUsuarios.addUsuario(u);
			return u;
		}

		return null;
	}

	public Usuario loginUsuario(String usuario, String clave) {

		Usuario u = CatalogoUsuario.getUnicaInstancia().getUsuarioPorNombre(usuario);
		if (u != null && u.getContrasenia().equals(clave)) {
			usuarioActual = u;
			return u;
		}
		return null;
	}

	// TODO
	public boolean esUsuarioRegisrado(String movil) {
		return CatalogoUsuario.getUnicaInstancia().getUsuario(movil) != null;
	}

	public boolean addContactoIndividual(String nombre, String movil) {
		Usuario usuario = CatalogoUsuario.getUnicaInstancia().getUsuario(movil);
		if (usuario == null || usuarioActual.getCodigo() == usuario.getCodigo())
			return false;

		ContactoIndividual contacto = usuarioActual.addContactoIndividual(nombre, movil, usuario);
		if (contacto == null)
			return false;

		adaptadorContactoIndividual.registrarContacto(contacto);
		adaptadorUsuario.modificarUsuario(usuarioActual);

		return true;
	}

	public List<String> getNombreContactos(List<Contacto> contactos) {
		return contactos.stream()
				.map(c -> c.getNombre())
				.collect(Collectors.toList());
	}

	public List<Contacto> getContactosOrdenadosPorHora() {
		return usuarioActual.getContactosOrdenadosPorHora();
	}
	
	// enviar un mensaje a un contacto (receptor)
	public void enviarMensaje(Contacto receptor, String texto, int emoticono) {
		usuarioActual.enviarMensaje(receptor, texto, emoticono);
	}
	
	// crear un nuevo grupo dado el nombre y la lista de miembros (contactosIndividuales)
	public Grupo crearGrupo(String nombreGrupo, List<ContactoIndividual> miembros) {
		Grupo grupo = new Grupo(nombreGrupo, usuarioActual);
		adaptadorGrupo.registrarContacto(grupo);
		
		grupo.addMiembros(usuarioActual, usuarioActual);
		for (ContactoIndividual c : miembros) {
			grupo.addMiembros(usuarioActual, c.getUsuario());
		}
		
		adaptadorUsuario.modificarUsuario(usuarioActual);
		return grupo;
	}
	
}

package umu.tds.controlador;

import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.stream.Collectors;

import umu.tds.JavaBean.IMensajesListener;
import umu.tds.JavaBean.MensajeEvent;
import umu.tds.catalogo.CatalogoUsuario;
import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.IContactoDAO;
import umu.tds.dao.IEstadoDAO;
import umu.tds.dao.IUsuarioDAO;
import umu.tds.diagramas.Diagrama;
import umu.tds.diagramas.Histograma;
import umu.tds.modelo.Contacto;
import umu.tds.modelo.ContactoIndividual;
import umu.tds.modelo.Estado;
import umu.tds.modelo.Grupo;
import umu.tds.modelo.Mensaje;
import umu.tds.modelo.RolUsuario;
import umu.tds.modelo.Usuario;
import umu.tds.modelo.UsuarioNormal;
import umu.tds.modelo.UsuarioPremium;

public class ControladorAppChat implements IMensajesListener{

	private static ControladorAppChat unicaInstancia;

	private IUsuarioDAO adaptadorUsuario;
	private IContactoDAO adaptadorContactoIndividual;
	private IContactoDAO adaptadorGrupo;
	private IEstadoDAO adaptadorEstado;
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
		adaptadorEstado = factoria.getEstadoDAO();
	}
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	public void setUsuarioActual(Usuario u) {
			usuarioActual = u;
			if(usuarioActual!=null)
			System.out.println("El usuario actual para controlador es: "+ usuarioActual.getNombre());
			else 
			System.out.println("Has salido de sesion AppChat");
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

		Usuario u = CatalogoUsuario.getUnicaInstancia().getUsuarioPorUsuario(usuario);
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
		
		// puede ser que un usuario se ha borrado su cuenta durante ejecucion de este
		// y hay q actualizar catalogo
		try {
			catalogoUsuarios.cargarCatalogo();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		Usuario usuario = CatalogoUsuario.getUnicaInstancia().getUsuario(movil);
		if (usuario == null || usuarioActual.getCodigo() == usuario.getCodigo())
			return false;

		return usuarioActual.addContactoIndividual(nombre, movil, usuario);
	}
	
	// eliminar un contacto individual
	public boolean deleteContactoIndividual(Contacto contacto) {
		if( usuarioActual.deleteContactoIndividual(contacto)) {
			adaptadorUsuario.modificarUsuario(usuarioActual);
			return true;
		}
		
		return false;
	}

	public List<String> getNombreContactos(List<Contacto> contactos) {
		return contactos.stream()
				.map(c -> c.getNombre())
				.collect(Collectors.toList());
	}

	public List<Contacto> getContactosOrdenadosPorHora(Usuario usuario) {
		return usuario.getContactosOrdenadosPorHora();
	}
	
	// enviar un mensaje a un contacto (receptor)
	public void enviarMensaje(Contacto receptor, String texto, int emoticono) {
		usuarioActual.enviarMensaje(receptor, texto, emoticono);
	}
	
	// crear un nuevo grupo dado el nombre y la lista de miembros (contactosIndividuales)
	public Grupo crearGrupo(String nombreGrupo, List<ContactoIndividual> miembros) {
		Grupo grupo = new Grupo(nombreGrupo, usuarioActual);
		adaptadorGrupo.registrarContacto(grupo);
		
		grupo.addMiembro(usuarioActual, usuarioActual);
		for (ContactoIndividual c : miembros) {
			grupo.addMiembro(usuarioActual, c.getUsuario());
		}
		
		return grupo;
	}
	
	
	public Grupo modificarGrupo(Grupo grupo, List<ContactoIndividual> miembros) {
		return grupo.modificarGrupo(usuarioActual, miembros);
	}
	
	public boolean addMiembroAlGrupo(ContactoIndividual contacto, Grupo grupo) {
		return grupo.addMiembro(usuarioActual, contacto.getUsuario());
	}
	
	// tambien se puede usar como para salir del grupo
	public boolean deleteMiembro(Usuario usuario, Grupo grupo) {
		return grupo.deleteMiemrbo(usuarioActual, usuario);
	}
	
	
	// crea un nuevo estado eliminando el anterior
	public boolean crearEstado(String frase, String pathImg) {
		Estado nuevoEstado = usuarioActual.crearEstado(frase, pathImg);
		if (nuevoEstado == null)
			return false;
		
		adaptadorEstado.registrarEstado(nuevoEstado);
		adaptadorUsuario.modificarUsuario(usuarioActual);
		return true;
	}
	
	//OBTENER IMAGEN DE CONTACTO
	public String getImageContacto(Contacto c) {
		if (c instanceof ContactoIndividual) {
			return ((ContactoIndividual) c).getUsuario().getPathImg();
		} else {
			return ((Grupo) c).getPathImg();
		}
	}
	
	//RETORNAR EL ULTIMO MENSAJE DE CONTACTO 
	public Mensaje getUltimoMensaje(Contacto c) {
		// TODO Auto-generated method stub
		if(c.getLastMensaje() == null) return null;
		return c.getLastMensaje();
	}
	
	
	public void cambiarFotoPerfil(String pathImg) {
		usuarioActual.setPathImg(pathImg);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}
	
	
	public void cambiarMsgSaludo(String msgSaludo) {
		usuarioActual.setMsgSaludo(msgSaludo);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}
	
	public void cambiarRolUsuario() {
		RolUsuario rol;
		usuarioActual.calcularDescuento();
		
		if (usuarioActual.isPremium()) {
			rol = new UsuarioNormal();
			rol.volverEnNormal(usuarioActual);
		} else {
			rol= new UsuarioPremium();
			rol.convertirseEnPremium(usuarioActual);
		}
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}
	
	public void eliminarUsuario() {
		usuarioActual.borrarUsuario();
		adaptadorUsuario.borrarUsuario(usuarioActual);
		catalogoUsuarios.removeUsuario(usuarioActual);
	}
	
	public boolean isAdministrador(Usuario usuario, Grupo grupo) {
		return usuario.getCodigo() == grupo.getAdmin().getCodigo();
	}
	
	@SuppressWarnings("deprecation")
	public List<Mensaje> buscarMensaje(Contacto contacto, String texto, Date fechaInicio, 
								Date fechaFin, String movil){
		if (fechaInicio != null)
			fechaInicio.setHours(fechaInicio.getHours()-24);
		return usuarioActual.buscarMensajes(contacto, texto, fechaInicio, fechaFin, movil);
	}
	
	public boolean generarEstadisticas() {
		if (usuarioActual.isPremium()) {
			Diagrama.generarDiagrama(usuarioActual);
			Histograma.generarHistograma(usuarioActual);
			return true;
		}
		return false;
	}
	
	public boolean generarPDFContactos() {
		return usuarioActual.generarPDFContactos();
	}

	public boolean existeContacto(String nombreContacto) {
		return usuarioActual.existeContacto(nombreContacto);
	}
	
	@Override
	public void nuevosMensajes(EventObject mensajeEvent) {
		MensajeEvent evento = (MensajeEvent) mensajeEvent;
		usuarioActual.importarMensajes(evento.getMensajesWhatsApp(), evento.getNombreContacto());	
	}
}

package umu.tds.practicaAppChat;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import umu.tds.catalogo.CatalogoUsuario;
import umu.tds.controlador.ControladorAppChat;
import umu.tds.dao.AdaptadorUsuarioDAO;
import umu.tds.dao.DAOException;
import umu.tds.modelo.Contacto;
import umu.tds.modelo.ContactoIndividual;
import umu.tds.modelo.Grupo;
import umu.tds.modelo.Mensaje;
import umu.tds.modelo.Usuario;

public class AppChatTests {
	
	private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	private Usuario yu, pepe, riki;
	private AdaptadorUsuarioDAO adaptadorUsuario = AdaptadorUsuarioDAO.getUnicaInstancia();
	private ControladorAppChat controlador = ControladorAppChat.getUnicaInstancia();
	
	
	
	@Before
	public void setUp() throws Exception {
		yu = new Usuario("yu", sdf.parse("12/10/1996"), "123", "yu@um.es", "yu", "123", "soy YUYE");
		pepe = new Usuario("pepe", sdf.parse("12/10/1997"), "456", "pepe@um.es", "pepe", "123", "soy PEPE");
		riki = new Usuario("riki", sdf.parse("12/10/1997"), "789", "riki@um.es", "riki", "123", "soy RIKI");
		controlador.setUsuarioActual(yu);
	}

	@After
	public void tearDown() throws Exception {
		if (yu.getCodigo() != 0)
			adaptadorUsuario.borrarUsuario(yu);
		if(pepe.getCodigo() != 0)
			adaptadorUsuario.borrarUsuario(pepe);
		if(riki.getCodigo() != 0)
			adaptadorUsuario.borrarUsuario(riki);
		
	}

	@Test
	public void testRegistrarUsuario() {
		assertTrue(adaptadorUsuario.registrarUsuario(yu));
		assertNotEquals(0, yu.getCodigo());
	}
	
	@Test
	public void testRegistroUsuarioRepetido() {
		adaptadorUsuario.registrarUsuario(yu);
		assertFalse(adaptadorUsuario.registrarUsuario(yu));
	}
	

	@Test
	public void testAddContactoIndividual() {
		adaptadorUsuario.registrarUsuario(yu);
		adaptadorUsuario.registrarUsuario(riki);
		assertTrue(yu.addContactoIndividual(riki.getNombre(), riki.getMovil(), riki));
		assertEquals(1, yu.getListaContacto().size());
	}
	
	@Test
	public void testAddContactoRepetido() {
		adaptadorUsuario.registrarUsuario(yu);
		adaptadorUsuario.registrarUsuario(riki);
		yu.addContactoIndividual(riki.getNombre(), riki.getMovil(), riki);
		assertFalse(yu.addContactoIndividual(riki.getNombre(), riki.getMovil(), riki));
	}
	
	@Test
	public void testDeleteContactoIndividual() {
		adaptadorUsuario.registrarUsuario(yu);
		adaptadorUsuario.registrarUsuario(riki);
		yu.addContactoIndividual(riki.getNombre(), riki.getMovil(), riki);
		assertTrue(yu.deleteContactoIndividual(yu.getListaContacto().get(0)));
		assertEquals(0, yu.getListaContacto().size());
	}
	
	@Test
	public void testDeleteContactoIndividualNoExistente() {
		adaptadorUsuario.registrarUsuario(yu);
		adaptadorUsuario.registrarUsuario(riki);
		assertFalse(yu.deleteContactoIndividual(
				new ContactoIndividual(riki.getNombre(), riki.getMovil(), riki)
		));
	}
	
	@Test
	public void testDeleteGrupo() {		// salir del grupo
		// registrar usuarios
		adaptadorUsuario.registrarUsuario(yu);
		adaptadorUsuario.registrarUsuario(riki);
		adaptadorUsuario.registrarUsuario(pepe);
		
		// añadir contactos
		yu.addContactoIndividual(riki.getNombre(), riki.getMovil(), riki);
		yu.addContactoIndividual(pepe.getNombre(), pepe.getMovil(), pepe);
		
		// crear la lista de contactos para registrar en el grupo
		List<ContactoIndividual> contactosDeYu = new LinkedList<ContactoIndividual>();
		for(Contacto c:yu.getListaContacto())
			contactosDeYu.add((ContactoIndividual) c);
				
		// crear el grupo
		Grupo grupo = controlador.crearGrupo("G1", contactosDeYu);
		assertNotNull(grupo);
		
		// comprobar que se ha añadido el grupo al contacto
		assertEquals(1, yu.getListaGrupo().size());
		
		// eliminar el miembro yu 
		grupo.deleteMiemrbo(yu, yu);
		
		// comprobar que yu ha salido del grupo
		assertEquals(0, yu.getListaGrupo().size());
		assertEquals(2, grupo.getMiembros().size());
	}
	
	@Test
	public void testEnviarMensajeAContactoIndividual() {
		
		adaptadorUsuario.registrarUsuario(yu);
		adaptadorUsuario.registrarUsuario(riki);
		yu.addContactoIndividual(riki.getNombre(), riki.getMovil(), riki);
		assertEquals(0, riki.getListaContacto().size());	// riki aun no tiene contacto de yu
		
		Contacto cRiki = yu.getListaContacto().get(0);
		String texto = "hola";
		
		
		try {
			CatalogoUsuario.getUnicaInstancia().cargarCatalogo();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		yu.enviarMensaje(cRiki, texto , -1);	
		
		assertEquals(1, riki.getListaContacto().size()); // riki ya tiene contacto de yu ya que ha recibido su msg
		Contacto cYu = riki.getListaContacto().get(0);
		assertEquals(1, cYu.getMensajes().size());
		
		// comprobar los datos del mensaje recibido
		Mensaje msg = cYu.getLastMensaje();
		assertEquals(yu.getMovil(), msg.getTlfEmisor());
		assertEquals(texto, msg.getTexto());
	}
	
	@Test
	public void testEnviarMensajeAGrupo() {
		// registrar usuarios
		adaptadorUsuario.registrarUsuario(yu);
		adaptadorUsuario.registrarUsuario(riki);
		adaptadorUsuario.registrarUsuario(pepe);
		
		// comprobar que los usuarios no tienen ningun grupo de contacto
		assertEquals(0, yu.getListaGrupo().size());
		assertEquals(0, riki.getListaGrupo().size());
		assertEquals(0, pepe.getListaGrupo().size());
		
		// añadir contactos
		yu.addContactoIndividual(riki.getNombre(), riki.getMovil(), riki);
		yu.addContactoIndividual(pepe.getNombre(), pepe.getMovil(), pepe);
		
		// crear la lista de contactos para registrar en el grupo
		List<ContactoIndividual> contactosDeYu = new LinkedList<ContactoIndividual>();
		for(Contacto c:yu.getListaContacto())
			contactosDeYu.add((ContactoIndividual) c);
		
		// crear el grupo
		Grupo grupo = controlador.crearGrupo("G1", contactosDeYu);
		assertNotNull(grupo);
		
		// enviar el mensaje al grupo
		String texto = "hola a todos";
		yu.enviarMensaje(grupo, texto, -1);
		
		// comprobar que los usuarios ya tienen un grupo de contacto
		assertEquals(1, yu.getListaGrupo().size());
		assertEquals(1, riki.getListaGrupo().size());
		assertEquals(1, pepe.getListaGrupo().size());
		
		// comprobar los mensajes recibido en cada miembro
		Mensaje msg = yu.getListaGrupo().get(0).getLastMensaje();
		assertEquals(yu.getMovil(), msg.getTlfEmisor());
		assertEquals(texto, msg.getTexto());
		
		msg = riki.getListaGrupo().get(0).getLastMensaje();
		assertEquals(yu.getMovil(), msg.getTlfEmisor());
		assertEquals(texto, msg.getTexto());
		
		msg = pepe.getListaGrupo().get(0).getLastMensaje();
		assertEquals(yu.getMovil(), msg.getTlfEmisor());
		assertEquals(texto, msg.getTexto());
	}
	
	@Test
	public void testCrearEstado() {
		adaptadorUsuario.registrarUsuario(yu);
		String frase = "nuevo estado";
		String pathImg = "imgs/smile.jpg";
		
		assertTrue(controlador.crearEstado(frase, pathImg));
		assertEquals(frase, yu.getEstado().getFrase());
		assertEquals(pathImg, yu.getEstado().getPathImg());
	}
	
	
	@Test
	public void testBorrarUsuario() {
		
		// registrar usuarios
		adaptadorUsuario.registrarUsuario(yu);
		adaptadorUsuario.registrarUsuario(riki);
		adaptadorUsuario.registrarUsuario(pepe);
		
		// añadir contactos
		yu.addContactoIndividual(riki.getNombre(), riki.getMovil(), riki);
		yu.addContactoIndividual(pepe.getNombre(), pepe.getMovil(), pepe);
		
		// enviar mensajes a los 2 contacots para que ellos registren el contacto yu
		yu.enviarMensaje(yu.getListaContacto().get(0), "hola", -1);
		yu.enviarMensaje(yu.getListaContacto().get(1), "hola", -1);
		
		
		// crear la lista de contactos para registrar en el grupo
		List<ContactoIndividual> contactosDeYu = new LinkedList<ContactoIndividual>();
		for(Contacto c:yu.getListaContacto())
			contactosDeYu.add((ContactoIndividual) c);
				
		// crear el grupo
		Grupo grupo = controlador.crearGrupo("G1", contactosDeYu);
		assertEquals(3, grupo.getMiembros().size());
		
		// comprobar que los usuarios riki y pepe tienen el contacto yu y el grupo
		assertEquals(yu.getMovil(), riki.getListaContacto().get(0).getNombre());
		assertEquals(yu.getMovil(), pepe.getListaContacto().get(0).getNombre());
		assertEquals("G1", riki.getListaGrupo().get(0).getNombre());
		assertEquals("G1", pepe.getListaGrupo().get(0).getNombre());
		
		// borrar el usuario
		yu.borrarUsuario();
		
		// actualizar los usuarios de la eliminacion
		adaptadorUsuario.actualizarUsuario(riki);	
		adaptadorUsuario.actualizarUsuario(pepe);
		
		// comprobar que el contacto yu ya no existen en los usuarios riki y pepe
		assertEquals(0, riki.getListaContacto().size());
		assertEquals(0, pepe.getListaContacto().size());
		
		// comprobar que el usuario yu se ha elimiando del grupo
		assertEquals(2, grupo.getMiembros().size());
	}
}

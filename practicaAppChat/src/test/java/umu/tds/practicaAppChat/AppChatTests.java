package umu.tds.practicaAppChat;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import umu.tds.dao.AdaptadorUsuarioDAO;
import umu.tds.modelo.Usuario;

public class AppChatTests {
	
	private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws ParseException {
		
		Usuario yu = new Usuario("yu", sdf.parse("12/10/1996"), "123", "yu@um.es", "yu", "123", "soy YUYE");
		Usuario pepe = new Usuario("pepe", sdf.parse("12/10/1997"), "456", "pepe@um.es", "pepe", "123", "soy PEPE");
		Usuario juan = new Usuario("juan", sdf.parse("12/10/1997"), "789", "juan@um.es", "juan", "123", "soy JUAN");
		
		AdaptadorUsuarioDAO.getUnicaInstancia().registrarUsuario(yu);
		System.out.println("Código de yu:"+ yu.getCodigo());
		AdaptadorUsuarioDAO.getUnicaInstancia().registrarUsuario(pepe);
		System.out.println("Código de pepe:"+ pepe.getCodigo());
		
		AdaptadorUsuarioDAO.getUnicaInstancia().registrarUsuario(juan);
		System.out.println("Código de juan:"+ juan.getCodigo());
		/*
		yu.setCodigo(4);
		pepe.setCodigo(20);
		
		AdaptadorUsuarioDAO.getUnicaInstancia().borrarUsuario(yu);
		AdaptadorUsuarioDAO.getUnicaInstancia().borrarUsuario(pepe);
		*/
	}
	
	

}

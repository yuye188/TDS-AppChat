package umu.tds.controlador;

import java.util.Date;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.IUsuarioDAO;
import umu.tds.modelo.CatalogoUsuario;

public class ControladorAppChat {

	private static ControladorAppChat unicaInstancia;

	private IUsuarioDAO adaptadorUsuario;
	private CatalogoUsuario catalogoUsuarios;

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
	}

	//TODO
	public boolean registrarUsuario(String nombre, Date fechaNacimiento, String movil, String email, String usuario, String contrasenia,
			String msgSaludo) {
		return false;
	}

	public boolean loginUsuario(String usuario, String clave) {
		return false;
	}
	
	//TODO
	public boolean esUsuarioRegisrado(String usuario) {
		return false;
	}

}

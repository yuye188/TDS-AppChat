package umu.tds.catalogo;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.IUsuarioDAO;
import umu.tds.modelo.Usuario;

//TODO
public class CatalogoUsuario {
	
	private static CatalogoUsuario unicaInstancia = null;

	private Map<String,Usuario> usuarioPorTelefono;
	private Map<String,Usuario> usuarioPorUsuario;
	
	private FactoriaDAO dao;
	private IUsuarioDAO adaptadorUsuario;
	
	private CatalogoUsuario() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorUsuario = dao.getUsuarioDAO();
			usuarioPorTelefono = new HashMap<String, Usuario>();
			usuarioPorUsuario = new HashMap<String, Usuario>();
			cargarCatalogo();
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static CatalogoUsuario getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new CatalogoUsuario();
		return unicaInstancia;
	}
	
	
	//Devolver usuario del HashMap por telefono
	public Usuario getUsuario(String movil) {
		return usuarioPorTelefono.get(movil);
	}
	
	//Devolver usuario por nombre de usuario
	public Usuario getUsuarioPorUsuario(String usuario) {
		return usuarioPorUsuario.get(usuario);
	}
	
	
	public void addUsuario(Usuario u) {
		usuarioPorTelefono.put(u.getMovil(), u);
		usuarioPorUsuario.put(u.getUsuario(), u);
		System.out.println("Añadido usuario a Catalogo Usuario");
	}
	
	public void removeUsuario(Usuario u) {
		usuarioPorTelefono.remove(u.getMovil());
		usuarioPorUsuario.remove(u.getUsuario());
		System.out.println("Eliminado usuario a Catalogo Usuario");
	}
	
	/*Recupera todos los usuarios para trabajar con ellos en memoria*/
	public void cargarCatalogo() throws DAOException {
		usuarioPorTelefono.clear();
		usuarioPorUsuario.clear();
		 List<Usuario> usuariosBD = adaptadorUsuario.recuperarTodosoUsuarios();
		 for (Usuario u: usuariosBD) {
			     usuarioPorTelefono.put(u.getMovil(),u);
			     usuarioPorUsuario.put(u.getUsuario(), u);
		 }
	}

}

package umu.tds.dao;

import java.util.List;

import umu.tds.modelo.Usuario;


public interface IUsuarioDAO {

	public boolean registrarUsuario(Usuario usuario);
	public void borrarUsuario(Usuario usuario);
	public void modificarUsuario(Usuario usuario);
	public Usuario recuperarUsuario(int codigo);
	public List<Usuario> recuperarTodosoUsuarios();
	public Usuario actualizarMensajes(Usuario u);
}

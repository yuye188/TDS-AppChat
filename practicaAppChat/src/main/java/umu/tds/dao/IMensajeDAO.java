package umu.tds.dao;

import java.util.List;

import umu.tds.modelo.Mensaje;


public interface IMensajeDAO {
	public boolean registrarMensaje(Mensaje mensaje);
	public void borrarMensaje(Mensaje mensaje);
	public void modificarMensaje(Mensaje mensaje);
	public Mensaje recuperarMensaje(int codigo);
	public List<Mensaje> recuperarTodosMensajes();
}

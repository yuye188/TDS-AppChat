package umu.tds.dao;

import java.util.List;

import umu.tds.modelo.Estado;


public interface IEstadoDAO {
	public boolean registrarEstado(Estado estado);
	public void borrarEstado(Estado estado);
	public void modificarEstado(Estado estado);
	public Estado recuperarEstado(int codigo);
	public List<Estado> recuperarTodosEstados();
}

package umu.tds.dao;

import java.util.List;

import umu.tds.modelo.Contacto;


public interface IContactoDAO {

	public boolean registrarContacto(Contacto contacto);
	public void borrarContacto(Contacto contacto);
	public void modificarContacto(Contacto contacto);
	public Contacto recuperarContacto(int codigo);
	public List<Contacto> recuperarTodosContactos();
}

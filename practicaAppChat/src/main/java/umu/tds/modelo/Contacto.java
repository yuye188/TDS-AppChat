package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;


public class Contacto {
	
	private int codigo;
	private String nombre;
	private List<Mensaje> mensajes;
	
	public Contacto(String nombre) {
		super();
		this.nombre = nombre;
		mensajes = new LinkedList<Mensaje>();
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
	

}

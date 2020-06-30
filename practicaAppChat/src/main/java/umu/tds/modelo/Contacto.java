package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;

import umu.tds.dao.AdaptadorMensajeDAO;



public abstract class Contacto {
	
	private int codigo;
	private String nombre;
	private List<Mensaje> mensajes;
	
	public Contacto(String nombre) {
		super();
		this.nombre = nombre;
		mensajes = new LinkedList<Mensaje>();
	}

	public static int compararContactosPorHora(Contacto c1, Contacto c2) {
		return c2.mensajes.get(c2.mensajes.size()-1).getHora()
				.compareTo(c1.mensajes.get(c1.mensajes.size()-1).getHora());
	}
	
	// enviar el mensaje y grabarlo en bd
	public void enviarMensaje(Mensaje mensaje) {
		this.mensajes.add(mensaje);
		AdaptadorMensajeDAO.getUnicaInstancia().registrarMensaje(mensaje);
		this.modificarContacto();
	}
	
	public void addNuevoMensaje(Mensaje mensaje) {
		this.mensajes.add(mensaje);
		this.mensajes.sort((m1, m2)-> m1.getHora().before(m2.getHora()) ? -1 : 1);
		modificarContacto();
	}
	
	//RETORNAR ULTIMO MENSAJE
	public Mensaje getLastMensaje() {
		// TODO Auto-generated method stub
		if(this.mensajes.isEmpty()) return null;
		return this.mensajes.get(mensajes.size()-1);
	}
	
	public abstract void modificarContacto();
	
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

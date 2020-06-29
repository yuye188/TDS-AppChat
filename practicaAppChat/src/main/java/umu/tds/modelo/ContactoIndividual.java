package umu.tds.modelo;

import umu.tds.dao.AdaptadorContactoIndividualDAO;
import umu.tds.persistencia.CatalogoUsuario;

public class ContactoIndividual extends Contacto{
	
	private String movil;
	private Usuario usuario;

	public ContactoIndividual(String nombre, String movil, Usuario usuario) {
		super(nombre);
		this.movil = movil;
		this.usuario = usuario;
	}

	// enviar el mensaje al receptor, el receptor se recibirá el mensaje y se grabará en bd
	@Override
	public void enviarMensaje(Mensaje mensaje) {
		super.enviarMensaje(mensaje);
		ContactoIndividual receptor = (ContactoIndividual) mensaje.getReceptor();
		Usuario emisor = CatalogoUsuario.getUnicaInstancia().getUsuario(mensaje.getTlfEmisor());
		receptor.getUsuario().recibirMensaje(emisor, mensaje);
	}
	
	public String getMovil() {
		return movil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public void modificarContacto() {
		AdaptadorContactoIndividualDAO.getUnicaInstancia().modificarContacto(this);
	}
	
	

}

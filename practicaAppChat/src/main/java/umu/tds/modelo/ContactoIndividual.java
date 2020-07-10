package umu.tds.modelo;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import pulsadorLuz.MensajeWhatsApp;
import umu.tds.catalogo.CatalogoUsuario;
import umu.tds.dao.AdaptadorContactoIndividualDAO;
import umu.tds.dao.AdaptadorMensajeDAO;
import umu.tds.dao.AdaptadorUsuarioDAO;
import umu.tds.modelo.Mensaje.MsgBuilder;

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
		//ContactoIndividual receptor = (ContactoIndividual) mensaje.getReceptor();
		Usuario emisor = CatalogoUsuario.getUnicaInstancia().getUsuario(mensaje.getTlfEmisor());
		AdaptadorUsuarioDAO.getUnicaInstancia().actualizarUsuario(this.usuario);
		this.usuario.recibirMensaje(emisor, mensaje);
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

	@Override
	public int importarMensajes(List<MensajeWhatsApp> mensajes, Usuario importador) {
		
		int mensajesImportados = 0;
		for (MensajeWhatsApp mensaje : mensajes) {
			
			if (mensaje.getAutor().equals(importador.getNombre())) {
				this.addMensajeWhatsapp(mensaje, importador.getMovil());
				mensajesImportados++;
			}
			
			else if (mensaje.getAutor().equals(this.usuario.getNombre())) {
				this.addMensajeWhatsapp(mensaje, this.usuario.getMovil());
				mensajesImportados++;
			}
		}
		return mensajesImportados;
	}
	
	

}

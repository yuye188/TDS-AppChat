package umu.tds.modelo;

public class ContactoIndividual extends Contacto{
	
	private String movil;
	private Usuario usuario;

	public ContactoIndividual(String nombre, String movil, Usuario usuario) {
		super(nombre);
		this.movil = movil;
		this.usuario = usuario;
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
	
	

}

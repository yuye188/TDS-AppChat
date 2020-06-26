package umu.tds.modelo;

public class ContactoIndividual extends Contacto{
	
	private int movil;
	private Usuario usuario;

	public ContactoIndividual(String nombre, int movil, Usuario usuario) {
		super(nombre);
		this.movil = movil;
		this.usuario = usuario;
	}

	public int getMovil() {
		return movil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setMovil(int movil) {
		this.movil = movil;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}

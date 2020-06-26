package umu.tds.modelo;

import java.util.List;

public class Grupo extends Contacto {

	private List<Usuario> miembros;
	private Usuario admin;
	private String pathImg;

	public static final String GroupProfile = "/imgs/groupProfile.png";

	public Grupo(String nombre, Usuario admin) {
		super(nombre);
		this.admin = admin;
		this.pathImg = GroupProfile;
	}

	public List<Usuario> getMiembros() {
		return miembros;
	}

	public Usuario getAdmin() {
		return admin;
	}

	public String getPathImg() {
		return pathImg;
	}

	public static String getGroupprofile() {
		return GroupProfile;
	}

	public void setMiembros(List<Usuario> miembros) {
		this.miembros = miembros;
	}

	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}

	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
	}

}

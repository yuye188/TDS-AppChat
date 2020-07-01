package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;

import umu.tds.dao.AdaptadorGrupoDAO;
import umu.tds.dao.AdaptadorUsuarioDAO;

public class Grupo extends Contacto {

	private List<Usuario> miembros;
	private Usuario admin;
	private String pathImg;

	public static final String GroupProfile = "imgs/groupProfile.png";

	public Grupo(String nombre, Usuario admin) {
		super(nombre);
		this.admin = admin;
		this.pathImg = GroupProfile;
		this.miembros = new LinkedList<Usuario>();
	}

	public boolean addMiembro(Usuario admin, Usuario usuario) {
		if (admin.getCodigo() == this.admin.getCodigo()) {
			this.miembros.add(usuario);
			usuario.getListaGrupo().add(this);
			modificarContacto();
			AdaptadorUsuarioDAO.getUnicaInstancia().modificarUsuario(usuario);
			return true;
		}
		
		return false;
	}
	
	public boolean isMiembro(Usuario usuario){
		return this.miembros.stream().anyMatch(u->u.getCodigo() == usuario.getCodigo());
	}
	
	// eliminar un miembro del grupo
	// si el miembro a eliminar es administrador, se establecerá el primer miembro de la lista como admin
	// si la lista se queda vacio, se elimina el grupo
	public boolean deleteMiemrbo(Usuario admin, Usuario usuario) {
		if (admin.getCodigo() == this.admin.getCodigo() || isMiembro(usuario)) {
			this.miembros.remove(usuario);
			usuario.deleteContactoGrupo(this);
			
			if (this.miembros.size() == 0) {
				AdaptadorGrupoDAO.getUnicaInstancia().borrarContacto(this);
				return true;
			}
			
			if (usuario.getCodigo() == admin.getCodigo())
				admin = this.miembros.get(0);
			
			modificarContacto();
			return true;
		}
		return false;
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

	@Override
	public void modificarContacto() {
		AdaptadorGrupoDAO.getUnicaInstancia().modificarContacto(this);
	}

}

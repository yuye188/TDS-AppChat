package umu.tds.modelo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Usuario {

	public static final String PROFILE = "imgs/profile.png";

	private int codigo; // id

	private String nombre;
	private Date fechaNacimiento;
	private String movil;
	private String usuario;
	private String contrasenia;
	private String email;
	private String pathImg;
	private boolean premium;
	private RolUsuario rol;
	private List<Contacto> listaContacto;
	private Descuento descuento;
	private List<Contacto> gruposAdmin;
	private String msgSaludo;
	private Estado estado;

	public Usuario(String nombre, Date fechaNacimiento, String movil, String email ,String usuario, 
			String contrasenia, String msgSaludo) {
		super();
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.movil = movil;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.premium = false;
		this.email = email;
		this.msgSaludo = msgSaludo;
		this.estado = new Estado(" ", Estado.ImgDefecto);
		this.listaContacto = new LinkedList<Contacto>();
		this.gruposAdmin = new LinkedList<Contacto>();
		this.descuento = new DescuentoCompuesto();
		this.pathImg = PROFILE;
	}
	

	public ContactoIndividual addContactoIndividual(String nombre, String movil, Usuario usuario) {
		boolean existe = this.listaContacto.stream()
							 .map(ContactoIndividual.class::cast)
							 .anyMatch(c -> c.getNombre().equals(nombre) || c.getMovil().equals(movil));
		if (existe)	return null;
		ContactoIndividual contacto = new ContactoIndividual(nombre, movil, usuario);
		this.listaContacto.add(contacto);
		return contacto;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public String getEmail() {
		return email;
	}

	public String getPathImg() {
		return pathImg;
	}

	public RolUsuario getRol() {
		return rol;
	}

	public List<Contacto> getListaContacto() {
		return listaContacto;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
	}

	public void setRol(RolUsuario rol) {
		this.rol = rol;
	}

	public void setListaContacto(List<Contacto> listaContacto) {
		this.listaContacto = listaContacto;
	}

	public String getMsgSaludo() {
		return msgSaludo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setMsgSaludo(String msgSaludo) {
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Descuento getDescuento() {
		return descuento;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}


	public List<Contacto> getGruposAdmin() {
		return gruposAdmin;
	}


	public void setGruposAdmin(List<Contacto> gruposAdmin) {
		this.gruposAdmin = gruposAdmin;
	}

	
}

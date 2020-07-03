package umu.tds.modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import umu.tds.catalogo.CatalogoUsuario;
import umu.tds.dao.AdaptadorContactoIndividualDAO;
import umu.tds.dao.AdaptadorEstadoDAO;
import umu.tds.dao.AdaptadorGrupoDAO;
import umu.tds.dao.AdaptadorUsuarioDAO;
import umu.tds.modelo.Mensaje.MsgBuilder;

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
	private DescuentoCompuesto descuento;
	private List<Contacto> listaGrupo;
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
		this.listaGrupo = new LinkedList<Contacto>();
		this.pathImg = PROFILE;
	}
	

	// se añadirá el contacto si no hay ninguno que tiene el mismo num.tlf
	// si coincide con un num.tlf y el nombre es diferente, se actualizará el nombre
	public boolean addContactoIndividual(String nombre, String movil, Usuario usuario) {
		for(Contacto contacto :this.listaContacto) {
			ContactoIndividual individual = (ContactoIndividual) contacto;
			if (individual.getMovil().equals(movil)) {
				if (!individual.getNombre().equals(nombre)) {
					individual.setNombre(nombre);
					AdaptadorContactoIndividualDAO.getUnicaInstancia().modificarContacto(contacto);
					AdaptadorUsuarioDAO.getUnicaInstancia().modificarUsuario(this);
					return true;
				}
				return false;
			}
		}
		ContactoIndividual contacto = new ContactoIndividual(nombre, movil, usuario);
		this.listaContacto.add(contacto);
		
		AdaptadorContactoIndividualDAO.getUnicaInstancia().registrarContacto(contacto);
		AdaptadorUsuarioDAO.getUnicaInstancia().modificarUsuario(this);
		return true;
	}
	
	
	public boolean deleteContactoGrupo(Contacto contacto) {
		if (this.listaGrupo.remove(contacto)) {
			AdaptadorUsuarioDAO.getUnicaInstancia().modificarUsuario(this);
			return true;
		}
		return false;
	}
	
	public boolean deleteContactoIndividual(Contacto contacto) {
		
		if (this.listaContacto.contains(contacto)) {
			this.listaContacto.remove(contacto);
			AdaptadorContactoIndividualDAO.getUnicaInstancia().borrarContacto(contacto);
			return true;
		}

		return false;
	}
	
	public boolean deleteContactoIndividualPorMovil(String movil) {
		Contacto contacto = this.listaContacto.stream()
										.map(ContactoIndividual.class::cast)
										.filter(c -> c.getMovil().equals(movil))
										.findFirst().orElse(null);
		
		if(this.deleteContactoIndividual(contacto)) {
			AdaptadorUsuarioDAO.getUnicaInstancia().modificarUsuario(this);
			return true;
		}
		return false;
	}
	
	
	public List<Contacto> getContactosOrdenadosPorHora(){
		
		List<Contacto> contactos = new LinkedList<Contacto>();
		contactos.addAll(this.getListaContacto());
		contactos.addAll(this.getListaGrupo());
		
		
		Collections.sort(contactos, (c1, c2) -> {
			if (c2.getMensajes().isEmpty() && c1.getMensajes().isEmpty()) 
				return 0;
			if (c2.getMensajes().isEmpty()) 
				return -1;
			if (c1.getMensajes().isEmpty()) 
				return 1;
			return Contacto.compararContactosPorHora(c1, c2);
		});

		return contactos;
	}
	
	// enviar un mensaje a un receptor
	public void enviarMensaje(Contacto receptor, String texto, int emoticono) {
		receptor.enviarMensaje(MsgBuilder.createBuilder(this.getMovil(), receptor)
										 .setTexto(texto)
										 .setEmoticon(emoticono)
										 .build());
	}
	
	public void recibirMensaje(Usuario emisor, Mensaje mensaje) {
		
		ContactoIndividual contactoIndividual;
		
		// buscar si el mensaje viene de un contacto existente
		for (Contacto c: this.listaContacto) {
			contactoIndividual = (ContactoIndividual) c;
			if (contactoIndividual.getMovil().equals(emisor.getMovil())) {
				contactoIndividual.addNuevoMensaje(mensaje);
				AdaptadorUsuarioDAO.getUnicaInstancia().modificarUsuario(this);
				return;
			}
		}
		
		// si no existe el contacto se crea (cuyo nombre será el tlf del emisor) y se añade a la lista de contactos individuales
		String tlf = mensaje.getTlfEmisor();
		contactoIndividual = new ContactoIndividual(tlf, tlf, CatalogoUsuario.getUnicaInstancia().getUsuario(tlf));
		this.listaContacto.add(contactoIndividual);
		
		AdaptadorContactoIndividualDAO.getUnicaInstancia().registrarContacto(contactoIndividual);
		AdaptadorUsuarioDAO.getUnicaInstancia().modificarUsuario(this);
		
		contactoIndividual.addNuevoMensaje(mensaje);
	}
	
	// comprueba si pathImg es un fichero y si lo es, crea el nuevo estado elimiando el anterior
	public Estado crearEstado(String frase, String pathImg) {
		
		File f = new File(pathImg);
		if (!f.exists() || !f.isFile())
			return null;
		
		AdaptadorEstadoDAO.getUnicaInstancia().borrarEstado(estado);
		estado = new Estado(frase, pathImg);
		return estado;
	}
	
	
	public List<Grupo> ordenarGruposSegunMensajes() {
		return this.listaGrupo.stream()
				.map(g->(Grupo) g)
				.sorted(Comparator.comparingInt(g -> g.getMensajesEnviados(this).size())).limit(6)
				.collect(Collectors.toList());
	}
	
	public List<Integer> calcularNumMsgPorMeses() {
		List<Integer> listaValores = new LinkedList<Integer>();
		
		Calendar inicio = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		
		
		for (int i = 0; i < 12; i++) {
			
			inicio.set(Calendar.MONTH, i);
			inicio.set(Calendar.DAY_OF_MONTH, 1);
			
			fin.set(Calendar.MONTH, i);
			fin.set(Calendar.DAY_OF_MONTH, fin.getMaximum(Calendar.DAY_OF_MONTH));
			
			Integer numMsgCI = this.listaContacto.stream()
										.mapToInt(c->this.buscarMensajes(c, "", inicio.getTime(), fin.getTime(), "").size())
										.sum();
			
			Integer numMsgGrupo = this.listaGrupo.stream()
										.mapToInt(c->this.buscarMensajes(c, "", inicio.getTime(), fin.getTime(), "").size())
										.sum();
			
			listaValores.add(numMsgCI+numMsgGrupo);
		}

		return listaValores;
	}
	
	public void calcularDescuento() {
		
		this.descuento = new DescuentoCompuesto();
		
		if (DescuentoFechas.isEntreFechas()) {
			DescuentoFechas desFechas = new DescuentoFechas();
			this.descuento.addDescuento(desFechas);
		}
		
		if(DescuentoJovenes.isMenor(this.getFechaNacimiento())) {
			DescuentoJovenes desJoven = new DescuentoJovenes();
			this.descuento.addDescuento(desJoven);
		}
		
		if (DescuentoNumMsg.superaNumMensajes(this.calcularNumMsgTotal())) {
			DescuentoNumMsg desNumMsg = new DescuentoNumMsg();
			this.descuento.addDescuento(desNumMsg);
		}
	}
	
	
	public int calcularNumMsgTotal() {
		int total = this.getListaContacto().stream()
											.mapToInt(c->c.getMensajes().size())
											.sum();
		total += this.getListaGrupo().stream()
											.mapToInt(c->c.getMensajes().size())
											.sum();
		return total;
	}
	
	
	
	public void borrarUsuario() {
		for(Contacto c: this.listaContacto) {
			((ContactoIndividual)c).getUsuario().deleteContactoIndividualPorMovil(this.movil);
		}
		
		for(Contacto c:this.listaGrupo) {
			Grupo grupo = (Grupo) c;
			grupo.deleteMiemrbo(grupo.getAdmin(), this);
		}
	}
	
	
	public List<Mensaje> buscarMensajes(Contacto contacto, String texto, Date fechaIni, 
			Date fechaFin, String movil){
		
		if (contacto.getClass() == ContactoIndividual.class) {
			return contacto.getMensajes().stream()
									 .filter(m->texto.isEmpty() || m.getTexto().contains(texto))
									 .filter(m->fechaIni==null || m.getHora().after(fechaIni))
									 .filter(m->fechaFin==null || m.getHora().before(fechaFin))
									 .collect(Collectors.toList());
		}
		
		return contacto.getMensajes().stream()
									 .filter(m->texto.isEmpty() || m.getTexto().contains(texto))
									 .filter(m->fechaIni== null || m.getHora().after(fechaIni))
									 .filter(m->fechaFin==null || m.getHora().before(fechaFin))
									 .filter(m->movil.isEmpty() || m.getTlfEmisor().equals(movil))
									 .collect(Collectors.toList());
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
		this.msgSaludo = msgSaludo;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Descuento getDescuento() {
		return descuento;
	}

	public void setDescuento(DescuentoCompuesto descuento) {
		this.descuento = descuento;
	}


	public List<Contacto> getListaGrupo() {
		return listaGrupo;
	}


	public void setListaGrupo(List<Contacto> gruposAdmin) {
		this.listaGrupo = gruposAdmin;
	}

	
}

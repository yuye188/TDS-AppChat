package umu.tds.modelo;

import java.util.Date;


public class Mensaje {
	
	private int codigo; // id
	
	private String texto;
	private Date hora;
	private int emoticon;
	private String tlfEmisor;
	private Contacto receptor;
	
	private Mensaje(MsgBuilder builder) {
		this.texto = builder.texto;
		this.hora = builder.hora;
		this.emoticon = builder.emoticon;
		this.tlfEmisor = builder.tlfEmisor;
		this.receptor = builder.receptor;
	}
	
	public static class MsgBuilder {
		
		private String texto;
		private Date hora;
		private int emoticon;
		private String tlfEmisor;
		private Contacto receptor;
		
		private MsgBuilder(String tlfEmisor, Contacto receptor) {
			this.tlfEmisor = tlfEmisor;
			this.receptor = receptor;
			this.texto ="";
			this.hora = new Date(System.currentTimeMillis());
			this.emoticon = -1;
		}
		
		public static MsgBuilder createBuilder(String tlfEmisor, Contacto receptor) {
			return new MsgBuilder(tlfEmisor, receptor);
		}
		
		public MsgBuilder setTexto(String texto) {
			this.texto = texto;
			return this;
		}
		
		public MsgBuilder setEmoticon(int emoticon) {
			this.emoticon = emoticon;
			return this;
		}
		
		public Mensaje build() {
			return new Mensaje(this);
		}
	}

	public int getCodigo() {
		return codigo;
	}

	public String getTexto() {
		return texto;
	}

	public Date getHora() {
		return hora;
	}

	public int getEmoticon() {
		return emoticon;
	}

	public Contacto getReceptor() {
		return receptor;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public void setEmoticon(int emoticon) {
		this.emoticon = emoticon;
	}

	public String getTlfEmisor() {
		return tlfEmisor;
	}

	public void setTlfEmisor(String tlfEmisor) {
		this.tlfEmisor = tlfEmisor;
	}

	public void setReceptor(Contacto receptor) {
		this.receptor = receptor;
	}
	
	

}

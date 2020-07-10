package pulsadorLuz;

import java.util.EventObject;
import java.util.List;

public class MensajeEvent extends EventObject {
	
	List<MensajeWhatsApp> mensajesWhatsApp;
	String nombreContacto;

	public MensajeEvent(Object fuente, List<MensajeWhatsApp> mensajesWhatsApp, String nombreContacto) {
		super(fuente);
		this.mensajesWhatsApp = mensajesWhatsApp;
		this.nombreContacto = nombreContacto;
	}

	public List<MensajeWhatsApp> getMensajesWhatsApp() {
		return mensajesWhatsApp;
	}

	public String getNombreContacto() {
		return this.nombreContacto;
	}
}

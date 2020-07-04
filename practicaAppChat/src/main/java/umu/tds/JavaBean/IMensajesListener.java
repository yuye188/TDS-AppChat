package umu.tds.JavaBean;

import java.util.EventListener;
import java.util.EventObject;

public interface IMensajesListener extends EventListener{
	public void nuevosMensajes(EventObject mensajeEvent);
}

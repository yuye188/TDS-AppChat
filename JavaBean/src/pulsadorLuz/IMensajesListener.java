package pulsadorLuz;

import java.util.EventListener;
import java.util.EventObject;

public interface IMensajesListener extends EventListener{
	public void nuevosMensajes(EventObject mensajeEvent);
}

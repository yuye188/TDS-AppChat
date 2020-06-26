package umu.tds.modelo;

public class DescuentoNumMsg implements Descuento{

	public static final int NUM_MENSAJES = 10;
	
	public double calcularDescuento() {
		return PRECIO_BASE * 0.1;
	}
	
	// se aplicará descuento si el número de mensajes enviado superado el NUM_MENSAJES
	public static boolean superaNumMensajes(int nMensajes) {
		return nMensajes > NUM_MENSAJES;
	}
}

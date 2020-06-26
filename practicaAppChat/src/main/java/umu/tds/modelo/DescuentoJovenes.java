package umu.tds.modelo;

import java.util.Date;

public class DescuentoJovenes implements Descuento{

	public static final int EDAD = 25;
	
	public double calcularDescuento() {
		return PRECIO_BASE * 0.3;
	}
	
	// se aplicará descuento si la edad de usuario es menor que EDAD
	@SuppressWarnings("deprecation")
	public static boolean isMenor(Date fechaNacimiento) {
		Date now = new Date(System.currentTimeMillis());
		if (now.getYear()-fechaNacimiento.getYear() < EDAD)
			return true;
		
		return false;
	}
}

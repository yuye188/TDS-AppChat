package umu.tds.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DescuentoFechas implements Descuento {

	public static final String FECHA_INICIO = "2020-06-30";
	public static final String FECHA_FIN = "2020-07-30";
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public double calcularDescuento() {
		return PRECIO_BASE * 0.2;
	}

	// se aplicará descuento si la fecha actual se encuentra en el periodo de oferta
	public static boolean isEntreFechas() {
		try {
			Date inicio = sdf.parse(FECHA_INICIO);
			Date fin = sdf.parse(FECHA_FIN);
			Date now = new Date(System.currentTimeMillis());
			
			if (now.after(inicio) && now.before(fin))
				return true;
			
		} catch (ParseException e) {
			System.err.println("Error en parser");
			e.printStackTrace();
		}
		
		return false;
	}

}

package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;

public class DescuentoCompuesto implements Descuento {

	private List<Descuento> descuentos;

	public DescuentoCompuesto() {
		this.descuentos = new LinkedList<Descuento>();
	}

	public double calcularDescuento() {
		
		double precioFinal = PRECIO_BASE;
		
		for (Descuento descuento : descuentos)
			precioFinal -= descuento.calcularDescuento(); // descontar el precioFinal para cada descuento
		
		return precioFinal;
	}

	public void addDescuento(Descuento descuento) {
		if (!this.descuentos.contains(descuento))
			this.descuentos.add(descuento);
	}

}

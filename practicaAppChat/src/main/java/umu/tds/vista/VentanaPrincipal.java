package umu.tds.vista;

import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int ancho = 640;
	private static final int alto = 640;

	public VentanaPrincipal() {
		setSize(ancho, alto);
		setLocationRelativeTo(null);
		setTitle("AppChat");
	}
}

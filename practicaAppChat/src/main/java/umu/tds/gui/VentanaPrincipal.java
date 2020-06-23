package umu.tds.gui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;

public class VentanaPrincipal extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int ancho = 400;
	private static final int alto = 400;

	public VentanaPrincipal() {
		setSize(ancho, alto);
		setLocationRelativeTo(null);
		setTitle("AppChat");
	}
}

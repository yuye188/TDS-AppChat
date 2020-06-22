package umu.tds.vista;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.FlowLayout;


public class VentanaLogin extends Ventana {

	private static final int ancho = 640;
	private static final int alto = 640;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public VentanaLogin() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

	}

	@Override
	protected void crearPantalla() {
		setSize(ancho, alto );
		
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		// TODO Auto-generated method stub
		
	}

}

package umu.tds.gui;

import javax.swing.JFrame;

import umu.tds.modelo.Usuario;

public class VentanaPrincipal{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int VENTANA_LOGIN = 1;
	public static final int VENTANA_REGISTRO = 2;
	public static final int VENTANA_CHAT_PRINCIPAL = 3;
	
	private VentanaLogin vLogin;
	private VentanaRegistro vRegistro;

	public VentanaPrincipal() {
		/* crear pantallas */
		vLogin = new VentanaLogin();
	}

	public static void main(String[] args) {
		VentanaPrincipal principal = new VentanaPrincipal();
	}

	public void mostrarVentana(int valor) {

		if (valor == VENTANA_LOGIN) {
			//vLogin = new VentanaLogin(this);
			vLogin = new VentanaLogin();
		}

		if (valor == VENTANA_REGISTRO) {
			new VentanaRegistro(this);
		}
		
	}
	
	public void mostrarVentanaChat(VentanaPrincipal v, Usuario u) {
		Ventana vChat = new VentanaChat(v, u);
		
	}
	
	public void mostrarVentanaEstado() {
	}
	
	public void mostrarContacto(VentanaPrincipal v, Usuario u) {
		VentanaContacto c = new VentanaContacto();
	}
	
	
}

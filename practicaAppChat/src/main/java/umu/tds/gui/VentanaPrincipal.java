package umu.tds.gui;

import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame {

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
		vLogin = new VentanaLogin(this);
	}

	public static void main(String[] args) {
		VentanaPrincipal principal = new VentanaPrincipal();
	}

	public void mostrarVentana(int valor) {
		System.out.println("Aqui llego mostrar pantalla " + valor);

		if (valor == VENTANA_LOGIN) {
			vLogin.mostrarVentana(true);
		}

		if (valor == VENTANA_REGISTRO) {
			new VentanaRegistro(this);
		}
		
		if(valor == VENTANA_CHAT_PRINCIPAL) {
			new VentanaChat();
		}

	}
}

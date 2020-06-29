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
		System.out.println("Aqui llego mostrar pantalla " + valor);

		if (valor == VENTANA_LOGIN) {
			//vLogin = new VentanaLogin(this);
			vLogin = new VentanaLogin();
		}

		if (valor == VENTANA_REGISTRO) {
			new VentanaRegistro(this);
		}
		
	}
	
	public void mostrarVentanaChat(VentanaPrincipal v, Usuario u) {
		System.out.println("Mostrar Ventana Chat");
		Ventana vChat = new VentanaChat(v, u);
		
	}
	
	public void mostrarVentanaEstado() {
		System.out.println("Mostrar Ventana Estado");
	}
	
	public void mostrarContacto(VentanaPrincipal v, Usuario u) {
		System.out.println("Mostrar Ventana Contacto");
		VentanaContacto c = new VentanaContacto();
	}
	
	/*public void mostrarVentanaEstado() {
		System.out.println("Mostrar Ventana Estado");
	}*/
	
}

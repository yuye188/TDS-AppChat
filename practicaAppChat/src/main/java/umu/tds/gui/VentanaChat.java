package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaChat extends Ventana {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frameChat;

	public VentanaChat() {
		// TODO Auto-generated constructor stub
		crearPantalla();
		mostrarVentana(true);
	}
	
	@Override
	protected void crearPantalla() {
		// TODO Auto-generated method stub
		
		frameChat = new JFrame();
		frameChat.setTitle("AppChat");
		frameChat.setSize(450, 320);
		frameChat.setLocationRelativeTo(null);
		frameChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameChat.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblBienvenidoAlChat = new JLabel("Bienvenido Al chat Principal");
		frameChat.getContentPane().add(lblBienvenidoAlChat, BorderLayout.NORTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarVentana(boolean b) {
		// TODO Auto-generated method stub
		
	}


}

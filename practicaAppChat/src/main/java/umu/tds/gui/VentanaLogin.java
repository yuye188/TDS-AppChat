package umu.tds.gui;

import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import umu.tds.controlador.ControladorAppChat;
import umu.tds.modelo.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;


public class VentanaLogin extends Ventana {

	private static final int ancho = 640;
	private static final int alto = 640;
	
	private static final long serialVersionUID = 1L;
	
	private VentanaPrincipal ventana;
	private JFrame frmLoginAppChat;
	private JTextField textLogin;
	private JPasswordField textPassword;
	
	private JButton btnLogin, btnRegistro, btnSalir;

	public VentanaLogin(VentanaPrincipal v) {
		ventana = v;
		crearPantalla();
		mostrarVentana(frmLoginAppChat);
	}

	public VentanaLogin() {
		crearPantalla();
		mostrarVentana(frmLoginAppChat);
	}
	
	@Override
	protected void crearPantalla() {
		//setSize(ancho, alto );
		
		frmLoginAppChat = new JFrame();
		frmLoginAppChat.setTitle("Login AppChat");
		frmLoginAppChat.setSize(450, 320);
		frmLoginAppChat.setLocationRelativeTo(null);
		frmLoginAppChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginAppChat.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel_Norte = new JPanel();
		panel_Norte.setPreferredSize(new Dimension(100, 70));
		frmLoginAppChat.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		panel_Norte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));

		JLabel lblAppChat = new JLabel("AppChat");
		lblAppChat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAppChat.setForeground(Color.DARK_GRAY);
		panel_Norte.add(lblAppChat);

		JPanel panel_Oeste = new JPanel();
		frmLoginAppChat.getContentPane().add(panel_Oeste, BorderLayout.WEST);
		panel_Oeste.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		panel_Oeste.add(rigidArea_4);

		JPanel panel_Centro = new JPanel();
		frmLoginAppChat.getContentPane().add(panel_Centro, BorderLayout.CENTER);
		panel_Centro.setLayout(new GridLayout(4, 2, 0, 0));

		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		panel_Centro.add(rigidArea_3);

		JPanel panel = new JPanel();
		panel_Centro.add(panel);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setPreferredSize(new Dimension(56, 14));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblNewLabel);

		textLogin = new JTextField();
		panel.add(textLogin);
		textLogin.setColumns(15); // ancho columna a 15

		JPanel panel_1 = new JPanel();
		panel_Centro.add(panel_1);

		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblNewLabel_1);

		textPassword = new JPasswordField();
		panel_1.add(textPassword);
		textPassword.setColumns(15);

		JPanel panel_Sur = new JPanel();
		frmLoginAppChat.getContentPane().add(panel_Sur, BorderLayout.SOUTH);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setPreferredSize(new Dimension(40, 40));
		panel_Sur.add(rigidArea);

		btnLogin = new JButton("Login");
		btnLogin.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_Sur.add(btnLogin);
		
		btnRegistro = new JButton("Registro");
		btnRegistro.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_Sur.add(btnRegistro);
		
		btnSalir = new JButton("Salir");
		btnSalir.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_Sur.add(btnSalir);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_1.setPreferredSize(new Dimension(40, 40));
		panel_Sur.add(rigidArea_1);
		
		//AÑADIR MANEJADORES 
		btnLogin.addActionListener(this);
		btnRegistro.addActionListener(this);
		btnSalir.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btnLogin) {
			
			Usuario u = ControladorAppChat.getUnicaInstancia().loginUsuario(textLogin.getText(), new String(textPassword.getPassword()));
			if(u != null) {
				JOptionPane.showMessageDialog(frmLoginAppChat, "Login User: " + textLogin.getText() + "!", "User Login",
						JOptionPane.INFORMATION_MESSAGE);
				
				liberarVentana(frmLoginAppChat);
				//ventana.mostrarVentanaChat(ventana, u);
				new VentanaChat(u);
			}
			else {
				JOptionPane.showMessageDialog(frmLoginAppChat, "Nombre de usuario o contrase\u00F1a no valido", "User Login",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		if(e.getSource() == btnRegistro) {
			liberarVentana(frmLoginAppChat);
			new VentanaRegistro();
		}

		if(e.getSource() == btnSalir) {
			liberarVentana(frmLoginAppChat);
			System.exit(0);
		}
	}	
}

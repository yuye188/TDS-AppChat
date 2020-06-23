package umu.tds.gui;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;

public class VentanaRegistro extends Ventana {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private VentanaPrincipal ventana;
	
	private JFrame jframe;
	private JTextField nombre, telefono, email, usuario;
	private JDateChooser dateChooser;
	private JPasswordField clave1, clave2;
	private JLabel wNombre, wTelefono, wFecha, wEmail, wUsuario, wClave, wCampos;
	private JButton btnRegistrar, btnCancelar;

	public VentanaRegistro(VentanaPrincipal v){
		ventana=v; 
		crearPantalla();
		jframe.setVisible(true);
	}

	protected void crearPantalla() {

		jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(480, 360);
		jframe.setTitle("Registro Usuario");
		jframe.getContentPane().setLayout(new BorderLayout());
		
		JPanel panel_norte = new JPanel();
		jframe.getContentPane().add(panel_norte, BorderLayout.NORTH);
		
		JLabel lblDatosRegistroUsuario = new JLabel("Datos Registro Usuario");
		lblDatosRegistroUsuario.setFont(new Font("Calibri", Font.PLAIN, 26));
		panel_norte.add(lblDatosRegistroUsuario);
		
		JPanel panel_este = new JPanel();
		jframe.getContentPane().add(panel_este, BorderLayout.WEST);
		
		Component rigidArea_este = Box.createRigidArea(new Dimension(25, 25));
		panel_este.add(rigidArea_este);
		
		JPanel panel_central = new JPanel();
		jframe.getContentPane().add(panel_central, BorderLayout.CENTER);
		GridBagLayout gbl_panel_central = new GridBagLayout();
		gbl_panel_central.columnWidths = new int[] {0, 100, 90, 50, 90, 24, 0, 0, 1};
		gbl_panel_central.rowHeights = new int[] {0, 0, 25, 25, 25, 25, 25, 25, 25, 1};
		gbl_panel_central.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_central.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_central.setLayout(gbl_panel_central);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		panel_central.add(lblNombre, gbc_lblNombre);
		
		nombre = new JTextField();
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.gridwidth = 3;
		gbc_nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombre.insets = new Insets(0, 0, 5, 5);
		gbc_nombre.gridx = 2;
		gbc_nombre.gridy = 1;
		panel_central.add(nombre, gbc_nombre);
		nombre.setColumns(10);
		
		wNombre = new JLabel("*");
		wNombre.setForeground(Color.RED);
		GridBagConstraints gbc_warningNombre = new GridBagConstraints();
		gbc_warningNombre.insets = new Insets(0, 0, 5, 5);
		gbc_warningNombre.gridx = 5;
		gbc_warningNombre.gridy = 1;
		panel_central.add(wNombre, gbc_warningNombre);
		
		JLabel lblTelfono = new JLabel("Teléfono:");
		GridBagConstraints gbc_lblTelfono = new GridBagConstraints();
		gbc_lblTelfono.anchor = GridBagConstraints.EAST;
		gbc_lblTelfono.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelfono.gridx = 1;
		gbc_lblTelfono.gridy = 2;
		panel_central.add(lblTelfono, gbc_lblTelfono);
		
		telefono = new JTextField();
		GridBagConstraints gbc_telefono = new GridBagConstraints();
		gbc_telefono.gridwidth = 3;
		gbc_telefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefono.insets = new Insets(0, 0, 5, 5);
		gbc_telefono.gridx = 2;
		gbc_telefono.gridy = 2;
		panel_central.add(telefono, gbc_telefono);
		telefono.setColumns(10);
		
		wTelefono = new JLabel("*");
		wTelefono.setForeground(Color.RED);
		GridBagConstraints gbc_warningTelefono = new GridBagConstraints();
		gbc_warningTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_warningTelefono.gridx = 5;
		gbc_warningTelefono.gridy = 2;
		panel_central.add(wTelefono, gbc_warningTelefono);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha nacimiento:");
		GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
		gbc_lblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNacimiento.gridx = 1;
		gbc_lblFechaNacimiento.gridy = 3;
		panel_central.add(lblFechaNacimiento, gbc_lblFechaNacimiento);
		
		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 2;
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 3;
		panel_central.add(dateChooser, gbc_dateChooser);
		
		wFecha = new JLabel("*");
		wFecha.setForeground(Color.RED);
		GridBagConstraints gbc_warningFecha = new GridBagConstraints();
		gbc_warningFecha.insets = new Insets(0, 0, 5, 5);
		gbc_warningFecha.gridx = 5;
		gbc_warningFecha.gridy = 3;
		panel_central.add(wFecha, gbc_warningFecha);
		
		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 4;
		panel_central.add(lblEmail, gbc_lblEmail);
		
		email = new JTextField();
		GridBagConstraints gbc_email = new GridBagConstraints();
		gbc_email.gridwidth = 2;
		gbc_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_email.insets = new Insets(0, 0, 5, 5);
		gbc_email.gridx = 2;
		gbc_email.gridy = 4;
		panel_central.add(email, gbc_email);
		email.setColumns(10);
		
		wEmail = new JLabel("*");
		wEmail.setForeground(Color.RED);
		GridBagConstraints gbc_warningEmail = new GridBagConstraints();
		gbc_warningEmail.insets = new Insets(0, 0, 5, 5);
		gbc_warningEmail.gridx = 5;
		gbc_warningEmail.gridy = 4;
		panel_central.add(wEmail, gbc_warningEmail);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 5;
		panel_central.add(lblUsuario, gbc_lblUsuario);
		
		usuario = new JTextField();
		GridBagConstraints gbc_usuario = new GridBagConstraints();
		gbc_usuario.gridwidth = 2;
		gbc_usuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_usuario.insets = new Insets(0, 0, 5, 5);
		gbc_usuario.gridx = 2;
		gbc_usuario.gridy = 5;
		panel_central.add(usuario, gbc_usuario);
		usuario.setColumns(10);
		
		wUsuario = new JLabel("*");
		wUsuario.setForeground(Color.RED);
		GridBagConstraints gbc_warningUsuario = new GridBagConstraints();
		gbc_warningUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_warningUsuario.gridx = 5;
		gbc_warningUsuario.gridy = 5;
		panel_central.add(wUsuario, gbc_warningUsuario);
		
		JLabel lblClave = new JLabel("Clave:");
		GridBagConstraints gbc_lblClave = new GridBagConstraints();
		gbc_lblClave.anchor = GridBagConstraints.EAST;
		gbc_lblClave.insets = new Insets(0, 0, 5, 5);
		gbc_lblClave.gridx = 1;
		gbc_lblClave.gridy = 6;
		panel_central.add(lblClave, gbc_lblClave);
		
		clave1 = new JPasswordField();
		clave1.setColumns(10);
		GridBagConstraints gbc_clave1 = new GridBagConstraints();
		gbc_clave1.fill = GridBagConstraints.HORIZONTAL;
		gbc_clave1.insets = new Insets(0, 0, 5, 5);
		gbc_clave1.gridx = 2;
		gbc_clave1.gridy = 6;
		panel_central.add(clave1, gbc_clave1);
		
		JLabel lblRepite = new JLabel("Repite:");
		GridBagConstraints gbc_lblRepite = new GridBagConstraints();
		gbc_lblRepite.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepite.gridx = 3;
		gbc_lblRepite.gridy = 6;
		panel_central.add(lblRepite, gbc_lblRepite);
		
		clave2 = new JPasswordField();
		clave2.setColumns(10);
		GridBagConstraints gbc_clave2 = new GridBagConstraints();
		gbc_clave2.insets = new Insets(0, 0, 5, 5);
		gbc_clave2.fill = GridBagConstraints.HORIZONTAL;
		gbc_clave2.gridx = 4;
		gbc_clave2.gridy = 6;
		panel_central.add(clave2, gbc_clave2);
		
		wClave = new JLabel("*");
		wClave.setForeground(Color.RED);
		GridBagConstraints gbc_warningClave = new GridBagConstraints();
		gbc_warningClave.insets = new Insets(0, 0, 5, 5);
		gbc_warningClave.gridx = 5;
		gbc_warningClave.gridy = 6;
		panel_central.add(wClave, gbc_warningClave);
		
		btnRegistrar = new JButton("Registrar");
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.fill = GridBagConstraints.BOTH;
		gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrar.gridx = 2;
		gbc_btnRegistrar.gridy = 7;
		panel_central.add(btnRegistrar, gbc_btnRegistrar);
		
		btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 7;
		panel_central.add(btnCancelar, gbc_btnCancelar);
		
		wCampos = new JLabel("Los campos * son obligatorios");
		wCampos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		wCampos.setForeground(Color.RED);
		GridBagConstraints gbc_lblLosCampos = new GridBagConstraints();
		gbc_lblLosCampos.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLosCampos.gridwidth = 4;
		gbc_lblLosCampos.insets = new Insets(0, 0, 0, 5);
		gbc_lblLosCampos.gridx = 2;
		gbc_lblLosCampos.gridy = 8;
		panel_central.add(wCampos, gbc_lblLosCampos);
		
		JPanel panel_oeste = new JPanel();
		jframe.getContentPane().add(panel_oeste, BorderLayout.EAST);
		
		Component rigidArea_oeste = Box.createRigidArea(new Dimension(25, 25));
		panel_oeste.add(rigidArea_oeste);
		
		JPanel panel_sur = new JPanel();
		jframe.getContentPane().add(panel_sur, BorderLayout.SOUTH);
		
		Component rigidArea_sur = Box.createRigidArea(new Dimension(20, 20));
		panel_sur.add(rigidArea_sur);
		
		ocultarErrores();
		
		ventana.revalidate(); /*redibujar con el nuevo JPanel*/
		ventana.repaint();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//TODO 
		//Analizar tipo de evento 
		if(e.getSource() == btnRegistrar) {
			
		}
		else if(e.getSource() == btnCancelar) {
			
		}
		
	}
	
	
	/**
	 * Comprueba que los campos de dato de registro estan bien
	 */
	
	private boolean checkFields() {
		boolean ok=true;
		
		/*borrar todos los errores en pantalla*/
		ocultarErrores();
		
		if (nombre.getText().trim().isEmpty()) {
			wNombre.setVisible(true); 
			ok=false;
		}

		if (telefono.getText().trim().isEmpty()) {
			wTelefono.setVisible(true); 
			ok=false;
		}
		
		if (email.getText().trim().isEmpty()) {
			wEmail.setVisible(true); 
			ok=false;
		}
		
		if (usuario.getText().trim().isEmpty()) {
			wUsuario.setVisible(true); 
			ok=false;
		}
		
		if (dateChooser.getDate().toString().isEmpty()) {
			wFecha.setVisible(true);
			ok = false;
		}
		
		String password = new String(clave1.getPassword());
		String password2 = new String(clave2.getPassword());
		
		if (password.equals("")) {
			wClave.setVisible(true); 
			ok=false;
		} 
		
		if (!ok) wCampos.setVisible(true);
		
		if (ok && !password.equals(password2)) {
			wClave.setVisible(true);
			ok=false;
		}
		/* Comprobar que no exista otro usuario con igual login */
		
		/*if (ControladorAsistentes.getUnicaInstancia().esAsistenteRegistrado(usuario.getText())) {
			warningExiste.setVisible(true); 
			ok=false;		
		}*/
		return ok;
	}

	
	/**
	 * Oculta todos los errores que pueda haber en la pantalla
	 */
	private void ocultarErrores() {
		wCampos.setVisible(false);
		wNombre.setVisible(false);
		wTelefono.setVisible(false);
		wFecha.setVisible(false);
		wEmail.setVisible(false);
		wUsuario.setVisible(false);
		wClave.setVisible(false);
	}

}

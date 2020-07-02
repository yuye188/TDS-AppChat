package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import umu.tds.controlador.ControladorAppChat;
import umu.tds.modelo.Usuario;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaSettings extends Ventana{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame fSet;
	private ControladorAppChat unica = ControladorAppChat.getUnicaInstancia();
	private Usuario actual;
	
	private int size = 45;

	private JButton btnNombreUsuario;
	private JLabel lblNewLabel;
	private JButton btnCambairIcono;
	private JButton btnCambiarSaludo;
	private JButton btnPremium;
	private JButton btnEsta;
	private JButton btnSalirSesion;
	private JButton btnBorrarCuenta;

	public VentanaSettings(Usuario u) {
	
		System.out.println("Creando Ventana Setting");
		System.out.println("El usuario anterior es:" + unica.getUsuarioActual().getNombre());
		actual = u;
		unica.setUsuarioActual(actual);
		System.out.println("El usuario actual es:" + unica.getUsuarioActual().getNombre());
		
		crearPantalla();
		mostrarVentana(fSet);
	}

	@Override
	protected void crearPantalla() {
		// TODO Auto-generated method stub
		fSet = new JFrame();
		fSet.setTitle("AppChat");
		fSet.setSize(Ventana.SIZE_X, Ventana.SIZE_Y);
		fSet.setLocationRelativeTo(null);
		fSet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fSet.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Norte = new JPanel();
		fSet.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		GridBagLayout gbl_panel_Norte = new GridBagLayout();
		gbl_panel_Norte.columnWidths = new int[]{155, 0};
		gbl_panel_Norte.rowHeights = new int[]{50, 0};
		gbl_panel_Norte.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_Norte.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_Norte.setLayout(gbl_panel_Norte);
		
		JLabel lblSetting = new JLabel(" Setting");
		lblSetting.setHorizontalAlignment(SwingConstants.LEFT);
		lblSetting.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblSetting = new GridBagConstraints();
		gbc_lblSetting.fill = GridBagConstraints.BOTH;
		gbc_lblSetting.gridx = 0;
		gbc_lblSetting.gridy = 0;
		panel_Norte.add(lblSetting, gbc_lblSetting);
		
		JPanel panel_Central = new JPanel();
		fSet.getContentPane().add(panel_Central, BorderLayout.CENTER);
		GridBagLayout gbl_panel_Central = new GridBagLayout();
		gbl_panel_Central.columnWidths = new int[]{30, 135, 135, 30, 0};
		gbl_panel_Central.rowHeights = new int[]{0, 40, 35, 35, 0, 35, 35, 0, 0, 0, 0, 35, 35, 0};
		gbl_panel_Central.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Central.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_Central.setLayout(gbl_panel_Central);
		
		btnNombreUsuario = new JButton(actual.getNombre(), getImagenIcon(actual.getPathImg(), size, size));
		btnNombreUsuario.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnNombreUsuario = new GridBagConstraints();
		gbc_btnNombreUsuario.fill = GridBagConstraints.BOTH;
		gbc_btnNombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_btnNombreUsuario.gridx = 1;
		gbc_btnNombreUsuario.gridy = 1;
		panel_Central.add(btnNombreUsuario, gbc_btnNombreUsuario);
		
		
		lblNewLabel = new JLabel(actual.getMsgSaludo());
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		panel_Central.add(lblNewLabel, gbc_lblNewLabel);
		
		btnCambairIcono = new JButton("Cambiar Imagen Icono");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		panel_Central.add(btnCambairIcono, gbc_btnNewButton);
		
		btnCambiarSaludo = new JButton("Editar Mensaje Saludo");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 3;
		panel_Central.add(btnCambiarSaludo, gbc_btnNewButton_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.CYAN);
		separator_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 4;
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 4;
		panel_Central.add(separator_1, gbc_separator_1);
		
		btnPremium = new JButton("Convertirse / Dejarse En Premium");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.gridwidth = 2;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 5;
		panel_Central.add(btnPremium, gbc_btnNewButton_2);
		btnPremium.addActionListener(this);
		
		btnEsta = new JButton("Mostrar Estadistica");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.gridwidth = 2;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 6;
		panel_Central.add(btnEsta, gbc_btnNewButton_3);
		btnEsta.addActionListener(this);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.CYAN);
		separator.setBackground(Color.WHITE);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.gridwidth = 4;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 9;
		panel_Central.add(separator, gbc_separator);
		
		btnSalirSesion = new JButton("Salir Sesion");
		GridBagConstraints gbc_btnSalirSesion = new GridBagConstraints();
		gbc_btnSalirSesion.fill = GridBagConstraints.BOTH;
		gbc_btnSalirSesion.gridwidth = 2;
		gbc_btnSalirSesion.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalirSesion.gridx = 1;
		gbc_btnSalirSesion.gridy = 11;
		panel_Central.add(btnSalirSesion, gbc_btnSalirSesion);
		
		btnBorrarCuenta = new JButton("Borrar Cuenta");
		btnBorrarCuenta.setForeground(Color.RED);
		GridBagConstraints gbc_btnBorrarCuenta = new GridBagConstraints();
		gbc_btnBorrarCuenta.insets = new Insets(0, 0, 0, 5);
		gbc_btnBorrarCuenta.gridwidth = 2;
		gbc_btnBorrarCuenta.fill = GridBagConstraints.BOTH;
		gbc_btnBorrarCuenta.gridx = 1;
		gbc_btnBorrarCuenta.gridy = 12;
		panel_Central.add(btnBorrarCuenta, gbc_btnBorrarCuenta);
		
		JPanel panel_Sur = new VentanaSur(fSet,actual);
		fSet.getContentPane().add(panel_Sur, BorderLayout.SOUTH);
		
		//AÑADIR MANEJADORES
		btnBorrarCuenta.addActionListener(this);
		btnCambairIcono.addActionListener(this);
		btnCambiarSaludo.addActionListener(this);
		btnSalirSesion.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnBorrarCuenta) {
			System.out.println("Pulsado borrar cuenta");
		}
		
		if(e.getSource() == btnCambairIcono) {
			System.out.println("Pulsado cambiar icono");
			JFileChooser file = new JFileChooser();
			
			file.showOpenDialog(fSet);
			File archivo = file.getSelectedFile();
			
			if(archivo != null) {
				unica.cambiarFotoPerfil(archivo.getPath());
				JOptionPane.showMessageDialog(fSet, "Se ha cambiado su foto de perfil", "Resultado",JOptionPane.INFORMATION_MESSAGE);
				fSet.revalidate();
				fSet.repaint();
			}else {
				JOptionPane.showMessageDialog(fSet, "Selecciona una imagen", "Imagen",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if(e.getSource() == btnCambiarSaludo) {
			System.out.println("Pulsado cambiar saludo");
			String saludo = JOptionPane.showInputDialog(fSet, "Modifica tu mensaje de saludo", "Moficacion" ,JOptionPane.QUESTION_MESSAGE);
			unica.cambiarMsgSaludo(saludo);
			JOptionPane.showMessageDialog(fSet, "Se ha cambiado su mensaje de saludo a:"+saludo, "Resultado",JOptionPane.INFORMATION_MESSAGE);
			System.out.println("El nuevo saludo para Usario: " +actual.getNombre() + "es: "+ saludo);
		}
		
		if(e.getSource() == btnEsta) {
			System.out.println("Pulsado estadistica ");
		}
		
		if(e.getSource() == btnPremium) {
			System.out.println("Pulsado premium");
			unica.cambiarRolUsuario();
		}
		if(e.getSource() == btnSalirSesion) {
			System.out.println("Pulsado salir sesion");
			liberarVentana(fSet);
			unica.setUsuarioActual(null);
			new VentanaLogin();
		}
		
	}
}

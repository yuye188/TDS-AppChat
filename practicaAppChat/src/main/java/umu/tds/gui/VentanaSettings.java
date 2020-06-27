package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaSettings extends Ventana{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frameChat;

	public VentanaSettings() {
		crearPantalla();
		frameChat.setVisible(true);
	}

	@Override
	protected void crearPantalla() {
		// TODO Auto-generated method stub
		frameChat = new JFrame();
		frameChat.setTitle("AppChat");
		frameChat.setSize(Ventana.SIZE_X, Ventana.SIZE_Y);
		frameChat.setLocationRelativeTo(null);
		frameChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameChat.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Norte = new JPanel();
		frameChat.getContentPane().add(panel_Norte, BorderLayout.NORTH);
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
		frameChat.getContentPane().add(panel_Central, BorderLayout.CENTER);
		GridBagLayout gbl_panel_Central = new GridBagLayout();
		gbl_panel_Central.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_Central.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_Central.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_Central.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_Central.setLayout(gbl_panel_Central);
		
		JButton btnNombreUsuario = new JButton("Nombre Usuario");
		GridBagConstraints gbc_btnNombreUsuario = new GridBagConstraints();
		gbc_btnNombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_btnNombreUsuario.gridx = 1;
		gbc_btnNombreUsuario.gridy = 1;
		panel_Central.add(btnNombreUsuario, gbc_btnNombreUsuario);
		
		JLabel lblNewLabel = new JLabel("msgSaludo");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 1;
		panel_Central.add(lblNewLabel, gbc_lblNewLabel);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 10;
		panel_Central.add(separator, gbc_separator);
		
		JButton btnSalirSesion = new JButton("Salir Sesion");
		GridBagConstraints gbc_btnSalirSesion = new GridBagConstraints();
		gbc_btnSalirSesion.fill = GridBagConstraints.BOTH;
		gbc_btnSalirSesion.gridwidth = 3;
		gbc_btnSalirSesion.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalirSesion.gridx = 1;
		gbc_btnSalirSesion.gridy = 12;
		panel_Central.add(btnSalirSesion, gbc_btnSalirSesion);
		
		JButton btnBorrarCuenta = new JButton("Borrar Cuenta");
		btnBorrarCuenta.setForeground(Color.RED);
		GridBagConstraints gbc_btnBorrarCuenta = new GridBagConstraints();
		gbc_btnBorrarCuenta.gridwidth = 3;
		gbc_btnBorrarCuenta.fill = GridBagConstraints.BOTH;
		gbc_btnBorrarCuenta.insets = new Insets(0, 0, 0, 5);
		gbc_btnBorrarCuenta.gridx = 1;
		gbc_btnBorrarCuenta.gridy = 13;
		panel_Central.add(btnBorrarCuenta, gbc_btnBorrarCuenta);
		
		JPanel panel_Sur = new VentanaSur();
		frameChat.getContentPane().add(panel_Sur, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

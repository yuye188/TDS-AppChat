package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import umu.tds.modelo.Usuario;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class VentanaEstado extends Ventana {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frameEstado;
	private Usuario actual;

	private JButton btnCrearEstado;

	private JButton btnVerestado;

	private JPanel panel_Estado;

	public VentanaEstado(Usuario u) {

		System.out.println("Creando Ventana Estado");
		System.out.println("El usuario anterior es:" + unica.getUsuarioActual().getNombre());
		actual = u;
		Ventana.unica.setUsuarioActual(actual);
		System.out.println("El usuario actual es:" + unica.getUsuarioActual().getNombre());
		crearPantalla();
		mostrarVentana(frameEstado);
	}

	@Override
	protected void crearPantalla() {
		// TODO Auto-generated method stub
		frameEstado = new JFrame();
		frameEstado.setTitle("AppChat");
		frameEstado.setSize(Ventana.SIZE_X, Ventana.SIZE_Y);
		frameEstado.setLocationRelativeTo(null);
		frameEstado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frameEstado.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel_Norte = new JPanel();
		frameEstado.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		GridBagLayout gbl_panel_Norte = new GridBagLayout();
		gbl_panel_Norte.columnWidths = new int[] { 0, 0 };
		gbl_panel_Norte.rowHeights = new int[] { 50, 0 };
		gbl_panel_Norte.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_Norte.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_Norte.setLayout(gbl_panel_Norte);

		JLabel lblNewLabel = new JLabel("Estado");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_Norte.add(lblNewLabel, gbc_lblNewLabel);

		JPanel panel_Central = new JPanel();
		frameEstado.getContentPane().add(panel_Central, BorderLayout.CENTER);
		GridBagLayout gbl_panel_Central = new GridBagLayout();
		gbl_panel_Central.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_Central.rowHeights = new int[] { 0, 45, 35, 35, 35, 40, 0, 0 };
		gbl_panel_Central.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_Central.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_Central.setLayout(gbl_panel_Central);

		JButton btnNewButton = new JButton("Boton Usuario");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		panel_Central.add(btnNewButton, gbc_btnNewButton);

		btnCrearEstado = new JButton("Crear Estado");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 4;
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		panel_Central.add(btnCrearEstado, gbc_btnNewButton_1);

		btnVerestado = new JButton("Ver estado");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.gridwidth = 4;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 3;
		panel_Central.add(btnVerestado, gbc_btnNewButton_2);

		JLabel lblPanelEstadoNuevo = new JLabel("Panel Estado Nuevo");
		lblPanelEstadoNuevo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblPanelEstadoNuevo = new GridBagConstraints();
		gbc_lblPanelEstadoNuevo.gridwidth = 4;
		gbc_lblPanelEstadoNuevo.fill = GridBagConstraints.BOTH;
		gbc_lblPanelEstadoNuevo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPanelEstadoNuevo.gridx = 1;
		gbc_lblPanelEstadoNuevo.gridy = 5;
		panel_Central.add(lblPanelEstadoNuevo, gbc_lblPanelEstadoNuevo);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 6;
		panel_Central.add(scrollPane, gbc_scrollPane);

		panel_Estado = new JPanel();
		scrollPane.setViewportView(panel_Estado);

		JPanel panel_Sur = new VentanaSur(frameEstado, actual);
		frameEstado.getContentPane().add(panel_Sur, BorderLayout.SOUTH);
	
		//AÑADIR MANEJADORES
		btnCrearEstado.addActionListener(this);
		btnVerestado.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnCrearEstado) {
			System.out.println("Pulsado crear estado");
		}
		
		if(e.getSource() == btnVerestado) {
			System.out.println("Pulsado ver estado");
		}
	}

	public static void main(String[] args) {
		VentanaEstado s = new VentanaEstado(null);
	}
}

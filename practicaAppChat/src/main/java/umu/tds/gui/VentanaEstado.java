package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class VentanaEstado extends Ventana {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frameEstado;
	
	public VentanaEstado() {
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
		gbl_panel_Norte.columnWidths = new int[]{0, 0};
		gbl_panel_Norte.rowHeights = new int[]{50, 0};
		gbl_panel_Norte.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_Norte.rowWeights = new double[]{0.0, Double.MIN_VALUE};
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
		gbl_panel_Central.columnWidths = new int[]{0, 0, 0};
		gbl_panel_Central.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_Central.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Central.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_Central.setLayout(gbl_panel_Central);
		
		JButton btnNewButton = new JButton("Boton Usuario");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		panel_Central.add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblPanelEstadoNuevo = new JLabel("Panel Estado Nuevo");
		GridBagConstraints gbc_lblPanelEstadoNuevo = new GridBagConstraints();
		gbc_lblPanelEstadoNuevo.gridx = 1;
		gbc_lblPanelEstadoNuevo.gridy = 4;
		panel_Central.add(lblPanelEstadoNuevo, gbc_lblPanelEstadoNuevo);
		
		JPanel panel_Sur = new VentanaSur();
		frameEstado.getContentPane().add(panel_Sur, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		VentanaEstado s = new VentanaEstado();
	}
}

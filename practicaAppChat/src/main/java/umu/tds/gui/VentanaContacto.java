package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class VentanaContacto extends Ventana{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frameContacto;

	public VentanaContacto() {
		crearPantalla();
		frameContacto.setVisible(true);
	}

	@Override
	protected void crearPantalla() {
		// TODO Auto-generated method stub
		frameContacto = new JFrame();
		frameContacto.setTitle("AppChat");
		frameContacto.setSize(Ventana.SIZE_X, Ventana.SIZE_Y);
		frameContacto.setLocationRelativeTo(null);
		frameContacto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameContacto.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Norte = new JPanel();
		frameContacto.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		GridBagLayout gbl_panel_Norte = new GridBagLayout();
		gbl_panel_Norte.columnWidths = new int[]{155, 0};
		gbl_panel_Norte.rowHeights = new int[]{50, 0};
		gbl_panel_Norte.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_Norte.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_Norte.setLayout(gbl_panel_Norte);
		
		JLabel lblContactos = new JLabel("Contactos");
		lblContactos.setHorizontalAlignment(SwingConstants.LEFT);
		lblContactos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblContactos = new GridBagConstraints();
		gbc_lblContactos.fill = GridBagConstraints.BOTH;
		gbc_lblContactos.gridx = 0;
		gbc_lblContactos.gridy = 0;
		panel_Norte.add(lblContactos, gbc_lblContactos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frameContacto.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_ComandaHeader = new JPanel();
		scrollPane.setColumnHeaderView(panel_ComandaHeader);
		GridBagLayout gbl_panel_ComandaHeader = new GridBagLayout();
		gbl_panel_ComandaHeader.columnWidths = new int[]{0, 0};
		gbl_panel_ComandaHeader.rowHeights = new int[]{35, 35, 10, 0};
		gbl_panel_ComandaHeader.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_ComandaHeader.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_ComandaHeader.setLayout(gbl_panel_ComandaHeader);
		
		JButton btnAñadirContacto = new JButton("Añadir Contacto");
		GridBagConstraints gbc_btnAñadirContacto = new GridBagConstraints();
		gbc_btnAñadirContacto.insets = new Insets(0, 0, 5, 0);
		gbc_btnAñadirContacto.fill = GridBagConstraints.BOTH;
		gbc_btnAñadirContacto.gridx = 0;
		gbc_btnAñadirContacto.gridy = 0;
		panel_ComandaHeader.add(btnAñadirContacto, gbc_btnAñadirContacto);
		
		JButton btnAadirGrupo = new JButton("Añadir Grupo");
		GridBagConstraints gbc_btnAadirGrupo = new GridBagConstraints();
		gbc_btnAadirGrupo.insets = new Insets(0, 0, 5, 0);
		gbc_btnAadirGrupo.fill = GridBagConstraints.BOTH;
		gbc_btnAadirGrupo.gridx = 0;
		gbc_btnAadirGrupo.gridy = 1;
		panel_ComandaHeader.add(btnAadirGrupo, gbc_btnAadirGrupo);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		panel_ComandaHeader.add(separator, gbc_separator);
		
		JPanel panel_ListaContacto = new JPanel();
		scrollPane.setViewportView(panel_ListaContacto);
		
		JPanel panel_Sur = new VentanaSur();
		frameContacto.getContentPane().add(panel_Sur, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.lowagie.tools.plugins.Txt2Pdf;

import umu.tds.controlador.ControladorAppChat;
import umu.tds.modelo.Usuario;

import javax.swing.JTextField;

public class VentanaAddContacto extends Ventana {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frameAdd;
	private JTextField nombre;
	private JTextField tel;

	private JButton btnAceptar;
	private JButton btnCancelar;

	private VentanaPrincipal ventana;
	
	private Usuario actual;
	
	public VentanaAddContacto(VentanaPrincipal v) {
		ventana = v;
		crearPantalla();
		frameAdd.setVisible(true);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public VentanaAddContacto(Usuario u) {
		// TODO Auto-generated constructor stub
		System.out.println("Creando Ventana Add Contacto");
		System.out.println("El usuario anterior es:" + unica.getUsuarioActual().getNombre());
		actual = u;
		Ventana.unica.setUsuarioActual(actual);
		System.out.println("El usuario actual es:" + unica.getUsuarioActual().getNombre());
		
		crearPantalla();
		mostrarVentana(frameAdd);
	}

	@Override
	protected void crearPantalla() {
		// TODO Auto-generated method stub
		frameAdd = new JFrame();
		frameAdd.setTitle("AppChat");
		frameAdd.setSize(300, 255);
		frameAdd.setLocationRelativeTo(null);
		frameAdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameAdd.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Norte = new JPanel();
		frameAdd.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		GridBagLayout gbl_panel_Norte = new GridBagLayout();
		gbl_panel_Norte.columnWidths = new int[]{155, 0};
		gbl_panel_Norte.rowHeights = new int[]{50, 0};
		gbl_panel_Norte.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_Norte.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_Norte.setLayout(gbl_panel_Norte);
		
		JLabel lblAdd = new JLabel("Añadir Contacto");
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblAdd = new GridBagConstraints();
		gbc_lblAdd.fill = GridBagConstraints.BOTH;
		gbc_lblAdd.gridx = 0;
		gbc_lblAdd.gridy = 0;
		panel_Norte.add(lblAdd, gbc_lblAdd);
		
		JPanel panel_Central = new JPanel();
		frameAdd.getContentPane().add(panel_Central, BorderLayout.CENTER);
		GridBagLayout gbl_panel_Central = new GridBagLayout();
		gbl_panel_Central.columnWidths = new int[]{0, 63, 0, 0, 0, 0};
		gbl_panel_Central.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_Central.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Central.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_Central.setLayout(gbl_panel_Central);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.fill = GridBagConstraints.VERTICAL;
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		panel_Central.add(lblNombre, gbc_lblNombre);
		
		nombre = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel_Central.add(nombre, gbc_textField);
		nombre.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.fill = GridBagConstraints.VERTICAL;
		gbc_lblTelefono.anchor = GridBagConstraints.EAST;
		gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefono.gridx = 1;
		gbc_lblTelefono.gridy = 2;
		panel_Central.add(lblTelefono, gbc_lblTelefono);
		
		tel = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		panel_Central.add(tel, gbc_textField_1);
		tel.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 4;
		panel_Central.add(btnAceptar, gbc_btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 4;
		panel_Central.add(btnCancelar, gbc_btnCancelar);
		
		//AÑADIR MANEJADORES
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnAceptar) {
			System.out.println("Pulsado Aceptar");
			if(nombre.getText().isEmpty() || tel.getText().isEmpty()) {
				JOptionPane.showMessageDialog(frameAdd, "Nombre o Telefono Vacio", "User Add",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				boolean add = ControladorAppChat.getUnicaInstancia().addContactoIndividual(nombre.getText(), tel.getText());
				if(add) {
					JOptionPane.showMessageDialog(frameAdd, "Contacto añadido", "User Add",
							JOptionPane.INFORMATION_MESSAGE);
					//ventana.mostrarContacto(ventana, null);
					liberarVentana(frameAdd);
					VentanaContacto v = new VentanaContacto(actual);
				}else {
					JOptionPane.showMessageDialog(frameAdd, "Contacto añadido o Telefono no registrado", "User Add",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		if(e.getSource() == btnCancelar) {
			liberarVentana(frameAdd);
			VentanaContacto v= new VentanaContacto(actual);
		}	
	}

	public static void main(String[] args) {
		VentanaAddContacto v = new VentanaAddContacto(Ventana.actual);
	}
}

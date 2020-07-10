package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import umu.tds.modelo.Usuario;

import javax.swing.JButton;
import javax.swing.JFileChooser;


public class VentanaCrearEstado extends Ventana {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame cEstado;
	private JTextField fraseEstado;
	private JTextField imagenEstado;
	private JButton btnSeleFoto;
	private JButton btnCrearEstado;
	private JButton btnCancelar;
	private String pathImage;
	
	private Usuario actual;

	public VentanaCrearEstado(Usuario u) {
		
		actual = u;
		Ventana.unica.setUsuarioActual(actual);
		
		crearPantalla();
		mostrarVentana(cEstado);
	}

	@Override
	protected void crearPantalla() {
		// TODO Auto-generated method stub
		cEstado = new JFrame();
		cEstado.setSize(400,261);
		cEstado.setLocationRelativeTo(null);
		cEstado.setTitle("Crear Nuevo estado");
		cEstado.setContentPane(this);
		cEstado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Centro = new JPanel();
		add(panel_Centro, BorderLayout.CENTER);
		GridBagLayout gbl_panel_Centro = new GridBagLayout();
		gbl_panel_Centro.columnWidths = new int[]{85, 0, 0, 90, 0};
		gbl_panel_Centro.rowHeights = new int[]{10, 35, 35, 0, 40, 0};
		gbl_panel_Centro.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Centro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_Centro.setLayout(gbl_panel_Centro);
		
		JLabel lblFraseEstado = new JLabel("Frase Estado:");
		GridBagConstraints gbc_lblFraseEstado = new GridBagConstraints();
		gbc_lblFraseEstado.fill = GridBagConstraints.VERTICAL;
		gbc_lblFraseEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblFraseEstado.gridx = 0;
		gbc_lblFraseEstado.gridy = 1;
		panel_Centro.add(lblFraseEstado, gbc_lblFraseEstado);
		
		fraseEstado = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel_Centro.add(fraseEstado, gbc_textField);
		fraseEstado.setColumns(10);
		
		JLabel lblFotoEstado = new JLabel("Foto Estado:");
		GridBagConstraints gbc_lblFotoEstado = new GridBagConstraints();
		gbc_lblFotoEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblFotoEstado.fill = GridBagConstraints.VERTICAL;
		gbc_lblFotoEstado.gridx = 0;
		gbc_lblFotoEstado.gridy = 2;
		panel_Centro.add(lblFotoEstado, gbc_lblFotoEstado);
		
		imagenEstado = new JTextField();
		imagenEstado.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel_Centro.add(imagenEstado, gbc_textField_1);
		imagenEstado.setColumns(10);
		
		btnSeleFoto = new JButton("Seleccionar Foto");
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 2;
		panel_Centro.add(btnSeleFoto, gbc_btnNewButton);
		
		btnCrearEstado = new JButton("Crear ");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 4;
		panel_Centro.add(btnCrearEstado, gbc_btnNewButton_1);
		
		btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 4;
		panel_Centro.add(btnCancelar, gbc_btnNewButton_2);
		
		//AÑADIR MANEJADORES
		btnSeleFoto.addActionListener(this);
		btnCrearEstado.addActionListener(this);
		btnCancelar.addActionListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btnSeleFoto) {
			
			JFileChooser file = new JFileChooser();
			file.showOpenDialog(cEstado);
			File archivo = file.getSelectedFile();
			
			if(archivo != null) {
				pathImage = archivo.getPath();
				imagenEstado.setText(pathImage);
			}else {
				JOptionPane.showMessageDialog(cEstado, "Selecciona una imagen", "Imagen",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if(e.getSource() == btnCrearEstado) {
			if(pathImage != null && fraseEstado.getText() != null) {
				unica.crearEstado(fraseEstado.getText(), pathImage);
				liberarVentana(cEstado);
				new VentanaEstado(actual);
			}
		}
		
		if(e.getSource() == btnCancelar) {
			liberarVentana(cEstado);
			new VentanaEstado(actual);
		}
		
	}

}

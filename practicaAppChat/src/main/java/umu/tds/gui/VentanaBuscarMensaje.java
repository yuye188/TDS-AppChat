package umu.tds.gui;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.toedter.calendar.JDateChooser;

import umu.tds.controlador.ControladorAppChat;
import umu.tds.modelo.Usuario;

import javax.swing.JButton;
import java.awt.ScrollPane;
import java.awt.TextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import java.awt.Font;

public class VentanaBuscarMensaje extends Ventana{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame fBuscar;
	private JTextField textField;
	
	private Usuario actual;
	
	public VentanaBuscarMensaje(Usuario u) {
		
		System.out.println("Creando Ventana Busqueda");
		System.out.println("El usuario anterior es:" + unica.getUsuarioActual().getNombre());
		actual = u;
		Ventana.unica.setUsuarioActual(actual);
		System.out.println("El usuario actual es:" + unica.getUsuarioActual().getNombre());
		
		crearPantalla();
		mostrarVentana(fBuscar);
	}
	
	@Override
	protected void crearPantalla() {
		// TODO Auto-generated method stub
		fBuscar = new JFrame();
		fBuscar.setSize(480, 400);
		fBuscar.setLocationRelativeTo(null);
		fBuscar.setTitle("Buscador Mensaje");
		
		JPanel panel_Norte = new JPanel();
		fBuscar.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		
		JLabel lblBuscadorMensajeAppchat = new JLabel("Buscador Mensaje AppChat");
		lblBuscadorMensajeAppchat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_Norte.add(lblBuscadorMensajeAppchat);
		
		JPanel panel_Central = new JPanel();
		fBuscar.getContentPane().add(panel_Central, BorderLayout.CENTER);
		GridBagLayout gbl_panel_Central = new GridBagLayout();
		gbl_panel_Central.columnWidths = new int[]{165, 100, 67, 100, 0, 0};
		gbl_panel_Central.rowHeights = new int[]{30, 0, 30, 30, 30, 30, 0, 0};
		gbl_panel_Central.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Central.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_Central.setLayout(gbl_panel_Central);
		
		JLabel lblTexto = new JLabel("Texto a buscar:");
		GridBagConstraints gbc_lblTexto = new GridBagConstraints();
		gbc_lblTexto.fill = GridBagConstraints.VERTICAL;
		gbc_lblTexto.anchor = GridBagConstraints.EAST;
		gbc_lblTexto.insets = new Insets(0, 0, 5, 5);
		gbc_lblTexto.gridx = 0;
		gbc_lblTexto.gridy = 0;
		panel_Central.add(lblTexto, gbc_lblTexto);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_Central.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		GridBagConstraints gbc_lblFechaInicio = new GridBagConstraints();
		gbc_lblFechaInicio.fill = GridBagConstraints.VERTICAL;
		gbc_lblFechaInicio.anchor = GridBagConstraints.EAST;
		gbc_lblFechaInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaInicio.gridx = 0;
		gbc_lblFechaInicio.gridy = 2;
		panel_Central.add(lblFechaInicio, gbc_lblFechaInicio);
		
		JDateChooser dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 2;
		panel_Central.add(dateChooser, gbc_dateChooser);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		GridBagConstraints gbc_lblFechaFin = new GridBagConstraints();
		gbc_lblFechaFin.anchor = GridBagConstraints.EAST;
		gbc_lblFechaFin.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaFin.gridx = 2;
		gbc_lblFechaFin.gridy = 2;
		panel_Central.add(lblFechaFin, gbc_lblFechaFin);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		GridBagConstraints gbc_dateChooser_1 = new GridBagConstraints();
		gbc_dateChooser_1.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser_1.fill = GridBagConstraints.BOTH;
		gbc_dateChooser_1.gridx = 3;
		gbc_dateChooser_1.gridy = 2;
		panel_Central.add(dateChooser_1, gbc_dateChooser_1);
		
		JButton btnBuscar = new JButton("BUSCAR");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.BOTH;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 1;
		gbc_btnBuscar.gridy = 3;
		panel_Central.add(btnBuscar, gbc_btnBuscar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 3;
		panel_Central.add(btnCancelar, gbc_btnCancelar);
		
		JLabel lblResultadoBusqueda = new JLabel("RESULTADO DE LA BUSQUEDA");
		GridBagConstraints gbc_lblResultadoBusqueda = new GridBagConstraints();
		gbc_lblResultadoBusqueda.fill = GridBagConstraints.VERTICAL;
		gbc_lblResultadoBusqueda.anchor = GridBagConstraints.EAST;
		gbc_lblResultadoBusqueda.insets = new Insets(0, 0, 5, 5);
		gbc_lblResultadoBusqueda.gridx = 0;
		gbc_lblResultadoBusqueda.gridy = 5;
		panel_Central.add(lblResultadoBusqueda, gbc_lblResultadoBusqueda);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 6;
		panel_Central.add(scrollPane, gbc_scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		panel.add(textArea, BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		VentanaBuscarMensaje s = new VentanaBuscarMensaje(null);
	}
}

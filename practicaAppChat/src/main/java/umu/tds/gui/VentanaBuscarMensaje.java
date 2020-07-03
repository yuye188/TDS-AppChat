package umu.tds.gui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.toedter.calendar.JDateChooser;

import umu.tds.controlador.ControladorAppChat;
import umu.tds.modelo.Contacto;
import umu.tds.modelo.Mensaje;
import umu.tds.modelo.Usuario;

import javax.swing.JButton;
import java.awt.ScrollPane;
import java.awt.TextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import java.awt.Font;

public class VentanaBuscarMensaje extends Ventana {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame fBuscar;
	private JTextField textMensaje;
	private JTextField textUsuarioMovil;
	private JTextField contacto;
	private JDateChooser fechaIni;
	private JDateChooser fechaFin;
	private JButton btnBuscar;
	private JButton btnCancelar;
	private JTextArea resultadoBusqueda;
	private JButton btnSeleContacto;

	private Usuario actual;
	private Contacto contactoElegido= null;
	private JPopupMenu menuContacto;
	private List<JMenuItem> contactoSeleccionado;


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
		fBuscar.setSize(552, 446);
		fBuscar.setLocationRelativeTo(null);
		fBuscar.setTitle("Buscador Mensaje");
		fBuscar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel_Norte = new JPanel();
		fBuscar.getContentPane().add(panel_Norte, BorderLayout.NORTH);

		JLabel lblBuscadorMensajeAppchat = new JLabel("Buscador Mensaje AppChat");
		lblBuscadorMensajeAppchat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_Norte.add(lblBuscadorMensajeAppchat);

		JPanel panel_Central = new JPanel();
		fBuscar.getContentPane().add(panel_Central, BorderLayout.CENTER);
		GridBagLayout gbl_panel_Central = new GridBagLayout();
		gbl_panel_Central.columnWidths = new int[] { 165, 100, 67, 100, 20, 0 };
		gbl_panel_Central.rowHeights = new int[] { 10, 30, 30, 30, 30, 30, 30, 0, 0 };
		gbl_panel_Central.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_Central.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_Central.setLayout(gbl_panel_Central);

		JLabel lblTexto = new JLabel("Texto a buscar:");
		lblTexto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblTexto = new GridBagConstraints();
		gbc_lblTexto.fill = GridBagConstraints.VERTICAL;
		gbc_lblTexto.anchor = GridBagConstraints.EAST;
		gbc_lblTexto.insets = new Insets(0, 0, 5, 5);
		gbc_lblTexto.gridx = 0;
		gbc_lblTexto.gridy = 1;
		panel_Central.add(lblTexto, gbc_lblTexto);

		textMensaje = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel_Central.add(textMensaje, gbc_textField);
		textMensaje.setColumns(10);

		JLabel lblNombreContacto = new JLabel("Nombre Contacto:");
		lblNombreContacto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNombreContacto = new GridBagConstraints();
		gbc_lblNombreContacto.fill = GridBagConstraints.VERTICAL;
		gbc_lblNombreContacto.anchor = GridBagConstraints.EAST;
		gbc_lblNombreContacto.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreContacto.gridx = 0;
		gbc_lblNombreContacto.gridy = 2;
		panel_Central.add(lblNombreContacto, gbc_lblNombreContacto);

		contacto = new JTextField();
		contacto.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel_Central.add(contacto, gbc_textField_1);
		contacto.setColumns(10);

		btnSeleContacto = new JButton(getImagenIcon("imgs/iconobuscar.png", 25, 25));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 2;
		panel_Central.add(btnSeleContacto, gbc_btnNewButton);

		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblFechaInicio = new GridBagConstraints();
		gbc_lblFechaInicio.fill = GridBagConstraints.VERTICAL;
		gbc_lblFechaInicio.anchor = GridBagConstraints.EAST;
		gbc_lblFechaInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaInicio.gridx = 0;
		gbc_lblFechaInicio.gridy = 3;
		panel_Central.add(lblFechaInicio, gbc_lblFechaInicio);

		fechaIni = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 3;
		panel_Central.add(fechaIni, gbc_dateChooser);

		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblFechaFin = new GridBagConstraints();
		gbc_lblFechaFin.anchor = GridBagConstraints.EAST;
		gbc_lblFechaFin.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaFin.gridx = 2;
		gbc_lblFechaFin.gridy = 3;
		panel_Central.add(lblFechaFin, gbc_lblFechaFin);

		fechaFin = new JDateChooser();
		GridBagConstraints gbc_dateChooser_1 = new GridBagConstraints();
		gbc_dateChooser_1.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser_1.fill = GridBagConstraints.BOTH;
		gbc_dateChooser_1.gridx = 3;
		gbc_dateChooser_1.gridy = 3;
		panel_Central.add(fechaFin, gbc_dateChooser_1);

		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.BOTH;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 1;
		gbc_btnBuscar.gridy = 5;
		panel_Central.add(btnBuscar, gbc_btnBuscar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 5;
		panel_Central.add(btnCancelar, gbc_btnCancelar);
		
		JLabel lblUsuarioMovil = new JLabel("Movil del usuario a buscar:");
		lblUsuarioMovil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblUsuarioMovil = new GridBagConstraints();
		gbc_lblUsuarioMovil.fill = GridBagConstraints.VERTICAL;
		gbc_lblUsuarioMovil.anchor = GridBagConstraints.EAST;
		gbc_lblUsuarioMovil.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuarioMovil.gridx = 0;
		gbc_lblUsuarioMovil.gridy = 4;
		panel_Central.add(lblUsuarioMovil, gbc_lblUsuarioMovil);

		textUsuarioMovil = new JTextField();
		GridBagConstraints gbc_textUsuarioMovil = new GridBagConstraints();
		gbc_textUsuarioMovil.gridwidth = 3;
		gbc_textUsuarioMovil.insets = new Insets(0, 0, 5, 5);
		gbc_textUsuarioMovil.fill = GridBagConstraints.BOTH;
		gbc_textUsuarioMovil.gridx = 1;
		gbc_textUsuarioMovil.gridy = 4;
		panel_Central.add(textUsuarioMovil, gbc_textUsuarioMovil);
		textMensaje.setColumns(10);

		JLabel lblResultadoBusqueda = new JLabel("RESULTADO DE LA BUSQUEDA");
		lblResultadoBusqueda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblResultadoBusqueda = new GridBagConstraints();
		gbc_lblResultadoBusqueda.gridwidth = 3;
		gbc_lblResultadoBusqueda.fill = GridBagConstraints.BOTH;
		gbc_lblResultadoBusqueda.insets = new Insets(0, 0, 5, 5);
		gbc_lblResultadoBusqueda.gridx = 0;
		gbc_lblResultadoBusqueda.gridy = 6;
		panel_Central.add(lblResultadoBusqueda, gbc_lblResultadoBusqueda);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 7;
		panel_Central.add(scrollPane, gbc_scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));

		resultadoBusqueda = new JTextArea();
		resultadoBusqueda.setLineWrap(true);
		resultadoBusqueda.setEditable(false);
		panel.add(resultadoBusqueda, BorderLayout.CENTER);

		//AÑADIR CONTACTOS AL POPMENU
		menuContacto = new JPopupMenu();
		menuContacto.setLayout(new GridLayout(0, 1, 2, 2));
		contactoSeleccionado = new ArrayList<JMenuItem>();
		for(Contacto c : actual.getListaContacto()) {
			JMenuItem contacto = new JMenuItem(c.getNombre());
			contacto.addActionListener(this);
			menuContacto.add(contacto);
			contactoSeleccionado.add(contacto);
		}
		
		for(Contacto c : actual.getListaGrupo()) {
			JMenuItem contacto = new JMenuItem(c.getNombre());
			contacto.addActionListener(this);
			menuContacto.add(contacto);
			contactoSeleccionado.add(contacto);
		}
		
		// AÑADIR MANEJADORES
		btnBuscar.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnSeleContacto.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnBuscar) {

			System.out.println("Pulsado Busacando");
			// SI TODOS LOS CAMPOS ESTAN BIEN BUSCAMOS
			if (checkCampos()) {
				System.out.println("Buscando mensaje del contacto");
				
				List<Mensaje> mensajes = unica.buscarMensaje(contactoElegido, textMensaje.getText(), 
								fechaIni.getDate(), fechaFin.getDate(), textUsuarioMovil.getText());
				
				System.out.println("total :"+mensajes.size());
				resultadoBusqueda.setText(mensajes.stream()
											.map(m->m.getHora().toLocaleString()+"\tmovil:"+m.getTlfEmisor()+"\ttexto:"+m.getTexto()+"\n")
											.collect(Collectors.joining()));
			}
		}

		if (e.getSource() == btnCancelar) {
			System.out.println("Pulsado Cancelando");
			liberarVentana(fBuscar);
		}

		if (e.getSource() == btnSeleContacto) {
			System.out.println("Pulsado Seleccionando contacto a buscar");
			menuContacto.pack();
			menuContacto.show(btnSeleContacto, (int)btnSeleContacto.getSize().getWidth(), 0);
		}

		if(contactoSeleccionado.contains(e.getSource())) {
			System.out.println("Seleccionando contacto a buscar");
			
			int indice = 0;
			while (!contactoSeleccionado.get(indice).equals(e.getSource())) {
				indice++;
			}
			
			if (indice < actual.getListaContacto().size())
				contactoElegido = actual.getListaContacto().get(indice);
			else
				contactoElegido = actual.getListaGrupo().get(indice-actual.getListaContacto().size());
			
			String con = contactoSeleccionado.get(indice).getText();
			contacto.setText(con);
		}
		
	}
	
	public boolean checkCampos() {
		
		boolean campos = true;
		/*
		if (textMensaje.getText().isEmpty()) {
			campos = false;
			JOptionPane.showMessageDialog(fBuscar, "Mensaje a Buscar vacio", "Mensaje",
					JOptionPane.WARNING_MESSAGE);
		}

		else if (fechaIni.getDate() == null || fechaFin.getDate() == null) {
			campos = false;
			JOptionPane.showMessageDialog(fBuscar, "Seleccione Fechas", "Fecha",
					JOptionPane.WARNING_MESSAGE);
		}
		
		else */
		if (fechaIni.getDate() != null && fechaFin.getDate() != null) {
			long inicio = fechaIni.getDate().getTime();
			long fin = fechaFin.getDate().getTime();
			if (fechaIni.getDate().getTime() > fechaFin.getDate().getTime()) {
				campos = false;
				JOptionPane.showMessageDialog(fBuscar, "Fecha inicio posterior a fin", "Fecha",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		else if (contacto.getText().isEmpty()) {
			campos = false;
			JOptionPane.showMessageDialog(fBuscar, "Seleccione un contacto", "Contacto",
					JOptionPane.WARNING_MESSAGE);
		}
		
		return campos;
		
	}
}

package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import umu.tds.modelo.Contacto;
import umu.tds.modelo.ContactoIndividual;
import umu.tds.modelo.Estado;
import umu.tds.modelo.Usuario;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Panel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;
import java.awt.Color;

public class VentanaEstado extends Ventana {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frameEstado;
	private Usuario actual;
	private int size = 45;
	private int size_foto_estado = 250;
	
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
		gbl_panel_Central.rowHeights = new int[] { 45, 45, 45, 35, 40, 0, 0 };
		gbl_panel_Central.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_Central.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_Central.setLayout(gbl_panel_Central);

		Icon icono = getImagenIcon(actual.getPathImg(), size, size);
		JLabel usuario = new JLabel(actual.getNombre(),icono, SwingConstants.CENTER);
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		panel_Central.add(usuario, gbc_btnNewButton);
		
		btnCrearEstado = new JButton("Crear Estado Nuevo");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 4;
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 1;
		panel_Central.add(btnCrearEstado, gbc_btnNewButton_1);

		btnVerestado = new JButton("Ver estado");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.gridwidth = 4;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 2;
		panel_Central.add(btnVerestado, gbc_btnNewButton_2);
		
		JLabel lblPanelEstadoNuevo = new JLabel("Panel Estado Nuevo");
		lblPanelEstadoNuevo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblPanelEstadoNuevo = new GridBagConstraints();
		gbc_lblPanelEstadoNuevo.gridwidth = 4;
		gbc_lblPanelEstadoNuevo.fill = GridBagConstraints.BOTH;
		gbc_lblPanelEstadoNuevo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPanelEstadoNuevo.gridx = 1;
		gbc_lblPanelEstadoNuevo.gridy = 4;
		panel_Central.add(lblPanelEstadoNuevo, gbc_lblPanelEstadoNuevo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		panel_Central.add(scrollPane, gbc_scrollPane);

		panel_Estado = new JPanel();
		scrollPane.setViewportView(panel_Estado);
		panel_Estado.setLayout(new BoxLayout(panel_Estado, BoxLayout.Y_AXIS));
		
		JPanel panel_Sur = new VentanaSur(frameEstado, actual);
		frameEstado.getContentPane().add(panel_Sur, BorderLayout.SOUTH);

		// AÑADIR MANEJADORES
		btnCrearEstado.addActionListener(this);
		btnVerestado.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnCrearEstado) {
			System.out.println("Pulsado crear estado");
			liberarVentana(frameEstado);
			new VentanaCrearEstado(actual);
		}

		if (e.getSource() == btnVerestado) {
			System.out.println("Pulsado ver estado");
			
			crearEstadoPanel();
			
			List<Contacto> c = actual.getListaContacto();
			System.out.println("El usuario actual tiene: " +actual.getNombre() +" tiene contactos: " +c.size());
 			if(c.size() != 0) {
				for(Contacto cont: actual.getListaContacto()) {
					if(c instanceof ContactoIndividual) {
						ContactoIndividual con = ((ContactoIndividual) cont);
						crearEstadoPanel(con);
					}
				}
			}else {
				crearPanelNoNuevoEstado();
			}
		}
	}

	public void crearEstadoPanel(ContactoIndividual contacto) {
		
		System.out.println("Creando pantalla estado para contactos");
		
		Icon icono = getImagenIcon(contacto.getUsuario().getPathImg(), size, size);
		String nombre = contacto.getNombre(); 
		Estado esContacocto = contacto.getUsuario().getEstado();
		
		String fraseEstado = esContacocto.getFrase();
		Icon foto = getImagenIcon(esContacocto.getPathImg(), size_foto_estado, size_foto_estado);
		
		JPanel panel = new JPanel();
		panel_Estado.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 27, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{40, 30, 20, 0, 0, 0, 20, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblUsuariolabel = new JLabel(nombre,icono, SwingConstants.CENTER);
		GridBagConstraints gbc_lblUsuariolabel = new GridBagConstraints();
		gbc_lblUsuariolabel.anchor = GridBagConstraints.WEST;
		gbc_lblUsuariolabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblUsuariolabel.gridwidth = 3;
		gbc_lblUsuariolabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuariolabel.gridx = 0;
		gbc_lblUsuariolabel.gridy = 0;
		panel.add(lblUsuariolabel, gbc_lblUsuariolabel);
		
		JLabel lblEstado = new JLabel("ESTADO:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.gridwidth = 3;
		gbc_lblEstado.fill = GridBagConstraints.BOTH;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstado.gridx = 0;
		gbc_lblEstado.gridy = 1;
		panel.add(lblEstado, gbc_lblEstado);
			
		JTextArea areaEstado = new JTextArea(fraseEstado);
		areaEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		areaEstado.setEditable(false);
		areaEstado.setLineWrap(true);
		areaEstado.setBackground(lblEstado.getBackground());
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(areaEstado, gbc_lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBackground(Color.GRAY);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.gridwidth = 5;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		panel.add(separator, gbc_separator);
		
		JLabel lblNewLabel_2 = new JLabel(foto);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.gridheight = 3;
		gbc_lblNewLabel_2.gridwidth = 5;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);
		separator_1.setBackground(Color.GRAY);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.gridwidth = 5;
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 6;
		panel.add(separator_1, gbc_separator_1);
		
		panel_Estado.revalidate();
		panel_Estado.repaint();
	}
	
	//CREA ESTADO SOLO PARA USUARIO ACTUAL
	public void crearEstadoPanel() {
		
		JPanel panel = new JPanel();
		panel_Estado.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 27, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{40, 30, 20, 0, 0, 0, 20, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		Icon icono = getImagenIcon(actual.getPathImg(), size, size);
		JLabel lblUsuariolabel = new JLabel(actual.getNombre(),icono, SwingConstants.CENTER);
		GridBagConstraints gbc_lblUsuariolabel = new GridBagConstraints();
		gbc_lblUsuariolabel.anchor = GridBagConstraints.WEST;
		gbc_lblUsuariolabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblUsuariolabel.gridwidth = 3;
		gbc_lblUsuariolabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuariolabel.gridx = 0;
		gbc_lblUsuariolabel.gridy = 0;
		panel.add(lblUsuariolabel, gbc_lblUsuariolabel);
		
		JLabel lblEstado = new JLabel("TU ESTADO:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEstado.setVerticalTextPosition(SwingConstants.NORTH);
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.gridwidth = 3;
		gbc_lblEstado.fill = GridBagConstraints.BOTH;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstado.gridx = 0;
		gbc_lblEstado.gridy = 1;
		panel.add(lblEstado, gbc_lblEstado);
		
		String estado = actual.getEstado().getFrase();
		JTextArea areaEstado = new JTextArea(estado);
		areaEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		areaEstado.setEditable(false);
		areaEstado.setLineWrap(true);
		areaEstado.setBackground(lblEstado.getBackground());
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(areaEstado, gbc_lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBackground(Color.GRAY);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.gridwidth = 5;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		panel.add(separator, gbc_separator);
		
		String pathEstado = actual.getEstado().getPathImg();
		Icon foto = getImagenIcon(pathEstado, size_foto_estado, size_foto_estado);
		JLabel lblNewLabel_2 = new JLabel(foto);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.gridheight = 3;
		gbc_lblNewLabel_2.gridwidth = 5;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);
		separator_1.setBackground(Color.GRAY);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.gridwidth = 5;
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 6;
		panel.add(separator_1, gbc_separator_1);
		
		panel_Estado.revalidate();
		panel_Estado.repaint();
	}
	
	//CREAR PANEL CUANDO LOS CONTACTOS NO TIENE ESTADO NUEVO
	public void crearPanelNoNuevoEstado() {
		System.out.println("Creando Pantalla No estado nuevo");
		JPanel panel = new JPanel();
		panel_Estado.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel con = new JLabel("NO HAY ESTADO NUEVO");
		con.setHorizontalAlignment(SwingConstants.CENTER);
		con.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_con = new GridBagConstraints();
		gbc_con.fill = GridBagConstraints.HORIZONTAL;
		gbc_con.gridx = 0;
		gbc_con.gridy = 0;
		panel.add(con, gbc_con);
		
		panel_Estado.revalidate();
		panel_Estado.repaint();
	}
	
	public static void main(String[] args) {
		VentanaEstado s = new VentanaEstado(null);
	}
}

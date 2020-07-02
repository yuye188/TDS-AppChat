package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import umu.tds.controlador.ControladorAppChat;
import umu.tds.dao.AdaptadorUsuarioDAO;
import umu.tds.modelo.Contacto;
import umu.tds.modelo.ContactoIndividual;
import umu.tds.modelo.Grupo;
import umu.tds.modelo.Usuario;


import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Icon;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;


public class VentanaContacto extends Ventana{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frameContacto;
	private JButton btnAñadirContacto;
	private JButton btnAadirGrupo;
	private JPanel panel_ListaContacto;

	private VentanaPrincipal v;
	private Usuario actual;
	private int size = 45;
	
	private List<JMenuItem> menuContacto ;
	private List<JButton> menuContactoBoton;

	//private JButton btnModificar;
	
	public VentanaContacto() {
		crearPantalla();
		mostrarVentana(frameContacto);
	}

	public VentanaContacto(Usuario u) {
		// TODO Auto-generated constructor stub
		System.out.println("Creando Ventana Contacto");
		System.out.println("El usuario anterior es:" + unica.getUsuarioActual().getNombre());
		actual = AdaptadorUsuarioDAO.getUnicaInstancia().actualizarMensajes(u);
		Ventana.unica.setUsuarioActual(actual);
		System.out.println("El usuario actual es:" + unica.getUsuarioActual().getNombre());
		
		crearPantalla();
		actualizarPantalla();
		mostrarVentana(frameContacto);
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
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frameContacto.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_ComandaHeader = new JPanel();
		scrollPane.setColumnHeaderView(panel_ComandaHeader);
		GridBagLayout gbl_panel_ComandaHeader = new GridBagLayout();
		gbl_panel_ComandaHeader.columnWidths = new int[]{0, 0};
		gbl_panel_ComandaHeader.rowHeights = new int[]{35, 35, 35, 10, 0};
		gbl_panel_ComandaHeader.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_ComandaHeader.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_ComandaHeader.setLayout(gbl_panel_ComandaHeader);
		
		btnAñadirContacto = new JButton("Añadir Contacto");
		GridBagConstraints gbc_btnAñadirContacto = new GridBagConstraints();
		gbc_btnAñadirContacto.insets = new Insets(0, 0, 5, 0);
		gbc_btnAñadirContacto.fill = GridBagConstraints.BOTH;
		gbc_btnAñadirContacto.gridx = 0;
		gbc_btnAñadirContacto.gridy = 0;
		panel_ComandaHeader.add(btnAñadirContacto, gbc_btnAñadirContacto);
		
		btnAadirGrupo = new JButton("Añadir Grupo");
		GridBagConstraints gbc_btnAadirGrupo = new GridBagConstraints();
		gbc_btnAadirGrupo.insets = new Insets(0, 0, 5, 0);
		gbc_btnAadirGrupo.fill = GridBagConstraints.BOTH;
		gbc_btnAadirGrupo.gridx = 0;
		gbc_btnAadirGrupo.gridy = 1;
		panel_ComandaHeader.add(btnAadirGrupo, gbc_btnAadirGrupo);
		/*
		btnModificar = new JButton("Modificar Grupo");
		GridBagConstraints gbc_btnModificar = new GridBagConstraints();
		gbc_btnModificar.fill = GridBagConstraints.BOTH;
		gbc_btnModificar.insets = new Insets(0, 0, 5, 0);
		gbc_btnModificar.gridx = 0;
		gbc_btnModificar.gridy = 2;
		panel_ComandaHeader.add(btnModificar, gbc_btnModificar);*/
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 3;
		panel_ComandaHeader.add(separator, gbc_separator);
		
		panel_ListaContacto = new JPanel();
		scrollPane.setViewportView(panel_ListaContacto);
		panel_ListaContacto.setLayout(new BoxLayout(panel_ListaContacto, BoxLayout.Y_AXIS));
			
		JPanel panel_Sur = new VentanaSur(frameContacto, actual);
		frameContacto.getContentPane().add(panel_Sur, BorderLayout.SOUTH);

		//AÑADIR MANEJADORES
		btnAadirGrupo.addActionListener(this);
		btnAñadirContacto.addActionListener(this);
		//btnModificar.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnAñadirContacto) {
			System.out.println("Pulsado Añadir Contacto");
			liberarVentana(frameContacto);
			VentanaAddContacto v = new VentanaAddContacto(actual);
		}
		
		if(e.getSource() == btnAadirGrupo) {
			System.out.println("Pulsado Añadir Grupo");
			//liberarVentana(frameContacto);
			new VentanaGrupo(actual, null);
			actualizarPantalla();
		}
		/*
		if(e.getSource() == btnModificar) {
			System.out.println("Modificar Grupo");
			
		}*/
				
		if(menuContactoBoton.contains(e.getSource())) {
			System.out.println("Seleccionado Contacto");
			
			int i=0;
			while(!menuContactoBoton.get(i).equals(e.getSource())) {
				i++;
			}
			System.out.println("Seleccionado Contacto de la posicion :" + i);
			
			
			Contacto contacto;
			if (i < actual.getListaContacto().size())
				 contacto = actual.getListaContacto().get(i);
			else
				contacto = actual.getListaGrupo().get(i-actual.getListaContacto().size());
			
			System.out.println("El contacto actual a mandar mensaje: "+ contacto.getNombre());
			
			liberarVentana(frameContacto);
			new VentanaConversacion(actual,contacto);
		}
	}
	
	public void actualizarPantalla() {
		
		System.out.println("Cargando Lista Contacto" );
		System.out.println("El usuario actual " + actual.getUsuario() +" tiene "+  
				actual.getListaContacto().size() + " contactos");
		
		panel_ListaContacto.removeAll();
		//menuContacto = new ArrayList<JMenuItem>();
		menuContactoBoton = new ArrayList<JButton>();
		for(Contacto c: actual.getListaContacto()) {
		
			ContactoIndividual s = (ContactoIndividual) c;
			
			Icon user = getImagenIcon(s.getUsuario().getPathImg(), size, size);
			String nombre = s.getNombre();
			String saludo = "";
			if(s.getUsuario().getMsgSaludo()!=null) saludo = s.getUsuario().getMsgSaludo();
			Icon boton = getImagenIcon("imgs/iconomensaje.png", size, size);
			
			crearContactoPanel(user, nombre, saludo, boton);		
		}
		
		System.out.println("Cargando Lista Grupo" );
		System.out.println("El usuario actual " + actual.getUsuario() +" tiene "+  
				actual.getListaGrupo().size() + " grupos");
		
		for(Contacto c: actual.getListaGrupo()){
			
			Grupo grupo = (Grupo) c;
			
			Icon user = getImagenIcon(grupo.getPathImg(), size, size);
			String nombre = grupo.getNombre();
			//String saludo = "";
			//if(grupo.getUsuario().getMsgSaludo()!=null) saludo = grupo.getUsuario().getMsgSaludo();
			Icon boton = getImagenIcon("imgs/iconomensaje.png", size, size);
			
			crearContactoPanel(user, nombre, "", boton);		
		}
		
	}
	
	public void crearContactoPanel(Icon icono, String nombre, String saludo, Icon iconoenvio) {
		
		JPanel panel = new JPanel();
		panel_ListaContacto.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{25, 25, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel iconoUser = new JLabel(icono);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.gridheight = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(iconoUser, gbc_lblNewLabel);
		
		JLabel nombreUser = new JLabel(nombre);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.gridwidth = 5;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(nombreUser, gbc_lblNewLabel_1);
		
		JTextArea mSaludo = new JTextArea(saludo);
		mSaludo.setLineWrap(true);
		mSaludo.setEditable(false);
		mSaludo.setBackground(nombreUser.getBackground());
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.gridwidth = 5;
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(mSaludo, gbc_lblNewLabel_2);
		
		JButton btnMensaje = new JButton(iconoenvio);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridheight = 2;
		gbc_btnNewButton.gridx = 9;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnMensaje, gbc_btnNewButton);
		
		btnMensaje.addActionListener(this);
		menuContactoBoton.add(btnMensaje);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		panel_ListaContacto.add(separator);
	}
	
	public static void main(String[] args) {
		VentanaContacto v = new VentanaContacto(Ventana.actual);
	}
}

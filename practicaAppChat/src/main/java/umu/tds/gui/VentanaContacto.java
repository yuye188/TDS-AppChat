package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.lowagie.text.pdf.AcroFields.Item;

import umu.tds.modelo.Contacto;
import umu.tds.modelo.ContactoIndividual;
import umu.tds.modelo.Usuario;

import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import java.awt.GridLayout;


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
	List<JMenuItem> menuContacto ;
	
	public VentanaContacto() {
		crearPantalla();
		mostrarVentana(frameContacto);
	}

	public VentanaContacto(Usuario u) {
		// TODO Auto-generated constructor stub
		actual = u;
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
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		panel_ComandaHeader.add(separator, gbc_separator);
		
		panel_ListaContacto = new JPanel();
		scrollPane.setViewportView(panel_ListaContacto);
		panel_ListaContacto.setLayout(new GridLayout(0, 1, 0, 15));
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		panel_ListaContacto.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("New menu item");
		panel_ListaContacto.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("New menu item");
		panel_ListaContacto.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		panel_ListaContacto.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("New menu item");
		panel_ListaContacto.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		panel_ListaContacto.add(mntmNewMenuItem_3);
		
		/*JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		panel_ListaContacto.add(mntmNewMenuItem);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.ORANGE);
		separator_1.setBackground(Color.GREEN);
		panel_ListaContacto.add(separator_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		panel_ListaContacto.add(mntmNewMenuItem_1);*/
		
		/*JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		panel_ListaContacto.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		panel_ListaContacto.add(mntmNewMenuItem_1);*/
		
		JPanel panel_Sur = new VentanaSur(frameContacto, actual);
		frameContacto.getContentPane().add(panel_Sur, BorderLayout.SOUTH);

		//AÑADIR MANEJADORES
		btnAadirGrupo.addActionListener(this);
		btnAñadirContacto.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnAñadirContacto) {
			System.out.println("Pulsado Añadir Contacto");
			liberarVentana(frameContacto);
			//VentanaAddContacto v = new VentanaAddContacto(Ventana.actual);
			VentanaAddContacto v = new VentanaAddContacto(actual);
		}
		
		if(e.getSource() == btnAadirGrupo) {
			System.out.println("Pulsado Añadir Grupo");
			//liberarVentana(frameContacto);
		}
		
		if(menuContacto.contains(e.getSource())) {
			System.out.println("Seleccionado Contacto");
		}
	}
	
	public void actualizarPantalla() {
		//panel_ListaContacto.removeAll();
		
		menuContacto = new ArrayList<JMenuItem>();
	
		System.out.println("El contacto actual " + actual.getUsuario() +" tiene "+  
		actual.getListaContacto().size() + " contactos");
		
		/*for(Contacto c: actual.getListaContacto()) {
			ContactoIndividual s = (ContactoIndividual) c;
			String texto = s.getNombre(); //+ s.getUsuario().getMsgSaludo();
			Icon icono = getImagenIcon("img/profile.png", size, size);
			Icon ic = getImagenIcon(s.getUsuario().getPathImg(), size, size);
			Icon i = new ImageIcon("img/profile.png");
			JMenuItem item = new JMenuItem("Hola",getImagenIcon("imgs/profile.png", 45, 45));
			JMenuItem item2 = new JMenuItem("dasdas", i);
			setSize(item, size+10, size+10);
			setSize(item2, size+10, size+10);
			item.setAlignmentX(LEFT_ALIGNMENT);
			item.setVerticalTextPosition(SwingConstants.CENTER);
			item.addActionListener(this);
			menuContacto.add(item);
			panel_ListaContacto.add(item);
			panel_ListaContacto.add(separator);
			panel_ListaContacto.add(item2);
		}*/
	
		JMenuItem item = new JMenuItem("Hola",getImagenIcon("imgs/profile.png", 45, 45));
	
		JMenuItem item2 = new JMenuItem("dasdas", getImagenIcon("imgs/profile.png", 45, 45));
		JMenuItem item3= new JMenuItem("Hola",getImagenIcon("imgs/profile.png", 45, 45));
		JMenuItem item4 = new JMenuItem("dasdas", getImagenIcon("imgs/profile.png", 45, 45));
		
		//setSize(item, size, size);
		//setSize(item2, size, size);
		JSeparator s1 = new JSeparator();
		s1.setBackground(Color.GREEN);
		JSeparator s2 = new JSeparator();
		s2.setBackground(Color.GREEN);
		JSeparator s3 = new JSeparator();
		s3.setBackground(Color.GREEN);
		JSeparator s4 = new JSeparator();
		s4.setBackground(Color.GREEN);
		item.addActionListener(this);
		menuContacto.add(item);
		panel_ListaContacto.add(item);
		//panel_ListaContacto.add(s1);
		panel_ListaContacto.add(item2);
		//panel_ListaContacto.add(s2);
		panel_ListaContacto.add(item3);
		//panel_ListaContacto.add(s3);
		panel_ListaContacto.add(item4);
		//panel_ListaContacto.add(s4);
	}
	
	public static void main(String[] args) {
		VentanaContacto v = new VentanaContacto(Ventana.actual);
	}
}

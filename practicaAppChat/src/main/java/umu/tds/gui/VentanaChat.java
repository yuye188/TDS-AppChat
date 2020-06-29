package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import umu.tds.controlador.ControladorAppChat;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.CatalogoUsuario;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class VentanaChat extends Ventana {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frameChat;
	
	private int size = 45;
	
	//USUARIO Y VENTANA PRINCIPAL
	private Usuario actual;
	private VentanaPrincipal ventana;
	
	//PANEL NORTE
	private JButton btnInfoUser, btnBuscar, btnJavaBean;
	//PANEL CONTACTOS
	private JScrollPane panel_Scroll_Contactos;
	private JPanel panel_Contactos;
	//PANEL SUR
	private JButton btnEstado, btnFoto, btnContacto, btnChat, btnSetting;
	
	public VentanaChat(VentanaPrincipal v, Usuario u) {
		// TODO Auto-generated constructor stub
		ventana = v;
		actual = u;
		
		crearPantalla();	
		mostrarVentana(frameChat);
	}
	
	public VentanaChat() {
		System.out.println();
		actual = null;
		crearPantalla();
		mostrarVentana(frameChat);
	}
	
	public VentanaChat(Usuario u) {
		// TODO Auto-generated constructor stub
		System.out.println("Creando Ventana Chat");
		System.out.println("El usuario anterior es:" + unica.getUsuarioActual().getNombre());
		actual = u;
		Ventana.unica.setUsuarioActual(actual);
		System.out.println("El usuario actual es:" + unica.getUsuarioActual().getNombre());
		crearPantalla();	
		mostrarVentana(frameChat);
	}
	
	@Override
	protected void crearPantalla() {
		// TODO Auto-generated method stub
		
		frameChat = new JFrame();
		frameChat.setTitle("AppChat");
		frameChat.setSize(Ventana.SIZE_X, Ventana.SIZE_Y);
		frameChat.setLocationRelativeTo(null);
		frameChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameChat.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Norte = new JPanel();
		frameChat.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		GridBagLayout gbl_panel_Norte = new GridBagLayout();
		gbl_panel_Norte.columnWidths = new int[]{101, 0, 0, 35, 0};
		gbl_panel_Norte.rowHeights = new int[]{40, 35, 0};
		gbl_panel_Norte.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Norte.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_Norte.setLayout(gbl_panel_Norte);
		
		if(actual == null) {
			btnInfoUser = new JButton("Nombre User",getImagenIcon("imgs/profile.png", size, size));
		}else {
			btnInfoUser = new JButton(actual.getNombre(),getImagenIcon("imgs/profile.png", size, size));
		}
		
		//btnInfoUser = new JButton(actual.getNombre(),getImagenIcon("imgs/profile.png", size, size));
		btnInfoUser.setHorizontalTextPosition(JButton.RIGHT);
		setSize(btnInfoUser, size, size);
		GridBagConstraints gbc_btnInfoUser = new GridBagConstraints();
		gbc_btnInfoUser.fill = GridBagConstraints.BOTH;
		gbc_btnInfoUser.insets = new Insets(0, 0, 5, 5);
		gbc_btnInfoUser.gridx = 0;
		gbc_btnInfoUser.gridy = 0;
		panel_Norte.add(btnInfoUser, gbc_btnInfoUser);
		
		btnJavaBean = new JButton("Ja");
		GridBagConstraints gbc_btnJavaBean = new GridBagConstraints();
		gbc_btnJavaBean.fill = GridBagConstraints.BOTH;
		gbc_btnJavaBean.insets = new Insets(0, 0, 5, 0);
		gbc_btnJavaBean.gridx = 3;
		gbc_btnJavaBean.gridy = 0;
		panel_Norte.add(btnJavaBean, gbc_btnJavaBean);
		
		JLabel lblConversacion = new JLabel("Conversacion");
		lblConversacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblConversacion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConversacion.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblConversacion = new GridBagConstraints();
		gbc_lblConversacion.fill = GridBagConstraints.BOTH;
		gbc_lblConversacion.gridwidth = 3;
		gbc_lblConversacion.insets = new Insets(0, 0, 0, 5);
		gbc_lblConversacion.gridx = 0;
		gbc_lblConversacion.gridy = 1;
		panel_Norte.add(lblConversacion, gbc_lblConversacion);
		
		btnBuscar = new JButton(getImagenIcon("imgs/iconobuscar.png", size, size));
		setSize(btnBuscar, size, size);
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.BOTH;
		gbc_btnBuscar.gridx = 3;
		gbc_btnBuscar.gridy = 1;
		panel_Norte.add(btnBuscar, gbc_btnBuscar);
		
		panel_Scroll_Contactos = new JScrollPane();
		panel_Scroll_Contactos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_Scroll_Contactos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frameChat.getContentPane().add(panel_Scroll_Contactos, BorderLayout.CENTER);
		
		panel_Contactos = new JPanel();
		panel_Scroll_Contactos.setViewportView(panel_Contactos);
		
		JPanel panel_Sur = new VentanaSur(frameChat, actual);
		frameChat.getContentPane().add(panel_Sur, BorderLayout.SOUTH);
		
		//AÑADIR MANEJADORES
		btnBuscar.addActionListener(this);
		btnJavaBean.addActionListener(this);
		btnInfoUser.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btnBuscar) {
			System.out.println("Pulsado Buscar");
			new VentanaBuscarMensaje(actual);
		}

		if(e.getSource() == btnInfoUser) {
			System.out.println("Pulsado InfoUser");
		}
	
		if(e.getSource() == btnJavaBean) {
			System.out.println("Pulsado JavaBean");
		}

	}
	
	public static void main(String[] args) {
		System.out.println("Prueba Ventana Chat");
		VentanaChat ventana = new VentanaChat(Ventana.actual);
	}

}

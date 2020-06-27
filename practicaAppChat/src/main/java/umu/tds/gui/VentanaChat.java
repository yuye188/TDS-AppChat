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

import umu.tds.modelo.Usuario;

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
		// TODO Auto-generated constructor stub
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
		
		btnInfoUser = new JButton("Nombre Usuario",getImagenIcon("imgs/profile.png", size, size));
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
		
		/*System.out.println(panel_Centro.getWidth() + panel_Centro.getHeight());
		System.out.println(panel_Contactos.getWidth() + panel_Contactos.getHeight());*/
		
		JPanel panel_Sur = new JPanel();
		frameChat.getContentPane().add(panel_Sur, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_Sur = new GridBagLayout();
		gbl_panel_Sur.columnWidths = new int[]{65, 65, 65, 65, 65, 0};
		gbl_panel_Sur.rowHeights = new int[]{25, 0};
		gbl_panel_Sur.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_Sur.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_Sur.setLayout(gbl_panel_Sur);
		
		btnEstado = new JButton(getImagenIcon("imgs/iconoestado.png", size, size));
		setSize(btnEstado, size, size);
		GridBagConstraints gbc_btnEstado = new GridBagConstraints();
		gbc_btnEstado.fill = GridBagConstraints.BOTH;
		gbc_btnEstado.insets = new Insets(0, 0, 0, 5);
		gbc_btnEstado.gridx = 0;
		gbc_btnEstado.gridy = 0;
		panel_Sur.add(btnEstado, gbc_btnEstado);
		
		btnFoto = new JButton(getImagenIcon("imgs/iconocamara.png", size, size));
		setSize(btnFoto, size, size);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 0;
		panel_Sur.add(btnFoto, gbc_btnNewButton_2);
		
		btnContacto = new JButton(getImagenIcon("imgs/iconocontacto.png", size, size));
		setSize(btnContacto, size, size);
		GridBagConstraints gbc_btnLlamada = new GridBagConstraints();
		gbc_btnLlamada.fill = GridBagConstraints.BOTH;
		gbc_btnLlamada.insets = new Insets(0, 0, 0, 5);
		gbc_btnLlamada.gridx = 2;
		gbc_btnLlamada.gridy = 0;
		panel_Sur.add(btnContacto, gbc_btnLlamada);
		
		btnChat = new JButton(getImagenIcon("imgs/iconochat.png", size, size));
		setSize(btnChat, size, size);
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4.gridx = 3;
		gbc_btnNewButton_4.gridy = 0;
		panel_Sur.add(btnChat, gbc_btnNewButton_4);
							
		btnSetting = new JButton(getImagenIcon("imgs/iconosetting.png", size, size));
		setSize(btnSetting, size, size);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.gridx = 4;
		gbc_btnNewButton_3.gridy = 0;
		panel_Sur.add(btnSetting, gbc_btnNewButton_3);
		
		//AÑADIR MANEJADORES
		btnBuscar.addActionListener(this);
		btnChat.addActionListener(this);
		btnEstado.addActionListener(this);
		btnFoto.addActionListener(this);
		btnJavaBean.addActionListener(this);
		btnContacto.addActionListener(this);
		btnInfoUser.addActionListener(this);
		btnSetting.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnBuscar) {
			System.out.println("Pulsado Buscar");
		}
		
		if(e.getSource() == btnChat) {
			System.out.println("Pulsado Chat");
			new VentanaConversacion();
		}
		
		if(e.getSource() == btnEstado) {
			System.out.println("Pulsado Estado");
		}
		
		if(e.getSource() == btnFoto) {
			System.out.println("Pulsado Foto");
			JOptionPane.showMessageDialog(frameChat, "NO SE PUEDE HACER FOTO AUN", "AppChat Foto",
					JOptionPane.INFORMATION_MESSAGE);
		}
		
		if(e.getSource() == btnInfoUser) {
			System.out.println("Pulsado InfoUser");
		}
	
		if(e.getSource() == btnJavaBean) {
			System.out.println("Pulsado JavaBean");
		}
		
		if(e.getSource() == btnContacto) {
			System.out.println("Pulsado Agenda Contacto");
			VentanaContacto c = new VentanaContacto();
		}
		
		if(e.getSource() == btnSetting) {
			System.out.println("Pulsado Setting");
			VentanaSettings s = new VentanaSettings();
		}
	}
	
	public static void main(String[] args) {
		VentanaChat ventana = new VentanaChat();
	}

}

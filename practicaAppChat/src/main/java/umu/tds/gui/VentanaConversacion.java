package umu.tds.gui;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tds.BubbleText;

import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

public class VentanaConversacion extends Ventana{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame contentPane;
	private int size = 45;
	
	private JButton btnVolver, btnInfoUser, btnMore, btnEnviar;
	private JTextArea textMensaje;
	
	//POPMENU DE OPCION USUARIO
	private JPopupMenu popupMenu;
	private JMenuItem eliminarContacto;
	
	//POPMENU EMOJI
	private JButton btnEmoji ;
	private JPopupMenu menuEmoji;
	
	public VentanaConversacion() {
		crearPantalla();
		mostrarVentana(true);
	}	
	
	@Override
	protected void crearPantalla() {
		// TODO Auto-generated method stub
		contentPane = new JFrame();
		contentPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setSize(355, 565);
		contentPane.setTitle("Conversacion");
		contentPane.setLocationRelativeTo(null);
		contentPane.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Norte = new JPanel();
		contentPane.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		panel_Norte.setLayout(new BorderLayout(0, 0));
		
		btnVolver = new JButton(getImagenIcon("imgs/Back-Icon.png", size, size));
		setSize(btnVolver, size, size);
		panel_Norte.add(btnVolver, BorderLayout.WEST);
		
		btnInfoUser = new JButton("Nombre Usuario", getImagenIcon("imgs/profile.png", size, size));
		btnInfoUser.setHorizontalTextPosition(JButton.RIGHT);
		panel_Norte.add(btnInfoUser, BorderLayout.CENTER);
		
		btnMore = new JButton(getImagenIcon("imgs/Menu_opciones.PNG", size, size));
		setSize(btnMore, size, size);
		panel_Norte.add(btnMore, BorderLayout.EAST);
		
		popupMenu = new JPopupMenu();
		
		eliminarContacto = new JMenuItem("Eliminar Contacto");

		popupMenu.add(eliminarContacto);
		
		JScrollPane panel_Centro = new JScrollPane();
		panel_Centro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.getContentPane().add(panel_Centro, BorderLayout.CENTER);
		
		JPanel panel_Chat = new JPanel();
		panel_Centro.setViewportView(panel_Chat);
		
		JPanel panel_Sur = new JPanel();
		contentPane.getContentPane().add(panel_Sur, BorderLayout.SOUTH);
		panel_Sur.setLayout(new BorderLayout(0, 0));
		
		btnEmoji = new JButton(getImagenIcon(BubbleText.getEmoji(14).getImage(), 30));
		panel_Sur.add(btnEmoji, BorderLayout.WEST);
			
		menuEmoji = new JPopupMenu();
		menuEmoji.setLayout(new GridLayout(5, 5, 2, 2));
		cargarEmoji();
		
		
		textMensaje = new JTextArea();
		textMensaje.setTabSize(10);
		textMensaje.setLineWrap(true);
		panel_Sur.add(textMensaje, BorderLayout.CENTER);
		
		btnEnviar = new JButton("Enviar");
		panel_Sur.add(btnEnviar, BorderLayout.EAST);
		

		//AÑADIR MANEJADORES
		btnVolver.addActionListener(this);
		btnInfoUser.addActionListener(this);
		btnMore.addActionListener(this);
		btnEmoji.addActionListener(this);
		btnEnviar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btnVolver) {
			System.out.println("Pulsado Volver");
		}
		
		if(e.getSource() == btnInfoUser) {
			System.out.println("Pulsado InfoUser");
		}
		
		if(e.getSource() == btnMore) {
			System.out.println("Pulsado MenuMore");
			popupMenu.show(btnMore, 0, 20);
		}
		
		if(e.getSource() == btnEmoji) {
			System.out.println("Pulsado Emoji");
			menuEmoji.pack(); //.pack para ocupar el tamaño minimo ncesario
			System.out.println(menuEmoji.getWidth());
			menuEmoji.show(btnEmoji, 0, 0);
		}
		
		if(e.getSource() == btnEnviar) {
			System.out.println("Pulsado Enviar");
		}
	}
	
	private void cargarEmoji() {
		//CARGAR LOS 25 EMOJIS DE LA CLASE BUBBLE
		for (int i = 0; i < 25; i++) {
			JMenuItem jmi = new JMenuItem(BubbleText.getEmoji(i));
			jmi.addActionListener(this);
			menuEmoji.add(jmi);
		}
	}

	@Override
	public void mostrarVentana(boolean b) {
		// TODO Auto-generated method stub
		contentPane.setVisible(b);
	}
	

	public static void main(String[] args) {
		VentanaConversacion v = new VentanaConversacion();
	}

}

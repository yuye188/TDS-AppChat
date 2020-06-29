package umu.tds.gui;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tds.BubbleText;
import umu.tds.modelo.Contacto;
import umu.tds.modelo.ContactoIndividual;
import umu.tds.modelo.Mensaje;
import umu.tds.modelo.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ScrollPaneConstants;

import java.util.ArrayList;
import java.util.List;

import java.awt.GridLayout;
import javax.swing.BoxLayout;

public class VentanaConversacion extends Ventana {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame contentPane;
	private int size = 45;

	private JButton btnVolver, btnInfoUser, btnMore, btnEnviar;

	// POPMENU DE OPCION USUARIO
	private JPopupMenu popupMenu;
	private JMenuItem eliminarContacto;

	// POPMENU EMOJI
	private JButton btnEmoji;
	private JPopupMenu menuEmoji;
	private List<JMenuItem> emojiSeleccionado;

	private Usuario actual;
	// private Contacto cActual;
	private ContactoIndividual cActual;

	private JScrollPane scrollPane;
	private JTextArea mensaje;

	private JPanel panel_Chat;

	private JScrollPane panel_Centro;

	/*public VentanaConversacion() {
		crearPantalla();
		mostrarVentana(contentPane);
	}*/

	public VentanaConversacion(Usuario u, Contacto m) {

		System.out.println("El usuario anterior es:" + unica.getUsuarioActual().getNombre());
		actual = u;
		Ventana.unica.setUsuarioActual(actual);
		System.out.println("El usuario actual es:" + unica.getUsuarioActual().getNombre());
		cActual = (ContactoIndividual) m;
		
		System.out.println("El contacto actual a enviar mensaje: " + cActual.getUsuario().getNombre());
		System.out.println("Tamaño de lista mensaje: "+cActual.getMensajes().size());
		crearPantalla();
		actualizarPantalla();
		//contentPane.setResizable(false);
		mostrarVentana(contentPane);
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

		// btnInfoUser = new JButton("Nombre Usuario", getImagenIcon("imgs/profile.png",
		// size, size));
		btnInfoUser = new JButton(cActual.getNombre(), getImagenIcon(cActual.getUsuario().getPathImg(), size, size));
		btnInfoUser.setHorizontalTextPosition(JButton.RIGHT);
		panel_Norte.add(btnInfoUser, BorderLayout.CENTER);

		btnMore = new JButton(getImagenIcon("imgs/Menu_opciones.PNG", size, size));
		setSize(btnMore, size, size);
		panel_Norte.add(btnMore, BorderLayout.EAST);

		popupMenu = new JPopupMenu();

		eliminarContacto = new JMenuItem("Borrar Contacto");

		popupMenu.add(eliminarContacto);

		panel_Centro = new JScrollPane();
		panel_Centro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.getContentPane().add(panel_Centro, BorderLayout.CENTER);

		panel_Chat = new JPanel();
		panel_Centro.setViewportView(panel_Chat);
		panel_Chat.setLayout(new GridLayout(0, 1, 0, 0));
				
		JPanel panel_Sur = new JPanel();
		contentPane.getContentPane().add(panel_Sur, BorderLayout.SOUTH);
		panel_Sur.setLayout(new BorderLayout(0, 0));

		btnEmoji = new JButton(getImagenIcon(BubbleText.getEmoji(14).getImage(), 45));
		panel_Sur.add(btnEmoji, BorderLayout.WEST);

		menuEmoji = new JPopupMenu();
		menuEmoji.setLayout(new GridLayout(5, 5, 2, 2));
		emojiSeleccionado = new ArrayList<JMenuItem>();
		cargarEmoji();

		btnEnviar = new JButton("Enviar");
		panel_Sur.add(btnEnviar, BorderLayout.EAST);

		scrollPane = new JScrollPane();
		panel_Sur.add(scrollPane, BorderLayout.CENTER);

		mensaje = new JTextArea();
		mensaje.setLineWrap(true);
		scrollPane.setViewportView(mensaje);

		// AÑADIR MANEJADORES
		btnVolver.addActionListener(this);
		btnInfoUser.addActionListener(this);
		btnMore.addActionListener(this);
		btnEmoji.addActionListener(this);
		btnEnviar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == btnVolver) {
			System.out.println("Pulsado Volver");
			liberarVentana(contentPane);
			new VentanaChat(actual);
		}

		if (e.getSource() == btnInfoUser) {
			System.out.println("Pulsado InfoUser");
		}

		if (e.getSource() == btnMore) {
			System.out.println("Pulsado MenuMore");
			popupMenu.show(btnMore, 0, 45);
		}

		if (e.getSource() == btnEmoji) {
			System.out.println("Pulsado Emoji");
			menuEmoji.pack(); // .pack para ocupar el tamaño minimo necesario
			// MOSTRAR EMOTICONOS AL LADO DE SU BOTON
			menuEmoji.show(btnEmoji, btnEmoji.getX() - 335, btnEmoji.getY() - 203);
		}

		// ENVIAR MENSAJE NORMAL
		if (e.getSource() == btnEnviar) {
			System.out.println("Pulsado Enviar");

			if (mensaje.getText().isEmpty()) {
				JOptionPane.showMessageDialog(contentPane, "Mensaje Vacio", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			} else {
				System.out.println("Mandar mensaje a: " + cActual.getNombre());
				unica.enviarMensaje(cActual, mensaje.getText(), -1);
				mensaje.setText("");
				actualizarPantalla();
			}
		}

		// ENVIAR EMOJI
		if (emojiSeleccionado.contains(e.getSource())) {
			System.out.println("Seleccionando emoji");
			int i = 0;
			while (!emojiSeleccionado.get(i).equals(e.getSource())) {
				i++;
			}
			System.out.println("Contacto a enviar: " + cActual.getNombre());
			System.out.println("Seleccionado emoji: " + i);
			unica.enviarMensaje(cActual, "", i);
			actualizarPantalla();
		}
	}

	public void actualizarPantalla() {
		panel_Chat.removeAll();
		System.out.println("Repintando Pantalla");
		System.out.println("Contacto actual a mostrar mensaje: " + cActual.getNombre());
		
		int sentOrecive;
		Color colorMensaje;
		String nombre = "";
		String t = "";
		for (Mensaje m : cActual.getMensajes()) {
			if (m.getTlfEmisor().equals(actual.getMovil())) {
				sentOrecive = BubbleText.SENT;
				colorMensaje = Color.GREEN;
				// LO DEJAMOS PRIMERO COMO USAURIO ACTUAL NOMBRE
				nombre = actual.getNombre();
			} else {
				sentOrecive = BubbleText.RECEIVED;
				colorMensaje = Color.WHITE;
				// if(cActual instanceof Grupo) {

				// }else {
				if (cActual.getNombre() == null) {
					nombre = cActual.getMovil();
				} else {
					nombre = cActual.getNombre();
				}
				// }
			}
			BubbleText b = null;
			if (m.getEmoticon() != -1) {
				//b = new BubbleText(panel_Chat, m.getEmoticon(), colorMensaje, nombre, sentOrecive, 12);
				panel_Chat.add(new BubbleText(panel_Chat, m.getEmoticon(), colorMensaje, nombre, sentOrecive, 12));
			} else {
				//b = new BubbleText(panel_Chat, m.getTexto(), colorMensaje, nombre, sentOrecive);
				panel_Chat.add(new BubbleText(panel_Chat, m.getTexto(), colorMensaje, nombre, sentOrecive));
				t = m.getTexto();
			}
			
		}
		//ACTUALIZAR Y REVALIDAR PANEL POR CAMBIOS
		
		/*panel_Chat.add(new BubbleText(panel_Chat, t, Color.CYAN, "envio", BubbleText.SENT));
		panel_Chat.add(new BubbleText(panel_Chat, t, Color.CYAN, "revibo", BubbleText.SENT));
	
		panel_Chat.add(new BubbleText(panel_Chat, t, Color.CYAN, "envio", BubbleText.RECEIVED));
		panel_Chat.add(new BubbleText(panel_Chat, "t", Color.CYAN, "revibo", BubbleText.RECEIVED));*/
	
		panel_Chat.revalidate();
		panel_Chat.repaint();
	}

	private void cargarEmoji() {
		// CARGAR LOS 25 EMOJIS DE LA CLASE BUBBLE
		for (int i = 0; i < 25; i++) {
			JMenuItem jmi = new JMenuItem(BubbleText.getEmoji(i));
			jmi.addActionListener(this);
			menuEmoji.add(jmi);
			emojiSeleccionado.add(jmi);
		}
	}

	public static void main(String[] args) {
		//VentanaConversacion v = new VentanaConversacion();
	}
}

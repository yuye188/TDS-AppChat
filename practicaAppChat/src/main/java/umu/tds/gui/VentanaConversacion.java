package umu.tds.gui;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tds.BubbleText;
import umu.tds.catalogo.CatalogoUsuario;
import umu.tds.controlador.ControladorAppChat;
import umu.tds.dao.AdaptadorContactoIndividualDAO;
import umu.tds.dao.AdaptadorGrupoDAO;
import umu.tds.dao.AdaptadorUsuarioDAO;
import umu.tds.dao.PoolDAO;
import umu.tds.modelo.Contacto;
import umu.tds.modelo.ContactoIndividual;
import umu.tds.modelo.Grupo;
import umu.tds.modelo.Mensaje;
import umu.tds.modelo.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;

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
	private JMenuItem modificarGrupo;

	// POPMENU EMOJI
	private JButton btnEmoji;
	private JPopupMenu menuEmoji;
	private List<JMenuItem> emojiSeleccionado;

	private Usuario actual;
	// private Contacto cActual;
	private Contacto cActual;

	private JScrollPane scrollPane;
	private JTextArea mensaje;

	private JPanel panel_Chat;

	private JScrollPane panel_Centro;
	
	private Timer timer;

	/*public VentanaConversacion() {
		crearPantalla();
		mostrarVentana(contentPane);
	}*/

	public VentanaConversacion(Usuario u, Contacto m) {
		
		actual = u;
		Ventana.unica.setUsuarioActual(actual);
		
		if (m.getClass() == ContactoIndividual.class)
			cActual = (ContactoIndividual) m;
		else cActual = (Grupo) m;
		
		crearPantalla();

		mostrarVentana(contentPane);
		actualizarPantalla();
		//contentPane.setResizable(false);
		
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				int numMsg = cActual.getMensajes().size();
				if (cActual.getClass() == ContactoIndividual.class) {
					try {
					cActual = (ContactoIndividual) AdaptadorContactoIndividualDAO.getUnicaInstancia().actualizarMensajes(cActual);
					} catch (NullPointerException e) {
						timer.cancel();
						timer.purge();
						JOptionPane.showMessageDialog(null, "El usuario "+cActual.getNombre()+" ha eliminado su cuenta", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						liberarVentana(contentPane);
						new VentanaChat(actual);
						return;
					}
				}
				else {
					
						cActual = (Grupo) AdaptadorGrupoDAO.getUnicaInstancia().actualizarMensajes(cActual);
							if (!((Grupo)cActual).getMiembros().contains(actual)) {
							timer.cancel();
							timer.purge();
							JOptionPane.showMessageDialog(null, "Usted ha sido eliminado en el grupo: "+cActual.getNombre(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
							liberarVentana(contentPane);
							new VentanaChat(actual);
							return;
						}
				}
				
				if (numMsg != cActual.getMensajes().size()) {
					actualizarPantalla();
					
					JScrollBar vertical = panel_Centro.getVerticalScrollBar();
					vertical.setValue( vertical.getMaximum()-1);
					
				}
				
			}
		}, 0, 2000);
	}

	@Override
	protected void crearPantalla() {
		
		contentPane = new JFrame();
		contentPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setSize(355, 565);
		contentPane.setResizable(false);
		contentPane.setTitle("Conversacion");
		contentPane.setLocationRelativeTo(null);
		contentPane.getContentPane().setLayout(new BorderLayout());

		JPanel panel_Norte = new JPanel();
		contentPane.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		panel_Norte.setLayout(new BorderLayout(0, 0));

		btnVolver = new JButton(getImagenIcon("imgs/Back-Icon.png", size, size));
		setSize(btnVolver, size, size);
		panel_Norte.add(btnVolver, BorderLayout.WEST);

		// btnInfoUser = new JButton("Nombre Usuario", getImagenIcon("imgs/profile.png",
		// size, size));
		String pathImg = null;
		if (cActual.getClass() == ContactoIndividual.class)
			pathImg = ((ContactoIndividual)cActual).getUsuario().getPathImg();
		else
			pathImg = ((Grupo)cActual).getPathImg();
		
		btnInfoUser = new JButton(cActual.getNombre(), getImagenIcon(pathImg, size, size));
		btnInfoUser.setHorizontalTextPosition(JButton.RIGHT);
		panel_Norte.add(btnInfoUser, BorderLayout.CENTER);

		btnMore = new JButton(getImagenIcon("imgs/Menu_opciones.PNG", size, size));
		setSize(btnMore, size, size);
		panel_Norte.add(btnMore, BorderLayout.EAST);

		popupMenu = new JPopupMenu();

		
		String borrar_salir = "Borrar Contacto";
		
		if (cActual.getClass() == Grupo.class) {
			modificarGrupo = new JMenuItem("Modificar Grupo");
			popupMenu.add(modificarGrupo);
			borrar_salir = "Salir del grupo";
			modificarGrupo.addActionListener(this);
		}
			
		eliminarContacto = new JMenuItem(borrar_salir);
		popupMenu.add(eliminarContacto);

		panel_Centro = new JScrollPane();
		
		panel_Centro.setMinimumSize(new Dimension(340, 565));
		panel_Centro.setPreferredSize(new Dimension(340, 565));
		panel_Centro.setMaximumSize(new Dimension(340, 565));
		panel_Centro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_Centro.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.getContentPane().add(panel_Centro, BorderLayout.CENTER);

		panel_Chat = new JPanel();
		panel_Chat.setMinimumSize(new Dimension(320, 565));
		//panel_Chat.setPreferredSize(new Dimension(340, 565));
		panel_Centro.setViewportView(panel_Chat);
		panel_Chat.setLayout(new BoxLayout(panel_Chat, BoxLayout.Y_AXIS));

				
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
		eliminarContacto.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == eliminarContacto) {
			timer.cancel();
			timer.purge();
			
			boolean eliminar = false;
			if (cActual.getClass() == ContactoIndividual.class) 
				eliminar = unica.deleteContactoIndividual(cActual);
			else
				eliminar = unica.deleteMiembro(actual, (Grupo) cActual);
			
			if (eliminar) {
				JOptionPane.showMessageDialog(contentPane, "Se ha elimiando el contacto "+cActual.getNombre(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				liberarVentana(contentPane);
				new VentanaChat(actual);
			} else 
				JOptionPane.showMessageDialog(contentPane, "No se ha podido eliminar el contacto "+cActual.getNombre(), "Mensaje", JOptionPane.ERROR_MESSAGE);
		} 
		
		if (modificarGrupo != null && e.getSource() == modificarGrupo ) {
			
			if (unica.isAdministrador(actual, (Grupo) cActual))
				new VentanaGrupo(actual, (Grupo) cActual);
			else 
				JOptionPane.showMessageDialog(contentPane, "Usted no es el administrador del grupo", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if (e.getSource() == btnVolver) {
			timer.cancel();
			timer.purge();
			liberarVentana(contentPane);
			new VentanaChat(actual);
		}

		if (e.getSource() == btnInfoUser) {
			if (cActual.getClass() == ContactoIndividual.class)
				new VentanaPerfil(((ContactoIndividual)cActual).getUsuario());
			else
				new VentanaPerfilGrupo(cActual);
		}

		if (e.getSource() == btnMore) {
			popupMenu.show(btnMore, 0, 45);
		}

		if (e.getSource() == btnEmoji) {
			menuEmoji.pack(); // .pack para ocupar el tamaño minimo necesario
			// MOSTRAR EMOTICONOS AL LADO DE SU BOTON
			menuEmoji.show(btnEmoji, btnEmoji.getX() - 335, btnEmoji.getY() - 203);
		}

		// ENVIAR MENSAJE NORMAL
		if (e.getSource() == btnEnviar) {

			if (mensaje.getText().isEmpty()) {
				JOptionPane.showMessageDialog(contentPane, "Mensaje Vacio", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			} else {
				unica.enviarMensaje(cActual, mensaje.getText(), -1);
				mensaje.setText("");
				actualizarPantalla();
			}
		}

		// ENVIAR EMOJI
		if (emojiSeleccionado.contains(e.getSource())) {
			int i = 0;
			while (!emojiSeleccionado.get(i).equals(e.getSource())) {
				i++;
			}
			unica.enviarMensaje(cActual, "", i);
			actualizarPantalla();
		}
	}

	public void actualizarPantalla() {
		
		panel_Chat.removeAll();
		
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
				
				nombre = CatalogoUsuario.getUnicaInstancia().getUsuario(m.getTlfEmisor()).getNombre();
				
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
		panel_Chat.revalidate();
		panel_Chat.repaint();
		
		// dibujar rectangle en el inferior derecho
		panel_Chat.scrollRectToVisible(new Rectangle(0, panel_Chat.getHeight(), 2, 2));
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

}
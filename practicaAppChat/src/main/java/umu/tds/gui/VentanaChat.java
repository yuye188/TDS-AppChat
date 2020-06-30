package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import beans.Mensaje;
import tds.BubbleText;
import umu.tds.controlador.ControladorAppChat;
import umu.tds.modelo.Contacto;
import umu.tds.modelo.ContactoIndividual;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.CatalogoUsuario;

import java.awt.GridBagLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.BoxLayout;

public class VentanaChat extends Ventana {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frameChat;

	private int size = 45;

	// USUARIO Y VENTANA PRINCIPAL
	private Usuario actual;
	private VentanaPrincipal ventana;

	// PANEL NORTE
	private JButton btnInfoUser, btnBuscar, btnJavaBean;
	// PANEL CONTACTOS
	private JScrollPane panel_Scroll_Contactos;
	private JPanel panel_Contactos;

	// BOTON
	private List<JButton> menuContactoBoton;

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
		actualizarPantalla();
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
		gbl_panel_Norte.columnWidths = new int[] { 101, 0, 0, 35, 0 };
		gbl_panel_Norte.rowHeights = new int[] { 40, 35, 0 };
		gbl_panel_Norte.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_Norte.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_Norte.setLayout(gbl_panel_Norte);

		if (actual == null) {
			btnInfoUser = new JButton("Nombre User", getImagenIcon("imgs/profile.png", size, size));
		} else {
			btnInfoUser = new JButton(actual.getUsuario(), getImagenIcon(actual.getPathImg(), size, size));
		}

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

		JLabel lblConversacion = new JLabel("Conversacion Recientes");
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
		panel_Contactos.setLayout(new BoxLayout(panel_Contactos, BoxLayout.Y_AXIS));

		JPanel panel_Sur = new VentanaSur(frameChat, actual);
		frameChat.getContentPane().add(panel_Sur, BorderLayout.SOUTH);

		// AÑADIR MANEJADORES
		btnBuscar.addActionListener(this);
		btnJavaBean.addActionListener(this);
		btnInfoUser.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == btnBuscar) {
			System.out.println("Pulsado Buscar");
			new VentanaBuscarMensaje(actual);
		}

		if (e.getSource() == btnInfoUser) {
			System.out.println("Pulsado InfoUser");
			new VentanaPerfil(actual);
		}

		if (e.getSource() == btnJavaBean) {
			System.out.println("Pulsado JavaBean");
		}

		if (menuContactoBoton.contains(e.getSource())) {
			System.out.println("Seleccionado Contacto Desde Ventana Chat");

			int i = 0;
			while (!menuContactoBoton.get(i).equals(e.getSource())) {
				i++;
			}
			System.out.println("Seleccionado Contacto de la posicion :" + i);
			Contacto m = actual.getContactosOrdenadosPorHora().get(i);
			System.out.println("El contacto actual a mandar mensaje: " + m.getNombre());

			liberarVentana(frameChat);
			new VentanaConversacion(actual, m);
		}

	}

	public void actualizarPantalla() {
		System.out.println("Cargando Lista Conversacion reciente");
		System.out.println("El usuario actual " + actual.getUsuario() + " tiene " + actual.getListaContacto().size()
				+ " contactos");

		panel_Contactos.removeAll();
		menuContactoBoton = new ArrayList<JButton>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		// OBTENER LISTA DE MENSAJE DEL CONTACTO ORDENADO POR TIEMPO
		for (Contacto c : unica.getContactosOrdenadosPorHora()) {

			Icon user = getImagenIcon(unica.getImageContacto(c), size, size);
			Icon boton = getImagenIcon("imgs/iconomensaje.png", size, size);
			String nombre = c.getNombre();
			String fecha = "";
			String texto = "";
			JLabel text = new JLabel(texto);

			// SI HAY MENSAJE PARA CONTACTO
			if (unica.getUltimoMensaje(c) != null) {
				fecha = nombre + "  " + sdf.format(unica.getUltimoMensaje(c).getHora());
				// SI EL ULTIMO MENSAJE ES TEXTO O EMOJI
				if (unica.getUltimoMensaje(c).getTexto().isEmpty()) {
					Icon emoji = getImagenIcon(BubbleText.getEmoji(c.getLastMensaje().getEmoticon()).getImage(), 40);
					JLabel emo = new JLabel(emoji);
					crearConversacionPanel(user, fecha, emo, boton);
				} else {
					texto = unica.getUltimoMensaje(c).getTexto();
					if(texto.length() > 25) texto = texto.substring(0, 22) + "...";
					text = new JLabel(texto);
					crearConversacionPanel(user, fecha, text, boton);
				}
			} else {
				crearConversacionPanel(user, nombre, text, boton);
			}
		}
	}

	public void crearConversacionPanel(Icon iconoContacto, String fecha, JLabel texto, Icon iconoenvio) {

		JPanel panel = new JPanel();
		panel_Contactos.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 25, 25, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel iconoUser = new JLabel(iconoContacto);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.gridheight = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(iconoUser, gbc_lblNewLabel);

		JLabel nombre_fecha = new JLabel(fecha);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.gridwidth = 5;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(nombre_fecha, gbc_lblNewLabel_1);

		JLabel mensaje = texto;
		mensaje.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.gridwidth = 5;
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(mensaje, gbc_lblNewLabel_2);

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
		panel_Contactos.add(separator);

	}

	public static void main(String[] args) {
		System.out.println("Prueba Ventana Chat");
		VentanaChat ventana = new VentanaChat(Ventana.actual);
	}

}

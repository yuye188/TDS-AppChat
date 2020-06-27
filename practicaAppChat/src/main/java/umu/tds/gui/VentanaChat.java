package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Color;

public class VentanaChat extends Ventana {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frameChat;
	
	private int size = 45;

	
	public VentanaChat() {
		// TODO Auto-generated constructor stub
		crearPantalla();	
		mostrarVentana(true);
	}
	
	@Override
	protected void crearPantalla() {
		// TODO Auto-generated method stub
		
		frameChat = new JFrame();
		frameChat.setTitle("AppChat");
		frameChat.setSize(350, 582);
		frameChat.setLocationRelativeTo(null);
		frameChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameChat.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Norte = new JPanel();
		frameChat.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		GridBagLayout gbl_panel_Norte = new GridBagLayout();
		gbl_panel_Norte.columnWidths = new int[]{101, 0, 0, 35, 0};
		gbl_panel_Norte.rowHeights = new int[]{30, 35, 0};
		gbl_panel_Norte.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Norte.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_Norte.setLayout(gbl_panel_Norte);
		
		JButton btnEditar = new JButton("Editar");
		GridBagConstraints gbc_btnEditar = new GridBagConstraints();
		gbc_btnEditar.fill = GridBagConstraints.BOTH;
		gbc_btnEditar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditar.gridx = 0;
		gbc_btnEditar.gridy = 0;
		panel_Norte.add(btnEditar, gbc_btnEditar);
		
		JButton btnJavabeanbutton = new JButton("JavaBeanButton");
		GridBagConstraints gbc_btnJavabeanbutton = new GridBagConstraints();
		gbc_btnJavabeanbutton.insets = new Insets(0, 0, 5, 0);
		gbc_btnJavabeanbutton.gridx = 3;
		gbc_btnJavabeanbutton.gridy = 0;
		panel_Norte.add(btnJavabeanbutton, gbc_btnJavabeanbutton);
		
		JLabel lblConversacion = new JLabel("Conversacion");
		lblConversacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblConversacion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConversacion.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblConversacion = new GridBagConstraints();
		gbc_lblConversacion.fill = GridBagConstraints.BOTH;
		gbc_lblConversacion.gridwidth = 2;
		gbc_lblConversacion.insets = new Insets(0, 0, 0, 5);
		gbc_lblConversacion.gridx = 0;
		gbc_lblConversacion.gridy = 1;
		panel_Norte.add(lblConversacion, gbc_lblConversacion);
		
		JButton btnBuscar = new JButton(getImagenIcon("imgs/iconobuscar.png", size, size));
		setSize(btnBuscar, size, size);
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.BOTH;
		gbc_btnBuscar.gridx = 3;
		gbc_btnBuscar.gridy = 1;
		panel_Norte.add(btnBuscar, gbc_btnBuscar);
		
		JScrollPane panel_Centro = new JScrollPane();
		panel_Centro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_Centro.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frameChat.getContentPane().add(panel_Centro, BorderLayout.CENTER);
		
		JPanel panel_Contactos = new JPanel();
		panel_Centro.setViewportView(panel_Contactos);
		
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
		
		JButton btnEstado = new JButton(getImagenIcon("imgs/iconoestado.png", size, size));
		setSize(btnEstado, size, size);
		GridBagConstraints gbc_btnEstado = new GridBagConstraints();
		gbc_btnEstado.fill = GridBagConstraints.BOTH;
		gbc_btnEstado.insets = new Insets(0, 0, 0, 5);
		gbc_btnEstado.gridx = 0;
		gbc_btnEstado.gridy = 0;
		panel_Sur.add(btnEstado, gbc_btnEstado);
		
		JButton btnFoto = new JButton(getImagenIcon("imgs/iconocamara.png", size, size));
		setSize(btnFoto, size, size);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 0;
		panel_Sur.add(btnFoto, gbc_btnNewButton_2);
		
		JButton btnLlamada =  new JButton(getImagenIcon("imgs/iconollamada.png", size, size));
		setSize(btnLlamada, size, size);
		GridBagConstraints gbc_btnLlamada = new GridBagConstraints();
		gbc_btnLlamada.fill = GridBagConstraints.BOTH;
		gbc_btnLlamada.insets = new Insets(0, 0, 0, 5);
		gbc_btnLlamada.gridx = 2;
		gbc_btnLlamada.gridy = 0;
		panel_Sur.add(btnLlamada, gbc_btnLlamada);
		
		JButton btnChat= new JButton(getImagenIcon("imgs/iconochat.png", size, size));
		setSize(btnChat, size, size);
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4.gridx = 3;
		gbc_btnNewButton_4.gridy = 0;
		panel_Sur.add(btnChat, gbc_btnNewButton_4);
		
		btnChat.setEnabled(true);
		
		
		JButton btnSetting = new JButton(getImagenIcon("imgs/iconosetting.png", size, size));
		setSize(btnSetting, size, size);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.gridx = 4;
		gbc_btnNewButton_3.gridy = 0;
		panel_Sur.add(btnSetting, gbc_btnNewButton_3);
		
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarVentana(boolean b) {
		// TODO Auto-generated method stub
		frameChat.setVisible(b);
		
	}
	
	public static void main(String[] args) {
		VentanaChat ventana = new VentanaChat();
	}

}

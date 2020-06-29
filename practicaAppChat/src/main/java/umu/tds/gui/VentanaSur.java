package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import umu.tds.controlador.ControladorAppChat;
import umu.tds.modelo.Usuario;


public class VentanaSur extends Ventana implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame fActual;
	//PANEL SUR
	private JButton btnEstado, btnFoto, btnContacto, btnChat, btnSetting;
	
	private int size = 45;
	private ControladorAppChat unica = ControladorAppChat.getUnicaInstancia();
	private Usuario actual;
	
	/**
	 * Create the panel.
	 */
	public VentanaSur(JFrame frame, Usuario u) {
		
		System.out.println("Creando Ventana Sur");
		System.out.println("El usuario anterior es:" + unica.getUsuarioActual().getNombre());
		actual = u;
		unica.setUsuarioActual(actual);
		System.out.println("El usuario actual es:" + unica.getUsuarioActual().getNombre());
		
		fActual = frame;
		
		setLayout(new BorderLayout(0, 0));
		setSize(Ventana.SIZE_X, 25);
		JPanel panel_Sur = new JPanel();
		add(panel_Sur, BorderLayout.SOUTH);
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
		
		btnChat.addActionListener(this);
		btnEstado.addActionListener(this);
		btnFoto.addActionListener(this);
		btnContacto.addActionListener(this);
		btnSetting.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btnChat) {
			System.out.println("Pulsado Chat Sur");
			liberarVentana(fActual);
			new VentanaChat(actual);
		}
		
		if(e.getSource() == btnEstado) {
			System.out.println("Pulsado Estado Sur");
			liberarVentana(fActual);
			new VentanaEstado(actual);
		}
		
		if(e.getSource() == btnFoto) {
			System.out.println("Pulsado Foto Sur");
			JOptionPane.showMessageDialog(fActual, "NO SE PUEDE HACER FOTO AUN", "AppChat Foto",
					JOptionPane.INFORMATION_MESSAGE);
		}
		
		if(e.getSource() == btnContacto) {
			System.out.println("Pulsado Agenda Contacto Sur");
			liberarVentana(fActual);
			new VentanaContacto(actual);
		}
		
		if(e.getSource() == btnSetting) {
			System.out.println("Pulsado Setting Sur");
			liberarVentana(fActual);
			VentanaSettings s = new VentanaSettings(actual);
		}
	}

	@Override
	protected void crearPantalla() {
		// TODO Auto-generated method stub
		
	}

	
}

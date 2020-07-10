package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import umu.tds.modelo.Usuario;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Font;

public class VentanaPerfil extends Ventana{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame fPerfil;
	
	private Usuario actual;
	private JTextField teleUser;
	private JTextField emailUser;
	private JTextField nameUser;
	private JTextField mSaludoUser;
	private JTextField isPremium;
	private int size = 100;

	/**
	 * Create the frame.
	 */
	public VentanaPerfil(Usuario u) {
		actual = u;
		crearPantalla();
		mostrarVentana(fPerfil);
	}



	@Override
	protected void crearPantalla() {
		fPerfil = new JFrame();
		fPerfil.setTitle("Perfil Usuario");
		fPerfil.setSize(450, 250);
		fPerfil.setLocationRelativeTo(null);
		fPerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fPerfil.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		fPerfil.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 20, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		Icon icono = getImagenIcon(actual.getPathImg(), size, size);
		JLabel foto = new JLabel(icono);
		GridBagConstraints gbc_foto = new GridBagConstraints();
		gbc_foto.fill = GridBagConstraints.BOTH;
		gbc_foto.gridheight = 4;
		gbc_foto.gridwidth = 5;
		gbc_foto.insets = new Insets(0, 0, 5, 5);
		gbc_foto.gridx = 0;
		gbc_foto.gridy = 2;
		panel.add(foto, gbc_foto);
		
		JLabel nombre = new JLabel("Nombre:");
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.anchor = GridBagConstraints.EAST;
		gbc_nombre.fill = GridBagConstraints.VERTICAL;
		gbc_nombre.insets = new Insets(0, 0, 5, 5);
		gbc_nombre.gridx = 5;
		gbc_nombre.gridy = 2;
		panel.add(nombre, gbc_nombre);
		
		nameUser = new JTextField(actual.getNombre());
		nameUser.setEditable(false);
		GridBagConstraints gbc_nameUser = new GridBagConstraints();
		gbc_nameUser.insets = new Insets(0, 0, 5, 5);
		gbc_nameUser.fill = GridBagConstraints.BOTH;
		gbc_nameUser.gridx = 6;
		gbc_nameUser.gridy = 2;
		panel.add(nameUser, gbc_nameUser);
		nameUser.setColumns(10);
		
		JLabel Telefono = new JLabel("Telefono:");
		Telefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_Telefono = new GridBagConstraints();
		gbc_Telefono.fill = GridBagConstraints.VERTICAL;
		gbc_Telefono.anchor = GridBagConstraints.EAST;
		gbc_Telefono.insets = new Insets(0, 0, 5, 5);
		gbc_Telefono.gridx = 5;
		gbc_Telefono.gridy = 3;
		panel.add(Telefono, gbc_Telefono);
		
		teleUser = new JTextField(actual.getMovil());
		teleUser.setEditable(false);
		GridBagConstraints gbc_teleUser = new GridBagConstraints();
		gbc_teleUser.anchor = GridBagConstraints.NORTH;
		gbc_teleUser.insets = new Insets(0, 0, 5, 5);
		gbc_teleUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_teleUser.gridx = 6;
		gbc_teleUser.gridy = 3;
		panel.add(teleUser, gbc_teleUser);
		teleUser.setColumns(10);
		
		JLabel mSaludo = new JLabel("Mensaje Saludo:");
		mSaludo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_mSaludo = new GridBagConstraints();
		gbc_mSaludo.fill = GridBagConstraints.VERTICAL;
		gbc_mSaludo.anchor = GridBagConstraints.EAST;
		gbc_mSaludo.insets = new Insets(0, 0, 5, 5);
		gbc_mSaludo.gridx = 5;
		gbc_mSaludo.gridy = 4;
		panel.add(mSaludo, gbc_mSaludo);
		
		mSaludoUser = new JTextField(actual.getMsgSaludo());
		mSaludoUser.setEditable(false);
		GridBagConstraints gbc_mSaludoUser = new GridBagConstraints();
		gbc_mSaludoUser.insets = new Insets(0, 0, 5, 5);
		gbc_mSaludoUser.fill = GridBagConstraints.BOTH;
		gbc_mSaludoUser.gridx = 6;
		gbc_mSaludoUser.gridy = 4;
		panel.add(mSaludoUser, gbc_mSaludoUser);
		mSaludoUser.setColumns(10);
		
		JLabel Email = new JLabel("Email:");
		Email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_Email = new GridBagConstraints();
		gbc_Email.fill = GridBagConstraints.VERTICAL;
		gbc_Email.anchor = GridBagConstraints.EAST;
		gbc_Email.insets = new Insets(0, 0, 5, 5);
		gbc_Email.gridx = 5;
		gbc_Email.gridy = 5;
		panel.add(Email, gbc_Email);
		
		emailUser = new JTextField(actual.getEmail());
		emailUser.setEditable(false);
		GridBagConstraints gbc_emailUser = new GridBagConstraints();
		gbc_emailUser.insets = new Insets(0, 0, 5, 5);
		gbc_emailUser.fill = GridBagConstraints.BOTH;
		gbc_emailUser.gridx = 6;
		gbc_emailUser.gridy = 5;
		panel.add(emailUser, gbc_emailUser);
		emailUser.setColumns(10);
		
		JLabel IsPremium = new JLabel("isPremium:");
		IsPremium.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_IsPremium = new GridBagConstraints();
		gbc_IsPremium.fill = GridBagConstraints.VERTICAL;
		gbc_IsPremium.anchor = GridBagConstraints.EAST;
		gbc_IsPremium.insets = new Insets(0, 0, 5, 5);
		gbc_IsPremium.gridx = 5;
		gbc_IsPremium.gridy = 6;
		panel.add(IsPremium, gbc_IsPremium);
		
		isPremium = new JTextField(String.valueOf(actual.isPremium()));
		isPremium.setEditable(false);
		GridBagConstraints gbc_premiumlUser = new GridBagConstraints();
		gbc_premiumlUser.insets = new Insets(0, 0, 5, 5);
		gbc_premiumlUser.fill = GridBagConstraints.BOTH;
		gbc_premiumlUser.gridx = 6;
		gbc_premiumlUser.gridy = 6;
		panel.add(isPremium, gbc_premiumlUser);
		isPremium.setColumns(10);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

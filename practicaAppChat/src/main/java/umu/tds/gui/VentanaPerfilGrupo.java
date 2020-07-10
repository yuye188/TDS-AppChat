package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import umu.tds.modelo.Contacto;
import umu.tds.modelo.Grupo;
import umu.tds.modelo.Usuario;

public class VentanaPerfilGrupo extends Ventana {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame fPerfil;

	private Grupo grupo;
	private JTextArea nameUsers;
	private JTextField nameGrupo;

	private int size = 100;
	
	public VentanaPerfilGrupo(Contacto g) {
		grupo = (Grupo)g;
		crearPantalla();
		mostrarVentana(fPerfil);
	}
	
	@Override
	protected void crearPantalla() {
		fPerfil = new JFrame();
		fPerfil.setTitle("Perfil Grupo");
		fPerfil.setSize(450, 390);
		fPerfil.setLocationRelativeTo(null);
		fPerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fPerfil.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		fPerfil.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		Icon icono = getImagenIcon(grupo.getPathImg(), size, size);
		JLabel foto = new JLabel(icono);
		GridBagConstraints gbc_foto = new GridBagConstraints();
		gbc_foto.fill = GridBagConstraints.BOTH;
		gbc_foto.gridheight = 5;
		gbc_foto.gridwidth = 5;
		gbc_foto.insets = new Insets(5, 0, 5, 5);
		gbc_foto.gridx = 0;
		gbc_foto.gridy = 4;
		panel.add(foto, gbc_foto);

		
		JLabel nombre = new JLabel("Nombre del grupo:");
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.gridwidth = 2;
		gbc_nombre.anchor = GridBagConstraints.EAST;
		gbc_nombre.fill = GridBagConstraints.BOTH;
		gbc_nombre.insets = new Insets(0, 0, 5, 5);
		gbc_nombre.gridx = 3;
		gbc_nombre.gridy = 2;
		panel.add(nombre, gbc_nombre);
		
		
		
		nameGrupo = new JTextField(grupo.getNombre());
		nameGrupo.setEditable(false);
		GridBagConstraints gbc_nameUser = new GridBagConstraints();
		gbc_nameUser.gridwidth = 2;
		gbc_nameUser.insets = new Insets(0, 0, 5, 5);
		gbc_nameUser.fill = GridBagConstraints.BOTH;
		gbc_nameUser.gridx = 5;
		gbc_nameUser.gridy = 2;
		panel.add(nameGrupo, gbc_nameUser);
		nameGrupo.setColumns(10);
		
		String nombreMiembros = "";
		for(Usuario u: grupo.getMiembros())
			nombreMiembros += u.getNombre()+"\n";
		
		System.out.println(nombreMiembros);
		nameUsers = new JTextArea();
		nameUsers.setLineWrap(true);
		nameUsers.setEditable(false);
		GridBagConstraints gbc_nameUsers = new GridBagConstraints();
		gbc_nameUsers.gridheight = 8;
		gbc_nameUsers.gridwidth = 2;
		gbc_nameUsers.insets = new Insets(0, 0, 5, 5);
		gbc_nameUsers.fill = GridBagConstraints.BOTH;
		gbc_nameUsers.gridx = 5;
		gbc_nameUsers.gridy = 3;
		panel.add(nameUsers, gbc_nameUsers);
		nameUsers.setColumns(10);
		nameUsers.setText(nombreMiembros);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

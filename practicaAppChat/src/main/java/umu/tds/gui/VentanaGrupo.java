package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.ls.LSInput;

import umu.tds.modelo.Contacto;
import umu.tds.modelo.ContactoIndividual;
import umu.tds.modelo.Grupo;
import umu.tds.modelo.Usuario;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.MenuItem;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

public class VentanaGrupo extends Ventana {

	private JPanel contentPane;
	private JFrame fGrupo;
	private int size = 45;
	
	private JTextField nombreGrupo;
	private JTextField administrador;
	private JPanel panel_Izquierda;
	private JPanel panel_Derecha;
	private JButton btnAceptar;
	private JButton btnCancelar;

	private Usuario actual;
	private Grupo gActual;
	private List<ContactoIndividual> listaContactoIz;
	private List<ContactoIndividual> listaContactoDer;
	private List<JMenuItem> menuitemIz;
	private List<JMenuItem> menuitemDer;

	public VentanaGrupo(Usuario u, Grupo g) {
		System.out.println("Creando Ventana Grupo");
		System.out.println("El usuario anterior es:" + unica.getUsuarioActual().getNombre());
		actual = u;
		gActual = g;
		Ventana.unica.setUsuarioActual(actual);
		System.out.println("El usuario actual es:" + unica.getUsuarioActual().getNombre());
		crearPantalla();
		mostrarVentana(fGrupo);
	}

	@Override
	protected void crearPantalla() {

		// TODO Auto-generated method stub
		if(gActual == null) {
			fGrupo = new JFrame();
			fGrupo.setTitle("Creacion Grupo");
			fGrupo.setSize(400, 580);
			fGrupo.setLocationRelativeTo(null);
			fGrupo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			fGrupo.getContentPane().setLayout(new BorderLayout(0, 0));
		}else {
			fGrupo = new JFrame();
			fGrupo.setTitle("Modificacion Grupo");
			fGrupo.setSize(400, 580);
			fGrupo.setLocationRelativeTo(null);
			fGrupo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			fGrupo.getContentPane().setLayout(new BorderLayout(0, 0));
		}


		JPanel panel_Norte = new JPanel();
		fGrupo.getContentPane().add(panel_Norte, BorderLayout.NORTH);
		panel_Norte.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel lblNewLabel = new JLabel("Nombre Grupo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_Norte.add(lblNewLabel);

		
		nombreGrupo = new JTextField();
		panel_Norte.add(nombreGrupo);
		nombreGrupo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Administrador Grupo:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_Norte.add(lblNewLabel_1);

		administrador = new JTextField();
		administrador.setEditable(false);
		panel_Norte.add(administrador);
		administrador.setColumns(10);

		JPanel panel_Central = new JPanel();
		fGrupo.getContentPane().add(panel_Central, BorderLayout.CENTER);
		panel_Central.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_Contendor_Iz = new JPanel();
		panel_Central.add(panel_Contendor_Iz);
		panel_Contendor_Iz.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_2 = new JLabel("Lista Contacto");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_Contendor_Iz.add(lblNewLabel_2, BorderLayout.NORTH);

		JScrollPane scrollPane_Izquierda = new JScrollPane();
		scrollPane_Izquierda.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_Contendor_Iz.add(scrollPane_Izquierda);

		panel_Izquierda = new JPanel();
		scrollPane_Izquierda.setViewportView(panel_Izquierda);
		panel_Izquierda.setLayout(new GridLayout(0, 1, 5, 0));

		JPanel panel_Contenedor_Der = new JPanel();
		panel_Central.add(panel_Contenedor_Der);
		panel_Contenedor_Der.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_3 = new JLabel("Miembro");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_Contenedor_Der.add(lblNewLabel_3, BorderLayout.NORTH);

		JScrollPane scrollPane_Derecha = new JScrollPane();
		scrollPane_Derecha.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_Contenedor_Der.add(scrollPane_Derecha);

		panel_Derecha = new JPanel();
		scrollPane_Derecha.setViewportView(panel_Derecha);
		panel_Derecha.setLayout(new GridLayout(0, 1, 5, 0));

		JPanel panel_Sur = new JPanel();
		fGrupo.getContentPane().add(panel_Sur, BorderLayout.SOUTH);
		panel_Sur.setLayout(new GridLayout(0, 2, 0, 0));

		btnAceptar = new JButton("ACEPTAR");
		panel_Sur.add(btnAceptar);

		btnCancelar = new JButton("CANCELAR");
		panel_Sur.add(btnCancelar);

		// SET TEXT FIED
		administrador.setText(actual.getUsuario());

		// INICIALIZAR LISTA
				listaContactoIz = new ArrayList<ContactoIndividual>();
				listaContactoDer = new ArrayList<ContactoIndividual>();
				menuitemIz = new ArrayList<JMenuItem>();
				menuitemDer = new ArrayList<JMenuItem>();

		//MOSTRAR CONTACTOS DEPENDIENDO DE GRUPO
		if(gActual == null) {
			// AÑADIR MIEMBROS A PANEL IZQUIERDA
			for (Contacto c : actual.getListaContacto()) {

				ContactoIndividual con = (ContactoIndividual) c;
				Icon icono = getImagenIcon(con.getUsuario().getPathImg(), size, size);

				JMenuItem jmi = new JMenuItem(con.getNombre(), icono);
				jmi.addActionListener(this);

				// AÑADIR A LA LISTA DE IZQUIERDA
				listaContactoIz.add(con);
				menuitemIz.add(jmi);

				// AÑADIR A LA PANTALLA
				panel_Izquierda.add(jmi);
				
				/*ContactoIndividual con2 = (ContactoIndividual) c;
				Icon icono2 = getImagenIcon(con.getUsuario().getPathImg(), size, size);

				JMenuItem jmi2 = new JMenuItem("DDdD", icono2);
				jmi2.addActionListener(this);

				// AÑADIR A LA LISTA DE IZQUIERDA
				listaContactoIz.add(con2);
				menuitemIz.add(jmi2);

				// AÑADIR A LA PANTALLA
				panel_Izquierda.add(jmi2);*/
			}
		}else {
			//SI EL GRUPO YA ESXITE 
			nombreGrupo.setText(gActual.getNombre());
			
			List <Contacto> listaC = actual.getListaContacto();
 			List <Usuario> listU = new ArrayList<Usuario>();
			
 			//OBTENER USUARIOS DE LA LISTA DE CONTACTO
 			for(Contacto c: listaC) {
 				Usuario u = ((ContactoIndividual)c).getUsuario();
 				if(gActual.getMiembros().contains(u)) {
 					listaContactoDer.add((ContactoIndividual) c);
 				}else {
 					listaContactoIz.add((ContactoIndividual) c);
 				}
 			}
 			
 			//PARA CONTACTOS NO AÑADIDOS AL GRUPO
			for(ContactoIndividual con : listaContactoIz){
				Icon icono = getImagenIcon(con.getUsuario().getPathImg(), size, size);
				JMenuItem jmi = new JMenuItem(con.getNombre(), icono);
				jmi.addActionListener(this);

				menuitemIz.add(jmi);
				// AÑADIR A LA PANTALLA
				panel_Izquierda.add(jmi);
			}
			
			//PARA CONTACTOS AÑADIDOS AL GRUPO
			for(ContactoIndividual con : listaContactoDer){
				Icon icono = getImagenIcon(con.getUsuario().getPathImg(), size, size);
				JMenuItem jmi = new JMenuItem(con.getNombre(), icono);
				jmi.addActionListener(this);

				menuitemDer.add(jmi);
				// AÑADIR A LA PANTALLA
				panel_Derecha.add(jmi);
			}
		}
		
		

		// AÑADIR MANEJADORES
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnAceptar) {
			System.out.println("Pulsado Crear Grupo");
			if (nombreGrupo.getText().isEmpty()) {
				JOptionPane.showMessageDialog(fGrupo, "Nombre Grupo Vacio", "Nombre Grupo",
						JOptionPane.WARNING_MESSAGE);
			}else if(listaContactoDer.isEmpty()) {
				JOptionPane.showMessageDialog(fGrupo, "No hay miembro para grupo", "Grupo", JOptionPane.WARNING_MESSAGE);
			}else {
				if(gActual == null) {
					Grupo g = unica.crearGrupo(nombreGrupo.getText(),listaContactoDer);
					System.out.println("Creado Grupo: "+ g.getNombre());
					liberarVentana(fGrupo);
				}
				else {
					System.out.println("Modificando Grupo");
					//Grupo g = unica.crearGrupo(nombreGrupo.getText(),listaContactoDer);
					//liberarVentana(fGrupo);
				}
			}
		}
		
		//PANEL IZQUIERDA
		if (menuitemIz.contains(e.getSource())) {

			System.out.println("Seleccionando Contacto de Panel Izquierda");
			System.out.println("Item size Iz:" + menuitemIz.size() +" lista size Iz: " +listaContactoIz.size());
			System.out.println("Item size Der:" + menuitemDer.size() +" lista size Der: " +listaContactoDer.size());
			
			int i = 0;
			while (!menuitemIz.get(i).equals(e.getSource())) {
				i++;
			}
			
			System.out.println("Seleccionado Contacto de la posicion :" + i);
			ContactoIndividual m = listaContactoIz.get(i);
			JMenuItem item = menuitemIz.get(i);
			//item.addActionListener(this);
			System.out.println("El contacto actual a añadir al grupo : " + m.getNombre());

			// AÑADIRLO A LA LISTA DE MIEMBRO GRUPO
			listaContactoDer.add(m);
			menuitemDer.add(item);
			//menuitemDer.add(menuitemIz.get(i));
			// ELIMINARLO DE LA LISTA ACTUAL
			listaContactoIz.remove(i);
			menuitemIz.remove(i);

			// ELIMINAR EL COMPONENTE DE PANEL IZQUIERDA
			panel_Izquierda.remove(i);

			// AÑADIRLO AL PANEL DE DERECHA
			panel_Derecha.add(item);

			// REPINTAR PANTALLA
			panel_Izquierda.revalidate();
			panel_Izquierda.repaint();
			
			panel_Derecha.revalidate();
			panel_Derecha.repaint();
			
			System.out.println("Item size Iz:" + menuitemIz.size() +" lista size Iz: " +listaContactoIz.size());
			System.out.println("Item size Der:" + menuitemDer.size() +" lista size Der: " +listaContactoDer.size());
		}

		else if (menuitemDer.contains(e.getSource())) {

			System.out.println("Seleccionando Contacto de Panel Derecha");
			System.out.println("Item size Iz:" + menuitemIz.size() +" lista size Iz: " +listaContactoIz.size());
			System.out.println("Item size Der:" + menuitemDer.size() +" lista size Der: " +listaContactoDer.size());
			int i = 0;
			while (!menuitemDer.get(i).equals(e.getSource())) {
				i++;
			}

			System.out.println("Seleccionado Contacto de la posicion :" + i);
			ContactoIndividual m = listaContactoDer.get(i);
			JMenuItem item = menuitemDer.get(i);
			//item.addActionListener(this);
			System.out.println("El contacto actual a eliminar del grupo : " + m.getNombre());

			// AÑADIRLO A LA LISTA DE MIEMBRO GRUPO
			listaContactoIz.add(m);
			menuitemIz.add(item);
			//menuitemIz.add(menuitemDer.get(i));
			// ELIMINARLO DE LA LISTA ACTUAL
			listaContactoDer.remove(i);
			menuitemDer.remove(i);

			// ELIMINAR EL COMPONENTE DE PANEL IZQUIERDA
			panel_Derecha.remove(i);

			// AÑADIRLO AL PANEL DE DERECHA
			panel_Izquierda.add(item);

			// REPINTAR PANTALLA
			panel_Izquierda.revalidate();
			panel_Izquierda.repaint();
			panel_Derecha.revalidate();
			panel_Derecha.repaint();
			
			System.out.println("Item size Iz:" + menuitemIz.size() +" lista size Iz: " +listaContactoIz.size());
			System.out.println("Item size Der:" + menuitemDer.size() +" lista size Der: " +listaContactoDer.size());
		}

		if (e.getSource() == btnCancelar) {
			System.out.println("Pulsado Cancelar Creacion Grupo");
			liberarVentana(fGrupo);
		}

	}

}

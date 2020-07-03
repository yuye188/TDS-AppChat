package umu.tds.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import umu.tds.catalogo.CatalogoUsuario;
import umu.tds.controlador.ControladorAppChat;
import umu.tds.modelo.Usuario;

@SuppressWarnings("serial")
public abstract class Ventana extends JPanel implements ActionListener {
	
	public static final int SIZE_X = 350;
	public static final int SIZE_Y = 580;
	
	public static ControladorAppChat unica = ControladorAppChat.getUnicaInstancia();
	//public static Usuario actual = new Usuario("Ruiqing", new Date(), "111", "nombre1@um.es", "Rui", "qq", "Hola");
	//public static Usuario actual = CatalogoUsuario.getUnicaInstancia().getUsuario("11");
	public static Usuario actual = null;
	
	// Para la configuración del panel
	protected abstract void crearPantalla();

	// Para tratamiento de los eventos
	public abstract void actionPerformed(ActionEvent e);
	
	//
	public void mostrarVentana(JFrame frame) {frame.setVisible(true);}
	
	//Para liberar Ventana Actual
	public void liberarVentana(JFrame frame) {frame.dispose();};

	// Para configurar las dimensiones de la ventana
	protected void setSize(JComponent componente, int x, int y) {
		componente.setMinimumSize(new Dimension(x, y));
		componente.setMaximumSize(new Dimension(x, y));
		componente.setPreferredSize(new Dimension(x, y));
	}

	// Para obtener la imagen dado su path y las escalas x e y
	protected ImageIcon getImagenIcon(String path, int x, int y) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image img = imageIcon.getImage();
		Image imgSacled = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		return new ImageIcon(imgSacled);
	}

	//Funci髇 que devuelve la imagen especificada con tama駉 x * x
	protected ImageIcon getImagenIcon(Image image, int x) {
		Image newimg = image.getScaledInstance(x, x, Image.SCALE_SMOOTH);
		return (new ImageIcon(newimg));
	}

	
}

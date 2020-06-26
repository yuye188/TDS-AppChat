package umu.tds.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Ventana extends JPanel implements ActionListener {
	
	// Para la configuración del panel
	protected abstract void crearPantalla();

	// Para tratamiento de los eventos
	public abstract void actionPerformed(ActionEvent evento);
	
	public abstract void mostrarVentana(boolean b) ;

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

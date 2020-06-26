package umu.tds.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class VentanaConversacion extends Ventana{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame contentPane;
	
	public VentanaConversacion() {
		crearPantalla();
		mostrarVentana(true);
	}	
	
	@Override
	protected void crearPantalla() {
		// TODO Auto-generated method stub
		contentPane = new JFrame();
		contentPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setBounds(100, 100, 300, 400);
		contentPane.setTitle("Conversacion");
		contentPane.getContentPane().setLayout(new BoxLayout(contentPane.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panelGeneral = new JPanel();
		contentPane.getContentPane().add(panelGeneral);
		panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) panelSuperior.getLayout();
		flowLayout.setAlignOnBaseline(true);
		panelGeneral.add(panelSuperior);
		
		JButton btnVolvel = new JButton("Volver");
		panelSuperior.add(btnVolvel);
		
		JButton btnUsuario = new JButton("Usuario");
		panelSuperior.add(btnUsuario);
		
		JButton btnLlamar = new JButton("Llamar");
		panelSuperior.add(btnLlamar);
		
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scrollPane = new JScrollPane(panelCentral);
		panelGeneral.add(scrollPane);
		FlowLayout flowLayout_1 = (FlowLayout) panelCentral.getLayout();
		flowLayout_1.setAlignOnBaseline(true);
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout_2 = (FlowLayout) panelInferior.getLayout();
		flowLayout_2.setAlignOnBaseline(true);
		panelGeneral.add(panelInferior);
		
		JButton btnEmoji = new JButton("Emoji");
		panelInferior.add(btnEmoji);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		JScrollPane scrollMensaje = new JScrollPane(textArea);
		panelInferior.add(scrollMensaje);
		
		JButton btnEnviar = new JButton("Enviar");
		panelInferior.add(btnEnviar);

		
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarVentana(boolean b) {
		// TODO Auto-generated method stub
		
	}

}

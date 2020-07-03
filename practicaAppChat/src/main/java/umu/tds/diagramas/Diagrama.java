package umu.tds.diagramas;

import java.awt.Color;
import java.io.IOException;
import java.text.DecimalFormat;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.style.PieStyler.AnnotationType;

import umu.tds.modelo.Usuario;
import umu.tds.modelo.Grupo;


public class Diagrama {
	public static void generarDiagrama(Usuario usuario){
		
		// Create Chart
		String titulo = "Diagrama del mensajes enviados a los grupos del usuario " + usuario.getNombre();
		PieChart chart = new PieChartBuilder()
									.width(800)
									.height(600)
									.title(titulo)
									.build();

		// Customize Chart
		Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110), new Color(243, 180, 159), new Color(246, 199, 182) };
		chart.getStyler().setSeriesColors(sliceColors);
	    chart.getStyler().setAnnotationType(AnnotationType.LabelAndPercentage);
	    
	    // Series
	    DecimalFormat format =  new DecimalFormat("#.##");
	    for(Grupo g : usuario.ordenarGruposSegunMensajes()) {
	    	if(!g.getMensajes().isEmpty()) {
	    		chart.addSeries(g.getNombre() + " : " + 
	    				format.format(g.calcularPorcentajeMensajesDeEnvios(usuario)),
	    				g.getMensajesEnviados(usuario).size());
	    	}
	    }
	    
	    // Save
		try {
			BitmapEncoder.saveBitmap(chart, "estadisticas/"+usuario.getNombre()+"_diagrama", BitmapFormat.PNG);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

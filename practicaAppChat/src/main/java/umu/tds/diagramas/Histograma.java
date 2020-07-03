package umu.tds.diagramas;

import java.io.IOException;
import java.util.Arrays;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

import umu.tds.modelo.Usuario;


public class Histograma {

	public static void generarHistograma(Usuario usuario) {

		// Create Chart
		String titulo = "Número de mensajes enviados por meses del usuario: "+usuario.getNombre();
		CategoryChart chart = new CategoryChartBuilder()
											.width(800)
											.height(600)
											.title(titulo)
											.xAxisTitle("Mes")
											.yAxisTitle("Núm. de msg enviados")
											.build();

		// Customize Chart
		chart.getStyler().setHasAnnotations(true);

		// Series
		chart.addSeries("test 1", Arrays.asList(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Sept.", "Octu.", "Noviem.", "Diciem." }),
				usuario.calcularNumMsgPorMeses());

		// Save
		try {
			BitmapEncoder.saveBitmap(chart, "estadisticas/"+usuario.getNombre()+"_histograma", BitmapFormat.PNG);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

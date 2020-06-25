package umu.tds.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Estado;

public class AdaptadorEstadoDAO implements IEstadoDAO{

	private ServicioPersistencia servPersistencia;
	private static AdaptadorEstadoDAO unicaInstancia = null;
	
	private AdaptadorEstadoDAO() {
		this.servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public static AdaptadorEstadoDAO getUnicaInstancia() { // patron singleton
		if  (unicaInstancia == null)
			unicaInstancia = new AdaptadorEstadoDAO();
		return unicaInstancia;
	}
	
	public boolean registrarEstado(Estado estado) {
		
		// comprobar si ya existe el estado
		Entidad eEstado;
		boolean existe = true; 
		try {
			eEstado = servPersistencia.recuperarEntidad(estado.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) {
			return false;
		}
		
		// crear entidad Estado
		eEstado = new Entidad();
		eEstado.setNombre("Estado");
		eEstado.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("frase", estado.getFrase()),
						new Propiedad("pathImg", estado.getPathImg()))));

		eEstado = servPersistencia.registrarEntidad(eEstado);
		estado.setCodigo(eEstado.getId()); 
		
		return true;
	}

	public void borrarEstado(Estado estado) {
		Entidad eEstado = servPersistencia.recuperarEntidad(estado.getCodigo());
		servPersistencia.borrarEntidad(eEstado);
	}

	public void modificarEstado(Estado estado) {
		Entidad eEstado = servPersistencia.recuperarEntidad(estado.getCodigo());

		servPersistencia.eliminarPropiedadEntidad(eEstado, "frase");
		servPersistencia.anadirPropiedadEntidad(eEstado, "frase", estado.getFrase());
		servPersistencia.eliminarPropiedadEntidad(eEstado, "pathImg");
		servPersistencia.anadirPropiedadEntidad(eEstado, "pathImg", estado.getPathImg());
	}

	public Estado recuperarEstado(int codigo) {
		
		// Si la entidad está en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Estado) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		// si no, la recupera de la base de datos
		Entidad eEstado;
		String frase;
		String pathImg;

		eEstado = servPersistencia.recuperarEntidad(codigo);
		
		frase = servPersistencia.recuperarPropiedadEntidad(eEstado, "frase");
		pathImg = servPersistencia.recuperarPropiedadEntidad(eEstado, "pathImg");

		Estado estado = new Estado(frase,pathImg);
		estado.setCodigo(codigo);

		// añadir Estado al pool
		PoolDAO.getUnicaInstancia().addObjeto(codigo, estado);		
		
		return estado;
	}

	public List<Estado> recuperarTodosEstados() {
		List<Entidad> eEstados = servPersistencia.recuperarEntidades("Estado");
		List<Estado> estados = new LinkedList<Estado>();

		for (Entidad eEstado : eEstados) {
			estados.add(recuperarEstado(eEstado.getId()));
		}
		return estados;
	}

}

package umu.tds.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Contacto;
import umu.tds.modelo.ContactoIndividual;
import umu.tds.modelo.Usuario;

public class AdaptadorContactoIndividualDAO implements IContactoDAO{
	
	private ServicioPersistencia servPersistencia;
	private static AdaptadorContactoIndividualDAO unicaInstancia = null;
	
	public AdaptadorContactoIndividualDAO() {
		this.servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public static AdaptadorContactoIndividualDAO getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorContactoIndividualDAO();
		return unicaInstancia;
	}

	@Override
	public boolean registrarContacto(Contacto contacto) {
		
		// comprobar si ya existe el Contacto
		Entidad eContactoIndividual;
		boolean existe = true; 
		
		ContactoIndividual contactoIndividual = (ContactoIndividual) contacto;
		try {
			eContactoIndividual = servPersistencia.recuperarEntidad(contactoIndividual.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) {
			return false;
		}
		
		eContactoIndividual = new Entidad();
		eContactoIndividual.setNombre("ContactoIndividual");
		eContactoIndividual.setPropiedades(new ArrayList<Propiedad>(Arrays.asList
					   (new Propiedad("nombre", contactoIndividual.getNombre()),
						//TODO Mensajes
						new Propiedad("movil", contactoIndividual.getMovil()),
						new Propiedad("usuario", String.valueOf(contactoIndividual.getUsuario().getCodigo())))));
		
		eContactoIndividual = servPersistencia.registrarEntidad(eContactoIndividual);
		contactoIndividual.setCodigo(eContactoIndividual.getId()); 
		return true;
	}

	@Override
	public void borrarContacto(Contacto contacto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarContacto(Contacto contacto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Contacto recuperarContacto(int codigo) {
		
		// Si la entidad está en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (ContactoIndividual) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		// si no, la recupera de la base de datos
		Entidad eContactoIndividual;
		String nombre;
		String movil;
		Usuario usuario;
		// TODO Mensajes
		
		eContactoIndividual = servPersistencia.recuperarEntidad(codigo);
		
		nombre = servPersistencia.recuperarPropiedadEntidad(eContactoIndividual, "nombre");
		movil = servPersistencia.recuperarPropiedadEntidad(eContactoIndividual, "movil");
		
		ContactoIndividual contacto = new ContactoIndividual(nombre, movil, null);
		contacto.setCodigo(codigo);
		
		// añadir ContactoIndividual al pool
		PoolDAO.getUnicaInstancia().addObjeto(codigo, contacto);
		
		AdaptadorUsuarioDAO adaptadorUsuario = AdaptadorUsuarioDAO.getUnicaInstancia();
		usuario = adaptadorUsuario.recuperarUsuario(Integer.valueOf(servPersistencia.recuperarPropiedadEntidad(eContactoIndividual, "usuario")));
		
		contacto.setUsuario(usuario);
		
		return contacto;
		
	}

	@Override
	public List<Contacto> recuperarTodosContactos() {
		// TODO Auto-generated method stub
		return null;
	}

}

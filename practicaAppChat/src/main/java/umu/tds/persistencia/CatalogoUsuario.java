package umu.tds.persistencia;

//TODO
public class CatalogoUsuario {
	
	private static CatalogoUsuario unicaInstancia = null;

	public static CatalogoUsuario getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new CatalogoUsuario();
		return unicaInstancia;
	}

}

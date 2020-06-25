package umu.tds.dao;

public class AppChatFactoriaDAO extends FactoriaDAO{

	@Override
	public IUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioDAO.getUnicaInstancia();
	}

	
}

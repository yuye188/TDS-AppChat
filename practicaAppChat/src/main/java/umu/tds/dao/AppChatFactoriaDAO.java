package umu.tds.dao;

public class AppChatFactoriaDAO extends FactoriaDAO{

	@Override
	public IUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioDAO.getUnicaInstancia();
	}

	@Override
	public IContactoDAO getContactoIndividualDAO() {
		return AdaptadorContactoIndividualDAO.getUnicaInstancia();
	}

	@Override
	public IContactoDAO getGrupoDAO() {
		return AdaptadorGrupoDAO.getUnicaInstancia();
	}

	
}

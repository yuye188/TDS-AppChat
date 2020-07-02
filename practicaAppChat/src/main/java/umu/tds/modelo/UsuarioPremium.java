package umu.tds.modelo;

public class UsuarioPremium implements RolUsuario{

	@Override
	public void convertirseEnPremium(Usuario usuario) {
		if (!usuario.isPremium()) {
			System.out.println("La cuenta del usuario se ha convertido a Premium pagando: "
					+ usuario.getDescuento().calcularDescuento() + "euros");
			usuario.setPremium(true);
			usuario.setRol(this);
		}
		else
			System.out.println("La cuenta del usuario ya es Premium");
		
	}

	@Override
	public void volverEnNormal(Usuario usuario) {
		return;
	}

}

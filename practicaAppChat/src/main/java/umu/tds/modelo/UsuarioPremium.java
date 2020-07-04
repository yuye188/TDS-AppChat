package umu.tds.modelo;

public class UsuarioPremium implements RolUsuario{

	@Override
	public double convertirseEnPremium(Usuario usuario) {
		if (!usuario.isPremium()) {
			/*
			System.out.println("La cuenta del usuario se ha convertido a Premium pagando: "
					+ usuario.getDescuento().calcularDescuento() + "euros");
					*/
			usuario.setPremium(true);
			usuario.setRol(this);
			return usuario.getDescuento().calcularDescuento();
		}
		return 0.0;
		
	}

	@Override
	public double volverEnNormal(Usuario usuario) {
		return 0.0;
	}

}

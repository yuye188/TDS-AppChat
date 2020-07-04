package umu.tds.modelo;

public class UsuarioNormal implements RolUsuario {

	public double convertirseEnPremium(Usuario usuario) {
		return 0.0;
	}

	public double volverEnNormal(Usuario usuario) {
		if (usuario.isPremium()) {
			/*
			System.out.println("La cuenta del usuario se ha vuelto a Normal recibiendo: "
					+ usuario.getDescuento().calcularDescuento() + "euros");
			*/
			usuario.setPremium(false);
			usuario.setRol(this);
			return usuario.getDescuento().calcularDescuento();
		}
		
		return 0.0;
	}

}

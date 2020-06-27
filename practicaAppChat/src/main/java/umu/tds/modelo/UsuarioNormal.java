package umu.tds.modelo;

public class UsuarioNormal implements RolUsuario {

	public void convertirseEnPremium(Usuario usuario) {
		if (!usuario.isPremium())
			System.out.println("La cuenta del usuario se ha convertido a Premium pagando: "
					+ usuario.getDescuento().calcularDescuento() + "euros");
		else
			System.out.println("La cuenta del usuario ya es Premium");
	}

	public void volverEnNormal(Usuario usuario) {
		if (usuario.isPremium())
			System.out.println("La cuenta del usuario se ha vuelto a Normal recibiendo: "
					+ usuario.getDescuento().calcularDescuento() + "euros");
		else
			System.out.println("La cuenta del usuario ya es normal");
	}

}

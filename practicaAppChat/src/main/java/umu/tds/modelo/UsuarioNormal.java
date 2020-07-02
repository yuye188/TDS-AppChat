package umu.tds.modelo;

public class UsuarioNormal implements RolUsuario {

	public void convertirseEnPremium(Usuario usuario) {
		return;
	}

	public void volverEnNormal(Usuario usuario) {
		if (usuario.isPremium()) {
			System.out.println("La cuenta del usuario se ha vuelto a Normal recibiendo: "
					+ usuario.getDescuento().calcularDescuento() + "euros");
			usuario.setPremium(false);
			usuario.setRol(this);
		}
		else
			System.out.println("La cuenta del usuario ya es normal");
	}

}

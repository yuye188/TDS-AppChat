package umu.tds.modelo;

public class UsuarioNormal implements RolUsuario{

	public void convertirseEnPremium(Descuento descuento) {
		System.out.println("La cuenta del usuario se ha convertido a Premium pagando: "+descuento.calcularDescuento());
	}

	public void volverEnNormal() {
		
		System.out.println("La cuenta del usuario ya es normal");
	}

}

package umu.tds.modelo;

public class Estado {

	private String frase;
	private String pathImg;
	private int codigo; // id
	
	public static final String ImgDefecto = "imgs/estado.png";

	public Estado(String frase, String pathImg) {
		super();
		this.frase = frase;
		this.pathImg = pathImg;
	}

	public String getFrase() {
		return frase;
	}

	public String getPathImg() {
		return pathImg;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}

package dominio;

public class Destino {
	private String ciudad;
	private String calle;
	private int numeracion;
	
	public Destino (String ciudad, String calle, int numeracion) {
		this.ciudad = ciudad;
		this.calle = calle;
		this.numeracion = numeracion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public int getNumeracion() {
		return numeracion;
	}
}

package dominio;

public class Paquete {
	private double alto;
	private double ancho;
	private double profundo;
	private double peso;
	private Destino destino;
	
	public Paquete (Destino destino, double alto, double ancho, double profundo, double peso) {
		this.alto = alto;
		this.ancho = ancho;
		this.profundo = profundo;
		this.peso = peso;
		this.destino = destino;
	}
	
	public double calcularVolumen() {
		return alto * ancho * profundo;
	}

	public double getPeso() {
		return peso;
	}

	public Destino getDestino() {
		return destino;
	}
	
	
}

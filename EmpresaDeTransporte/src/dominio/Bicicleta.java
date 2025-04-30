package dominio;

class Bicicleta extends Vehiculo {
	
	public Bicicleta () {
		super(2, 2);
	}

	@Override
	public boolean puedeAgregarPaquete(Paquete p) {
		
		if(cantidadPaquetes >= 2 || p.calcularVolumen() > 0.125 || p.getPeso() > 15) {
			return false;
		}else if(cantidadDestinos == 0 || destinos [0].equals(p.getDestino())) {
			return true;
		}
		return false;
	}
	
	

}

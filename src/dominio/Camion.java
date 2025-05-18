package dominio;


public class Camion extends Vehiculo {
	public Camion(){
		super(20, 1000, 16000);//volumen , ciudades, resistepeso
	}

	@Override
	public boolean puedeAgregarPaquete(Paquete p) {
		double pesoTotal = 0;
		double volumenTotal = 0;
		if(p.getPeso() > resistePeso || p.calcularVolumen() > volumenDisponible) { //si el paquete solo pasa alguno de los limites prefijados retorna false
			return false;
		}
		
		if(paquetes.size() == 0) {
			return true;
		}
		
		for(Paquete a : paquetes) {
			pesoTotal += a.getPeso();
			volumenTotal += a.calcularVolumen();
		}
		
		if((pesoTotal + p.getPeso()) > resistePeso || volumenTotal + p.calcularVolumen() > volumenDisponible ) {
			return false;
		}
		
		return false;
	}    
}


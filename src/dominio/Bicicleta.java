package dominio;

public class Bicicleta extends Vehiculo {

	public Bicicleta() {
		super(0.250, 1, 15);
	}

	@Override
	public boolean puedeAgregarPaquete(Paquete p) {
		String ciudadDeDestino = null;
		if (p.calcularVolumen() > (volumenDisponible / 2) || p.getPeso() > resistePeso || paquetes.size() > 1) {
			
			return false;
		} 

		if(paquetes.size()==1) {
			
			if ((paquetes.get(0).getPeso() + p.getPeso()) > resistePeso 
			|| (paquetes.get(0).calcularVolumen() + p.calcularVolumen()) > volumenDisponible) {
				
				return false;
			}
			ciudadDeDestino = paquetes.get(0).getDestino().getCiudad();
			if(!ciudadDeDestino.equals(p.getDestino().getCiudad())){
				return false;
			}
		}
		
		if(paquetes.size() >= 2) {
			return false;
		}
		
		return true;
	}
	
	
	
}

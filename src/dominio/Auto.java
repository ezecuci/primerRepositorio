package dominio;

import java.util.ArrayList;
import java.util.List;

public class Auto extends Vehiculo {
	
	public Auto(){
		super(2, 3, 500);
	}

	@Override
	public boolean puedeAgregarPaquete(Paquete p) {
		
		double pesoTotal = 0;
		double volumenTotal = 0;
		if(p.getPeso() > resistePeso || p.calcularVolumen() > volumenDisponible) { //si el paquete solo pasa alguno de los limites prefijados retorna false
			return false;
		}
		
		if(paquetes.size() == 0) {// se pregunta si no hay paquetes guardados
			return true;
		}
		
		for(Paquete a : paquetes) {
			pesoTotal += a.getPeso();
			volumenTotal += a.calcularVolumen();
		}
		
		if((pesoTotal + p.getPeso()) > resistePeso || volumenTotal + p.calcularVolumen() > volumenDisponible ) {
			return false;// si el peso o volument cargado mas el peso o volumen del paquete actual supera los limites devuelve false
		}
		
		List <String> ciudades = new ArrayList <>();

	    for (Paquete a : paquetes) {
	        String ciudad = a.getDestino().getCiudad();

	        if (!ciudades.contains(ciudad)) {
	            if (ciudades.size() < 3) {
	                ciudades.add(ciudad);
	            }
	        }
	    }
	    
	    
	    String ciudadActual = p.getDestino().getCiudad();
	    
	    if (ciudades.contains(ciudadActual)) {
	    	return true;
	    }
	    
	    if (ciudades.size() < 3) {
	    	return true;
	    }
	    
		return false;
	}    
}






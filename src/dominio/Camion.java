package dominio;

public class Camion extends Vehiculo {
	public Camion(){
		super(20, 1000, 16000);
	}

	@Override
	public boolean puedeAgregarPaquete(Paquete p) {
		double pesoTotal = 0;
		double volumenTotal = 0;
		if(p.getPeso() > resistePeso || p.calcularVolumen() > volumenDisponible) { //si el paquete solo pasa alguno de los limites prefijados retorna false
			return false;
		}
		
		for(int i = 0; i < paquetes.length ; i ++) { // si sumando el paquete pasa alguno de los limites prefijados retorna false
			if (paquetes[i] != null) {
				volumenTotal += paquetes[i].calcularVolumen();
				pesoTotal += paquetes[i].getPeso();
			}
		}
		if (paquetes[0] == null) {
		    return true;
		}
		
		if (paquetes[0] != null) {// si sumando el paquete alguno de los dos parametros se pasa devuelve false
			if ((volumenTotal + p.calcularVolumen()) > volumenDisponible || (pesoTotal + p.getPeso()) > resistePeso) {
				return false;	
			}
			
			for(int i = 0; i < paquetes.length ; i ++ ) {
				if (paquetes[i] != null) {
					if(p.getDestino().getCiudad().equals(paquetes[i].getDestino().getCiudad())) {
						return true;//si ya esta registrada la ciudad devuelve true
					}
				}
			}
			
			String [] ciudades = new String[maxCiudades];
		    int cantidadCiudades = 0;
		    for (int i = 0; i < paquetes.length; i++) {
		        if (paquetes[i] != null) {
		            String ciudadActual = paquetes[i].getDestino().getCiudad();
		            boolean yaRegistrada = false;

		            for (int j = 0; j < cantidadCiudades; j++) {
		                if (ciudades[j].equals(ciudadActual)) {
		                    yaRegistrada = true;
		                    break;
		                }
		            }

		            if (!yaRegistrada) {
		                if (cantidadCiudades < maxCiudades) {
		                    ciudades[cantidadCiudades] = ciudadActual;
		                    cantidadCiudades++;
		                } else {
		                    // hay 3 ciudades distintas registradas
		                    break;
		                }
		            }
		        }
		    }

		    // Verifico si la ciudad del nuevo paquete ya está entre las registradas
		    String ciudadPaquete = p.getDestino().getCiudad();
		    for (int i = 0; i < cantidadCiudades; i++) {
		        if (ciudades[i].equals(ciudadPaquete)) {
		            return true;
		        }
		    }

		    // Si hay espacio para una nueva ciudad, también se puede cargar
		    if (cantidadCiudades <3) {
		    	return true;
			}
		}
		return false;
	}	
}

package dominio;

class Auto extends Vehiculo {
	
	public Auto(){
		super(100000, 100000);
	}

	@Override
	public boolean puedeAgregarPaquete(Paquete p) {
		
		double pesoTotal = 0;
		double volumenTotal = 0;
		if(p.getPeso() > 500 || p.calcularVolumen() > 2) { //si el paquete solo pasa alguno de los limites prefijados retorna false
			return false;
		}
		
		for(int i = 0; i < paquetes.length ; i ++) { // si sumando el paquete pasa alguno de los limites prefijados retorna false
				volumenTotal += paquetes[i].calcularVolumen();
				pesoTotal += paquetes[i].getPeso();
		}
		
		if (paquetes[0] != null) {// si sumando el paquete alguno de los dos parametros se pasa devuelve false
			if ((volumenTotal + p.calcularVolumen()) > 2 || (pesoTotal + p.getPeso()) > 500) {
				return false;	
			}
			
			for(int i = 0; i < paquetes.length ; i ++ ) {
				if(p.getDestino().getCiudad().equals(paquetes[i].getDestino().getCiudad())) {
					return true;
				}
			}
			String[] ciudades = new String[3];
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
		                if (cantidadCiudades < 3) {
		                    ciudades[cantidadCiudades] = ciudadActual;
		                    cantidadCiudades++;
		                } else {
		                    // Ya hay 3 ciudades distintas registradas
		                    break;
		                }
		            }
		        }
		    }

		    // Verificamos si la ciudad del nuevo paquete ya está entre las registradas
		    String ciudadPaquete = p.getDestino().getCiudad();
		    for (int i = 0; i < cantidadCiudades; i++) {
		        if (ciudades[i].equals(ciudadPaquete)) {
		            return true;
		        }
		    }

		    // Si aún hay espacio para una nueva ciudad, también se puede cargar
		    return cantidadCiudades < 3;
		}
		return false;
	}	
}





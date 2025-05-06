package servicio;

import dominio.*;

public class GestorDeEmpresa {

    private Vehiculo[] vehiculos;
    private int cantidadVehiculos;
    public GestorDeEmpresa() {
        vehiculos = new Vehiculo[100]; // puede almacenar hasta 100 vehículos
        cantidadVehiculos = 0;
    }

    public boolean agregarVehiculo(Vehiculo v) {
    	String idActual;
        if (cantidadVehiculos < vehiculos.length) {
        	idActual = "#" + ((int)(Math.random() * 500) + 100);
        	v.setIdVehiculo(idActual);
            vehiculos[cantidadVehiculos] = v;
            cantidadVehiculos++;
            return true;
        } else {
            return false;
        }
    }

    public boolean agregarPaquete(Paquete p, String codigoVehiculo) {
    	String idPaquete;	
    	for (int i = 0; i < cantidadVehiculos; i++) {
    			if(vehiculos[i].getIdVehiculo().equals(codigoVehiculo)) {
    				if(vehiculos[i].puedeAgregarPaquete(p)) {
    					idPaquete = "#" + ((int)(Math.random() * 500) + 500);
    		        	p.setIdPaquete(idPaquete);
    					vehiculos[i].agregarPaquete(p);
    					return true;
    				}
    			}
    			return false;
    		}
        return false;
    }

    public Vehiculo[] getVehiculos() {
        return vehiculos;
    }

    public int getCantidadVehiculos() {
        return cantidadVehiculos;
    }

	public boolean eliminarPaquete(String idVehiculoActual, String idPaquete) {
		for (int i = 0; i < cantidadVehiculos; i++) {
			if( idVehiculoActual.equals(vehiculos[i].getIdVehiculo())) {
				for(int j = 0; j < vehiculos[i].getPaquetes().length ; j ++) {
					if(!vehiculos[i].getPaquetes()[j].equals(null)) {
						if( idPaquete.equals(vehiculos[i].getPaquetes()[j].getIdPaquete())){
							vehiculos[i].eliminarPaquete(j);
							return true;
						}
					}			
				}
			}
		}
		return false;
	}
}
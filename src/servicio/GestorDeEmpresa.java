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
    	
        if (cantidadVehiculos < vehiculos.length) {
            vehiculos[cantidadVehiculos] = v;
            cantidadVehiculos++;
            return true;
        } else {
            return false;
        }
    }

    public boolean agregarPaquete(Paquete p) {
    	if(vehiculos[0]!= null) {
    		for (int i = 0; i < cantidadVehiculos; i++) {
    			if (vehiculos[i].puedeAgregarPaquete(p)) {
    				vehiculos[i].agregarPaquete(p);
    				return true;
    			}
    		}
    	}	
        return false;
    }

    public Vehiculo[] getVehiculos() {
        return vehiculos;
    }

    public int getCantidadVehiculos() {
        return cantidadVehiculos;
    }    
}
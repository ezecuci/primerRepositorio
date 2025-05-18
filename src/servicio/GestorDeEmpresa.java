package servicio;


import dominio.*;

import java.util.ArrayList;

public class GestorDeEmpresa {
	private int contadorVehiculos;
	private String idVehiculo;
    private ArrayList <Vehiculo> vehiculos;
    
    public GestorDeEmpresa() {
        vehiculos = new ArrayList<>();
        contadorVehiculos = 0;
    }

    public boolean agregarVehiculo(Vehiculo v) {
    	
    	
    	idVehiculo = "#" + String.valueOf(10000 + contadorVehiculos).substring(1);
    	
        v.setIdVehiculo(idVehiculo);
        
        vehiculos.add(v);   
        
        contadorVehiculos ++;
        
        return true;    
    }

    public boolean agregarPaquete(Paquete p, String codigoVehiculo) {
    	String idPaquete;	
    	
    	for(Vehiculo v : vehiculos) {
    		if(v.getIdVehiculo().equals(codigoVehiculo)){
    			if(v.puedeAgregarPaquete(p)){
    				idPaquete = "*" + String.valueOf(10000 + v.getCantidadPaquetes()).substring(1);
    				p.setIdPaquete(idPaquete);
    				if (v.agregarPaquete(p)) {
    					return true;
    				}
    			}
    		}
    	}
        return false;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    
	public boolean eliminarPaquete(String idVehiculo, String idPaquete) {
		for(Vehiculo v : vehiculos) {
			if(v.getIdVehiculo().equals(idVehiculo)) {
				for(Paquete p : v.getPaquetes()) {
					if( p.getIdPaquete().equals(idPaquete)) {
						return v.eliminarPaquete(idPaquete);
					}
				}
			}
		}
		return false;
	}

	public boolean existeVehiculoConId(String id) {
		 for (Vehiculo v : vehiculos) {
		        if (v.getIdVehiculo().equals(id)) {
		            return true;
		        }
		    }
		    return false;
	}
	
}

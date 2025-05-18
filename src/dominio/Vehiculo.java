package dominio;
import java.util.List;
import java.util.ArrayList;

public abstract class Vehiculo {

	protected String idVehiculo;
	
	protected double volumenOcupado;
	protected double pesoCargado;
	
	protected int maxCiudades;
	protected double resistePeso;
	protected double volumenDisponible;
	
	protected int cantidadPaquetes;
	protected List <Paquete> paquetes;
	protected List <Destino> destinos;
	protected List <String> ciudades;
	
	
	
	public Vehiculo(double volumenDisponible, int maxCiudades, double resistePeso) {
		
		this.volumenDisponible = volumenDisponible;
		this.maxCiudades = maxCiudades;
		this.resistePeso = resistePeso;
		this.paquetes = new ArrayList<>();
		this.destinos = new ArrayList<>();
		this.ciudades = new ArrayList<>();
	}
	
	public abstract boolean puedeAgregarPaquete(Paquete p);

	public boolean agregarPaquete(Paquete p) {
		if (puedeAgregarPaquete(p)) {
			paquetes.add(p);
			volumenOcupado += p.calcularVolumen();
			pesoCargado += p.getPeso();
			return true;
		}
		return false;
	}
	
	public boolean eliminarPaquete(String codPaquete) {
		
		for(Paquete p : paquetes) {
			if(p.getIdPaquete().equals(codPaquete)){
				paquetes.remove(p);
				return true;
			}
		}
		return false;
	}
	public int getCantidadPaquetes() {
	    return paquetes.size();
	}

	public double getPesoCargado() {
	    return pesoCargado;
	}

	public double getVolumenOcupado() {
	    return volumenOcupado;
	}
	
	public String getIdVehiculo() {
		return idVehiculo;
	}
	
	public void setIdVehiculo(String idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	
	public List<Paquete> getPaquetes() {
		return paquetes;
	}

}

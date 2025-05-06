package dominio;

public abstract class Vehiculo {

	protected String idVehiculo;
	protected int maxCiudades;
	protected double pesoCargado;
	protected double resistePeso;
	protected double volumenDisponible;
	protected double volumenOcupado;
	protected int cantidadPaquetes;
	protected Paquete[] paquetes;
	protected Destino[] destinos;
	protected String [] ciudades;
	private static final int MAX_PAQ_DEST = 10000;
	
	public Vehiculo(double volumenDisponible, int maxCiudades, double resistePeso) {
		this.volumenDisponible = volumenDisponible;
		this.maxCiudades = maxCiudades;
		this.resistePeso = resistePeso;
		paquetes = new Paquete [MAX_PAQ_DEST];
		cantidadPaquetes = 0;
		pesoCargado = 0;
		volumenOcupado = 0;
		destinos = new Destino [MAX_PAQ_DEST];
		this.ciudades = new String[maxCiudades];
	}
	
	public abstract boolean puedeAgregarPaquete(Paquete p);

	public boolean agregarPaquete(Paquete p) {
		if (puedeAgregarPaquete(p)) {
			paquetes [cantidadPaquetes]=p;
			cantidadPaquetes ++;
			volumenOcupado += p.calcularVolumen();
			pesoCargado += p.getPeso();
			registrarDestino(p.getDestino());
			return true;
		}
		return false;
	}
	
	protected void registrarDestino(Destino destino) { // 
		for(int i = 0; i < destinos.length; i ++) {
			if(destinos[i] != null) {
				if(destinos[i].equals(destino)) {
				return;
				}
			}else{
				if(registrarCiudad(destino.getCiudad(), maxCiudades, ciudades)) {
				destinos[i] = destino;
				return;
				}
			}
		}
	}

	protected boolean registrarCiudad(String ciudad, int maxCiudades, String[] ciudades) {
	    // Verificar si la ciudad ya está registrada
	    for (int i = 0; i < maxCiudades; i++) {
	        if (ciudades[i] != null && ciudades[i].equals(ciudad)) {
	            return true; // Ya existe
	        }
	    }

	    // Buscar un lugar vacío para registrar la ciudad
	    for (int i = 0; i < maxCiudades; i++) {
	        if (ciudades[i] == null) {
	            ciudades[i] = ciudad;
	            return true;
	        }
	    }

	    return false; // No se pudo registrar porque está lleno
	}
	public boolean eliminarPaquete(int indicePaquete) {
		paquetes[indicePaquete] = null;
		return true;
	}
	public int getCantidadPaquetes() {
	    return cantidadPaquetes;
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
	public Paquete[] getPaquetes() {
		return this.paquetes;
	}

}

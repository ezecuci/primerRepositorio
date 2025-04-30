package dominio;

abstract class Vehiculo {

	protected Destino [] destinos;
	protected int cantidadDestinos;
	protected double volumenOcupado;
	protected double pesoOcupado;
	protected Paquete[] paquetes;
	protected int cantidadPaquetes;
	
	public Vehiculo(int maxDestinos, int maxPaquetes) {
		destinos = new Destino [maxDestinos];
		cantidadDestinos = 0;
		volumenOcupado = 0;
		pesoOcupado = 0;
		paquetes = new Paquete [maxPaquetes];
		cantidadPaquetes = 0;
	}
	
	public abstract boolean puedeAgregarPaquete(Paquete p);

	public boolean agregarPaquete(Paquete p) {
		if (puedeAgregarPaquete(p)) {
			paquetes [cantidadPaquetes]=p;
			cantidadPaquetes ++;
			volumenOcupado += p.calcularVolumen();
			pesoOcupado += p.getPeso();
			registrarDestino(p.getDestino());
			return true;
		}
		return false;
	}
	
	protected void registrarDestino(Destino destino) {
		for(int i = 0; i < cantidadDestinos; i ++) {
			if(destinos[i].equals(destino)) {
			return;
			}
		}
		if(cantidadDestinos < destinos.length) {
			destinos[cantidadDestinos] = destino;
			cantidadDestinos ++;
		}
	}
	
}

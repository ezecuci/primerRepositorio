package dominio;

public class Bicicleta extends Vehiculo {

	public Bicicleta() {
		super(0.250, 1, 15);
	}

	@Override
	public boolean puedeAgregarPaquete(Paquete p) {
		if (paquetes[0] == null) {
			if (p.calcularVolumen() > (volumenDisponible / 2) || p.getPeso() > resistePeso) {
				return false;
			} else {
				return true;
			}
		}
		if (paquetes[0].getPeso() + p.getPeso() > resistePeso
				|| paquetes[0].calcularVolumen() + p.calcularVolumen() > volumenDisponible
				|| !paquetes[0].getDestino().getCiudad().equals(p.getDestino().getCiudad()) || paquetes[1] != null) {
			return false;
		}
		return true;
	}

}

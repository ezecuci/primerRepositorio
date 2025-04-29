package dominio;

public enum Menum {
	CARGAR_VEHICULO("Cargar un nuevo vehiculo"),CARGAR_PAQUETE("Cargar nuevo paquete"), VER_VEHICULOS("Ver vehiculos disponibles"), TERMINAR_INTERACCION("Terminar Interacción");
	
	protected String etiqueta;
	
	Menum (String etiqueta){
		this.etiqueta = etiqueta;
	}
	
	public String getEtiqueta() {
		return etiqueta;
	}
	
}

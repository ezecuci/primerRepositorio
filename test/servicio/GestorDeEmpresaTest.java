package servicio;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import dominio.*;

import org.junit.jupiter.api.Test;
import servicio.GestorDeEmpresa;
class GestorDeEmpresaTest {

	@Test
	public void agregarVehiculoCuandoHayEspacio() {
		GestorDeEmpresa gestor = new GestorDeEmpresa();
		Camion vehiculoPrueba = new Camion ();

		assertTrue(gestor.agregarVehiculo(vehiculoPrueba), "No se pudo agregar el vehiculo");
	}
	
	@Test
	public void agregarVehiculoCuandoNoHayEspacio() {
		GestorDeEmpresa gestor = new GestorDeEmpresa();
		Camion vehiculoPrueba = new Camion ();
		
		for (int i = 0; i < 100 ; i ++) {	
			gestor.agregarVehiculo(vehiculoPrueba);
		}
		
		assertFalse(gestor.agregarVehiculo(vehiculoPrueba), "Se pudo agregar vehiculo");
	}
	
	@Test
	public void agregarPaqueteCuandoHayEspacio (){
		GestorDeEmpresa gestor = new GestorDeEmpresa();
		Auto vehiculoPrueba = new Auto ();
		gestor.agregarVehiculo(vehiculoPrueba);
		Paquete paquetePrueba = new Paquete(new Destino("El Palomar", "Bergamini", 123), 0.5, 0.5, 0.5, 0.5);
		boolean resultado;
		resultado = gestor.agregarPaquete(paquetePrueba, vehiculoPrueba.getIdVehiculo());
		
		assertTrue(resultado, "No se pudo agregar el paquete, se esperaba true y devolvio false");
	}
	@Test
	public void agrgarPaqueteCuandoNoHayEspacio() {
		GestorDeEmpresa gestor = new GestorDeEmpresa();
		Auto vehiculoPrueba = new Auto ();
		gestor.agregarVehiculo(vehiculoPrueba);
		Paquete paquetePrueba = new Paquete(new Destino("El Palomar", "Bergamini", 123), 0.2, 0.2, 0.2, 0.2);
		
		for(int i = 0; i < vehiculoPrueba.getPaquetes().length ; i++) {
			if ( false == gestor.agregarPaquete(paquetePrueba, vehiculoPrueba.getIdVehiculo())){
				break;
			}
		}
		
		boolean resultado = gestor.agregarPaquete(paquetePrueba, vehiculoPrueba.getIdVehiculo());
		assertFalse(resultado , "Se pudo agregar cuando no habia espacio, se esperaba false y devolvio true");
	}
	
	
	
}

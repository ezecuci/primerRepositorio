package servicio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dominio.*;

import servicio.GestorDeEmpresa;

class GestorDeEmpresaTest {

	@Test
	public void seCreanIdsCorrectosCuandoSeAgregaUnNuevoVehiculo() {
		GestorDeEmpresa gestor = new GestorDeEmpresa();
		Vehiculo v1 = new Auto();
		Vehiculo v2 = new Bicicleta();
		Vehiculo v3 = new Camion();
		gestor.agregarVehiculo(v1);
		gestor.agregarVehiculo(v2);
		gestor.agregarVehiculo(v3);
		
		String idVehiculoDePrueba = v3.getIdVehiculo();
		boolean respuesta = idVehiculoDePrueba.equals("#0002");
	
		assertTrue ("El id del vehiculo deberia a ser #0002", respuesta );
	}	
	
	@Test
	public void agregarUnPaqueteAUnVehiculoDevuelveTrue() {
		GestorDeEmpresa gestor = new GestorDeEmpresa();
		Vehiculo v1 = new Auto();
		gestor.agregarVehiculo(v1);
		
		Paquete p1 = new Paquete(new Destino("Ciudadela", "Rivadavia", 3005 ), 1.2 , 1.2 , 1.2 , 1.2);
		
		boolean respuesta = gestor.agregarPaquete(p1, v1.getIdVehiculo());
		assertTrue ("No se pudo agregar el vehiculo", respuesta);	
	}
	
	@Test
	public void devuelveFalseCuandoNoSePuedenAgregarPaqueteCuandoNoHayLugarEnUnCamion() {
		GestorDeEmpresa gestor = new GestorDeEmpresa();
		Vehiculo v1 = new Camion();
		gestor.agregarVehiculo(v1);
		
		Paquete paqueteGrande = new Paquete(new Destino("Ciudadela", "Rivadavia", 3005 ), 2 , 5 , 1.9 , 15980);
		Paquete paqueteChico = new Paquete(new Destino("Ciudadela", "Rivadavia", 3005 ), 0.25 , 2 , 2 , 19);
		
		boolean primerPaquete = gestor.agregarPaquete(paqueteGrande, v1.getIdVehiculo()); //carggo paquete grande apenas mas chico del tamanio limite
		
		System.out.println("El paquete grande entro: "+ primerPaquete);
		
		boolean respuesta = gestor.agregarPaquete(paqueteChico, v1.getIdVehiculo()); //cargo paquete chico pero que no entra 
		
		System.out.println("el paquete chico entro tambien: " + respuesta );
		assertFalse (respuesta, "Se pudo agregar el paquete que no deberia haber entrado en el camion");	
	}
	
	@Test
	public void devuelveFalseSiSeIntentaCargarTresPaquetesEnUnaBicicleta() {
		GestorDeEmpresa gestor = new GestorDeEmpresa();
		Vehiculo v = new Bicicleta();
		gestor.agregarVehiculo(v);
		
		Paquete p1 = new Paquete(new Destino("Ciudadela", "Rivadavia", 3005 ), 0.05 , 2 , 1 , 2);
		Paquete p2 = new Paquete(new Destino("Ciudadela", "Rivadavia", 3005 ), 0.05 , 2 , 1 , 2);
		Paquete p3 = new Paquete(new Destino("Ciudadela", "Rivadavia", 3005 ), 0.05 , 2 , 1 , 2);
		
		gestor.agregarPaquete(p1 , v.getIdVehiculo());
		gestor.agregarPaquete(p2 , v.getIdVehiculo());
		boolean respuesta = gestor.agregarPaquete(p3 , v.getIdVehiculo());
		System.out.println(respuesta);
		assertFalse(respuesta, "Se pudo agregar el tercer paquete cuando no se podria haber agregado");
	}
	
	@Test
	public void siSeIntentaCargarUnPaqueteAUnvehiculoQueNofueAgreadoDevuelveFalse() {
		GestorDeEmpresa gestor = new GestorDeEmpresa();
		Vehiculo v = new Camion();
		v.setIdVehiculo("#0004");
		
		Paquete p = new Paquete(new Destino("Ciudadela", "Rivadavia", 3005 ), 0.05 , 2 , 1 , 4);
		boolean respuesta =gestor.agregarPaquete(p, v.getIdVehiculo());
		
		assertFalse(respuesta, "Devolvio true porque se cargo el paquete en un vehiculo que no esta agregado a la empresa");
	}
	
	@Test
	public void queDevuelvaFalseCuandoSeIntentaCargarPaquetesConMasDeTresciudadesDeDestino() {
		GestorDeEmpresa gestor = new GestorDeEmpresa();
		Vehiculo v = new Auto();
		gestor.agregarVehiculo(v);
		
		Paquete p1 = new Paquete(new Destino("Ciudadela", "Rivadavia", 3005 ), 0.05 , 2 , 1 , 2);
		Paquete p2 = new Paquete(new Destino("Palermo", "Rivadavia", 3005 ), 0.05 , 2 , 1 , 2);
		Paquete p3 = new Paquete(new Destino("Palermo", "Rivadavia", 3005 ), 0.05 , 2 , 1 , 2);
		Paquete p4 = new Paquete(new Destino("Chacarita", "Rivadavia", 3005 ), 0.05 , 2 , 1 , 2);
		Paquete p5 = new Paquete(new Destino("Chacarita", "Rivadavia", 3005 ), 0.05 , 2 , 1 , 2);
		
		Paquete p6 = new Paquete(new Destino("San Miguel", "Rivadavia", 3005 ), 0.05 , 2 , 1 , 2);
		
		gestor.agregarPaquete(p1 , v.getIdVehiculo());
		gestor.agregarPaquete(p2 , v.getIdVehiculo());
		gestor.agregarPaquete(p3 , v.getIdVehiculo());
		gestor.agregarPaquete(p4 , v.getIdVehiculo());
		gestor.agregarPaquete(p5 , v.getIdVehiculo());
		
		boolean respuesta = gestor.agregarPaquete(p6 , v.getIdVehiculo());
		
		System.out.println("Se pudo cargar el paquete con 4ta ciudad de destino: " + respuesta);
		assertFalse(respuesta , "Devuelve true porque se pudo cargar el paquete con 4ta ciudad de destino, debio ser false la respuesta");
	}
	
	@Test
	public void devuelveFalseSiSeIntentaCargarPaquetesConDosCiudadesDistintas() {
		GestorDeEmpresa gestor = new GestorDeEmpresa();
		Vehiculo v = new Bicicleta();
		gestor.agregarVehiculo(v);
		
		Paquete p1 = new Paquete(new Destino("Ciudadela", "Rivadavia", 3005 ), 0.05 , 2 , 1 , 2);
		Paquete p2 = new Paquete(new Destino("Merlo", "Rivadavia", 3005 ), 0.05 , 2 , 1 , 2);
		Paquete p3 = new Paquete(new Destino("Ciudadela", "Rivadavia", 3005 ), 0.05 , 2 , 1 , 2);
		
		gestor.agregarPaquete(p1 , v.getIdVehiculo());
		gestor.agregarPaquete(p2 , v.getIdVehiculo());
		boolean comprobacionExtra = gestor.agregarPaquete(p3 , v.getIdVehiculo());
		System.out.println("El tercer paquete se agrego: "+ comprobacionExtra);
		 
		boolean respuesta = gestor.agregarPaquete(p2, v.getIdVehiculo());
		System.out.println("Se pudo cargar un paquete a Ciudadela y otro a Merlo: "+respuesta);
		assertFalse(respuesta, "Se pudo cargar un paquete con un destino ciudad diferente al anterior en bicicleta pero debio dar false");
		assertTrue("No se pudo cargar el tercer paquete que si iba al mismo destino que el primero" , comprobacionExtra);
	}
	
	
	
	
	
	
	
}
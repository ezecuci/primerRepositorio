package interfaz;

import servicio.GestorDeEmpresa;
import dominio.*;

import java.util.ArrayList;
import java.util.Scanner;

public class InterfazDeUsuario {

	private GestorDeEmpresa gestor;
	private Scanner scanner;

	public InterfazDeUsuario() {
		gestor = new GestorDeEmpresa();
		scanner = new Scanner(System.in);
	}

	public void iniciar() {
		int opcion;
		do {
			mostrarMenu();
			opcion = scanner.nextInt();
			scanner.nextLine(); // limpiar salto de línea

			switch (opcion) {
			case 0:
				cargarVehiculo();
				break;
			case 1:
				cargarPaquete();
				break;
			case 2:
				mostrarVehiculos();
				break;
			case 3:
				eliminarPaquete();
				break;
			case 4:
				mostrarMensaje("Interaccion Finalizada");
				break;
			default:
				System.out.println("Opción no válida, por favor intente nuevamente.");
			}
		} while (opcion != 4);
	}

	private void eliminarPaquete() {
		
		System.out.println("Ingrese el codigo del vehiculo en el cual se encuentra el paquete que desea eliminar (solo el numero, sin #)");
		String idVehiculo = elegirVehiculo();
		
		for(Vehiculo v : gestor.getVehiculos()) {
			if(v.getIdVehiculo().equals(idVehiculo)) {
				if(v.getCantidadPaquetes() == 0) {
					System.out.println("Aun no se cargaron paquetes en este vehiculo");
					return;
				}
			}
		}
		
		System.out.println("Paquetes disponibles:");
		int contadorPaquetes = 1;
		for(Vehiculo v : gestor.getVehiculos()) {
			if(idVehiculo.equals(v.getIdVehiculo())) {
				for(Paquete p : v.getPaquetes()) {
					System.out.println("- Paquete N° " + contadorPaquetes + " - " + p.getIdPaquete());
					contadorPaquetes ++;
				}
				break;
			}
		}
		
		System.out.println("Ingrese el codigo del paquete que desea eliminar (solo el codigo numerico, sin el *)");
		
		String codigoIngresado;
		
		boolean encontrado = false;
		
		do {
			codigoIngresado = "*" + scanner.nextLine();
			
			for( Vehiculo v : gestor.getVehiculos()) {
				if (idVehiculo.equals(v.getIdVehiculo())) {
					for(Paquete p : v.getPaquetes()) {
						if(codigoIngresado.equals(p.getIdPaquete())) {
							encontrado = true;
							break;
						}
					}
				break;
				}
				
			}
			
			if (!encontrado) {
				System.out.println("El codigo de paquete no es correcto, intentelo de nuevo");
			}
				
		}while( !encontrado);
		
		if(gestor.eliminarPaquete(idVehiculo, codigoIngresado)) {
			System.out.println("El paquete fue eliminado");
		}else {
			System.out.println("No pudo eliminarse el paquete");
		}
		
	}

	private void mostrarVehiculos() {
		
		ArrayList <Vehiculo> vehiculos = gestor.getVehiculos();
		int contador = 1;
		
		for(Vehiculo v : vehiculos) {
			
			System.out.println("Vehiculo N° " + contador + " - " + v.getClass().getSimpleName() + " " + v.getIdVehiculo() );
			contador ++;
		}
	}		

	private void cargarVehiculo() {
		mostrarMensaje("Seleccione el tipo de vehículo:\n1. Bicicleta\n2. Auto\n3. Camión");
		int opcion = scanner.nextInt();

		Vehiculo vehiculo = null;

		switch (opcion) {
		case 1:
			vehiculo = new Bicicleta();
			break;
		case 2:
			vehiculo = new Auto();
			break;
		case 3:
			vehiculo = new Camion();
			break;
		default:
			mostrarMensaje("Opción inválida. No se agregó ningún vehículo.");
			return;
		}

		boolean exito = gestor.agregarVehiculo(vehiculo);
		if (exito) {
			mostrarMensaje("Vehículo agregado con éxito.");
		} else {
			mostrarMensaje("No se pudo agregar el vehículo. Límite alcanzado.");
		}
	}

	private void cargarPaquete() {
		mostrarMensaje("\nIndicar ciudad de destino.");
		String ciudad = scanner.nextLine();
		mostrarMensaje("Indicar calle del destino.");
		String calle = scanner.nextLine();
		mostrarMensaje("Indicar la numeración del destino.\n");
		int numero = scanner.nextInt();

		mostrarMensaje(
				"indicar el peso del paquete en kilogramos (si no llega a un metro usar coma para escribir decimales).");
		double peso = scanner.nextDouble();
		mostrarMensaje(
				"indicar el largo del paquete en metros (si no llega a un metro usar coma para escribir decimales).");
		double largo = scanner.nextDouble();
		mostrarMensaje(
				"indicar el ancho del paquete en metros (si no llega a un metro usar coma para escribir decimales).");
		double ancho = scanner.nextDouble();
		mostrarMensaje(
				"indicar el alto del paquete en metros (si no llega a un metro usar coma para escribir decimales).\n");
		double alto = scanner.nextDouble();
		scanner.nextLine();

		Destino destino = new Destino(ciudad, calle, numero);
		Paquete paquete = new Paquete(destino, peso, largo, ancho, alto);

		if (gestor.getVehiculos().size() != 0) {
			
			mostrarMensaje("Indique el codigo del vehiculo en el que desea cargar el paquete (solo el numero sin #)\n");
			String idVehiculo = elegirVehiculo();

			
			if (gestor.agregarPaquete(paquete, idVehiculo)) {
				mostrarMensaje("El paquete fue cargado con exito\n");
			} else {
				mostrarMensaje("No pudo cargarse el paquete\n");
			}
		} else {
			mostrarMensaje("No hay vehiculos cargados.\n");
		}

	}

	private String elegirVehiculo() {
		
		mostrarVehiculos();
		
		String codigoIngresado;
	
		boolean encontrado = false;
		
		do {
			codigoIngresado = "#" + scanner.nextLine();
			
	        encontrado = gestor.existeVehiculoConId(codigoIngresado);
			
			if (!encontrado) {
				System.out.println("El codigo ingresado no corresponde a ningun vehiculo, intentelo de nuevo");
			}
				
		}while( !encontrado);
		
		return codigoIngresado;
	}
	

	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}

	private void mostrarMenu() {
		System.out.println("Seleccione una opción:");
		for (int i = 0; i < Menum.values().length; i++) {
			System.out.println((i) + ". " + Menum.values()[i].getEtiqueta() + ".");
		}
	}
}

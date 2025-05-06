package interfaz;

import servicio.GestorDeEmpresa;
import dominio.*;

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
		if(gestor.getCantidadVehiculos()==0) {
			mostrarMensaje("No hay vehiculos cargados, por lo tanto, no existen paquetes");
		}else {
		
			for (int i = 0; i < gestor.getCantidadVehiculos(); i++) {
				mostrarMensaje("- Vehiculo " + gestor.getVehiculos()[i].getIdVehiculo() + " "
					+ gestor.getVehiculos()[i].getClass().getSimpleName());
			}
		mostrarMensaje("Ingrese el codigo del vehiculo que contiene el paquete que desea eliminar");
		String opcion = "#" + scanner.nextLine();
		boolean llave = false;
		int indice = 0;
		do {
				
			for(int i = 0; i < gestor.getCantidadVehiculos(); i ++) {
				if (opcion.equals(gestor.getVehiculos()[i].getIdVehiculo())) {
					llave = true;
					indice = i;
				}
			}
			if (llave == false) {
				mostrarMensaje("Codigo incorrecto, ingrese otro o ingrese 0 para volver al menu principal");
				opcion = "#" + scanner.nextLine();
			}
			if (opcion.equals("#0")){
				llave = true;
			}
				
		}while ( llave == false);
		if (opcion.equals("#0")) {
			return;
		}else {
			
			mostrarMensaje("Igrese el codigo del paquete que desea eliminar");
			for(int i = 0; i < gestor.getVehiculos()[indice].getPaquetes().length ; i ++) {
				if(gestor.getVehiculos()[indice].getPaquetes()[i] != null) {
					mostrarMensaje("Paquete " + ( i + 1 ) + " " + gestor.getVehiculos()[indice].getPaquetes()[i].getIdPaquete());
				}
			}
			String opcionDos;
			boolean llaveDos = false;
			String  idVehiculoActual = "";
			boolean seEliminoElPaquete = false;
			do {
				opcionDos = "#" + scanner.nextLine();
				for(int j = 0 ; j < gestor.getCantidadVehiculos() ; j ++) {
					for(int i = 0; i < gestor.getVehiculos()[j].getPaquetes().length ; i ++) {
						if(gestor.getVehiculos()[j].getPaquetes()[i] != null) {
							if (opcionDos.equals(gestor.getVehiculos()[j].getPaquetes()[i].getIdPaquete())) {
								llaveDos= true;
								idVehiculoActual = gestor.getVehiculos()[j].getIdVehiculo();
								seEliminoElPaquete = gestor.eliminarPaquete(idVehiculoActual, opcionDos);
							}
							if(seEliminoElPaquete == true) {
								mostrarMensaje ("Paquete eliminado");
								return;
							}
						}
					}
				}
				if (llaveDos == false) {
					mostrarMensaje("Codigo incorrecto, ingrese otro o ingrese 0 para volver al menu principal");
					opcionDos = "#" + scanner.nextLine();
				}
				if (opcionDos.equals("#0")){
					return;
				}
					
			}while ( llaveDos == false);
			
			
			}
		}
	}

	private void mostrarVehiculos() {
		if (gestor.getCantidadVehiculos() == 0) {
			mostrarMensaje("No hay vehiculos cargados.\n");
		}else {
		
			for (int i = 0; i < gestor.getCantidadVehiculos(); i++) {
				mostrarMensaje("- Vehiculo " + gestor.getVehiculos()[i].getIdVehiculo() + " "
						+ gestor.getVehiculos()[i].getClass().getSimpleName());
			}
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
				"indicar el alto del paquete en metros (si no llega a un metro usar coma para escribir decimales).");
		double alto = scanner.nextDouble();
		scanner.nextLine();

		Destino destino = new Destino(ciudad, calle, numero);
		Paquete paquete = new Paquete(destino, peso, largo, ancho, alto);

		if (gestor.getCantidadVehiculos() != 0) {
			
			String opcion = elegirVehiculos();

			
			if (gestor.agregarPaquete(paquete, opcion)) {
				mostrarMensaje("El paquete fue cargado con exito\n");
			} else {
				mostrarMensaje("No pudo cargarse el paquete0");
			}
		} else {
			mostrarMensaje("No hay vehiculos cargados.\n");
		}

	}

	private String elegirVehiculos() {
		String opcion;
		if (gestor.getCantidadVehiculos() == 0) {
			mostrarMensaje("No hay vehiculos cargados.\n");
			return opcion = "";
		}
		mostrarMensaje("Indique en que vehiculo desea cargar el paquete (solo el numero sin #)");
		
		for (int i = 0; i < gestor.getCantidadVehiculos(); i++) {
			mostrarMensaje("- Vehiculo " + gestor.getVehiculos()[i].getIdVehiculo() + " "
					+ gestor.getVehiculos()[i].getClass().getSimpleName());
		}
		
		opcion = "#" + scanner.nextLine();
		boolean llave = false;
		do {
			for(int i = 0; i < gestor.getCantidadVehiculos(); i ++) {
				if (opcion.equals(gestor.getVehiculos()[i].getIdVehiculo())) {
					llave = true;
					return opcion;
				}
			}
			if (llave == false) {
				mostrarMensaje("Codigo incorrecto, ingrese otro o ingrese 0 para volver al menu principal");
				opcion = "#" + scanner.nextLine();
			}
			if (opcion.equals("#0")){
				llave = true;
			}
			
		}while ( llave == false);
		return opcion = "";
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

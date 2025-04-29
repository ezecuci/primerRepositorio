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
                	mostrarMensaje("Interaccion Finalizada");
                	break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        } while (opcion != 3);
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
        
        mostrarMensaje("indicar el peso del paquete en kilogramos (si no llega a un metro usar coma para escribir decimales).");
        double peso = scanner.nextDouble();
        mostrarMensaje("indicar el largo del paquete en metros (si no llega a un metro usar coma para escribir decimales).");
        double largo  = scanner.nextDouble();
        mostrarMensaje("indicar el ancho del paquete en metros (si no llega a un metro usar coma para escribir decimales).");
        double ancho  = scanner.nextDouble();
        mostrarMensaje("indicar el alto del paquete en metros (si no llega a un metro usar coma para escribir decimales).");
        double alto  = scanner.nextDouble();
        
        
        Destino destino = new Destino(ciudad, calle, numero);
        Paquete paquete = new Paquete(destino, peso, largo, ancho, alto);
        
        
        if(gestor.getCantidadVehiculos()!=0) {
        	mostrarMensaje("\nIndique en que vehiculo desea cargar el paquete");
        	for(int i = 0 ; i < gestor.getCantidadVehiculos(); i++) {
        		mostrarMensaje("Vehiculo " + (i+1) + " " + gestor.getVehiculos()[i].getClass().getSimpleName() );
        	}
        	
        	int opcion = scanner.nextInt();
        	if(gestor.agregarPaquete(paquete)) {
        		mostrarMensaje("El paquete fue cargado con exito\n");
        	}else {
        		mostrarMensaje("No pudo cargarse el paquete, intente con otro vehiculo\n");
        	}
        }else {
        	mostrarMensaje("No hay vehiculos cargados.\n");
        }
        
    }

    private void mostrarVehiculos() {
    	if(gestor.getCantidadVehiculos()== 0) {
    		mostrarMensaje("No hay vehiculos cargados.\n");
    		return;
    	}
        for(int i = 0; i < gestor.getCantidadVehiculos() ; i ++) {
        	mostrarMensaje("- Vehiculo " + (i + 1) + " " + gestor.getVehiculos()[i].getClass().getSimpleName());
        }
        mostrarMensaje("\n");
    }
    public void mostrarMensaje(String mensaje) {
    	System.out.println(mensaje);
    }
    private void mostrarMenu() {
        System.out.println("Seleccione una opción:");
        for (int i = 0; i < Menum.values().length; i ++) {
            System.out.println( (i) + ". " + Menum.values()[i].getEtiqueta() + "." );
        }
    }
}

package UIMain;

import java.io.*;
import java.util.Scanner;

import gestorAplicacion.comida.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.humanos.*;

//Esta clase la he estado modificando yo (Richard), cualquier sugerencia me pueden escribir aqui
public class Main {
    // estoy implementado serializacion, si algo se borra
    public static void main(String[] args) {

        // Colocar aqui el metodo para iniciar sesion
        do{
            UI.titulo();
            Scanner input = new Scanner(System.in);
            String strOpciones = "Escriba el numero correspondiente a la opcion que quiere elegir\n" +
                    "0. Cerrar sesion\n" +
                    "1. Agregar productos a la canasta de compras\n" +
                    "2. Ver las facturas de mis compras pasadas\n";

            System.out.println(strOpciones);
            String eleccion = input.nextLine();
            boolean eleccionValida = true;

            do {
                switch (eleccion) {
                    // codigo que hace que se muestren opciones disponibles
                    case "0":
                        System.out.println("Esta seguro de que quiere cerrar su sesion?");
                        System.out.println("Escriba 1 para si, escriba 0 para cancelar");
                        eleccion = input.nextLine();
                        switch (eleccion) {
                            case "0":
                                break;
                            case "1":
                                Cliente.setSesion(null);
                                System.out.println("Su sesion ha sido finalizada");
                                break;
                            default:
                                System.out.println("Usted escogio una opción que no estaba en la lista");
                                break;

                        }

                        // codigo que crea canasta y permite agregar productos
                    case "1":
                        UI.mostrarOpciones();
                        GestionCompra.gestionRecibirOrdenCanasta(Cliente.getSesion().getCanastaOrden());
                        break;

                    // codigo que le pide al cliente su identificación y le muestra sus opciones
                    case "2":

                        break;

                    // caso donde el cliente elije una opcion no valida
                    default:
                        System.out.println("Usted escogio una opción que no estaba en la lista");
                        eleccionValida = false;
                        break;
                }
            } while (eleccionValida == false && Cliente.getSesion() != null);
        } while(true);
    }
}

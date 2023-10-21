package UIMain;
import java.io.*;
import java.util.Scanner;

import gestorAplicacion.comida.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.humanos.*;

//Esta clase la he estado modificando yo (Richard), cualquier sugerencia me pueden escribir aqui
public class Main {
    //estoy implementado serializacion, si algo se borra
    public static void main(String[] args) {

    	
        //Colocar aqui el metodo para iniciar sesion

        Scanner input = new Scanner(System.in);
        UI.titulo();
        String strOpciones = "Escriba el numero correspondiente a la opcion que quiere elegir\n" +
                "0. Cerrar sesion\n"+
                "1. Agregar productos a la canasta de compras\n" +
                "2. Ver las facturas de mis compras pasadas\n";
                
        System.out.println(strOpciones);
        int IntEleccion = input.nextInt();
        boolean eleccionValida = true;

        do {
            switch (IntEleccion) {
                // codigo que hace que se muestren opciones disponibles
                case 0:
                    Cliente.setSesion(null);
                    break;

                // codigo que crea canasta y permite agregar productos
                case 1:
                    UI.mostrarOpciones();
                    GestionCompra.gestionRecibirOrdenCanasta(Cliente.getSesion().getCanastaOrden());
                    break;

                // codigo que le pide al cliente su identificación y le muestra sus opciones
                case 2:
                    System.out.println("Escriba su identificación: ");
                    int identificacion = input.nextInt();
                    break;

                // caso donde el cliente elije una opcion no valida
                default:
                    System.out.println("Usted escogio una opción que no estaba en la lista, ");
                    eleccionValida = false;
                    break;
            }
        } while (eleccionValida == false && Cliente.getSesion() != null);
    }

}

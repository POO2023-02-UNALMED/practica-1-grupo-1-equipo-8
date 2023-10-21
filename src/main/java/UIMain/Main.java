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
        
        UI.titulo();
        Scanner input = new Scanner(System.in);
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
                    System.out.println("Esta seguro de que quiere cerrar su sesion?");
                    System.out.println("Escriba 1 para si, escriba cualquier otra tecla para cancelar");
                    IntEleccion = input.nextInt();
                    if(IntEleccion == 1){
                        Cliente.setSesion(null);
                    } else if(IntEleccion == 0){
                        break;
                    } else{
                        System.out.println("Usted escogio una opcion no valida, vuelva a intentar");
                    }
                    break;

                // codigo que crea canasta y permite agregar productos
                case 1:
                    UI.mostrarOpciones();
                    GestionCompra.gestionRecibirOrdenCanasta(Cliente.getSesion().getCanastaOrden());
                    break;

                // codigo que le pide al cliente su identificación y le muestra sus opciones
                case 2:

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

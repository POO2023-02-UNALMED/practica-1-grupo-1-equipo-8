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
        boolean continuar = true;

        // Colocar aqui el metodo para iniciar sesion
        do{
            UI.titulo();
            Scanner input = new Scanner(System.in);
            String strOpciones = "Escriba el numero correspondiente a la opcion que quiere elegir\n" +
                    "0. Cerrar sesion\n" +
                    "1. Agregar productos a la canasta de compras\n" +
                    "2. Ver catalogo de productos e informacion adicional"+
                    "3. Ver las facturas de mis compras pasadas\n";

            System.out.println(strOpciones);
            String eleccion = input.nextLine();
            boolean eleccionValida = true;

            do {
                switch (eleccion) {

                    case "0": //cerrar sesion
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

                    case "1": //gestion de canasta, opciones, facturacion, domicilio
                        UI.mostrarOpciones();
                        GestionCompra.gestionRecibirOrdenCanasta(Cliente.getSesion().getCanastaOrden());
                        System.out.println("Desea continuar con la facturación? escriba s para si, escriba n para no, escriba 0 para volver al menu.");
                        eleccion = input.nextLine();
                        switch (eleccion){
                            case "s":
                                Panaderia.generarRecibo();
                                System.out.println("Desea que le enviemos su pedido a domicilio? escriba s para si, n para no, escriba 0 para volver al menu");
                                eleccion = input.nextLine();
                                switch (eleccion){
                                    case "s":
                                        break;
                                    case "n":
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        System.out.println("Escriba una opcion valida");
                                        break;
                                }
                                break;
                            case "n":
                                break;
                            case "0":
                                System.out.println("Volviendo al menu");
                            default:
                                System.out.println("Elija una opcion valida");
                                break;
                        }

                        break;

                    // codigo que le pide al cliente su identificación y le muestra sus opciones
                    case "2":
                        UI.mostrarOpciones();
                        do{
                            System.out.println("Seleccione un producto (Escriba el numero de id), o escriba 0 para salir ");
                            eleccion = input.nextLine();
                            if (eleccion == "0"){
                                break;
                            } else{
                            System.out.println(Producto.obtenerObjetoPorId(eleccion).getNutrientes());
                            }
                            break;
                        } while(true);

                    // caso donde el cliente elije una opcion no valida
                    default:
                        System.out.println("Usted escogio una opción que no estaba en la lista");
                        eleccionValida = false;
                        break;
                }
            } while (eleccionValida == true && Cliente.getSesion() != null);
        } while(true);
    }
}

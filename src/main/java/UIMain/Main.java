package UIMain;

import java.io.*;
import java.util.Scanner;

import gestorAplicacion.comida.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.humanos.*;

//Esta clase la he estado modificando yo (Richard), cualquier sugerencia me pueden escribir aqui
public class Main { //preferiblemente colocar solo los metodos para que el codigo no quede todo disperso
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        GestionInicioCliente.sesionCliente(); //este metodo aun no esta listo
        do{
            UI.titulo();
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
                        UI.cerrarSesion();
                      break;
                    case "1":
                        UI.compras();
                        break;
                    case "2":
                        UI.verCatalogoDescripcion();
                        break;
                    case "3":
                        UI.historialRecibos(Cliente.getSesion());
                    // caso donde el cliente elije una opcion no valida
                    default:
                        System.out.println("Usted escogio una opción que no estaba en la lista");
                        eleccionValida = false;
                        break;
                }
            } while (eleccionValida == true && Cliente.getSesion() != null);
          }while(true);

        
    }
}

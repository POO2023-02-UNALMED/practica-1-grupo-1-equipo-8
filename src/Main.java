import java.util.Scanner;
import comida.*;
import gestion.*;
import humanos.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("");
        String strOpciones = "Escriba el numero correspondiente a la opcion que quiere elejir" +
                "1. Ver productos disponibles para comprar" +
                "2. Agregar productos a la canasta de compras" +
                "3. Ver las facturas de mis compras";
        System.out.println(strOpciones);
        int eleccion = input.nextInt();
        boolean eleccionValida = true;

        do {
            switch (eleccion) {
                case 1:
                    // necesitamos un metodo de la clase comida que muestre las opciones disponibles
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Usted escogio una opción que no estaba en la lista, ");
                    eleccionValida = false;
                    break;
            }
        } while (eleccionValida == false);
    }
}

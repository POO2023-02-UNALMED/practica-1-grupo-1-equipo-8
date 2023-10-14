package UIMain;

import java.util.ArrayList;
import java.util.Scanner;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.humanos.Domiciliario;

public  class GestionDomicilioCliente {
    public boolean enviarCanastasAFacturar(Canasta canastaTrabajar){
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Desea enviar la canasta a facturar? (S/N)");
        String respuesta = sc.nextLine();
        if (respuesta.equals("N")){
            return false;
        }
        return true;
    }

    public static double pedirCalificacion(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la calificacion del domiciliario (1-5): ");
        double calificacion = sc.nextDouble();
        return calificacion;
    }

    public static void estadoDomicilio(boolean estado){
        if (estado){
            System.out.println("El domicilio se ha realizado con exito");
    }
        else{
            System.out.println("El domicilio no se ha podido realizar \n Ya te enviaremos tu domiclio");
        }
    }

    public static void mostrarRanking(){
        ArrayList<Domiciliario> domiciliarios = Panaderia.getDomiciliarios();
        for (Domiciliario domiciliario : domiciliarios){
            System.out.println(domiciliario.getNombre() + " " + domiciliario.getCalificacion());
        }
    }
}

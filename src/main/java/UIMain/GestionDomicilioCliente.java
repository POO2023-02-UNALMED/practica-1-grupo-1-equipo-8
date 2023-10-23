package UIMain;

import java.util.ArrayList;
import java.util.Scanner;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.humanos.Domiciliario;
import gestorAplicacion.humanos.Cliente;

public class GestionDomicilioCliente {
    public static int zonas = 0;
    
    public static void menuDomicilioCliente(Cliente cliente){
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido a la seccion de domicilios");
        System.out.println("1. Realizar domicilio");
        System.out.println("2. Calificar domiciliario");
        System.out.println("3. Salir");
        int opcion = sc.nextInt();
        switch(opcion){
            case 1:
                cliente.enviarCanastasADomicilio(cliente.getCanastaEnMano());
                
                break;
            case 2:
                pedirCalificacion();
                break;
            case 3:
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }
    
    public static void barrasDomicilio(){
        ArrayList<String> procesoEntrega = new ArrayList<>();
        procesoEntrega.add("Recibiendo pedido");
        procesoEntrega.add("En camino");
        procesoEntrega.add("Entrega completada");

        int etapas = procesoEntrega.size();
        int progreso = 0;
        
        while (progreso < etapas) {
            // Simula el progreso de cada etapa.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Calcula el porcentaje de progreso.
            double porcentaje = ((progreso + 1.0) / etapas) * 100;

            // Muestra el progreso actual.
            String etapaActual = procesoEntrega.get(progreso);
            System.out.println(Texto.centrar(porcentaje + "%  [#" + "#".repeat(progreso * 10) + "] 100%"));
            System.out.println(Texto.centrar("Proceso de " + etapaActual + " completado"));

            progreso++;
        }

        // Comprueba si la entrega se realizó con éxito.
        if (progreso == etapas) {
            System.out.println("¡Entrega a domicilio realizada con éxito!");
        } else {
            System.out.println("La entrega a domicilio no se pudo completar.");
        }
    }

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
}

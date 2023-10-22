package UIMain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.humanos.Cocinero;

public class GestionCocinar {

    public static int zonas = 0;

    /**
     * Este método gestiona el proceso de cocción solicitando al usuario que ingrese un número entre 0 y 5.
     * Continuará solicitando al usuario hasta que se ingrese un número válido y luego devolverá ese número.
     *
     * @return El valor entero ingresado por el usuario.
     */
    public static int gestionCocina() {
        Scanner sc = new Scanner(System.in); 
        int num;
        do {
            System.out.print("Ingrese un número entre 0 y 5: ");
            while (!sc.hasNextInt()) {
                System.out.print("Ingrese un número entre 0 y 5: ");
                sc.next();
            }
            num = sc.nextInt();
        } while (num < 0 || num > 5);
        return num;
    }

    /**
     * Este método imprime el progreso de un proceso de cocción en función del número de elementos en el proceso.
     * Si solo hay un elemento, imprime un progreso del 100% y la finalización del proceso.
     * Si hay dos elementos, imprime un progreso del 50% para el primer elemento y un progreso del 100% para el segundo elemento, y luego imprime la finalización del proceso.
     * Si hay tres elementos, imprime un progreso del 33% para el primer elemento, un progreso del 66% para el segundo elemento y un progreso del 100% para el tercer elemento, y luego imprime la finalización del proceso.
     * @param procesoCook Una lista de cadenas que representa el proceso de cocción.
     * @param longitud Un entero que representa el número de elementos en el proceso de cocción.
     */
    public static void barrasCocinando(List<String> procesoCook,int longitud){
        if (longitud==1){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("100%  [###############################] 100%");
            String elemento = procesoCook.get(0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Proceso de " + elemento + " completado");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Producto Cocinado");
            zonas=0;
        }
        if (longitud==2){
            if(zonas==0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("50%  [#############...................] 100%");
                String elemento = procesoCook.get(0);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Proceso de " + elemento + " completado");
                zonas++;
            }
            else if(zonas==1){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("100%  [###############################] 100%");
                String elemento2 = procesoCook.get(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Proceso de " + elemento2 + " completado");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producto Cocinado");
                zonas=0;
            }
        }
        if (longitud==3){
            if(zonas==0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("33%  [#########.......................] 100%");
                String elemento = procesoCook.get(0);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Proceso de " + elemento + " completado");
                zonas++;
            }
            else if(zonas==1){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("66%  [###################.............] 100%");
                String elemento2 = procesoCook.get(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Proceso de " + elemento2 + " completado");
                zonas++;
            }
            else if(zonas==2){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("100%  [###############################] 100%");
                String elemento3 = procesoCook.get(2);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Proceso de " + elemento3 + " completado");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producto Cocinado");
                zonas=0;
            }
        }
    }

    /**
     * Método que imprime una representación gráfica de una X y muestra el elemento del proceso de cocinado que ha fallado.
     * @param procesoCook Lista de Strings que contiene los elementos del proceso de cocinado.
     * @param longitud Entero que indica la longitud de la lista procesoCook.
     */
    public static void fallosCocinando(List<String> procesoCook,int longitud){
        if (longitud == 1){
            System.out.println("X    X");
            System.out.println(" X  X ");
            System.out.println("  XX  ");
            System.out.println(" X  X ");
            System.out.println("X    X");
            String elemento = procesoCook.get(0);
            System.out.println("Fallo en el proceso de " + elemento);
            zonas=0;
        }
        if (longitud == 2){
            System.out.println("X    X");
            System.out.println(" X  X ");
            System.out.println("  XX  ");
            System.out.println(" X  X ");
            System.out.println("X    X");
            if(zonas==0){
            String elemento = procesoCook.get(0);
            System.out.println("Fallo en el proceso de " + elemento);
            zonas=0;
        }
            if(zonas==1){
            String elemento2 = procesoCook.get(1);
            System.out.println("Fallo en el proceso de " + elemento2);
            zonas=0;
        }
        }
        if (longitud==3){
            System.out.println("X    X");
            System.out.println(" X  X ");
            System.out.println("  XX  ");
            System.out.println(" X  X ");
            System.out.println("X    X");
            if(zonas==0){
            String elemento = procesoCook.get(0);
            System.out.println("Fallo en el proceso de " + elemento);
            zonas=0;
            }
            if(zonas==1){
            String elemento2 = procesoCook.get(1);
            System.out.println("Fallo en el proceso de " + elemento2);
            zonas=0;
            }
            if(zonas==2){
            String elemento3 = procesoCook.get(2);
            System.out.println("Fallo en el proceso de " + elemento3);
            zonas=0;
            }
        }
    }
    /**
     * Método que imprime un patrón de X's y un mensaje de error indicando que ha habido un fallo en el proceso de cocinado por falta de ingredientes.
     */
    public static void fallosCocinando(){
        System.out.println("X    X");
        System.out.println(" X  X ");
        System.out.println("  XX  ");
        System.out.println(" X  X ");
        System.out.println("X    X");
        System.out.println("Fallo en el proceso de Cocinado por falta de ingredientes");
    }
}
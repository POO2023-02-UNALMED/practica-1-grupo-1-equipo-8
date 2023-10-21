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

    public void gestionCocina(Canasta canastaTrabajar){
        Scanner sc = new Scanner(System.in);
        }

    public void barrasCocinando(List<String> procesoCook,int longitud){
        
    }

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
}
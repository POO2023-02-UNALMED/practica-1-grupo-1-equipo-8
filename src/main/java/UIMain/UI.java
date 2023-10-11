package UIMain;

import java.util.Scanner;
import gestorAplicacion.humanos.Cliente;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.gestion.Panaderia;

public class UI {

      public static final String BLACK = "\u001B[30m";
      public static final String RED = "\u001B[31m";
      public static final String GREEN = "\u001B[32m";
      public static final String YELLOW = "\u001B[33m";
      public static final String BLUE = "\u001B[34m";
      public static final String PURPLE = "\u001B[35m";
      public static final String CYAN = "\u001B[36m";
      public static final String WHITE = "\u001B[37m";
      public static final String RESET = "\u001B[0m";

      public static void registroCliente() {
    	
    	Scanner input = new Scanner(System.in);
    	
    	System.out.println("Bienvenido a POO Bakery si desea registrarse como usuario escriba si, si ya tiene un usuario registrado escriba no");
    	String eleccion = input.nextLine();
    	
    	if (eleccion == "si") {
    	
    		System.out.println("Por favor ingrese, su nombre, su id y su presupuesto");
    		
    		String nombre = input.nextLine();
    		int id = input.nextInt();
    		float presupuesto = input.nextFloat();
    	
    		Panaderia.registrarCliente(nombre, id, presupuesto);
    		
    	}
    	
    	if (eleccion == "no") {
    		
    		System.out.println("Por favor ingrese su nombre y su contraseña"); // a definir si se hace el cambio de la contraseña
    		
    	}
    	
    	else {
    		
    		System.out.println("Ha escogido una opción invalida, por favor vuelva a intentarlo");
    		
    		registroCliente();
    		
    	}
    }

     public static void titulo(){ //tuve que agregar el simbolo \ antes de cada comilla para que java ignore los caracteres especiales, al final se vera diferente
        System.out.println("                                   ____                                    ?~~bL");
        System.out.println("                                 z@~ b                                    |  `U,");
        System.out.println("                                ]@[  |                                   ]'  z@'");
        System.out.println("                                d@~' `|, .__     _----L___----, __, .  _t'   `@j");
        System.out.println("                                `@L_,   \"-~ `--\"~-a,           `C.  ~\"\"O_    ._`@");
        System.out.println("                                q@~'   ]P       ]@[            `Y=,   `H+z_  `a@");
        System.out.println("                                `@L  _z@        d@               Ya     `-@b,_a'");
        System.out.println("                                  `-@d@a'       )@[               `VL      `a@@'");
        System.out.println("                                    aa~'   ],  .a@'                qqL  ), ./~");
        System.out.println("                                    @@_  _z~  _d@[                 .V@  .L_d'");
        System.out.println("                                    \"~@@@'  ]@@@'        __      )@n@bza@-\"");
        System.out.println("                                        `-@zzz@@@L        )@@z     ]@@=%-\"");
        System.out.println("                                        \"~~@@@@@bz_    _a@@@@z___a@K");
        System.out.println("                                            \"~-@@@@@@@@@@@@@@@@@@~\"");
        System.out.println("                                                `~~~-@~~-@@~~~~~'");
        System.out.println("");
        System.out.println("88888888ba    ,ad8888ba,     ,ad8888ba,      88888888ba             88                                         ");  
        System.out.println("88      \"8b  d8\"'    `\"8b   d8\"'    `\"8b     88      \"8b            88                                   ");
        System.out.println("88      ,8P d8'        `8b d8'        `8b    88      ,8P            88                                         ");
        System.out.println("88aaaaaa8P' 88          88 88          88    88aaaaaa8P' ,adPPYYba, 88   ,d8  ,adPPYba, 8b,dPPYba, 8b       d8 ");
        System.out.println("88\"\"\"\"\"\"'   88          88 88          88    88\"\"\"\"\"\"8b, \"\"     `Y8 88 ,a8\"  a8P_____88 88P'   \"Y8 `8b     d8'");
        System.out.println("88          Y8,        ,8P Y8,        ,8P    88      `8b ,adPPPPP88 8888[    8PP\"\"\"\"\"\"\" 88          `8b ");
        System.out.println("88           Y8a.    .a8P   Y8a.    .a8P     88      a8P 88,    ,88 88`\"Yba, \"8b,   ,aa 88           `8b,d8' ");
        System.out.println("88            `\"Y8888Y\"'     `\"Y8888Y\"'      88888888P\"  `\"8bbdP\"Y8 88   `Y8a `\"Ybbd8\"' 88             Y88'");
        System.out.println("                                                                                                       d8'     ");
        System.out.println("                                                                                                      d8'      ");
        System.out.println("");
    }
    //Este metodo muestra las opciones de todos los productos que puede comprar el cliente
    public static String mostrarOpciones() {

      String mensaje = Texto.centrar("PRODUCTOS\n");

      for (Producto producto : Producto.getProductos()) { //productos que le alcanza el dinero al cliente
        if (producto.getCosto() <= Cliente.getSesion().getPresupuesto()) {
          mensaje += GREEN+Texto.alinear(String.format("%s. %s", producto.getId(), producto.getNombre()),producto.getUnidades(),producto.getCosto())+RESET+"\n";
        }
      }

      for (Producto producto : Producto.getProductos()) { //productos que no le alcanza el dinero al cliente
        if(producto.getCosto() > Cliente.getSesion().getPresupuesto()){
          mensaje += RED+Texto.alinear(String.format("%s. %s", producto.getId(), producto.getNombre()),producto.getUnidades(),producto.getCosto())+RESET+"\n";
        }
      }
      
      mensaje += Texto.centrar("INGREDIENTES\n");

      for (Ingrediente ingrediente: Ingrediente.getIngredientes()){
        if (ingrediente.getPrecioDeVenta()<Cliente.getSesion().getPresupuesto()){
          mensaje += GREEN+Texto.alinear(String.format("%s. %s", ingrediente.getId(), ingrediente.getNombre()),0,ingrediente.getPrecioDeVenta())+RESET+"\n";
        }
      }

      for (Ingrediente ingrediente: Ingrediente.getIngredientes()){
        if (ingrediente.getPrecioDeVenta()<Cliente.getSesion().getPresupuesto()){
          mensaje += RED+Texto.alinear(String.format("%s. %s", ingrediente.getId(), ingrediente.getNombre()),0,ingrediente.getPrecioDeVenta())+RESET+"\n";
        }
      }

      return mensaje;

    }

}

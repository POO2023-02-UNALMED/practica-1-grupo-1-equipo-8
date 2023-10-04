package UIMain;
import java.io.*;
import java.util.Scanner;
import comida.*;
import gestion.*;
import humanos.*;

//Esta clase la he estado modificando yo (Richard), cualquier sugerencia me pueden escribir aqui
public class Main {
    //estoy implementado serializacion, si algo se borra
    public static void main(String[] args) {
    /*Panaderia panaderia = new Panaderia();
    	FileOutputStream fileOutputStream = new FileOutputStream("panaderia.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(panaderia);
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("panaderia.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        objectInputStream.readObject();
        Panaderia panaderia2 = (Panaderia) objectInputStream.readObject();
        objectInputStream.close();*/



    	registroCliente();
    	
        Scanner input = new Scanner(System.in);
        titulo();
        String strOpciones = "Escriba el numero correspondiente a la opcion que quiere elejir\n" +
                "1. Ver productos disponibles para compra\n" +
                "2. Agregar productos a la canasta de compras\n" +
                "3. Ver las facturas de mis compras pasadas\n";
        System.out.println(strOpciones);
        int IntEleccion = input.nextInt();
        boolean eleccionValida = true;

        do {
            switch (IntEleccion) {
                // codigo que hace que se muestren opciones disponibles
                case 1:

                    break;

                // codigo que crea canasta y permite agregar productos
                case 2:

                    break;

                // codigo que le pide al cliente su identificación y le muestra sus opciones
                case 3:
                    System.out.println("Escriba su identificación: ");
                    int identificacion = input.nextInt();
                    break;

                // caso donde el cliente elije una opcion no valida
                default:
                    System.out.println("Usted escogio una opción que no estaba en la lista, ");
                    eleccionValida = false;
                    break;
            }
        } while (eleccionValida == false);
    }

    public static void titulo(){ //tuve que agregar el simbolo \ antes de cada comilla para que java ignore los caracteres especiales, al final se vera diferente
        System.out.println("                                    ____                                    ?~~bL");
        System.out.println("                                    z@~ b                                    |  `U,");
        System.out.println("                                ]@[  |                                   ]'  z@'");
        System.out.println("                                d@~' `|, .__     _----L___----, __, .  _t'   `@j");
        System.out.println("                                `@L_,   \"-~ `--\"~-a,           `C.  ~\"\"O_    ._`@");
        System.out.println("                                q@~'   ]P       ]@[            `Y=,   `H+z_  `a@");
        System.out.println("                                `@L  _z@        d@               Ya     `-@b,_a'");
        System.out.println("                                    `-@d@a'       )@[               `VL      `a@@'");
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
    }
    
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

}

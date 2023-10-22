package UIMain;

import java.util.Scanner;

import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.humanos.Cliente;

public class GestionInicioCliente {

  //Este método se encarga de la sesion del cliente, bien sea para crear una cuenta o para ingresar a una existente
  //este método 

  public static boolean sesionCliente(Panaderia panaderia) { // IMPORTANTE CAMBIAR EL ATRIBUTO ESTATICO CLIENTE.SESION

    Scanner input = new Scanner(System.in);

    System.out.println(
        "Bienvenido a Poo Bakery, si ya tiene una cuenta registrada con nosotros por favor ingrese 1, si desea crear una cuenta por favor ingrese 2, si desea salir por favor escriba 0");

    int eleccion = input.nextInt();

    if (eleccion == 1) {
      
      GestionInicioCliente.iniciarSesion(panaderia);
    }

    else if(eleccion == 2){
      
      GestionInicioCliente.registroCliente(panaderia);
    }

    else if(eleccion == 0){

      input.close();
      return false;
    }

    input.close();
    return true;
  }

  public static void registroCliente(Panaderia panaderia) {

    Scanner input = new Scanner(System.in);

      System.out.println("Por favor ingrese, su nombre, su id y una contraseña");

      String nombre = input.nextLine();
      int id = input.nextInt();
      String presupuesto = input.nextLine();

      System.out.println(panaderia.crearCuenta(nombre, id, presupuesto));
      input.close();
  }

  public static void iniciarSesion(Panaderia panaderia){
    
    Scanner input = new Scanner(System.in);

      System.out.println("Por favor ingrese su id y su contraseña");

      int id = input.nextInt();
      String contrasena = input.nextLine();

      Cliente usuario = panaderia.inicioSesionId(id);
      String suceso = panaderia.inicioSesionConstrasena(usuario, contrasena);
      System.out.println(suceso);

      while (suceso == "Contrasena incorrecta"){

      System.out.println("Por favor ingrese de nuevo su contraseña o ingrese 0 si desea ir al menú anterior");

      String contrasena2 = input.nextLine();

      if (contrasena2 == "0"){

        GestionInicioCliente.sesionCliente(panaderia);
      }

      else{

        suceso = panaderia.inicioSesionConstrasena(usuario, contrasena2);
      }
    }
    input.close();
  }
}

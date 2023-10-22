package UIMain;

import java.util.Scanner;

import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.humanos.Cliente;

public class GestionInicioCliente {

  //Este método se encarga de la sesion del cliente, bien sea para crear una cuenta o para ingresar a una existente
  //este método 

  public static boolean sesionCliente(Panaderia panaderia) { // IMPORTANTE CAMBIAR EL ATRIBUTO ESTATICO CLIENTE.SESION

    Scanner input = new Scanner(System.in);

    System.out.println("Bienvenido a Poo Bakery, si ya tiene una cuenta registrada con nosotros por favor ingrese 1, si desea crear una cuenta por favor ingrese 2, si desea salir por favor escriba 0");

    String eleccion = input.nextLine();
    switch(eleccion){
      case "1":
        GestionInicioCliente.iniciarSesion(panaderia);
        input.close();
        return true;
      case "2":
        GestionInicioCliente.registroCliente(panaderia);
        input.close();
        return true;
      case "0":
        input.close();
        return false;
      default:
        System.out.println("Por favor ingrese una opcion valida");
        input.close();
        return true;
    }
  }

  public static void registroCliente(Panaderia panaderia) {

    Scanner input = new Scanner(System.in);
    int id = 0;

      System.out.println("Por favor ingrese su nombre:");
      String nombre = input.nextLine();

      while(true){
        System.out.println("Por favor ingrese su id:");
        try{
          id = input.nextInt();
          break;
        }
        catch(Exception e){
          System.out.println("Por favor ingrese un id valido (solo numeros)");
        }
      }

      System.out.println("Por favor ingrese su contrasena:");
      String contrasena = input.nextLine();

      System.out.println(panaderia.crearCuenta(nombre, id, contrasena));
      input.close();
  }

  public static void iniciarSesion(Panaderia panaderia){
    
    Scanner input = new Scanner(System.in);

      int id = 0;

      while(true){
        System.out.println("Por favor ingrese su id:");
        try{
          id = input.nextInt();
          break;
        }
        catch(Exception e){
          System.out.println("Por favor ingrese un id valido (solo numeros)");
        }
      }

      System.out.println("Por favor ingrese su contrasena:");
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

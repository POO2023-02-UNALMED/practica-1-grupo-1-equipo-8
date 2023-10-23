package UIMain;

import java.util.Scanner;

import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.humanos.Cliente;

public class GestionInicioCliente {

  //Este método se encarga de la sesion del cliente, bien sea para crear una cuenta o para ingresar a una existente
  //este método 

  public static boolean sesionCliente(Panaderia panaderia) { // IMPORTANTE CAMBIAR EL ATRIBUTO ESTATICO CLIENTE.SESION

    Scanner input2 = new Scanner(System.in);
    while(true){
      System.out.println("Bienvenido a Poo Bakery, si ya tiene una cuenta registrada con nosotros por favor ingrese 1, si desea crear una cuenta por favor ingrese 2, si desea salir por favor escriba 0");

      String eleccion = input2.nextLine();
      switch(eleccion){
        case "1":
          GestionInicioCliente.iniciarSesion(panaderia);
          return true;
        case "2":
          GestionInicioCliente.registroCliente(panaderia);
          return true;
        case "0":
          return false;
        default:
          System.out.println("Por favor ingrese una opcion valida");
          break;
      }
    }
  }

  public static void registroCliente(Panaderia panaderia) {
    Scanner input3 = new Scanner(System.in);
    String id = "";
    String nombre="";
    while(true){
      System.out.println("Por favor ingrese su nombre:");
      nombre = input3.nextLine();
      boolean contieneSoloLetras = nombre.matches("[a-zA-Z]+");
      if (!contieneSoloLetras) {
        System.out.println("El nombre contiene numeros u otros caracteres, ingrese un nombre valido (Solo letras)");
        continue;
      }

      while(true){
        System.out.println("Por favor ingrese su id: ");
        id = input3.nextLine();
        try{
          int fr = Integer.parseInt(id);
          break;
        }
        catch(NumberFormatException e){
          System.out.println("Por favor ingrese un id valido (solo numeros)");
        }
      }

      System.out.println("Por favor ingrese su contrasena:");
      String contrasena = input3.nextLine();
      
      System.out.println(panaderia.crearCuenta(nombre, Integer.parseInt(id), contrasena));
      break;
    }
  }

  public static void iniciarSesion(Panaderia panaderia){
    Scanner input4 = new Scanner(System.in);
    while(true){
      String id = "";

      while(true){
        System.out.println("Por favor ingrese su id: ");
        id = input4.nextLine();
        try{
          int fr = Integer.parseInt(id);
          break;
        }
        catch(NumberFormatException e){
          System.out.println("Por favor ingrese un id valido (solo numeros)");
        }
      }

      Cliente usuario = panaderia.inicioSesionId(Integer.parseInt(id));
      if (usuario == null){
        System.out.println("No existe un usuario con ese id");
        continue;
      }

      System.out.println("Por favor ingrese su contrasena:");
      String contrasena = input4.nextLine();

      String suceso = panaderia.inicioSesionConstrasena(usuario, contrasena);

      while (suceso.equals("Contrasena incorrecta")){
        System.out.println(suceso);
        System.out.println("Por favor ingrese de nuevo su contrasena o ingrese 0 si desea ir al menu anterior");

        String contrasena2 = input4.nextLine();
        

        if (contrasena2 == "0"){

          GestionInicioCliente.sesionCliente(panaderia);
        }

        else{

          suceso = panaderia.inicioSesionConstrasena(usuario, contrasena2);
          if (suceso =="Inicio de sesion exitoso"){
            break;
          }
        }
      }
      System.out.println(suceso);
      break;
    }
  }
}

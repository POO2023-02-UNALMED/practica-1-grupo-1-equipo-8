package UIMain;

import java.util.Scanner;

import gestorAplicacion.gestion.Panaderia;

public class GestionInicioCliente {

  public static void sesionCliente(){ //IMPORTANTE CAMBIAR EL ATRIBUTO ESTATICO CLIENTE.SESION
  
  
    System.out.println("Si ya tiene una cuenta registrada con nosotros por favor ingrese 1, si desea crear una cuenta por favor ingrese 0");

    if (){
      
    }
        /*
    *Si ya tiene cuenta, se le pide que ingrese su id y contraseña para acceder a sus datos (Se llama a inicioSesion())
      *Si los datos ingresados fueron erroneos, se le avisa al cliente y se le da la opcion de volver a intentarlo o crear una cuenta nueva

    *Si no tiene cuenta, se le pide que ingrese su nombre, id, contraseña y presupuesto (Se llama a crearCuenta())
      *Se guardan sus datos id y contraseña para que despues pueda acceder a su cuenta en un proximo programa
      *Además se guardan el resto de datos en cliente

    ! Por determinar, se le pide ingresar la direccion, presupuesto y descuentoPorTipo al cliente justo al crearse al cuenta y antes de mostrar opciones? (Yo creo que es conveniente que se haga asi, es decir, justo al crear la cuenta)
    *Se llama a la funcion gestionDatosFaltantes() para que el cliente ingrese la informacion faltante si es que realmente falta

    *Le salta el menu de bienvenida con su nombre
    *Se le muestra el menu de opciones
    */
  }

  //coloco aqui el borrador de metodo de inicio de cliente que crearon antes
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

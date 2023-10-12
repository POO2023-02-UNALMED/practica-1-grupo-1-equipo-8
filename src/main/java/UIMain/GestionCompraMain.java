package UIMain;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.gestion.Canasta;

public class GestionCompraMain {
  public void gestionRecibirOrdenCanasta(Canasta canasta){
    Scanner scanner = new Scanner(System.in);
    String objetoEntrante;
    String cantidad;
    boolean continuar = true;

    while (continuar) {
      //Se pueden cambiar luego estos print
      System.out.println("Ingrese el nombre del producto o ingrediente (Escriba '0' para terminar): ");
      objetoEntrante = scanner.nextLine();
      if (objetoEntrante.equalsIgnoreCase("0")) {
        continuar = false;
        break;
      }

      System.out.println("Ingrese la cantidad: ");
      cantidad = scanner.nextLine();
      if (cantidad.equalsIgnoreCase("0")) {
        continuar = false;
        break;
      }

      if(canasta.recibirOrden(objetoEntrante, cantidad,true)){
        System.out.println("Se ha agregado el producto o ingrediente a la canasta.");
      } 
      else {
        String entrada;
        int cantidadIngrediente;
        Map<String, Integer> ingredientesNecesarios = new HashMap<String, Integer>();
        System.out.println("No manejamos el producto que ingresó");
        System.out.println("Indiquenos los ingredientes necesarios para su preparacion y se lo cocinaremos");
        while (true) {
          System.out.println("Ingrese el ingrediente (Escriba 's' cuando termine con el listado): ");
          while (true) {
            System.out.println("Ingresa el nombre del ingrediente: ");
            entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("s") & ingredientesNecesarios.isEmpty()) {
              System.out.println("Necesitas al menos un ingrediente");
            } else {
              break;
            }
          }

          if (entrada.equalsIgnoreCase("s")) {
            break;
          }

          while (true) {
            System.out.println("Ingrese la cantidad: ");
            try {
              cantidadIngrediente = scanner.nextInt();
              if (cantidadIngrediente <= 0) {
                System.out.println("La cantidad debe ser mayor a 0");
              } else {
                break;
              }
            } catch (Exception e) {
              System.out.println("Debes ingresar un numero");
            }
          }

          ingredientesNecesarios = canasta.gestionAgregar(entrada, cantidadIngrediente, ingredientesNecesarios);
          System.out.println("Desea agregar otro ingrediente? (Escriba 's' cuando termine con el listado):");
          entrada = scanner.nextLine();
          if (entrada.equalsIgnoreCase("s")) {
            break;
          }
        }
        if(canasta.recibirOrdenPersonalizada(objetoEntrante, ingredientesNecesarios, cantidad,true)){
          System.out.println("Se ha agregado el nuevo producto a la canasta.");
        }
        else {
        System.out.println("No se ha podido agregar el producto a la canasta, indica un elemento válido.");
      }
      }
      System.out.println("Desea agregar otro producto o ingrediente a la canasta? (Escriba '0' cuando termine con el listado):");
      objetoEntrante = scanner.nextLine();
      if (objetoEntrante.equalsIgnoreCase("0")||objetoEntrante.equalsIgnoreCase("no")) {
        break;
      }
    }
    scanner.close();
  }
  
  /**
   * Muestra el contenido de la cesta de la compra, incluidos productos, ingredientes y coste total.
   */
  public void mostrarCanasta(Canasta canasta) {
    System.out.println(Texto.centrar("PRODUCTOS"));
    System.out.println("_".repeat(55));
    System.out.println(Texto.alinear("Descripcion", "Cantidad", "Costo"));
    System.out.println("_".repeat(55));
    System.out.println("");
    for (Map.Entry<Producto, Integer> productoEntry : canasta.getProductos().entrySet()) {
      Producto producto = productoEntry.getKey();
      Integer cantidad = productoEntry.getValue();
      System.out.println(Texto.alinear(producto.getNombre(), cantidad, producto.getCosto()));
    }
    System.out.println("_".repeat(55));
    System.out.println("");
    System.out.println(Texto.centrar("INGREDIENTES"));
    System.out.println("_".repeat(55));
    System.out.println(Texto.alinear("Descripcion", "Cantidad", "Costo"));
    System.out.println("_".repeat(55));
    for (Map.Entry<Ingrediente, Integer> ingredienteEntry : canasta.getIngredientes().entrySet()) {
      Ingrediente ingrediente = ingredienteEntry.getKey();
      Integer cantidad = ingredienteEntry.getValue();
      System.out.println(Texto.alinear(ingrediente.getNombre(), cantidad, ingrediente.getPrecioDeVenta()));
    }
    System.out.println("_".repeat(55));
    System.out.println(Texto.alinear("Descuento efectuado: ",canasta.getDescuento()));
    System.out.println(Texto.alinear("**** SUBTOTAL/TOTAL *****",canasta.getCostoTotalEnLista())); //el valor para i1 sera el total de productos comprados
    System.out.println("_".repeat(55));
  }
  
  //Este método se encarga de indicar si robaron al trabajador al ir a comprar los ingredientes
  
  public static void lecturaRobo(boolean suceso) {
	  
	  if (suceso == true) {
		  
		  System.out.println("Han robado al trabajador que iba por los ingredientes para su pedido, pedimos disculpas por el atraso, enviaremos a alguien mas por la compra");
		  
	  }
	  
	  else {
		  
		  System.out.println("El trabajador ha regresado con los ingredientes con su pedido, por favor espere a que preparemos su pedido");
		  
	  } 
  }
  
  public static void lecturaQuiebra(boolean suceso) {
	  
	  if (suceso == true) {
		  
		  System.out.println("La panadería no tenía dinero para realizar su pedido y ha entrado en quiebra :( , pero una franquicia más grande la ha comprado :D , podremos proseguir con su pedido");
		  
	  }
	  
	  else {
		  
		  System.out.println("La panadería no tenía dinero para realizar su pedido :( , pero ha pedido un prestamo y ya puede comprar lo que necesita para preparar su pedido :D");
		  
    } 
	  
  }
  
  public static void lecturaCompra(boolean suceso) {
	  
	  if (suceso == false) { 
		  
		  System.out.println("No tenemos suficientes ingredientes para realizar su compra, por favor espere unos minutos mientras nos encargamos de ello.");
		  
	  }
	  
	  else {
		  
		  System.out.println("Estamos en proceso de conseguir los ingredientes para su pedido, lamentamos los inconvenientes");
		  
	  }
		 
   }
}

package UIMain;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import comida.Ingrediente;
import comida.Producto;
import gestion.Canasta;

public class GestionCompraMain {
  public void gestionRecibirOrdenCanasta(Canasta canasta){
    Scanner scanner = new Scanner(System.in);
    String objetoEntrante;
    String cantidad;
    boolean continuar = true;

    while (continuar) {
      //Se pueden cambiar luego estos print
      System.out.println("Ingrese el nombre del producto o ingrediente (Escriba 'salir' para terminar): ");
      objetoEntrante = scanner.nextLine();
      if (objetoEntrante.equalsIgnoreCase("salir")) {
        continuar = false;
        break;
      }

      System.out.println("Ingrese la cantidad: ");
      cantidad = scanner.nextLine();
      if (cantidad.equalsIgnoreCase("salir")) {
        continuar = false;
        break;
      }

      if(canasta.recibirOrden(objetoEntrante, cantidad)){
        System.out.println("Se ha agregado el producto o ingrediente a la canasta.");
      } 
      else {
        String entrada;
        int cantidadIngrediente;
        Map<String, Integer> ingredientesNecesarios = new HashMap<String, Integer>();
        System.out.println("No manejamos el producto que ingresó");
        System.out.println("Indiquenos los ingredientes necesarios para su preparacion y se lo cocinaremos");
        while (true) {
          System.out.println("Ingrese el ingrediente (Escriba 'ya' cuando termine con el listado): ");
          while (true) {
            System.out.println("Ingresa el nombre del ingrediente: ");
            entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("ya") & ingredientesNecesarios.isEmpty()) {
              System.out.println("Necesitas al menos un ingrediente");
            } else {
              break;
            }
          }

          if (entrada.equalsIgnoreCase("ya")) {
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
          System.out.println("Desea agregar otro ingrediente? (Escriba 'ya' cuando termine con el listado):");
          entrada = scanner.nextLine();
          if (entrada.equalsIgnoreCase("ya")) {
            break;
          }
        }
        if(canasta.recibirOrdenPersonalizada(objetoEntrante, ingredientesNecesarios, cantidad)){
          System.out.println("Se ha agregado el nuevo producto a la canasta.");
        }
        else {
        System.out.println("No se ha podido agregar el producto a la canasta, indica un elemento válido.");
      }
      }
      System.out.println("Desea agregar otro producto o ingrediente a la canasta? (Escriba 'salir' cuando termine con el listado):");
      objetoEntrante = scanner.nextLine();
      if (objetoEntrante.equalsIgnoreCase("salir")||objetoEntrante.equalsIgnoreCase("no")) {
        break;
      }
    }
    scanner.close();
  }
  
  /**
   * Muestra el contenido de la cesta de la compra, incluidos productos, ingredientes y coste total.
   */
  public void mostrarCanasta(Canasta canasta) {
    System.out.println("Productos:");
    for (Map.Entry<Producto, Integer> productoEntry : canasta.getProductos().entrySet()) {
      Producto producto = productoEntry.getKey();
      Integer cantidad = productoEntry.getValue();
      System.out.println(producto.getNombre() + " x" + cantidad);
    }
    System.out.println("Ingredientes:");
    for (Map.Entry<Ingrediente, Integer> ingredienteEntry : canasta.getIngredientes().entrySet()) {
      Ingrediente ingrediente = ingredienteEntry.getKey();
      Integer cantidad = ingredienteEntry.getValue();
      System.out.println(ingrediente.getNombre() + " x" + cantidad);
    }
    System.out.println("Costo: " + canasta.getCosto());
    System.out.println("Descuento efectuado: " + canasta.getDescuento());
  }
}

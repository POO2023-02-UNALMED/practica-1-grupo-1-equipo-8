package UIMain;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.gestion.Inventario;

public class GestionCompra {
  

  /**
   * Método que maneja la recepción de una orden de compra de una canasta.
   * Permite al usuario agregar o modificar productos de un catálogo o agregar un producto personalizado.
   * @param canasta La canasta a la que se le agregará la orden.
   */
  public static void gestionRecibirOrdenCanasta(Canasta canasta){
    Scanner scanner = new Scanner(System.in);
    String tipoDeProducto="0"; //1=Catalogo, 2=Personalizado
    String objetoEntrante;
    String cantidad;
    String kit;
    boolean continuar = true;

    while (true) {
      continuar = true;
      if(tipoDeProducto.equals("0")){
        while(true){
          System.out.println("Desea modificar un producto de nuestro catalogo o agregar un producto personalizado? (Escriba '0'=Volver atrás - '1'=Catalogo - '2'=Personalizado): ");
          tipoDeProducto = scanner.nextLine();
          if(tipoDeProducto.equalsIgnoreCase("0")||tipoDeProducto.equalsIgnoreCase("1")||tipoDeProducto.equalsIgnoreCase("2")){
            if(tipoDeProducto.equalsIgnoreCase("0")){
              continuar = false;
            }
            break;
          }
          else{
            System.out.println("Debes ingresar un numero adecuado");
          }
        }
      }

      if(!continuar){
        break;
      }

      if(tipoDeProducto.equalsIgnoreCase("1")){
        tipoDeProducto = "0";
        while(true){
          System.out.println("Ingrese el id del elemento que quiere modificar de la orden(Escriba '0'para volver atrás): ");
          try{
            objetoEntrante = scanner.nextLine();
            int numero = Integer.parseInt(objetoEntrante);
            if (numero == 0) {
              continuar = false;
            }
            break;
          }
          catch(Exception e){
            System.out.println("Debes ingresar un numero adecuado");
            continue;
          }
        }

        if(!continuar){
          continue;
        }

        while(true){
          System.out.println("Ingrese la cantidad (Número positivo para agregar a la orden, negativo para restar de la orden) (Escriba '0'para volver atrás) : ");
          try{
            cantidad = scanner.nextLine();
            int numero = Integer.parseInt(cantidad);
            if (numero == 0) {
              continue;
            }
            else if(numero>19){
              System.out.println("No puedes pedir más de 19 productos iguales");
              continue;
            }
            else if(numero < -19){
              System.out.println("No puedes quitar más de 19 productos iguales");
              continue;
            }
            else{
              break;
            }
          }
          catch(Exception e){
            System.out.println("Debes ingresar un numero adecuado");
          }
        }

        while(true){
          System.out.println("Desea que se le entregue el kit de cocina? (Escriba '1'=Si o '2'=No): ");
          kit = scanner.nextLine();
          if(kit.equalsIgnoreCase("1")||kit.equalsIgnoreCase("2")){
            break;
          }
          else{
            System.out.println("Debes ingresar un numero adecuado");
          }
        }

        String respuesta; 
        if(kit.equals("1")){
          respuesta = canasta.recibirOrden(objetoEntrante, cantidad,true);
        }
        else {
          respuesta = canasta.recibirOrden(objetoEntrante, cantidad,false);
        }

        if(canasta.estadoOrden){ //Si no hay errores
          System.out.println(respuesta);
        } 

        else {
          System.out.println(respuesta);
          if(respuesta.equals("No se ha podido realizar el proceso")){
            System.out.println("No manejamos un producto con el id que ingresó");
            System.out.println("Si nos indica los ingredientes necesarios para su preparacion, se lo cocinaremos");
            
            while(true){
              System.out.println("Desea agregar un producto personalizado? (Escriba '0'=No y volver al inicio - '1'=Si):");
              try{
                String entrada = scanner.nextLine();
                int numero = Integer.parseInt(entrada);
                if (numero == 0) {
                  continuar = false;
                }
                break;
              }
              catch(Exception e){
                System.out.println("Debes ingresar un numero adecuado");
              }
            }

            if(!continuar){
              continue;
            }

            tipoDeProducto = "2";

          }

          else{
            System.out.println("No se ha podido realizar el proceso, vuelva a intentarlo");
            continue;
          }
        }
      }

      else if(tipoDeProducto.equalsIgnoreCase("2")){
        tipoDeProducto = "0";
        String entrada;
        int cantidadIngrediente;
        boolean kitValue = false;
        HashMap<String, Integer> ingredientesNecesarios = new HashMap<String, Integer>();

        while(true){
          System.out.println("Ingrese el nombre del producto personalizado (Escriba '0'para volver atrás): ");
          objetoEntrante = scanner.nextLine();
          if(objetoEntrante.equalsIgnoreCase("0")){
            continuar = false;
          }
          break;
        }

        if(!continuar){
          continue;
        }

        System.out.println("Ingrese los ingrediente (Escriba '0' cuando termine con el listado): ");
        while (true) {
          while (true) {
            System.out.println("Ingresa el nombre del ingrediente (Escriba '0' si ya terminó con el listado):");
            entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("0") & ingredientesNecesarios.isEmpty()) {
              System.out.println("Necesitas al menos un ingrediente");
            } 
            else if(entrada.equalsIgnoreCase("0")){
              continuar = false;
            }
            else{
              break;
            }
          }
          if(!continuar){
            break;
          }

          while (true) {
            System.out.println("Ingrese la cantidad: ");
            try {
              cantidadIngrediente = scanner.nextInt();
              if (cantidadIngrediente <= 0) {
                System.out.println("La cantidad debe ser mayor a 0");
              } 
              else if(cantidadIngrediente>19){
                System.out.println("No puedes pedir más de 19 ingredientes iguales");
              }
              else {
                break;
              }
            } catch (Exception e) {
              System.out.println("Debes ingresar un numero");
            }
          }

          ingredientesNecesarios = canasta.gestionAgregar(entrada, cantidadIngrediente, ingredientesNecesarios);
          }
        
        while(true){
          System.out.println("Ingrese la cantidad (Número positivo para agregar a la orden, negativo para restar de la orden) (Escriba '0'para volver atrás) : ");
          try{
            cantidad = scanner.nextLine();
            int numero = Integer.parseInt(cantidad);
            if (numero == 0) {
              continuar = false;
            }
            else if(numero>19){
              System.out.println("No puedes pedir más de 19 productos iguales");
              continue;
            }
            else if(numero < -19){
              System.out.println("No puedes quitar más de 19 productos iguales");
              continue;
            }
            else{
              break;
            }
          }
          catch(Exception e){
            System.out.println("Debes ingresar un numero adecuado");
            continue;
          }
        }

        while (true) {
          System.out.println("Desea que se le entregue el kit de cocina? (Escriba '1'=Si o '2'=No): ");
          kit = scanner.nextLine();
          if (kit.equalsIgnoreCase("1") || kit.equalsIgnoreCase("2")) {
            break;
          } else {
            System.out.println("Debes ingresar un numero adecuado");
          }
        }

        if(kit.equals("1")){
          kitValue = true;
        }
        else {
          kitValue = false;
        }
        if(canasta.recibirOrdenPersonalizada(objetoEntrante, ingredientesNecesarios, cantidad, kitValue)){
          System.out.println("Su orden ha sido recibida y el producto ha sido anotado");
        }
        else{
          System.out.println("No se ha podido realizar el proceso, vuelva a intentarlo");
          continue;
        }
      }
      System.out.println("Desea agregar otro producto o ingrediente a la canasta? (Escriba '0' para terminar - '1' para seguir):");
      try{
        String entrada;
        entrada = scanner.nextLine();
        int numero = Integer.parseInt(entrada);
        if (numero == 0) {
          continuar = false;
        }
        else if(numero == 1){
          continue;
        }
      }
      catch(Exception e){
        System.out.println("Debes ingresar un numero adecuado");
      }
      if(!continuar){
        break;
      }
    }
    scanner.close();
  }
  
  /**
   * Muestra el contenido de la cesta de la compra, incluidos productos, ingredientes y coste total.
   */
  //Esto se va a mostrar antes de pagar, mientras el cliente agrega sus productos, y tambien en la factura
  public static void mostrarCanasta(Canasta canasta) {
    System.out.println(Texto.centrar("PRODUCTOS"));
    System.out.println("_".repeat(55));
    System.out.println(Texto.alinear("Descripcion", "Cantidad", "Costo"));
    System.out.println("_".repeat(55));
    System.out.println("");
    for (Map.Entry<String, Integer> entry : canasta.getProductosEnLista().entrySet()) {
      String producto = entry.getKey();
      Integer cantidad = entry.getValue();
      System.out.println(Texto.alinear(Producto.obtenerObjetoPorId(producto).getNombre(), cantidad, Producto.obtenerObjetoPorId(producto).getCosto()));
    }

    System.out.println("_".repeat(55));
    System.out.println("");
    System.out.println(Texto.centrar("INGREDIENTES"));
    System.out.println("_".repeat(55));
    System.out.println(Texto.alinear("Descripcion", "Cantidad", "Costo"));
    System.out.println("_".repeat(55));
    System.out.println("");
    for (Map.Entry<String, Integer> entry : canasta.getIngredientesEnLista().entrySet()) {
      String ingrediente = entry.getKey();
      Integer cantidad2 = entry.getValue();
      System.out.println(Texto.alinear(Ingrediente.obtenerObjetoPorId(ingrediente).getNombre(), cantidad2, Ingrediente.obtenerObjetoPorId(ingrediente).getPrecioDeCompra()));
    }

    System.out.println("_".repeat(55));
    System.out.println("");
    System.out.println(Texto.centrar("KITS"));
    System.out.println("_".repeat(55));
    System.out.println(Texto.alinear("Descripcion", "Cantidad", "Costo"));
    System.out.println("_".repeat(55));
    System.out.println("");
    for (Map.Entry<String, Integer> entry : canasta.getKitsEnLista().entrySet()) {
      String kit = entry.getKey();
      Integer cantidad2 = entry.getValue();
      System.out.println(Texto.alinear(Producto.obtenerObjetoPorId(kit).getNombre(), cantidad2, Producto.obtenerObjetoPorId(kit).getCosto()));
    }


    System.out.println("_".repeat(55));
    System.out.println(" ");
    System.out.println(Texto.alinear("Descuento efectuado: ",canasta.getDescuentoEnLista()));
    System.out.println(Texto.alinear("subtotal",canasta.getCostoTotalEnLista())); //el valor para i1 sera el total de productos comprados
  }
  
  //Este método se encarga de indicar si robaron al trabajador al ir a comprar los ingredientes
}

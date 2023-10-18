import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.comida.ProductoFrio;
import gestorAplicacion.gestion.Panaderia;

import java.util.HashMap;

public class ProductosTest {
  public static void main(String[] args) {
    System.out.println("Test de productos");
    
    HashMap<String, Integer> ingredientes = new HashMap<String, Integer>();
    ingredientes.put("leche", 1);
    ingredientes.put("azucar", 1);
    ProductoFrio producto = ProductoFrio.crearProductoPersonalizado("postre", ingredientes);
    for(Ingrediente ingrediente : Ingrediente.getBaseDatosIngredientes()) {
      System.out.println(ingrediente.getNombre());
    }
    for(Producto producto1 : Producto.getBaseDatosProductos()) {
      System.out.println(producto1.getNombre());
    }

    ProductoFrio producto2 = ProductoFrio.crearProducto("3");

    for(Ingrediente ingrediente : Ingrediente.getBaseDatosIngredientes()) {
      System.out.println(ingrediente.getNombre());
    }
    for(Producto producto1 : Producto.getBaseDatosProductos()) {
      if(producto1 instanceof ProductoFrio){
        System.out.println(producto1.getNombre());
        }
    }

    ProductoFrio producto3 = ProductoFrio.crearProducto("3");
    for(Ingrediente ingrediente : Ingrediente.getBaseDatosIngredientes()) {
      System.out.println(ingrediente.getNombre());
    }
    for(Producto producto1 : Producto.getBaseDatosProductos()) {
      if(producto1 instanceof ProductoFrio){
        System.out.println(producto1.getNombre());
        }
    }

    Panaderia.getInvProductos().add(producto3);
    ProductoFrio productoSacadoDeInventario = (ProductoFrio) Panaderia.buscarProductoPorId("3");
  }
}

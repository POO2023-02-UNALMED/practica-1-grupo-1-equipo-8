package gestorAplicacion.comida;

import java.util.HashMap;
import java.util.ArrayList;

public class ProductoFrio extends Producto{
  private double tipoDeEnvase;
  private int tiempoDeVida;

  public ProductoFrio(String nombre, HashMap<String,Integer> ingredientes, double costo, String sabor, ArrayList<String> procesoDeCocina, double tipoDeEnvase, int tiempoDeVida) {
    super(nombre, ingredientes, costo, sabor, procesoDeCocina);
    this.tipoDeEnvase = tipoDeEnvase;
    this.tiempoDeVida = tiempoDeVida;
  }

  public ProductoFrio(String nombre, HashMap<String,Integer> ingredientes) {
    super(nombre, ingredientes);
    this.tipoDeEnvase = tipoDeEnvase;
    this.tiempoDeVida = tiempoDeVida;
  }
  
  public static Producto crearProducto(String Nnombre) {
    Producto newProducto = obtenerObjetoPorNombre(Nnombre);
    return new Producto(newProducto.getNombre(), newProducto.getIngredientes(), newProducto.getCosto(),
        newProducto.getSabor(), newProducto.getProcesoDeCocina());
  }
  
  public static Producto crearProductoPersonalizado(String Nnombre, HashMap<String, Integer> ingredientes) {
    for (HashMap.Entry<String, Integer> entry : ingredientes.entrySet()) {
      if (!Ingrediente.verificacionExistenciaPorNombre(entry.getKey())) {
        new Ingrediente(entry.getKey());
      }
    }
    return new Producto(Nnombre, ingredientes);
  }
  
  public ArrayList<String> seleccionProcesoDeCocina() {
    ArrayList<String> procesoDeCocina = new ArrayList<String>();

    return procesoDeCocina;
  }
}

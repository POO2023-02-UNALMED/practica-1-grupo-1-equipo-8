package gestion;
import comida.Ingrediente;
import comida.Producto;
import java.util.HashMap;
import java.util.Map;

public class Canasta {
  private Map<Producto, Integer> productos = new HashMap<Producto, Integer>();
  private Map<Ingrediente, Integer> ingredientes = new HashMap<Ingrediente, Integer>();
  private double costo;

  //gets y sets de los atributos
  public Map<Producto, Integer> getProductos() {return productos;}
  public void setProductos(Map<Producto, Integer> productos) {this.productos = productos;}
  public Map<Ingrediente, Integer> getIngredientes() {return ingredientes;}
  public void setIngredientes(Map<Ingrediente, Integer> ingredientes) {this.ingredientes = ingredientes;}
  public double getCosto() {return costo;}
  public void setCosto(double costo) {this.costo = costo;}

  //Metodos para agregar y eliminar productos a los maps de la canasta
  public void agregarProducto(Producto producto){gestionAgregar(producto,1);}
  public void eliminarProducto(Producto producto){productos.remove(producto);}
  public void agregarIngrediente(Ingrediente ingrediente){gestionAgregar(ingrediente,1);}
  public void eliminarIngrediente(Ingrediente ingrediente){ingredientes.remove(ingrediente);}
  public void agregarKit(Producto producto){
    Map<Ingrediente,Integer> ingrdtsProducto=producto.ingredientes;
    ingrdtsProducto.forEach((ingrdts,cantidad)-> gestionAgregar(ingrdts,cantidad));
  }
  //No veo positivo la funcion de eliminarKit, de la manera como está planteado generaría demasiados problemas
  //public void eliminarKit(Producto producto){}

  //Metodos que gestionan correctamente la modificacion de los maps
  //Verifican que efectivamente el elemento no exista en el map antes de agregarlo, en el caso de que si, simplemente agrega una unidad más
  public void gestionAgregar(Producto producto,int elementNum){
    if ((producto != null) && (!productos.containsKey(producto))){
      productos.put(producto,elementNum);
    } else if((producto != null)){
      productos.put(producto,productos.get(producto)+elementNum);
    } else{
      System.out.println("Hay un problema, no se puede añadir un objeto nulo");
    }
  }
  public void gestionAgregar(Ingrediente ingrediente,int elementNum){
    if ((ingrediente != null) && (!ingredientes.containsKey(ingrediente))){
      ingredientes.put(ingrediente,elementNum);
    } else if((ingrediente != null)){
      ingredientes.put(ingrediente,ingredientes.get(ingrediente)+elementNum);
    } else{
      System.out.println("Hay un problema, no se puede añadir un objeto nulo");
    }
  }
  //Verifican que efectivamente el elemento exista en el map antes de eliminarlo, en el caso de que no, avisa al cliente
  public void gestionEliminar(Ingrediente ingrediente){
    if ((ingrediente != null) && (ingredientes.containsKey(ingrediente))){
      ingredientes.remove(ingrediente);
    } else if((ingrediente != null)){
      System.out.println("El elemento no está en la canasta");
    } else{
      System.out.println("Hay un problema, no se puede eliminar un objeto nulo");
    }
  }
  public void gestionEliminar(Producto producto){
    if ((producto != null) && (productos.containsKey(producto))){
      productos.remove(producto);
    } else if((producto != null)){
      System.out.println("El elemento no está en la canasta");
    } else{
      System.out.println("Hay un problema, no se puede eliminar un objeto nulo");
    }
  }
}

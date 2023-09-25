package gestion;
import comida.Ingrediente;
import comida.Producto;
import java.util.HashMap;
import java.util.Map;

public class Canasta {
  private Map<Producto, Integer> productos = new HashMap<Producto, Integer>();
  private Map<Ingrediente, Integer> ingredientes = new HashMap<Ingrediente, Integer>();
  private float costo;

  //getters y setters de los atributos
  public Map<Producto, Integer> getProductos() {return productos;}
  public void setProductos(Map<Producto, Integer> productos) {this.productos = productos;}
  public Map<Ingrediente, Integer> getIngredientes() {return ingredientes;}
  public void setIngredientes(Map<Ingrediente, Integer> ingredientes) {this.ingredientes = ingredientes;}
  public double getCosto() {return costo;}
  public void setCosto(float costo) {this.costo = costo;}

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

  //Método para calcular el costo de la canasta
  public float cuponProductos(Producto prdcto, int cnt){
    float multiplicadorDescuento=1.0f;
    //Si la cantidad es mayor a 3 se aplica descuento del 10%
    if(cnt>=3){
      multiplicadorDescuento=multiplicadorDescuento-0.1f;
    }
    //si el producto se encuentra en el array de productos con descuento de Panadería se le hace descuento del 20%
    if(Panaderia.getProductosEnDescuento().contains(prdcto)){
      multiplicadorDescuento=multiplicadorDescuento-0.2f;
    }
    return multiplicadorDescuento;
  }

  //Método para generar el costo de la canasta, verifica el precio de cada producto y los suma
  public float generarCosto(){
    float costoCanasta=0;
    for (Map.Entry<Producto, Integer> productoEntry : productos.entrySet()) {
      Producto producto = productoEntry.getKey();
      Integer cantidad = productoEntry.getValue();
      float descuento=cuponProductos(producto, cantidad);
      costoCanasta+=producto.getCosto()*cantidad*descuento;
    }
    for (Map.Entry<Ingrediente, Integer> ingredienteEntry : ingredientes.entrySet()) {
      Ingrediente ingrediente = ingredienteEntry.getKey();
      Integer cantidad = ingredienteEntry.getValue();
      costoCanasta+=ingrediente.getPrecio()*cantidad;
    }
    this.costo=costoCanasta;
    return this.costo;
  }

  //Método para productos personalizados
  public void crearProductoPersonalizado(){}
}

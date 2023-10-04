package gestion;

import comida.Ingrediente;
import comida.Producto;
import gestion.Cupon.DescuentoPorCantidad;
import gestion.Cupon.DescuentoProducto;
import humanos.Cliente;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Canasta {
  private Map<Producto, Integer> productos = new HashMap<Producto, Integer>();
  private Map<Ingrediente, Integer> ingredientes = new HashMap<Ingrediente, Integer>();

  private Map<String, Integer> productosEnLista = new HashMap<String, Integer>();
  private Map<String, Integer> ingredientesEnLista = new HashMap<String, Integer>();

  private String identificador;
  private double costo;
  private double descuento;

  // Constructor Canasta
  public Canasta(String identificador, Map<Producto, Integer> productos, Map<Ingrediente, Integer> ingredientes) { 
    this.identificador = identificador;
    this.productos = productos;
    this.ingredientes = ingredientes;
    this.costo = generarCosto();
  }
  public Canasta(Map<Producto, Integer> productos, Map<Ingrediente, Integer> ingredientes) { 
    this.productos = productos;
    this.ingredientes = ingredientes;
    this.costo = generarCosto();
  }

  // getters y setters de los atributos
  public Map<Producto, Integer> getProductos() {
    return productos;
  }

  public void setProductos(Map<Producto, Integer> productos) {
    this.productos = productos;
  }

  public Map<Ingrediente, Integer> getIngredientes() {
    return ingredientes;
  }

  public void setIngredientes(Map<Ingrediente, Integer> ingredientes) {
    this.ingredientes = ingredientes;
  }

  public double getCosto() {
    return costo;
  }

  public void setCosto(float costo) {
    this.costo = costo;
  }

  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  public double getDescuento() {
    return descuento;
  }

  public void setDescuento(float descuento) {
    this.descuento = descuento;
  }

  // Metodos para agregar y eliminar productos a los maps de la canasta
  public void agregarProducto(Producto producto) {
    gestionAgregar(producto, 1);
    this.costo=generarCosto();
  }

  public void agregarProducto(Producto producto, int cantidad) {
    gestionAgregar(producto, cantidad);
    this.costo=generarCosto();
  }

  public void eliminarProducto(Producto producto) {
    gestionEliminar(producto);
    this.costo=generarCosto();
  }

  public void agregarIngrediente(Ingrediente ingrediente) {
    gestionAgregar(ingrediente, 1);
    this.costo=generarCosto();
  }

  public void agregarIngrediente(Ingrediente ingrediente, int cantidad) {
    gestionAgregar(ingrediente, cantidad);
    this.costo=generarCosto();
  }

  public void eliminarIngrediente(Ingrediente ingrediente) {
    gestionEliminar(ingrediente);
    this.costo=generarCosto();
  }

  public void agregarKit(Producto producto) {
    Map<Ingrediente, Integer> ingrdtsProducto = producto.getIngredientes();
    ingrdtsProducto.forEach((ingrdts, cantidad) -> gestionAgregar(ingrdts, cantidad));
    this.costo=generarCosto();
  }
  // No veo positivo la funcion de eliminarKit, de la manera como está planteado
  // generaría demasiados problemas
  // public void eliminarKit(Producto producto){}

  // Metodos que gestionan correctamente la modificacion de los maps
  // Verifican que efectivamente el elemento no exista en el map antes de
  // agregarlo, en el caso de que si, simplemente agrega una unidad más

  //Para productos
  private void gestionAgregar(Producto producto, int elementNum) {
    if ((producto != null) && (!productos.containsKey(producto))) {
      productos.put(producto, elementNum);
    } else if ((producto != null)) {
      productos.put(producto, productos.get(producto) + elementNum);
    } 
  }

  //Para ingredientes
  private void gestionAgregar(Ingrediente ingrediente, int elementNum) {
    if ((ingrediente != null) && (!ingredientes.containsKey(ingrediente))) {
      ingredientes.put(ingrediente, elementNum);
    } else if ((ingrediente != null)) {
      ingredientes.put(ingrediente, ingredientes.get(ingrediente) + elementNum);
    } 
  }

  //Para productos en lista
  private void gestionAgregar(String prdct, int elementNum,String iD) {
    if ((prdct != null) && (!productosEnLista.containsKey(prdct))) {
      productosEnLista.put(prdct, elementNum);
    } else if ((prdct != null)) {
      productosEnLista.put(prdct, productosEnLista.get(prdct) + elementNum);
    } 
  }

  //Para ingredientes en lista
  private void gestionAgregar(String ingrd, int elementNum,int iD) {
    if ((ingrd != null) && (!ingredientesEnLista.containsKey(ingrd))) {
      ingredientesEnLista.put(ingrd, elementNum);
    } else if ((ingrd != null)) {
      ingredientesEnLista.put(ingrd, ingredientesEnLista.get(ingrd) + elementNum);
    } 
  }

  //Para agregar strings a una lista enviada
  public Map<String, Integer> gestionAgregar(String ingrd, int elementNum,Map<String, Integer> lista) {
    if ((ingrd != null) && (!lista.containsKey(ingrd))) {
      lista.put(ingrd, elementNum);
    } else if ((ingrd != null)) {
      lista.put(ingrd, lista.get(ingrd) + elementNum);
    }
    return lista;
  }

  // Verifican que efectivamente el elemento exista en el map antes de eliminarlo,
  // en el caso de que no, avisa al cliente
  //Para productos
  private boolean gestionEliminar(Ingrediente ingrediente) {
    if ((ingrediente != null) && (ingredientes.containsKey(ingrediente))) {
      ingredientes.remove(ingrediente);
      return true;
    } else {return false;}
  }

  //Para ingredientes
  private boolean gestionEliminar(Producto producto) {
    if ((producto != null) && (productos.containsKey(producto))) {
      productos.remove(producto);
      return true;
    } else {return false;}
  }

  //Para productos en lista
  private boolean gestionEliminar(String prdct, String iD) {
    if ((prdct != null) && (productosEnLista.containsKey(prdct))) {
      productosEnLista.remove(prdct);
      return true;
    } else{return false;}
  }

  //Para ingredientes en lista
  private boolean gestionEliminar(String ingrd, int iD) {
    if ((ingrd != null) && (ingredientesEnLista.containsKey(ingrd))) {
      ingredientesEnLista.remove(ingrd);
      return true;
    } else {return false;}
  }

  /**
   * Calcula el multiplicador de descuento para un producto y cantidad determinados en función de las reglas de descuento definidas en los enums DescuentoPorCantidad y DescuentoProducto.
   * @param prdcto el producto para el cual se está calculando el multiplicador de descuento
   * @param cnt la cantidad del producto para la cual se está calculando el multiplicador de descuento
   * @return el multiplicador de descuento que se aplicará al precio del producto
   */
  public double cuponProductos(Producto prdcto, int cnt) {
    double multiplicadorDescuento = 1.0;
    // Si la cantidad cumple los requisitos se le aplica el descuento del enum
    if (cnt >= 8) {
      multiplicadorDescuento-=DescuentoPorCantidad.OCHO.getValor();
    }
    else if (cnt >= 5) {
      multiplicadorDescuento-=DescuentoPorCantidad.CINCO.getValor();
    }
    else if (cnt >= 3) {
      multiplicadorDescuento-=DescuentoPorCantidad.TRES.getValor();
    }

    // si el producto se encuentra dentro de los productos en oferta del enum se aplica el descuento
    for (DescuentoProducto producto : DescuentoProducto.values()) {
      if (producto.getProducto().equals(prdcto.getNombre())) {
        multiplicadorDescuento-=producto.getValor();
      }
    }
    return multiplicadorDescuento;
  }

  /**
   * Calcula el costo total de la canasta, teniendo en cuenta cualquier descuento aplicado por cupones.
   * El costo se calcula sumando el costo de todos los productos en la canasta, multiplicado por su cantidad y cualquier descuento aplicable,
   * y sumando el costo de todos los ingredientes en la canasta, multiplicado por su cantidad.
   * @return el costo total de la canasta
   */
  public double generarCosto() {
    double costoCanasta = 0;
    double descuentoCanasta = 0.0f;
    for (Map.Entry<Producto, Integer> productoEntry : productos.entrySet()) {
      Producto producto = productoEntry.getKey();
      Integer cantidad = productoEntry.getValue();
      double descuento = cuponProductos(producto, cantidad);
      costoCanasta+= producto.getCosto() * cantidad * descuento;
      descuentoCanasta+= producto.getCosto()*cantidad *(1-descuento);
    }
    for (Map.Entry<Ingrediente, Integer> ingredienteEntry : ingredientes.entrySet()) {
      Ingrediente ingrediente = ingredienteEntry.getKey();
      Integer cantidad = ingredienteEntry.getValue();
      costoCanasta += ingrediente.getPrecio() * cantidad;
    }
    this.descuento=descuentoCanasta;
    return costoCanasta;
  }

  /**
   * Recibe una orden para un producto o ingrediente y lo agrega a la canasta.
   * @param objetoEntrante el nombre del producto o ingrediente a agregar.
   * @param cantidad la cantidad del producto o ingrediente a agregar.
   * @return true si el producto o ingrediente se agregó correctamente, false en caso contrario.
   */
  public boolean recibirOrden(String objetoEntrante, String cantidad) {
    if (Panaderia.verificarExistenciaProductoPorNombre(objetoEntrante)) {
      gestionAgregar(objetoEntrante, Integer.parseInt(cantidad), "1");
      return true;
    } else if (Panaderia.verificarExistenciaIngredientePorNombre(objetoEntrante)) {
      gestionAgregar(objetoEntrante, Integer.parseInt(cantidad), 1);
      return true;
    } 
    return false;
  }

  /**
   * Recibe una orden personalizada y la agrega a la canasta si el producto existe.
   * @param objetoEntrante El nombre del producto personalizado.
   * @param ingredientesNecesarios Un mapa que contiene los ingredientes necesarios y sus cantidades para el producto personalizado.
   * @param cantidad La cantidad del producto personalizado que se agregará a la canasta.
   * @return True si el producto personalizado se agregó a la canasta, false en caso contrario.
   */
  public boolean recibirOrdenPersonalizada(String objetoEntrante,  Map<String, Integer> ingredientesNecesarios, String cantidad) {
    try {
      Panaderia.crearProductoPersonalizado(objetoEntrante, ingredientesNecesarios);
    }
    catch (Exception e){
      return false;
    }
    if (Panaderia.verificarExistenciaProductoPorNombre(objetoEntrante)) {
        gestionAgregar(objetoEntrante, Integer.parseInt(cantidad), "1");
        return true;
      }
      else{
        return false;
      }
  }

  /**
   * Envía el pedido de la canasta a panadería para cocinar los productos y agregar los ingredientes.
   * Luego se añaden a la cesta los productos cocinados y los ingredientes añadidos.
   * Por último, las listas de productos e ingredientes se establecen como nulas.
   */
  public void enviarOrdenCanasta() {
    Map<Producto, Integer> productosCocinados = Panaderia.cocinar(productosEnLista);
    Map<Ingrediente, Integer> ingredientesCocinados = Panaderia.agregarIngredientesACanasta(ingredientesEnLista);
    productosCocinados.forEach((producto, cantidad) -> agregarProducto(producto, cantidad));
    ingredientesCocinados.forEach((ingrediente, cantidad) -> agregarIngrediente(ingrediente, cantidad));
    productosEnLista=null;
    ingredientesEnLista=null;
  }

}
package gestorAplicación.gestion;

import gestorAplicación.comida.Ingrediente;
import gestorAplicación.comida.Producto;
import gestorAplicación.gestion.Cupon.DescuentoPorCantidad;
import gestorAplicación.gestion.Cupon.DescuentoProducto;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Esta clase representa una canasta que puede contener productos e ingredientes.
 * Tiene métodos para agregar y eliminar productos e ingredientes, así como para calcular su costo.
 * También tiene atributos para un identificador único, costo y descuento.
 * La clase tiene tres constructores, uno que recibe un identificador, un mapa de productos y un mapa de ingredientes,
 * otro que recibe solo un mapa de productos y un mapa de ingredientes, y un tercero que recibe una lista de canastas,
 * un cliente y una dirección de entrega para calcular el costo total y aplicar un descuento.
 */
public class Canasta implements Serializable {
  private Map<Producto, Integer> productos = new HashMap<Producto, Integer>();
  private Map<Ingrediente, Integer> ingredientes = new HashMap<Ingrediente, Integer>();
  private Map<String, ArrayList<Object>> kits = new HashMap<String, ArrayList<Object>>();
  
  private Map<String, Integer> productosEnLista = new HashMap<String, Integer>();
  private Map<String, Integer> ingredientesEnLista = new HashMap<String, Integer>();
  private Map<String, Integer> kitsEnLista = new HashMap<String, Integer>();

  private String identificador;
  private int itemsTotalesEnCanasta;
  private int itemsTotalesEnLista;
  private double costo;
  private double descuento;

  // Constructor Canasta
  public Canasta() {
    this.productos = new HashMap<Producto, Integer>();
    this.ingredientes = new HashMap<Ingrediente, Integer>();
    this.kits = new HashMap<String, ArrayList<Object>>();
    this.identificador = "";
    this.itemsTotalesEnCanasta = 0;
    this.costo = 0;
    this.descuento = 0;
  }
  public Canasta(String identificador, Map<Producto, Integer> productos, Map<Ingrediente, Integer> ingredientes) { 
    this.identificador = identificador;
    this.productos = productos;
    this.ingredientes = ingredientes;
    this.costo = generarCosto();
    calcularElementosCanasta();
  }
  public Canasta(Map<Producto, Integer> productos, Map<Ingrediente, Integer> ingredientes) { 
    this.productos = productos;
    this.ingredientes = ingredientes;
    this.costo = generarCosto();
    calcularElementosCanasta();
  }
  
  //TODO modificar el constructor, así no funciona canasta
  /* 
  public Canasta(ArrayList<Canasta> canastas, Direccion direccion, Cliente cliente) {
    double costo = 0;
    for (Canasta canasta : canastas) {
      costo += canasta.getCosto();
    }
    Random rand = new Random();
    this.identificador = Integer.toString(rand.nextInt(1000));
    this.productosEnLista = new HashMap<String, Integer>();
    this.ingredientesEnLista = new HashMap<String, Integer>();
    this.costo = costo;
    this.descuento = cliente.getTipoDescuento().getDescuento();
  }
  */
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

  public Map<String, ArrayList<Object>> getKits() {
    return kits;
  }

  public void setKits(Map<String, ArrayList<Object>> kits) {
    this.kits = kits;
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

  public int getitemsTotalesEnCanasta() {
    return itemsTotalesEnCanasta;
  }

  public void setitemsTotalesEnCanasta(int itemsTotalesEnCanasta) {
    this.itemsTotalesEnCanasta = itemsTotalesEnCanasta;
  }

  // Metodos para agregar y eliminar productos a los maps de la canasta
  public void agregarProducto(Producto producto) {
    gestionAgregar(producto, 1);
    calcularElementosCanasta();
    this.costo=generarCosto();
  }

  public void agregarProducto(Producto producto, int cantidad) {
    gestionAgregar(producto, cantidad);
    calcularElementosCanasta();
    this.costo=generarCosto();
  }

  public void eliminarProducto(Producto producto) {
    gestionEliminar(producto);
    calcularElementosCanasta();
    this.costo=generarCosto();
  }

  public void agregarIngrediente(Ingrediente ingrediente) {
    gestionAgregar(ingrediente, 1);
    calcularElementosCanasta();
    this.costo=generarCosto();
  }

  public void agregarIngrediente(Ingrediente ingrediente, int cantidad) {
    gestionAgregar(ingrediente, cantidad);
    calcularElementosCanasta();
    this.costo=generarCosto();
  }

  public void eliminarIngrediente(Ingrediente ingrediente) {
    gestionEliminar(ingrediente);
    calcularElementosCanasta();
    this.costo=generarCosto();
  }

  public void agregarKit(String nombreKit, int cantidad) {
    Map<Ingrediente, Integer> productosKit = new HashMap<Ingrediente, Integer>();
    ArrayList<Object> kitsIngredienteCantidad = new ArrayList<Object>();
    for (Map.Entry<Producto, Integer> entry : Panaderia.getInvProductos().entrySet()) {
      Producto p = entry.getKey();
      if (p.getNombre().equals(nombreKit)) {
        productosKit=p.getIngredientes();
      }
    }
    if ((nombreKit != null) && (!kits.containsKey(nombreKit))) {
      kitsIngredienteCantidad.add(productosKit);
      kitsIngredienteCantidad.add(cantidad);
      getKits().put(nombreKit, kitsIngredienteCantidad);
    } else if ((nombreKit != null)) {
      kitsIngredienteCantidad.add(productosKit);
      kitsIngredienteCantidad.add(cantidad+(Integer)kits.get(nombreKit).get(1));
      kits.put(nombreKit, kitsIngredienteCantidad);
    } 
    calcularElementosCanasta();
    this.costo=generarCosto();
  } 

  public void agregarKit(String nombreKit, ArrayList<Object> listaIngredienteCantidad){
    if ((nombreKit != null) && (!kits.containsKey(nombreKit))) {
      getKits().put(nombreKit, listaIngredienteCantidad);
    } else if ((nombreKit != null)) {
      listaIngredienteCantidad.set(1, (Integer)listaIngredienteCantidad.get(1)+(Integer)kits.get(nombreKit).get(1));
      kits.put(nombreKit, listaIngredienteCantidad);
    } 
    calcularElementosCanasta();
    this.costo=generarCosto();
  }

  public void eliminarKit(String nombreKit, int cantidad) {
    for (Map.Entry<String, ArrayList<Object>> entry : getKits().entrySet()) {
      String nombre = entry.getKey();
      ArrayList<Object> valor = entry.getValue();
      int cantidadActual = 0;
      if (nombre.equals(nombreKit)) {
        cantidadActual = (Integer) valor.get(1);
      }
      if(cantidadActual-cantidad>0 && cantidadActual!=0){
        valor.set(1, cantidadActual-cantidad);
      }
      else if(cantidadActual-cantidad==0 && cantidadActual!=0){
        getKits().remove(nombreKit);
      }
    }
    calcularElementosCanasta();
    this.costo=generarCosto();
  }

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

  private void gestionAgregar(String kits, int elementNum,boolean iD) {
    if ((kits != null) && (!kitsEnLista.containsKey(kits))) {
      kitsEnLista.put(kits, elementNum);
    } else if ((kits != null)) {
      kitsEnLista.put(kits, kitsEnLista.get(kits) + elementNum);
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
  //TODO: cambiar para que pueda eliminar una cierta cantidad
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

  public void calcularElementosCanasta() {
    int elementos = 0;
    for (Map.Entry<Producto, Integer> productoEntry :this.productos.entrySet()) {
      elementos+=productoEntry.getValue();
    }
    for (Map.Entry<Ingrediente, Integer> ingredienteEntry : this.ingredientes.entrySet()) {
      elementos+=ingredienteEntry.getValue();
    }
    for (Map.Entry<String, ArrayList<Object>> entry : this.kits.entrySet()) {
      ArrayList<Object> valor = entry.getValue();
      elementos+=(Integer)valor.get(1);
    }
    this.itemsTotalesEnCanasta=elementos;
  }

  public void calcularElementosLista() {
    int elementos = 0;
    for (Map.Entry<String, Integer> productoEntry :this.productosEnLista.entrySet()) {
      elementos+=productoEntry.getValue();
    }
    for (Map.Entry<String, Integer> ingredienteEntry : this.ingredientesEnLista.entrySet()) {
      elementos+=ingredienteEntry.getValue();
    }
    for (Map.Entry<String, Integer> entry : this.kitsEnLista.entrySet()) {
      elementos+=entry.getValue();
    }
    this.itemsTotalesEnLista=elementos;
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
      costoCanasta += ingrediente.getPrecioDeVenta() * cantidad;
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
  public boolean recibirOrden(String objetoEntrante, String cantidad, boolean receta) {
    if (Panaderia.verificarExistenciaProductoPorNombre(objetoEntrante)) {
      if (receta){
        gestionAgregar(objetoEntrante, Integer.parseInt(cantidad), true);
        calcularElementosLista();
      } else{
        gestionAgregar(objetoEntrante, Integer.parseInt(cantidad), "1");
        calcularElementosLista();
      }
      return true;
    } else if (Panaderia.verificarExistenciaIngredientePorNombre(objetoEntrante) && !receta) {
      gestionAgregar(objetoEntrante, Integer.parseInt(cantidad), 1);
      calcularElementosLista();
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
  public boolean recibirOrdenPersonalizada(String objetoEntrante,  Map<String, Integer> ingredientesNecesarios, String cantidad, boolean receta) {
    try {
      Panaderia.crearProductoPersonalizado(objetoEntrante, ingredientesNecesarios);
    }
    catch (Exception e){
      return false;
    }
    if (Panaderia.verificarExistenciaProductoPorNombre(objetoEntrante)) {
      if (receta){
        gestionAgregar(objetoEntrante, Integer.parseInt(cantidad), true);
        calcularElementosLista();
      } else{
        gestionAgregar(objetoEntrante, Integer.parseInt(cantidad), "1");
        calcularElementosLista();
      }
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
    Map<String, ArrayList<Object>> kitsCocinados = Panaderia.agregarKitsACanasta(kitsEnLista);
    productosCocinados.forEach((producto, cantidad) -> agregarProducto(producto, cantidad));
    ingredientesCocinados.forEach((ingrediente, cantidad) -> agregarIngrediente(ingrediente, cantidad));
    kitsCocinados.forEach((nombre, ingredienteCantidad) -> agregarKit(nombre, ingredienteCantidad));
    productosEnLista=null;
    ingredientesEnLista=null;
    kitsCocinados=null;
  }
}
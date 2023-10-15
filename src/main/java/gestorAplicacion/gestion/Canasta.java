package gestorAplicacion.gestion;

import java.util.HashMap;
import java.util.Map;

import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Esta clase representa una canasta de compras que puede contener productos, ingredientes y kits.
 * También realiza un seguimiento del costo total, descuento y número de elementos en la canasta.
 * Además, también puede funcionar como una lista de compras, realizando un seguimiento de los elementos a comprar.
 * La clase proporciona métodos para agregar elementos a la canasta y lista, así como getters y setters para sus atributos.
**/
public class Canasta implements Serializable {

  private ArrayList<Producto> productos = new ArrayList<Producto>();
  private ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
  private ArrayList<ArrayList<Ingrediente>> kits = new ArrayList<ArrayList<Ingrediente>>();
  
  private HashMap<String, Integer> productosEnLista = new HashMap<String, Integer>();
  private HashMap<String, Integer> ingredientesEnLista = new HashMap<String, Integer>();
  private HashMap<String, Integer> kitsEnLista = new HashMap<String, Integer>();

  private String identificador;
  private int itemsTotalesEnCanasta;
  private int itemsTotalesEnLista;
  private double costoTotalEnLista;
  private double costoTrasDescuentoEnLista;
  private double descuentoEnLista;

  // Constructores Canasta
  public Canasta() {
    this.productos = new ArrayList<Producto>();
    this.ingredientes = new ArrayList<Ingrediente>();
    this.kits = new ArrayList<ArrayList<Ingrediente>>();
    this.identificador = "";
    this.itemsTotalesEnCanasta = 0;
    calcularElementosCanasta();
  }

  public Canasta(ArrayList<Producto> productos, ArrayList<Ingrediente> ingredientes, ArrayList<ArrayList<Ingrediente>> kits) { 
    this.productos = productos;
    this.ingredientes = ingredientes;
    this.kits = kits;
    calcularElementosCanasta();
  }

  public Canasta(HashMap<String, Integer> productosEnLista, HashMap<String, Integer> ingredientesEnLista, HashMap<String, Integer> kitsEnLista) { 
    this.productosEnLista = productosEnLista;
    this.ingredientesEnLista = ingredientesEnLista;
    this.kitsEnLista = kitsEnLista;
    calcularElementosCanasta();
  }
  
  // getters y setters de los atributos
  public ArrayList<Producto> getProductos() {
    return productos;
  }

  public void setProductos(ArrayList<Producto> productos) {
    this.productos = productos;
  }

  public ArrayList<Ingrediente> getIngredientes() {
    return ingredientes;
  }

  public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
    this.ingredientes = ingredientes;
  }

  public ArrayList<ArrayList<Ingrediente>> getKits() {
    return kits;
  }

  public void setKits(ArrayList<ArrayList<Ingrediente>> kits) {
    this.kits = kits;
  }

  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  public int getitemsTotalesEnCanasta() {
    return itemsTotalesEnCanasta;
  }

  public void setitemsTotalesEnCanasta(int itemsTotalesEnCanasta) {
    this.itemsTotalesEnCanasta = itemsTotalesEnCanasta;
  }

  public int getitemsTotalesEnLista() {
    return itemsTotalesEnLista;
  }

  public void setitemsTotalesEnLista(int itemsTotalesEnLista) {
    this.itemsTotalesEnLista = itemsTotalesEnLista;
  }

  public double getCostoTotalEnLista() {
    return costoTotalEnLista;
  }

  public void setCostoTotalEnLista(double costoTotalEnLista) {
    this.costoTotalEnLista = costoTotalEnLista;
  }

  public double getCostoTrasDescuentoEnLista() {
    return costoTrasDescuentoEnLista;
  }

  public void setCostoTrasDescuentoEnLista(double costoTrasDescuentoEnLista) {
    this.costoTrasDescuentoEnLista = costoTrasDescuentoEnLista;
  }

  public double getDescuentoEnLista() {
    return descuentoEnLista;
  }

  public void setDescuentoEnLista(double descuentoEnLista) {
    this.descuentoEnLista = descuentoEnLista;
  }

  public HashMap<String, Integer> getProductosEnLista() {
	return productosEnLista;
  }
  
  public void setProductosEnLista(HashMap<String, Integer> productosEnLista) {
	this.productosEnLista = productosEnLista;
  }
  
  public HashMap<String, Integer> getIngredientesEnLista() {
	return ingredientesEnLista;
  }

  public void setIngredientesEnLista(HashMap<String, Integer> ingredientesEnLista) {
	this.ingredientesEnLista = ingredientesEnLista;
  }

  public HashMap<String, Integer> getKitsEnLista() {
	return kitsEnLista;
  }

  public void setKitsEnLista(HashMap<String, Integer> kitsEnLista) {
	this.kitsEnLista = kitsEnLista;
  }

  public int getItemsTotalesEnCanasta() {
	return itemsTotalesEnCanasta;
  }

  public void setItemsTotalesEnCanasta(int itemsTotalesEnCanasta) {
	this.itemsTotalesEnCanasta = itemsTotalesEnCanasta;
  }

  public int getItemsTotalesEnLista() {
	return itemsTotalesEnLista;
  }

  public void setItemsTotalesEnLista(int itemsTotalesEnLista) {
	this.itemsTotalesEnLista = itemsTotalesEnLista;
  }

  // Metodos que gestionan correctamente la modificacion de los maps
  // Verifican que efectivamente el elemento no exista en el map antes de
  // agregarlo, en el caso de que si, simplemente agrega la cantidad indicada a la que ya había

  //Para productos
  private void gestionAgregarP(ArrayList<Producto> productos) {
    for (Producto p : productos) {
      if (p != null) {
        productos.add(p);
      }
    }
    calcularElementosCanasta();
  }

  //Para ingredientes
  private void gestionAgregarI(ArrayList<Ingrediente> ingredientes) {
    for (Ingrediente i : ingredientes) {
      if (i != null) {
        ingredientes.add(i);
      }
    }
    calcularElementosCanasta();
  }

  //Para kits
  private void gestionAgregarK(ArrayList<ArrayList<Ingrediente>> kits) {
    for (ArrayList<Ingrediente> kit : kits) {
      if (kit != null) {
        kits.add(kit);
      }
    }
    calcularElementosCanasta();
  }

  //Para productos en lista
  private void gestionAgregar(String prdct, int elementNum,String iD) {
    if ((prdct != null) && (!productosEnLista.containsKey(prdct))) {
      productosEnLista.put(prdct, elementNum);
    } else if ((prdct != null)) {
      productosEnLista.put(prdct, productosEnLista.get(prdct) + elementNum);
    } 
    calcularElementosLista();
    generarCostoDeOrden();
  }

  //Para ingredientes en lista
  private void gestionAgregar(String ingrd, int elementNum,int iD) {
    if ((ingrd != null) && (!ingredientesEnLista.containsKey(ingrd))) {
      ingredientesEnLista.put(ingrd, elementNum);
    } else if ((ingrd != null)) {
      ingredientesEnLista.put(ingrd, ingredientesEnLista.get(ingrd) + elementNum);
    } 
    calcularElementosLista();
    generarCostoDeOrden();
  }

  //Para kits en lista
  private void gestionAgregar(String kit, int elementNum, boolean iD) {
    if ((kit != null) && (!kitsEnLista.containsKey(kit))) {
      kitsEnLista.put(kit, elementNum);
    } else if ((kit != null)) {
      kitsEnLista.put(kit, kitsEnLista.get(kit) + elementNum);
    }
    calcularElementosLista();
    generarCostoDeOrden();
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

  //Para productos en lista
  private boolean gestionEliminar(String prdct, int cantidad, String iD) {
    if ((prdct != null) && (productosEnLista.containsKey(prdct))) {
      if(productosEnLista.get(prdct)+cantidad>0){
        productosEnLista.put(prdct, productosEnLista.get(prdct)+cantidad);
        calcularElementosLista();
        generarCostoDeOrden();
        return true;
      }
      else if(productosEnLista.get(prdct)+cantidad==0){
        productosEnLista.remove(prdct);
        calcularElementosLista();
        generarCostoDeOrden();
        return true;
      }
      else if(productosEnLista.get(prdct)+cantidad<0){
        return false;
      }
    } else {return false;}
    return false;
  }

  //Para ingredientes en lista
  private boolean gestionEliminar(String ingrd, int cantidad, int iD) {
    if ((ingrd != null) && (ingredientesEnLista.containsKey(ingrd))) {
      if(ingredientesEnLista.get(ingrd)+cantidad>0){
        ingredientesEnLista.put(ingrd, ingredientesEnLista.get(ingrd)+cantidad);
        calcularElementosLista();
        generarCostoDeOrden();
        return true;
      }
      else if(ingredientesEnLista.get(ingrd)+cantidad==0){
        ingredientesEnLista.remove(ingrd);
        calcularElementosLista();
        generarCostoDeOrden();
        return true;
      }
      else if(ingredientesEnLista.get(ingrd)+cantidad<0){
        return false;
      }
    } else {return false;}
    return false;
  }

  //Para kits en lista
  private boolean gestionEliminar(String kit, int cantidad, boolean iD) {
    if ((kit != null) && (kitsEnLista.containsKey(kit))) {
      if(kitsEnLista.get(kit)+cantidad>0){
        kitsEnLista.put(kit, kitsEnLista.get(kit)+cantidad);
        calcularElementosLista();
        generarCostoDeOrden();
        return true;
      }
      else if(kitsEnLista.get(kit)+cantidad==0){
        kitsEnLista.remove(kit);
        calcularElementosLista();
        generarCostoDeOrden();
        return true;
      }
      else if(kitsEnLista.get(kit)+cantidad<0){
        return false;
      }
    } else {return false;}
    return false;
  }

  /**
   * Calcula el número total de elementos en la canasta sumando las cantidades de productos, ingredientes y kits.
   * Actualiza el valor de itemsTotalesEnCanasta.
   */
  public void calcularElementosCanasta() {
    int elementos = 0;
    for(Producto producto : productos){
      elementos+=1;
    }
    for(Ingrediente ingrediente : ingredientes){
      elementos+=1;
    }
    for(ArrayList<Ingrediente> kit : kits){
      elementos+=1;
    }
    this.itemsTotalesEnCanasta=elementos;
  }
  /**
   * Calcula el número total de elementos en la canasta sumando las cantidades de productosEnLista, ingredientesEnLista y kitsEnLista.
   */
  public void calcularElementosLista() {
    int elementos = 0;
    if(productosEnLista!=null){
      for (Map.Entry<String, Integer> productoEntry :this.productosEnLista.entrySet()) {
        elementos+=productoEntry.getValue();
      }
    }
    if(ingredientesEnLista!=null){
      for (Map.Entry<String, Integer> ingredienteEntry : this.ingredientesEnLista.entrySet()) {
        elementos+=ingredienteEntry.getValue();
      }
    }
    if(kitsEnLista!=null){
      for (Map.Entry<String, Integer> entry : this.kitsEnLista.entrySet()) {
        elementos+=entry.getValue();
      }
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
   * Calcula el costo total del pedido, incluyendo descuentos, para los elementos actuales en la canasta.
   * El costo se calcula en función de la cantidad de cada producto, ingrediente o kit en la canasta.
   * Los descuentos se aplican en función de los cupones disponibles para cada producto.
   * El costo final se almacena en las variables de instancia costoTotalEnLista y costoTrasDescuentoEnLista.
   */
  public void generarCostoDeOrden(){
    double costoCanasta = 0;
    double descuentoCanasta=0.0;
    if(productosEnLista!=null){
      for (Map.Entry<String, Integer> productoEntry : productosEnLista.entrySet()) {
        Producto producto = Producto.obtenerObjetoPorNombre(productoEntry.getKey());
        Integer cantidad = productoEntry.getValue();
        double descuento = cuponProductos(producto, cantidad);
        costoCanasta+= producto.getCosto() * cantidad * descuento;
        descuentoCanasta+= producto.getCosto()*cantidad *(1-descuento);
      }
    }
    if(ingredientesEnLista!=null){
      for (Map.Entry<String, Integer> ingredienteEntry : ingredientesEnLista.entrySet()) {
        Ingrediente ingrediente = Ingrediente.obtenerObjetoPorNombre(ingredienteEntry.getKey());
        Integer cantidad = ingredienteEntry.getValue();
        costoCanasta += ingrediente.getPrecioDeVenta() * cantidad;
      }
    }
    if(kitsEnLista!=null){
      for (Map.Entry<String, Integer> entry : kitsEnLista.entrySet()) {
        Producto producto = Producto.obtenerObjetoPorId(entry.getKey());
        Integer cantidad = entry.getValue();
        costoCanasta += producto.getCosto() * cantidad;
      }
    }
    this.descuentoEnLista=descuentoCanasta;
    this.costoTotalEnLista=costoCanasta+descuentoCanasta;
    this.costoTrasDescuentoEnLista=costoCanasta;
  }

  /**
   * Este método recibe una orden para un producto o ingrediente y lo agrega a la canasta si la cantidad es positiva, o lo elimina si la cantidad es negativa.
   * @param objetoEntrante El id del producto o ingrediente que se va a agregar o eliminar.
   * @param cantidad La cantidad del producto o ingrediente que se va a agregar o eliminar.
   * @param receta Un booleano que indica si la orden es para un kit de receta o no.
   * @return Un booleano que indica si la orden se agregó o eliminó correctamente de la canasta.
   */
  public boolean recibirOrden(String objetoEntrante, String cantidad, boolean receta) {
    if (Integer.parseInt(cantidad) < 0) {
      boolean estado;
      if (Producto.verificacionExistenciaPorId(objetoEntrante)) {
        if (receta){
          estado = gestionEliminar(objetoEntrante, Integer.parseInt(cantidad), true);
        } 
        else{
          estado = gestionEliminar(objetoEntrante, Integer.parseInt(cantidad), "1");
        }
        if (estado){
          return true;
        }
        else{
          return false;
        }
      } 
      else if (Ingrediente.verificacionExistenciaPorId(objetoEntrante) && !receta) {
        estado=gestionEliminar(objetoEntrante, Integer.parseInt(cantidad), 1);
        if (estado){
          return true;
        }
        else{
          return false;
        }
      }
      return false;
    }
    else{
      if (Producto.verificacionExistenciaPorId(objetoEntrante)) {
        if (receta){
          gestionAgregar(objetoEntrante, Integer.parseInt(cantidad),true);
        } 
        else{
          gestionAgregar(objetoEntrante, Integer.parseInt(cantidad),"1");
        }
        return true;
      } 
      else if (Ingrediente.verificacionExistenciaPorId(objetoEntrante) && !receta) {
        gestionAgregar(objetoEntrante, Integer.parseInt(cantidad),1);
        return true;
      } 
    }
    return false;
  }

  /**
   * Recibe una orden personalizada para un producto y sus ingredientes necesarios, y lo agrega a la canasta.
   * @param objetoEntrante El id del producto personalizado.
   * @param ingredientesNecesarios Un mapa que contiene los ingredientes necesarios y sus cantidades para el producto personalizado.
   * @param cantidad La cantidad del producto personalizado a agregar a la canasta.
   * @param receta Un booleano que indica si el producto personalizado es un kit (true) o un producto individual (false).
   * @return Un booleano que indica si el producto personalizado se agregó correctamente a la canasta.
   */
  public boolean recibirOrdenPersonalizada(String objetoEntrante,  HashMap<String, Integer> ingredientesNecesarios, String cantidad, boolean receta) {
    try {
      Producto.crearProductoPersonalizado(objetoEntrante, ingredientesNecesarios);
    }
    catch (Exception e){
      return false;
    }
    if (Producto.verificacionExistenciaPorNombre(objetoEntrante)) {
      if (receta){
        gestionAgregar(Producto.obtenerObjetoPorNombre(objetoEntrante).getId(), Integer.parseInt(cantidad), true);
      } else{
        gestionAgregar(Producto.obtenerObjetoPorNombre(objetoEntrante).getId(), Integer.parseInt(cantidad),"1");
      }
        return true;
    } 
    else{
      return false;
    }
  }
  
  /**
   * Envía la orden de los productos, ingredientes y kits en la canasta a la panadería para ser cocinados y preparados.
   * Los productos, ingredientes y kits cocinados son luego agregados a la canasta.
   * Finalmente, las listas de productos, ingredientes y kits en la canasta se establecen en nulo.
   */
  public void enviarOrdenCanasta() {
  ArrayList<Producto> productosCocinados = Panaderia.agregarProductosACanasta(productosEnLista);
  ArrayList<Ingrediente> ingredientesCocinados = Panaderia.agregarIngredientesACanasta(ingredientesEnLista);
  ArrayList<ArrayList<Ingrediente>> kitsCocinados = Panaderia.agregarKitsACanasta(kitsEnLista);

  gestionAgregarP(productosCocinados);
  gestionAgregarI(ingredientesCocinados);
  gestionAgregarK(kitsCocinados);

  productosEnLista=null;
  ingredientesEnLista=null;
  kitsCocinados=null;
  }

  /**
   * Este enum representa los diferentes productos y sus valores de descuento
   * correspondientes.
   */
  public enum DescuentoProducto {
    PRODUCTO_A("Producto A", 0.1),
    PRODUCTO_B("Producto B", 0.1),
    PRODUCTO_C("Producto C", 0.1),
    PRODUCTO_D("Producto D", 0.1),
    PRODUCTO_E("Producto E", 0.1);

    private final String producto;
    private final double valor;

    DescuentoProducto(String producto, double valor) {
      this.producto = producto;
      this.valor = valor;
    }

    public String getProducto() {
      return producto;
    }

    public double getValor() {
      return valor;
    }
  }

  /**
   * Este enum representa los diferentes valores de descuento que se pueden
   * aplicar a un cupón basado en la cantidad de productos comprados.
   * Los valores de descuento son del 5% para 3 productos, del 10% para 5
   * productos y del 15% para 8 productos.
   */
  public enum DescuentoPorCantidad {
    TRES(0.05),
    CINCO(0.1),
    OCHO(0.15);

    private final double valor;

    DescuentoPorCantidad(double valor) {
      this.valor = valor;
    }

    public double getValor() {
      return valor;
    }
  }

}
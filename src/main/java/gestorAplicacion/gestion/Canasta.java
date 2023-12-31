package gestorAplicacion.gestion;

import java.util.HashMap;
import java.util.Map;

import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.comida.ProductoCaliente;
import gestorAplicacion.comida.ProductoFrio;
import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.humanos.Cliente;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Esta clase representa una canasta de compras que puede contener productos, ingredientes y kits.
 * También realiza un seguimiento del costo total, descuento y número de elementos en la canasta.
 * Además, también puede funcionar como una lista de compras, realizando un seguimiento de los elementos a comprar.
 * La clase proporciona métodos para agregar elementos a la canasta y lista, así como getters y setters para sus atributos.
**/
public class Canasta implements Serializable, Cloneable {

  private ArrayList<Producto> productos = new ArrayList<Producto>();
  private ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
  private ArrayList<ArrayList<Ingrediente>> kits = new ArrayList<ArrayList<Ingrediente>>();
  
  private HashMap<String, Integer> productosEnLista = new HashMap<String, Integer>();
  private HashMap<String, Integer> ingredientesEnLista = new HashMap<String, Integer>();
  private HashMap<String, Integer> kitsEnLista = new HashMap<String, Integer>();

  private String identificador;
  private int itemsTotalesEnCanasta;
  private int itemsTotalesEnLista;
  private double costoTotalEnLista; //subtotal 
  private double costoTrasDescuentoEnLista; //total // total de canasta seria subtotal de factura
  private double descuentoEnLista; //total ahorrado

  private double calificacion;
  private String comentario;
  private boolean pagada;
  public boolean estadoOrden;

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

  public Canasta(ArrayList<Producto> productos, ArrayList<Ingrediente> ingredientes, ArrayList<ArrayList<Ingrediente>> kits, HashMap<String, Integer> productosEnLista, HashMap<String, Integer> ingredientesEnLista, HashMap<String, Integer> kitsEnLista, String identificador, int itemsTotalesEnCanasta, int itemsTotalesEnLista, double costoTotalEnLista, double costoTrasDescuentoEnLista, double descuentoEnLista, double calificacion, String comentario, boolean pagada) {
    this.productos = productos;
    this.ingredientes = ingredientes;
    this.kits = kits;
    this.productosEnLista = productosEnLista;
    this.ingredientesEnLista = ingredientesEnLista;
    this.kitsEnLista = kitsEnLista;
    this.identificador = identificador;
    this.itemsTotalesEnCanasta = itemsTotalesEnCanasta;
    this.itemsTotalesEnLista = itemsTotalesEnLista;
    this.costoTotalEnLista = costoTotalEnLista;
    this.costoTrasDescuentoEnLista = costoTrasDescuentoEnLista;
    this.descuentoEnLista = descuentoEnLista;
    this.calificacion = calificacion;
    this.comentario = comentario;
    this.pagada = pagada;
  }

    public Canasta(HashMap<String, Integer> productosEnLista, HashMap<String, Integer> ingredientesEnLista, HashMap<String, Integer> kitsEnLista, int itemsTotalesEnCanasta, int itemsTotalesEnLista, double costoTotalEnLista, double costoTrasDescuentoEnLista, double descuentoEnLista) {

    this.productosEnLista = productosEnLista;
    this.ingredientesEnLista = ingredientesEnLista;
    this.kitsEnLista = kitsEnLista;
    this.itemsTotalesEnCanasta = itemsTotalesEnCanasta;
    this.itemsTotalesEnLista = itemsTotalesEnLista;
    this.costoTotalEnLista = costoTotalEnLista;
    this.costoTrasDescuentoEnLista = costoTrasDescuentoEnLista;
    this.descuentoEnLista = descuentoEnLista;

  }

  public Canasta(HashMap<String, Integer> productosEnLista, HashMap<String, Integer> ingredientesEnLista, HashMap<String, Integer> kitsEnLista, int itemsTotalesEnCanasta, int itemsTotalesEnLista, double costoTotalEnLista, double costoTrasDescuentoEnLista, double descuentoEnLista, String identificador) {

    this.productosEnLista = productosEnLista;
    this.ingredientesEnLista = ingredientesEnLista;
    this.kitsEnLista = kitsEnLista;
    this.identificador = identificador;
    this.itemsTotalesEnCanasta = itemsTotalesEnCanasta;
    this.itemsTotalesEnLista = itemsTotalesEnLista;
    this.costoTotalEnLista = costoTotalEnLista;
    this.costoTrasDescuentoEnLista = costoTrasDescuentoEnLista;
    this.descuentoEnLista = descuentoEnLista;
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

  public boolean isPagada() {
    return pagada;
  }

  public void setPagada(boolean pagada) {
    this.pagada = pagada;
  }

  public double getCalificacion() {
    return calificacion;
  }

  public void setCalificacion(double calificacion) {
    this.calificacion = calificacion;
  }

  public String getComentario() {
    return comentario;
  }

  public void setComentario(String comentario) {
    this.comentario = comentario;
  }

  // Metodos que gestionan correctamente la modificacion de los maps
  // Verifican que efectivamente el elemento no exista en el map antes de
  // agregarlo, en el caso de que si, simplemente agrega la cantidad indicada a la que ya había

  //Para productos
  /**
   * Agrega una lista de productos a la canasta.
   * @param productos la lista de productos a agregar.
   */
  private void gestionAgregarP(ArrayList<Producto> productos) {
    for (Producto p : productos) {
      if (p != null) {
        this.productos.add(p);
      }
    }
    calcularElementosCanasta();
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  //Para ingredientes
  /**
   * Agrega los ingredientes de la lista dada a la canasta actual.
   * Si un ingrediente es nulo, no se agrega a la canasta.
   * Finalmente, recalcula el número de elementos en la canasta.
   * @param ingredientes La lista de ingredientes a agregar a la canasta.
   */
  private void gestionAgregarI(ArrayList<Ingrediente> ingredientes) {
    for (Ingrediente i : ingredientes) {
      if (i != null) {
        this.ingredientes.add(i);
      }
    }
    calcularElementosCanasta();
  }

  //Para kits
  /**
   * Agrega los kits de ingredientes a la canasta.
   * @param kits Lista de kits de ingredientes a agregar.
   */
  private void gestionAgregarK(ArrayList<ArrayList<Ingrediente>> kits) {
    for (ArrayList<Ingrediente> kit : kits) {
      if (kit != null) {
        this.kits.add(kit);
      }
    }
    calcularElementosCanasta();
  }

  //Para productos en lista
  /**
   * Agrega un producto a la lista de la canasta y actualiza la cantidad de elementos y el costo total de la orden.
   * @param prdct el nombre del producto a agregar.
   * @param elementNum la cantidad de elementos del producto a agregar.
   * @param iD el identificador del producto a agregar.
   */
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
  /**
   * Agrega un ingrediente a la lista de ingredientes de la canasta y actualiza la cantidad de elementos y el costo total de la orden.
   * @param ingrd el nombre del ingrediente a agregar.
   * @param elementNum la cantidad de elementos del ingrediente a agregar.
   * @param iD el identificador del ingrediente a agregar.
   */
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
  /**
   * Agrega un kit a la lista de kits en la canasta y actualiza la cantidad de elementos y el costo de la orden.
   * @param kit el nombre del kit a agregar.
   * @param elementNum la cantidad de elementos del kit a agregar.
   * @param iD un booleano que indica si el kit ya está en la lista o no.
   */
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
  /**
   * Agrega un ingrediente a la lista de la canasta y su cantidad correspondiente.
   * Si el ingrediente ya existe en la lista, se suma la cantidad nueva a la existente.
   * @param ingrd el nombre del ingrediente a agregar
   * @param elementNum la cantidad del ingrediente a agregar
   * @param lista la lista de ingredientes de la canasta
   * @return la lista actualizada de ingredientes de la canasta
   */
  public HashMap<String, Integer> gestionAgregar(String ingrd, int elementNum,HashMap<String, Integer> lista) {
    if ((ingrd != null) && (!lista.containsKey(ingrd))) {
      lista.put(ingrd, elementNum);
    } else if ((ingrd != null)) {
      lista.put(ingrd, lista.get(ingrd) + elementNum);
    }
    return lista;
  }

  //Para productos en lista
  /**
   * Método privado que gestiona la eliminación de un producto de la canasta.
   * @param prdct el nombre del producto a eliminar.
   * @param cantidad la cantidad del producto a eliminar.
   * @param iD el identificador del usuario que realiza la eliminación.
   * @return true si se eliminó el producto correctamente, false en caso contrario.
   */
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
  /**
   * Método privado que gestiona la eliminación de un ingrediente de la canasta.
   * @param ingrd el nombre del ingrediente a eliminar.
   * @param cantidad la cantidad del ingrediente a eliminar.
   * @param iD el ID del ingrediente a eliminar.
   * @return true si se eliminó el ingrediente correctamente, false si no se pudo eliminar.
   */
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
  /**
   * Método privado que se encarga de gestionar la eliminación de un kit de la canasta.
   * @param kit El nombre del kit a eliminar.
   * @param cantidad La cantidad de elementos del kit a eliminar.
   * @param iD Un booleano que indica si se debe eliminar el kit por completo o solo disminuir su cantidad.
   * @return true si se pudo eliminar el kit o disminuir su cantidad, false en caso contrario.
   */
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
    int elementos= productos.size()+ingredientes.size()+kits.size();
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
   * Calcula el costo total de la canasta, incluyendo descuentos si los hay.
   * El costo total se calcula sumando el costo de los productos, ingredientes y kits en la canasta.
   * Si hay descuentos, se calcula el descuento total y se resta del costo total.
   * El costo total tras descuento se calcula sin tener en cuenta los descuentos.
   */
  public void generarCostoDeOrden(){
    double costoCanasta = 0;
    double descuentoCanasta=0.0;
    if(productosEnLista!=null){
      for (Map.Entry<String, Integer> productoEntry : productosEnLista.entrySet()) {
        Producto producto = Producto.obtenerObjetoPorId(productoEntry.getKey());
        Integer cantidad = productoEntry.getValue();
        double descuento = cuponProductos(producto, cantidad);
        costoCanasta+= producto.getCosto() * cantidad * descuento;
        descuentoCanasta+= producto.getCosto()*cantidad *(1-descuento);
      }
    }
    if(ingredientesEnLista!=null){
      for (Map.Entry<String, Integer> ingredienteEntry : ingredientesEnLista.entrySet()) {
        Ingrediente ingrediente = Ingrediente.obtenerObjetoPorId(ingredienteEntry.getKey());
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
   * Método que recibe una orden de agregar o eliminar un producto o ingrediente de la canasta.
   * @param objetoEntrante El objeto que se desea agregar o eliminar de la canasta.
   * @param cantidad La cantidad del objeto que se desea agregar o eliminar de la canasta.
   * @param receta Indica si el objeto es un producto que se desea agregar o eliminar para una receta.
   * @return true si la operación se realizó correctamente, false en caso contrario.
   */
  public String recibirOrden(String objetoEntrante, String cantidad, boolean receta) {
    this.estadoOrden = false;
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
          this.estadoOrden = true;
          return "Se ha restado la cantidad del producto de la canasta";
        }
        else{
          return "No se ha podido restar la cantidad del producto de la canasta";
        }
      } 
      else if (Ingrediente.verificacionExistenciaPorId(objetoEntrante) && !receta) {
        estado=gestionEliminar(objetoEntrante, Integer.parseInt(cantidad), 1);
        if (estado){
          this.estadoOrden = true;
          return "Se ha restado la cantidad del ingrediente de la canasta";
        }
        else{
          return "No se ha podido restar la cantidad del ingrediente de la canasta";
        }
      }
      return "No se ha podido realizar el proceso, vuelva a intentarlo";
    }
    else{
      if (Producto.verificacionExistenciaPorId(objetoEntrante)) {
        if (receta){
          gestionAgregar(objetoEntrante, Integer.parseInt(cantidad),true);
        } 
        else{
          gestionAgregar(objetoEntrante, Integer.parseInt(cantidad),"1");
        }
        this.estadoOrden = true;
        return "Se ha agregado la cantidad del producto a la canasta";
      } 
      else if (Ingrediente.verificacionExistenciaPorId(objetoEntrante) && !receta) {
        gestionAgregar(objetoEntrante, Integer.parseInt(cantidad),1);
        this.estadoOrden = true;
        return "Se ha agregado la cantidad del ingrediente a la canasta";
      } 
    }
    return "No se ha podido realizar el proceso";
  }


  /**
   * Recibe una orden personalizada de un producto y la agrega a la canasta.
   * @param objetoEntrante el nombre del producto personalizado.
   * @param ingredientesNecesarios los ingredientes necesarios para crear el producto personalizado.
   * @param cantidad la cantidad de productos a agregar a la canasta.
   * @param receta indica si la orden personalizada se agrega con la receta o no.
   * @return true si la orden personalizada se agrega correctamente a la canasta, false en caso contrario.
   */
  public boolean recibirOrdenPersonalizada(String objetoEntrante,  HashMap<String, Integer> ingredientesNecesarios, String cantidad, boolean receta) {
    try {
      int randomNum = (int) (Math.floor(Math.random() * 2 + 1));
      if(randomNum ==1){
        ProductoFrio.crearProductoPersonalizado(objetoEntrante, ingredientesNecesarios);
      }
      else{
        ProductoCaliente.crearProductoPersonalizado(objetoEntrante, ingredientesNecesarios);
      }
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
   * Envía la orden de la canasta a la panadería para agregar los productos, ingredientes y kits en la lista de la canasta.
   * Los productos, ingredientes y kits cocinados son agregados a la lista de gestión de la canasta.
   * Luego, se establecen las listas de productos, ingredientes y kits en la canasta como nulas.
   */
  public void enviarOrdenCanasta() {
    ArrayList<Producto> productosCocinados = Cliente.getSesion().getPanaderia().agregarProductosACanasta(productosEnLista);
    ArrayList<Ingrediente> ingredientesCocinados = Cliente.getSesion().getPanaderia().agregarIngredientesACanasta(ingredientesEnLista);
    ArrayList<ArrayList<Ingrediente>> kitsCocinados = Cliente.getSesion().getPanaderia().agregarKitsACanasta(kitsEnLista);

    gestionAgregarP(productosCocinados);
    gestionAgregarI(ingredientesCocinados);
    gestionAgregarK(kitsCocinados);
  }

  /**
   * Este enum representa los diferentes productos y sus valores de descuento
   * correspondientes.
   */
  public enum DescuentoProducto {
    PRODUCTO_A("rollos de canela", 0.1),
    PRODUCTO_B("bunuelo", 0.1),
    PRODUCTO_C("brownie", 0.1),
    PRODUCTO_D("torta de milo", 0.1),
    PRODUCTO_E("cheesecake", 0.1);

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
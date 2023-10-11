package gestorAplicacion.gestion;

import java.util.HashMap;
import java.util.Map;

import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.gestion.Cupon.DescuentoPorCantidad;
import gestorAplicacion.gestion.Cupon.DescuentoProducto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Esta clase representa una canasta de compras que puede contener productos, ingredientes y kits.
 * También realiza un seguimiento del costo total, descuento y número de elementos en la canasta.
 * Además, también puede funcionar como una lista de compras, realizando un seguimiento de los elementos a comprar.
 * La clase proporciona métodos para agregar elementos a la canasta y lista, así como getters y setters para sus atributos.
**/
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
  private double costoTotal;
  private double costoTrasDescuento;
  private double descuento;
  private double costoTotalEnLista;
  private double costoTrasDescuentoEnLista;
  private double descuentoEnLista;

  // Constructores Canasta
  public Canasta() {
    this.productos = new HashMap<Producto, Integer>();
    this.ingredientes = new HashMap<Ingrediente, Integer>();
    this.kits = new HashMap<String, ArrayList<Object>>();
    this.identificador = "";
    this.itemsTotalesEnCanasta = 0;
    this.costoTotal = 0;
    this.costoTrasDescuento = 0;
    this.descuento = 0;
    generarCosto();
    calcularElementosCanasta();
  }
  public Canasta(String identificador, Map<Producto, Integer> productos, Map<Ingrediente, Integer> ingredientes) { 
    this.identificador = identificador;
    this.productos = productos;
    this.ingredientes = ingredientes;
    generarCosto();
    calcularElementosCanasta();
  }
  public Canasta(Map<Producto, Integer> productos, Map<Ingrediente, Integer> ingredientes) { 
    this.productos = productos;
    this.ingredientes = ingredientes;
    generarCosto();
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
    return costoTotal;
  }

  public void setCosto(float costo) {
    this.costoTotal = costo;
  }

  public double getCostoTrasDescuento() {
    return costoTrasDescuento;
  }

  public void setCostoTrasDescuento(float costoTrasDescuento) {
    this.costoTrasDescuento = costoTrasDescuento;
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

  public Map<String, Integer> getProductosEnLista() {
	return productosEnLista;
  }
  
  public void setProductosEnLista(Map<String, Integer> productosEnLista) {
	this.productosEnLista = productosEnLista;
  }
  
  public Map<String, Integer> getIngredientesEnLista() {
	return ingredientesEnLista;
  }

  public void setIngredientesEnLista(Map<String, Integer> ingredientesEnLista) {
	this.ingredientesEnLista = ingredientesEnLista;
  }

  public Map<String, Integer> getKitsEnLista() {
	return kitsEnLista;
  }

  public void setKitsEnLista(Map<String, Integer> kitsEnLista) {
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

  public double getCostoTotal() {
	return costoTotal;
  }

  public void setCostoTotal(double costoTotal) {
	this.costoTotal = costoTotal;
  }

  public void setCostoTrasDescuento(double costoTrasDescuento) {
	this.costoTrasDescuento = costoTrasDescuento;
  }

  public void setDescuento(double descuento) {
	this.descuento = descuento;
  }
  
// Metodos para agregar items a la canasta
  public void agregarProducto(Producto producto, int cantidad) {
    gestionAgregar(producto, cantidad);
    calcularElementosCanasta();
    generarCosto();
  }

  public void agregarIngrediente(Ingrediente ingrediente, int cantidad) {
    gestionAgregar(ingrediente, cantidad);
    calcularElementosCanasta();
    generarCosto();
  }

  public void agregarKit(String nombreKit, ArrayList<Object>listaIngredienteCantidad) {
    gestionAgregar(nombreKit, listaIngredienteCantidad);
    calcularElementosCanasta();
    generarCosto();
  } 

  //Metodos para agregar items a la lista
  public void agregarProducto(String nombreProducto, int cantidad) {
    gestionAgregar(nombreProducto, cantidad, "1");
    calcularElementosLista();
    generarCostoEnLista();
  }

  public void agregarIngrediente(String nombreIngrediente, int cantidad) {
    gestionAgregar(nombreIngrediente, cantidad, 1);
    calcularElementosLista();
    generarCostoEnLista();
  }

  public void agregarKit(String nombreKit, int cantidad) {
    gestionAgregar(nombreKit, cantidad, true);
    calcularElementosLista();
    generarCostoEnLista();
  }

  //Metodos para eliminar items de la canasta
  public boolean eliminarProducto(Producto producto, int cantidad) {
    if(gestionEliminar(producto, cantidad)){
      calcularElementosCanasta();
      generarCosto();
      return true;
    }
    else{return false;}
  }

  public boolean eliminarIngrediente(Ingrediente ingrediente, int cantidad) {
    if(gestionEliminar(ingrediente, cantidad)){
      calcularElementosCanasta();
      generarCosto();
      return true;
    }
    else{return false;}
  }
  
  public boolean eliminarKit(String nombreKit, int cantidad) {
    if(gestionEliminar(nombreKit, cantidad)){
      calcularElementosCanasta();
      generarCosto();
      return true;
    }
    else{return false;}
  }

  //Metodos para eliminar items de la lista
  public boolean eliminarProducto(String nombreProducto, int cantidad) {
    if(gestionEliminar(nombreProducto, cantidad, "1")){
      calcularElementosLista();
      generarCostoEnLista();
      return true;
    }
    else{return false;}
  }

  public boolean eliminarIngrediente(String nombreIngrediente, int cantidad) {
    if(gestionEliminar(nombreIngrediente, cantidad, 1)){
      calcularElementosLista();
      generarCostoEnLista();
      return true;
    }
    else{return false;}
  }

  public boolean eliminarKit(String nombreKit, int cantidad, boolean iD) {
    if(gestionEliminar(nombreKit, cantidad, iD)){
      calcularElementosLista();
      generarCostoEnLista();
      return true;
    }
    else{return false;}
  }

  // Metodos que gestionan correctamente la modificacion de los maps
  // Verifican que efectivamente el elemento no exista en el map antes de
  // agregarlo, en el caso de que si, simplemente agrega la cantidad indicada a la que ya había

  //Para productos
  private void gestionAgregar(Producto producto, int elementNum) {
    for(Map.Entry<Producto, Integer> entry : productos.entrySet()) {
      Producto p = entry.getKey();
      if (p.getId().equals(producto.getId())) {
        productos.put(p, entry.getValue()+elementNum);
      }
    }
    productos.put(producto, elementNum);
  }

  //Para ingredientes
  private void gestionAgregar(Ingrediente ingrediente, int elementNum) {
    for(Map.Entry<Ingrediente, Integer> entry : ingredientes.entrySet()) {
      Ingrediente i = entry.getKey();
      if (i.getId().equals(ingrediente.getId())) {
        ingredientes.put(i, entry.getValue()+elementNum);
      }
    }
    ingredientes.put(ingrediente, elementNum);
  }

  //Para kits
  private void gestionAgregar(String kit, ArrayList<Object> listaIngredienteCantidad) {
    if ((kit != null) && (!kits.containsKey(kit))) {
      kits.put(kit, listaIngredienteCantidad);
    } else if ((kit != null)) {
      listaIngredienteCantidad.set(1, (Integer)listaIngredienteCantidad.get(1)+(Integer)kits.get(kit).get(1));
      kits.put(kit, listaIngredienteCantidad);
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

  //Para kits en lista
  private void gestionAgregar(String kit, int elementNum, boolean iD) {
    if ((kit != null) && (!kitsEnLista.containsKey(kit))) {
      kitsEnLista.put(kit, elementNum);
    } else if ((kit != null)) {
      kitsEnLista.put(kit, kitsEnLista.get(kit) + elementNum);
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

  //Metodos que verifican que efectivamente el elemento exista en el map antes de eliminarlo,y que exista la cantidad suficiente para eliminarlo
  //Para ingredientes
  private boolean gestionEliminar(Ingrediente ingrediente,int cantidad) {
    for(Map.Entry<Ingrediente, Integer> entry : ingredientes.entrySet()) {
      Ingrediente i = entry.getKey();
      if (i.getId().equals(ingrediente.getId())) {
        if(entry.getValue()+cantidad>0){
          ingredientes.put(i, entry.getValue()-cantidad);
          return true;
        }
        else if(entry.getValue()+cantidad==0){
          ingredientes.remove(ingrediente);
          return true;
        }
        else if(entry.getValue()+cantidad<0){
          return false;
        }
      }
    }
    return false;
  }

  //Para productos
  private boolean gestionEliminar(Producto producto, int cantidad) {
    for(Map.Entry<Producto, Integer> entry : productos.entrySet()) {
      Producto p = entry.getKey();
      if (p.getId().equals(producto.getId())) {
        if(entry.getValue()+cantidad>0){
          productos.put(p, entry.getValue()+cantidad);
          return true;
        }
        else if(entry.getValue()+cantidad==0){
          productos.remove(producto);
          return true;
        }
        else if(entry.getValue()+cantidad<0){
          return false;
        }
      }
    }
    return false;
  }

  //Para kits
  private boolean gestionEliminar(String kit, int cantidad) {
    for (Map.Entry<String, ArrayList<Object>> entry : getKits().entrySet()) {
      String nombre = entry.getKey();
      ArrayList<Object> valor = entry.getValue();
      int cantidadActual = 0;
      if (nombre.equals(kit)) {
        cantidadActual = (Integer) valor.get(1);
      }
      if(cantidadActual+cantidad>0 && cantidadActual!=0){
        valor.set(1, cantidadActual+cantidad);
        return true;
      }
      else if(cantidadActual+cantidad==0 && cantidadActual!=0){
        getKits().remove(kit);
        return true;
      }
    }
    return false;
  }

  //Para productos en lista
  private boolean gestionEliminar(String prdct, int cantidad, String iD) {
    if ((prdct != null) && (productosEnLista.containsKey(prdct))) {
      if(productosEnLista.get(prdct)+cantidad>0){
        productosEnLista.put(prdct, productosEnLista.get(prdct)+cantidad);
        return true;
      }
      else if(productosEnLista.get(prdct)+cantidad==0){
        productosEnLista.remove(prdct);
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
        return true;
      }
      else if(ingredientesEnLista.get(ingrd)+cantidad==0){
        ingredientesEnLista.remove(ingrd);
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
        return true;
      }
      else if(kitsEnLista.get(kit)+cantidad==0){
        kitsEnLista.remove(kit);
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
    if(productos!=null){
      for (Map.Entry<Producto, Integer> productoEntry : productos.entrySet()) {
        elementos+=productoEntry.getValue();
      }
    }
    if(ingredientes!=null){
      for (Map.Entry<Ingrediente, Integer> ingredienteEntry : ingredientes.entrySet()) {
        elementos+=ingredienteEntry.getValue();
      }
    }
    if(kits!=null){
      for (Map.Entry<String, ArrayList<Object>> entry : kits.entrySet()) {
        elementos+=(Integer)entry.getValue().get(1);
      }
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
   * Calcula el costo total de la canasta, teniendo en cuenta los descuentos aplicados a los productos y los ingredientes.
   * El costo total se calcula sumando el costo de todos los productos e ingredientes en la canasta y restando el descuento aplicado a cada producto.
   * El descuento se calcula utilizando el método cuponProductos.
   * El costo final se almacena en el atributo costoTotal del objeto Canasta.
   * El costo antes del descuento se almacena en el atributo costoTrasDescuento del objeto Canasta.
   * El descuento total aplicado a la canasta se almacena en el atributo descuento del objeto Canasta.
   */
  public void generarCosto() {
    double costoCanasta = 0;
    double descuentoCanasta=0.0;
    if(productos!=null){
      for (Map.Entry<Producto, Integer> productoEntry : productos.entrySet()) {
        Producto producto = productoEntry.getKey();
        Integer cantidad = productoEntry.getValue();
        double descuento = cuponProductos(producto, cantidad);
        costoCanasta+= producto.getCosto() * cantidad * descuento;
        descuentoCanasta+= producto.getCosto()*cantidad *(1-descuento);
      }
    }
    if(ingredientes!=null){
      for (Map.Entry<Ingrediente, Integer> ingredienteEntry : ingredientes.entrySet()) {
        Ingrediente ingrediente = ingredienteEntry.getKey();
        Integer cantidad = ingredienteEntry.getValue();
        costoCanasta += ingrediente.getPrecioDeVenta() * cantidad;
      }
    }
    if(kits!=null){
      for (Map.Entry<String, ArrayList<Object>> entry : kits.entrySet()) {
        ArrayList<Object> valor = entry.getValue();
        Map<Ingrediente, Integer> ingredientesKit = (Map<Ingrediente, Integer>)valor.get(0);
        double costoIngredientesKit = 0;
        for (Map.Entry<Ingrediente, Integer> ingredienteEntry : ingredientesKit.entrySet()) {
          Ingrediente ingrediente = ingredienteEntry.getKey();
          Integer cantidad = ingredienteEntry.getValue();
          costoIngredientesKit += ingrediente.getPrecioDeVenta() * cantidad;
        }
        costoCanasta += costoIngredientesKit * (Integer)valor.get(1);
      }
    }
    this.descuento=descuentoCanasta;
    this.costoTotal=costoCanasta+descuentoCanasta;
    this.costoTrasDescuento=costoCanasta;
  }

  /**
   * Calcula el costo total de los elementos en la canasta de compras, incluyendo cualquier descuento aplicado.
   * El costo se calcula en función de la cantidad de cada producto, ingrediente o kit en la canasta.
   * El descuento se calcula en función de los cupones aplicables para cada producto en la canasta.
   * El costo final se almacena en las variables de instancia costoTotalEnLista, costoTrasDescuentoEnLista y descuentoEnLista.
   * @return void
   */
  public void generarCostoEnLista(){
    double costoCanasta = 0;
    double descuentoCanasta=0.0;
    if(productosEnLista!=null){
      for (Map.Entry<String, Integer> productoEntry : productosEnLista.entrySet()) {
      Producto producto = Panaderia.buscarProductoPorId(productoEntry.getKey());
      Integer cantidad = productoEntry.getValue();
      double descuento = cuponProductos(producto, cantidad);
      costoCanasta+= producto.getCosto() * cantidad * descuento;
      descuentoCanasta+= producto.getCosto()*cantidad *(1-descuento);
      }
    }
    if(ingredientesEnLista!=null){
      for (Map.Entry<String, Integer> ingredienteEntry : ingredientesEnLista.entrySet()) {
        Ingrediente ingrediente = Panaderia.buscarIngredientePorId(ingredienteEntry.getKey());
        Integer cantidad = ingredienteEntry.getValue();
        costoCanasta += ingrediente.getPrecioDeVenta() * cantidad;
      }
    }
    if(kitsEnLista!=null){
      for (Map.Entry<String, Integer> entry : kitsEnLista.entrySet()) {
        String productoReceta = entry.getKey();
        Producto producto = Panaderia.buscarProductoPorId(productoReceta);
        Map<Ingrediente, Integer> ingredientesKit = producto.getIngredientes();
        double costoIngredientesKit = 0;
        for (Map.Entry<Ingrediente, Integer> ingredienteEntry : ingredientesKit.entrySet()) {
          Ingrediente ingrediente = ingredienteEntry.getKey();
          Integer cantidad = ingredienteEntry.getValue();
          costoIngredientesKit += ingrediente.getPrecioDeVenta() * cantidad;
        }
        costoCanasta += costoIngredientesKit * (Integer)entry.getValue();
      }
    }
    this.costoTotalEnLista=costoCanasta+descuentoCanasta;
    this.costoTrasDescuentoEnLista=costoCanasta;
    this.descuentoEnLista=descuentoCanasta;
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
      if (Panaderia.verificarExistenciaProductoPorId(objetoEntrante)) {
        if (receta){
          estado = eliminarKit(objetoEntrante, Integer.parseInt(cantidad), true);
        } 
        else{
          estado = eliminarProducto(objetoEntrante, Integer.parseInt(cantidad));
        }
        if (estado){
          return true;
        }
        else{
          return false;
        }
      } 
      else if (Panaderia.verificarExistenciaIngredientePorId(objetoEntrante) && !receta) {
        estado=eliminarIngrediente(objetoEntrante, Integer.parseInt(cantidad));
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
      if (Panaderia.verificarExistenciaProductoPorId(objetoEntrante)) {
        if (receta){
          agregarKit(objetoEntrante, Integer.parseInt(cantidad));
        } 
        else{
          agregarProducto(objetoEntrante, Integer.parseInt(cantidad));
        }
        return true;
      } 
      else if (Panaderia.verificarExistenciaIngredientePorId(objetoEntrante) && !receta) {
        agregarIngrediente(objetoEntrante, Integer.parseInt(cantidad));
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
  public boolean recibirOrdenPersonalizada(String objetoEntrante,  Map<String, Integer> ingredientesNecesarios, String cantidad, boolean receta) {
    try {
      Panaderia.crearProductoPersonalizado(objetoEntrante, ingredientesNecesarios);
    }
    catch (Exception e){
      return false;
    }
    if (Panaderia.verificarExistenciaProductoPorId(objetoEntrante)) {
      if (receta){
        agregarKit(objetoEntrante, Integer.parseInt(cantidad));
      } else{
        agregarProducto(objetoEntrante, Integer.parseInt(cantidad));
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
  Map<Producto, Integer> productosCocinados = Panaderia.agregarProductosACanasta(productosEnLista);
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
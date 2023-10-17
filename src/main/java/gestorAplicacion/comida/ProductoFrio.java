package gestorAplicacion.comida;

import gestorAplicacion.humanos.Domiciliario;
import gestorAplicacion.humanos.Cocinero;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class ProductoFrio extends Producto{
  private boolean congelador=false;
  private int tiempoDeCongelamiento;

  public ProductoFrio(String nombre, String ids, HashMap<String,Integer> ingredientes, double costo, int vecesVendido, int tiempoDeCongelamiento) {
    super(nombre, ids, ingredientes, costo, vecesVendido);
    this.tiempoDeCongelamiento = tiempoDeCongelamiento;
  }

  public ProductoFrio(String nombre, HashMap<String,Integer> ingredientes) {
    super(nombre, ingredientes);
    this.tiempoDeCongelamiento = calcularCongelamiento();
  }

  public boolean getcongelador() {
    return congelador;
  }

  public void setcongelador(boolean congelador) {
    this.congelador = congelador;
  }

  public int gettiempoDeCongelamiento() {
    return tiempoDeCongelamiento;
  }

  public void settiempoDeCongelamiento(int tiempoDeCongelamiento) {
    this.tiempoDeCongelamiento = tiempoDeCongelamiento;
  }
  
  /**
   * Crea un nuevo objeto ProductoFrio con el nombre especificado y aumenta el contador de veces vendido en 1.
   * @param Nnombre el nombre del producto a crear
   * @return el nuevo objeto ProductoFrio creado
   */
  public static ProductoFrio crearProducto(String Nnombre) {
    ProductoFrio newProducto = (ProductoFrio) obtenerObjetoPorId(Nnombre);
    newProducto.vecesVendido++;
    return new ProductoFrio(newProducto.getNombre(), newProducto.getId(), newProducto.getIngredientes(), newProducto.getCosto(), newProducto.getVecesVendido(), newProducto.gettiempoDeCongelamiento());
  }
  
  /**
   * Crea un producto frío personalizado con los ingredientes y el nombre especificados.
   * Si un ingrediente no existe, se crea uno nuevo.
   * @param Nnombre el nombre del producto frío personalizado
   * @param ingredientes los ingredientes y sus cantidades necesarias para crear el producto frío personalizado
   * @param congelador true si el producto frío personalizado debe ser almacenado en el congelador, false de lo contrario
   * @return el producto frío personalizado creado
   */
  public static ProductoFrio crearProductoPersonalizado(String Nnombre, HashMap<String, Integer> ingredientes) {
    for (HashMap.Entry<String, Integer> entry : ingredientes.entrySet()) {
      if (!Ingrediente.verificacionExistenciaPorNombre(entry.getKey())) {
        new Ingrediente(entry.getKey());
      }
    }
    return new ProductoFrio(Nnombre, ingredientes);
  }
  
  /**
   * Retorna una lista de procesos de cocina aleatorios para el producto frio.
   * Los procesos pueden ser: Gelatinificar, Amasar, Mezclar, Congelar, Licuar o Decoracion.
   * La cantidad de procesos aleatorios a seleccionar esta entre 1 y 3.
   * @return una lista de procesos de cocina aleatorios para el producto frio.
   */
  @Override
  public ArrayList<String> seleccionProcesosDeCocina() {
    ArrayList <String> procesos = new ArrayList<String>();
	procesos.add("Gelatinificar");
	procesos.add("Amasar");
	procesos.add("Mezclar");
	procesos.add("Congelar");
	procesos.add("Licuar");
	procesos.add("Decoracion");
	Random numAleatorio = new Random();
  	int cuantosProcesos = numAleatorio.nextInt(3)+1;
			ArrayList <Integer> numerosIndices = new ArrayList<Integer>();
	for(int i=0;i<cuantosProcesos;i++){
		Random random = new Random();
  	int numerosDelProceso = random.nextInt(6);
		numerosIndices.add(numerosDelProceso);
	}
	ArrayList <String> procesosFinales = new ArrayList<String>();
	for (int numero : numerosIndices){
		String procesoRandom = procesos.get(numero);
		procesosFinales.add(procesoRandom);
	}
	return procesosFinales;
	}

  //TODO montar vehiculo especial y contenedor especial para productos frios
  public Domiciliario empaqueCongelador(Domiciliario domiciliario){
    if (domiciliario.isEmpaqueFrio() == true) {
      return domiciliario;
    } else {
      domiciliario.setEmpaqueFrio(true);
      return domiciliario;
    }
  }

  //TODO implementar tiempo de congelamiento aleatorio
  public int calcularCongelamiento(){
    int tiempoDeCongelamiento = 0;

    return tiempoDeCongelamiento;
  }

  //TODO implementar proceso de congelamiento para interactuar con cocinero
  public Cocinero procesoCongelamiento(Cocinero cocinero){
    /* 
    if(cocinero.getContenedor().getCongelador()){
      return true;
    }
    return false;
    */
    return cocinero;
  }
}
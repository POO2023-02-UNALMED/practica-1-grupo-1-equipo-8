package gestorAplicacion.comida;

import gestorAplicacion.humanos.Domiciliario;
import gestorAplicacion.humanos.Cocinero;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

/**
 * La clase ProductoFrio es una subclase de Producto que representa un producto frío en el sistema de gestión de comida.
 * Un producto frío es un tipo de producto que puede ser almacenado en el congelador y requiere un tiempo de congelamiento.
 * Esta clase proporciona métodos para crear productos fríos personalizados, seleccionar procesos de cocina aleatorios,
 * empaquetar el producto frío en un contenedor de frío y congelar el producto frío si el cocinero tiene una nevera disponible.
 */
public class ProductoFrio extends Producto{
  private boolean congelador=false;
  private int tiempoDeCongelamiento;

//constructores 
  public ProductoFrio(String nombre, String ids, HashMap<String,Integer> ingredientes, double costo, int vecesVendido, int tiempoDeCongelamiento) {
    super(nombre, ids, ingredientes, costo, vecesVendido);
    this.tiempoDeCongelamiento = tiempoDeCongelamiento;
  }

  public ProductoFrio(String nombre, HashMap<String,Integer> ingredientes) {
    super(nombre, ingredientes);
    this.tiempoDeCongelamiento = calcularCongelamiento();
  }
//getters y setters
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
   * Crea un nuevo objeto ProductoFrio a partir de un nombre dado.
   * @param Nnombre el nombre del producto a crear.
   * @return el nuevo objeto ProductoFrio creado.
   */
  public static ProductoFrio crearProducto(String Nnombre) {
    ProductoFrio newProducto = (ProductoFrio) obtenerObjetoPorId(Nnombre);
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
  public ArrayList<String> seleccionProcesosDeCocina (){
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
	for (int i = 0; i < cuantosProcesos; i++) {
        int numerosDelProceso;
        boolean repetido;
        do {
            Random random = new Random();
            numerosDelProceso = random.nextInt(6); // Usamos 6, ya que hay 6 procesos en la lista
            repetido = numerosIndices.contains(numerosDelProceso);
        } while (repetido);

        numerosIndices.add(numerosDelProceso);
    }
	ArrayList <String> procesosFinales = new ArrayList<String>();
	for (int numero : numerosIndices){
		String procesoRandom = procesos.get(numero);
		procesosFinales.add(procesoRandom);
	}
	return procesosFinales;
	}

  /**
   * Empaqueta el producto frío en un contenedor de frío por parte del domiciliario.
   * @param domiciliario El domiciliario que realizará el empaque.
   * @return El domiciliario con el empaque de frío activado.
   */
  public Domiciliario empaqueCongelador(Domiciliario domiciliario){
    if (domiciliario.isEmpaqueFrio() == true) {
      return domiciliario;
    } else {
      domiciliario.setEmpaqueFrio(true);
      return domiciliario;
    }
  }

  /**
   * Calcula el tiempo de congelamiento de un producto frío de manera aleatoria.
   * @return tiempo de congelamiento en horas.
   */
  public int calcularCongelamiento(){
    Random numAleatorio = new Random();
    int tiempoDeCongelamiento = numAleatorio.nextInt(20);;

    return tiempoDeCongelamiento;
  }

  /**
   * Congela el producto frío si el cocinero tiene una nevera disponible.
   * @param cocinero el cocinero que realiza el proceso de congelamiento.
   * @return el cocinero con el producto frío congelado si la nevera está disponible, de lo contrario, el mismo cocinero sin cambios.
   */
  public Cocinero procesoCongelamiento(Cocinero cocinero){
    if (cocinero.isNevera() == true) {
      return cocinero;
    } else {
      cocinero.setNevera(true);
      return cocinero;
    }
  }
}
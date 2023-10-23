package gestorAplicacion.comida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import gestorAplicacion.humanos.Cocinero;

public class ProductoCaliente extends Producto{
	private boolean horno=false;

//constructores
	public ProductoCaliente(String nombre, String ids, HashMap<String,Integer> ingredientes, double costo, int vecesVendido) {
		super(nombre, ids, ingredientes, costo, vecesVendido);
}

//getters y setters
public boolean isHorno() {
	return horno;
}

public void setHorno(boolean horno) {
	this.horno = horno;
}

	/**
	 * Crea un nuevo objeto ProductoCaliente a partir de su nombre.
	 * @param Nnombre el nombre del producto caliente a crear.
	 * @return el nuevo objeto ProductoCaliente creado.
	 */
	public static ProductoCaliente crearProducto(String Nnombre) {
		ProductoCaliente newProducto = (ProductoCaliente) obtenerObjetoPorId(Nnombre);
		return new ProductoCaliente(newProducto.getNombre(), newProducto.getId(), newProducto.getIngredientes(), newProducto.getCosto(), newProducto.getVecesVendido());
	}

	/**
	 * Crea un nuevo producto personalizado con el nombre y los ingredientes especificados.
	 * Si alguno de los ingredientes no existe en la lista de ingredientes, se crea uno nuevo.
	 * @param Nnombre el nombre del producto personalizado
	 * @param ingredientes un HashMap que contiene los ingredientes y sus cantidades
	 * @return el nuevo producto personalizado creado
	 */
	public static Producto crearProductoPersonalizado(String Nnombre, HashMap<String, Integer> ingredientes) {
    for (HashMap.Entry<String, Integer> entry : ingredientes.entrySet()) {
    if (!Ingrediente.verificacionExistenciaPorNombre(entry.getKey())) {
        new Ingrediente(entry.getKey());
    }
    }
    return new Producto(Nnombre, ingredientes);
}

	/**
	 * Proceso de horneado de un producto caliente por parte de un cocinero.
	 * @param cocinero El cocinero que realizará el proceso de horneado.
	 * @return El cocinero con el estado del horno actualizado.
	 */
	public Cocinero procesoHornear(Cocinero cocinero){
		if (cocinero.isHorno() == true) {
			return cocinero;
		} else {
			cocinero.setHorno(true);
			return cocinero;
		}
	}

/**
 * Retorna una lista de procesos de cocina aleatorios para el producto caliente.
 * Los procesos posibles son: Hornear, Amasar, Mezclar, Fritar, Asar y Decoracion.
 * El número de procesos aleatorios a seleccionar está entre 1 y 3.
 * @return una lista de procesos de cocina aleatorios para el producto caliente.
 */
@Override
  public ArrayList<String> seleccionProcesosDeCocina (){
	ArrayList <String> procesos = new ArrayList<String>();
	procesos.add("Hornear");
	procesos.add("Amasar");
	procesos.add("Mezclar");
	procesos.add("Fritar");
	procesos.add("Asar");
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
}
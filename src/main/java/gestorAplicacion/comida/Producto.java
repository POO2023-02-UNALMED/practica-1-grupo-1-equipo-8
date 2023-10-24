package gestorAplicacion.comida;

import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

import java.io.Serializable;


/**
 * La clase Producto representa un producto de comida que se puede vender en un restaurante.
 * Cada producto tiene un nombre, un identificador único, un costo, una lista de ingredientes y un proceso de cocina.
 * También se mantiene un registro de cuántas veces se ha vendido cada producto y cuáles son los productos más vendidos.
 * La clase proporciona métodos para crear, buscar y actualizar productos en la base de datos.
 */
public class Producto implements Serializable, ComidaDefault{
	
	protected static ArrayList<Producto> baseDatosProductos = new ArrayList<Producto>();
	protected static int cantidadProductosUnicos;
	protected String nombre;
	protected String id;
	protected double costo;
	protected HashMap<String,Integer> ingredientes = new HashMap<String,Integer>();
	protected ArrayList<String> procesoDeCocina = new ArrayList<String>();
	protected int vecesVendido;
	protected static ArrayList<Producto> topMasVendidos = new ArrayList<Producto>(6);

	//Constructores
	public Producto(String nombre, String id, HashMap<String,Integer> ingredientes, double costo, int vecesVendido) {
		this.nombre = nombre;
		this.id = id;
		this.ingredientes = ingredientes;
		this.vecesVendido = vecesVendido;
		this.costo = calcularCosto();

	}

	public Producto(String nombre,HashMap<String,Integer> ingredientes){
		this.nombre=nombre;
		this.ingredientes=ingredientes;
		this.costo=calcularCosto();
		cantidadProductosUnicos++;
		this.id = String.valueOf(cantidadProductosUnicos + Ingrediente.getCantidadIngredientesUnicos());
		baseDatosProductos.add(this);
	}

	//Getters y Setters

	public double getCosto(){
		return costo;
	}
	
	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	public HashMap<String, Integer> getIngredientes() {
		return ingredientes;
	}
	
	public void setIngredientes(HashMap<String, Integer> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<String> getProcesoDeCocina() {
		return procesoDeCocina;
	}

	public void setProcesoDeCocina(ArrayList<String> procesoDeCocina) {
		this.procesoDeCocina = procesoDeCocina;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static int getCantidadProductosUnicos() {
		return cantidadProductosUnicos;
	}

	public static void setCantidadProductosUnicos(int cantidadProductosUnicos) {
		Producto.cantidadProductosUnicos = cantidadProductosUnicos;
	}

	public int getVecesVendido() {
		return vecesVendido;
	}

	public void setVecesVendido(int vecesVendido) {
		this.vecesVendido = vecesVendido;
	}

	public static ArrayList<Producto> getBaseDatosProductos() {
		return baseDatosProductos;
	}

	public static void setBaseDatosProductos(ArrayList<Producto> baseDatosProductos) {
		Producto.baseDatosProductos = baseDatosProductos;
	}

	public static ArrayList<Producto> getTopMasVendidos() {
		return topMasVendidos;
	}

	public static void setTopMasVendidos(ArrayList<Producto> topMasVendidos) {
		Producto.topMasVendidos = topMasVendidos;
	}

	//Métodos

	/**
	 * Verifica si existe un producto en la base de datos con el nombre especificado.
	 * @param nombre El nombre del producto a buscar.
	 * @return true si existe un producto con el nombre especificado, false en caso contrario.
	 */
	public static boolean verificacionExistenciaPorNombre(String nombre) {
		boolean existe = false;
		for (int i = 0; i < baseDatosProductos.size(); i++) {
			if (baseDatosProductos.get(i).getNombre().equals(nombre)) {
				existe = true;
			}
		}
		return existe;
	}

	/**
	 * Verifica si existe un producto en la base de datos a partir de su ID.
	 * @param id El ID del producto a buscar.
	 * @return true si el producto existe en la base de datos, false en caso contrario.
	 */
	public static boolean verificacionExistenciaPorId(String id) {
		boolean existe = false;
		for (int i = 0; i < baseDatosProductos.size(); i++) {
			if (baseDatosProductos.get(i).getId().equals(id)) {
				existe = true;
			}
		}
		return existe;
	}

	/**
	 * Busca y devuelve un objeto Producto de la base de datos por su nombre.
	 * @param nombre El nombre del producto a buscar.
	 * @return El objeto Producto correspondiente al nombre dado, o null si no se encuentra.
	 */
	public static Producto obtenerObjetoPorNombre(String nombre) {
		Producto producto = null;
		for (int i = 0; i < baseDatosProductos.size(); i++) {
			if (baseDatosProductos.get(i).getNombre().equals(nombre)) {
				producto = baseDatosProductos.get(i);
			}
		}
		return producto;
	}

	/**
	 * Devuelve el objeto Producto correspondiente al id proporcionado.
	 * @param id el id del Producto a buscar
	 * @return el objeto Producto correspondiente al id proporcionado, o null si no se encuentra
	 */
	public static Producto obtenerObjetoPorId(String id) {
		Producto producto = null;
		for (int i = 0; i < baseDatosProductos.size(); i++) {
			if (baseDatosProductos.get(i).getId().equals(id)) {
				producto = baseDatosProductos.get(i);
			}
		}
		return producto;
	}

	/**
	 * Crea un nuevo objeto Producto con los mismos atributos que el objeto con el nombre especificado.
	 * @param Nnombre el nombre del objeto Producto a crear.
	 * @return el nuevo objeto Producto creado.
	 */
	public static Producto crearProducto(String Nnombre) {
		Producto newProducto = obtenerObjetoPorId(Nnombre);
		return new Producto(newProducto.getNombre(), newProducto.getId(), newProducto.getIngredientes(), newProducto.getCosto(), newProducto.getVecesVendido());
	}

	/**
	 * Crea un nuevo producto personalizado con el nombre y los ingredientes especificados.
	 * Si un ingrediente no existe, se crea uno nuevo.
	 * @param Nnombre El nombre del producto personalizado.
	 * @param ingredientes Un HashMap que contiene los ingredientes y sus cantidades.
	 * @return El nuevo producto personalizado creado.
	 */
	public static Producto crearProductoPersonalizado(String Nnombre, HashMap<String,Integer> ingredientes) {
		for (HashMap.Entry<String, Integer> entry : ingredientes.entrySet()) {
			if (!Ingrediente.verificacionExistenciaPorNombre(entry.getKey())) {
				new Ingrediente(entry.getKey());
			}
		}
		return new Producto(Nnombre, ingredientes);
	}

	/**
	 * Calcula el costo del producto tomando en cuenta los ingredientes y la tarifa de ganancias.
	 * @return el costo del producto
	 */
	public double calcularCosto() {
		double costo = 0;
		for (HashMap.Entry<String, Integer> entry : ingredientes.entrySet()) {
			costo += Ingrediente.obtenerObjetoPorNombre(entry.getKey()).getPrecioDeVenta() * entry.getValue();
		}
		return costo*tarifaGanancias;
	}


	/**
	 * Este método devuelve una lista de procesos de cocina seleccionados aleatoriamente.
	 * Los procesos de cocina disponibles son: Hornear, Gelatinificar, Amasar, Mezclar, Fritar, Asar, Congelar, Licuar y Decoracion.
	 * El número de procesos seleccionados es aleatorio y varía entre 1 y 3.
	 * @return una lista de procesos de cocina seleccionados aleatoriamente.
	 */
	public ArrayList<String> seleccionProcesosDeCocina (){
	ArrayList <String> procesos = new ArrayList<String>();
	procesos.add("Hornear");
	procesos.add("Gelatinificar");
	procesos.add("Amasar");
	procesos.add("Mezclar");
	procesos.add("Fritar");
	procesos.add("Asar");
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

	/**
	 * Método que organiza los productos más vendidos en orden descendente y los almacena en una lista.
	 * Se utiliza la variable estática baseDatosProductos para obtener los productos y su número de ventas.
	 * Se utiliza la variable estática topMasVendidos para almacenar los 5 productos más vendidos.
	 */
	public static void organizarTopMasVendidos(){
		topMasVendidos.clear();
		for(int i=0; i<baseDatosProductos.size(); i++){
			for(int j=0; j<baseDatosProductos.size(); j++){
				if(baseDatosProductos.get(i).getVecesVendido() > baseDatosProductos.get(j).getVecesVendido()){
					Producto aux = baseDatosProductos.get(i);
					baseDatosProductos.set(i, baseDatosProductos.get(j));
					baseDatosProductos.set(j, aux);
				}
			}
		}
		for(int i=0; i<baseDatosProductos.size(); i++){
			if(i<5){
				topMasVendidos.add(baseDatosProductos.get(i));
			}
		}
	}
}
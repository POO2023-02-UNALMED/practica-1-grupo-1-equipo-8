package gestorAplicacion.comida;

import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

import java.io.Serializable;


public class Producto implements Serializable{
	
	protected static ArrayList<Producto> baseDatosProductos = new ArrayList<Producto>();
	protected static int cantidadProductosUnicos;
	protected String nombre;
	protected String id;
	protected double costo;
	protected String sabor;
	protected HashMap<String,Integer> ingredientes = new HashMap<String,Integer>();
	protected ArrayList<String> procesoDeCocina = new ArrayList<String>();
	/* 
  protected Integer unidades;
	// Hablar con richar para eliminar el atributo de abajo
	protected static List<Producto> productos = new ArrayList<Producto>(); //lista de productos necesaria para dar la lista de opciones y para procesar las ordenes facilmente
	*/
	//Constructores
	public Producto(String nombre, HashMap<String,Integer> ingredientes, double costo, String sabor, ArrayList<String> procesoDeCocina) {
		this.nombre = nombre;
		this.ingredientes = ingredientes;
		this.sabor = sabor;
		this.procesoDeCocina = procesoDeCocina;
		this.costo = calcularCosto();

	}

	public Producto(String nombre,HashMap<String,Integer> ingredientes){
		this.nombre=nombre;
		this.ingredientes=ingredientes;
		this.costo=calcularCosto();
		this.procesoDeCocina=seleccionProcesoDeCocina();
		cantidadProductosUnicos++;
		this.id = String.valueOf(cantidadProductosUnicos + Ingrediente.getCantidadIngredientesUnicos());
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

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	/* 
	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}
	*/
	
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

	//Métodos

	public static boolean verificacionExistenciaPorNombre(String nombre) {
		boolean existe = false;
		for (int i = 0; i < baseDatosProductos.size(); i++) {
			if (baseDatosProductos.get(i).getNombre().equals(nombre)) {
				existe = true;
			}
		}
		return existe;
	}

	public static boolean verificacionExistenciaPorId(String id) {
		boolean existe = false;
		for (int i = 0; i < baseDatosProductos.size(); i++) {
			if (baseDatosProductos.get(i).getId().equals(id)) {
				existe = true;
			}
		}
		return existe;
	}

	public static Producto obtenerObjetoPorNombre(String nombre) {
		Producto producto = null;
		for (int i = 0; i < baseDatosProductos.size(); i++) {
			if (baseDatosProductos.get(i).getNombre().equals(nombre)) {
				producto = baseDatosProductos.get(i);
			}
		}
		return producto;
	}

	public static Producto obtenerObjetoPorId(String id) {
		Producto producto = null;
		for (int i = 0; i < baseDatosProductos.size(); i++) {
			if (baseDatosProductos.get(i).getId().equals(id)) {
				producto = baseDatosProductos.get(i);
			}
		}
		return producto;
	}

	public static Producto crearProducto(String Nnombre) {
		Producto newProducto = obtenerObjetoPorNombre(Nnombre);
		return new Producto(newProducto.getNombre(), newProducto.getIngredientes(), newProducto.getCosto(), newProducto.getSabor(), newProducto.getProcesoDeCocina());
	}

	public static Producto crearProductoPersonalizado(String Nnombre, HashMap<String,Integer> ingredientes) {
		for (HashMap.Entry<String, Integer> entry : ingredientes.entrySet()) {
			if (!Ingrediente.verificacionExistenciaPorNombre(entry.getKey())) {
				new Ingrediente(entry.getKey());
			}
		}
		return new Producto(Nnombre, ingredientes);
	}

	public double calcularCosto() {
		double costo = 0;
		for (HashMap.Entry<String, Integer> entry : ingredientes.entrySet()) {
			costo += Ingrediente.obtenerObjetoPorNombre(entry.getKey()).getPrecioDeCompra() * entry.getValue();
		}
		return costo;
	}

	//TODO implementar funcion de proceso de cocina aleatorio
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
}
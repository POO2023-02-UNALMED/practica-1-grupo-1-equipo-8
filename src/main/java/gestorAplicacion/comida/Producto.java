package gestorAplicacion.comida;
import java.util.HashMap;
import java.util.Map;

import gestorAplicacion.gestion.Panaderia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Producto implements Serializable{
	
	private String nombre;
	private Map<Ingrediente,Integer> ingredientes = new HashMap<Ingrediente,Integer>();
	private float costo;
	private String sabor;
	private float tiempoProducción;
  private Integer unidades;
	private List<String> procesoDeCocina = new ArrayList<String>();
	private String id;
	private static int cantidadProductos;

	//Constructores
	public Producto(String nombre, Map<Ingrediente,Integer> ingredientes, float costo, String sabor, float tiempoProducción, Integer unidades) {
		
		this.nombre = nombre;
		this.ingredientes=ingredientes;
		this.costo=costo;
		this.sabor = sabor;
		this.tiempoProducción = tiempoProducción;
		this.unidades = unidades;
		cantidadProductos++;
		this.id = String.valueOf(cantidadProductos + Ingrediente.getCantidadIngredientes());
	}
	
	public Producto(String nombre,Map<Ingrediente,Integer> ingredientes){
		this.nombre=nombre;
		this.ingredientes=ingredientes;
		cantidadProductos++;
		this.id = String.valueOf(cantidadProductos + Ingrediente.getCantidadIngredientes());
	}

	//Getters y Setters

	public float getCosto(){
		return costo;
	}
	
	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public Map<Ingrediente, Integer> getIngredientes() {
		return ingredientes;
	}
	
	public void setIngredientes(Map<Ingrediente, Integer> ingredientes) {
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

	public float getTiempoProducción() {
		return tiempoProducción;
	}

	public void setTiempoProducción(float tiempoProducción) {
		this.tiempoProducción = tiempoProducción;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}
	
	public List<String> getProcesoDeCocina() {
		return procesoDeCocina;
	}

	public void setProcesoDeCocina(List<String> procesoDeCocina) {
		this.procesoDeCocina = procesoDeCocina;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static int getCantidadProductos() {
		return cantidadProductos;
	}

	public static void setCantidadProductos(int cantidadProductos) {
		Producto.cantidadProductos = cantidadProductos;
	}

	//Métodos

	public static Producto crearProducto(Panaderia panaderia, String nombre, Map<Ingrediente,Integer> ingredientes, String sabor) {
		
		float calculotiempo = 0; //usar al alateaoridad para esto
		float calculocosto = 0; //crear estos métodos usando el map
		
		Producto postre = new Producto(nombre, ingredientes, calculocosto, sabor, calculotiempo, 0);
		
		panaderia.agregarProducto(postre, 0);
		
		return postre;
		
	}
	
}
//Nota para la persona que trabaje esta clase
//Creo que es mejor trabajar los ingredientes del producto como diccionario para acceder a ellos mejor desde la clase canasta y así eliminar el atributo cantidad de la clase ingredientes, si hay algun cambio, me avisan para hacer el cambio en Canasta
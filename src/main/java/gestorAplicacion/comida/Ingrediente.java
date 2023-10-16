package gestorAplicacion.comida;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gestorAplicacion.gestion.Panaderia;

//necesito un metodo o un atributo que me de los ingredientes disponibles en bodega!!! (Richard)
public class Ingrediente implements Serializable{
	private static ArrayList<Ingrediente> baseDatosIngredientes = new ArrayList<Ingrediente>();
	private static int cantidadIngredientesUnicos;
	private double TARIFAGANANCIA =2/3;
	private String nombre;
	private String id;
	private double PrecioDeVenta;
	private double PrecioDeCompra;
	private int vecesVendido=0;
	public static final int probabilidadConstante =1;
	//Hablar con richar para eliminar el atributo de abajo
	public static List<Ingrediente> ingredientes = new ArrayList<Ingrediente>(); //lista de ingredientes totales necesaria para dar la lista de opciones y para procesar las ordenes facilmente

	// constructores sobrecargados
		public Ingrediente(String nombre1) {
		this.nombre = nombre1;
		Random aleatorio = new Random();
		double numeroAleatorio = aleatorio.nextInt(2701) + 300; // Genera un entero entre 0 y 100 (ambos inclusive).
		this.PrecioDeVenta = numeroAleatorio;
		double numeroAleatorioCompra = numeroAleatorio*TARIFAGANANCIA;
		this.PrecioDeCompra =  Math.ceil(numeroAleatorioCompra);
		cantidadIngredientesUnicos++;
		this.id = String.valueOf(cantidadIngredientesUnicos+Producto.getCantidadProductosUnicos());
		baseDatosIngredientes.add(this);
	}

	public Ingrediente(String nombre, String id, double precioDeVenta, double precioDeCompra, int vecesVendido) {
		this.nombre = nombre;
		this.id = id;
		this.PrecioDeVenta = precioDeVenta;
		this.PrecioDeCompra = precioDeCompra;
		this.vecesVendido = vecesVendido;
	}

	public static List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	

	public static void setIngredientes(List<Ingrediente> ingredientes) {
		Ingrediente.ingredientes = ingredientes;
	}

	// getters y setters de los atributos

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String Newnombre) {
		this.nombre = Newnombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String Newid) {
		this.id = Newid;
	}

	public static int getCantidadIngredientesUnicos() {
		return cantidadIngredientesUnicos;
	}

	public static void setCantidadIngredientesUnicos(int NewcantidadIngredientesUnicos) {
		cantidadIngredientesUnicos = NewcantidadIngredientesUnicos;
	}

	public double getPrecioDeVenta() {
		return PrecioDeVenta;
	}

	public void setPrecioDeVenta(double precioDeVenta) {
		PrecioDeVenta = precioDeVenta;
	}

	public double getPrecioDeCompra() {
		return PrecioDeCompra;
	}

	public void setPrecioDeCompra(double precioDeCompra) {
		PrecioDeCompra = precioDeCompra;
	}

	public int getVecesVendido() {
		return vecesVendido;
	}

	public void setVecesVendido(int vecesVendido) {
		this.vecesVendido = vecesVendido;
	}

	public static ArrayList<Ingrediente> getBaseDatosIngredientes() {
		return baseDatosIngredientes;
	}

	public static void setBaseDatosIngredientes(ArrayList<Ingrediente> baseDatosIngredientes) {
		Ingrediente.baseDatosIngredientes = baseDatosIngredientes;
	}

	/**
	 * Verifica si un ingrediente existe en la base de datos por su nombre.
	 * @param nombre El nombre del ingrediente a verificar.
	 * @return true si el ingrediente existe, false de lo contrario.
	 */
	public static boolean verificacionExistenciaPorNombre(String nombre) {
		boolean existe = false;
		for (int i = 0; i < baseDatosIngredientes.size(); i++) {
			if (baseDatosIngredientes.get(i).getNombre().equals(nombre)) {
				existe = true;
			}
		}
		return existe;
	}

	public static boolean verificacionExistenciaPorId(String id) {
		boolean existe = false;
		for (int i = 0; i < baseDatosIngredientes.size(); i++) {
			if (baseDatosIngredientes.get(i).getId().equals(id)) {
				existe = true;
			}
		}
		return existe;
	}

	/**
	 * Busca un objeto Ingrediente en la base de datos por su nombre.
	 * @param nombre El nombre del ingrediente a buscar.
	 * @return El objeto Ingrediente correspondiente al nombre, o null si no se encuentra.
	 */
	public static Ingrediente obtenerObjetoPorNombre(String nombre) {
		Ingrediente ingrediente = null;
		for (int i = 0; i < baseDatosIngredientes.size(); i++) {
			if (baseDatosIngredientes.get(i).getNombre().equals(nombre)) {
				ingrediente = baseDatosIngredientes.get(i);
			}
		}
		return ingrediente;
	}
	
	public static Ingrediente obtenerObjetoPorId(String id) {
		Ingrediente ingrediente = null;
		for (int i = 0; i < baseDatosIngredientes.size(); i++) {
			if (baseDatosIngredientes.get(i).getId().equals(id)) {
				ingrediente = baseDatosIngredientes.get(i);
			}
		}
		return ingrediente;
	}
	
	/**
	 * Crea un nuevo objeto Ingrediente con el nombre especificado. Si ya existe un Ingrediente con ese nombre, devuelve una copia del Ingrediente existente.
	 * @param Nnombre el nombre del Ingrediente a crear o copiar
	 * @return un nuevo objeto Ingrediente o una copia del Ingrediente existente
	 */
	public static Ingrediente crearIngrediente(String nombreId) {
		if(verificacionExistenciaPorNombre(nombreId) || verificacionExistenciaPorId(nombreId)) {
			Ingrediente newIngrediente = obtenerObjetoPorNombre(nombreId);
			newIngrediente.setVecesVendido(newIngrediente.getVecesVendido()+1);
			return new Ingrediente(newIngrediente.getNombre(), newIngrediente.getId(), newIngrediente.getPrecioDeVenta(), newIngrediente.getPrecioDeCompra(), newIngrediente.getVecesVendido());
		}

		else {
			return new Ingrediente(nombreId);
		}
	}

	public static void revisarCaducidad(Ingrediente ingrediente, int cantidad){
		Random numAleatorio = new Random();
	    int caducidad = numAleatorio.nextInt(20);
	    if(caducidad == probabilidadConstante){
	    	String ingredienteId = ingrediente.getId();
	    	Panaderia.restarIngrediente(ingredienteId, cantidad);
	    }
	}

	public static int getProbabilidadconstante() {
		return probabilidadConstante;
	}
}

package gestorAplicacion.comida;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gestorAplicacion.gestion.Inventario;
import gestorAplicacion.gestion.Panaderia;
import UIMain.GestionCocinar;

//necesito un metodo o un atributo que me de los ingredientes disponibles en bodega!!! (Richard)
public class Ingrediente implements Serializable, ComidaDefault {
	private static ArrayList<Ingrediente> baseDatosIngredientes = new ArrayList<Ingrediente>();
	private static int cantidadIngredientesUnicos;
	private String nombre;
	private String id;
	private double PrecioDeVenta;
	private double PrecioDeCompra;
	private int vecesVendido = 0;
	public static final int probabilidadConstante = 1;
	private boolean caducado = false;
	private Inventario inventario;

	private static ArrayList<Ingrediente> topMasVendidos = new ArrayList<Ingrediente>(6);

	// constructores sobrecargados
	public Ingrediente(String nombre1) {
		this.nombre = nombre1;
		Random aleatorio = new Random();
		double numeroAleatorio = aleatorio.nextInt(2701) + 300; // Genera un entero entre 0 y 100 (ambos inclusive).
		this.PrecioDeCompra = numeroAleatorio;
		double numeroAleatorioCompra = numeroAleatorio * ComidaDefault.tarifaGanancias;
		this.PrecioDeVenta = Math.ceil(numeroAleatorioCompra);
		cantidadIngredientesUnicos++;
		this.id = String.valueOf(cantidadIngredientesUnicos + Producto.getCantidadProductosUnicos());
		baseDatosIngredientes.add(this);
	}

	public Ingrediente(String nombre, String id, double precioDeVenta, double precioDeCompra, int vecesVendido) {
		this.nombre = nombre;
		this.id = id;
		this.PrecioDeVenta = precioDeVenta;
		this.PrecioDeCompra = precioDeCompra;
		this.vecesVendido = vecesVendido;
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

	public boolean isCaducado() {
		return caducado;
	}

	public void setCaducado(boolean caducado) {
		this.caducado = caducado;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public static void setBaseDatosIngredientes(ArrayList<Ingrediente> baseDatosIngredientes) {
		Ingrediente.baseDatosIngredientes = baseDatosIngredientes;
	}

	public static void getTopMasVendidos(ArrayList<Ingrediente> topMasVendidos) {
		Ingrediente.topMasVendidos = topMasVendidos;
	}

	/**
	 * Verifica si un ingrediente existe en la base de datos por su nombre.
	 * 
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

	/**
	 * Verifica si un ingrediente existe en la base de datos por su nombre.
	 * 
	 * @param nombre El id del ingrediente a verificar.
	 * @return true si el ingrediente existe, false de lo contrario.
	 */
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
	 * 
	 * @param nombre El nombre del ingrediente a buscar.
	 * @return El objeto Ingrediente correspondiente al nombre, o null si no se
	 *         encuentra.
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

	/**
	 * Busca y devuelve el objeto Ingrediente correspondiente al id dado.
	 * 
	 * @param id el id del Ingrediente a buscar
	 * @return el objeto Ingrediente correspondiente al id dado, o null si no se
	 *         encuentra
	 */
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
	 * Crea un nuevo objeto Ingrediente con el nombre especificado. Si ya existe un
	 * Ingrediente con ese nombre, devuelve una copia del Ingrediente existente.
	 * 
	 * @param Nnombre el nombre del Ingrediente a crear o copiar
	 * @return un nuevo objeto Ingrediente o una copia del Ingrediente existente
	 */
	public static Ingrediente crearIngrediente(String nombreId) {
		if (verificacionExistenciaPorNombre(nombreId) || verificacionExistenciaPorId(nombreId)) {
			Ingrediente newIngrediente = obtenerObjetoPorNombre(nombreId);
			return new Ingrediente(newIngrediente.getNombre(), newIngrediente.getId(), newIngrediente.getPrecioDeVenta(),
					newIngrediente.getPrecioDeCompra(), newIngrediente.getVecesVendido());
		}

		else {
			return new Ingrediente(nombreId);
		}
	}

	/**
	 * Método que establece la caducidad de un ingrediente de manera aleatoria.
	 * Si la caducidad es igual a la probabilidad constante, el ingrediente se marca
	 * como caducado.
	 * 
	 * @param ingrediente El ingrediente al que se le establecerá la caducidad.
	 */
	public void caducidad(Ingrediente ingrediente) {
		ingrediente.setCaducado(false);
		Random numAleatorio = new Random();
		int caducidad = numAleatorio.nextInt(20);
		if (caducidad == probabilidadConstante) {
			ingrediente.setCaducado(true);
		}
	}

	/**
	 * Revisa la caducidad del ingrediente y resta la cantidad especificada del
	 * inventario si está vencido.
	 * 
	 * @param cantidad La cantidad de ingrediente a revisar y restar del inventario
	 *                 si está vencido.
	 */
	public void revisarCaducidad(int cantidad, Panaderia panaderia) {

		this.caducidad(this);
		boolean vencido = this.isCaducado();
		if (vencido) {
			String ingredienteId = this.getId();
			panaderia.getInventario().restarIngrediente(ingredienteId, cantidad);
			GestionCocinar.falloCaducado();
			this.setCaducado(false);
		}
	}

	public static int getProbabilidadconstante() {
		return probabilidadConstante;
	}

	public static ArrayList<Ingrediente> getTopMasVendidos() {
		return topMasVendidos;
	}

	public static void setTopMasVendidos(ArrayList<Ingrediente> topMasVendidos) {
		Ingrediente.topMasVendidos = topMasVendidos;
	}

	/**
	 * Método que organiza los ingredientes más vendidos en orden descendente y los
	 * almacena en una lista.
	 * Se utiliza la variable estática baseDatosIngredientes para obtener los
	 * ingredientes y su número de ventas.
	 * Se almacenan los 5 ingredientes más vendidos en la lista estática
	 * topMasVendidos.
	 */
	public static void organizarTopMasVendidos() {
		topMasVendidos.clear();
		for (int i = 0; i < baseDatosIngredientes.size(); i++) {
			for (int j = 0; j < baseDatosIngredientes.size(); j++) {
				if (baseDatosIngredientes.get(i).getVecesVendido() > baseDatosIngredientes.get(j).getVecesVendido()) {
					Ingrediente aux = baseDatosIngredientes.get(i);
					baseDatosIngredientes.set(i, baseDatosIngredientes.get(j));
					baseDatosIngredientes.set(j, aux);
				}
			}
		}
		for (int i = 0; i < baseDatosIngredientes.size(); i++) {
			if (i < 5) {
				topMasVendidos.add(baseDatosIngredientes.get(i));
			}
		}
	}
}

package gestorAplicacion.comida;

import java.io.Serializable;
import java.util.Random;

import gestorAplicacion.gestion.Panaderia;

public class Ingrediente implements Serializable{
	private String nombre;
	private String id;
	private static int cantidadIngredientes;
	private double PrecioDeVenta;
	private double PrecioDeCompra;
	public static final int probabilidadConstante =1;

	// constructores sobrecargados
	public Ingrediente(String nombre1, double PrecioDeVenta,double PrecioDeCompra) {
		this.nombre = nombre1;
		this.PrecioDeVenta = PrecioDeVenta;
		this.PrecioDeCompra = PrecioDeCompra;
		cantidadIngredientes++;
		this.id = String.valueOf(cantidadIngredientes+Producto.getCantidadProductos());
	}

	public Ingrediente(String nombre1) {
		this.nombre = nombre1;
		Random aleatorio = new Random();
		double numeroAleatorio = aleatorio.nextInt(2701) + 300; // Genera un entero entre 0 y 100 (ambos inclusive).
		this.PrecioDeVenta = numeroAleatorio;
		double numeroAleatorioCompra = numeroAleatorio*(2.0/3.0);
		this.PrecioDeCompra =  Math.ceil(numeroAleatorioCompra);
		cantidadIngredientes++;
		this.id = String.valueOf(cantidadIngredientes+Producto.getCantidadProductos());
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

	public static int getCantidadIngredientes() {
		return cantidadIngredientes;
	}

	public static void setCantidadIngredientes(int NewcantidadIngredientes) {
		cantidadIngredientes = NewcantidadIngredientes;
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
	
	// metodos para crear
	//TODO agregarle funcionalidad xd
	public static Ingrediente crearIngrediente(String Nnombre, double NPrecioDeVenta,double NPrecioDeCompra) {
		return new Ingrediente(Nnombre, NPrecioDeVenta,NPrecioDeCompra);
	}

	public static Ingrediente crearIngrediente(String Nnombre) {
		return new Ingrediente(Nnombre);
	}
	public static void revisarCaducidad(Ingrediente ingrediente, int cantidad){
		Random numAleatorio = new Random();
	     int caducidad = numAleatorio.nextInt(30);
	     if(caducidad == probabilidadConstante){
	    	 String ingredienteId = ingrediente.getId();
	    	 Panaderia.restarIngrediente(ingredienteId, cantidad);
	     }
	}
}

package gestorAplicación.comida;

import java.io.Serializable;
import java.util.Random;

public class Ingrediente implements Serializable{
	private String nombre;
	// int cantidad;
	private double PrecioDeVenta;
	private double PrecioDeCompra;

	// constructores sobrecargados

	public Ingrediente(String nombre1, double PrecioDeVenta,double PrecioDeCompra) {
		this.nombre = nombre1;
		this.PrecioDeVenta = PrecioDeVenta;
		this.PrecioDeCompra = PrecioDeCompra;
	}

	public Ingrediente(String nombre1) {
		this.nombre = nombre1;
		Random aleatorio = new Random();
		double numeroAleatorio = aleatorio.nextInt(2701) + 300; // Genera un entero entre 0 y 100 (ambos inclusive).
		this.PrecioDeVenta = numeroAleatorio;
		double numeroAleatorioCompra = numeroAleatorio*(2.0/3.0);
		this.PrecioDeCompra =  Math.ceil(numeroAleatorioCompra);
	}
	// getters y setters de los atributos

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String Newnombre) {
		this.nombre = Newnombre;
	}

	// public int getCantidad() {return cantidad;}
	// public void setCantidad(int Newcantidad) {this.cantidad = Newcantidad;}

	

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
	
	public static Ingrediente crearIngrediente(String Nnombre, double NPrecioDeVenta,double NPrecioDeCompra) {
		return new Ingrediente(Nnombre, NPrecioDeVenta,NPrecioDeCompra);
	}

	public static Ingrediente crearIngrediente(String Nnombre) {
		return new Ingrediente(Nnombre);
	}
}

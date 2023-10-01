package comida;

import java.util.Random;

public class Ingrediente {
	private String nombre;
	// int cantidad;
	private float precio;

	// constructores sobrecargados

	public Ingrediente(String nombre1, float precio1) {
		this.nombre = nombre1;
		this.precio = precio1;
	}

	public Ingrediente(String nombre1) {
		this.nombre = nombre1;
		Random aleatorio = new Random();
		int numeroAleatorio = aleatorio.nextInt(2701) + 300; // Genera un entero entre 0 y 100 (ambos inclusive).
		this.precio = numeroAleatorio;
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
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(float Newprecio) {
		this.precio = Newprecio;
	}

	// metodos para crear

	public static Ingrediente crearIngrediente(String Nnombre, float Nprecio) {
		return new Ingrediente(Nnombre, Nprecio);
	}

	public static Ingrediente crearIngrediente(String Nnombre) {
		return new Ingrediente(Nnombre);
	}
}

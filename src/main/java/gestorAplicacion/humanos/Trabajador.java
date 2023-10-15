package gestorAplicacion.humanos;

import java.util.Random;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.gestion.Canasta;
import java.io.Serializable;
import java.util.Map;

public abstract class Trabajador implements Serializable{
    protected String nombre;
    protected double habilidad;
    protected double calificacion;
    protected double dineroEnMano;
    protected double salario;
    protected boolean robado;

    public Trabajador() {
        this("John Doe", 0, 0, 1000000);
    }

    public Trabajador(String nombre) {
        this(nombre, 0, 0, 1000000);
    }

    public Trabajador(String nombre, double calificacion, double dineroEnMano, double salario) {
        Random rand = new Random();

        this.nombre = nombre;
        this.calificacion = calificacion;
        this.habilidad = rand.nextInt(10) + 1; // 1 <= habilidad <= 10
        this.dineroEnMano = dineroEnMano;
        this.salario = salario;
        this.robado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public double getHabilidad() {
        return habilidad;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public double getDineroEnMano() {
        return dineroEnMano;
    }

    public double getSalario() {
        return salario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHabilidad(double habilidad) {
        this.habilidad = habilidad;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public void setDineroEnMano(double dineroEnMano) {
        this.dineroEnMano = dineroEnMano;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

	public boolean isRobado() {
		return robado;
	}

	public void setRobado(boolean robado) {
		this.robado = robado;
	}

	public abstract boolean laborParticular(Canasta canasta);

	public abstract boolean conseguirIngredientes(Map<String, Integer> listingredientes);

}

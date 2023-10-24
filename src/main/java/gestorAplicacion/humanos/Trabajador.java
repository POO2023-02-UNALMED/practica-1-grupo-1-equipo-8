package gestorAplicacion.humanos;

import java.util.Random;
import gestorAplicacion.gestion.Canasta;
import java.io.Serializable;
import java.util.Map;
import gestorAplicacion.gestion.Panaderia;

/**
 * Clase abstracta que representa a un trabajador en la panadería.
 * Contiene atributos como nombre, habilidad, calificación, dinero en mano, salario, y si ha sido robado o no.
 * También tiene una referencia a la panadería en la que trabaja y métodos abstractos para realizar labores particulares y conseguir ingredientes para una receta.
 */
public abstract class Trabajador implements Serializable{
    protected String nombre;
    protected double habilidad;
    protected double calificacion;
    protected double dineroEnMano;
    protected double salario;
    protected boolean robado;
    protected Panaderia panaderia;

//constructores
    public Trabajador() {
        this("John Doe", 0, 0, 1000);
        panaderia.getTrabajadores().add(this);
    }

    public Trabajador(String nombre) {
        this(nombre, 0, 0, 1000);
        panaderia.getTrabajadores().add(this);
    }

    public Trabajador(String nombre,boolean x){
        this("Sergio",0,0,1000);
    }

    public Trabajador(String nombre, Panaderia panaderia){
        Random rand = new Random();
        this.nombre = nombre;
        this.calificacion = 0;
        this.habilidad = rand.nextInt(10) + 9; // 9 <= habilidad <= 19
        this.dineroEnMano = 0;
        this.salario = 1000;
        this.robado = true;
        this.panaderia = panaderia;
        panaderia.getTrabajadores().add(this);
    }

    public Trabajador(String nombre,double calificacion, double dineroEnMano,double salario, Panaderia panaderia,boolean x){
        Random rand=new Random();
        this.nombre = nombre;
        this.calificacion= 0;
        this.habilidad = rand.nextInt(10) + 9;
        this.dineroEnMano=0;
        this.salario=1000;
        this.panaderia = panaderia;
        panaderia.getTrabajadores().add(this);
    }

    public Trabajador(String nombre, double calificacion, double dineroEnMano, double salario) {
        Random rand = new Random();

        this.nombre = nombre;
        this.calificacion = calificacion;
        this.habilidad = rand.nextInt(10) + 9; // 9 <= habilidad <= 19
        this.dineroEnMano = dineroEnMano;
        this.salario = salario;
        this.robado = true;
        panaderia.getTrabajadores().add(this);
    }

//getters y setters
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

/**
 * Método abstracto que indica si el trabajador puede realizar una labor particular con una canasta dada.
 * @param canasta La canasta que se va a utilizar para la labor.
 * @return true si el trabajador puede realizar la labor con la canasta dada, false en caso contrario.
 */
	public abstract boolean laborParticular(Canasta canasta);

/**
 * Método abstracto que representa la acción de conseguir ingredientes para una receta.
 * @param listingredientes un mapa que contiene los ingredientes necesarios para la receta y su cantidad requerida.
 * @return true si se logran conseguir todos los ingredientes, false en caso contrario.
 */
	public abstract boolean conseguirIngredientes(Map<String, Integer> listingredientes);

}

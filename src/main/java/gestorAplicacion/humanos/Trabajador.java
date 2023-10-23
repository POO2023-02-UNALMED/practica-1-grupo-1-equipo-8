package gestorAplicacion.humanos;

import java.util.Random;
import gestorAplicacion.gestion.Canasta;
import java.io.Serializable;
import java.util.Map;
import gestorAplicacion.gestion.Panaderia;

public abstract class Trabajador implements Serializable{
    protected String nombre;
    protected double habilidad;
    protected double calificacion;
    protected double dineroEnMano;
    protected double salario;
    protected boolean robado;
    protected Panaderia panaderia;

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

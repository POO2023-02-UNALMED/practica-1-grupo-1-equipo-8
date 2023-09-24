package humanos;
import java.util.Random;

public class Trabajador {
    String nombre;
    double habilidad;
    double dineroEnMano;

    public Trabajador() {
        this("Sin nombre", 0, 0);
    }

    public Trabajador(String nombre) {
        Random rand = new Random();
        this.nombre = nombre;
        this.habilidad = rand.nextDouble() * 10;
        this.dineroEnMano = 0;
    }

    public Trabajador(String nombre, double habilidad, double dineroEnMano) {
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.dineroEnMano = dineroEnMano;
    }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public double getHabilidad() {return habilidad;}

    public void setHabilidad(double habilidad) {this.habilidad = habilidad;}

    public double getDineroEnMano() {return dineroEnMano;}

    public void setDineroEnMano(double dineroEnMano) {this.dineroEnMano = dineroEnMano;}

    public String laborParticular() {return "No hay labor particular definida";}
}

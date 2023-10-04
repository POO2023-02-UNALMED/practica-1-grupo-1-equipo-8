package humanos;

import java.util.Random;

import gestion.Panaderia;

public abstract class Trabajador {
    String nombre;
    double habilidad;
    double dineroEnMano;
    Panaderia panaderia;

    public Trabajador() {
        this("John Doe", 0, 0, null);
    }

    public Trabajador(String nombre, Panaderia panaderia) {
        Random rand = new Random();
        this.nombre = nombre;
        this.habilidad = rand.nextDouble() * 10; // 0 <= habilidad <= 10
        this.dineroEnMano = 0;
        this.panaderia = panaderia;
    }

    public Trabajador(String nombre, double habilidad, double dineroEnMano, Panaderia panaderia) {
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.dineroEnMano = dineroEnMano;
        this.panaderia = panaderia;
    }

    public String getNombre() {
        return nombre;
    }

    public double getHabilidad() {
        return habilidad;
    }

    public double getDineroEnMano() {
        return dineroEnMano;
    }

    public Panaderia getPanaderia() {
        return panaderia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHabilidad(double habilidad) {
        this.habilidad = habilidad;
    }

    public void setDineroEnMano(double dineroEnMano) {
        this.dineroEnMano = dineroEnMano;
    }

    public void setPanaderia(Panaderia panaderia) {
        this.panaderia = panaderia;
    }

    public abstract boolean laborParticular();
}

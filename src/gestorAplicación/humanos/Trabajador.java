package gestorAplicación.humanos;

import java.util.Random;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gestorAplicación.comida.Ingrediente;
import gestorAplicación.gestion.Canasta;
import gestorAplicación.gestion.Panaderia;

public abstract class Trabajador implements Serializable{
    String nombre;
    double habilidad;
    double habilidadParticular;
    double dineroEnMano;
    boolean robado;

    public Trabajador() {
        this("John Doe", 0, 0);
    }

    public Trabajador(String nombre) {
        Random rand = new Random();
        this.nombre = nombre;
        this.habilidad = rand.nextDouble() * 10; // 0 <= habilidad <= 10
        this.dineroEnMano = 0;
        this.robado = false;
    }

    public Trabajador(String nombre, double habilidad, double dineroEnMano) {
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.dineroEnMano = dineroEnMano;
        this.robado = false;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHabilidad(double habilidad) {
        this.habilidad = habilidad;
    }

    public void setDineroEnMano(double dineroEnMano) {
        this.dineroEnMano = dineroEnMano;
    }

    public abstract boolean laborParticular(Canasta canasta, Cliente cliente); //no deberia recibir parametro cliente

}

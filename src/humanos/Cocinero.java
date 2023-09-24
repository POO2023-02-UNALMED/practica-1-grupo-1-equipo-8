package humanos;
import java.util.Random;
import gestion.Panaderia;
import comida.Ingrediente;
import comida.Producto;

public class Cocinero extends Trabajador {
    String especialidad;

    public Cocinero() {
        super();
    }

    public Cocinero(String nombre, String especialidad) {
        super(nombre);
        this.especialidad = especialidad;
    }

    public Cocinero(String nombre, double habilidad, double dineroEnMano, String especialidad) {
        super(nombre, habilidad, dineroEnMano);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {return especialidad;}

    public void setEspecialidad(String especialidad) {this.especialidad = especialidad;}

    @Override
    public void laborParticular(Producto producto, int cantidad) {
        Random rand = new Random();
        double quemar = rand.nextDouble() * 10;
        // obtener ingredientes, restarlos del invetario
        // comparar si se quema comparando con la habilidad
        // si se quema, no se agrega el producto
        // si no se quema, se agrega el producto
        // se suman el producto al inventario
        // repetir tantas veces como cantidad
        // si no hay ingredientes toca pedir plata e ir a comprar
    }
}


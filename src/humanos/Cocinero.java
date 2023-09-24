package humanos;
import java.lang.reflect.Array;
import java.util.Random;
import gestion.Panaderia;
import comida.Ingrediente;
import comida.Producto;
import java.util.ArrayList;
import java.util.Map;

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
    public void laborParticular(Producto producto, int cantidadDeseada) {
        Random rand = new Random();

        int cantidadCocinada = 0;

        while (cantidadCocinada < cantidadDeseada) {
            Map<String, Integer> ingredientesRequeridos = producto.getIngredientes();
            for (String nombreIngrediente : ingredientesRequeridos.keySet()) {
                int cantidadRequerida = ingredientesRequeridos.get(nombreIngrediente);
                for (Ingrediente ingrediente : inventario) {
                    if (ingrediente.getNombre().equals(nombreIngrediente) && ingrediente.getCantidad() >= cantidadRequerida) {
                        ingrediente.restarIngrediente(cantidadRequerida);
                    } /*else {
                        puedeCocinar = false;
                        break;
                    }
                }
                if (!puedeCocinar) {
                    System.out.println("No se pueden cocinar más productos debido a la falta de ingredientes.");
                    break;
                }
            }
            if (!puedeCocinar) {
                break;
            }
            double quemar = rand.nextDouble() * 10;
            if (quemar <= probabilidadQuemar) {
                System.out.println("El cocinero se quemó. Deteniendo la cocción.");
                break;
            }
            cantidadCocinada++;
        }
        System.out.println("El cocinero ha cocinado " + cantidadCocinada + " unidades de " + producto.getNombre());
    }
}
*/

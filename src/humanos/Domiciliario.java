package humanos;
import java.util.Map;
import java.util.Random;

import gestion.Panaderia;

public class Domiciliario extends Trabajador {
    Boolean licencia;

    public Domiciliario() {
        super();
        this.licencia = false;
    }

    public Domiciliario(String nombre, Boolean licencia) {
        super(nombre);
        this.licencia = licencia;
    }

    public Domiciliario(String nombre, double habilidad, double dineroEnMano, Boolean licencia) {
        super(nombre, habilidad, dineroEnMano);
        this.licencia = licencia;
    }

    public Boolean isLicencia() {return licencia;}

    public void setLicencia(Boolean licencia) {this.licencia = licencia;}

    //@Override
    // Método para conseguir ingredientes de la Panaderia
    public void conseguirIngrediente(Panaderia panaderia, Map<String, Integer> ingredientesAComprar) {
        // Calcular el costo total de los ingredientes
        double costoTotal = 0.0;
        for (Map.Entry<String, Integer> entry : ingredientesAComprar.entrySet()) {
            String nombreIngrediente = entry.getKey();
            int cantidadAComprar = entry.getValue();
            // Supongamos que hay un precio por cada ingrediente
            double precioPorIngrediente = obtenerPrecioPorIngrediente(nombreIngrediente);
            costoTotal += precioPorIngrediente * cantidadAComprar;
        }

        // Pedir dinero prestado a la Panaderia
        panaderia.prestarDinero(costoTotal);

        // Comprar los ingredientes y agregarlos al inventario de la Panaderia
        for (Map.Entry<String, Integer> entry : ingredientesAComprar.entrySet()) {
            String nombreIngrediente = entry.getKey();
            int cantidadAComprar = entry.getValue();
            panaderia.agregarIngrediente(nombreIngrediente, cantidadAComprar);
        }
    }
}

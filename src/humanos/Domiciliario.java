package humanos;

import java.util.Map;
import java.util.Random;
import java.util.ArrayList;

import gestion.Panaderia;
import gestion.Canasta;
import humanos.Cliente.Direccion;
import gestion.Recibo;
import java.util.ArrayList;

public class Domiciliario extends Trabajador {
    Boolean licencia;

    public Domiciliario() {
        super();
        this.licencia = false;
    }

    public Domiciliario(String nombre, Panaderia panaderia, Boolean licencia) {
        super(nombre, panaderia);
        this.licencia = licencia;
    }

    public Domiciliario(String nombre, double habilidad, double dineroEnMano, Panaderia panaderia, Boolean licencia) {
        super(nombre, habilidad, dineroEnMano, panaderia);
        this.licencia = licencia;
    }

    public Boolean isLicencia() {
        return licencia;
    }

    public void setLicencia(Boolean licencia) {
        this.licencia = licencia;
    }

    // @Override
    // Método para conseguir ingredientes de la Panaderia
    /*public void conseguirIngrediente(Panaderia panaderia, Map<String, Integer> ingredientesAComprar) {
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
    }*/

    public boolean laborParticular(ArrayList<Canasta> canastas, Cliente cliente){
        Direccion direccion = cliente.getDireccion();
        Random rand = new Random();
        int probabilidad = rand.nextInt(100);
        if (probabilidad > habilidad){
            return false;
        }
        else{
            // Se le cobra al cliente
            double costoTotal = 0.0;
            for (Canasta canasta : canastas) {
                costoTotal += canasta.generarCosto();
            }
            cliente.setPresupuesto(cliente.getPresupuesto() - costoTotal);
            // Se le paga al domiciliario
            dineroEnMano += costoTotal;
            // Se le agrega el recibo al cliente
            Recibo recibo = new Recibo(canastas, direccion, this);
            cliente.getRecibos().add(recibo);
            // Se le agrega el recibo al domiciliario
            recibos.add(recibo);
            return true;
        }
    }
}

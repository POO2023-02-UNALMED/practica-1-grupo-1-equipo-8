package gestorAplicación.humanos;

import java.util.Map;
import java.util.Random;

import gestorAplicación.comida.Ingrediente;
import gestorAplicación.gestion.Canasta;
import gestorAplicación.gestion.Panaderia;
import gestorAplicación.gestion.Recibo;
import gestorAplicación.humanos.Cliente.Direccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ArrayList;

public class Domiciliario extends Trabajador implements Serializable{
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

    

    public boolean laborParticular(Canasta canasta, Cliente cliente){
        Direccion direccion = cliente.getDireccion();
        Random rand = new Random();
        int probabilidad = rand.nextInt(100);
        if (probabilidad > habilidad){
            this.habilidad++;
            return false;
        }
        else{
            // Se le cobra al cliente
        	canasta.generarCosto();
            double costoTotal = canasta.getCostoTotal();
            cliente.setPresupuesto(cliente.getPresupuesto() - costoTotal);
            // Se le paga al domiciliario
            dineroEnMano += costoTotal;
            // Se le agrega el recibo al cliente
            Recibo recibo = new Recibo(canasta, direccion, cliente);
            cliente.getRecibos().add(recibo);
            // Se le agrega el recibo al domiciliario
            cliente.getRecibos().add(recibo);
            return true;
        }
    }

    public boolean conseguirIngredientes(Map<Ingrediente, Integer> listingredientes){
        
        double valorcompra = 0;
        this.robado = false;

        for (Map.Entry<Ingrediente, Integer> ingrediente : listingredientes.entrySet()){

        int cantidad = ingrediente.getValue();
        valorcompra += (ingrediente.getKey().getPrecioDeCompra())*(cantidad*2);

     }

     if (valorcompra <= Panaderia.getDinero()){

        this.dineroEnMano += valorcompra;
        Panaderia.setDinero((float) (Panaderia.getDinero()-valorcompra));

     }

     else{

        Panaderia.conseguirPrestamo( (float) valorcompra);
        this.dineroEnMano += valorcompra;
        Panaderia.setDinero((float) (Panaderia.getDinero()-valorcompra));

     }

     Random numAleatorio = new Random();

     double habilidadLadron = numAleatorio.nextDouble() * 10;

     if (habilidadLadron > this.habilidad){

        this.dineroEnMano = 0;
        this.robado = true;
        return this.robado;
     }

     else{

        this.dineroEnMano = 0;

        for (Map.Entry<Ingrediente, Integer> ingrediente : listingredientes.entrySet()){
            
            int cantidad = ingrediente.getValue();
            Panaderia.agregarIngrediente(ingrediente.getKey(),(cantidad*2));
        }

        return this.robado;
    
     }
    }

}

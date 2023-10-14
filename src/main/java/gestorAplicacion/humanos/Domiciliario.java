package gestorAplicacion.humanos;

import java.util.Map;
import java.util.Random;
import java.util.List;

import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.gestion.Recibo;
import gestorAplicacion.humanos.Cliente.Direccion;

import java.io.Serializable;
import java.util.ArrayList;

public class Domiciliario extends Trabajador implements Serializable{
    Boolean licencia;
    Boolean ocupado;

    public Domiciliario() {
        super();
        this.licencia = false;
        this.licencia = false;
    }

    public Domiciliario(String nombre, double habilidad, double dineroEnMano, Boolean licencia) {
        super(nombre, habilidad, dineroEnMano);
        this.licencia = licencia;
        this.ocupado = false;
    }

    public Boolean isLicencia() {
        return licencia;
    }

    public void setLicencia(Boolean licencia) {
        this.licencia = licencia;
    }

    public Boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }


    public boolean laborParticular(ArrayList<Canasta> canasta){
        Random rand = new Random();
        int probabilidad = rand.nextInt(10);
        if (probabilidad > habilidad){
            this.habilidad++;
            return false;
        } 
        return true;
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

        for (Map.Entry<Ingrediente, Integer> compras : listingredientes.entrySet()){
        	
        	int cantidad = compras.getValue()*2;
            Ingrediente ingrediente = compras.getKey();
            
            for (Map.Entry<Ingrediente, Integer> Inventario : Panaderia.getInvIngredientes().entrySet()) {
            	
            	String idInventario = Inventario.getKey().getId();
            	
            	if (ingrediente.getId() == idInventario) {
            		
            		Panaderia.getInvIngredientes().put(Inventario.getKey(), Inventario.getValue() + cantidad);
            		break;
            		
            	}
            	
            }
        	
        }

        return this.robado;
    
     }
    
   }

}

package gestorAplicacion.humanos;

import java.util.Map;
import java.util.Random;

import UIMain.GestionDomicilioCliente;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.gestion.Panaderia;

import java.io.Serializable;

public class Domiciliario extends Trabajador implements Serializable{
    Boolean licencia;
    Boolean ocupado;
    Canasta canasta;

    public Domiciliario() {
        super();
        this.licencia = false;
        this.ocupado = false;
        this.canasta = null;
    }

    public Domiciliario(String nombre, double habilidad, double calificacion, double dineroEnMano, Boolean licencia) {
        super(nombre, habilidad, calificacion, dineroEnMano);
        this.licencia = licencia;
        this.ocupado = false;
        this.canasta = null;
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

    public Canasta getCanasta() {
        return canasta;
    }

    public void setCanasta(Canasta canasta) {
        this.canasta = canasta;
    }


    public boolean laborParticular(Canasta canasta){
        Catastrofe malechor = new Catastrofe();
        if (malechor.pincharLLanta(this)){
            boolean x = false;
            GestionDomicilioCliente.estadoDomicilio(x);
            return x;
        }
        else{
            this.canasta = null;
            this.ocupado = false;
            boolean x = true;
            GestionDomicilioCliente.estadoDomicilio(x);
            return x;
        }
    }

    //TODO: arreglar porque ya no se estan implementando los hasmasps de antes. Hay que hablar con TEO
    public boolean conseguirIngredientes(Map<Ingrediente, Integer> listingredientes){
        
        double valorcompra = 0;
        this.robado = false;

        for (Map.Entry<Ingrediente, Integer> ingrediente : listingredientes.entrySet()){

        int cantidad = ingrediente.getValue();
        valorcompra += (ingrediente.getKey().getPrecioDeCompra())*(cantidad*2);

     }

     if (valorcompra <= Panaderia.getDinero()){

        this.dineroEnMano += valorcompra;
        Panaderia.setDinero((double) (Panaderia.getDinero()-valorcompra));

     }

     else{

        Panaderia.conseguirPrestamo( (double) valorcompra);
        this.dineroEnMano += valorcompra;
        Panaderia.setDinero((double) (Panaderia.getDinero()-valorcompra));

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

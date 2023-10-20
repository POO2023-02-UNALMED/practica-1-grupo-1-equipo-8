package gestorAplicacion.humanos;

import java.util.Map;
import java.util.Random;
import java.util.ArrayList;

import UIMain.GestionDomicilioCliente;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.comida.ProductoFrio;

import java.io.Serializable;

public class Domiciliario extends Trabajador{
    Boolean licencia;
    Boolean ocupado;
    Canasta canasta;
    private boolean empaqueFrio;
    private Panaderia panaderia;

    public Domiciliario() {
        super();
        this.licencia = false;
        this.ocupado = false;
        this.canasta = null;
    }

    public Domiciliario(String nombre) {
        super(nombre);
        this.licencia = false;
        this.ocupado = false;
        this.canasta = null;
    }

    public Domiciliario(String nombre, double habilidad, double calificacion, double dineroEnMano) {
        super(nombre, habilidad, calificacion, dineroEnMano);
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

    public boolean isEmpaqueFrio() {
        return empaqueFrio;
    }

    public void setEmpaqueFrio(boolean empaqueFrio) {
        this.empaqueFrio = empaqueFrio;
    }

    public Panaderia getPanaderia() {
		return panaderia;
	}

	public void setPanaderia(Panaderia panaderia) {
		this.panaderia = panaderia;
	}

	public Boolean getLicencia() {
		return licencia;
	}

	public Boolean getOcupado() {
		return ocupado;
	}

	public boolean laborParticular(Canasta canasta){
        
        Catastrofe transito = new Catastrofe();
        if (transito.paradaTransito(this)){
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

    //Este método se ejecuta cuando se llama el método comprarIngredientes de Panadería, basicamnete se encarga de comprar ingedientes cuando falta y añadirlos al inventario
    //Recibe una diccionario de Strings y enteros paea saber exactamente qué se debe comprar y cuánta cantidad
    //Cuando se "compran" nuevos ingredientes realmente se están creando objetos y añandiendose al inventario
    
    public boolean conseguirIngredientes(Map<String, Integer> listingredientes) {

        double valorcompra = 0;
        this.robado = false;
        
        Ingrediente.organizarTopMasVendidos();
        ArrayList<Ingrediente> top = Ingrediente.getTopMasVendidos();

        for (Map.Entry<String, Integer> ingrediente : listingredientes.entrySet()) {

            int cantidad = ingrediente.getValue();            
            Ingrediente necesitado = Ingrediente.obtenerObjetoPorNombre(ingrediente.getKey());
            int cantidadexistente = this.getPanaderia().getInventario().verificarCantidadIngredientePorId(necesitado.getId());
            
            if (top.contains(necesitado)) { //Pedir ayuda revisión
            	
            	if ((cantidad * 2) + cantidadexistente < 40) {
            		
            		 valorcompra += (necesitado.getPrecioDeCompra())  * (cantidad * 2);            		
            	}
            	
            	else if (cantidad + cantidadexistente < 40){
            		
            		valorcompra += (necesitado.getPrecioDeCompra() * cantidad);
            		
            	}
            	
            }
            
            else {
            	
            	if (cantidad + cantidadexistente < 20) {
            		
            		valorcompra += (necesitado.getPrecioDeCompra() * cantidad);
            		
            	}
            	
            }
            
        }

        if (valorcompra <= this.panaderia.getDinero()) {

            this.dineroEnMano += valorcompra;
            this.panaderia.setDinero((double) (this.panaderia.getDinero() - valorcompra));

        }

        else {

            this.panaderia.conseguirPrestamo((double) valorcompra);
            this.dineroEnMano += valorcompra;
            this.panaderia.setDinero((double) (this.panaderia.getDinero() - valorcompra));

        } 
        
        Catastrofe Ladron = Catastrofe.responsableAleatorio();
        Domiciliario postRobo = Ladron.robarComprador((Cocinero) this);

        if (postRobo.robado = true) {

        	 return this.robado;
        }

        else {

            this.dineroEnMano = 0;

            for (Map.Entry<String, Integer> compras : listingredientes.entrySet()) {

                int cantidad = compras.getValue();
                String ingrediente = compras.getKey();
                Ingrediente necesitado = Ingrediente.obtenerObjetoPorNombre(ingrediente);
                int cantidadexistente = this.getPanaderia().getInventario().verificarCantidadIngredientePorId(necesitado.getId());
                
                if (top.contains(necesitado) & ((cantidad * 2) + cantidadexistente) < 40) {
                	
                	for (int i = 0; i < cantidad*2; i++) {
                        Ingrediente ingrdt = Ingrediente.crearIngrediente(ingrediente);
                        this.panaderia.getInventario().agregarIngrediente(ingrdt);
                    }
                
                }
                
                else if (top.contains(necesitado) & (cantidad + cantidadexistente) < 40){
                	
                	for (int i = 0; i < cantidad; i++) {
                        Ingrediente ingrdt = Ingrediente.crearIngrediente(ingrediente);
                        this.panaderia.getInventario().agregarIngrediente(ingrdt);
                    }           	
                }
                
                else {
                	
                	for (int i = 0; i < cantidad; i++) {
                        Ingrediente ingrdt = Ingrediente.crearIngrediente(ingrediente);
                        this.panaderia.getInventario().agregarIngrediente(ingrdt);
                    }           	
                	
                }
                
            }

            return this.robado;
       }
    }

 }

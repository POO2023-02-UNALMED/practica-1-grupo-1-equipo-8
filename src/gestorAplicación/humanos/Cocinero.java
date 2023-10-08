package gestorAplicación.humanos;

import java.lang.reflect.Array;

import gestorAplicación.comida.Ingrediente;
import gestorAplicación.comida.Producto;
import gestorAplicación.gestion.Canasta;
import gestorAplicación.gestion.Panaderia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Cocinero extends Trabajador {
    private String especialidad;
    private boolean quemado = false;

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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isQuemado(){
        return quemado;
    }

    public void setQuemado(boolean quemado) {
        this.quemado = quemado;
    }

public Map<Ingrediente, Integer> ingredientesCocinero (Producto producto) {
    Map<Ingrediente,Integer> ingredientesNecesarios = producto.getIngredientes();
    Map<Ingrediente, Integer> ingrFaltantes = Panaderia.revisarCantidadIngredientes(ingredientesNecesarios);
    return ingrFaltantes;
}
public boolean productoQuemado(Producto producto){
    Random numAleatorio = new Random();
     double dificultadProducto = numAleatorio.nextDouble() * 10; //poner menos probabilidad
     if(dificultadProducto > this.habilidadParticular){
        setQuemado(true);
        return isQuemado();
     }
     return isQuemado();
}


@Override
public boolean laborParticular(Canasta canasta) {
    Map<Producto, Integer> productos = canasta.getProductos();
	for (Map.Entry<Producto, Integer> product : productos.entrySet()) {
        Producto producto = product.getKey();
        Map<Ingrediente,Integer> ingrFaltantes= ingredientesCocinero(producto);
        if(!ingrFaltantes.isEmpty()){
            Panaderia.comprarIngredientes(ingrFaltantes);
            for (Map.Entry<Ingrediente, Integer> faltante : ingrFaltantes.entrySet()){
                Ingrediente ingFaltante = faltante.getKey();
                String ingrfaltante = ingFaltante.getNombre();
                Integer cantidad = faltante.getValue();
                Panaderia.restarIngrediente(ingrfaltante,cantidad);
            }
            return false;
        }
    } 
    for (Map.Entry<Producto, Integer> product : productos.entrySet()) {
        Producto producto = product.getKey();
        boolean cocinado = productoQuemado(producto); //hacer esto por cada metodo de cocinar y que se llame al cocinero con esa especialidad
        if(!cocinado) {
            return false;
        }
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

        for (Map.Entry<Ingrediente, Integer> ingrediente : listingredientes.entrySet()){
            
            int cantidad = ingrediente.getValue();
            Panaderia.agregarIngrediente(ingrediente.getKey(),(cantidad*2));
        }

        return this.robado;
    
    }
}
}

// use hasmaps y sale facil get datos

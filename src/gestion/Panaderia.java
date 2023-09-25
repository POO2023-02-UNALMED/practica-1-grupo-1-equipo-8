package gestion;
import java.util.HashMap;
import java.util.Map;

import humanos.Cocinero;
import humanos.Cliente;
import comida.Ingrediente;
import comida.Producto;
import java.util.List;
import java.util.ArrayList;

public class Panaderia {
    private Map<String, Integer> invIngredientes;

    private List<Producto> invProductos = new ArrayList<Producto>();
    private List<Cocinero> cocineros = new ArrayList<Cocinero>();
    private List<Cliente> clientes = new ArrayList<Cliente>();
    private float dinero;
    private static List<Producto> productosEnDescuento = new ArrayList<Producto>();
    static{
        //Agregar lista de productos en descuento para la canasta
        productosEnDescuento.add(0, null);;
    }

    public Panaderia() {

    }

    // metodos get:
    public List<Ingrediente> getInvIngredientes() {return invIngredientes;}

    public List<Producto> getInvProductos() {return invProductos;}

    public List<Cocinero> getCocineros() {return cocineros;}

    public List<Cliente> getClientes() {return clientes;}

    public float getDinero() {return dinero;}

    public static List<Producto> getProductosEnDescuento() {return productosEnDescuento;}

    // metodos set:
    public void setInvIngredientes(List<Ingrediente> invIngredientes) {
        this.invIngredientes = invIngredientes;
    }

    public void setInvProductos(List<Producto> invProductos) {
        this.invProductos = invProductos;
    }

    public void setCocineros(List<Cocinero> cocineros) {
        this.cocineros = cocineros;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setDinero(float dinero) {
        this.dinero = dinero;
    }
    public static void setProductosEnDescuento(List<Producto> productos){productosEnDescuento=productos;}

    // otros metodos:
    public void agregarIngrediente(Ingrediente ingrediente) {
        invIngredientes.add(ingrediente);
    }

    public void agregarProducto(Producto producto) {
        invProductos.add(producto);
    }

    public void agregarCocinero(Cocinero cocinero) {
        cocineros.add(cocinero);
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void eliminarIngrediente(Ingrediente ingrediente) {
        invIngredientes.remove(ingrediente);
    }

    public void eliminarProducto(Producto producto) {
        invProductos.remove(producto);
    }

    public void eliminarCocinero(Cocinero cocinero) {
        cocineros.remove(cocinero);
    }

    public void eliminarCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    public void agregarDinero(float dinero) {
        this.dinero += dinero;
    }

    public void restarDinero(float dinero) {
        this.dinero -= dinero;
    }

    // Método para agregar un ingrediente al inventario
    public void agregarIngrediente(String nombre, int cantidad) {
        if (invIngredientes.containsKey(nombre)) {
            // Si el ingrediente ya existe, actualiza la cantidad
            int cantidadExistente = invIngredientes.get(nombre);
            invIngredientes.put(nombre, cantidadExistente + cantidad);
        } else {
            // Si el ingrediente no existe, agrégalo al inventario
            invIngredientes.put(nombre, cantidad);
        }
    }

    // Método para obtener la cantidad de un ingrediente en el inventario
    public int obtenerCantidadIngrediente(String nombre) {
        if (invIngredientes.containsKey(nombre)) {
            return invIngredientes.get(nombre);
        }
        // Si el ingrediente no está en el inventario, la cantidad es 0
        return 0;
    }

    public void restarIngrediente(String nombre, int cantidad) {
        if (invIngredientes.containsKey(nombre)) {
            int cantidadExistente = invIngredientes.get(nombre);
            if (cantidadExistente >= cantidad) {
                // Si hay suficiente cantidad del ingrediente, resta la cantidad especificada
                invIngredientes.put(nombre, cantidadExistente - cantidad);
            }
        }
    }
}

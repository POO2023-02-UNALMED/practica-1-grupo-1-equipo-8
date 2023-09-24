package gestion;

import humanos.Cocinero;
import humanos.Cliente;
import comida.Ingrediente;
import comida.Producto;
import java.util.List;
import java.util.ArrayList;

public class Panaderia {
    private List<Ingrediente> invIngredientes = new ArrayList<Ingrediente>();
    private List<Producto> invProductos = new ArrayList<Producto>();
    private List<Cocinero> cocineros = new ArrayList<Cocinero>();
    private List<Cliente> clientes = new ArrayList<Cliente>();
    private float dinero;

    public Panaderia() {

    }

    // metodos get:
    public List<Ingrediente> getInvIngredientes() {
        return invIngredientes;
    }

    public List<Producto> getInvProductos() {
        return invProductos;
    }

    public List<Cocinero> getCocineros() {
        return cocineros;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public float getDinero() {
        return dinero;
    }

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
}


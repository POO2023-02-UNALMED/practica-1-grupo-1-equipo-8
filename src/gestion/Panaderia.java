package gestion;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import humanos.Cliente;
import comida.Ingrediente;
import comida.Producto;
import humanos.Trabajador;
import humanos.Cocinero;
import humanos.Domiciliario;

public class Panaderia {
    private static Map<Ingrediente, Integer> invIngredientes= new HashMap<Ingrediente, Integer>();
    private static Map<Producto,Integer> invProductos = new HashMap<Producto,Integer>();
    private List<Trabajador> trabajadores = new ArrayList<Trabajador>();
    private List<Cocinero> cocineros = new ArrayList<Cocinero>();
    private List<Domiciliario> domiciliarios = new ArrayList<Domiciliario>();
    private List<Cliente> clientes = new ArrayList<Cliente>();
    private float dinero;
    private static List<Producto> productosEnDescuento = new ArrayList<Producto>();
    private static Canasta canastaDelDia;
    static {
        // Agregar lista de productos en descuento para la canasta
        productosEnDescuento.add(0, null);
        ;
        // Agregar lista de productos de la canasta del dia
        canastaDelDia = new Canasta(null, null);
    }

    public Panaderia() {

    }

    // metodos get:
    // public List<Ingrediente> getInvIngredientes() {return invIngredientes;}

    public static Map<Producto,Integer> getInvProductos() {
        return invProductos;
    }

    public static Map<Ingrediente, Integer> getInvIngredientes() {
        return invIngredientes;
    }

    public static List<Producto> getProductosEnDescuento() {
        return productosEnDescuento;
    }

    public static Canasta getCanastaDelDia() {
        return canastaDelDia;
    }

    public List<Trabajador> getTrabajadores() {
		return trabajadores;
	}

	public List<Cocinero> getCocineros() {
		return cocineros;
	}

	public List<Domiciliario> getDomiciliarios() {
		return domiciliarios;
	}

	public List<Cliente> getClientes() {
        return clientes;
    }

    public float getDinero() {
        return dinero;
    }

    // metodos set:
    // public void setInvIngredientes(List<Ingrediente> invIngredientes) {
    // this.invIngredientes = invIngredientes;
    // }

    public static void setInvProductos(Map<Producto,Integer> newInvProductos) {
        invProductos = newInvProductos;
    }

    public static void setInvIngredientes(Map<Ingrediente, Integer> newInvIngredientes) {
        invIngredientes = newInvIngredientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setDinero(float dinero) {
        this.dinero = dinero;
    }

    public static void setProductosEnDescuento(List<Producto> productos) {
        productosEnDescuento = productos;
    }

    public void setTrabajadores(List<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}

	public void setCocineros(List<Cocinero> cocineros) {
		this.cocineros = cocineros;
	}

	public void setDomiciliarios(List<Domiciliario> domiciliarios) {
		this.domiciliarios = domiciliarios;
	}

	public static void setCanastaDelDia(Canasta canasta) {
        canastaDelDia = canasta;
    }

    // otros metodos:
    // public void agregarIngrediente(Ingrediente ingrediente) {
    // invIngredientes.add(ingrediente);
    // }

    public void agregarProducto(Producto producto, int cantidad) {
        invProductos.put(producto, cantidad);
    }

    public void agregarTrabajador(Trabajador cocinero) {
        trabajadores.add(cocinero);
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

    public void eliminarCocinero(Trabajador cocinero) {
        trabajadores.remove(cocinero);
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

    /**
     * Verifica si existe un producto con el nombre de pila en el inventario de lapanadería.
     * @param prdct el nombre del producto a buscar
     * @return verdadero si existe un producto con el nombre de pila, falso en casocontrario
     */
    public static boolean verificarExistenciaProductoPorNombre(String prdct){
        for (Map.Entry<Producto, Integer> productoEntry : invProductos.entrySet()) {
            Producto producto = productoEntry.getKey();
            if (producto.getNombre().equalsIgnoreCase(prdct)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si un ingrediente existe en el inventario por su nombre.
     * @param ingrd el nombre del ingrediente a verificar
     * @return verdadero si el ingrediente existe, falso en caso contrario
     */
    public static boolean verificarExistenciaIngredientePorNombre(String ingrd){
        for (Map.Entry<Ingrediente, Integer> ingredienteEntry : invIngredientes.entrySet()) {
            Ingrediente ingrediente = ingredienteEntry.getKey();
            if (ingrediente.getNombre().equalsIgnoreCase(ingrd)) {
                return true;
            }
        }
        return false;
    }

    // Método para agregar un ingrediente al inventario
    public void agregarIngrediente(Ingrediente ingrediente, int cantidad) {
        if (invIngredientes.containsKey(ingrediente)) {
            // Si el ingrediente ya existe, actualiza la cantidad
            int cantidadExistente = invIngredientes.get(ingrediente);
            invIngredientes.put(ingrediente, cantidadExistente + cantidad);
        } else {
            // Si el ingrediente no existe, agrégalo al inventario
            invIngredientes.put(ingrediente, cantidad);
        }
    }

    public void restarIngrediente(Ingrediente ingrediente, int cantidad) {
        if (invIngredientes.containsKey(ingrediente)) {
            int cantidadExistente = invIngredientes.get(ingrediente);
            if (cantidadExistente >= cantidad) {
                // Si hay suficiente cantidad del ingrediente, resta la cantidad especificada
                invIngredientes.put(ingrediente, cantidadExistente - cantidad);
            }
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

    public void prestarDinero(double cantidad){
        
    }

    //TODO: METODOS DE MATEO, NO TOCAR
    // Método para productos personalizados
    public static void crearProductoPersonalizado(String nombreProducto, Map<String, Integer> ingredientes) {
        //TODO: Desarrollar este metodo para crear producto personalizado
    }

    public static Map<Producto, Integer> cocinar(Map<String, Integer> productos){
        //TODO: Desarrollar este metodo para crear producto enviados por canasta
        Map<Producto, Integer> productosCocinados = new HashMap<Producto, Integer>();
        return productosCocinados;
    }

    public static Map<Ingrediente, Integer> agregarIngredientesACanasta(Map<String, Integer> ingredientes){
        //TODO: Desarrollar este metodo para agregar ingredientes a la canasta
        Map<Ingrediente, Integer> ingredientesCocinados = new HashMap<Ingrediente, Integer>();
        return ingredientesCocinados;
    }
    
    //Método sobrevargado registrarCliente
    
    public String registrarCliente(String nombre, Integer id, String tipoDescuento, float presupuesto, ArrayList<Canasta> canastas, ArrayList<Recibo> recibos) {
    	
    	Cliente cliente = new Cliente(nombre, id, tipoDescuento, presupuesto, canastas, recibos, this);
    	
    	this.clientes.add(cliente);
    	
    	return "Ha sido registrado como cliente con exito bajo el nombre: " + cliente.getNombre();
    	
    }
    
    public String registrarCliente(String nombre, Integer id, String tipoDescuento, float presupuesto, Panaderia panaderia) {
    	
    	Cliente cliente = new Cliente(nombre, id, tipoDescuento, presupuesto, this);
    	
    	this.clientes.add(cliente);
    	
    	return "Ha sido registrado como cliente con exito bajo el nombre: " + cliente.getNombre();
    
    }
    
    public String registrarCliente(String nombre, Integer id, float presupuesto, Panaderia panaderia) {
    	
    	Cliente cliente = new Cliente(nombre, id, presupuesto, this);
    	
    	this.clientes.add(cliente);
    	
    	return "Ha sido registrado como cliente con exito bajo el nombre: " + cliente.getNombre();
    
    }
    
    public void comprarIngredientes(List<Ingrediente> listingredientes) {
    	
    	
    	
    }
}

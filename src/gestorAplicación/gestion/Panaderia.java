package gestorAplicación.gestion;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import gestorAplicación.comida.Ingrediente;
import gestorAplicación.comida.Producto;
import gestorAplicación.humanos.Cliente;
import gestorAplicación.humanos.Cocinero;
import gestorAplicación.humanos.Domiciliario;
import gestorAplicación.humanos.Trabajador;

import java.util.Collections;

public class Panaderia implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Map<Ingrediente, Integer> invIngredientes= new HashMap<Ingrediente, Integer>();
    private static Map<Producto,Integer> invProductos = new HashMap<Producto,Integer>();
    private static ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
    private static ArrayList<Cocinero> cocineros = new ArrayList<Cocinero>();
    private static ArrayList<Domiciliario> domiciliarios = new ArrayList<Domiciliario>();
    private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private static float dinero;
    private static List<Producto> productosEnDescuento = new ArrayList<Producto>();
    private static Canasta canastaDelDia;
    private static float valorDeudas;
    private static boolean enQuiebra = false;
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

	public static List<Cliente> getClientes() {
        return clientes;
    }

    public static float getDinero() {
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

    public static void setClientes(ArrayList<Cliente> clientes) {
        Panaderia.clientes = clientes;
    }

    public static void setDinero(float dinero) {
        Panaderia.dinero = dinero;
    }

    public static void setProductosEnDescuento(List<Producto> productos) {
        productosEnDescuento = productos;
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
        Panaderia.dinero += dinero;
    }

    public void restarDinero(float dinero) {
        Panaderia.dinero -= dinero;
    }

    public static boolean saldarDeudas(){

        if (Panaderia.valorDeudas < Panaderia.dinero){

            Panaderia.dinero = Panaderia.dinero-Panaderia.valorDeudas;
            Panaderia.valorDeudas = 0;
            Panaderia.enQuiebra = false;
            return Panaderia.enQuiebra;
        }

        else {
            
            Panaderia.enQuiebra = true;
            Panaderia.dinero = 10000000;
            return Panaderia.enQuiebra;
            Panaderia.saldarDeudas();
        }

    }

    public static void conseguirPrestamo(float valorNecesitado) {

        if (Panaderia.valorDeudas == 0){

            Panaderia.dinero += valorNecesitado;
            Panaderia.valorDeudas = valorNecesitado;

        }

        else{

            Panaderia.saldarDeudas();
            Panaderia.dinero += valorNecesitado;
            Panaderia.valorDeudas = valorNecesitado;

        }

    }

    /**
     * Verifica si existe un producto con el nombre de pila en el inventario de
     * lapanadería.
     * 
     * @param prdct el nombre del producto a buscar
     * @return verdadero si existe un producto con el nombre de pila, falso en
     *         casocontrario
     */
    public static boolean verificarExistenciaProductoPorNombre(String prdct) {
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
     * 
     * @param ingrd el nombre del ingrediente a verificar
     * @return verdadero si el ingrediente existe, falso en caso contrario
     */
    public static boolean verificarExistenciaIngredientePorNombre(String ingrd) {
        for (Map.Entry<Ingrediente, Integer> ingredienteEntry : invIngredientes.entrySet()) {
            Ingrediente ingrediente = ingredienteEntry.getKey();
            if (ingrediente.getNombre().equalsIgnoreCase(ingrd)) {
                return true;
            }
        }
        return false;
    }

    // Método para agregar un ingrediente al inventario
    /**
     * Agrega un ingrediente al inventario de la panadería.
     * Si el ingrediente ya existe en el inventario, actualiza la cantidad.
     * Si el ingrediente no existe en el inventario, lo agrega con la cantidad especificada.
     * @param ingrediente el ingrediente a agregar o actualizar en el inventario
     * @param cantidad la cantidad del ingrediente a agregar o actualizar en el inventario
     */
    public static void agregarIngrediente(Ingrediente ingrediente, int cantidad) {
        if (invIngredientes.containsKey(ingrediente)) {
            // Si el ingrediente ya existe, actualiza la cantidad
            int cantidadExistente = invIngredientes.get(ingrediente);
            invIngredientes.put(ingrediente, cantidadExistente + cantidad);
        } else {
            // Si el ingrediente no existe, agrégalo al inventario
            invIngredientes.put(ingrediente, cantidad);
        }
    }

    /**
     * Resta la cantidad especificada de un ingrediente del inventario de la panadería.
     * @param ingrediente el nombre del ingrediente a restar
     * @param cantidad la cantidad a restar del ingrediente
     */
    public static void restarIngrediente(String ingrediente, int cantidad) {
        for (Map.Entry<Ingrediente, Integer> entry : invIngredientes.entrySet()) {
            Ingrediente I = entry.getKey();
            if (I.getNombre().equals(ingrediente)) {
                int cantidadExistente = entry.getValue();
                invIngredientes.put(I, cantidadExistente - cantidad);
                break;
            }
        }
    }

    /**
     * Resta la cantidad especificada de un ingrediente del inventario de la panadería.
     * Si la cantidad especificada es mayor a la cantidad existente del ingrediente en el inventario,
     * no se realiza ninguna acción.
     *
     * @param ingrediente el ingrediente a restar del inventario
     * @param cantidad la cantidad del ingrediente a restar
     */

    public static void restarIngrediente(Ingrediente ingrediente, int cantidad) {
        if (invIngredientes.containsKey(ingrediente)) {
            int cantidadExistente = invIngredientes.get(ingrediente);
            if (cantidadExistente >= cantidad) {
                // Si hay suficiente cantidad del ingrediente, resta la cantidad especificada
                invIngredientes.put(ingrediente, cantidadExistente - cantidad);
            }
        }
    }

    
    /**
     * Retorna la cantidad de un ingrediente específico en el inventario de la panadería.
     * @param ingrediente el nombre del ingrediente a buscar
     * @return la cantidad del ingrediente en el inventario, o 0 si no se encuentra
     */
    public static int obtenerCantidadIngrediente(String ingrediente) {
        for (Map.Entry<Ingrediente, Integer> entry : invIngredientes.entrySet()) {
            Ingrediente I = entry.getKey();
            if (I.getNombre().equals(ingrediente)) {
                return entry.getValue();
            }
        }
        return 0;
    }

    public void prestarDinero(double cantidad){
        
    }

    /**
     * Retorna un mapa de ingredientes y sus cantidades requeridas para hacer un producto dado.
     *
     * @param producto el nombre del producto para obtener los ingredientes
     * @return un mapa de ingredientes y sus cantidades requeridas para hacer el producto dado
     */
    public static Map<Ingrediente, Integer> ingredientesPorProducto(String producto) {
        Map<Ingrediente, Integer> ingredientes = new HashMap<>();
        for (Map.Entry<Producto, Integer> entry : invProductos.entrySet()) {
            Producto p = entry.getKey();
            if (p.getNombre().equals(producto)) {
                ingredientes = p.getIngredientes();
            break;
            }
        }
        return ingredientes;
    }

    /**
     * Este método verifica si hay suficientes ingredientes para hacer una receta.
     * @param ingredientes Un mapa que contiene los ingredientes y sus cantidades.
     * @return Un mapa que contiene los ingredientes faltantes y la cantidad necesaria para hacer la receta.
     */
    public static Map<Ingrediente, Integer> revisarCantidadIngredientes(Map<Ingrediente, Integer> ingredientes) {
        HashMap<Ingrediente, Integer> ingredientesFaltantes = new HashMap<Ingrediente, Integer>();
        for (Map.Entry<Ingrediente, Integer> entry : ingredientes.entrySet()) {
            Ingrediente ingrediente = entry.getKey();
            int cantidad = entry.getValue();
            int cantidadNecesaria = obtenerCantidadIngrediente(ingrediente.getNombre());
            if (cantidadNecesaria < cantidad) {
                ingredientesFaltantes.put(ingrediente, cantidadNecesaria - cantidad);
                }
        }
        return ingredientesFaltantes;
    }

    /**
     * Crea un producto personalizado con el nombre y los ingredientes dados.
     * @param nombreProducto el nombre del producto personalizado
     * @param ingredientes un mapa que contiene los ingredientes y sus respectivas cantidades
     */
    public static void crearProductoPersonalizado(String nombreProducto, Map<String, Integer> ingredientes) {
        Map<Ingrediente, Integer> ingredientesProducto = new HashMap<Ingrediente, Integer>();
        for (Map.Entry<String, Integer> ingrdts : ingredientes.entrySet()) {
            String ingrediente = ingrdts.getKey();
            Integer cantidad = ingrdts.getValue();
            double precioCompra = obtenerPrecioCompraIngrediente(ingrediente);
            double precioVenta = obtenerPrecioVentaIngrediente(ingrediente);
            if (!verificarExistenciaIngredientePorNombre(ingrediente)) {
                ingredientesProducto.put(new Ingrediente(ingrediente, precioVenta, precioCompra), cantidad);
            }
        }
        getInvProductos().put(new Producto(nombreProducto, ingredientesProducto), 0);
    }

    /**
     * Retorna el precio de compra de un ingrediente dado su nombre.
     * Si el ingrediente no se encuentra en el inventario, se crea un nuevo ingrediente con el nombre dado y se retorna su precio de compra.
     * @param ingrediente el nombre del ingrediente a buscar
     * @return el precio de compra del ingrediente
     */
    public static double obtenerPrecioCompraIngrediente(String ingrediente) {
        for (Map.Entry<Ingrediente, Integer> entry : invIngredientes.entrySet()) {
            Ingrediente I = entry.getKey();
            if (I.getNombre().equals(ingrediente)) {
                return I.getPrecioDeCompra();
            }
        }
        return new Ingrediente(ingrediente).getPrecioDeCompra();
    }

    /**
     * Retorna el precio de venta de un ingrediente dado.
     * Si el ingrediente está en el inventario, retorna su precio de venta.
     * Si no, crea un nuevo ingrediente con el nombre dado y retorna su precio de venta predeterminado.
     * @param ingrediente el nombre del ingrediente para obtener el precio de venta
     * @return el precio de venta del ingrediente
     */
    public static double obtenerPrecioVentaIngrediente(String ingrediente) {
        for (Map.Entry<Ingrediente, Integer> entry : invIngredientes.entrySet()) {
            Ingrediente i = entry.getKey();
            if (i.getNombre().equals(ingrediente)) {
                return i.getPrecioDeVenta();
            }
        }
        return new Ingrediente(ingrediente).getPrecioDeVenta();
    }

    /**
     * Cocina los productos dados creando un mapa de productos y sus respectivos ingredientes,
     * y luego utilizando un chef aleatorio para cocinarlos.
     * @param productos un mapa de nombres de productos y sus respectivas cantidades para cocinar
     * @return un mapa de productos cocinados y sus respectivas cantidades
     */
    public static Map<Producto, Integer> cocinar(Map<String, Integer> productos) {
        Map<Producto, Integer> productosParaCocinar = new HashMap<Producto, Integer>();
        for (Map.Entry<String, Integer> productoEntry : productos.entrySet()) {
            String producto = productoEntry.getKey();
            Integer cantidad = productoEntry.getValue();
            Map<Ingrediente, Integer> ingredientes = ingredientesPorProducto(producto);
            productosParaCocinar.put(new Producto(producto, ingredientes), cantidad);
        }
        Canasta canastaDeProducotsCocinar = new Canasta(productosParaCocinar, null);
        while(true){
            Cocinero cocinero = cocineroAleatorio();
            if(cocinero.laborParticular(canastaDeProducotsCocinar)){
                break;
            }
        }
        return productosParaCocinar;
    }

    /**
     * Agrega los ingredientes a la canasta y los resta del inventario de la panadería.
     * Si hay ingredientes faltantes, los compra automáticamente.
     * @param ingredientes un mapa con los nombres de los ingredientes y su cantidad.
     * @return un mapa con los ingredientes agregados a la canasta y su cantidad.
     */
    public static Map<Ingrediente, Integer> agregarIngredientesACanasta(Map<String, Integer> ingredientes) {
        Map<Ingrediente, Integer> ingredientesCanasta = new HashMap<Ingrediente, Integer>();
        for (Map.Entry<String, Integer> ingrdts : ingredientes.entrySet()) {
            String ingrediente = ingrdts.getKey();
            Integer cantidad = ingrdts.getValue();
            double precioCompra = obtenerPrecioCompraIngrediente(ingrediente);
            double precioVenta = obtenerPrecioVentaIngrediente(ingrediente);
            ingredientesCanasta.put(new Ingrediente(ingrediente, precioVenta, precioCompra), cantidad);
        }
        Map<Ingrediente, Integer> ingredientesFaltantes = revisarCantidadIngredientes(ingredientesCanasta);
        if(!ingredientesFaltantes.isEmpty()){
            comprarIngredientes(ingredientesFaltantes);
        }
        for (Map.Entry<Ingrediente, Integer> entry : ingredientesCanasta.entrySet()) {
            Ingrediente ingrediente = entry.getKey();
            int cantidad = entry.getValue();
            restarIngrediente(ingrediente, cantidad);
        }
        return ingredientesCanasta;
    }

    /**
     * Agrega los kits de productos a la canasta y devuelve un mapa con los kits y sus ingredientes y cantidades correspondientes.
     * @param kitsEnLista un mapa con los nombres de los kits y sus cantidades.
     * @return un mapa con los nombres de los kits y sus ingredientes y cantidades correspondientes.
     */
    public static Map<String, ArrayList<Object>> agregarKitsACanasta(Map<String, Integer> kitsEnLista) {
        Map<String, ArrayList<Object>> kitsCanasta = new HashMap<String, ArrayList<Object>>();
        Map<Ingrediente, Integer> ingredientesProducto = new HashMap<Ingrediente, Integer>();
        Map<String, Integer> productosKit = new HashMap<String, Integer>();
        ArrayList<Object> kitsIngredienteCantidad = new ArrayList<Object>();
        for (Map.Entry<String, Integer> kit : kitsEnLista.entrySet()) {
            String nombreKit = kit.getKey();
            Integer cantidad = kit.getValue();
            for (Map.Entry<Producto, Integer> entry : Panaderia.getInvProductos().entrySet()) {
                Producto p = entry.getKey();
                if (p.getNombre().equals(nombreKit)) {
                    ingredientesProducto = p.getIngredientes();
                    for (Map.Entry<Ingrediente, Integer> ingredientesKit : p.getIngredientes().entrySet()){
                        String nombreIngrediente = ingredientesKit.getKey().getNombre();
                        Integer cantidadIngrediente = ingredientesKit.getValue();
                        productosKit.put(nombreIngrediente, cantidadIngrediente*cantidad);
                    }
                }
                agregarIngredientesACanasta(productosKit);
                break;
            }
            kitsIngredienteCantidad.add(ingredientesProducto);
            kitsIngredienteCantidad.add(cantidad);
            kitsCanasta.put(nombreKit, kitsIngredienteCantidad);
            ingredientesProducto= new HashMap<Ingrediente, Integer>();
            productosKit = new HashMap<String, Integer>();
            kitsIngredienteCantidad = new ArrayList<Object>();
        }
        return kitsCanasta;
    }

    //Método sobrevargado registrarCliente
    
    public static String registrarCliente(String nombre, Integer id, String tipoDescuento, float presupuesto, ArrayList<Canasta> canastas, ArrayList<Recibo> recibos) {
    	
    	Cliente cliente = new Cliente(nombre, id, tipoDescuento, presupuesto, canastas, recibos);
    	
    	Panaderia.clientes.add(cliente);
    	
    	return "Ha sido registrado como cliente con exito bajo el nombre: " + cliente.getNombre();
    	
    }
    
    public static String registrarCliente(String nombre, Integer id, String tipoDescuento, float presupuesto) {
    	
    	Cliente cliente = new Cliente(nombre, id, tipoDescuento, presupuesto);
    	
    	Panaderia.clientes.add(cliente);
    	
    	return "Ha sido registrado como cliente con exito bajo el nombre: " + cliente.getNombre();
    
    }
    
    public static String registrarCliente(String nombre, Integer id, float presupuesto) {
    	
    	Cliente cliente = new Cliente(nombre, id, presupuesto);
    	
    	Panaderia.clientes.add(cliente);
    	
    	return "Ha sido registrado como cliente con exito bajo el nombre: " + cliente.getNombre();
    
    }

    public static Trabajador trabajadorAleatorio(){

        ArrayList<Trabajador> x = (ArrayList<Trabajador>) Panaderia.trabajadores.clone();
        
        Collections.shuffle(x);

        Trabajador elegido = x.get(0);

        return elegido;

    }

    public static Cocinero cocineroAleatorio(){
        
        ArrayList<Cocinero> x = (ArrayList<Cocinero>) Panaderia.cocineros.clone();
        
        Collections.shuffle(x);

        Cocinero elegido = x.get(0);

        return elegido;

    }

    public static Domiciliario domiciliarioAleatorio(){

        ArrayList<Domiciliario> x = (ArrayList<Domiciliario>) Panaderia.domiciliarios.clone();
        
        Collections.shuffle(x);

        Domiciliario elegido = x.get(0);

        return elegido;

    }
    
    public static void comprarIngredientes(Map<Ingrediente, Integer> listingredientes) {
    	
    	Trabajador elegido = Panaderia.trabajadorAleatorio();

        boolean x = elegido.conseguirIngredientes(listingredientes);

        if (x == true){

            Panaderia.comprarIngredientes(listingredientes);

        }
    	
    }
}

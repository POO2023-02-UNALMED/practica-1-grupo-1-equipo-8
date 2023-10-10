package gestorAplicacion.gestion;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import UIMain.GestionCompraMain;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.humanos.Cliente;
import gestorAplicacion.humanos.Cocinero;
import gestorAplicacion.humanos.Domiciliario;
import gestorAplicacion.humanos.Trabajador;
import gestorAplicacion.humanos.Cliente.Descuento;
import gestorAplicacion.humanos.Cliente.Direccion;

import java.util.Collections;

public class Panaderia implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Map<Ingrediente, Integer> invIngredientes= new HashMap<Ingrediente, Integer>();
    private static Map<Producto,Integer> invProductos = new HashMap<Producto,Integer>();
    private static ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
    private static ArrayList<Cocinero> cocineros = new ArrayList<Cocinero>();
    private static ArrayList<Domiciliario> domiciliarios = new ArrayList<Domiciliario>();
    private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private static double dinero;
    private static Canasta canastaDelDia;
    private static double valorDeudas;
    private static boolean enQuiebra = false;
    static {
        // Agregar lista de productos de la canasta del dia
        canastaDelDia = new Canasta(null, null);
    }

    public Panaderia() {

    }

    // metodos get:
    public static Map<Producto,Integer> getInvProductos() {
        return invProductos;
    }

    public static Map<Ingrediente, Integer> getInvIngredientes() {
        return invIngredientes;
    }

    public static Canasta getCanastaDelDia() {
        return canastaDelDia;
    }

    public static List<Trabajador> getTrabajadores() {
		return trabajadores;
	}

	public static List<Cocinero> getCocineros() {
		return cocineros;
	}

	public static List<Domiciliario> getDomiciliarios() {
		return domiciliarios;
	}

	public static List<Cliente> getClientes() {
        return clientes;
    }

    public static double getDinero() {
        return dinero;
    }

    // metodos set:
    public static void setInvProductos(Map<Producto,Integer> newInvProductos) {
        invProductos = newInvProductos;
    }

    public static void setInvIngredientes(Map<Ingrediente, Integer> newInvIngredientes) {
        invIngredientes = newInvIngredientes;
    }

    public static void setClientes(ArrayList<Cliente> clientes) {
        Panaderia.clientes = clientes;
    }

    public static void setDinero(double dinero) {
        Panaderia.dinero = dinero;
    }

    public static void setCanastaDelDia(Canasta canasta) {
        canastaDelDia = canasta;
    }

    public static void setTrabajadores(ArrayList<Trabajador> trabajadores) {
        Panaderia.trabajadores = trabajadores;
    }

    public static void setCocineros(ArrayList<Cocinero> cocineros) {
        Panaderia.cocineros = cocineros;
    }

    public static void setDomiciliarios(ArrayList<Domiciliario> domiciliarios) {
        Panaderia.domiciliarios = domiciliarios;
    }

    public static double getValorDeudas() {
        return valorDeudas;
    }

    public static void setValorDeudas(double valorDeudas) {
        Panaderia.valorDeudas = valorDeudas;
    }

    public static boolean isEnQuiebra() {
        return enQuiebra;
    }

    public static void setEnQuiebra(boolean enQuiebra) {
        Panaderia.enQuiebra = enQuiebra;
    }


    //Metodos para agregar elementos a las listas
    public static void agregarTrabajador(Trabajador cocinero) {
        trabajadores.add(cocinero);
    }

    public static void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public static void eliminarCocinero(Trabajador cocinero) {
        trabajadores.remove(cocinero);
    }

    public static void eliminarCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    public static void agregarDinero(double dinero) {
        Panaderia.dinero += dinero;
    }

    public static void restarDinero(double dinero) {
        Panaderia.dinero -= dinero;
    }

    //Métodos para saldar las deudas de la panadería
    
    //Este método se encarga de saldar las deudas de la panadería
    //No tiene parámetros ya que trabaja con los atributos de esta
    //Devuelve un booleano que será true si la panadería quebró y false si no lo hizo
    //Este booleano quedará guardado en el atributo enQuiebra de la panadería 
    //Para evitar el fin del programa, siempre que la panadería quiebre la comprará una franquicia más grande y le dará dinero 
    
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
        }

    }
    
  //Este método se encarga de pedir un prestamo cuando la panadería no tenga suficiente dinero para comprar ingredientes
  //El parámetro double es el valor de la compra que necesita hacer la panadería
  //No devolverá nada ya que agregará el dinero que necesite la panadería una vez se acepte el prestamo
  //El prestamo será aceptado solamente si la panadería no tiene deudas

    public static void conseguirPrestamo(double valorNecesitado) {

        if (Panaderia.valorDeudas == 0){

            Panaderia.dinero += valorNecesitado;
            Panaderia.valorDeudas = valorNecesitado;

        }

        else{

            Panaderia.saldarDeudas();
            
            while (Panaderia.enQuiebra == true) {
            	
            	GestionCompraMain.lecturaQuiebra(Panaderia.enQuiebra);
            	Panaderia.saldarDeudas();
         
            }
            
            Panaderia.dinero += valorNecesitado;
            Panaderia.valorDeudas = valorNecesitado;

        }
        
        GestionCompraMain.lecturaQuiebra(Panaderia.enQuiebra);

    }

    //TODO Desarrollar el metodo cobrarCliente que recibe una lista de canastas y un recibo y cobra al cliente, además actualiza la plata de la panaderia
    public static void cobrarCliente(List<Canasta> canastas, List<Recibo> recibo){

    }

    //TODO Desarrollar el metodo enviarDomicilio que recibe una lista de canastas y las envia a domicilio
    public static void enviarDomicilio(List<Canasta> canastas){

    }

    /**
     * Verifica si existe un producto con el nombre de pila en el inventario de la panadería.
     * @param prdct el nombre del producto a buscar
     * @return verdadero si existe un producto con el nombre de pila, falso en casocontrario
     */
    public static boolean verificarExistenciaProductoPorId(String prdct) {
        for (Map.Entry<Producto, Integer> productoEntry : invProductos.entrySet()) {
            Producto producto = productoEntry.getKey();
            if (producto.getId().equals(prdct)) {
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
    public static boolean verificarExistenciaIngredientePorId(String ingrd) {
        for (Map.Entry<Ingrediente, Integer> ingredienteEntry : invIngredientes.entrySet()) {
            Ingrediente ingrediente = ingredienteEntry.getKey();
            if (ingrediente.getId().equals(ingrd)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Busca un producto en el inventario de la panadería por su ID.
     * @param id el ID del producto a buscar
     * @return el producto con el ID especificado, o null si no se encuentra en el inventario
     */
    public static Producto buscarProductoPorId(String id) {
        for (Map.Entry<Producto, Integer> productoEntry : invProductos.entrySet()) {
            Producto producto = productoEntry.getKey();
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    /**
     * Busca un ingrediente en el inventario de ingredientes de la panadería por su ID.
     * @param id el ID del ingrediente a buscar
     * @return el objeto Ingrediente correspondiente al ID, o null si no se encuentra
     */
    public static Ingrediente buscarIngredientePorId(String id) {
        for (Map.Entry<Ingrediente, Integer> ingredienteEntry : invIngredientes.entrySet()) {
            Ingrediente ingrediente = ingredienteEntry.getKey();
            if (ingrediente.getId().equals(id)) {
                return ingrediente;
            }
        }
        return null;
    }

    // Método para agregar un ingrediente al inventario
    /**
     * Agrega una cantidad determinada de un ingrediente al inventario de la panadería.
     * Si el ingrediente ya existe en el inventario, se suma la cantidad nueva a la existente.
     * Si el ingrediente no existe en el inventario, se agrega con la cantidad especificada.
     * @param ingrediente el ingrediente a agregar al inventario
     * @param cantidad la cantidad del ingrediente a agregar
     */
    public static void agregarIngrediente(Ingrediente ingrediente, int cantidad) {
        for(Map.Entry<Ingrediente, Integer> entry : invIngredientes.entrySet()) {
            Ingrediente I = entry.getKey();
            if (I.getId().equals(ingrediente.getId())) {
                int cantidadExistente = entry.getValue();
                invIngredientes.put(I, cantidadExistente + cantidad);
                break;
            }
        }
        invIngredientes.put(ingrediente, cantidad);
    }

    /**
     * Agrega una cantidad determinada de un ingrediente al inventario de la panadería.
     * Si el ingrediente ya existe en el inventario, se actualiza la cantidad.
     * Si el ingrediente no existe en el inventario, se agrega con la cantidad especificada.
     * @param ingrediente el nombre del ingrediente a agregar
     * @param cantidad la cantidad del ingrediente a agregar
     */
    public static void agregarIngrediente(String ingrediente, int cantidad) {
        for(Map.Entry<Ingrediente, Integer> entry : invIngredientes.entrySet()) {
            Ingrediente I = entry.getKey();
            if (I.getId().equals(ingrediente)) {
                int cantidadExistente = entry.getValue();
                invIngredientes.put(I, cantidadExistente + cantidad);
                break;
            }
        }
        invIngredientes.put(new Ingrediente(ingrediente), cantidad);
    }

    /**
     * Resta la cantidad especificada de un ingrediente del inventario de la panadería.
     * @param ingrediente el nombre del ingrediente a restar
     * @param cantidad la cantidad a restar del ingrediente
     */
    public static boolean restarIngrediente(String ingrediente, int cantidad) {
        for (Map.Entry<Ingrediente, Integer> entry : invIngredientes.entrySet()) {
            Ingrediente I = entry.getKey();
            if (I.getId().equals(ingrediente)) {
                int cantidadExistente = entry.getValue();
                if (cantidadExistente > cantidad) {
                    // Si hay suficiente cantidad del ingrediente, resta la cantidad especificada
                    invIngredientes.put(I, cantidadExistente - cantidad);
                    return true;
                }/* 
                else if(cantidadExistente-cantidad==0){
                    invIngredientes.remove(I);
                    return true;
                }*/
            }
        }
        return false;
    }

    /**
     * Resta la cantidad especificada de un ingrediente del inventario de la panadería.
     * Si la cantidad especificada es mayor que la cantidad existente del ingrediente, no se realiza ninguna acción.
     * Si la cantidad especificada es igual a la cantidad existente del ingrediente, el ingrediente se elimina del inventario.
     * @param ingrediente el ingrediente a restar del inventario
     * @param cantidad la cantidad de ingrediente a restar
     * @return true si se restó la cantidad especificada del ingrediente del inventario, false en caso contrario
     */
    public static boolean restarIngrediente(Ingrediente ingrediente, int cantidad) {
        for (Map.Entry<Ingrediente, Integer> entry : invIngredientes.entrySet()) {
            Ingrediente I = entry.getKey();
            if (I.getId().equals(ingrediente.getId())) {
                int cantidadExistente = entry.getValue();
                if (cantidadExistente > cantidad) {
                    // Si hay suficiente cantidad del ingrediente, resta la cantidad especificada
                    invIngredientes.put(I, cantidadExistente - cantidad);
                    return true;
                }/* 
                else if(cantidadExistente-cantidad==0){
                    invIngredientes.remove(I);
                    return true;
                }*/
            }
        }
        return false;
    }

    /**
     * Agrega una cantidad determinada de un producto al inventario de la panadería.
     * Si el producto ya existe en el inventario, se suma la cantidad nueva a la cantidad existente.
     * Si el producto no existe en el inventario, se agrega con la cantidad nueva.
     * @param producto el producto a agregar al inventario
     * @param cantidad la cantidad del producto a agregar
     */
    public static void agregarProducto(Producto producto, int cantidad) {
        for(Map.Entry<Producto, Integer> entry : invProductos.entrySet()) {
            Producto P = entry.getKey();
            if (P.getId().equals(producto.getId())) {
                int cantidadExistente = entry.getValue();
                invProductos.put(P, cantidadExistente + cantidad);
                break;
            }
        }
        invProductos.put(producto, cantidad);
    }

    /**
     * Agrega un producto al inventario de la panadería.
     * Si el producto ya existe, se actualiza la cantidad existente.
     * @param producto el nombre del producto a agregar
     * @param ingredientes los ingredientes necesarios para producir el producto
     * @param cantidad la cantidad de productos a agregar
     */
    public static void agregarProducto(String producto,HashMap<Ingrediente, Integer> ingredientes, int cantidad) {
        for(Map.Entry<Producto, Integer> entry : invProductos.entrySet()) {
            Producto P = entry.getKey();
            if (P.getId().equals(producto)) {
                int cantidadExistente = entry.getValue();
                invProductos.put(P, cantidadExistente + cantidad);
                break;
            }
        }
        invProductos.put(new Producto(producto, ingredientes), cantidad);
    }

    /**
     * Resta la cantidad especificada de un producto del inventario de la panadería.
     * @param producto el identificador del producto a restar.
     * @param cantidad la cantidad a restar del producto.
     * @return true si se pudo restar la cantidad especificada del producto, false en caso contrario.
     */
    public static boolean restarProducto(String producto, int cantidad) {
        for (Map.Entry<Producto, Integer> entry : invProductos.entrySet()) {
            Producto P = entry.getKey();
            if (P.getId().equals(producto)) {
                int cantidadExistente = entry.getValue();
                if (cantidadExistente > cantidad) {
                    // Si hay suficiente cantidad del producto, resta la cantidad especificada
                    invProductos.put(P, cantidadExistente - cantidad);
                    return true;
                }
                /* 
                else if(cantidadExistente-cantidad==0){
                    invProductos.remove(P);
                    return true;
                }*/
            }
        }
        return false;
    }

    /**
     * Resta la cantidad especificada del producto del inventario de la panadería.
     * @param producto el producto a restar del inventario.
     * @param cantidad la cantidad de producto a restar.
     * @return true si se pudo restar la cantidad especificada del producto del inventario, false en caso contrario.
     */
    public static boolean restarProducto(Producto producto, int cantidad) {
        for (Map.Entry<Producto, Integer> entry : invProductos.entrySet()) {
            Producto P = entry.getKey();
            if (P.getId().equals(producto.getId())) {
                int cantidadExistente = entry.getValue();
                if (cantidadExistente > cantidad) {
                    // Si hay suficiente cantidad del producto, resta la cantidad especificada
                    invProductos.put(P, cantidadExistente - cantidad);
                    return true;
                }/*
                else if(cantidadExistente-cantidad==0){
                    invProductos.remove(P);
                    return true;
                }*/
            }
        }
        return false;
    }

    /**
     * Retorna la cantidad de un ingrediente específico en el inventario de la panadería.
     * @param ingrediente el nombre del ingrediente a buscar
     * @return la cantidad del ingrediente en el inventario, o 0 si no se encuentra
     */
    public static int obtenerCantidadIngrediente(String ingrediente) {
        for (Map.Entry<Ingrediente, Integer> entry : invIngredientes.entrySet()) {
            Ingrediente I = entry.getKey();
            if (I.getId().equals(ingrediente)) {
                return entry.getValue();
            }
        }
        return 0;
    }

    /**
     * Retorna la cantidad de un producto dado en el inventario.
     * @param producto el ID del producto a buscar
     * @return la cantidad del producto en el inventario, o 0 si el producto no se encuentra
     */
    public static int obtenerCantidadProducto(String producto) {
        for (Map.Entry<Producto, Integer> entry : invProductos.entrySet()) {
            Producto P = entry.getKey();
            if (P.getId().equals(producto)) {
                return entry.getValue();
            }
        }
        return 0;
    }

    /**
     * Retorna un mapa de ingredientes y sus cantidades requeridas para hacer un producto dado.
     * @param producto el nombre del producto para obtener los ingredientes
     * @return un mapa de ingredientes y sus cantidades requeridas para hacer el producto dado
     */
    public static Map<Ingrediente, Integer> ingredientesPorProducto(String producto) {
        Map<Ingrediente, Integer> ingredientes = new HashMap<>();
        for (Map.Entry<Producto, Integer> entry : invProductos.entrySet()) {
            Producto p = entry.getKey();
            if (p.getId().equals(producto)) {
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
            int cantidadNecesitada = entry.getValue();
            int cantidadExistente = obtenerCantidadIngrediente(ingrediente.getId());
            if (cantidadExistente < cantidadNecesitada) {
                ingredientesFaltantes.put(ingrediente, cantidadNecesitada - cantidadExistente);
                }
        }
        return ingredientesFaltantes;
    }

    /**
     * Este método recibe un mapa de productos y sus cantidades requeridas, y devuelve un mapa de productos y sus cantidades faltantes.
     * Itera sobre el mapa de productos y verifica si la cantidad existente de cada producto es menor que la cantidad requerida.
     * Si la cantidad existente es menor, agrega el producto y la cantidad faltante a un nuevo mapa.
     * @param productos un mapa de productos y sus cantidades requeridas
     * @return un mapa de productos y sus cantidades faltantes
     */
    public static Map<Producto, Integer> revisarCantidadProductos(Map<Producto, Integer> productos) {
        HashMap<Producto, Integer> productosFaltantes = new HashMap<Producto, Integer>();
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            Producto producto = entry.getKey();
            int cantidadNecesitada = entry.getValue();
            int cantidadExistente = obtenerCantidadProducto(producto.getId());
            if (cantidadExistente < cantidadNecesitada) {
                productosFaltantes.put(producto, cantidadNecesitada - cantidadExistente);
                }
        }
        return productosFaltantes;
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
            if (!verificarExistenciaIngredientePorId(ingrediente)) {
                Ingrediente newIngrediente = new Ingrediente(ingrediente);
                invIngredientes.put(newIngrediente, 0);
                ingredientesProducto.put(newIngrediente, cantidad);
            }
            else {
                ingredientesProducto.put(buscarIngredientePorId(ingrediente), cantidad);
            }
        }
        invProductos.put(new Producto(nombreProducto, ingredientesProducto), 0);
    }

    /**
     * Cocina los productos dados creando un mapa de productos y sus respectivos ingredientes,
     * y luego utilizando un chef aleatorio para cocinarlos.
     * @param productos un mapa de nombres de productos y sus respectivas cantidades para cocinar
     * @return un mapa de productos cocinados y sus respectivas cantidades
     */
    public static void cocinar(Map<Producto, Integer> productosParaCocinar) {
        Canasta canastaDeProductosCocinar = new Canasta(productosParaCocinar, null);
        ArrayList<Canasta> canastaParaCocinar = new ArrayList<Canasta>();
        canastaParaCocinar.add(canastaDeProductosCocinar);
        while(true){
            Cocinero cocinero = cocineroAleatorio();
            if(cocinero.laborParticular(canastaDeProductosCocinar)){
                break;
            }
        }
    }

    /**
     * Agrega los productos especificados en un mapa a la canasta de la panadería.
     * @param productos un mapa que contiene los IDs de los productos y la cantidad deseada de cada uno
     * @return un mapa que contiene los productos agregados a la canasta y la cantidad de cada uno
     */
    public static Map<Producto, Integer> agregarProductosACanasta(Map<String, Integer> productos) {
        Map<Producto, Integer> productosCanasta = new HashMap<Producto, Integer>();
        for (Map.Entry<String, Integer> entry : productos.entrySet()) {
            productosCanasta.put(buscarProductoPorId(entry.getKey()), entry.getValue());
        }
        Map<Producto, Integer> productosFaltantes = revisarCantidadProductos(productosCanasta);
        if(!productosFaltantes.isEmpty()){
            cocinar(productosFaltantes);
        }
        for (Map.Entry<Producto, Integer> entry : productosCanasta.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            restarProducto(producto, cantidad);
        }
        return productosCanasta;
    }

    /**
     * Agrega los ingredientes especificados en la canasta de la panadería y devuelve un mapa con los ingredientes agregados y su cantidad.
     * Si algún ingrediente no está disponible en la cantidad especificada, se comprará la cantidad faltante.
     * @param ingredientes un mapa con los nombres de los ingredientes y su cantidad a agregar en la canasta.
     * @return un mapa con los ingredientes agregados y su cantidad.
     */
    public static Map<Ingrediente, Integer> agregarIngredientesACanasta(Map<String, Integer> ingredientes) {
        Map<Ingrediente, Integer> ingredientesCanasta = new HashMap<Ingrediente, Integer>();
        for (Map.Entry<String, Integer> entry : ingredientes.entrySet()) {
            ingredientesCanasta.put(buscarIngredientePorId(entry.getKey()), entry.getValue());
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
     * Agrega los kits de la lista de kits a la canasta de kits de la panadería.
     * Para cada kit en la lista, se revisa si hay suficientes ingredientes en la panadería para hacer la cantidad deseada de kits.
     * Si no hay suficientes ingredientes, se compran los ingredientes faltantes.
     * Luego, se resta la cantidad de ingredientes utilizados de la panadería.
     * Finalmente, se agrega el kit a la canasta de kits de la panadería.
     * @param kitsEnLista un mapa que contiene el nombre de cada kit y la cantidad deseada de kits.
     * @return un mapa que contiene el nombre de cada kit y una lista que contiene los ingredientes del kit y la cantidad deseada de kits.
     */
    public static Map<String, ArrayList<Object>> agregarKitsACanasta(Map<String, Integer> kitsEnLista) {
        Map<String, ArrayList<Object>> kitsCanasta = new HashMap<String, ArrayList<Object>>();
        for (Map.Entry<String, Integer> kit : kitsEnLista.entrySet()) {
            String nombreKit = kit.getKey();
            Integer cantidad = kit.getValue();
            Map<Ingrediente, Integer> ingredientesKit = buscarProductoPorId(nombreKit).getIngredientes();
            Map<Ingrediente, Integer> ingredientesKitEnCantidad= new HashMap<Ingrediente, Integer>();
            ingredientesKit.forEach((ingrediente,cantidadIngrediente)-> ingredientesKitEnCantidad.put(ingrediente,cantidadIngrediente*cantidad));
            Map<Ingrediente, Integer> ingredientesFaltantes = revisarCantidadIngredientes(ingredientesKitEnCantidad);
            if(!ingredientesFaltantes.isEmpty()){
                comprarIngredientes(ingredientesFaltantes);
            }
            for (Map.Entry<Ingrediente, Integer> entry : ingredientesKitEnCantidad.entrySet()) {
                Ingrediente ingrediente = entry.getKey();
                int cantidadIngrediente = entry.getValue();
                restarIngrediente(ingrediente, cantidadIngrediente);
            }
            ArrayList<Object> kitsIngredienteCantidad = new ArrayList<Object>();
            kitsIngredienteCantidad.add(ingredientesKit);
            kitsIngredienteCantidad.add(cantidad);
            kitsCanasta.put(nombreKit, kitsIngredienteCantidad);
        }
        return kitsCanasta;
    }

    //Método sobrevargado registrarCliente
    
    public static String registrarCliente(String nombre, Integer id, Descuento tipoDescuento, double presupuesto, ArrayList<Canasta> canastas, ArrayList<Recibo> recibos) {
    	
    	Cliente cliente = new Cliente(nombre, id, tipoDescuento, presupuesto, canastas, recibos);
    	
    	Panaderia.clientes.add(cliente);
    	
    	return "Ha sido registrado como cliente con exito bajo el nombre: " + cliente.getNombre();
    	
    }
    
    public static String registrarCliente(String nombre, Integer id, Descuento tipoDescuento, double presupuesto) {
    	
    	Cliente cliente = new Cliente(nombre, id, tipoDescuento, presupuesto);
    	
    	Panaderia.clientes.add(cliente);
    	
    	return "Ha sido registrado como cliente con exito bajo el nombre: " + cliente.getNombre();
    
    }
    
    public static String registrarCliente(String nombre, Integer id, double presupuesto) {
    	
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
    	
    	GestionCompraMain.lecturaCompra();
    	
    	Trabajador elegido = Panaderia.trabajadorAleatorio();

        boolean x = elegido.conseguirIngredientes(listingredientes);

        while (x == true){

            Panaderia.comprarIngredientes(listingredientes);
            GestionCompraMain.lecturaRobo(x);
        }
        
        GestionCompraMain.lecturaRobo(x);
    }
}

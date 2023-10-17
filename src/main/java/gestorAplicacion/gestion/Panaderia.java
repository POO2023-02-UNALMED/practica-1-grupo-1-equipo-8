package gestorAplicacion.gestion;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import UIMain.GestionCompra;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.comida.ProductoFrio;
import gestorAplicacion.humanos.Cliente;
import gestorAplicacion.humanos.Cocinero;
import gestorAplicacion.humanos.Domiciliario;
import gestorAplicacion.humanos.Trabajador;
import gestorAplicacion.humanos.Cliente.*;
import gestorAplicacion.humanos.Cliente.Direccion;

import java.util.Collections;

public class Panaderia implements Serializable {
    private static ArrayList<Ingrediente> invIngredientes= new ArrayList<Ingrediente>();
    private static ArrayList<Producto> invProductos = new ArrayList<Producto>();

    private static ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
    private static ArrayList<Cocinero> cocineros = new ArrayList<Cocinero>();
    private static ArrayList<Domiciliario> domiciliarios = new ArrayList<Domiciliario>();
    private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    private static double dinero;
    private static double valorDeudas;
    private static boolean enQuiebra = false;

    private static Canasta canastaDelDia;
    private static ArrayList<Canasta> canastasPublicadas = new ArrayList<Canasta>();
    static {
        // Agregar lista de productos de la canasta del dia
        canastaDelDia = new Canasta();
    }

    public Panaderia() {

    }

    // metodos get:
    public static ArrayList<Producto> getInvProductos() {
        return invProductos;
    }

    public static ArrayList<Ingrediente> getInvIngredientes() {
        return invIngredientes;
    }

    public static Canasta getCanastaDelDia() {
        return canastaDelDia;
    }

    public static ArrayList<Trabajador> getTrabajadores() {
		return trabajadores;
	}

	public static ArrayList<Cocinero> getCocineros() {
		return cocineros;
	}

	public static ArrayList<Domiciliario> getDomiciliarios() {
		return domiciliarios;
	}

	public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static double getDinero() {
        return dinero;
    }

    // metodos set:
    public static void setInvProductos(ArrayList<Producto> newInvProductos) {
        invProductos = newInvProductos;
    }

    public static void setInvIngredientes(ArrayList<Ingrediente> newInvIngredientes) {
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

    public static ArrayList<Canasta> getCanastasPublicadas() {
        return canastasPublicadas;
    }

    public static void setCanastasPublicadas(ArrayList<Canasta> canastasPublicadas) {
        Panaderia.canastasPublicadas = canastasPublicadas;
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

    public static void agregarCanastasPublicadas(Canasta canasta) {
        canastasPublicadas.add(canasta);
    }

    //TODO corregir importacion de cocinero
    
    public static Cocinero contratarCocinero(String nombre, double habilidad,double calificacion, double dineroEnMano, String especialidad) {
        Cocinero indicado = new Cocinero(nombre, habilidad,calificacion, dineroEnMano, especialidad);
        cocineros.add(indicado);
        return indicado;
        
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
            
                GestionCompra.lecturaQuiebra(Panaderia.enQuiebra);
                Panaderia.saldarDeudas();
        
            }
            
            Panaderia.dinero += valorNecesitado;
            Panaderia.valorDeudas = valorNecesitado;

        }
        
        GestionCompra.lecturaQuiebra(Panaderia.enQuiebra);

    }

    //TODO Desarrollar el metodo cobrarCliente que recibe una lista de canastas y un recibo y cobra al cliente, además actualiza la plata de la panaderia
    public static void cobrarCliente(List<Canasta> canastas, List<Recibo> recibo) {
        
    }

    public static void enviarDomicilio(Canasta canasta, Cliente cliente) {
        Domiciliario domiciliario = domiciliarioAleatorio();
        ArrayList<Producto> producto = canasta.getProductos();

        for (Producto p : producto){
            if (p instanceof ProductoFrio){
                ((ProductoFrio)p).empaqueCongelador(domiciliario);
            }
        }
        if (!domiciliario.isLicencia()){
            Panaderia.restarDinero(10000);
            domiciliario.setLicencia(true);
        }
        domiciliario.setCanasta(canasta);
        domiciliario.setOcupado(true);
        boolean logro = domiciliario.laborParticular(canasta);
        while (!logro){
            domiciliario.setHabilidad(domiciliario.getHabilidad()+1);
            logro = domiciliario.laborParticular(canasta);
            Panaderia.restarDinero(10000);
            domiciliario.setLicencia(true);
        }
        cliente.setDomiciliario(domiciliario);
    }

    public static void reviewDomiciliario(Domiciliario domiciliario){
        double calificacion = domiciliario.getCalificacion();
        if (calificacion < 3){
            domiciliario.setSalario(domiciliario.getSalario()*0.9);
        } else if (calificacion == 5){
            domiciliario.setSalario(domiciliario.getSalario()*1.1);
        }
    }

    public static void reviewCocinero(Cocinero cocinero){
        double calificacion = cocinero.getCalificacion();
        if (calificacion < 3){
            cocinero.setSalario(cocinero.getSalario()*0.9);
        } else if (calificacion == 5){
            cocinero.setSalario(cocinero.getSalario()*1.1);
        }
    }

    /**
     * Verifica la cantidad de un producto en el inventario de la panadería por su ID.
     * @param prdct el ID del producto a verificar.
     * @return la cantidad de productos con el ID especificado en el inventario.
     */
    public static int verificarCantidadProductoPorId(String prdct) {
        int contador=0;
        for(Producto productos : invProductos) {
            if (productos.getId().equals(prdct)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Verifica la cantidad de ingredientes en el inventario por su ID.
     * @param ingrd el ID del ingrediente a verificar.
     * @return la cantidad de ingredientes con el ID especificado en el inventario.
     */
    public static int verificarCantidadIngredientePorId(String ingrd) {
        int contador=0;
        for(Ingrediente ingredientes : invIngredientes) {
            if (ingredientes.getId().equals(ingrd)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Busca un producto en el inventario de la panadería por su ID.
     * @param id el ID del producto a buscar
     * @return el producto con el ID especificado, o null si no se encuentra en el inventario
     */
    public static Producto buscarProductoPorId(String id) {
        for(Producto producto: invProductos) {
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
        for(Ingrediente ingrediente: invIngredientes) {
            if (ingrediente.getId().equals(id)) {
                return ingrediente;
            }
        }
        return null;
    }

    // Método para agregar un ingrediente al inventario

    /**
     * Agrega un ingrediente a la lista de ingredientes disponibles en la panadería.
     * @param ingrediente El ingrediente a agregar.
     */
    public static void agregarIngrediente(Ingrediente ingrediente) {
        invIngredientes.add(ingrediente);
    }

    /**
     * Agrega un producto a la lista de productos disponibles en la panadería.
     * @param producto el producto a agregar.
     */
    public static void agregarProducto(Producto producto) {
        invProductos.add(producto);
    }

    /**
     * Resta la cantidad especificada de un ingrediente del inventario de la panadería.
     * @param ingrediente el id del ingrediente a restar.
     * @param cantidad la cantidad de ingredientes a restar.
     */
    public static void restarIngrediente(String ingrediente, int cantidad) {
        if(verificarCantidadIngredientePorId(ingrediente) >= cantidad) {
            for(int i = 0; i < cantidad; i++){
                for(Ingrediente ingrediente1 : invIngredientes) {
                    if(ingrediente1.getId().equals(ingrediente)) {
                        invIngredientes.remove(ingrediente1);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Resta la cantidad especificada de un ingrediente del inventario de la panadería.
     * @param ingrediente el ingrediente a restar
     * @param cantidad la cantidad de ingrediente a restar
     */
    public static void restarIngrediente(Ingrediente ingrediente, int cantidad) {
        if(verificarCantidadIngredientePorId(ingrediente.getId()) >= cantidad) {
            for(int i = 0; i < cantidad; i++){
                for(Ingrediente ingrediente1 : invIngredientes) {
                    if(ingrediente1.getId().equals(ingrediente.getId())) {
                        invIngredientes.remove(ingrediente1);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Resta la cantidad especificada de un producto del inventario de la panadería.
     * @param producto el id del producto a restar.
     * @param cantidad la cantidad de productos a restar.
     */
    public static void restarProducto(String producto, int cantidad) {
        if(verificarCantidadProductoPorId(producto) >= cantidad) {
            for(int i = 0; i < cantidad; i++){
                for(Producto producto1 : invProductos) {
                    if(producto1.getId().equals(producto)) {
                        invProductos.remove(producto1);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Resta la cantidad especificada de un producto del inventario de la panadería.
     * @param producto el producto a restar del inventario
     * @param cantidad la cantidad de producto a restar
     */
    public static void restarProducto(Producto producto, int cantidad) {
        if(verificarCantidadProductoPorId(producto.getId()) >= cantidad) {
            for(int i = 0; i < cantidad; i++){
                for(Producto producto1 : invProductos) {
                    if(producto1.getId().equals(producto.getId())) {
                        invProductos.remove(producto1);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Método que permite cocinar una canasta de productos utilizando un cocinero aleatorio.
     * @param productosParaCocinar HashMap que contiene los productos y su cantidad necesaria para cocinar.
     */
    public static void cocinar(HashMap<String, Integer> productosParaCocinar) {
        Canasta canastaDeProductosCocinar = new Canasta();
        canastaDeProductosCocinar.setProductosEnLista(productosParaCocinar);
        ArrayList<Canasta> canastaParaCocinar = new ArrayList<Canasta>();
        canastaParaCocinar.add(canastaDeProductosCocinar);
        Cocinero cocinero = cocineroAleatorio();
        while(true){
            if(cocinero.laborParticular(canastaDeProductosCocinar)){
                break;
            }
        }
    }

    public static Map<String, Integer> revisarCantidadIngredientes(Map<String, Integer> ingredientesNecesarios){
        HashMap<String, Integer> ingredientessFaltantes = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : ingredientesNecesarios.entrySet()) {
            String ingredienteId = entry.getKey();
            Integer cantidad = entry.getValue();
            int cantidadExistente=verificarCantidadIngredientePorId(ingredienteId);
            if (cantidadExistente-cantidad<0){
                ingredientessFaltantes.put(ingredienteId,(cantidadExistente-cantidad)*(-2));
            }
        }
        return ingredientessFaltantes;
    }
    /**
     * Agrega los productos especificados en una canasta y devuelve una lista de los productos agregados.
     * Si algún producto no tiene suficiente cantidad en la panadería, se cocinará la cantidad faltante.
     * @param productos un HashMap que contiene los IDs de los productos y la cantidad deseada de cada uno.
     * @return una ArrayList con los productos agregados a la canasta.
     */
    public static ArrayList<Producto> agregarProductosACanasta(HashMap<String, Integer> productos) {
        ArrayList<Producto> productosCanasta = new ArrayList<Producto>();
        HashMap<String, Integer> productosFaltantes = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : productos.entrySet()) {
            int cantidadExistente=verificarCantidadIngredientePorId(entry.getKey());
            if (cantidadExistente-entry.getValue()<0){
                productosFaltantes.put(entry.getKey(),(cantidadExistente-entry.getValue())*(-2));
            }
        }
        if(!productosFaltantes.isEmpty()){
            cocinar(productosFaltantes);
        }
        for (Map.Entry<String, Integer> entry : productos.entrySet()) {
            for (int i=0;i<entry.getValue();i++){
                productosCanasta.add(buscarProductoPorId(entry.getKey()));
                restarProducto(entry.getKey(),entry.getValue());
            }
        }
        return productosCanasta;
    }


    /**
     * Agrega los ingredientes especificados en una canasta y devuelve una lista con los ingredientes agregados.
     * Si algún ingrediente no tiene suficiente cantidad en la panadería, se comprará la cantidad faltante.
     * @param ingredientes Un HashMap que contiene los ingredientes y la cantidad deseada de cada uno.
     * @return Una lista de objetos Ingrediente que representa los ingredientes agregados a la canasta.
     */
    public static ArrayList<Ingrediente> agregarIngredientesACanasta(HashMap<String, Integer> ingredientes) {
        ArrayList<Ingrediente> ingredientesCanasta = new ArrayList<Ingrediente>();
        HashMap<String, Integer> ingredientesFaltantes = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : ingredientes.entrySet()) {
            int cantidadExistente=verificarCantidadIngredientePorId(entry.getKey());
            if (cantidadExistente-entry.getValue()<0){
                ingredientesFaltantes.put(entry.getKey(),(cantidadExistente-entry.getValue())*(-2));
            }
        }
        if(!ingredientesFaltantes.isEmpty()){
            comprarIngredientes(ingredientesFaltantes);
        }
        for (Map.Entry<String, Integer> entry : ingredientes.entrySet()) {
            for (int i=0;i<entry.getValue();i++){
                ingredientesCanasta.add(buscarIngredientePorId(entry.getKey()));
                restarIngrediente(entry.getKey(),entry.getValue());
            }
        }
        return ingredientesCanasta;
    }


    /**
     * Agrega los kits de productos a la canasta y retorna una lista de kits de ingredientes.
     * Si no hay suficientes ingredientes para los kits, los compra automáticamente.
     * @param kitsEnLista HashMap que contiene los ids de los kits y la cantidad de veces que se deben agregar a la canasta.
     * @return ArrayList de ArrayLists de ingredientes que representan los kits agregados a la canasta.
     */
    public static ArrayList<ArrayList<Ingrediente>> agregarKitsACanasta(HashMap<String, Integer> kitsEnLista) {
        ArrayList<ArrayList<Ingrediente>> kitsCanasta = new ArrayList<ArrayList<Ingrediente>>();
        HashMap<String, Integer> ingredientesFaltantes = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> kit : kitsEnLista.entrySet()) {
            String idKit = kit.getKey();
            Integer cantidad = kit.getValue();
            Map<String, Integer> ingredientesKit = Producto.obtenerObjetoPorId(idKit).getIngredientes();
            ingredientesKit.forEach((ingrediente,cantidadIngrediente)-> ingredientesKit.put(ingrediente,cantidadIngrediente*cantidad));
            for(Map.Entry<String, Integer> entry : ingredientesKit.entrySet()){
                int cantidadExistente=verificarCantidadIngredientePorId(entry.getKey());
                if (cantidadExistente-entry.getValue()<0){
                    if(ingredientesFaltantes.containsKey(entry.getKey())) {
                        ingredientesFaltantes.put(entry.getKey(), ingredientesFaltantes.get(entry.getKey()) + (cantidadExistente - entry.getValue()) * (-2));
                    }else{
                        ingredientesFaltantes.put(entry.getKey(),(cantidadExistente-entry.getValue())*(-2));
                    }
                }
            }
        }
        if(!ingredientesFaltantes.isEmpty()){
            comprarIngredientes(ingredientesFaltantes);
        }
        for(Map.Entry<String, Integer> kit : kitsEnLista.entrySet()){
            String idKit = kit.getKey();
            Integer cantidad = kit.getValue();
            for (int i=0;i<cantidad;i++){
                ArrayList<Ingrediente> kitCanasta = new ArrayList<Ingrediente>();
                for (Map.Entry<String, Integer> entry : Producto.obtenerObjetoPorId(idKit).getIngredientes().entrySet()) {
                    for (int j=0;j<entry.getValue();j++){
                        kitCanasta.add(buscarIngredientePorId(entry.getKey()));
                        restarIngrediente(entry.getKey(),1);
                    }
                }
                kitsCanasta.add(kitCanasta);
            }
        }
        return kitsCanasta;
    }

    //Metodos para la gestion de cuentas de los clientes
    // TODO trabajar los metodos de abajo(Sahely)
    public static boolean inicioSesion(int id, String contrasena){
            /* 
            * Lo mismo que el método de abajo, podría cambiarse para que devuelva un string con el tipo de error en vez de booleano
            */
            for (Cliente cliente : Panaderia.clientes){
    
                if (cliente.getId() == id && cliente.getContrasena().equals(contrasena)){
    
                    Cliente.setSesion(cliente);
                    return true;
    
                }
    
            }
            return false;
    
    }


    public static boolean crearCuenta(String nombre, int id, String contrasena, double presupuesto){
        /* 
         * Esta función se encarga de crear una cuenta de cliente, la idea es que solo pase los parametros nombre, id, contrasena y presupuesto (O tambien se puede plantear para que se pregunte por el presupuesto luego de crear la cuenta)
         * si no existe un cliente con el mismo nombre e id, se crea un cliente con los parametros dados (Se manda a registro cliente)
         * en caso contrario, si ya existe, se devuelve false, para que la capa funcional pueda hacer sus respectivas impresiones y volver a llamar el crear cuenta
         * También se puede cambiar para devolver un String indicando el tipo de error en vez de un booleano, esa puede ser mejor opcion
         * 
         * También recomiendo plantear otra funcion (está abajo) que se encargue de verificar si la Contrasena es valida, tipo poniendole una longitud minima y que deba incluir numeros o así, sería bacano
         * claramente la info de esa funcion debería ser notificada a traves de retornos de strings para saver que está mal
        */
        return true;
    }

    public static String verificarContrasenaNueva(String Contrasena){
        /* 
         * Esta función se encarga de verificar si la Contrasena nueva cumple con los requisitos de seguridad
         * Si no cumple con los requisitos, devuelve un string indicando el tipo de error
         * Si cumple con los requisitos, devuelve un string vacio
        */
        return "";
    }

    //Método sobrevargado registrarCliente
    
    /**
     * Registra un nuevo cliente en la panadería con la información proporcionada.
     * @param nombre El nombre del cliente.
     * @param id El identificador del cliente.
     * @param tipoDescuento El tipo de descuento que se aplicará al cliente.
     * @return Un mensaje indicando que el cliente ha sido registrado con éxito.
     */
    public static String registrarCliente(String nombre, Integer id, String contrasena) {
    	
    	Cliente cliente = new Cliente(nombre, id, contrasena);
    	
    	Panaderia.clientes.add(cliente);
    	
    	return "Ha sido registrado como cliente con exito bajo el nombre: " + cliente.getNombre();
    	
    }
    
    /**
     * Registra un nuevo cliente con el nombre, ID y presupuesto dados.
     * @param nombre El nombre del cliente.
     * @param id El ID del cliente.
     * @param presupuesto El presupuesto del cliente.
     * @return Un mensaje indicando que el cliente ha sido registrado con éxito.
     * El mensaje incluye el nombre del cliente registrado.
     */
    public static String registrarCliente(String nombre, Integer id, String contrasena, double presupuesto) {
    	
    	Cliente cliente = new Cliente(nombre, id, contrasena, presupuesto);
    	
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
    
	//Este método se llama cuando en alguna parte del proceso de preparación de la compra hacen falta ingredientes 
	//Escoge aleatoriamente un trabajador para ir a conseguir los ingredientes y tiene métodos de lectura que permiten las impresiones en la capa funcional
	//Recibe un map de String y enteros para saber exactamente qué debe comprar y qué cantidad
	
    public static void comprarIngredientes(Map<String, Integer> listingredientes) {
    	
    	Trabajador elegido = Panaderia.trabajadorAleatorio();
    	
    	GestionCompra.lecturaCompra(elegido.isRobado());

        boolean x = elegido.conseguirIngredientes(listingredientes);

        while (x == true){
        	
        	GestionCompra.lecturaRobo(x);
            Panaderia.comprarIngredientes(listingredientes);
            
        }
        
        GestionCompra.lecturaRobo(x);
    }

        //METODOS DE FACTURACION
    public static boolean facturar(Recibo recibo){ //este metodo le resta el dinero del presupuesto al cliente y se lo pasa a la panaderia cuando el cliente elige pagar
        if (Cliente.getSesion().getPresupuesto() >= recibo.getTotal()){
            Cliente.getSesion().setPresupuesto(Cliente.getSesion().getPresupuesto()-recibo.getTotal());
            Panaderia.dinero += recibo.getTotal();
            recibo.setPagado(true);
            Cliente.getSesion().getRecibos().add(recibo);
            return true;
        }
        else{
            return false;
        }
}
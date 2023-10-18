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
import gestorAplicacion.humanos.Catastrofe;
import gestorAplicacion.humanos.Cliente;
import gestorAplicacion.humanos.Cocinero;
import gestorAplicacion.humanos.Domiciliario;
import gestorAplicacion.humanos.Trabajador;
import gestorAplicacion.humanos.Cliente.*;
import gestorAplicacion.humanos.Cliente.Direccion;

import java.util.Collections;

public class Panaderia implements Serializable {
	
    private ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
    private ArrayList<Cocinero> cocineros = new ArrayList<Cocinero>();
    private ArrayList<Domiciliario> domiciliarios = new ArrayList<Domiciliario>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    private double dinero;
    private double valorDeudas;
    private boolean enQuiebra = false;

    private static Canasta canastaDelDia;
    private ArrayList<Canasta> canastasPublicadas = new ArrayList<Canasta>();
    private Inventario inventario;
    
    static {
        // Agregar lista de productos de la canasta del dia
        canastaDelDia = new Canasta();
    }

    public Panaderia() {

    }
    
    //Métodos Get y Set

	public ArrayList<Trabajador> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(ArrayList<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}

	public ArrayList<Cocinero> getCocineros() {
		return cocineros;
	}

	public void setCocineros(ArrayList<Cocinero> cocineros) {
		this.cocineros = cocineros;
	}

	public ArrayList<Domiciliario> getDomiciliarios() {
		return domiciliarios;
	}

	public void setDomiciliarios(ArrayList<Domiciliario> domiciliarios) {
		this.domiciliarios = domiciliarios;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}

	public double getValorDeudas() {
		return valorDeudas;
	}

	public void setValorDeudas(double valorDeudas) {
		this.valorDeudas = valorDeudas;
	}

	public boolean isEnQuiebra() {
		return enQuiebra;
	}

	public void setEnQuiebra(boolean enQuiebra) {
		this.enQuiebra = enQuiebra;
	}

	public static Canasta getCanastaDelDia() {
		return canastaDelDia;
	}

	public static void setCanastaDelDia(Canasta canastaDelDia) {
		Panaderia.canastaDelDia = canastaDelDia;
	}

	public ArrayList<Canasta> getCanastasPublicadas() {
		return canastasPublicadas;
	}

	public void setCanastasPublicadas(ArrayList<Canasta> canastasPublicadas) {
		this.canastasPublicadas = canastasPublicadas;
	}
	
    public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}
	
	//Metodos para agregar elementos a las listas

	public void agregarTrabajador(Trabajador cocinero) {
        this.trabajadores.add(cocinero);
    }

    public void agregarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void eliminarCocinero(Trabajador cocinero) {
        this.trabajadores.remove(cocinero);
    }

    public void eliminarCliente(Cliente cliente) {
        this.clientes.remove(cliente);
    }

    public void agregarDinero(double dinero) {
        this.dinero += dinero;
    }

    public void restarDinero(double dinero) {
        this.dinero -= dinero;
    }

    public void agregarCanastasPublicadas(Canasta canasta) {
        canastasPublicadas.add(canasta);
    }

    //TODO corregir importacion de cocinero
    
    public Cocinero contratarCocinero(String nombre, double habilidad,double calificacion, double dineroEnMano, String especialidad) {
        Cocinero indicado = new Cocinero(nombre, habilidad,calificacion, dineroEnMano, especialidad);
        this.cocineros.add(indicado);
        return indicado;
        
    }
    

    //Métodos para saldar las deudas de la panadería
    
    //Este método se encarga de saldar las deudas de la panadería
    //No tiene parámetros ya que trabaja con los atributos de esta
    //Devuelve un booleano que será true si la panadería quebró y false si no lo hizo
    //Este booleano quedará guardado en el atributo enQuiebra de la panadería 
    //Para evitar el fin del programa, siempre que la panadería quiebre la comprará una franquicia más grande y le dará dinero 
    
    public boolean saldarDeudas(){

        if (this.valorDeudas < this.dinero){

            this.dinero = this.dinero-this.valorDeudas;
            this.valorDeudas = 0;
            this.enQuiebra = false;
            return this.enQuiebra;
        }

        else {
            
            this.enQuiebra = true;
            this.dinero = 10000000;
            return this.enQuiebra;
        }

    }
    
  //Este método se encarga de pedir un prestamo cuando la panadería no tenga suficiente dinero para comprar ingredientes
  //El parámetro double es el valor de la compra que necesita hacer la panadería
  //No devolverá nada ya que agregará el dinero que necesite la panadería una vez se acepte el prestamo
  //El prestamo será aceptado solamente si la panadería no tiene deudas

    public void conseguirPrestamo(double valorNecesitado) {

        if (this.valorDeudas == 0){

            this.dinero += valorNecesitado;
            this.valorDeudas = valorNecesitado;

        }

        else{

            this.saldarDeudas();
            
            while (this.enQuiebra == true) {
            
                GestionCompra.lecturaQuiebra(this.enQuiebra);
                this.saldarDeudas();
        
            }
            
            this.dinero += valorNecesitado;
            this.valorDeudas = valorNecesitado;

        }
        
        GestionCompra.lecturaQuiebra(this.enQuiebra);

    }

    //TODO Desarrollar el metodo cobrarCliente que recibe una lista de canastas y un recibo y cobra al cliente, además actualiza la plata de la panaderia
    public void cobrarCliente(List<Canasta> canastas, List<Recibo> recibo) {
        
    }

    public void enviarDomicilio(Canasta canasta, Cliente cliente) {
        Domiciliario domiciliario = domiciliarioAleatorio();
        ArrayList<Producto> producto = canasta.getProductos();

        for (Producto p : producto){
            if (p instanceof ProductoFrio){
                ((ProductoFrio)p).empaqueCongelador(domiciliario);
            }
        }

        Catastrofe malechor = new Catastrofe();
        domiciliario = malechor.pincharLLanta(domiciliario);

        if (!domiciliario.isLicencia()){
            this.restarDinero(10000);
            domiciliario.setLicencia(true);
        }

        domiciliario.setCanasta(canasta);
        domiciliario.setOcupado(true);
        boolean logro = domiciliario.laborParticular(canasta);
        while (!logro){
            domiciliario.setHabilidad(domiciliario.getHabilidad()+1);
            logro = domiciliario.laborParticular(canasta);
            this.restarDinero(10000);
            domiciliario.setLicencia(true);
        }
        cliente.setDomiciliario(domiciliario);
    }

    public void reviewDomiciliario(Domiciliario domiciliario){
        double calificacion = domiciliario.getCalificacion();
        if (calificacion < 3){
            domiciliario.setSalario(domiciliario.getSalario()*0.9);
        } else if (calificacion == 5){
            domiciliario.setSalario(domiciliario.getSalario()*1.1);
        }
    }

    public void reviewCocinero(Cocinero cocinero){
        double calificacion = cocinero.getCalificacion();
        if (calificacion < 3){
            cocinero.setSalario(cocinero.getSalario()*0.9);
        } else if (calificacion == 5){
            cocinero.setSalario(cocinero.getSalario()*1.1);
        }
    }


    /**
     * Método que permite cocinar una canasta de productos utilizando un cocinero aleatorio.
     * @param productosParaCocinar HashMap que contiene los productos y su cantidad necesaria para cocinar.
     */
    public void cocinar(HashMap<String, Integer> productosParaCocinar) {
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
    
    /**
     * Agrega los productos especificados en una canasta y devuelve una lista de los productos agregados.
     * Si algún producto no tiene suficiente cantidad en la panadería, se cocinará la cantidad faltante.
     * @param productos un HashMap que contiene los IDs de los productos y la cantidad deseada de cada uno.
     * @return una ArrayList con los productos agregados a la canasta.
     */
    
    public ArrayList<Producto> agregarProductosACanasta(HashMap<String, Integer> productos) {
        ArrayList<Producto> productosCanasta = new ArrayList<Producto>();
        HashMap<String, Integer> productosFaltantes = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : productos.entrySet()) {
            int cantidadExistente = this.inventario.verificarCantidadIngredientePorId(entry.getKey());
            if (cantidadExistente-entry.getValue()<0){
                productosFaltantes.put(entry.getKey(),(cantidadExistente-entry.getValue())*(-2));
            }
        }
        if(!productosFaltantes.isEmpty()){
            cocinar(productosFaltantes);
        }
        for (Map.Entry<String, Integer> entry : productos.entrySet()) {
            for (int i=0;i<entry.getValue();i++){
                productosCanasta.add(this.inventario.buscarProductoPorId(entry.getKey()));
                this.inventario.restarProducto(entry.getKey(),entry.getValue());
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
    public ArrayList<Ingrediente> agregarIngredientesACanasta(HashMap<String, Integer> ingredientes) {
        ArrayList<Ingrediente> ingredientesCanasta = new ArrayList<Ingrediente>();
        HashMap<String, Integer> ingredientesFaltantes = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : ingredientes.entrySet()) {
            int cantidadExistente = this.inventario.verificarCantidadIngredientePorId(entry.getKey());
            if (cantidadExistente-entry.getValue()<0){
                ingredientesFaltantes.put(entry.getKey(),(cantidadExistente-entry.getValue())*(-2));
            }
        }
        if(!ingredientesFaltantes.isEmpty()){
            comprarIngredientes(ingredientesFaltantes);
        }
        for (Map.Entry<String, Integer> entry : ingredientes.entrySet()) {
            for (int i=0;i<entry.getValue();i++){
                ingredientesCanasta.add(this.inventario.buscarIngredientePorId(entry.getKey()));
                this.inventario.restarIngrediente(entry.getKey(),entry.getValue());
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
    public ArrayList<ArrayList<Ingrediente>> agregarKitsACanasta(HashMap<String, Integer> kitsEnLista) {
        ArrayList<ArrayList<Ingrediente>> kitsCanasta = new ArrayList<ArrayList<Ingrediente>>();
        HashMap<String, Integer> ingredientesFaltantes = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> kit : kitsEnLista.entrySet()) {
            String idKit = kit.getKey();
            Integer cantidad = kit.getValue();
            Map<String, Integer> ingredientesKit = Producto.obtenerObjetoPorId(idKit).getIngredientes();
            ingredientesKit.forEach((ingrediente,cantidadIngrediente)-> ingredientesKit.put(ingrediente,cantidadIngrediente*cantidad));
            for(Map.Entry<String, Integer> entry : ingredientesKit.entrySet()){
                int cantidadExistente = this.inventario.verificarCantidadIngredientePorId(entry.getKey());
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
                        kitCanasta.add(this.inventario.buscarIngredientePorId(entry.getKey()));
                        this.inventario.restarIngrediente(entry.getKey(),1);
                    }
                }
                kitsCanasta.add(kitCanasta);
            }
        }
        return kitsCanasta;
    }

    //Metodos para la gestion de cuentas de los clientes
    // TODO trabajar los metodos de abajo(Sahely)
    public boolean inicioSesion(int id, String contrasena){
            /* 
            * Lo mismo que el método de abajo, podría cambiarse para que devuelva un string con el tipo de error en vez de booleano
            */
            for (Cliente cliente : this.clientes){
    
                if (cliente.getId() == id && cliente.getContrasena().equals(contrasena)){
    
                    Cliente.setSesion(cliente);
                    return true;
    
                }
    
            }
            return false;
    
    }


    public boolean crearCuenta(String nombre, int id, String contrasena, double presupuesto){
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

    public String verificarContrasenaNueva(String Contrasena){
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
    public String registrarCliente(String nombre, Integer id, String contrasena) {
    	
    	Cliente cliente = new Cliente(nombre, id, contrasena);
    	
    	this.clientes.add(cliente);
    	
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
    public String registrarCliente(String nombre, Integer id, String contrasena, double presupuesto) {
    	
    	Cliente cliente = new Cliente(nombre, id, contrasena, presupuesto);
    	
    	this.clientes.add(cliente);
    	
    	return "Ha sido registrado como cliente con exito bajo el nombre: " + cliente.getNombre();
    
    }

    public Trabajador trabajadorAleatorio(){

        ArrayList<Trabajador> x = (ArrayList<Trabajador>) this.trabajadores.clone();
        
        Collections.shuffle(x);

        Trabajador elegido = x.get(0);

        return elegido;

    }

    public Cocinero cocineroAleatorio(){
        
        ArrayList<Cocinero> x = (ArrayList<Cocinero>) this.cocineros.clone();
        
        Collections.shuffle(x);

        Cocinero elegido = x.get(0);

        return elegido;

    }

	public Domiciliario domiciliarioAleatorio(){

        ArrayList<Domiciliario> x = (ArrayList<Domiciliario>) this.domiciliarios.clone();
        
        Collections.shuffle(x);

        Domiciliario elegido = x.get(0);

        return elegido;

    }
    
	//Este método se llama cuando en alguna parte del proceso de preparación de la compra hacen falta ingredientes 
	//Escoge aleatoriamente un trabajador para ir a conseguir los ingredientes y tiene métodos de lectura que permiten las impresiones en la capa funcional
	//Recibe un map de String y enteros para saber exactamente qué debe comprar y qué cantidad
	
    public void comprarIngredientes(Map<String, Integer> listingredientes) {
    	
    	Trabajador elegido = this.trabajadorAleatorio();
    	
    	GestionCompra.lecturaCompra(elegido.isRobado());

        boolean x = elegido.conseguirIngredientes(listingredientes);

        while (x == true){
        	
        	GestionCompra.lecturaRobo(x);
            this.comprarIngredientes(listingredientes);
            
        }
        
        GestionCompra.lecturaRobo(x);
    }

        //METODOS DE FACTURACION
    public boolean facturar(Recibo recibo){ //este metodo le resta el dinero del presupuesto al cliente y se lo pasa a la panaderia cuando el cliente elige pagar
        if (Cliente.getSesion().getPresupuesto() >= recibo.getTotal()){
            Cliente.getSesion().setPresupuesto(Cliente.getSesion().getPresupuesto()-recibo.getTotal());
            this.dinero += recibo.getTotal();
            recibo.setPagado(true);
            Cliente.getSesion().getRecibos().add(recibo);
            return true;
        }
        else{
            return false;
        }
      }

    public static Recibo generarRecibo(){
       return new Recibo(Cliente.getSesion(), Cliente.getSesion().getCanastaEnMano().getCostoTrasDescuentoEnLista(), Cliente.getSesion().getTipoDescuento().getValor()); //puede que cambie parametros del constructor despues
    }
}
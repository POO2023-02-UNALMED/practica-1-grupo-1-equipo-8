package gestorAplicacion.gestion;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import UIMain.GestionConseguirIngredientes;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.comida.ProductoFrio;
import gestorAplicacion.humanos.Catastrofe;
import gestorAplicacion.humanos.Cliente;
import gestorAplicacion.humanos.Cocinero;
import gestorAplicacion.humanos.Domiciliario;
import gestorAplicacion.humanos.Trabajador;

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
        this.dinero = 1000000;
        this.valorDeudas = 0;
        this.enQuiebra = false;
        this.inventario = new Inventario();
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

    public void agregarDomiciliario(Domiciliario domiciliario) {
        this.domiciliarios.add(domiciliario);
    }

    public void eliminarDomiciliario(Domiciliario domiciliario) {
        this.domiciliarios.remove(domiciliario);
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

    public ArrayList<Canasta> getCanastasPublicadas(Canasta canasta) {
        return canastasPublicadas;
    }

    public Inventario getInventario(Inventario inventario) {
        return inventario;
    }

    /**
     * Contrata un nuevo cocinero y lo agrega a la lista de cocineros de la panadería.
     * @param nombre El nombre del cocinero a contratar.
     * @param habilidad La habilidad del cocinero a contratar.
     * @param calificacion La calificación del cocinero a contratar.
     * @param dineroEnMano La cantidad de dinero en mano del cocinero a contratar.
     * @param especialidad La especialidad del cocinero a contratar.
     * @return El objeto Cocinero recién contratado.
     */
    public Cocinero contratarCocinero(String nombre, double habilidad,double calificacion, double dineroEnMano, String especialidad) {
        Cocinero indicado = new Cocinero(nombre, habilidad,calificacion, dineroEnMano, especialidad, this);
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
            
                GestionConseguirIngredientes.lecturaQuiebra(this.enQuiebra);
                this.saldarDeudas();
        
            }
            
            this.dinero += valorNecesitado;
            this.valorDeudas = valorNecesitado;

        }
        
        GestionConseguirIngredientes.lecturaQuiebra(this.enQuiebra);

    }

    //TODO Desarrollar el metodo cobrarCliente que recibe una lista de canastas y un recibo y cobra al cliente, además actualiza la plata de la panaderia
    public void cobrarCliente(List<Canasta> canastas, List<Recibo> recibo) {
        
    }

    public void enviarDomicilio(Canasta canasta, Cliente cliente) {
        Domiciliario domiciliario = domiciliarioAleatorio();
        ArrayList<Producto> producto = canasta.getProductos();

        for (Producto p : producto){
            if (p instanceof ProductoFrio){
                domiciliario = ((ProductoFrio)p).empaqueCongelador(domiciliario);
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
        cliente.setDomiciliario(domiciliario);
        boolean logro = domiciliario.laborParticular(canasta);
        while (!logro){
            domiciliario.setHabilidad(domiciliario.getHabilidad()+1);
            logro = domiciliario.laborParticular(canasta);
            this.restarDinero(10000);
            domiciliario.setLicencia(true);
        }
        cliente.setDomiciliario(null);
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
                        Ingrediente.obtenerObjetoPorId(entry.getKey()).setVecesVendido(Ingrediente.obtenerObjetoPorId(entry.getKey()).getVecesVendido()+1);
                        Ingrediente.organizarTopMasVendidos();
                        this.inventario.restarIngrediente(entry.getKey(),1);
                    }
                }
                kitsCanasta.add(kitCanasta);
            }
        }
        return kitsCanasta;
    }

    //Metodos para la gestion de cuentas de los clientes

    /**
     * Busca un cliente en la lista de clientes de la panadería que tenga el ID especificado.
     * @param id El ID del cliente a buscar.
     * @return El cliente con el ID especificado, o null si no se encuentra.
     */
    public Cliente inicioSesionId(int id){

        for (Cliente cliente : getClientes()){

            if (cliente.getId() == id){

                return cliente;

            }

        }
        return null;
    
    }

    /**
     * Inicia sesión para un cliente con una contraseña dada.
     * @param cliente El cliente que desea iniciar sesión.
     * @param contrasena La contraseña del cliente.
     * @return Un mensaje indicando si el inicio de sesión fue exitoso o no.
     */
    public String inicioSesionConstrasena(Cliente cliente, String contrasena){
            
            if (cliente.getContrasena().equals(contrasena)){
    
                Cliente.setSesion(cliente);
                return "Inicio de sesion exitoso";
    
            }
    
            else{
                
                return "Contrasena incorrecta";
    
            }
    
    }

    /**
     * Crea una nueva cuenta de cliente con el nombre, id y contraseña proporcionados y la agrega a la lista de clientes de la panadería.
     * @param nombre el nombre del cliente
     * @param id el identificador único del cliente
     * @param contrasena la contraseña de la cuenta del cliente
     * @return un mensaje que indica que la cuenta se ha creado con éxito
     */
    public String crearCuenta(String nombre, int id, String contrasena){
        Cliente cliente = new Cliente(nombre, id, contrasena);
        this.clientes.add(cliente);
        Cliente.setSesion(cliente);
        
        return "Cuenta creada con exito";
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
    	
    	GestionConseguirIngredientes.lecturaCompra(elegido.isRobado());

        if ((elegido instanceof Domiciliario) & !(elegido instanceof Cocinero)){
            
            Domiciliario elegido2 = (Domiciliario) elegido;
            boolean x = elegido2.conseguirIngredientes(listingredientes);

            while (x == true){
        	
        	GestionConseguirIngredientes.lecturaRobo(x);
            this.comprarIngredientes(listingredientes);
            
        }
        
        GestionConseguirIngredientes.lecturaRobo(x);
    }

        else{
            
            Cocinero elegido2 = (Cocinero) elegido;
            boolean x = elegido2.conseguirIngredientes(listingredientes);

            while (x == true){
        	
        	GestionConseguirIngredientes.lecturaRobo(x);
            this.comprarIngredientes(listingredientes);
            
        }
        
        GestionConseguirIngredientes.lecturaRobo(x);
    }
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

    public static Recibo generarRecibo(){ //forma rapida de construir el recibo
       return new Recibo(Cliente.getSesion(), Cliente.getSesion().getCanastaOrden()); //puede que cambie parametros del constructor despues
    }
}
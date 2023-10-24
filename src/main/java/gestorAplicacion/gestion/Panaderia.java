package gestorAplicacion.gestion;

import java.util.HashMap;
import java.util.Map;
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

/**
 * La clase Panaderia representa una panadería que cuenta con trabajadores, clientes, domiciliarios, un inventario y una canasta del día.
 * Además, tiene un atributo de dinero que indica la cantidad de dinero que tiene la panadería, un atributo de valorDeudas que indica la cantidad de deudas que tiene la panadería y un atributo enQuiebra que indica si la panadería está en quiebra o no.
 * La panadería puede contratar cocineros, agregar y eliminar trabajadores, clientes y domiciliarios, agregar y restar dinero, publicar canastas y saldar sus deudas.
 * También cuenta con métodos para obtener las listas de trabajadores, cocineros, domiciliarios y clientes, la canasta del día y las canastas publicadas.
 */
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

    // Métodos Get y Set

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

    // Metodos para agregar elementos a las listas

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
     * Contrata un nuevo cocinero y lo agrega a la lista de cocineros de la
     * panadería.
     * 
     * @param nombre       El nombre del cocinero a contratar.
     * @param habilidad    La habilidad del cocinero a contratar.
     * @param calificacion La calificación del cocinero a contratar.
     * @param dineroEnMano La cantidad de dinero en mano del cocinero a contratar.
     * @param especialidad La especialidad del cocinero a contratar.
     * @return El objeto Cocinero recién contratado.
     */
    public Cocinero contratarCocinero(String nombre, double habilidad, double calificacion, double dineroEnMano,
            String especialidad) {
        Cocinero indicado = new Cocinero(nombre, habilidad, calificacion, dineroEnMano, especialidad, this);
        return indicado;

    }

    // Métodos para saldar las deudas de la panadería

    // Este método se encarga de saldar las deudas de la panadería
    // No tiene parámetros ya que trabaja con los atributos de esta
    // Devuelve un booleano que será true si la panadería quebró y false si no lo
    // hizo
    // Este booleano quedará guardado en el atributo enQuiebra de la panadería
    // Para evitar el fin del programa, siempre que la panadería quiebre la comprará
    // una franquicia más grande y le dará dinero

    public boolean saldarDeudas() {

        if (this.valorDeudas < this.dinero) {

            this.dinero = this.dinero - this.valorDeudas;
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

    // Este método se encarga de pedir un prestamo cuando la panadería no tenga
    // suficiente dinero para comprar ingredientes
    // El parámetro double es el valor de la compra que necesita hacer la panadería
    // No devolverá nada ya que agregará el dinero que necesite la panadería una vez
    // se acepte el prestamo
    // El prestamo será aceptado solamente si la panadería no tiene deudas

    public void conseguirPrestamo(double valorNecesitado) {

        if (this.valorDeudas == 0) {

            this.dinero += valorNecesitado;
            this.valorDeudas = valorNecesitado;

        }

        else {

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


    /**
     * Envía una canasta de productos a un cliente a través de un domiciliario aleatorio.
     * Si la canasta contiene productos fríos, el domiciliario los empaca en un congelador.
     * Luego, se simula una posible catástrofe en la que el domiciliario pincha una llanta.
     * Si el domiciliario no tiene licencia, se le resta dinero y se le otorga la licencia.
     * Se establece la canasta y el costo del domicilio en el domiciliario.
     * Se verifica si el domiciliario puede realizar la entrega de la canasta, en caso contrario se aumenta su habilidad.
     * Finalmente, se establece que el cliente ya no tiene un domiciliario asignado.
     * @param canasta La canasta de productos que se enviará al cliente.
     * @param cliente El cliente que recibirá la canasta de productos.
     */
    public void enviarDomicilio(Canasta canasta, Cliente cliente) {
        Domiciliario domiciliario = cliente.getDomiciliario();
        
        cliente.setDomiciliario(domiciliario);
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
        double costo = domiciliario.calcularCostoDomicilio(cliente, canasta);
        domiciliario.setCostoDomicilio(costo);
        while (!domiciliario.laborParticular(canasta)){
            domiciliario.setHabilidad(domiciliario.getHabilidad()+10);
            this.restarDinero(10000);
            domiciliario.setLicencia(true);
        }
        cliente.setCanastaEnMano(domiciliario.getCanasta());
        domiciliario.setCanasta(null);
    }

    /**
     * Revisa la calificación de un domiciliario y ajusta su salario en consecuencia.
     * Si la calificación es menor a 3, se reduce su salario en un 10%.
     * Si la calificación es igual a 5, se aumenta su salario en un 10%.
     * @param domiciliario el domiciliario cuyo salario se va a ajustar
     */
    public void reviewDomiciliario(Domiciliario domiciliario) {
        double calificacion = domiciliario.getCalificacion();
        if (calificacion < 3) {
            domiciliario.setSalario(domiciliario.getSalario() * 0.9);
        } else if (calificacion == 5) {
            domiciliario.setSalario(domiciliario.getSalario() * 1.1);
        }
    }

    /**
     * Revisa la calificación de un cocinero y ajusta su salario en consecuencia.
     * Si la calificación es menor a 3, se reduce su salario en un 10%.
     * Si la calificación es igual a 5, se aumenta su salario en un 10%.
     * @param cocinero El cocinero cuyo salario se va a ajustar.
     */
    public void reviewCocinero(Cocinero cocinero) {
        double calificacion = cocinero.getCalificacion();
        if (calificacion < 3) {
            cocinero.setSalario(cocinero.getSalario() * 0.9);
        } else if (calificacion == 5) {
            cocinero.setSalario(cocinero.getSalario() * 1.1);
        }
    }

    /**
     * Busca y devuelve la canasta con el identificador especificado.
     * @param id el identificador de la canasta a buscar
     * @return la canasta con el identificador especificado, o null si no se encuentra ninguna canasta con ese identificador
     */
    public Canasta obtenerCanastaPorId(String id){
        for (Canasta canasta : canastasPublicadas){
            if (canasta.getIdentificador().equals(id)){
                return canasta;
            }
        }
        return null;
    }

    /**
     * Método que permite cocinar una canasta de productos utilizando un cocinero
     * aleatorio.
     * 
     * @param productosParaCocinar HashMap que contiene los productos y su cantidad
     *                             necesaria para cocinar.
     */
    public void cocinar(HashMap<String, Integer> productosParaCocinar) {
        Canasta canastaDeProductosCocinar = new Canasta();
        canastaDeProductosCocinar.setProductosEnLista(productosParaCocinar);
        ArrayList<Canasta> canastaParaCocinar = new ArrayList<Canasta>();
        canastaParaCocinar.add(canastaDeProductosCocinar);
        Cocinero cocinero = cocineroAleatorio();
        while (true) {
            if (cocinero.laborParticular(canastaDeProductosCocinar)) {
                break;
            }
        }
    }

    /**
     * Agrega los productos especificados en una canasta y devuelve una lista de los
     * productos agregados.
     * Si algún producto no tiene suficiente cantidad en la panadería, se cocinará
     * la cantidad faltante.
     * 
     * @param productos un HashMap que contiene los IDs de los productos y la
     *                  cantidad deseada de cada uno.
     * @return una ArrayList con los productos agregados a la canasta.
     */

    public ArrayList<Producto> agregarProductosACanasta(HashMap<String, Integer> productos) {
        ArrayList<Producto> productosCanasta = new ArrayList<Producto>();
        HashMap<String, Integer> productosFaltantes = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : productos.entrySet()) {
            int cantidadExistente = this.inventario.verificarCantidadIngredientePorId(entry.getKey());
            if (cantidadExistente - entry.getValue() < 0) {
                productosFaltantes.put(entry.getKey(), (cantidadExistente - entry.getValue()) * (-2));
            }
        }
        if (!productosFaltantes.isEmpty()) {
            cocinar(productosFaltantes);
        }
        for (Map.Entry<String, Integer> entry : productos.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                productosCanasta.add(this.inventario.buscarProductoPorId(entry.getKey()));
                this.inventario.restarProducto(entry.getKey(), entry.getValue());
            }
        }
        return productosCanasta;
    }

    /**
     * Agrega los ingredientes especificados en una canasta y devuelve una lista con
     * los ingredientes agregados.
     * Si algún ingrediente no tiene suficiente cantidad en la panadería, se
     * comprará la cantidad faltante.
     * 
     * @param ingredientes Un HashMap que contiene los ingredientes y la cantidad
     *                     deseada de cada uno.
     * @return Una lista de objetos Ingrediente que representa los ingredientes
     *         agregados a la canasta.
     */
    public ArrayList<Ingrediente> agregarIngredientesACanasta(HashMap<String, Integer> ingredientes) {
        ArrayList<Ingrediente> ingredientesCanasta = new ArrayList<Ingrediente>();
        HashMap<String, Integer> ingredientesFaltantes = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : ingredientes.entrySet()) {
            int cantidadExistente = this.inventario.verificarCantidadIngredientePorId(entry.getKey());
            if (cantidadExistente - entry.getValue() < 0) {
                ingredientesFaltantes.put(entry.getKey(), (cantidadExistente - entry.getValue()) * (-2));
            }
        }
        if (!ingredientesFaltantes.isEmpty()) {
            for(Map.Entry<String, Integer> entry : ingredientesFaltantes.entrySet()){
                ingredientesFaltantes.put(Ingrediente.obtenerObjetoPorId(entry.getKey()).getNombre(), entry.getValue());
                ingredientesFaltantes.remove(entry.getKey());
            }
            comprarIngredientes(ingredientesFaltantes);
        }
        for (Map.Entry<String, Integer> entry : ingredientes.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                ingredientesCanasta.add(this.inventario.buscarIngredientePorId(entry.getKey()));
                this.inventario.restarIngrediente(entry.getKey(), entry.getValue());
                Ingrediente.obtenerObjetoPorId(entry.getKey()).setVecesVendido(Ingrediente.obtenerObjetoPorId(entry.getKey()).getVecesVendido() + 1);
                Ingrediente.organizarTopMasVendidos();
            }
        }
        return ingredientesCanasta;
    }

    /**
     * Agrega los kits de productos a la canasta y retorna una lista de kits de
     * ingredientes.
     * Si no hay suficientes ingredientes para los kits, los compra automáticamente.
     * 
     * @param kitsEnLista HashMap que contiene los ids de los kits y la cantidad de
     *                    veces que se deben agregar a la canasta.
     * @return ArrayList de ArrayLists de ingredientes que representan los kits
     *         agregados a la canasta.
     */
    public ArrayList<ArrayList<Ingrediente>> agregarKitsACanasta(HashMap<String, Integer> kitsEnLista) {
        ArrayList<ArrayList<Ingrediente>> kitsCanasta = new ArrayList<ArrayList<Ingrediente>>();
        HashMap<String, Integer> ingredientesFaltantes = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> kit : kitsEnLista.entrySet()) {
            String idKit = kit.getKey();
            Integer cantidad = kit.getValue();
            Map<String, Integer> ingredientesKit = Producto.obtenerObjetoPorId(idKit).getIngredientes();
            ingredientesKit.forEach((ingrediente, cantidadIngrediente) -> ingredientesKit.put(ingrediente,
                    cantidadIngrediente * cantidad));
            for (Map.Entry<String, Integer> entry : ingredientesKit.entrySet()) {
                int cantidadExistente = this.inventario.verificarCantidadIngredientePorId(entry.getKey());
                if (cantidadExistente - entry.getValue() < 0) {
                    if (ingredientesFaltantes.containsKey(entry.getKey())) {
                        ingredientesFaltantes.put(entry.getKey(), ingredientesFaltantes.get(entry.getKey())
                                + (cantidadExistente - entry.getValue()) * (-2));
                    } else {
                        ingredientesFaltantes.put(entry.getKey(), (cantidadExistente - entry.getValue()) * (-2));
                    }
                }
            }
        }
        if (!ingredientesFaltantes.isEmpty()) {
            for(Map.Entry<String, Integer> entry : ingredientesFaltantes.entrySet()){
                ingredientesFaltantes.put(Ingrediente.obtenerObjetoPorId(entry.getKey()).getNombre(), entry.getValue());
                ingredientesFaltantes.remove(entry.getKey());
            }
            comprarIngredientes(ingredientesFaltantes);
        }
        for (Map.Entry<String, Integer> kit : kitsEnLista.entrySet()) {
            String idKit = kit.getKey();
            Integer cantidad = kit.getValue();
            for (int i = 0; i < cantidad; i++) {
                ArrayList<Ingrediente> kitCanasta = new ArrayList<Ingrediente>();
                for (Map.Entry<String, Integer> entry : Producto.obtenerObjetoPorId(idKit).getIngredientes()
                        .entrySet()) {
                    for (int j = 0; j < entry.getValue(); j++) {
                        kitCanasta.add(this.inventario.buscarIngredientePorId(entry.getKey()));
                        Ingrediente.obtenerObjetoPorId(entry.getKey()).setVecesVendido(Ingrediente.obtenerObjetoPorId(entry.getKey()).getVecesVendido() + 1);
                        Ingrediente.organizarTopMasVendidos();
                        this.inventario.restarIngrediente(entry.getKey(), 1);
                    }
                }
                kitsCanasta.add(kitCanasta);
            }
        }
        return kitsCanasta;
    }

    // Metodos para la gestion de cuentas de los clientes

    /**
     * Busca un cliente en la lista de clientes de la panadería que tenga el ID
     * especificado.
     * 
     * @param id El ID del cliente a buscar.
     * @return El cliente con el ID especificado, o null si no se encuentra.
     */
    public Cliente inicioSesionId(int id) {

        for (Cliente cliente : getClientes()) {

            if (cliente.getId() == id) {

                return cliente;

            }

        }
        return null;

    }

    /**
     * Inicia sesión para un cliente con una contraseña dada.
     * 
     * @param cliente    El cliente que desea iniciar sesión.
     * @param contrasena La contraseña del cliente.
     * @return Un mensaje indicando si el inicio de sesión fue exitoso o no.
     */
    public String inicioSesionConstrasena(Cliente cliente, String contrasena) {

        if (cliente.getContrasena().equals(contrasena)) {

            Cliente.setSesion(cliente);
            return "Inicio de sesion exitoso";

        }

        else {

            return "Contrasena incorrecta";

        }

    }

    /**
     * Crea una nueva cuenta de cliente con el nombre, id y contraseña
     * proporcionados y la agrega a la lista de clientes de la panadería.
     * 
     * @param nombre     el nombre del cliente
     * @param id         el identificador único del cliente
     * @param contrasena la contraseña de la cuenta del cliente
     * @return un mensaje que indica que la cuenta se ha creado con éxito
     */
    public String crearCuenta(String nombre, int id, String contrasena) {
        Cliente cliente = new Cliente(nombre, id, contrasena);
        this.clientes.add(cliente);
        Cliente.setSesion(cliente);

        return "Cuenta creada con exito";
    }

    /**
     * Devuelve un trabajador aleatorio de la lista de trabajadores de la panadería.
     * @return Trabajador elegido aleatoriamente.
     */
    public Trabajador trabajadorAleatorio() {

        ArrayList<Trabajador> x = (ArrayList<Trabajador>) this.trabajadores.clone();

        Collections.shuffle(x);

        Trabajador elegido = x.get(0);

        return elegido;

    }

    /**
     * Devuelve un cocinero aleatorio de la lista de cocineros de la panadería.
     * @return el cocinero elegido aleatoriamente.
     */
    public Cocinero cocineroAleatorio() {

        ArrayList<Cocinero> x = (ArrayList<Cocinero>) this.cocineros.clone();

        Collections.shuffle(x);

        Cocinero elegido = x.get(0);

        return elegido;

    }

    /**
     * Retorna un objeto de tipo Domiciliario elegido aleatoriamente de la lista de domiciliarios de la panadería.
     * @return Domiciliario elegido aleatoriamente.
     */
    public Domiciliario domiciliarioAleatorio() {

        ArrayList<Domiciliario> x = (ArrayList<Domiciliario>) this.domiciliarios.clone();

        Collections.shuffle(x);

        Domiciliario elegido = x.get(0);

        return elegido;

    }

    // Este método se llama cuando en alguna parte del proceso de preparación de la
    // compra hacen falta ingredientes
    // Escoge aleatoriamente un trabajador para ir a conseguir los ingredientes y
    // tiene métodos de lectura que permiten las impresiones en la capa funcional
    // Recibe un map de String y enteros para saber exactamente qué debe comprar y
    // qué cantidad

    public void comprarIngredientes(Map<String, Integer> listingredientes) {

        Domiciliario elegido = this.domiciliarioAleatorio();
        GestionConseguirIngredientes.lecturaCompra(elegido.isRobado());

        boolean x = elegido.conseguirIngredientes(listingredientes);
        GestionConseguirIngredientes.lecturaRobo(x);

        while (x == true) {

            elegido.setRobado(false);
            GestionConseguirIngredientes.lecturaCompra(elegido.isRobado());
            x = elegido.conseguirIngredientes(listingredientes);
            GestionConseguirIngredientes.lecturaRobo(x);
        }
        elegido.setRobado(true);
    }

    // METODOS DE FACTURACION
    /**
     * Este método resta el dinero del presupuesto al cliente y se lo pasa a la panadería cuando el cliente elige pagar.
     * 
     * @param recibo El recibo que se va a facturar.
     * @return true si el recibo se pudo facturar exitosamente, false si el cliente no tiene suficiente presupuesto para pagar el recibo.
     */
    public boolean facturar(Recibo recibo) { // este metodo le resta el dinero del presupuesto al cliente y se lo pasa a                                            // la panaderia cuando el cliente elige pagar
        if (Cliente.getSesion().getPresupuesto() >= recibo.getTotal()) {
            Cliente.getSesion().setPresupuesto(Cliente.getSesion().getPresupuesto() - recibo.getTotal());
            this.dinero += recibo.getTotal();
            recibo.setPagado(true);
            Cliente.getSesion().getRecibos().add(recibo);
            return true;
        } else {
            return false;
        }
    }
}
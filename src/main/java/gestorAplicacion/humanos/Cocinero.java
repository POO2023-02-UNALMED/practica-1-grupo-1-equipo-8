package gestorAplicacion.humanos;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.gestion.Panaderia;

public class Cocinero extends Domiciliario{
    private String especialidad;
    private boolean fallado = false;



    public Cocinero() {
        super();
    }

    public Cocinero(String nombre, String especialidad) {
        super(nombre);
        this.especialidad = especialidad;
    }

    public Cocinero(String nombre, double habilidad,double calificacion, double dineroEnMano, String especialidad) {
        super(nombre, habilidad, calificacion,dineroEnMano);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isFallado(){
        return fallado;
    }

    public void setFallado(boolean quemado) {
        this.fallado = quemado;
    }
    /**
     * Calcula los ingredientes faltantes necesarios para cocinar un producto específico.
     *
     * @param producto El producto que se desea cocinar.
     * @return Un mapa que contiene los ingredientes necesarios y la cantidad faltante de cada uno.
     *         Si no faltan ingredientes, el mapa estará vacío.
     */
    public Map<Ingrediente, Integer> ingredientesCocinero (Map<Ingrediente, Integer> ingredientesNecesarios) {
        // Revisa la cantidad de ingredientes disponibles en la panadería y calcula los faltantes.
        Map<Ingrediente, Integer> ingrFaltantes = Panaderia.revisarCantidadIngredientes(ingredientesNecesarios);
        // Retorna un mapa que contiene los ingredientes necesarios y la cantidad faltante de cada uno.
        return ingrFaltantes;
    }
    
    /**
     * Simula la posibilidad de que un producto cocinado por un cocinero se queme.
     *
     * @param cocinero El cocinero que cocina el producto.
     * @return true si el producto se quema, false en caso contrario.
     */
    /**
     * Encuentra al cocinero ideal para un proceso de cocina específico.
     *
     * @param proceso El nombre del proceso de cocina para el que se busca el cocinero ideal.
     * @return El cocinero ideal para el proceso o null si no se encuentra ninguno.
     */
    public Cocinero cocineroIdeal(String proceso) {
        Cocinero ideal=null;
        List<Cocinero> listaCocineros = Panaderia.getCocineros();
         // Itera a través de la lista de cocineros para encontrar el cocinero con la especialidad deseada.
        for (Cocinero cocinero : listaCocineros) {		// buscar en la base de datos el cocinero que su atributo especialidad coincida
            String especialidad= cocinero.getEspecialidad();
            if (especialidad.equals(proceso)) {
                ideal = cocinero;
                return ideal;
                // Se detiene la búsqueda una vez que se encuentra el cocinero ideal.
            }
        }
        Cocinero idealNew = Panaderia.contratarCocinero( nombre,  habilidad,  dineroEnMano,  proceso);
        return idealNew;
    }

    public void detenerCoccion(Producto producto){
        Map<Ingrediente,Integer> ingredientesUsados = producto.getIngredientes();
            for (Map.Entry<Ingrediente, Integer> usados : ingredientesUsados.entrySet()){
                Ingrediente ingUsado = usados.getKey();
                String ingrUsado = ingUsado.getId();
                Integer cantidad = usados.getValue();
                Panaderia.restarIngrediente(ingrUsado,cantidad);
            }
    }

    public boolean procesoCocinar(Producto producto){
        Catastrofe dificultad = new Catastrofe();
        List<String> procesoCook= producto.getProcesoDeCocina();
        for (String proceso : procesoCook){
            Cocinero chefIdeal = cocineroIdeal(proceso);
            boolean cookProducto = dificultad.dificultadProducto(chefIdeal);
            if (cookProducto){
                return true;
            }
        }
        return false;
    }

    public Map<Ingrediente, Integer> unirMapasIngredientes(List<Map<Ingrediente, Integer>> listaDeMapas){
        Map<Ingrediente, Integer> mapaAcumulativo = new HashMap<>();
        for (Map<Ingrediente, Integer> mapa : listaDeMapas) {
            mapa.forEach((clave, valor) -> mapaAcumulativo.merge(clave, valor, Integer::sum));
        }
        return mapaAcumulativo;
    }

    public Map<Ingrediente, Integer> multiplicarValoresEnMapa(Map<Ingrediente, Integer> mapa, int multiplicador) {
        Map<Ingrediente, Integer> nuevoMapa = new HashMap<>();
    
        for (Map.Entry<Ingrediente, Integer> entry : mapa.entrySet()) {
            Ingrediente ingrediente = entry.getKey();
            int valorOriginal = entry.getValue();
            int valorNuevo = valorOriginal * multiplicador;
            nuevoMapa.put(ingrediente, valorNuevo);
        }
    
        return nuevoMapa;
    }
    
    /**
     * Realiza una labor específica relacionada con la preparación y cocción de productos de la canasta.
     *
     * @param canasta El objeto Canasta que contiene los productos a procesar.
     * @return true si la labor se realiza con éxito; false si no se puede realizar debido a la falta de ingredientes o problemas de cocción.
     */
    @Override
    public boolean laborParticular(ArrayList<Canasta> canastaTrabajar) {
        Canasta canastaUnica = canastaTrabajar.get(0);
        // Obtiene los productos junto con sus cantidades de la canasta
        Map<Producto, Integer> productos = canastaUnica.getProductos();
        List<Map<Ingrediente, Integer>> listaDeMapas = new ArrayList<>();
        for (Map.Entry<Producto, Integer> product : productos.entrySet()) {
            Producto producto = product.getKey();
            Integer cantidad = product.getValue();
            Map<Ingrediente,Integer> ingredientesNecesarios = producto.getIngredientes();
            Map<Ingrediente,Integer> ingredientesAbsolutos = multiplicarValoresEnMapa(ingredientesNecesarios,cantidad);
            listaDeMapas.add(ingredientesAbsolutos);
        }
        Map<Ingrediente, Integer> listaIngredientesTotales = unirMapasIngredientes(listaDeMapas);
        Map<Ingrediente, Integer> ingrFaltantes = ingredientesCocinero(listaIngredientesTotales);
        if(!ingrFaltantes.isEmpty()){
            // Compra los ingredientes faltantes en la Panadería.
        Panaderia.comprarIngredientes(ingrFaltantes);
        // Retorna falso, indicando que no se puede realizar la labor debido a la falta de ingredientes.
        return false;
    }
    
         // Itera a través de los productos en la canasta nuevamente.
        for (Map.Entry<Producto, Integer> product : productos.entrySet()) {
            Producto producto = product.getKey();
            Cocinero cocinero = Panaderia.cocineroAleatorio();
            boolean fallado = cocinero.procesoCocinar(producto);
            if (fallado){
            cocinero.detenerCoccion(producto);
            return false;
            }
        }
        //verificar si hay un ingrediente caducado y si lo hay se elimina del inventario
        for (Map.Entry<Producto, Integer> product : productos.entrySet()) {
            Producto producto = product.getKey();
            Map<Ingrediente,Integer> ingredientesAUsar = producto.getIngredientes();
            for (Map.Entry<Ingrediente, Integer> verificar : ingredientesAUsar.entrySet()){
                Ingrediente ingVerificar = verificar.getKey();
                Integer cantidad = verificar.getValue();
                Ingrediente.revisarCaducidad(ingVerificar,cantidad);
            }
        }
        
     // Resta los ingredientes usados del inventario de la Panadería.
        for (Map.Entry<Producto, Integer> product : productos.entrySet()) {
            Producto producto = product.getKey();
            Cocinero cocinero = Panaderia.cocineroAleatorio();
            cocinero.detenerCoccion(producto);
        }
        // Si todos los productos se cocinan con éxito, retorna verdadero.
        return true;
    }
  
    //Este método se ejecuta cuando se llama el método comprarIngredientes de Panadería, basicamnete se encarga de comprar ingedientes cuando falta y añadirlos al inventario
    //Recibe una diccionario de Strings y enteros paea saber exactamente qué se debe comprar y cuánta cantidad
    //Cuando se "compran" nuevos ingredientes realmente se están creando objetos y añandiendose al inventario

    public boolean conseguirIngredientes(Map<String, Integer> listingredientes){

        double valorcompra = 0;
        this.robado = false;
        
        for (Map.Entry<String, Integer> ingrediente : listingredientes.entrySet()){

            int cantidad = ingrediente.getValue();
            valorcompra += (Ingrediente.obtenerObjetoPorNombre(ingrediente.getKey()).getPrecioDeCompra())*(cantidad*2);

        }

        if (valorcompra <= Panaderia.getDinero()){

            this.dineroEnMano += valorcompra;
            Panaderia.setDinero(Panaderia.getDinero()-valorcompra);

        }

        else{

            Panaderia.conseguirPrestamo( valorcompra);
            this.dineroEnMano += valorcompra;
            Panaderia.setDinero(Panaderia.getDinero()-valorcompra);

        }
        
        Catastrofe Ladron = Catastrofe.responsableAleatorio();
        Cocinero postRobo = Ladron.robarComprador(this);

        if (postRobo.robado = true){

            return this.robado;
        }

        else{

            this.dineroEnMano = 0;

        for (Map.Entry<String, Integer> compras : listingredientes.entrySet()){
            
            int cantidad = compras.getValue()*2;
            String ingrediente = compras.getKey();
            
            for(int i=0;i<cantidad;i++){
                Ingrediente ingrdt = Ingrediente.crearIngrediente(ingrediente);
                Panaderia.agregarIngrediente(ingrdt);
            }
        }

        return this.robado;
    
    }
  }
}

// use hasmaps y sale facil get datos

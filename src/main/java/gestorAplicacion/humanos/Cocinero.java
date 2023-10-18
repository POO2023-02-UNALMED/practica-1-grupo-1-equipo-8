package gestorAplicacion.humanos;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.comida.ProductoFrio;
import gestorAplicacion.comida.ProductoCaliente;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.gestion.Panaderia;

public class Cocinero extends Domiciliario{
    private String especialidad;
    private boolean fallado = false;
    private boolean trabajo = false;
    private boolean nevera;
    private boolean horno;
    private Panaderia panaderia;


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

    public boolean isTrabajo() {
        return trabajo;
    }

    public void setTrabajo(boolean trabajo) {
        this.trabajo = trabajo;
    }

    public boolean isNevera() {
        return nevera;
    }

    public void setNevera(boolean nevera) {
        this.nevera = nevera;
    }

    public boolean isHorno() {
        return horno;
    }

    public void setHorno(boolean horno) {
        this.horno = horno;
    }
    
    public Panaderia getPanaderia() {
		return panaderia;
	}

	public void setPanaderia(Panaderia panaderia) {
		this.panaderia = panaderia;
	}

	/**
     * Calcula los ingredientes faltantes necesarios para cocinar un producto específico.
     *
     * @param producto El producto que se desea cocinar.
     * @return Un mapa que contiene los ingredientes necesarios y la cantidad faltante de cada uno.
     *         Si no faltan ingredientes, el mapa estará vacío.
     */
    public Map<String, Integer> ingredientesCocinero (Map<String, Integer> ingredientesNecesarios) {
        // Revisa la cantidad de ingredientes disponibles en la panadería y calcula los faltantes.
        Map<String, Integer> ingrFaltantes = this.panaderia.revisarCantidadIngredientes(ingredientesNecesarios);
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
        List<Cocinero> listaCocineros = this.panaderia.getCocineros();
         // Itera a través de la lista de cocineros para encontrar el cocinero con la especialidad deseada.
        for (Cocinero cocinero : listaCocineros) {		// buscar en la base de datos el cocinero que su atributo especialidad coincida
            String especialidad= cocinero.getEspecialidad();
            if (especialidad.equals(proceso)) {
                ideal = cocinero;
                return ideal;
                // Se detiene la búsqueda una vez que se encuentra el cocinero ideal.
            }
        }
        Cocinero idealNew = this.panaderia.contratarCocinero( nombre,  habilidad, calificacion,  dineroEnMano,  proceso);
        return idealNew;
    }

    public void detenerCoccion(Producto producto){
        Map<String,Integer> ingredientesUsados = producto.getIngredientes();
            for (Map.Entry<String, Integer> usados : ingredientesUsados.entrySet()){
                String ingUsado = usados.getKey();
                Integer cantidad = usados.getValue();
                this.panaderia.restarIngrediente(ingUsado,cantidad);
            }
    }

    public boolean procesoCocinar(Producto producto){
        ArrayList<String> procesosProducto = producto.seleccionProcesosDeCocina();
        producto.setProcesoDeCocina(procesosProducto);
        Catastrofe dificultad = new Catastrofe();
        List<String> procesoCook= producto.getProcesoDeCocina();
        for (String proceso : procesoCook){
            Cocinero chefIdeal = cocineroIdeal(proceso);
            if (producto instanceof ProductoFrio) {
                ProductoFrio productoF = (ProductoFrio)producto;
                productoF.procesoCongelamiento(chefIdeal);
            }
            if (producto instanceof ProductoCaliente) {
                ProductoCaliente productoH = (ProductoCaliente)producto;
                productoH.procesoHornear(chefIdeal);
            }
            boolean cookProducto = dificultad.dificultadProducto(chefIdeal);
            if (cookProducto){
                chefIdeal.setHabilidad(chefIdeal.getHabilidad()+1);
                return true;
            }
            chefIdeal.setTrabajo(true);
        }
        return false;
    }

    public Map<String, Integer> unirMapasIngredientesId(List<Map<String, Integer>> listaDeMapas){
        Map<String, Integer> mapaAcumulativo = new HashMap<>();
        for (Map<String, Integer> mapa : listaDeMapas) {
            mapa.forEach((clave, valor) -> mapaAcumulativo.merge(clave, valor, Integer::sum));
        }
        return mapaAcumulativo;
    }

    public Map<String, Integer> multiplicarValoresEnMapa(Map<String, Integer> mapa, int multiplicador) {
        Map<String, Integer> nuevoMapa = new HashMap<>();
    
        for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
            String ingredienteId = entry.getKey();
            int valorOriginal = entry.getValue();
            int valorNuevo = valorOriginal * multiplicador;
            nuevoMapa.put(ingredienteId, valorNuevo);
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
    public boolean laborParticular(Canasta canastaTrabajar) {
        // Obtiene los productos junto con sus cantidades de la canasta
        HashMap<String, Integer> productos = canastaTrabajar.getProductosEnLista();
        List<Map<String, Integer>> listaDeMapas = new ArrayList<>();
        for (Map.Entry<String, Integer> product : productos.entrySet()) {
            String productoID = product.getKey();
            Producto producto = this.panaderia.buscarProductoPorId(productoID);
            Integer cantidad = product.getValue();
            Map<String,Integer> ingredientesNecesarios = producto.getIngredientes();
            Map<String,Integer> ingredientesAbsolutos = multiplicarValoresEnMapa(ingredientesNecesarios,cantidad);
            listaDeMapas.add(ingredientesAbsolutos);
        }
        Map<String, Integer> listaIngredientesTotales = unirMapasIngredientesId(listaDeMapas);
        Map<String, Integer> ingrFaltantes = ingredientesCocinero(listaIngredientesTotales);
        if(!ingrFaltantes.isEmpty()){
            // Compra los ingredientes faltantes en la Panadería.
        this.panaderia.comprarIngredientes(ingrFaltantes);
        // Retorna falso, indicando que no se puede realizar la labor debido a la falta de ingredientes.
        return false;
    }
        //verificar si hay un ingrediente caducado y si lo hay se elimina del inventario
        for (Map.Entry<String, Integer> product : productos.entrySet()) {
            String productoId = product.getKey();
            Producto producto = this.panaderia.buscarProductoPorId(productoId);
            Map<String,Integer> ingredientesAUsar = producto.getIngredientes();
            for (Map.Entry<String, Integer> verificar : ingredientesAUsar.entrySet()){
                String ingIdVerificar = verificar.getKey();
                Ingrediente ingVerificar = this.panaderia.buscarIngredientePorId(ingIdVerificar);
                Integer cantidad = verificar.getValue();
                Ingrediente.revisarCaducidad(ingVerificar,cantidad);
            }
        }
        
         // Itera a través de los productos en la canasta nuevamente.
        for (Map.Entry<String, Integer> product : productos.entrySet()) {
            String productoId = product.getKey();
            Producto producto = this.panaderia.buscarProductoPorId(productoId);
            Producto productoNew;
            if (producto instanceof ProductoFrio) {
            productoNew = ProductoFrio.crearProducto(productoId);
        } else if (producto instanceof ProductoCaliente) {
            productoNew = ProductoCaliente.crearProducto(productoId);
        } else {
            productoNew = Producto.crearProducto(productoId);
        }
            Cocinero cocinero = this.panaderia.cocineroAleatorio();
            boolean fallado = cocinero.procesoCocinar(productoNew);
            if (fallado){
            cocinero.detenerCoccion(producto);
            return false;
            }
        this.panaderia.agregarProducto(productoNew);
        }
        
     // Resta los ingredientes usados del inventario de la Panadería.
        for (Map.Entry<String, Integer> product : productos.entrySet()) {
            String productoId = product.getKey();
            Producto producto = this.panaderia.buscarProductoPorId(productoId);
            Cocinero cocinero = this.panaderia.cocineroAleatorio();
            cocinero.detenerCoccion(producto);
        }
        // Si todos los productos se cocinan con éxito, retorna verdadero.

        return true;
    }
  
    //Este método se ejecuta cuando se llama el método comprarIngredientes de Panadería, basicamnete se encarga de comprar ingedientes cuando falta y añadirlos al inventario
    //Recibe una diccionario de Strings y enteros paea saber exactamente qué se debe comprar y cuánta cantidad
    //Cuando se "compran" nuevos ingredientes realmente se están creando objetos y añandiendose al inventario

  
}

// use hasmaps y sale facil get datos

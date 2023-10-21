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
import UIMain.GestionCocinar;

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

    public Cocinero(String nombre, double habilidad,double calificacion, double dineroEnMano, String especialidad, Panaderia panaderia) {
        super(nombre, habilidad, calificacion,dineroEnMano, panaderia);
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
     * Calcula la cantidad de ingredientes faltantes para preparar un plato, revisando la cantidad disponible en la panadería.
     * @param ingredientesNecesarios un mapa que contiene los ingredientes necesarios y la cantidad requerida de cada uno.
     * @return un mapa que contiene los ingredientes necesarios y la cantidad faltante de cada uno.
     */
    public Map<String, Integer> ingredientesCocinero (Map<String, Integer> ingredientesNecesarios) {
        // Revisa la cantidad de ingredientes disponibles en la panadería y calcula los faltantes.
        Map<String, Integer> ingrFaltantes = this.panaderia.getInventario().revisarCantidadIngredientes(ingredientesNecesarios);
        // Retorna un mapa que contiene los ingredientes necesarios y la cantidad faltante de cada uno.
        return ingrFaltantes;
    }
    


    /**
     * Busca el cocinero ideal para un proceso de cocina dado. Si encuentra un cocinero en la lista de cocineros de la panadería con la especialidad deseada, lo devuelve. De lo contrario, contrata a un nuevo cocinero con la especialidad deseada y lo devuelve.
     * @param proceso el proceso de cocina para el que se busca un cocinero ideal
     * @return el cocinero ideal para el proceso de cocina dado
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

    /**
     * Detiene la cocción del producto especificado y resta los ingredientes usados(desperdiciados) del inventario de la panadería.
     * @param producto El producto cuya cocción se detendrá y cuyos ingredientes se restarán del inventario.
     */
    public void detenerCoccion(Producto producto){
        Map<String,Integer> ingredientesUsados = producto.getIngredientes();
            for (Map.Entry<String, Integer> usados : ingredientesUsados.entrySet()){
                String ingUsado = usados.getKey();
                Integer cantidad = usados.getValue();
                this.panaderia.getInventario().restarIngrediente(ingUsado,cantidad);
            }
    }

    /**
     * Realiza el proceso de cocinar un producto, siguiendo los pasos necesarios para su preparación.
     * 
     * @param producto El producto a cocinar.
     * @return false si el producto se cocinó con éxito, true en caso contrario.
     */
    public boolean procesoCocinar(Producto producto) {
        // Obtiene la lista de procesos de cocina necesarios para el producto.
        ArrayList<String> procesosProducto = producto.seleccionProcesosDeCocina();
        
        // Asigna la lista de procesos al producto.
        producto.setProcesoDeCocina(procesosProducto);
        
        // Crea una instancia de la clase Catastrofe para gestionar la dificultad.
        Catastrofe dificultad = new Catastrofe();
        
        // Obtiene la lista de procesos a realizar.
        List<String> procesoCook = producto.getProcesoDeCocina();
        int longitud = procesoCook.size();
        
        // Itera a través de los procesos de cocina.
        for (String proceso : procesoCook) {
            // Encuentra al cocinero ideal para el proceso actual.
            Cocinero chefIdeal = cocineroIdeal(proceso);
            
            // Realiza el proceso específico para productos fríos, si corresponde.
            if (producto instanceof ProductoFrio) {
                ProductoFrio productoF = (ProductoFrio) producto;
                productoF.procesoCongelamiento(chefIdeal);
            }
            
            // Realiza el proceso específico para productos calientes, si corresponde.
            if (producto instanceof ProductoCaliente) {
                ProductoCaliente productoH = (ProductoCaliente) producto;
                productoH.procesoHornear(chefIdeal);
            }
            
            // Evalúa la dificultad del proceso con el cocinero seleccionado.
            boolean cookProducto = dificultad.dificultadProducto(chefIdeal);
            
            if (cookProducto) {
                // Incrementa la habilidad del cocinero si el proceso falló.
                chefIdeal.setHabilidad(chefIdeal.getHabilidad() + 1);
                GestionCocinar.fallosCocinando(procesoCook,longitud);
                return true;
            }
            GestionCocinar.barrasCocinando(procesoCook,longitud);
            // Establece el cocinero como ocupado.
            chefIdeal.setTrabajo(true);
        }
        
        // Devuelve false si todos los procesos se completaron con éxito.
        return false;
    }



    /**
     * Método que une varios mapas de ingredientes en uno solo, acumulando las cantidades de los ingredientes que se repiten.
     * @param listaDeMapas Lista de mapas de ingredientes a unir.
     * @return Mapa acumulativo con los ingredientes y sus cantidades totales.
     */
    public Map<String, Integer> unirMapasIngredientesId(List<Map<String, Integer>> listaDeMapas){
        Map<String, Integer> mapaAcumulativo = new HashMap<>();
        for (Map<String, Integer> mapa : listaDeMapas) {
            mapa.forEach((clave, valor) -> mapaAcumulativo.merge(clave, valor, Integer::sum));
        }
        return mapaAcumulativo;
    }

    /**
     * Multiplica los valores de un mapa por un multiplicador dado y devuelve un nuevo mapa con los valores multiplicados.
     * @param mapa El mapa original con los valores a multiplicar.
     * @param multiplicador El valor por el cual se multiplicarán los valores del mapa.
     * @return Un nuevo mapa con los valores multiplicados.
     */
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
     * @param canastaTrabajar El objeto Canasta que contiene los productos a procesar.
     * @return true si la labor se realiza con éxito; false si no se puede realizar debido a la falta de ingredientes o problemas de cocción.
     */
    @Override
    public boolean laborParticular(Canasta canastaTrabajar) {
        // Obtiene los productos junto con sus cantidades de la canasta
        HashMap<String, Integer> productos = canastaTrabajar.getProductosEnLista();
        List<Map<String, Integer>> listaDeMapas = new ArrayList<>();
        
        // Preparación de ingredientes necesarios
        for (Map.Entry<String, Integer> product : productos.entrySet()) {
            String productoID = product.getKey();
            Producto producto = this.panaderia.getInventario().buscarProductoPorId(productoID);
            Integer cantidad = product.getValue();
            Map<String,Integer> ingredientesNecesarios = producto.getIngredientes();
            Map<String,Integer> ingredientesAbsolutos = multiplicarValoresEnMapa(ingredientesNecesarios,cantidad);
            listaDeMapas.add(ingredientesAbsolutos);
        }
        
        // Unión de ingredientes necesarios
        Map<String, Integer> listaIngredientesTotales = unirMapasIngredientesId(listaDeMapas);
        
        // Comprobación de ingredientes faltantes
        Map<String, Integer> ingrFaltantes = ingredientesCocinero(listaIngredientesTotales);
        
        if (!ingrFaltantes.isEmpty()) {
            GestionCocinar.fallosCocinando();
            // Compra los ingredientes faltantes en la Panadería.
            this.panaderia.comprarIngredientes(ingrFaltantes);
            // Retorna falso, indicando que no se puede realizar la labor debido a la falta de ingredientes.
            return false;
        }
        
        // Verificar caducidad de ingredientes
        for (Map.Entry<String, Integer> product : productos.entrySet()) {
            String productoId = product.getKey();
            Producto producto = this.panaderia.getInventario().buscarProductoPorId(productoId);
            Map<String,Integer> ingredientesAUsar = producto.getIngredientes();
            for (Map.Entry<String, Integer> verificar : ingredientesAUsar.entrySet()){
                String ingIdVerificar = verificar.getKey();
                Ingrediente ingVerificar = this.panaderia.getInventario().buscarIngredientePorId(ingIdVerificar);
                Integer cantidad = verificar.getValue();
                ingVerificar.revisarCaducidad(cantidad);
            }
        }

        // Itera a través de los productos en la canasta nuevamente.
        for (Map.Entry<String, Integer> product : productos.entrySet()) {
            String productoId = product.getKey();
            Producto producto = this.panaderia.getInventario().buscarProductoPorId(productoId);
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
            this.panaderia.getInventario().agregarProducto(productoNew);
        }
        
        // Resta los ingredientes usados del inventario de la Panadería.
        for (Map.Entry<String, Integer> product : productos.entrySet()) {
            String productoId = product.getKey();
            Producto producto = this.panaderia.getInventario().buscarProductoPorId(productoId);
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

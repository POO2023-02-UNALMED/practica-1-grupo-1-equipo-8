package gestorAplicacion.gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;

public class Inventario implements Serializable{
	
	private Panaderia panaderia;
	private ArrayList<Ingrediente> invIngredientes= new ArrayList<Ingrediente>();
    private ArrayList<Producto> invProductos = new ArrayList<Producto>();
    private final int maxCantidad = 20;
    
    //Métodos Get y Set
	
    public Panaderia getPanaderia() {
		return panaderia;
	}
    
	public void setPanaderia(Panaderia panaderia) {
		this.panaderia = panaderia;
	}
	
	public ArrayList<Ingrediente> getInvIngredientes() {
		return invIngredientes;
	}
	
	public void setInvIngredientes(ArrayList<Ingrediente> invIngredientes) {
		this.invIngredientes = invIngredientes;
	}
	
	public ArrayList<Producto> getInvProductos() {
		return invProductos;
	}
	
	public void setInvProductos(ArrayList<Producto> invProductos) {
		this.invProductos = invProductos;
	}
    
	 public int getMaxCantidad() {
		return maxCantidad;
	}

	/**
     * Verifica la cantidad de un producto en el inventario de la panadería por su ID.
     * @param prdct el ID del producto a verificar.
     * @return la cantidad de productos con el ID especificado en el inventario.
     */
    public int verificarCantidadProductoPorId(String prdct) {
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
    public int verificarCantidadIngredientePorId(String ingrd) {
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
    public Producto buscarProductoPorId(String id) {
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
    public Ingrediente buscarIngredientePorId(String id) {
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
    public void agregarIngrediente(Ingrediente ingrediente) {
        invIngredientes.add(ingrediente);
    }

    /**
     * Agrega un producto a la lista de productos disponibles en la panadería.
     * @param producto el producto a agregar.
     */
    public void agregarProducto(Producto producto) {
        invProductos.add(producto);
    }

    /**
     * Resta la cantidad especificada de un ingrediente del inventario de la panadería.
     * @param ingrediente el id del ingrediente a restar.
     * @param cantidad la cantidad de ingredientes a restar.
     */
    public void restarIngrediente(String ingrediente, int cantidad) {
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
    public  void restarIngrediente(Ingrediente ingrediente, int cantidad) {
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
    public void restarProducto(String producto, int cantidad) {
        if(verificarCantidadProductoPorId(producto) >= cantidad) {
            for(int i = 0; i < cantidad; i++){
                for(Producto producto1 : this.invProductos) {
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
    public void restarProducto(Producto producto, int cantidad) {
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
    * Revisa si hay suficientes ingredientes en la panadería para hacer un producto.
    * @param ingredientesNecesarios un mapa que contiene los ingredientes necesarios y su cantidad requerida.
    * @return un mapa que contiene los ingredientes faltantes y la cantidad necesaria.
    */
    
    public Map<String, Integer> revisarCantidadIngredientes(Map<String, Integer> ingredientesNecesarios){
        HashMap<String, Integer> ingredientessFaltantes = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : ingredientesNecesarios.entrySet()) {
            String ingredienteId = entry.getKey();
            Integer cantidad = entry.getValue();
            int cantidadExistente = verificarCantidadIngredientePorId(ingredienteId);
            if (cantidadExistente-cantidad<0){
                ingredientessFaltantes.put(ingredienteId,(cantidadExistente-cantidad)*(-2));
            }
        }
        return ingredientessFaltantes;
    }
}
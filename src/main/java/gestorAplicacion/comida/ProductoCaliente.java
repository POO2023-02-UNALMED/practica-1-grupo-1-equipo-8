package gestorAplicacion.comida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class ProductoCaliente extends Producto{
	private int tiempoEnHorno;

  public ProductoCaliente(String nombre, HashMap<String,Integer> ingredientes, double costo, String sabor, ArrayList<String> procesoDeCocina,int tiempoEnHorno) {
    super(nombre, ingredientes, costo, sabor, procesoDeCocina);
		this.tiempoEnHorno = tiempoEnHorno;
}
public ProductoCaliente(String nombre, HashMap<String,Integer> ingredientes,int tiempoEnHorno) {
    super(nombre, ingredientes);
		this.tiempoEnHorno = tiempoEnHorno;
    }

  public static Producto crearProducto(String Nnombre) {
    Producto newProducto = obtenerObjetoPorNombre(Nnombre);
    return new Producto(newProducto.getNombre(), newProducto.getIngredientes(), newProducto.getCosto(),
        newProducto.getSabor(), newProducto.getProcesoDeCocina());
  }
  
  public static Producto crearProductoPersonalizado(String Nnombre, HashMap<String, Integer> ingredientes) {
    for (HashMap.Entry<String, Integer> entry : ingredientes.entrySet()) {
      if (!Ingrediente.verificacionExistenciaPorNombre(entry.getKey())) {
        new Ingrediente(entry.getKey());
      }
    }
    return new Producto(Nnombre, ingredientes);
  }
@Override

  public ArrayList<String> seleccionProcesosDeCocina (){
	ArrayList <String> procesos = new ArrayList<String>();
	procesos.add("Hornear");
	procesos.add("Amasar");
	procesos.add("Mezclar");
	procesos.add("Fritar");
	procesos.add("Asar");
	procesos.add("Decoracion");
	Random numAleatorio = new Random();
  	int cuantosProcesos = numAleatorio.nextInt(3)+1;
			ArrayList <Integer> numerosIndices = new ArrayList<Integer>();
	for(int i=0;i<cuantosProcesos;i++){
		Random random = new Random();
  	int numerosDelProceso = random.nextInt(6);
		numerosIndices.add(numerosDelProceso);
	}
	ArrayList <String> procesosFinales = new ArrayList<String>();
	for (int numero : numerosIndices){
		String procesoRandom = procesos.get(numero);
		procesosFinales.add(procesoRandom);
	}
	return procesosFinales;
	}
}
package gestorAplicacion.comida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import gestorAplicacion.humanos.Cocinero;

public class ProductoCaliente extends Producto{
	private boolean horno=false;
	private int tiempoEnHorno;

	public ProductoCaliente(String nombre, String ids, HashMap<String,Integer> ingredientes, double costo, int vecesVendido, int tiempoEnHorno) {
		super(nombre, ids, ingredientes, costo, vecesVendido);
		this.tiempoEnHorno = tiempoEnHorno;
}
public ProductoCaliente(String nombre, HashMap<String,Integer> ingredientes,int tiempoEnHorno) {
    super(nombre, ingredientes);
		this.tiempoEnHorno = calcularHorneado();
    }

public boolean isHorno() {
	return horno;
}

public void setHorno(boolean horno) {
	this.horno = horno;
}

	public int getTiempoEnHorno() {
		return tiempoEnHorno;
	}

	public void setTiempoEnHorno(int tiempoEnHorno) {
		this.tiempoEnHorno = tiempoEnHorno;
	}

	public static ProductoCaliente crearProducto(String Nnombre) {
		ProductoCaliente newProducto = (ProductoCaliente) obtenerObjetoPorId(Nnombre);
		return new ProductoCaliente(newProducto.getNombre(), newProducto.getId(), newProducto.getIngredientes(), newProducto.getCosto(), newProducto.getVecesVendido(), newProducto.getTiempoEnHorno());
	}

	public static Producto crearProductoPersonalizado(String Nnombre, HashMap<String, Integer> ingredientes) {
    for (HashMap.Entry<String, Integer> entry : ingredientes.entrySet()) {
    if (!Ingrediente.verificacionExistenciaPorNombre(entry.getKey())) {
        new Ingrediente(entry.getKey());
      }
    }
    return new Producto(Nnombre, ingredientes);
  }

	public int calcularHorneado(){
		Random numAleatorio = new Random();
		int tiempoDeHornear = numAleatorio.nextInt(20);;

    return tiempoDeHornear;
	}

	public Cocinero procesoHornear(Cocinero cocinero){
		if (cocinero.isHorno() == true) {
			return cocinero;
		} else {
			cocinero.setHorno(true);
			return cocinero;
		}
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
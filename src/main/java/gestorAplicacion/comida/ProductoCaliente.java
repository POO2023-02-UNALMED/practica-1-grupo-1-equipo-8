import java.util.ArrayList;
import java.util.Random;

class ProductoCaliente extends Producto{

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
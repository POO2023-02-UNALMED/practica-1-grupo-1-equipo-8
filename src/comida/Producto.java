package comida;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Producto {
	private String nombre;
	private Map<Ingrediente,Integer> ingredientes = new HashMap<Ingrediente,Integer>();
  private float costo;

	//Constructores
	public Producto(String nombre,Map<Ingrediente,Integer> ingredientes, float costo) {
		this.nombre = nombre;
		this.ingredientes=ingredientes;
		this.costo=costo;
	}
	public Producto(String nombre,Map<Ingrediente,Integer> ingredientes){
		this.nombre=nombre;
		this.ingredientes=ingredientes;
	}

	public float getCosto(){
		return costo;
	}
	public Map<Ingrediente, Integer> getIngredientes() {return ingredientes;}
}
//Nota para la persona que trabaje esta clase
//Creo que es mejor trabajar los ingredientes del producto como diccionario para acceder a ellos mejor desde la clase canasta y así eliminar el atributo cantidad de la clase ingredientes, si hay algun cambio, me avisan para hacer el cambio en Canasta
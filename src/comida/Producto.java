package comida;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Producto {
	public final Map<Ingrediente,Integer> ingredientes = new HashMap<Ingrediente,Integer>();
    
}
//Nota para la persona que trabaje esta clase
//Creo que es mejor trabajar los ingredientes del producto como diccionario para acceder a ellos mejor desde la clase canasta y así eliminar el atributo cantidad de la clase ingredientes, si hay algun cambio, me avisan para hacer el cambio en Canasta
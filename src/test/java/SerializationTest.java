import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.humanos.Cliente;
import gestorAplicacion.gestion.Canasta;
import baseDatos.Serializador;

public class SerializationTest {
  public static void main(String[] args) {
    Panaderia panaderia=null;
    panaderia = Serializador.cargarPanaderia();

    for(Ingrediente ingrediente : Panaderia.getInvIngredientes().keySet()){
      System.out.println(ingrediente.getNombre());
      System.out.println(Panaderia.getInvIngredientes().get(ingrediente));
      System.out.println(ingrediente.getId());
      System.out.println(ingrediente.getCantidadIngredientes());
    }

    
    Cliente cliente1 = new Cliente("Cliente 1", 1, 56.0f);
    Canasta canasta1 = new Canasta();
    Panaderia.getClientes().add(cliente1);
    Panaderia.getClientes().get(0).getCanastas().add(canasta1);

    for(Ingrediente ingrediente : Panaderia.getInvIngredientes().keySet()){
      System.out.println(ingrediente.getNombre());
      System.out.println(Panaderia.getInvIngredientes().get(ingrediente));
      System.out.println(ingrediente.getId());
      System.out.println(ingrediente.getCantidadIngredientes());
    }
    Serializador.guardarPanaderia(panaderia);
  }
}
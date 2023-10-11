import gestorAplicacion.gestion.Panaderia;
import baseDatos.Serializador;


public class SerializationTest {
  public static void main(String[] args) {
    Panaderia panaderia = Serializador.cargarPanaderia();

    // Do something with panaderia

    
    Serializador.guardarPanaderia(panaderia);
  }
}

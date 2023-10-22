package UIMain;

import java.util.ArrayList;
import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.humanos.Domiciliario;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.humanos.Cocinero;

public class GestionRankings {
  public static void mostrarRankingDomiciliarios(Panaderia panaderia){
    ArrayList<Domiciliario> domiciliarios = panaderia.getDomiciliarios();
    for (Domiciliario domiciliario : domiciliarios){
      System.out.println(domiciliario.getNombre() + " " + domiciliario.getCalificacion());
    }
  }

  public static void mostrarRankingCocineros(Panaderia panaderia){
    ArrayList<Cocinero> cocineros = panaderia.getCocineros();
    for (Cocinero cocinero : cocineros){
      System.out.println(cocinero.getNombre() + " " + cocinero.getCalificacion());
    }
  }

  public static void mostrarRankingProductos(){
    ArrayList<Producto> productos = Producto.getTopMasVendidos();
    for (Producto producto : productos){
      System.out.println(producto.getNombre() + " " + producto.getVecesVendido());
    }
  }

  public static void mostrarRankingIngredientes(){
    ArrayList<Ingrediente> ingredientes = Ingrediente.getTopMasVendidos();
    for (Ingrediente ingrediente : ingredientes){
      System.out.println(ingrediente.getNombre() + " " + ingrediente.getVecesVendido());
    }
  }

  public static void mostrarRankingCanastas(Panaderia panaderia){
    ArrayList<Canasta> canastas = panaderia.getCanastasPublicadas();
    for (Canasta canasta : canastas){
      System.out.println(canasta.getIdentificador());
      GestionCompra.mostrarCanasta(canasta);
    }
  }
}

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
    System.out.println(Texto.centrar("MEJORES DOMICILIARIOS"));
    System.out.println("_".repeat(55));
    for (Domiciliario domiciliario : domiciliarios){
      System.out.println(domiciliario.getNombre() + " " + domiciliario.getCalificacion());
    }
    System.out.println("_".repeat(55));
  }

  public static void mostrarRankingCocineros(Panaderia panaderia){
    ArrayList<Cocinero> cocineros = panaderia.getCocineros();
    System.out.println(Texto.centrar("MEJORES COCINEROS"));
    System.out.println("_".repeat(55));
    for (Cocinero cocinero : cocineros){
      System.out.println(cocinero.getNombre() + " " + cocinero.getCalificacion());
    }
    System.out.println("_".repeat(55));
  }

  public static void mostrarRankingProductos(){
    ArrayList<Producto> productos = Producto.getTopMasVendidos();
    System.out.println(Texto.centrar("PRODUCTOS MAS VENDIDOS"));
    System.out.println("_".repeat(55));
    System.out.println(Texto.alinear("PRODUCTO"," ", "VECES VENDIDO"));
    System.out.println("_".repeat(55));
    for (Producto producto : productos){
      System.out.println(Texto.alinear(producto.getNombre()," ",String.valueOf(producto.getVecesVendido())));
    }
    System.out.println("_".repeat(55));
  }

  public static void mostrarRankingIngredientes(){
    ArrayList<Ingrediente> ingredientes = Ingrediente.getTopMasVendidos();
    System.out.println(Texto.centrar("INGREDIENTES MAS VENDIDOS"));
    System.out.println("_".repeat(55));
    System.out.println(Texto.alinear("INGREDIENTE"," ", "VECES VENDIDO"));
    System.out.println("_".repeat(55));
    for (Ingrediente ingrediente : ingredientes){
      System.out.println(ingrediente.getNombre() + " " + ingrediente.getVecesVendido());
    }
    System.out.println("_".repeat(55));
  }

  public static void mostrarRankingCanastas(Panaderia panaderia){
    System.out.println(Texto.centrar("CANASTAS RECOMENDADAS"));
    System.out.println("_".repeat(55));
    ArrayList<Canasta> canastas = panaderia.getCanastasPublicadas();
    for (Canasta canasta : canastas){
      System.out.println("Canasta "+canasta.getIdentificador());
      GestionCompra.mostrarCanasta(canasta);
    }
  }
}

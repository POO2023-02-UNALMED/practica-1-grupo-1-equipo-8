import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.humanos.Cliente;

class CanastaTest {

  private Panaderia panaderia;
  private Canasta canasta;
  private Producto producto1;
  private Producto producto2;
  private Ingrediente ingrediente1;
  private Ingrediente ingrediente2;
  private String kit1;
  private String kit2;
  private ArrayList<Object> listaIngredienteCantidad1;
  private ArrayList<Object> listaIngredienteCantidad2;

  @BeforeEach
  void setUp() throws Exception {
    panaderia = new Panaderia();
    canasta = new Canasta();
    Cliente cliente = new Cliente("Cliente 1", 1, 56.0f);
    panaderia.getClientes().add(cliente);
    Panaderia.getClientes().get(0).getCanastas().add(canasta);
    ingrediente1 = new Ingrediente("Ingrediente 1");
    ingrediente2 = new Ingrediente("Ingrediente 2");
    Map<Ingrediente, Integer> ingredientes1 = new HashMap<Ingrediente, Integer>();
    ingredientes1.put(ingrediente1, 2);
    ingredientes1.put(ingrediente2, 4);
    producto1 = new Producto("Producto 1", ingredientes1);
    producto2 = new Producto("Producto 2", ingredientes1);
    System.out.println(producto1.getId());
    kit1 = "Kit 1";
    kit2 = "Kit 2";
    Panaderia.getInvIngredientes().put(ingrediente1, 10);
    Panaderia.getInvIngredientes().put(ingrediente2, 10);
    Panaderia.getInvProductos().put(producto1, 10);
    Panaderia.getInvProductos().put(producto2, 10);
    listaIngredienteCantidad1 = new ArrayList<Object>();
    listaIngredienteCantidad1.add(ingredientes1);
    listaIngredienteCantidad1.add(1);
    listaIngredienteCantidad2 = new ArrayList<Object>();
    listaIngredienteCantidad2.add(ingredientes1);
    listaIngredienteCantidad2.add(3);
  }

  @Test
  void testAgregarProducto() {
    canasta.agregarProducto(producto1, 2);
    canasta.agregarProducto(producto2, 1);
    Map<Producto, Integer> productosEsperados = new HashMap<Producto, Integer>();
    productosEsperados.put(producto1, 2);
    productosEsperados.put(producto2, 1);
    assertEquals(productosEsperados, canasta.getProductos());
  }

  @Test
  void testAgregarIngrediente() {
    canasta.agregarIngrediente(ingrediente1, 3);
    canasta.agregarIngrediente(ingrediente2, 1);
    Map<Ingrediente, Integer> ingredientesEsperados = new HashMap<Ingrediente, Integer>();
    ingredientesEsperados.put(ingrediente1, 3);
    ingredientesEsperados.put(ingrediente2, 1);
    assertEquals(ingredientesEsperados, canasta.getIngredientes());
  }

  @Test
  void testAgregarKit() {
    canasta.agregarKit(kit1, listaIngredienteCantidad1);
    canasta.agregarKit(kit2, listaIngredienteCantidad2);
    Map<String, ArrayList<Object>> kitsEsperados = new HashMap<String, ArrayList<Object>>();
    kitsEsperados.put(kit1, listaIngredienteCantidad1);
    kitsEsperados.put(kit2, listaIngredienteCantidad2);
    assertEquals(kitsEsperados, canasta.getKits());
  }

  @Test
  void testAgregarProductoEnLista() {
    canasta.agregarProducto("3", 2);
    canasta.agregarProducto("4", 1);
    Map<String, Integer> productosEnListaEsperados = new HashMap<String, Integer>();
    productosEnListaEsperados.put("3", 2);
    productosEnListaEsperados.put("4", 1);
    assertEquals(productosEnListaEsperados, canasta.getProductosEnLista());
  }

  @Test
  void testAgregarIngredienteEnLista() {
    canasta.agregarIngrediente("1", 3);
    canasta.agregarIngrediente("2", 1);
    Map<String, Integer> ingredientesEnListaEsperados = new HashMap<String, Integer>();
    ingredientesEnListaEsperados.put("1", 3);
    ingredientesEnListaEsperados.put("2", 1);
    assertEquals(ingredientesEnListaEsperados, canasta.getIngredientesEnLista());
  }

  @Test
  void testAgregarKitEnLista() {
    canasta.agregarKit("3", 2);
    canasta.agregarKit("4", 1);
    Map<String, Integer> kitsEnListaEsperados = new HashMap<String, Integer>();
    kitsEnListaEsperados.put("3", 2);
    kitsEnListaEsperados.put("4", 1);
    assertEquals(kitsEnListaEsperados, canasta.getKitsEnLista());
  }

  @Test
  void testEliminarProducto() {
    canasta.agregarProducto(producto1, 2);
    canasta.agregarProducto(producto2, 1);
    assertTrue(canasta.eliminarProducto(producto1, -1));
    Map<Producto, Integer> productosEsperados = new HashMap<Producto, Integer>();
    productosEsperados.put(producto1, 1);
    productosEsperados.put(producto2, 1);
    assertEquals(productosEsperados, canasta.getProductos());
  }

  @Test
  void testEliminarIngrediente() {
    canasta.agregarIngrediente(ingrediente1, 3);
    canasta.agregarIngrediente(ingrediente2, 1);
    assertTrue(canasta.eliminarIngrediente(ingrediente1, -3));
    Map<Ingrediente, Integer> ingredientesEsperados = new HashMap<Ingrediente, Integer>();
    ingredientesEsperados.put(ingrediente2, 1);
    assertEquals(ingredientesEsperados, canasta.getIngredientes());
  }

  @Test
  void testEliminarKit() {
    canasta.agregarKit(kit1, listaIngredienteCantidad1);
    canasta.agregarKit(kit2, listaIngredienteCantidad2);
    assertTrue(canasta.eliminarKit(kit1,-1));
    Map<String, ArrayList<Object>> kitsEsperados = new HashMap<String, ArrayList<Object>>();
    kitsEsperados.put(kit2, listaIngredienteCantidad2);
    assertEquals(kitsEsperados, canasta.getKits());
  }

}

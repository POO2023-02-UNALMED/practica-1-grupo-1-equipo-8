package test;

import org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import gestorAplicación.gestion.Canasta;
import gestorAplicación.comida.Ingrediente;
import gestorAplicación.comida.Producto;



public class CanastaTest {

  @Test
  public void testAgregarProducto() {
    Canasta canasta = new Canasta();
    Producto producto = new Producto("Leche");
    canasta.agregarProducto(producto, 2);
    assertEquals(2, canasta.getProductos().get(producto));
  }

  @Test
  public void testAgregarIngrediente() {
    Canasta canasta = new Canasta();
    Ingrediente ingrediente = new Ingrediente("Tomate");
    canasta.agregarIngrediente(ingrediente, 3);
    assertEquals(3, canasta.getIngredientes().get(ingrediente));
  }

  @Test
  public void testAgregarKit() {
    Canasta canasta = new Canasta();
    ArrayList<Object> listaIngredienteCantidad = new ArrayList<Object>();
    listaIngredienteCantidad.add(new Ingrediente("Tomate"));
    listaIngredienteCantidad.add(3);
    canasta.agregarKit("Ensalada", listaIngredienteCantidad);
    assertEquals(1, canasta.getKits().size());
  }

  @Test
  public void testAgregarProductoEnLista() {
    Canasta canasta = new Canasta();
    canasta.agregarProducto("Leche", 2);
    assertEquals(2, canasta.getProductosEnLista().get("Leche"));
  }

  @Test
  public void testGenerarCosto() {
    Canasta canasta = new Canasta();
    Producto producto1 = new Producto("Leche", 2000);
    Producto producto2 = new Producto("Pan", 1000);
    canasta.agregarProducto(producto1, 2);
    canasta.agregarProducto(producto2, 3);
    assertEquals(7000.0, canasta.getCosto(), 0.0);
  }

  @Test
  public void testGenerarCostoEnLista() {
    Canasta canasta = new Canasta();
    canasta.agregarProducto("Leche", 2);
    canasta.agregarProducto("Pan", 3);
    assertEquals(6000.0, canasta.getCostoTotalEnLista(), 0.0);
  }
}
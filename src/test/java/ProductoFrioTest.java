import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;

import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.comida.ProductoFrio;

public class ProductoFrioTest {
  private ProductoFrio productoFrio;
  private String nombre;
  private HashMap<String, Integer> ingredientes;
  private double costo;
  private String sabor;
  private ArrayList<String> procesoDeCocina;
  private double tipoDeEnvase;
  private int tiempoDeVida;

  @BeforeEach
  public void setUp() {
    nombre = "Helado";
    ingredientes = new HashMap<String, Integer>();
    ingredientes.put("Leche", 2);
    ingredientes.put("Azúcar", 1);
    tipoDeEnvase = 0.5;
    tiempoDeVida = 30;
    ProductoFrio.crearProductoPersonalizado(nombre, ingredientes);
  }

  @Test
  public void testConstructor() {
    assertNotNull(productoFrio);
    assertEquals(nombre, productoFrio.getNombre());
    assertEquals(ingredientes, productoFrio.getIngredientes());
    assertEquals(costo, productoFrio.getCosto(), 0.0);
    assertEquals(sabor, productoFrio.getSabor());
    assertEquals(procesoDeCocina, productoFrio.getProcesoDeCocina());
    assertEquals(tipoDeEnvase, productoFrio.getTipoDeEnvase(), 0.0);
    assertEquals(tiempoDeVida, productoFrio.getTiempoDeVida());
  }

  @Test
  public void testCrearProducto() {
    Producto producto = ProductoFrio.crearProducto(nombre);
    assertNotNull(producto);
    assertEquals(nombre, producto.getNombre());
    assertEquals(ingredientes, producto.getIngredientes());
    assertEquals(costo, producto.getCosto(), 0.0);
    assertEquals(sabor, producto.getSabor());
    assertEquals(procesoDeCocina, producto.getProcesoDeCocina());
  }

  @Test
  public void testCrearProductoPersonalizado() {
    HashMap<String, Integer> ingredientesPersonalizados = new HashMap<String, Integer>();
    ingredientesPersonalizados.put("Leche", 2);
    ingredientesPersonalizados.put("Azúcar", 1);
    ingredientesPersonalizados.put("Vainilla", 1);
    Producto productoPersonalizado = ProductoFrio.crearProductoPersonalizado("Helado de Vainilla", ingredientesPersonalizados);
    assertNotNull(productoPersonalizado);
    assertEquals("Helado de Vainilla", productoPersonalizado.getNombre());
    assertEquals(ingredientesPersonalizados, productoPersonalizado.getIngredientes());
  }

  @Test
  public void testSeleccionProcesosDeCocina() {
    ArrayList<String> procesos = productoFrio.seleccionProcesosDeCocina();
    assertNotNull(procesos);
    assertEquals(1, procesos.size());
  }
}
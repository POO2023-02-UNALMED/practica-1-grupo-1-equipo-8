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
import gestorAplicacion.humanos.Cocinero;
import gestorAplicacion.humanos.Domiciliario;
import gestorAplicacion.humanos.Trabajador;

class PanaderiaTest {

  private Panaderia panaderia;
  private Producto producto1;
  private Producto producto2;
  private Ingrediente ingrediente1;
  private Ingrediente ingrediente2;
  private Cocinero cocinero1;
  private Cocinero cocinero2;
  private Domiciliario domiciliario1;
  private Domiciliario domiciliario2;
  private Cliente cliente1;
  private Cliente cliente2;

  @BeforeEach
  void setUp() throws Exception {
    panaderia = new Panaderia();
    ingrediente1 = new Ingrediente("Ingrediente 1");
    ingrediente2 = new Ingrediente("Ingrediente 2");
    Map<Ingrediente, Integer> ingredientes1 = new HashMap<Ingrediente, Integer>();
    ingredientes1.put(ingrediente1, 2);
    ingredientes1.put(ingrediente2, 4);
    producto1 = new Producto("Producto 1", ingredientes1);
    producto2 = new Producto("Producto 2", ingredientes1);
    cocinero1 = new Cocinero("Cocinero 1", 1, 56.0f, "Especialidad 1");
    cocinero2 = new Cocinero("Cocinero 2", 1, 56.0f, "Especialidad 2");
    domiciliario1 = new Domiciliario();
    domiciliario2 = new Domiciliario();
    cliente1 = new Cliente("Cliente 1", 1, 56.0f);
    cliente2 = new Cliente("Cliente 2", 1, 56.0f);
    Panaderia.getInvIngredientes().put(ingrediente1, 10);
    Panaderia.getInvIngredientes().put(ingrediente2, 10);
    Panaderia.getInvProductos().put(producto1, 10);
    Panaderia.getInvProductos().put(producto2, 10);
    Panaderia.agregarTrabajador(cocinero1);
    Panaderia.agregarTrabajador(cocinero2);
    Panaderia.agregarDinero(1000);
  }

  @Test
  void testAgregarTrabajador() {
    assertEquals(2, Panaderia.getTrabajadores().size());
    Cocinero cocinero3 = new Cocinero("Cocinero 3", 1, 56.0f, "Especialidad 3");
    Panaderia.agregarTrabajador(cocinero3);
    assertEquals(3, Panaderia.getTrabajadores().size());
  }

  @Test
  void testAgregarCliente() {
    assertEquals(0, Panaderia.getClientes().size());
    Cliente cliente3 = new Cliente("Cliente 3", 1, 56.0f);
    Panaderia.agregarCliente(cliente3);
    assertEquals(1, Panaderia.getClientes().size());
  }

  @Test
  void testEliminarCocinero() {
    assertEquals(2, Panaderia.getTrabajadores().size());
    Panaderia.eliminarCocinero(cocinero1);
    assertEquals(1, Panaderia.getTrabajadores().size());
  }

  @Test
  void testEliminarCliente() {
    assertEquals(0, Panaderia.getClientes().size());
    Cliente cliente3 = new Cliente("Cliente 3", 1, 56.0f);
    Panaderia.agregarCliente(cliente3);
    assertEquals(1, Panaderia.getClientes().size());
    Panaderia.eliminarCliente(cliente3);
    assertEquals(0, Panaderia.getClientes().size());
  }

  @Test
  void testAgregarDinero() {
    assertEquals(1000, Panaderia.getDinero());
    Panaderia.agregarDinero(500);
    assertEquals(1500, Panaderia.getDinero());
  }

  @Test
  void testRestarDinero() {
    assertEquals(1000, Panaderia.getDinero());
    Panaderia.restarDinero(500);
    assertEquals(500, Panaderia.getDinero());
  }

  @Test
  void testContratarCocinero() {
    assertEquals(2, Panaderia.getCocineros().size());
    Cocinero cocinero3 = Panaderia.contratarCocinero("Cocinero 3", 1, 56.0f, "Especialidad 3");
    assertEquals(3, Panaderia.getCocineros().size());
    assertTrue(Panaderia.getTrabajadores().contains(cocinero3));
  }

  @Test
  void testSaldarDeudas() {
    assertFalse(Panaderia.isEnQuiebra());
    Panaderia.setValorDeudas(500);
    Panaderia.saldarDeudas();
    assertEquals(500, Panaderia.getDinero());
    assertEquals(0, Panaderia.getValorDeudas());
    assertFalse(Panaderia.isEnQuiebra());
    Panaderia.setValorDeudas(1500);
    Panaderia.saldarDeudas();
    assertEquals(10000000, Panaderia.getDinero());
    assertEquals(0, Panaderia.getValorDeudas());
    assertTrue(Panaderia.isEnQuiebra());
  }

  @Test
  void testConseguirPrestamo() {
    assertEquals(0, Panaderia.getValorDeudas());
    Panaderia.conseguirPrestamo(500);
    assertEquals(500, Panaderia.getDinero());
    assertEquals(500, Panaderia.getValorDeudas());
    Panaderia.conseguirPrestamo(1000);
    assertEquals(1500, Panaderia.getDinero());
    assertEquals(1000, Panaderia.getValorDeudas());
  }
}
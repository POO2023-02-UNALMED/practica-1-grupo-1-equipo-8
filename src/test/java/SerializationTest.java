import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.humanos.Cliente;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.humanos.Trabajador;
import gestorAplicacion.humanos.Cocinero;
import gestorAplicacion.humanos.Domiciliario;
import baseDatos.Serializador;
import UIMain.UI;

import java.util.HashMap;

public class SerializationTest {
  public static void main(String[] args) {
    System.out.println("Test de serializacion");
    Panaderia panaderia = new Panaderia();
    //panaderia = Serializador.cargarPanaderia();
    System.out.println("panaderia cargada");

    
    
    Ingrediente.crearIngrediente("leche");
    Ingrediente.crearIngrediente("harina");
    Ingrediente.crearIngrediente("azucar");
    Ingrediente.crearIngrediente("huevos");
    Ingrediente.crearIngrediente("mantequilla");
    Ingrediente.crearIngrediente("sal");
    Ingrediente.crearIngrediente("levadura");
    Ingrediente.crearIngrediente("chocolate");
    Ingrediente.crearIngrediente("canela");
    Ingrediente.crearIngrediente("queso");
    Ingrediente.crearIngrediente("milo");
    Ingrediente.crearIngrediente("miga de galleta");

    HashMap<String, Integer> ingredientesBunuelo = new HashMap<String, Integer>();
    ingredientesBunuelo.put("harina", 2);
    ingredientesBunuelo.put("huevos", 2);
    ingredientesBunuelo.put("queso", 1);
    HashMap<String, Integer> ingredientesRollosDeCanela = new HashMap<String, Integer>();
    ingredientesRollosDeCanela.put("harina", 2);
    ingredientesRollosDeCanela.put("huevos", 2);
    ingredientesRollosDeCanela.put("canela", 1);
    ingredientesRollosDeCanela.put("azucar", 1);
    ingredientesRollosDeCanela.put("mantequilla", 1);
    ingredientesRollosDeCanela.put("leche", 1);
    HashMap<String, Integer> ingredientesBrownie = new HashMap<String, Integer>();
    ingredientesBrownie.put("harina", 1);
    ingredientesRollosDeCanela.put("huevos", 2);
    ingredientesRollosDeCanela.put("chocolate", 1);
    ingredientesRollosDeCanela.put("leche", 1);
    ingredientesRollosDeCanela.put("mantequilla", 1);
    HashMap<String, Integer> ingredientesTortaMilo = new HashMap<String, Integer>();
    ingredientesTortaMilo.put("harina", 1);
    ingredientesRollosDeCanela.put("huevos", 2);
    ingredientesRollosDeCanela.put("milo", 1);
    ingredientesRollosDeCanela.put("leche", 1);
    HashMap<String, Integer> ingredientesCheesecake = new HashMap<String, Integer>();
    ingredientesCheesecake.put("queso", 1);
    ingredientesCheesecake.put("huevos", 2);
    ingredientesCheesecake.put("miga de galleta", 1);
    ingredientesCheesecake.put("azucar", 1);
    ingredientesCheesecake.put("mantequilla", 1);

    Producto.crearProductoPersonalizado("bunuelo", ingredientesBunuelo);
    Producto.crearProductoPersonalizado("rollos de canela", ingredientesRollosDeCanela);
    Producto.crearProductoPersonalizado("brownie", ingredientesBrownie);
    Producto.crearProductoPersonalizado("torta de milo", ingredientesTortaMilo);
    Producto.crearProductoPersonalizado("cheesecake", ingredientesCheesecake);

    panaderia.crearCuenta("Sahely", 1123433775, "123");
    panaderia.crearCuenta("Richard", 202, "qwerty");
    Cliente.getSesion().setPresupuesto(30000);
    

    
    Cliente cliente1= panaderia.inicioSesionId(202);
    panaderia.inicioSesionConstrasena(cliente1,"qwerty");
    System.out.println("cliente1 cargado");
    HashMap<String, Integer> ingredientesCocinar = new HashMap<String, Integer>();
    ingredientesCocinar.put("14", 2);
    ingredientesCocinar.put("15", 4);
    Domiciliario domiciliario1 = new Domiciliario("Juan",panaderia);
    Cocinero cocinero1 = new Cocinero("Mateo", "Decoracion",panaderia);
    System.out.println("cocinero1 cargado");
    System.out.println("domiciliario1 cargado");

    for(Ingrediente ingrediente: Ingrediente.getBaseDatosIngredientes()){
      System.out.println(ingrediente.getNombre());
    }

    Ingrediente.getBaseDatosIngredientes();
    System.out.println("ingredientes cargados");
    panaderia.cocinar(ingredientesCocinar);

    UI.mostrarOpciones(panaderia.getInventario());
    

    Serializador.guardarPanaderia(panaderia);
    System.out.println("panaderia guardada");
  }
}
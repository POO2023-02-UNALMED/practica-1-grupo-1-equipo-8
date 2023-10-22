import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.humanos.Cliente;
import gestorAplicacion.gestion.Canasta;
import baseDatos.Serializador;
import UIMain.UI;

import java.util.HashMap;

public class SerializationTest {
  public static void main(String[] args) {
    System.out.println("Test de serializacion");
    Panaderia panaderia = new Panaderia();
    Panaderia panaderiaCargada = Serializador.cargarPanaderia();
    System.out.println(panaderiaCargada);

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

    UI.mostrarOpciones(panaderia.getInventario());

    Serializador.guardarPanaderia(panaderia);
    System.out.println("panaderia guardada");
  }
}
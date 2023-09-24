package humanos;
import java.lang.reflect.Array;
import java.util.Random;
import gestion.Panaderia;
import comida.Ingrediente;
import comida.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cocinero extends Trabajador {
    String especialidad;

    public Cocinero() {
        super();
    }

    public Cocinero(String nombre, String especialidad) {
        super(nombre);
        this.especialidad = especialidad;
    }

    public Cocinero(String nombre, double habilidad, double dineroEnMano, String especialidad) {
        super(nombre, habilidad, dineroEnMano);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {return especialidad;}

    public void setEspecialidad(String especialidad) {this.especialidad = especialidad;}

    //@Override
    public void laborParticular(Producto producto, int cantidadDeseada) {
        for (int i = 0; i < cantidadDeseada; i++) {
            List<String> ingredientesFaltantes = new ArrayList<>();
            Map<String, Integer> ingredientesRequeridos = producto.getIngredientes();
    
            for (String ingrediente : ingredientesRequeridos.keySet()) {
                if (ingredientesRequeridos.get(ingrediente) > Panaderia.getInvIngredientes().get(ingrediente)) {
                    ingredientesFaltantes.add(ingrediente);
                }
            }
}

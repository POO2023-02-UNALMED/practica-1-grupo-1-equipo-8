package humanos;
import java.util.Random;

public class Cocinero extends Persona {
    private double experiencia;
    private int salario;
    private double papaya;
    private boolean disponibilidad;

    public Cocinero() {
        Random random = new Random();
        this.experiencia = random.nextDouble();
        this.papaya = random.nextDouble();
    }

    public Cocinero(String nombre, String apellido, int dinero, int salario, boolean disponibilidad) {
        super(nombre, apellido, dinero);
        
        this.salario = salario;
        this.disponibilidad = disponibilidad;

        Random random = new Random();
        this.experiencia = random.nextDouble();
        this.papaya = random.nextDouble();
    }

    public double getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        if (experiencia >= 0) {
            this.experiencia = experiencia;
        }
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        if (salario >= 0) {
            this.salario = salario;
        }
    }

    public double getPapaya() {
        return papaya;
    }

    public void setPapaya(int papaya) {
        if (papaya >= 0) {
            this.papaya = papaya;
        }
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void cocinar() {
        System.out.println("Cocinando...");
    }

    public void comprarIngredientes() {
        System.out.println("Comprando ingredientes...");
    }
}

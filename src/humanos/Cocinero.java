package humanos;

public class Cocinero extends Persona{
    private int experiencia;
    private int salario;
    private int papaya;
    private boolean disponibilidad;

    public Cocinero() {}

    public Cocinero(String nombre, String apellido, int dinero, int experiencia, int salario, int papaya, boolean disponibilidad) {
        super(nombre, apellido, dinero);
        this.experiencia = experiencia;
        this.salario = salario;
        this.papaya = papaya;
        this.disponibilidad = disponibilidad;
    }

    public int getExperiencia() {
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

    public int getPapaya() {
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

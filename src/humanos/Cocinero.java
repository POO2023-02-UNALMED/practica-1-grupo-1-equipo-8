package humanos;

public class Cocinero extends Persona{
    private int experiencia;
    private int salario;
    private int papaya;

    public Cocinero() {}

    public Cocinero(String nombre, String apellido, int dinero, int experiencia, int salario, int papaya) {
        super(nombre, apellido, dinero);
        this.experiencia = experiencia;
        this.salario = salario;
        this.papaya = papaya;
    }
}

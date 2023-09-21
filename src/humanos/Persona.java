package humanos;

public class Persona {
    private String nombre;
    private String apellido;
    private int dinero;

    public Persona() {}

    public Persona(String nombre, String apellido, int dinero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dinero = dinero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApelllido(String apellido) {
        this.apellido = apellido;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
}

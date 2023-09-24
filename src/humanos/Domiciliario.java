package humanos;
import java.util.Random;

public class Domiciliario extends Trabajador {
    Boolean licencia;

    public Domiciliario() {
        super();
    }

    public Domiciliario(String nombre, Boolean licencia) {
        super(nombre);
        this.licencia = licencia;
    }

    public Domiciliario(String nombre, double habilidad, double dineroEnMano, Boolean licencia) {
        super(nombre, habilidad, dineroEnMano);
        this.licencia = licencia;
    }

    public Boolean getLicencia() {return licencia;}

    public void setLicencia(Boolean licencia) {this.licencia = licencia;}

    @Override
    public String laborParticular() {
        // TODO: implementar
    }
}

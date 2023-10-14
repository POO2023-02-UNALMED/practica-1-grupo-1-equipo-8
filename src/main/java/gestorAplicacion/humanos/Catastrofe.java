package gestorAplicacion.humanos;

import java.util.Random;

public class Catastrofe {
    
    private int maldad;

    public Catastrofe() {
        Random rand = new Random();
        this.maldad = rand.nextInt(10) + 1; // 1 <= maldad <= 10
    }

    public Catastrofe(int maldad) {
        this.maldad = maldad;
    }

    public int getMaldad() {
        return maldad;
    }

    public void setMaldad(int maldad) {
        this.maldad = maldad;
    }

    public boolean pincharLLanta(Domiciliario domiciliario){
        if (domiciliario.habilidad < this.maldad){
            domiciliario.setLicencia(false);
            return true;
        }
        return false;
    }

    public boolean dificultadProducto(Cocinero cocinero){
        if (cocinero.habilidad < this.maldad){
            cocinero.setFallado(false);
            return true;
        }
        return false;
    }

    //TODO: la idea es que se implementen las otras catastrofes para las otras funcionalidades
}




//TODO cambiar a panaderia lo siguiente apenas se puede
{
public void sortDomiciliariosByHabilidad(List<Domiciliario> domiciliarios) {
    Collections.sort(domiciliarios, new Comparator<Domiciliario>() {
        @Override
        public int compare(Domiciliario d1, Domiciliario d2) {
            return Integer.compare(d1.getHabilidad(), d2.getHabilidad());
        }
    });
}
}
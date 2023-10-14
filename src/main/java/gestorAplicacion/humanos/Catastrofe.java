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
}

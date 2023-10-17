package gestorAplicacion.humanos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import gestorAplicacion.gestion.Panaderia;

public class Catastrofe {
    
    private int maldad;
    private static ArrayList<Catastrofe> reponsables = new ArrayList<Catastrofe>();
    private double dineroRobado;

    public Catastrofe() {
        Random rand = new Random();
        this.maldad = rand.nextInt(10) + 1; // 1 <= maldad <= 10
        Catastrofe.reponsables.add(this);
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

    public Domiciliario pincharLLanta(Domiciliario domiciliario){
        if (domiciliario.habilidad < this.maldad){
            domiciliario.setLicencia(false);
            return domiciliario;
        }
        return domiciliario;
    }

    public boolean dificultadProducto(Cocinero cocinero){
        if (cocinero.habilidad < this.maldad){
            cocinero.setFallado(false);
            return true;
        }
        return false;
    }
    
    // Este  método sirve para escoger un objeto tipo Catastrofe aleatoriamente
    
    public static Catastrofe responsableAleatorio(){

        ArrayList<Catastrofe> x = (ArrayList<Catastrofe>) Catastrofe.reponsables.clone();
        
        Collections.shuffle(x);

        Catastrofe elegido = x.get(0);

        return elegido;

    }
    
    public Cocinero robarComprador(Cocinero rival) {
    	
    	if (this.getMaldad() > rival.getHabilidad()) {
    		
    		rival.setDineroEnMano(0);;
            rival.setRobado(true);
            this.dineroRobado += rival.getDineroEnMano();
    	}
    	
    	return rival;	
    }

    public boolean paradaTransito(Domiciliario domiciliario){
        Random rand = new Random();
        int chance = rand.nextInt(5) + 1; // 1 <= chance <= 5
        if (domiciliario.habilidad < chance){
            domiciliario.setLicencia(false);
            return false;
        }

        return true;
    }

    //TODO: la idea es que se implementen las otras catastrofes para las otras funcionalidades

    //TODO cambiar a panaderia lo siguiente apenas se puede
    
}
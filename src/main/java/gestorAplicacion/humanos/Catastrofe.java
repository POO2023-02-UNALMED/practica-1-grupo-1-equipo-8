package gestorAplicacion.humanos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import gestorAplicacion.gestion.Panaderia;

public class Catastrofe {

    public static String[] fallosCocina = {"El producto se quemo","El cocinero se corto un dedo","El producto se pudrio","Hubo un incendio","El cocinero esta triste","Encontramos un pelo en la comida"};

    private int maldad;
    private double dineroRobado;

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

        Catastrofe responsable = new Catastrofe();

        return responsable;

    }
    
    public Domiciliario robarComprador(Domiciliario rival) {
    	
    	if (this.getMaldad() > rival.getHabilidad()) {
    		
    		rival.setDineroEnMano(0);
            rival.setRobado(false);
            this.dineroRobado += rival.getDineroEnMano();
    	}

        else {
            rival.setRobado(true);
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
package gestorAplicacion.humanos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import gestorAplicacion.gestion.Panaderia;

/**
 * La clase Catastrofe representa un evento desafortunado que puede ocurrir en la aplicación de delivery.
 * Contiene métodos para simular diferentes tipos de catástrofes, como pinchar la llanta de un domiciliario, 
 * dificultar la preparación de un producto por parte de un cocinero, robar a un comprador, bloquear una calle 
 * y revocar la licencia de un domiciliario. También tiene un método para seleccionar aleatoriamente una 
 * catástrofe y un atributo para llevar un registro del dinero robado en caso de robo a un comprador.
 */
public class Catastrofe {

    public static String[] fallosCocina = {"El producto se quemo","El cocinero se corto un dedo","El producto se pudrio","Hubo un incendio","El cocinero esta triste","Encontramos un pelo en la comida"};

    private int maldad;
    private double dineroRobado;

//construcores
    public Catastrofe() {
        Random rand = new Random();
        this.maldad = rand.nextInt(10) + 1; // 1 <= maldad <= 10
    }

    public Catastrofe(int maldad) {
        this.maldad = maldad;
    }

//getters y setters
    public int getMaldad() {
        return maldad;
    }

    public void setMaldad(int maldad) {
        this.maldad = maldad;
    }

    /**
     * Método que simula el acto de pinchar la llanta de un domiciliario.
     * Si la habilidad del domiciliario es menor que la maldad de la catástrofe, se le revoca la licencia.
     * @param domiciliario El domiciliario al que se le va a pinchar la llanta.
     * @return El domiciliario con o sin licencia dependiendo de si su habilidad es menor que la maldad de la catástrofe.
     */
    public Domiciliario pincharLLanta(Domiciliario domiciliario){
        if (domiciliario.habilidad < this.maldad){
            domiciliario.setLicencia(false);
            return domiciliario;
        }
        return domiciliario;
    }

    /**
     * Comprueba si la habilidad del cocinero es menor que la maldad de la catástrofe.
     * Si es así, el cocinero no falla y se devuelve true.
     * Si no, el cocinero falla y se devuelve false.
     * @param cocinero el cocinero a comprobar su habilidad
     * @return true si el cocinero no falla, false si falla
     */
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

    /**
     * Verifica si un domiciliario puede pasar por una calle bloqueada debido a una catástrofe.
     * Si la habilidad del domiciliario es menor que un número aleatorio entre 1 y 5, se le revoca la licencia.
     * @param domiciliario El domiciliario que intenta pasar por la calle bloqueada.
     * @return true si el domiciliario puede pasar, false si se le revocó la licencia.
     */
    public boolean paradaTransito(Domiciliario domiciliario){
        int chance = this.maldad;
        if (domiciliario.getHabilidad() < chance){
            domiciliario.setLicencia(false);
            return false;
        }
        return true;
    }
}
package humanos;
import java.util.ArrayList;

public class Cliente extends Persona{
    int id;
    String cupon;
    ArrayList<Canasta> canastas;
    ArrayList<Recibos> recibos;

    public Cliente() {}

    public Cliente(String nombre, String apellido, int dinero, int id, String cupon, ArrayList<Canasta> canastas, ArrayList<Recibos> recibos) {
        super(nombre, apellido, dinero);
        this.id = id;
        this.cupon = cupon;
        this.canastas = canastas;
        this.recibos = recibos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id >= 0) {
            this.id = id;
        }
    }

    public String getCupon() {
        return cupon;
    }

    public void setCupon(String cupon) {
        this.cupon = cupon;
    }
    

    public ArrayList<Canasta> getCanastas() {
        return canastas;
    }

    public void crearCanasta() {
        this.canastas.add(new Canasta());
    }
    

    public ArrayList<Recibos> getRecibos() {
        return recibos;
    }

    public void addRecibo(Recibos recibo) {
        this.recibos.add(recibo);
    }
}

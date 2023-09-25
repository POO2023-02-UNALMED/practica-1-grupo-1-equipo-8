package humanos;

import java.util.ArrayList;
import java.util.List;
import gestion.Canasta;
import gestion.Recibo;

public class Cliente {
	
	private String nombre;
	private Integer id;
	private String tipoDescuento;
	private float presupuesto;
	private ArrayList<Canasta> canastas;
	private ArrayList<Recibo> recibos;
	
	//crear cliente con todos los atributos
	
	public Cliente(String nombre, Integer id, String tipoDescuento, float presupuesto, ArrayList<Canasta> canastas, ArrayList<Recibo> recibos) {
		
		this.nombre = nombre;
		this.id = id;
		this.tipoDescuento = tipoDescuento;
		this.presupuesto = presupuesto;
		this.canastas = canastas;
		this.recibos = recibos;
		
	}
	
	//crear un cliente sin pasarles listas de canastas y recibos (constructor estandar)
	
	public Cliente(String nombre, Integer id, String tipoDescuento, float presupuesto) {
		
		this(nombre, id, tipoDescuento, presupuesto, null, null); //cambiar esos null por listas vacías
			
	}
	
	//crear un cliente el cual no tiene ningún descuento
	
	public Cliente(String nombre, Integer id, float presupuesto) {
		
		this(nombre, id, "ninguno", presupuesto, null, null); //cambiar esos null por listas vacías
		
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTipoDescuento() {
		return tipoDescuento;
	}
	
	public void setTipoDescuento(String tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}
	
	public float getPresupuesto() {
		return presupuesto;
	}
	
	public void setPresupuesto(float presupuesto) {
		this.presupuesto = presupuesto;
	}
	
	public ArrayList<Canasta> getCanastas() {
		return canastas;
	}
	
	public void setCanastas(ArrayList<Canasta> canastas) {
		this.canastas = canastas;
	}
	
	public ArrayList<Recibo> getRecibos() {
		return recibos;
	}
	
	public void setRecibos(ArrayList<Recibo> recibos) {
		this.recibos = recibos;
	}
	
	public void crearCanasta() {
		
		Canasta canasta = new Canasta(null, null);
		this.canastas.add(canasta);
		
	}
		
}
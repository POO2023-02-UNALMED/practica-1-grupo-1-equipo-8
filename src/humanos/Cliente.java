package humanos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import comida.Ingrediente;
import comida.Producto;
import gestion.Canasta;
import gestion.Panaderia;
import gestion.Recibo;

public class Cliente {

	private String nombre;
	private Integer id;
	private String tipoDescuento;
	private float presupuesto;
	private List<Canasta> canastas = new ArrayList<Canasta>();
	private List<Recibo> recibos = new ArrayList<Recibo>();
	private Panaderia panaderia;

	// crear cliente con todos los atributos

	public Cliente(String nombre, Integer id, String tipoDescuento, float presupuesto, List<Canasta> canastas,
			List<Recibo> recibos, Panaderia panaderia) {

		this.nombre = nombre;
		this.id = id;
		this.tipoDescuento = tipoDescuento;
		this.presupuesto = presupuesto;
		this.canastas = canastas;
		this.recibos = recibos;
		this.panaderia = panaderia;

	}

	// crear un cliente sin pasarles listas de canastas y recibos (constructor
	// estandar)

	public Cliente(String nombre, Integer id, String tipoDescuento, float presupuesto, Panaderia panaderia) {
		
		List<Canasta> list1 = new ArrayList<Canasta>();
    	List<Recibo> list2 = new ArrayList<Recibo>();

    	this.nombre = nombre;
		this.id = id;
		this.tipoDescuento = tipoDescuento;
		this.presupuesto = presupuesto;
		this.canastas = list1;
		this.recibos = list2;
		this.panaderia = panaderia;

	}

	// crear un cliente el cual no tiene ningún descuento

	public Cliente(String nombre, Integer id, float presupuesto, Panaderia panaderia) {

		List<Canasta> list1 = new ArrayList<Canasta>();
    	List<Recibo> list2 = new ArrayList<Recibo>();

    	this.nombre = nombre;
		this.id = id;
		this.tipoDescuento = "ninguno";
		this.presupuesto = presupuesto;
		this.canastas = list1;
		this.recibos = list2;
		this.panaderia = panaderia;

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

	public List<Canasta> getCanastas() {
		return canastas;
	}

	public void setCanastas(ArrayList<Canasta> canastas) {
		this.canastas = canastas;
	}

	public List<Recibo> getRecibos() {
		return recibos;
	}

	public void setRecibos(ArrayList<Recibo> recibos) {
		this.recibos = recibos;
	}

	public Panaderia getPanaderia() {
		return panaderia;
	}

	public void setPanaderia(Panaderia panaderia) {
		this.panaderia = panaderia;
	}

	public void crearCanasta() {

		Canasta canasta = new Canasta(null, null);
		this.canastas.add(canasta);

	}

}
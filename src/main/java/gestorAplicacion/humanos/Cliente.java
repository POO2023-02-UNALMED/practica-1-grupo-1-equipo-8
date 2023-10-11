package gestorAplicacion.humanos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.gestion.Recibo;

public class Cliente implements Serializable{

	private String nombre;
	private Integer id;
	private Direccion direccion;
	private Descuento tipoDescuento;
	private double presupuesto;
	private List<Canasta> canastas = new ArrayList<Canasta>();
	private List<Recibo> recibos = new ArrayList<Recibo>();
	private static Cliente sesion; //atributo estatico que almacena el cliente que ha iniciado sesion (necesario para parte funcional)
	// crear cliente con todos los atributos

	public Cliente(String nombre, Integer id, Direccion direccion ,Descuento tipoDescuento, float presupuesto, List<Canasta> canastas,
			List<Recibo> recibos) {

		this.nombre = nombre;
		this.id = id;
		this.direccion = direccion;
		this.tipoDescuento = tipoDescuento;
		this.presupuesto = presupuesto;
		this.canastas = canastas;
		this.recibos = recibos;
		
	}

	// crear un cliente sin pasarles listas de canastas y recibos (constructor
	// estandar)

	public Cliente(String nombre, Integer id, Direccion direccion, Descuento tipoDescuento, float presupuesto) {
		
		List<Canasta> list1 = new ArrayList<Canasta>();
    	List<Recibo> list2 = new ArrayList<Recibo>();

    	this.nombre = nombre;
		this.id = id;
		this.direccion = direccion;
		this.tipoDescuento = tipoDescuento;
		this.presupuesto = presupuesto;
		this.canastas = list1;
		this.recibos = list2;

	}

	// crear un cliente el cual no tiene ningún descuento

	public Cliente(String nombre, Integer id, Direccion direccion, float presupuesto) {

		List<Canasta> list1 = new ArrayList<Canasta>();
    	List<Recibo> list2 = new ArrayList<Recibo>();

    	this.nombre = nombre;
		this.id = id;
		this.direccion = direccion;
		this.tipoDescuento = Descuento.NINGUNO;
		this.presupuesto = presupuesto;
		this.canastas = list1;
		this.recibos = list2;

	}

	public Cliente(String nombre, int id, Descuento tipoDescuento, double presupuesto, ArrayList<Canasta> canastas, ArrayList<Recibo> recibos) {
		this.nombre = nombre;
		this.id = id;
		this.tipoDescuento = tipoDescuento;
		this.presupuesto = presupuesto;
		this.canastas = canastas;
		this.recibos = recibos;
	}

	public Cliente(String nombre,int id,Descuento tipoDescuento, double presupuesto){
		this.nombre = nombre;
		this.id = id;
		this.tipoDescuento = tipoDescuento;
		this.presupuesto = presupuesto;
	}

	public Cliente (String nombre, int id, double presupuesto){
		this.nombre = nombre;
		this.id = id;
		this.presupuesto = presupuesto;
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

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion= direccion;
	}

	public Descuento getTipoDescuento() {
		return tipoDescuento;
	}

	public void setTipoDescuento(Descuento tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
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

	
	public void setCanastas(List<Canasta> canastas) {
		this.canastas = canastas;
	}

	public void setRecibos(List<Recibo> recibos) {
		this.recibos = recibos;
	}

	public static Cliente getSesion() {
		return sesion;
	}

	public static void setSesion(Cliente sesion) {
		Cliente.sesion = sesion;
	}

	public void crearCanasta() {

		Canasta canasta = new Canasta(null, null);
		this.canastas.add(canasta);

	}

	//TODO trabajar para enviar la canasta a pagar - Lo de abajo es solo una plantilla
	public void enviarCanastasAFacturar() {
		for (Canasta canasta : this.canastas) {
			Recibo recibo = new Recibo(canasta, this);
			this.recibos.add(recibo);
		}
		Panaderia.cobrarCliente(canastas,recibos);
	}

	//TODO trabajar para enviar la canasta a domicilio - Lo de abajo es solo una plantilla
	public void enviarCanastasADomicilio(){
		Panaderia.enviarDomicilio(canastas, this);
	}

	public enum Direccion {
		MEDELLIN("Cerca"),
		BOGOTA("Lejos"),
		ENVIGADO("Medio"),
		ITAGUI("Cerca");
	
		private final String distancia;
	
		Direccion(String distancia) {
			this.distancia = distancia;
		}
	
		public String getDistancia() {
			return distancia;
		}
	}
	
		public enum Descuento {
			NINGUNO(0),
			ESTUDIANTE(0.1),
			SENIOR(0.2),
			EMPLEADO(0.3);
		
			private final double descuento;
		
			Descuento(double descuento) {
				this.descuento = descuento;
			}
		
			public double getDescuento() {
				return descuento;
			}
		}

		

}
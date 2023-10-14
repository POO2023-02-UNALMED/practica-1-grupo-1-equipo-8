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
import gestorAplicacion.gestion.Cupon.DescuentoPorTipo;
import UIMain.GestionDomicilioCliente;

public class Cliente implements Serializable{

	private String nombre;
	private int id;
	private String contrasena;
	private String direccionTXT;
	private Direccion direccion = null;
	private DescuentoPorTipo tipoDescuento = null;
	private double presupuesto = 0;
	private ArrayList<Canasta> canastas = new ArrayList<Canasta>();
	private List<Recibo> recibos = new ArrayList<Recibo>();
	private static Cliente sesion; //atributo estatico que almacena el cliente que ha iniciado sesion (necesario para parte funcional)
	// crear cliente con todos los atributos
	private Domiciliario domiciliario;

	public Cliente(String nombre, Integer id, String contrasena, String direccionTXT, Direccion direccion ,DescuentoPorTipo tipoDescuento, double presupuesto, ArrayList<Canasta> canastas,
			List<Recibo> recibos) {

		this.nombre = nombre;
		this.id = id;
		this.contrasena = contrasena;
		this.direccionTXT = direccionTXT;
		this.direccion = direccion;
		this.tipoDescuento = tipoDescuento;
		this.presupuesto = presupuesto;
		this.canastas = canastas;
		this.recibos = recibos;
		
	}

	// crear un cliente sin pasarles listas de canastas y recibos (constructor
	// estandar)

	public Cliente(String nombre, Integer id, String contrasena, Direccion direccion, DescuentoPorTipo tipoDescuento, double presupuesto) {
		
		ArrayList<Canasta> list1 = new ArrayList<Canasta>();
		List<Recibo> list2 = new ArrayList<Recibo>();

        this.nombre = nombre;
		this.id = id;
		this.contrasena = contrasena;
		this.direccion = direccion;
		this.tipoDescuento = tipoDescuento;
		this.presupuesto = presupuesto;
		this.canastas = list1;
		this.recibos = list2;

	}

	// crear un cliente el cual no tiene ningún descuento

	public Cliente(String nombre, Integer id, String contrasena, Direccion direccion, double presupuesto) {

		this(nombre, id, contrasena, direccion, DescuentoPorTipo.NINGUNO, presupuesto);

	}

	public Cliente(String nombre, Integer id, String contrasena,  DescuentoPorTipo tipoDescuento, double presupuesto, ArrayList<Canasta> canastas, ArrayList<Recibo> recibos) {
		this.nombre = nombre;
		this.id = id;
		this.contrasena  = contrasena;
		this.tipoDescuento = tipoDescuento;
		this.presupuesto = presupuesto;
		this.canastas = canastas;
		this.recibos = recibos;
	}

	public Cliente(String nombre,int id, String contrasena, DescuentoPorTipo tipoDescuento, double presupuesto){
		this.nombre = nombre;
		this.id = id;
		this.contrasena = contrasena;
		this.tipoDescuento = tipoDescuento;
		this.presupuesto = presupuesto;
	}

	public Cliente (String nombre, int id, String contrasena, double presupuesto){
		this.nombre = nombre;
		this.id = id;
		this.contrasena = contrasena;
		this.presupuesto = presupuesto;
	}

	public Cliente(){
		ArrayList<Canasta> list1 = new ArrayList<Canasta>();
        List<Recibo> list2 = new ArrayList<Recibo>();
		this.tipoDescuento = DescuentoPorTipo.NINGUNO;
		this.canastas = list1;
		this.recibos = list2;
		this.contrasena = "1234";
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

	public String getDireccionTXT() {
		return direccionTXT;
	}

	public void setDireccionTXT(String direccionTXT) {
		this.direccionTXT = direccionTXT;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion= direccion;
	}

	public DescuentoPorTipo getTipoDescuento() {
		return tipoDescuento;
	}

	public void setTipoDescuento(DescuentoPorTipo tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public ArrayList<Canasta> getCanastas() {
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


	public void setRecibos(List<Recibo> recibos) {
		this.recibos = recibos;
	}

	public void setCanastas(List<Canasta> canastas) {
		this.canastas = (ArrayList<Canasta>) canastas;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
			this.contrasena = contrasena;
	}


	public static Cliente getSesion() {
		return sesion;
	}

	public static void setSesion(Cliente sesion) {
		Cliente.sesion = sesion;
	}

	public Domiciliario getDomiciliario() {
		return domiciliario;
	}

	public void setDomiciliario(Domiciliario domiciliario) {
		this.domiciliario = domiciliario;
	}


	//TODO revisar 
	public void crearCanasta() {
		
		//Canasta canasta = new Canasta(null, null);
		//this.canastas.add(canasta);

	}

	public void calificarDomiciliario(Domiciliario domiciliario, double calificacion){
		double calificacionVieja = domiciliario.getCalificacion();
		double calificacionNueva = (calificacionVieja + calificacion)/2;
		domiciliario.setCalificacion(calificacionNueva);
	}


	//TODO trabajar para enviar la canasta a pagar - Lo de abajo es solo una plantilla
	public void enviarCanastasAFacturar(Canasta canastas) {
		
	}

	public void enviarCanastasADomicilio(Canasta canastas){
		enviarCanastasAFacturar(canastas);
		Panaderia.enviarDomicilio(canastas, this);
		double calificacion = GestionDomicilioCliente.pedirCalificacion();
		calificarDomiciliario(domiciliario, calificacion);
		Panaderia.reviewDomiciliario(domiciliario);
	}

	//Métodos para agregar la informacion faltante del cliente
	//TODO trabajar los metodos de abajo(Sahely)
	
	/*
	 * Esta función se encarga de gestionar los datos faltantes del cliente
	 * Se encarga de ver si las tres funciones de abajo retornan true, es decir, si el cliente tiene direccion, presupuesto y descuento
	 * Si alguna de las tres funciones retorna false, se le pide al cliente que ingrese la informacion faltante retornando un string que avise del problema
	 * Si ya está todo completo se retorna un string vacío
	 */
	
	public String gestionDatosFaltantes(double valorCompra){
		
		boolean x = this.verificarDireccion();
		boolean y = this.verificarPresupuesto(valorCompra);
		boolean z = this.verificarDescuentoPorTipo();
		
		if (x == false & y == true & z == true) {
			
			return "Falta dirección";
		}
		
		else if (x == false & y == false & z == true){
			
			return "Falta dirección y presupuesto";
		}
		
		else if (x == false & y == true & z == false){
			
			return "Falta dirección y descuento";
		}
		
		else if (x == true & y == false & z == true){
			
			return "Falta presupuesto";
		}
		
		else if (x == true & y == false & z == false){
			
			return "Falta presupuesto y descuento";
		}
		
		else if (x == true & y == true & z == false){
			
			return "Falta descuento";
		}
		
		else {
			
			return "";
		}
	}
	
	/*
	 * Esta función se encarga de verificar si el cliente que está en sesión tiene
	 * direccion válida
	 * Si tiene direccion válida, se devuelve true
	 * Si no tiene direccion válida, se devuelve false
	 */

	public boolean verificarDireccion() {
		
		if (this.direccion == null) {
			
			return false;
		}
		
		else {
			
			return true;
		}		
	}
	
	/*
	 * Esta función se encarga de verificar si el cliente que está en sesión tiene
	 * presupuesto
	 * Si tiene presupuesto, se devuelve true
	 * Si no tiene presupuesto, se devuelve false
	 */

	public boolean verificarPresupuesto(double valorCompra) {
		
		if (this.presupuesto <= valorCompra) {
			
			return false;
		}
		
		else {
			
			return true;
		}	
	}
	
	/*
	 * Esta función se encarga de verificar si el cliente que está en sesión tiene
	 * descuento
	 * Si tiene descuento, se devuelve true
	 * Si no tiene descuento, se devuelve false
	 */

	public boolean verificarDescuentoPorTipo() {
		
		if (this.tipoDescuento == null) {
			
			return false;
		}
		
		else {
			
			return true;
		}
	}
	
	/*
	 * Esta función se encarga de establecer el domicilio del cliente que está en
	 * sesión
	 * Se le pasa el domicilio, y se verifica que sea válido
	 * Si es válido, se establece el domicilio y se devuelve true
	 * Si no es válido, se devuelve false
	 * También se debe hacer la logica para establecer la direccion del atributo de
	 * tipo direccion, porque en el atributo que es direccionTXT se guarda la
	 * direccion señuelo en formato de texto, y la idea es sacarle el
	 * establecimiento al cliente con la ciudad a traves del enum Direccion de
	 * cliente
	 * Si no hay ciudad valida hay que printear que hasta allá no se tiene cobertura
	 */

	public boolean establecerDomicilioValido(String direccion, String ciudad) {
		
		boolean desicion = false; 
		Direccion necesaria = null;
		
		Direccion[] DireccionesValidas = Direccion.values();
		
		for (Direccion i: DireccionesValidas) {
			
			if (ciudad.toUpperCase() == i.name()) {
				
				desicion = true;
				necesaria = i;
				break;
			}		
		}
		
		if (desicion == true) {
			
			this.direccionTXT = direccion;
			this.direccion = necesaria;
		}
		
		return desicion;
		//tengo que hacer un método que lea esto para decir si sí fue valido o no 
	}
	
	/*
	 * Esta función se encarga de establecer el presupuesto del cliente que está en
	 * sesión
	 * Se le pasa el presupuesto, y se verifica que sea válido
	 * Si es válido, se establece el presupuesto y se devuelve true
	 * Si no es válido, se devuelve false
	 */

	public boolean establecerPresupuestoValido(double presupuesto, double valorCompra) {
		
		if (presupuesto < valorCompra) {
			
			return false;
		}
		
		else {
			
			return true;
		}
		//tengo que hacer un método que lea esto para decir si sí fue valido o no 
	}
	
	/*
	 * Esta función se encarga de establecer el descuento del cliente que está en sesión
	 * A esta funcion se le pasa lo de Estudiante, Empleado, Maestro o así para que compare con el enum y establezca el descuento
	 * Se le pasa el descuento, y se verifica que sea válido
	 * Si es válido, se establece el descuento y se devuelve true
	 * Si no es válido, se devuelve false
	 */

	public boolean establecerDescuentoPorTipoValido(String descuento) {
		/*
		 * Esta función se encarga de establecer el descuento del cliente que está en sesión
		 * A esta funcion se le pasa lo de Estudiante, Empleado, Maestro o así para que compare con el enum y establezca el descuento
		 * Se le pasa el descuento, y se verifica que sea válido
		 * Si es válido, se establece el descuento y se devuelve true
		 * Si no es válido, se devuelve false
		 */
		return true;
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

}
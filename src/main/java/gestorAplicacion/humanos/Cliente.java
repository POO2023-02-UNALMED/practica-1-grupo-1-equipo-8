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

public class Cliente implements Serializable{

	private String nombre;
	private int id;
	private String contrasena;
	private String direccionTXT;
	private Direccion direccion;
	private DescuentoPorTipo tipoDescuento;
	private double presupuesto;
	private ArrayList<Canasta> canastas = new ArrayList<Canasta>();
	private List<Recibo> recibos = new ArrayList<Recibo>();
	private static Cliente sesion; //atributo estatico que almacena el cliente que ha iniciado sesion (necesario para parte funcional)
	// crear cliente con todos los atributos

	public Cliente(String nombre, Integer id, String direccionTXT, Direccion direccion ,DescuentoPorTipo tipoDescuento, double presupuesto, ArrayList<Canasta> canastas,
			List<Recibo> recibos) {

		this.nombre = nombre;
		this.id = id;
		this.direccionTXT = direccionTXT;
		this.direccion = direccion;
		this.tipoDescuento = tipoDescuento;
		this.presupuesto = presupuesto;
		this.canastas = canastas;
		this.recibos = recibos;
		
	}

	// crear un cliente sin pasarles listas de canastas y recibos (constructor
	// estandar)

	public Cliente(String nombre, Integer id, Direccion direccion, DescuentoPorTipo tipoDescuento, double presupuesto) {
		
		ArrayList<Canasta> list1 = new ArrayList<Canasta>();
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

	public Cliente(String nombre, Integer id, Direccion direccion, double presupuesto) {

		ArrayList<Canasta> list1 = new ArrayList<Canasta>();
    List<Recibo> list2 = new ArrayList<Recibo>();

    this.nombre = nombre;
		this.id = id;
		this.direccion = direccion;
		this.tipoDescuento = DescuentoPorTipo.NINGUNO;
		this.presupuesto = presupuesto;
		this.canastas = list1;
		this.recibos = list2;

	}

	public Cliente(String nombre, int id, DescuentoPorTipo tipoDescuento, double presupuesto, ArrayList<Canasta> canastas, ArrayList<Recibo> recibos) {
		this.nombre = nombre;
		this.id = id;
		this.tipoDescuento = tipoDescuento;
		this.presupuesto = presupuesto;
		this.canastas = canastas;
		this.recibos = recibos;
	}

	public Cliente(String nombre,int id,DescuentoPorTipo tipoDescuento, double presupuesto){
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

	public Cliente(){
		ArrayList<Canasta> list1 = new ArrayList<Canasta>();
        List<Recibo> list2 = new ArrayList<Recibo>();
		this.tipoDescuento = DescuentoPorTipo.NINGUNO;
		this.canastas = list1;
		this.recibos = list2;
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

	public void crearCanasta() {

		Canasta canasta = new Canasta(null, null);
		this.canastas.add(canasta);

	}

	//TODO trabajar para enviar la canasta a pagar - Lo de abajo es solo una plantilla
	public void enviarCanastasAFacturar() {
		/* 
		for (Canasta canasta : this.canastas) {
			Recibo recibo = new Recibo(canasta, this);
			this.recibos.add(recibo);
		}
		Panaderia.cobrarCliente(canastas,recibos);
		*/
	}

	//TODO trabajar para enviar la canasta a domicilio - Lo de abajo es solo una plantilla
	public void enviarCanastasADomicilio(ArrayList<Canasta> canastas){
		Panaderia.enviarDomicilio(canastas, this);
		enviarCanastasAFacturar();
	}

	//Métodos para agregar la informacion faltante del cliente
	//TODO trabajar los metodos de abajo(Sahely)
	public String gestionDatosFaltantes(){
		/*
		 * Esta función se encarga de gestionar los datos faltantes del cliente
		 * Se encarga de ver si las tres funciones de abajo retornan true, es decir, si el cliente tiene direccion, presupuesto y descuento
		 * Si alguna de las tres funciones retorna false, se le pide al cliente que ingrese la informacion faltante retornando un string que avise del problema
		 * Si ya está todo completo se retorna un string vacío
		 */
		return "";
	}

	public boolean verificarDireccion() {
		/*
		 * Esta función se encarga de verificar si el cliente que está en sesión tiene
		 * direccion válida
		 * Si tiene direccion válida, se devuelve true
		 * Si no tiene direccion válida, se devuelve false
		 */
		return true;
	}

	public boolean verificarPresupuesto() {
		/*
		 * Esta función se encarga de verificar si el cliente que está en sesión tiene
		 * presupuesto
		 * Si tiene presupuesto, se devuelve true
		 * Si no tiene presupuesto, se devuelve false
		 */
		return true;
	}

	public boolean verificarDescuentoPorTipo() {
		/*
		 * Esta función se encarga de verificar si el cliente que está en sesión tiene
		 * descuento
		 * Si tiene descuento, se devuelve true
		 * Si no tiene descuento, se devuelve false
		 */
		return true;
	}

	public boolean establecerDomicilioValido(String direccion, String ciudad) {
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
		return true;
	}

	public boolean establecerPresupuestoValido(double presupuesto) {
		/*
		 * Esta función se encarga de establecer el presupuesto del cliente que está en
		 * sesión
		 * Se le pasa el presupuesto, y se verifica que sea válido
		 * Si es válido, se establece el presupuesto y se devuelve true
		 * Si no es válido, se devuelve false
		 */
		return true;
	}

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
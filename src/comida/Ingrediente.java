package comida;

public class Ingrediente {
    String nombre;
    int cantidad;
    double precio;
    //String direccion; ?
    
    public Ingrediente() {
    	Producto.Ingredientes.add(this);
    }
    
    public Ingrediente(String nombre1,int cantidad1,double precio1) {
    	this.nombre =nombre1;
    	this.cantidad=cantidad1;
    	this.precio=precio1;
    	Producto.Ingredientes.add(this);
    }
    
  //getters y setters de los atributos
    
    public String getNombre() {return nombre;}
    public void setNombre(String Newnombre) {this.nombre =Newnombre;}
	public int getCantidad() {return cantidad;}
	public void setCosto(int Newcantidad) {this.cantidad = Newcantidad;}
	public double getPrecio() {return precio;}
	public void setPrecio(double Newprecio) {this.precio = Newprecio;}
	
	//metodo para crear
	
	public static Ingrediente crearIngrediente(String Nnombre,int Ncantidad, double Nprecio) {
		return new Ingrediente(Nnombre,Ncantidad,Nprecio);
	}
}

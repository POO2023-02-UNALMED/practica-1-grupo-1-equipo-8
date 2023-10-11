package gestorAplicacion.gestion;

import java.io.Serializable;

/**
 * Esta clase representa un cupón que se puede utilizar para aplicar descuentos
 * a productos.
 * Contiene tres enumeraciones: DescuentoBasico, DescuentoPorTipo y
 * DescuentoProducto.
 */
public class Cupon implements Serializable{

  /**
   * Este enum representa los diferentes valores de descuento que se pueden
   * aplicar a un cupón.
   * Cada valor de descuento está asociado con un porcentaje.
   */
  public enum DescuentoBasico {
    DIEZ_PORCIENTO(0.1),
    VEINTE_PORCIENTO(0.2),
    TREINTA_PORCIENTO(0.3),
    CUARENTA_PORCIENTO(0.4),
    CINCUENTA_PORCIENTO(0.5);

    private final double valor;

    DescuentoBasico(double valor) {
      this.valor = valor;
    }

    public double getValor() {
      return valor;
    }
  }

  /**
   * Este enum representa los diferentes tipos de descuentos que se pueden aplicar
   * a un cupón.
   * Los tipos disponibles son ESTUDIANTE y PROFESOR, cada uno con un valor de
   * descuento correspondiente.
   */
  public enum DescuentoPorTipo {
    ESTUDIANTE(0.1),
    PROFESOR(0.1);

    private final double valor;

    DescuentoPorTipo(double valor) {
      this.valor = valor;
    }

    public double getValor() {
      return valor;
    }
  }

  /**
   * Este enum representa los diferentes productos y sus valores de descuento
   * correspondientes.
   */
  public enum DescuentoProducto {
    PRODUCTO_A("Producto A", 0.1),
    PRODUCTO_B("Producto B", 0.1),
    PRODUCTO_C("Producto C", 0.1),
    PRODUCTO_D("Producto D", 0.1),
    PRODUCTO_E("Producto E", 0.1);

    private final String producto;
    private final double valor;

    DescuentoProducto(String producto, double valor) {
      this.producto = producto;
      this.valor = valor;
    }

    public String getProducto() {
      return producto;
    }

    public double getValor() {
      return valor;
    }
  }

  /**
   * Este enum representa los diferentes valores de descuento que se pueden aplicar a un cupón basado en la cantidad de productos comprados.
   * Los valores de descuento son del 5% para 3 productos, del 10% para 5 productos y del 15% para 8 productos.
   */
  public enum DescuentoPorCantidad {
    TRES(0.05),
    CINCO(0.1),
    OCHO(0.15);

    private final double valor;

    DescuentoPorCantidad(double valor) {
      this.valor = valor;
    }

    public double getValor() {
      return valor;
    }
  }

  
}

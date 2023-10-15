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



  
  
}

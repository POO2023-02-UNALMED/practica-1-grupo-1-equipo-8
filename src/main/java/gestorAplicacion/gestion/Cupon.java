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
    PROFESOR(0.1),
    NINGUNO(0),
    SENIOR(0.2),
    EMPLEADO(0.3);

    private final double valor;

    DescuentoPorTipo(double valor) {
      this.valor = valor;
    }

    public double getValor() {
      return valor;
    }
  }

  
  
}

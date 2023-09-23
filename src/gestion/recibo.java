package gestion;
public class Recibo {
    int idCliente;
    int idRecibo;
    float precioTotal;
    float precioFinal;
    float descuento;

    public Recibo(int idCliente, int idRecibo, float precioTotal, float descuento) {
        this.idCliente = idCliente;
        this.idRecibo = idRecibo;
        this.precioTotal = precioTotal;
        this.descuento = descuento;
        this.precioFinal = precioTotal * (1 - descuento);
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }

    public float getDescuento() {
        return descuento;
    }
}

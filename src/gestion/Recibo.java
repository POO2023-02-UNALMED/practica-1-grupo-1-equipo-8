package gestion;

import humanos.Cliente;

//IMPORTANTE hay que crear un historial de facturas, esto involucra serializacion.

import java.util.List; //Libreria para listas
import java.util.ArrayList; //Libreria para listas
import java.util.Date; //Libreria para manejar fechas
import java.time.Instant; //Libreria para obterer la fecha actual
import java.util.Random; //Libreria para sacar numeros aleatorios (pensaba en premiar un cliente aleatorio volviendo su factura gratis)

public class Recibo {
    Cliente cliente;
    int idRecibo;
    static int totalFacturas;
    float precioTotal;
    float precioFinal;
    float descuento;
    Date fecha = new Date();
    ArrayList<String> factura = new ArrayList<String>();

    public Recibo(Cliente cliente, int idRecibo, float precioTotal, float descuento) {
        this.cliente = cliente;
        this.idRecibo = idRecibo;
        this.precioTotal = precioTotal;
        this.descuento = descuento;
        this.precioFinal = precioTotal * (1 - descuento);
        this.fecha = Date.from(Instant.now());
        totalFacturas++;
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

    public ArrayList<String> getFactura() {
        return factura;
    }

    /*
     * En el siguiente metodo funcionara la parte de serialización de esta clase
     * la idea es que cada linea de la factura salga perfectamente alineada y/o
     * centrada como seria en la factura de una tienda o de una farmacia
     * estaba pensando en que se podria guardar cada factura en un documento de
     * texto
     */
    public ArrayList<String> imprimirFactura() {
        
        factura.add(String.format("DOMICILIOS 24 HORAS"));
        factura.add(String.format("PANADERIA POO"));
        factura.add(String.format("FACULTAD DE MINAS"));
        factura.add(String.format(""));
        factura.add("-".repeat(40));
        factura.add(String.format("Factura Nro: %s", idRecibo));
        factura.add(String.format("Fecha y hora: "));
        factura.add(String.format("Panadero que atendio su pedido: "));
        factura.add(String.format("Ciudad: Medellin"));
        factura.add(String.format("Cliente: %s", cliente.getNombre()));
        factura.add(String.format("Identificacion: %s", cliente.getId()));
        factura.add(String.format(""));
        factura.add(String.format("DETALLE DE VENTA"));
        factura.add("DESCRIPCION" + "".repeat(20) + "CANTIDAD" + "".repeat(20) + "VALOR");
        factura.add("-".repeat(64));

        factura.add(String.format(""));
        factura.add(String.format(""));
        factura.add(String.format(""));
        factura.add(String.format(""));
        factura.add(String.format(""));
        factura.add(String.format(""));
        factura.add(String.format(""));

        return factura;
    }

}

package gestion;

import humanos.Cliente;
//Esta clase la he estado modificando yo (Richard), cualquier sugerencia me pueden escribir aqui
//IMPORTANTE hay que crear un historial de facturas, esto involucra serializacion.

import java.util.List; //Libreria para listas
import java.util.ArrayList; //Libreria para listas
import java.util.Date; //Libreria para manejar fechas
import java.io.EOFException;
import java.time.Instant; //Libreria para obterer la fecha actual
import java.util.Random; //Libreria para sacar numeros aleatorios (pensaba en premiar un cliente aleatorio volviendo su factura gratis)
import java.util.HashMap;
import java.util.Map;
import comida.Producto;

public class Recibo {
    private Cliente cliente;
    private int idRecibo;
    private static int totalFacturas;
    private float precioTotal;
    private float precioFinal;
    private float descuento;
    private Date fecha = new Date();
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

    public Cliente getCliente() {
        return cliente;
    }

    public int getIdRecibo() {
        return idRecibo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public static void setTotalFacturas(int totalFacturas) {
        Recibo.totalFacturas = totalFacturas;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public void setPrecioFinal(float precioFinal) {
        this.precioFinal = precioFinal;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFactura(ArrayList<String> factura) {
        this.factura = factura;
    }

    public static int getTotalFacturas() {
        return totalFacturas;
    }

    public Date getFecha() {
        return fecha;
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
        factura.add("    ____                                    ?~~bL");
        factura.add("    z@~ b                                    |  `U,");
        factura.add("]@[  |                                   ]'  z@'");
        factura.add("d@~' `|, .__     _----L___----, __, .  _t'   `@j");
        factura.add("`@L_,   \"-~ `--\"~-a,           `C.  ~\"\"O_    ._`@");
        factura.add("q@~'   ]P       ]@[            `Y=,   `H+z_  `a@");
        factura.add("`@L  _z@        d@               Ya     `-@b,_a'");
        factura.add("    `-@d@a'       )@[               `VL      `a@@'");
        factura.add("    aa~'   ],  .a@'                qqL  ), ./~");
        factura.add("    @@_  _z~  _d@[                 .V@  .L_d'");
        factura.add("    \"~@@@'  ]@@@'        __      )@n@bza@-\"");
        factura.add("        `-@zzz@@@L        )@@z     ]@@=%-\"");
        factura.add("        \"~~@@@@@bz_    _a@@@@z___a@K");
        factura.add("            \"~-@@@@@@@@@@@@@@@@@@~\"");
        factura.add("                `~~~-@~~-@@~~~~~'");
        factura.add(String.format(""));
;       factura.add(String.format("DOMICILIOS 24 HORAS"));
        factura.add(String.format("PANADERIA POO"));
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

        for(Canasta canasta: cliente.getCanastas()){
            for(Map.Entry<Producto,Integer> item: canasta.getProductos().entrySet()){
                factura.add(String.format("%s %d %f", item.getKey(), item.getValue(), item.getKey().getCosto()*item.getValue()));
        }
        factura.add(String.format(""));
        factura.add("▄▄▄▄▄▄▄  ▄ ▄▄ ▄▄▄▄▄▄▄"); 
        factura.add("█ ▄▄▄ █ ██ ▀▄ █ ▄▄▄ █"); 
        factura.add("█ ███ █ ▄▀ ▀▄ █ ███ █"); 
        factura.add("█▄▄▄▄▄█ █ ▄▀█ █▄▄▄▄▄█"); 
        factura.add("▄▄ ▄  ▄▄▀██▀▀ ▄▄▄ ▄▄ "); 
        factura.add("▄   ▀█▄▀ ▄█ ▄▄▀▀ █▄ █"); 
        factura.add("██▄ █▄▄ ▄██▀▄ ▄▀ █ ▄█"); 
        factura.add("▄▄▄▄▄▄▄ █▄▀▀ ▄  ▄ ▄▄▀"); 
        factura.add("█ ▄▄▄ █   ██▀▀▄▄█   █"); 
        factura.add("█ ███ █ ▀▄ ▀▄  ██▄█▀█"); 
        factura.add("█▄▄▄▄▄█ █▀▀▄▄▀▀▀█  ▄ "); 

        return factura;
    }

}



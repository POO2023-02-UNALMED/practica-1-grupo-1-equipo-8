package gestorAplicacion.gestion;

import UIMain.Texto;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.humanos.Cliente;
//import gestorAplicacion.humanos.Cliente.Descuento;
import gestorAplicacion.humanos.Cliente.Direccion;

import java.util.List; //Libreria para listas
import java.util.ArrayList; //Libreria para listas
import java.util.Date; //Libreria para manejar fechas
import java.io.EOFException;
import java.io.Serializable;
import java.time.Instant; //Libreria para obterer la fecha actual
import java.util.Random; //Libreria para sacar numeros aleatorios (pensaba en premiar un cliente aleatorio volviendo su factura gratis)
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Recibo implements Serializable{
    private Cliente cliente;
    private int idRecibo;
    private static int totalFacturas;
    private double precioTotal;
    private double precioFinal;
    private DescuentoPorTipo descuento;
    private Date fecha = new Date();
    ArrayList<String> factura = new ArrayList<String>();

    //ESTE PRIMER CONSTRUCTOR ES EL UNICO QUE USARA LA CLASE RECIBO, LOS DEMAS CREO QUE SON PARA PRUEBAS DE NICOLAS
    public Recibo(Cliente cliente, double precioTotal, DescuentoPorTipo descuento) {
        totalFacturas++;
        this.cliente = cliente;
        this.idRecibo = totalFacturas;
        this.precioTotal = precioTotal;
        this.descuento = descuento;
        this.precioFinal = precioTotal * (1 - descuento.getValor()); 
        this.fecha = Date.from(Instant.now());
    }

    public Recibo(Cliente cliente, int idRecibo, double precioTotal) {
        this.cliente = cliente;
        this.idRecibo = idRecibo;
        this.precioTotal = precioTotal;
        this.descuento = DescuentoPorTipo.NINGUNO;
        this.precioFinal = precioTotal; 
        this.fecha = Date.from(Instant.now());
        totalFacturas++;
    }

    public Recibo(Cliente cliente, int idRecibo) {
        this.cliente = cliente;
        this.idRecibo = idRecibo;
        this.precioTotal = 0;
        this.descuento = DescuentoPorTipo.NINGUNO;
        this.precioFinal = precioTotal; 
        this.fecha = Date.from(Instant.now());
        totalFacturas++;
    }

    public Recibo(Canasta canasta, Direccion direccion, Cliente cliente){
        Random rand = new Random();
        this.cliente = cliente;
        this.idRecibo = rand.nextInt(1000);
        canasta.generarCosto();
        this.precioTotal = canasta.getCostoTotal();
        this.descuento = cliente.getTipoDescuento();
        this.precioFinal = precioTotal; 
        this.fecha = Date.from(Instant.now());
        totalFacturas++;
    }

    public Recibo(ArrayList<Canasta> canastas, Direccion direccion, Cliente cliente){
        Random rand = new Random();
        this.cliente = cliente;
        this.idRecibo = rand.nextInt(1000);
        this.precioTotal = 0;
        for(Canasta canasta: canastas){
        	canasta.generarCosto();
            this.precioTotal += canasta.getCostoTotal();
        }
        this.descuento = cliente.getTipoDescuento();
        this.precioFinal = precioTotal; 
        this.fecha = Date.from(Instant.now());
        totalFacturas++;
    }

    public double getPrecioTotal() {
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

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public void setDescuento(DescuentoPorTipo descuento) {
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

    public double getPrecioFinal() {
        return precioFinal;
    }

    public DescuentoPorTipo getDescuento() {
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
    public void imprimirFactura() {
        factura.add(Texto.centrar("                      ██████            "));
        factura.add(Texto.centrar("          ████████████░░░░░░██          "));
        factura.add(Texto.centrar("        ██░░░░░░░░░░░░██░░░░░░██████    "));
        factura.add(Texto.centrar("      ██░░░░░░░░░░░░░░██░░░░░░██░░░░██  "));
        factura.add(Texto.centrar("    ██░░░░░░░░░░░░░░░░░░██░░░░██░░░░░░██"));
        factura.add(Texto.centrar("    ██░░░░░░░░░░░░░░░░░░░░██░░██░░░░░░██"));
        factura.add(Texto.centrar("    ██░░░░░░░░░░░░░░░░░░░░██░░████████  "));
        factura.add(Texto.centrar("  ██░░████░░░░░░░░░░░░░░░░██░░██        "));
        factura.add(Texto.centrar("  ██░░░░░░████░░░░░░░░░░░░████          "));
        factura.add(Texto.centrar("██░░░░░░░░░░░░████░░░░░░░░██            "));
        factura.add(Texto.centrar("██░░░░░░░░░░░░░░░░██░░░░██              "));
        factura.add(Texto.centrar("  ██░░░░░░░░░░░░░░██████                "));
        factura.add(Texto.centrar("██░░████████░░░░░░██                    "));
        factura.add(Texto.centrar("██░░░░░░░░░░██████                      "));
        factura.add(Texto.centrar("  ██░░░░░░░░░░██                        "));
        factura.add(Texto.centrar("██░░██████████                          "));
        factura.add(Texto.centrar("██░░░░░░░░██                            "));
        factura.add(Texto.centrar("  ██░░░░██                              "));
        factura.add(Texto.centrar("    ████                                "));
        factura.add("");
        factura.add(String.format(""));
        factura.add(String.format(Texto.centrar("POO Bakery")));
        factura.add(String.format(Texto.centrar("DOMICILIOS 24 HORAS")));
        factura.add(String.format(""));
        factura.add(Texto.centrar(String.format("Factura Nro: %s", idRecibo)));
        factura.add(Texto.centrar(String.format("Fecha y hora: ")));
        factura.add(Texto.centrar(String.format("Panadero que atendio su pedido: Mateo")));
        factura.add(Texto.centrar(String.format("Ciudad: Medellin")));
        factura.add(Texto.centrar(String.format("Cliente: %s", cliente.getNombre())));
        factura.add(Texto.centrar(String.format("Identificacion: %s", cliente.getId())));
        factura.add(Texto.centrar(String.format("")));
        factura.add(Texto.centrar(String.format(Texto.centrar("DETALLE DE VENTA"))));
        factura.add(" ");
        /*factura.add(Texto.alinear("Descripcion", "Cantidad", "Precio")); //Reutilizar aqui la funcion de mateo
        factura.add("-".repeat(111));

        int contador = 0; //aqui tendre que reutilizar el codigo de mateo en gestionCompraMain
        for(Canasta canasta: cliente.getCanastas()){
            for(Map.Entry<Producto,Integer> item: canasta.getProductos().entrySet()){
                contador ++;
                factura.add(Texto.alinear(item.getKey().getNombre(), item.getValue(), item.getKey().getCosto()*item.getValue()));
        }*/
        factura.add(Texto.centrar(String.format(Texto.centrar("DETALLE DE IMPUESTOS"))));//DESPUES TRABAJARE EN LA DEDUCCION DE IMPUESTOS
        factura.add(Texto.centrar(String.format(Texto.centrar(""))));
        factura.add(Texto.centrar(String.format("Total articulos comprados: %s", idRecibo)));
        factura.add(Texto.centrar("EN POO BAKERY SOMOS EXPERTOS EN AHORRO:"));
        factura.add(Texto.centrar(String.format("TU AHORRO HOY FUE DEL %s%", (descuento.getValor()*100))));
        factura.add(Texto.centrar(String.format("EQUIVALENTE A: ")));
        factura.add(Texto.centrar("POO Bakery"));
        factura.add(Texto.centrar("solo calidad"));
        factura.add(Texto.centrar("Gracias por elegirnos"));
        factura.add(String.format(""));
        factura.add(Texto.centrar("▄▄▄▄▄▄▄  ▄ ▄▄ ▄▄▄▄▄▄▄")); 
        factura.add(Texto.centrar("█ ▄▄▄ █ ██ ▀▄ █ ▄▄▄ █")); 
        factura.add(Texto.centrar("█ ███ █ ▄▀ ▀▄ █ ███ █")); 
        factura.add(Texto.centrar("█▄▄▄▄▄█ █ ▄▀█ █▄▄▄▄▄█")); 
        factura.add(Texto.centrar("▄▄ ▄  ▄▄▀██▀▀ ▄▄▄ ▄▄ ")); 
        factura.add(Texto.centrar("▄   ▀█▄▀ ▄█ ▄▄▀▀ █▄ █")); 
        factura.add(Texto.centrar("██▄ █▄▄ ▄██▀▄ ▄▀ █ ▄█")); 
        factura.add(Texto.centrar("▄▄▄▄▄▄▄ █▄▀▀ ▄  ▄ ▄▄▀")); 
        factura.add(Texto.centrar("█ ▄▄▄ █   ██▀▀▄▄█   █")); 
        factura.add(Texto.centrar("█ ███ █ ▀▄ ▀▄  ██▄█▀█")); 
        factura.add(Texto.centrar("█▄▄▄▄▄█ █▀▀▄▄▀▀▀█  ▄ "));
        factura.add(String.format(""));


        }

    }




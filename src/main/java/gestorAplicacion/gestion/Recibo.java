package gestorAplicacion.gestion;

import UIMain.Texto;
import gestorAplicacion.humanos.Cliente;
import gestorAplicacion.humanos.Domiciliario;

import java.util.ArrayList; //Libreria para listas
import java.util.Date; //Libreria para manejar fechas
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant; //Libreria para obterer la fecha actual

/**
 * La clase Recibo representa un recibo de compra generado para un cliente.
 * Contiene información sobre el cliente, el número de recibo, el subtotal, el total, el descuento, la fecha, si ha sido pagado o no, la canasta de productos comprados y el costo de domicilio (si aplica).
 * Además, tiene métodos para obtener y establecer los valores de sus atributos, así como para imprimir la factura correspondiente.
 */
public class Recibo implements Serializable {
    private Cliente cliente;
    private int idRecibo;
    private static int totalFacturas;
    private double subtotal;
    private double total;
    private double descuento;
    private Date fecha = new Date();
    private boolean pagado = false;
    private Canasta canasta;
    private double costoDomicilio;
    public static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    ArrayList<String> factura = new ArrayList<String>();
		private Domiciliario domiciliario;

    public Recibo(Cliente cliente, Canasta canasta, Domiciliario domiciliario){

        totalFacturas++;
        this.costoDomicilio = domiciliario.getCostoDomicilio();
        this.canasta = canasta;
        this.cliente = cliente;
        this.idRecibo = totalFacturas;
        this.subtotal = canasta.getCostoTrasDescuentoEnLista();
        this.domiciliario = domiciliario;
        if(cliente.getTipoDescuento() == null){
            this.descuento = 0;
        } else{
            this.descuento = cliente.getTipoDescuento().getValor();
        }
        this.total = subtotal * (1 - descuento);
        this.fecha = Date.from(Instant.now());
    }
//getter y setters
    public double getCostoDomicilio(){
        return this.costoDomicilio;
    }

    public void setCostoDomicilio(double costoDomicilio){
        this.costoDomicilio = costoDomicilio;
    }

    public Domiciliario getDomiciliario(){
        return this.domiciliario;
    }

    public void setDomiciliario(Domiciliario domiciliario){
        this.domiciliario = domiciliario;
    }

    public Canasta getCanasta() {
        return canasta;
    }

    public void setCanasta(Canasta canasta) {
        this.canasta = canasta;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public double getSubtotal() {
        return subtotal;
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

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setDescuento(double descuento) {
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

    public double getTotal() {
        return total;
    }

    public double getDescuento() {
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
        factura.add(Texto.centrar(String.format("Fecha y hora: %s", formato.format(fecha))));
        factura.add(Texto.centrar(String.format("Panadero que atendio su pedido: Mateo")));
        factura.add(Texto.centrar(String.format("Ciudad: Medellin")));
        factura.add(Texto.centrar(String.format("Cliente: %s", cliente.getNombre())));
        factura.add(Texto.centrar(String.format("Identificacion: %s", cliente.getId())));
        factura.add(Texto.centrar(String.format("")));
        factura.add(Texto.centrar(String.format(Texto.centrar("DETALLE DE VENTA"))));
        factura.add(" ");
        /*
         * factura.add(Texto.alinear("Descripcion", "Cantidad", "Precio")); //Reutilizar
         * aqui la funcion de mateo
         * factura.add("-".repeat(111));
         * 
         * int contador = 0; //aqui tendre que reutilizar el codigo de mateo en
         * gestionCompraMain
         * for(Canasta canasta: cliente.getCanastas()){
         * for(Map.Entry<Producto,Integer> item: canasta.getProductos().entrySet()){
         * contador ++;
         * factura.add(Texto.alinear(item.getKey().getNombre(), item.getValue(),
         * item.getKey().getCosto()*item.getValue()));
         * }
         */
        factura.add(Texto.centrar(String.format(Texto.centrar("DETALLE DE IMPUESTOS"))));// DESPUES TRABAJARE EN LA
                                                                                         // DEDUCCION DE IMPUESTOS
        factura.add(Texto.centrar(String.format(Texto.centrar(""))));
        factura.add(Texto.centrar(String.format("Total articulos comprados: %s", idRecibo)));
        factura.add(Texto.centrar("EN POO BAKERY SOMOS EXPERTOS EN AHORRO:"));
        factura.add(Texto.centrar(String.format("TU AHORRO HOY FUE DEL %s%", (descuento * 100))));
        // factura.add(Texto.centrar(String.format("EQUIVALENTE A: "))); //colocar el
        // total ahorrado aqui cuando este todo listo
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

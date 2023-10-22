package UIMain;

import java.io.*;
import java.util.Scanner;
import gestorAplicacion.comida.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.humanos.*;
import baseDatos.Serializador;


//Esta clase la he estado modificando yo (Richard), cualquier sugerencia me pueden escribir aqui
public class Main { //preferiblemente colocar solo los metodos para que el codigo no quede todo disperso
    static Scanner input = new Scanner(System.in);
    static boolean continuar = true;
    public static void main(String[] args) {
        Panaderia panaderia=null;
        panaderia=Serializador.cargarPanaderia();
        do{
            continuar = GestionInicioCliente.sesionCliente(panaderia); //este metodo aun no esta listo
            do{ //bucle del menu
                boolean continuar = true;
                UI.titulo();
                String strOpciones = "Escriba el numero correspondiente a la opcion que quiere elegir\n" +
                        "0. Cerrar sesion\n" +
                        "1. Agregar productos a la canasta de compras\n" +
                        "2. Ver catalogo de productos, descripciones y productos mas vendidos\n"+
                        "3. Ver las facturas de mis compras pasadas\n" +
                        "4. Cambiar contraseña\n" +
                        "5. Meter plata a mi cuenta\n" +
                        "6. validar tipo de cliente\n" +
                        "7. Historial de ordenes, pedir canastas otra vez";
        
                System.out.println(strOpciones);
                String eleccion = input.nextLine();
                boolean eleccionValida = true;
                    switch (eleccion) {

                    case "0": //cerrar sesion
                        UI.cerrarSesion();
                        break;

                    case "1":
                        UI.compras(panaderia);
                        UI.domicilio();
                        UI.facturacion();
                        UI.concluirOrden();
                        break;

                    case "2":
                        UI.verCatalogoDescripcion(panaderia);
                        break;

                    case "3":
                        UI.historialRecibos(Cliente.getSesion());
                        break;
                    case "4":
                        UI.cambiarClave();
                        break;

                    case "5":
                        UI.meterPlata();
                        break;

                    case "6":
                        UI.validarTipoCliente();
                        break;

                    case "7":
                        UI.historialOrdenes();
                        break;

                    // caso donde el cliente elije una opcion no valida
                    default:
                        System.out.println("Usted escogio una opción que no estaba en la lista");
                        eleccionValida = false;
                        break;

                } while (eleccionValida == false | Cliente.getSesion() != null);
            }while(true);
        } while(continuar);
        Serializador.guardarPanaderia(panaderia);
        System.exit(0);
    }
}

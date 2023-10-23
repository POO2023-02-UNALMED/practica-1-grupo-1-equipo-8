package UIMain;

import java.util.Scanner;
import gestorAplicacion.comida.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.humanos.*;
import baseDatos.Serializador;

//Esta clase la he estado modificando yo (Richard), cualquier sugerencia me pueden escribir aqui
public class Main { // preferiblemente colocar solo los metodos para que el codigo no quede todo
                    // disperso
    static boolean continuar = true;
    static boolean eleccionValida = true;

    public static void main(String[] args) {
        Panaderia panaderia=null;
        panaderia=Serializador.cargarPanaderia();
        Scanner input = new Scanner(System.in);

        while(true){
            continuar = GestionInicioCliente.sesionCliente(panaderia);
            if(!continuar){
                break;
            }
            while (eleccionValida == false || Cliente.getSesion() != null){ //bucle del menu
                UI.titulo();
                String strOpciones = "Escriba el numero correspondiente a la opcion que quiere elegir\n" +
                        "0. Cerrar sesion\n" +
                        "1. Agregar productos a la canasta de compras\n" +
                        "2. Ver catalogo de productos y descripcion\n"+
                        "3. Lo mejor de nuestra panaderia\n" +
                        "4. Ver las facturas de mis compras pasadas\n" +
                        "5. Cambiar contrasena\n" +
                        "6. Meter plata a mi cuenta\n" +
                        "7. validar tipo de cliente\n" +
                        "8. Modificar direccion\n" +
                        "9. Historial de ordenes, pedir canastas otra vez";
        
                System.out.println(strOpciones);
                String eleccion = input.nextLine();
                eleccionValida = true;
                switch (eleccion) {

                    case "0": //cerrar sesion
                        UI.cerrarSesion(); //buena 
                        break;

                    case "1":
                        UI.compras(panaderia);
                        UI.domicilio(Cliente.getSesion()); //NICOOOOOOOOOO
                        UI.facturacion();
                        UI.concluirOrden();//NICOOOOOO y el resto
                        break;

                    case "2":
                        UI.verCatalogoDescripcion(panaderia); //buena (pero no pasa el nombre del producto y crashea cuando se ingresa una letra)
                        break;

                    case "3":
                        UI.verRanking(panaderia, Cliente.getSesion()); //Crash
                        break;

                    case "4":
                        UI.historialRecibos(Cliente.getSesion()); //(Creemos que bien)
                        break;
                    case "5":
                        UI.cambiarClave(Cliente.getSesion()); //buena
                        break;

                    case "6":
                        UI.meterPlata(Cliente.getSesion()); //buena
                        break;

                    case "7":
                        UI.validarTipoCliente(Cliente.getSesion()); //buena
                        break;
                    
                    case "8":
                        UI.modificarDireccion(Cliente.getSesion()); //buena
                        break;

                    case "9":
                        UI.historialOrdenes(Cliente.getSesion());
                        break;

                    // caso donde el cliente elije una opcion no valida
                    default:
                        System.out.println("Usted escogio una opción que no estaba en la lista");
                        eleccionValida = false;
                        break;
                }
            }
        }
        Serializador.guardarPanaderia(panaderia);
        System.exit(0);
    }
}

package UIMain;

//TODO arreglar
import java.util.Scanner;
import gestorAplicacion.humanos.Cliente;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.gestion.Recibo;

public class UI { // en esta clase estaran habran metodos en general de la interfaz de usuario
  public static final String BLACK = "\u001B[30m";
  public static final String RED = "\u001B[31m";
  public static final String GREEN = "\u001B[32m";
  public static final String YELLOW = "\u001B[33m";
  public static final String BLUE = "\u001B[34m";
  public static final String PURPLE = "\u001B[35m";
  public static final String CYAN = "\u001B[36m";
  public static final String WHITE = "\u001B[37m";
  public static final String RESET = "\u001B[0m";

  public static boolean continuar = true;
  static String eleccion;
  static Scanner input = new Scanner(System.in);

  public static void titulo() { // tuve que agregar el simbolo \ antes de cada comilla para que java ignore los
                                // caracteres especiales, al final se vera diferente
    System.out.println("                                   ____                                    ?~~bL");
    System.out.println("                                 z@~ b                                    |  `U,");
    System.out.println("                                ]@[  |                                   ]'  z@'");
    System.out.println("                                d@~' `|, .__     _----L___----, __, .  _t'   `@j");
    System.out.println("                                `@L_,   \"-~ `--\"~-a,           `C.  ~\"\"O_    ._`@");
    System.out.println("                                q@~'   ]P       ]@[            `Y=,   `H+z_  `a@");
    System.out.println("                                `@L  _z@        d@               Ya     `-@b,_a'");
    System.out.println("                                  `-@d@a'       )@[               `VL      `a@@'");
    System.out.println("                                    aa~'   ],  .a@'                qqL  ), ./~");
    System.out.println("                                    @@_  _z~  _d@[                 .V@  .L_d'");
    System.out.println("                                    \"~@@@'  ]@@@'        __      )@n@bza@-\"");
    System.out.println("                                        `-@zzz@@@L        )@@z     ]@@=%-\"");
    System.out.println("                                        \"~~@@@@@bz_    _a@@@@z___a@K");
    System.out.println("                                            \"~-@@@@@@@@@@@@@@@@@@~\"");
    System.out.println("                                                `~~~-@~~-@@~~~~~'");
    System.out.println("");
    System.out.println(
        "88888888ba    ,ad8888ba,     ,ad8888ba,      88888888ba             88                                         ");
    System.out.println(
        "88      \"8b  d8\"'    `\"8b   d8\"'    `\"8b     88      \"8b            88                                   ");
    System.out.println(
        "88      ,8P d8'        `8b d8'        `8b    88      ,8P            88                                         ");
    System.out.println(
        "88aaaaaa8P' 88          88 88          88    88aaaaaa8P' ,adPPYYba, 88   ,d8  ,adPPYba, 8b,dPPYba, 8b       d8 ");
    System.out.println(
        "88\"\"\"\"\"\"'   88          88 88          88    88\"\"\"\"\"\"8b, \"\"     `Y8 88 ,a8\"  a8P_____88 88P'   \"Y8 `8b     d8'");
    System.out.println(
        "88          Y8,        ,8P Y8,        ,8P    88      `8b ,adPPPPP88 8888[    8PP\"\"\"\"\"\"\" 88          `8b ");
    System.out.println(
        "88           Y8a.    .a8P   Y8a.    .a8P     88      a8P 88,    ,88 88`\"Yba, \"8b,   ,aa 88           `8b,d8' ");
    System.out.println(
        "88            `\"Y8888Y\"'     `\"Y8888Y\"'      88888888P\"  `\"8bbdP\"Y8 88   `Y8a `\"Ybbd8\"' 88             Y88'");
    System.out.println(
        "                                                                                                       d8'     ");
    System.out.println(
        "                                                                                                      d8'      ");
    System.out.println("");
  }

  // Este metodo muestra las opciones de todos los productos que puede comprar el
  // cliente
  public static String mostrarOpciones() {
    String mensaje = "PRODUCTOS DISPONIBLES PARA COMPRAR:";
    mensaje += Texto.centrar("PRODUCTOS\n");

    for (Producto producto : Producto.getBaseDatosProductos()) { // productos que le alcanza el dinero al cliente
      if (producto.getCosto() <= Cliente.getSesion().getPresupuesto()) {
        mensaje += GREEN + Texto.alinear(String.format("%s. %s", producto.getId(), producto.getNombre()),
            producto.getUnidades(), producto.getCosto()) + RESET + "\n";
      }
    }

    for (Producto producto : Producto.getBaseDatosProductos()) { // productos que no le alcanza el dinero al cliente
      if (producto.getCosto() > Cliente.getSesion().getPresupuesto()) {
        mensaje += RED + Texto.alinear(String.format("%s. %s", producto.getId(), producto.getNombre()),
            producto.getUnidades(), producto.getCosto()) + RESET + "\n";
      }
    }

    mensaje += Texto.centrar("INGREDIENTES\n");

    for (Ingrediente ingrediente : Ingrediente.getBaseDatosIngredientes()) {
      if (ingrediente.getPrecioDeVenta() < Cliente.getSesion().getPresupuesto()) {
        mensaje += GREEN + Texto.alinear(String.format("%s. %s", ingrediente.getId(), ingrediente.getNombre()), 0,
            ingrediente.getPrecioDeVenta()) + RESET + "\n";
      }
    }

    for (Ingrediente ingrediente : Ingrediente.getBaseDatosIngredientes()) {
      if (ingrediente.getPrecioDeVenta() < Cliente.getSesion().getPresupuesto()) {
        mensaje += RED + Texto.alinear(String.format("%s. %s", ingrediente.getId(), ingrediente.getNombre()), 0,
            ingrediente.getPrecioDeVenta()) + RESET + "\n";
      }
    }

    return mensaje;

  }

  public static String mostrarCanasta() {
    String mensaje = "PRODUCTOS EN LA CANASTA: \n";
    mensaje += Texto.centrar("PRODUCTOS\n");

    for (Producto producto : Producto.getBaseDatosProductos()) { // productos que le alcanza el dinero al cliente
      if (producto.getCosto() <= Cliente.getSesion().getPresupuesto()) {
        mensaje += GREEN + Texto.alinear(String.format("%s. %s", producto.getId(), producto.getNombre()),
            producto.getUnidades(), producto.getCosto()) + RESET + "\n";
      }
    }

    for (Producto producto : Producto.getBaseDatosProductos()) { // productos que no le alcanza el dinero al cliente
      if (producto.getCosto() > Cliente.getSesion().getPresupuesto()) {
        mensaje += RED + Texto.alinear(String.format("%s. %s", producto.getId(), producto.getNombre()),
            producto.getUnidades(), producto.getCosto()) + RESET + "\n";
      }
    }

    mensaje += Texto.centrar("INGREDIENTES\n");

    for (Ingrediente ingrediente : Ingrediente.getBaseDatosIngredientes()) {
      if (ingrediente.getPrecioDeVenta() < Cliente.getSesion().getPresupuesto()) {
        mensaje += GREEN + Texto.alinear(String.format("%s. %s", ingrediente.getId(), ingrediente.getNombre()), 0,
            ingrediente.getPrecioDeVenta()) + RESET + "\n";
      }
    }

    for (Ingrediente ingrediente : Ingrediente.getBaseDatosIngredientes()) {
      if (ingrediente.getPrecioDeVenta() < Cliente.getSesion().getPresupuesto()) {
        mensaje += RED + Texto.alinear(String.format("%s. %s", ingrediente.getId(), ingrediente.getNombre()), 0,
            ingrediente.getPrecioDeVenta()) + RESET + "\n";
      }
    }

    return mensaje;
  }

  public static void imprimirFactura(Recibo recibo) {
    System.out.println(Texto.centrar("                      ██████            "));
    System.out.println(Texto.centrar("          ████████████░░░░░░██          "));
    System.out.println(Texto.centrar("        ██░░░░░░░░░░░░██░░░░░░██████    "));
    System.out.println(Texto.centrar("      ██░░░░░░░░░░░░░░██░░░░░░██░░░░██  "));
    System.out.println(Texto.centrar("    ██░░░░░░░░░░░░░░░░░░██░░░░██░░░░░░██"));
    System.out.println(Texto.centrar("    ██░░░░░░░░░░░░░░░░░░░░██░░██░░░░░░██"));
    System.out.println(Texto.centrar("    ██░░░░░░░░░░░░░░░░░░░░██░░████████  "));
    System.out.println(Texto.centrar("  ██░░████░░░░░░░░░░░░░░░░██░░██        "));
    System.out.println(Texto.centrar("  ██░░░░░░████░░░░░░░░░░░░████          "));
    System.out.println(Texto.centrar("██░░░░░░░░░░░░████░░░░░░░░██            "));
    System.out.println(Texto.centrar("██░░░░░░░░░░░░░░░░██░░░░██              "));
    System.out.println(Texto.centrar("  ██░░░░░░░░░░░░░░██████                "));
    System.out.println(Texto.centrar("██░░████████░░░░░░██                    "));
    System.out.println(Texto.centrar("██░░░░░░░░░░██████                      "));
    System.out.println(Texto.centrar("  ██░░░░░░░░░░██                        "));
    System.out.println(Texto.centrar("██░░██████████                          "));
    System.out.println(Texto.centrar("██░░░░░░░░██                            "));
    System.out.println(Texto.centrar("  ██░░░░██                              "));
    System.out.println(Texto.centrar("    ████                                "));
    System.out.println("");
    System.out.println(String.format(""));
    System.out.println(String.format(Texto.centrar("POO Bakery")));
    System.out.println(String.format(Texto.centrar("DOMICILIOS 24 HORAS")));
    System.out.println(String.format(""));
    System.out.println(Texto.centrar(String.format("Factura Nro: %s", recibo.getIdRecibo())));
    System.out.println(Texto.centrar(String.format("Fecha y hora: %s", Recibo.formato.format(recibo.getFecha()))));
    System.out.println(Texto.centrar(String.format("Panadero que atendio su pedido: Mateo")));
    System.out.println(Texto.centrar(String.format("Ciudad: Medellin")));
    System.out.println(Texto.centrar(String.format("Cliente: %s", recibo.getCliente().getNombre())));
    System.out.println(Texto.centrar(String.format("Identificacion: %s", recibo.getCliente().getId())));
    System.out.println(Texto.centrar(String.format("")));
    System.out.println(Texto.centrar(String.format(Texto.centrar("DETALLE DE VENTA"))));
    System.out.println(" ");
    /*
     * System.out.println(Texto.alinear("Descripcion", "Cantidad", "Precio"));
     * //Reutilizar aqui la funcion de mateo
     * System.out.println("-".repeat(111));
     * 
     * int contador = 0; //aqui tendre que reutilizar el codigo de mateo en
     * gestionCompraMain
     * for(Canasta canasta: cliente.getCanastas()){
     * for(Map.Entry<Producto,Integer> item: canasta.getProductos().entrySet()){
     * contador ++;
     * System.out.println(Texto.alinear(item.getKey().getNombre(), item.getValue(),
     * item.getKey().getCosto()*item.getValue()));
     * }
     */
    System.out.println(Texto.centrar(String.format(Texto.centrar("DETALLE DE IMPUESTOS"))));// DESPUES TRABAJARE EN LA
                                                                                            // DEDUCCION DE IMPUESTOS
    System.out.println(Texto.centrar(String.format(Texto.centrar(""))));
    System.out.println(Texto.centrar(String.format("Total articulos comprados: %s", recibo.getIdRecibo())));
    System.out.println(Texto.centrar("EN POO BAKERY SOMOS EXPERTOS EN AHORRO:"));
    System.out.println(Texto.centrar(String.format("TU AHORRO HOY FUE DEL %s%", (recibo.getDescuento() * 100))));
    // System.out.println(Texto.centrar(String.format("EQUIVALENTE A: ")));
    // //colocar el total ahorrado aqui cuando este todo listo
    System.out.println(Texto.centrar("POO Bakery"));
    System.out.println(Texto.centrar("solo calidad"));
    System.out.println(Texto.centrar("Gracias por elegirnos"));
    System.out.println(String.format(""));
    System.out.println(Texto.centrar("▄▄▄▄▄▄▄  ▄ ▄▄ ▄▄▄▄▄▄▄"));
    System.out.println(Texto.centrar("█ ▄▄▄ █ ██ ▀▄ █ ▄▄▄ █"));
    System.out.println(Texto.centrar("█ ███ █ ▄▀ ▀▄ █ ███ █"));
    System.out.println(Texto.centrar("█▄▄▄▄▄█ █ ▄▀█ █▄▄▄▄▄█"));
    System.out.println(Texto.centrar("▄▄ ▄  ▄▄▀██▀▀ ▄▄▄ ▄▄ "));
    System.out.println(Texto.centrar("▄   ▀█▄▀ ▄█ ▄▄▀▀ █▄ █"));
    System.out.println(Texto.centrar("██▄ █▄▄ ▄██▀▄ ▄▀ █ ▄█"));
    System.out.println(Texto.centrar("▄▄▄▄▄▄▄ █▄▀▀ ▄  ▄ ▄▄▀"));
    System.out.println(Texto.centrar("█ ▄▄▄ █   ██▀▀▄▄█   █"));
    System.out.println(Texto.centrar("█ ███ █ ▀▄ ▀▄  ██▄█▀█"));
    System.out.println(Texto.centrar("█▄▄▄▄▄█ █▀▀▄▄▀▀▀█  ▄ "));
    System.out.println(String.format(""));
  }

  public static void menu(){
    do{
      UI.titulo();
      String strOpciones = "Escriba el numero correspondiente a la opcion que quiere elegir\n" +
              "0. Cerrar sesion\n" +
              "1. Agregar productos a la canasta de compras\n" +
              "2. Ver catalogo de productos e informacion adicional"+
              "3. Ver las facturas de mis compras pasadas\n";

      System.out.println(strOpciones);
      String eleccion = input.nextLine();
      boolean eleccionValida = true;

      do {
          switch (eleccion) {

              case "0": //cerrar sesion
                break;
              case "1": 
                  break;
              case "2":
                  break;
              // caso donde el cliente elije una opcion no valida
              default:
                  System.out.println("Usted escogio una opción que no estaba en la lista");
                  eleccionValida = false;
                  break;
          }
      } while (eleccionValida == true && Cliente.getSesion() != null);
    }while(true);

  }

  public static void cerrarSesion(){
      System.out.println("Esta seguro de que quiere cerrar su sesion?");
      System.out.println("Escriba 1 para si, escriba 0 para cancelar");
      eleccion = input.nextLine();
      switch (eleccion) {
          case "0":
              break;
          case "1":
              Cliente.setSesion(null);
              System.out.println("Su sesion ha sido finalizada");
              break;
          default:
              System.out.println("Usted escogio una opción que no estaba en la lista");
              break;
            }
    }

  public static void compras(){
    UI.mostrarOpciones();
    GestionCompra.gestionRecibirOrdenCanasta(Cliente.getSesion().getCanastaOrden());
    System.out.println("Desea continuar con la facturación? escriba s para si, escriba n para no, escriba 0 para volver al menu.");
    eleccion = input.nextLine();
    switch (eleccion){
        case "s":
            Recibo factura = Panaderia.generarRecibo();
            UI.imprimirFactura(factura);

            break;
        case "n":
            break;
        case "0":
            System.out.println("Volviendo al menu");
        default:
            System.out.println("Elija una opcion valida");
            break;
    }
  }

  public static void domicilio(){
                System.out.println("Desea que le enviemos su pedido a domicilio? escriba s para si, n para no, escriba 0 para volver al menu");
            eleccion = input.nextLine();
            switch (eleccion){
                case "s": //IMPORTANTE ESCRIBIR AQUI LO DEL PROCESO DE DOMICILIO

                    break;
                case "n":
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Escriba una opcion valida");
                    break;
            }
  }

  public static void verCatalogoDescripcion(){
    UI.mostrarOpciones();
    do{
        System.out.println("Seleccione un producto (Escriba el numero de id), o escriba 0 para salir ");
        eleccion = input.nextLine();
        if (eleccion == "0"){
            break;
        } else{
        System.out.println(Producto.obtenerObjetoPorId(eleccion).getNutrientes(Producto.obtenerObjetoPorId(eleccion).getNombre()));
        }
        break;
    } while(true);
  }

  public static void historialRecibos(Cliente cliente){
    for(Recibo recibo: cliente.getRecibos()){
      imprimirFactura(recibo);
      System.out.println(" ");
      System.out.println(" ");
      System.out.println(" ");
      System.out.println(" ");
    }
  }
}

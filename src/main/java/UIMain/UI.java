package UIMain;

import java.util.Scanner;
import java.lang.Thread;
import java.util.ArrayList;

import gestorAplicacion.humanos.Cliente;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.gestion.Recibo;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.gestion.Inventario;
import gestorAplicacion.humanos.Domiciliario;

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
  public static void mostrarOpciones(Inventario inventario) {

    String mensaje = "PRODUCTOS DISPONIBLES PARA COMPRAR:\n";
    mensaje += Texto.centrar("PRODUCTOS") + "\n";
    for (Producto producto : Producto.getBaseDatosProductos()) { // productos que le alcanza el dinero al cliente
      if (producto.getCosto() <= Cliente.getSesion().getPresupuesto()) {
        mensaje += GREEN + Texto.alinear(String.format("%s. %s", producto.getId(), producto.getNombre()),
            inventario.verificarCantidadProductoPorId(producto.getId()), producto.getCosto()) + RESET + "\n";
      }
    }

    for (Producto producto : Producto.getBaseDatosProductos()) { // productos que no le alcanza el dinero al cliente
      if (producto.getCosto() > Cliente.getSesion().getPresupuesto()) {
        mensaje += RED + Texto.alinear(String.format("%s. %s", producto.getId(), producto.getNombre()),
            inventario.verificarCantidadProductoPorId(producto.getId()), producto.getCosto()) + RESET + "\n";
      }
    }
    mensaje += Texto.centrar("INGREDIENTES") + "\n";
    for (Ingrediente ingrediente : Ingrediente.getBaseDatosIngredientes()) {
      if (ingrediente.getPrecioDeVenta() < Cliente.getSesion().getPresupuesto()) {
        mensaje += GREEN + Texto.alinear(String.format("%s. %s", ingrediente.getId(), ingrediente.getNombre()),
            inventario.verificarCantidadIngredientePorId(ingrediente.getId()),
            ingrediente.getPrecioDeVenta()) + RESET + "\n";
      }
    }

    for (Ingrediente ingrediente : Ingrediente.getBaseDatosIngredientes()) {
      if (ingrediente.getPrecioDeVenta() > Cliente.getSesion().getPresupuesto()) {
        mensaje += RED + Texto.alinear(String.format("%s. %s", ingrediente.getId(), ingrediente.getNombre()),
            inventario.verificarCantidadIngredientePorId(ingrediente.getId()),
            ingrediente.getPrecioDeVenta()) + RESET + "\n";
      }
    }
    System.out.println(mensaje);

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
    GestionCompra.mostrarCanasta(recibo.getCanasta());
    System.out.println("");
    System.out.println("");
    System.out.println("=".repeat(55));
    // System.out.println(Texto.alinear("Domicilio",String.valueOf(recibo.getCostoDomicilio())));
    System.out
        .println(Texto.alinear("Descuento", " ", "-" + String.valueOf(recibo.getSubtotal() * recibo.getDescuento())));
    System.out.println(Texto.alinear("****TOTAL*****", recibo.getTotal()));
    // System.out.println(Texto.alinear("Domicilio", ))
    System.out.println("");
    System.out.println("-".repeat(55));
    System.out.println(Texto.centrar(String.format(Texto.centrar("DETALLE DE IMPUESTOS"))));
    System.out.println("-".repeat(55));
    System.out.println(Texto.alinear("IVA", recibo.getTotal()));
    System.out.println("-".repeat(55));
    System.out.println("");
    System.out.println(Texto.centrar(String.format(Texto.centrar(""))));
    System.out.println(Texto.centrar(String.format("Total articulos comprados: %s", recibo.getIdRecibo())));
    System.out.println(Texto.centrar("EN POO BAKERY SOMOS EXPERTOS EN AHORRO:"));
    System.out.println(Texto.centrar(String.format("TU AHORRO HOY FUE DEL %s%", (recibo.getDescuento() * 100))));
    // System.out.println(Texto.centrar(String.format("EQUIVALENTE A: ")));
    // //colocar el total ahorrado aqui cuando este todo listo
    System.out.println(Texto.centrar("POO Bakery"));
    System.out.println(Texto.centrar("solo calidad"));
    System.out.println(Texto.centrar("Gracias por tu compra"));
    System.out.println(Texto.centrar("No se permiten devoluciones"));
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

  public static void menu() { // Ignorar este metodo, esto ira en main
    do {
      titulo();
      String strOpciones = "Escriba el numero correspondiente a la opcion que quiere elegir\n" +
          "0. Cerrar sesion\n" +
          "1. Agregar productos a la canasta de compras\n" +
          "2. Ver catalogo de productos e informacion adicional" +
          "3. Ver las facturas de mis compras pasadas\n";

      System.out.println(strOpciones);
      String eleccion = input.nextLine();
      boolean eleccionValida = true;

      do {
        switch (eleccion) {

          case "0": // cerrar sesion
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
    } while (true);

  }

  // cerrar sesion del usuario
  public static void cerrarSesion() { // Este metodo esta listo
    System.out.println("");
    System.out.println("Seguro de que quiere cerrar su sesion?");
    System.out.println("Escriba 1 para si, escriba 0 para cancelar");
    eleccion = input.nextLine();
    switch (eleccion) {
      case "0":
        break;
      case "1":
        Cliente.setSesion(null);
        System.out.println("Su sesion ha sido finalizada");
        try {
          Thread.sleep(500);
        } catch (Exception e) {
        }
        break;
      default:
        System.out.println("Usted escogio una opcion que no estaba en la lista");
        break;
    }
  }

  // Agregar cosas al carrito de compras
  public static boolean compras(Panaderia panaderia) { // este metodo esta listo, falta corregir mostrarOpciones y
    // mostrarCanasta
    continuar = true;
    Scanner input8 = new Scanner(System.in); //NO TOCAR
    
    if (Cliente.getSesion().getCanastaOrden() == null) {
      Cliente.getSesion().crearCanastaNueva();
    }

    GestionCompra.gestionRecibirOrdenCanasta(Cliente.getSesion().getCanastaOrden(), panaderia);
    System.out.println("");
    System.out.println("Asi queda su canasta:");
    GestionCompra.mostrarCanasta(Cliente.getSesion().getCanastaOrden());
    if (Cliente.getSesion().getCanastaOrden().getProductosEnLista().size() == 0
        && Cliente.getSesion().getCanastaOrden().getIngredientesEnLista().size() == 0
        && Cliente.getSesion().getCanastaOrden().getKitsEnLista().size() == 0) {
      System.out.println("Su canasta esta vacia");

      return false;
    }
    System.out.println(
        "Desea continuar con la facturacion y el domicilio? escriba s para si, escriba n para no, escriba 0 para volver al menu.");
    eleccion = input.nextLine();
    switch (eleccion) {
      case "s":
        break;
      case "n":
        continuar = false;
        break;
      case "0":
        System.out.println("Volviendo al menu");
        continuar = false;
      default:
        System.out.println("Elija una opcion valida");
        continuar = false;
        break;
    }
    return continuar;
  }

  // Aqui se procesa todo lo que tiene que ver con el envio a domicilio del
  // cliente
  public static void domicilio(Cliente cliente) { // pendiente por terminar este metodo
    Domiciliario domiciliario = cliente.getPanaderia().domiciliarioAleatorio();
    cliente.setDomiciliario(domiciliario);
    if (continuar = true) {
      System.out.println(
          "Desea que le enviemos su pedido a su domicilio? escriba s para si, n para no, escriba 0 para volver al menu");
      eleccion = input.nextLine();
      switch (eleccion) {
        case "s": // Terminar de ver esto

          boolean suceso = cliente.verificarDireccion();

          while (suceso = false) {

            String direccion = "";
            String ciudad = "";

            System.out.println("Por favor ingrese su direccion y su ciudad: ");
            System.out.println("Tenga en cuenta que las ciudades para las cuales tenemos domicilio son: ");
            System.out.println("Bogota");
            System.out.println("Medellin");
            System.out.println("Envigado");
            System.out.println("Itagui");

            direccion = input.nextLine();
            ciudad = input.nextLine();

            suceso = cliente.establecerDomicilioValido(direccion, ciudad);

            if (suceso == false) {

              System.out.println("Por favor ingrese una ciudad valida: ");
            }

            else {

              System.out.println("Su direccion ha sido validada y se ha asignado correctamente");
            }

          }
          break;
        case "n":
          break;
        case "0":
          continuar = false;
          break;
        default:
          System.out.println("Escriba una opcion valida");
          break;
      }
    }
  }

  public static void facturacion(Cliente cliente, Panaderia panaderia) { // pendiente por terminar este metodo
    if (continuar == true) {
      Recibo recibo = new Recibo(cliente, cliente.getCanastaOrden(), cliente.getDomiciliario());
      imprimirFactura(recibo);
      System.out.println("Desea pagar la factura de su pedido? escriba s para si, escriba n para no");
      eleccion = input.nextLine();
      switch (eleccion) {
        case "s":
          if(panaderia.facturar(recibo)){
            System.out.println("Su pago ha sido efectuado con exito");
            System.out.println("El nuevo saldo de su cuenta es: "+cliente.getPresupuesto());
          } else{
            while(true){
              System.out.println("No tienes suficiente dinero, escriba la cantidad de dinero que desea ingresar a su cuenta:");
              meterPlata(cliente);
              if(panaderia.facturar(recibo)){
                break;
              }
            }
          }
          cliente.enviarCanastasADomicilio(cliente.getCanastaEnMano());
          break;

        case "n":
          break;

        default:
          break;
      }
    }
  }

  // En este metodo van a ir todas las notificaciones del pedido (catastrofes o si
  // la entrega fue exitosa)
  public static void concluirOrden() { // pendiente por terminar este metodo
    Cliente.getSesion().notaCocineros();

    if (continuar == true) {
      System.out.println("Su pedido ha sido entregado con exito");
    }

    System.out.println(
        "Esperamos que haya disfrutado su odren, desea publicar su canasta para que otros usuarios puedan verla? escriba s para si y n para no");

    eleccion = input.nextLine();

    if (eleccion.equals("s")) {

      System.out.println(
          "Si desea solo calificar la canasta escriba 1, si desea solo dejar una descripción escriba 2, si desea dejar las dos escriba 3 y si solo desea publicarla escriba 0");

      String decision = input.nextLine();

      switch (decision) {

        case "1":
          break;

        case "2":
          break;

        case "3":
          break;

        case "0":
          break;

        default:
          break;
      }
    }

    else {

      System.out.println("Muchas  gracias por comprar a Poo Bakery");
    }
  }

  // Aqui se mostrara la informacion nutricional y otros
  public static void verCatalogoDescripcion(Panaderia panaderia) {
    if (Cliente.getSesion().verificarPresupuesto() == false) {
      System.out.println("");
      System.out.println("No tienes presupuesto agregado, para ver el catalogo agrega un presupuesto");
      while (Cliente.getSesion().verificarPresupuesto() == false) {
        System.out.println("");
        System.out.println("Ingrese su presupuesto: ");
        try {
          eleccion = input.nextLine();
          double presupuesto = Double.parseDouble(eleccion);
          Cliente.getSesion().setPresupuesto(presupuesto);
        } catch (Exception e) {
          System.out.println("");
          System.out.println("Ingrese un presupuesto valido:");
        }
      }
    }
    mostrarOpciones(panaderia.getInventario());
    // sugerencias

    while (true) {
    System.out.println("");
    System.out.println("Escriba el numero de ID de un producto para ver su informacion, o escriba 0 para salir: ");
    eleccion = input.nextLine();

    if (eleccion.equals("0")) {
        break;
    }

    try {
        int id = Integer.parseInt(eleccion);
        if (id <= Producto.getBaseDatosProductos().size() + Ingrediente.getBaseDatosIngredientes().size()) {
            System.out.println("");
            if (Producto.verificacionExistenciaPorId(eleccion)) {
                System.out.println(Producto.obtenerObjetoPorId(eleccion).getNutrientes(Producto.obtenerObjetoPorId(eleccion).getNombre()));
                break;
            } else if (Ingrediente.verificacionExistenciaPorId(eleccion)) {
                System.out.println(Ingrediente.obtenerObjetoPorId(eleccion).getNutrientes(Ingrediente.obtenerObjetoPorId(eleccion).getNombre()));
                break;
            }
        } else {
            System.out.println("Escriba un ID valido");
        }
    } catch (NumberFormatException e) {
        System.out.println("Caracter invalido, vuelva a intentar.");
    }
}

  }

  public static void verRanking(Panaderia panaderia, Cliente cliente) {
    GestionRankings.mostrarRankingProductos();
    GestionRankings.mostrarRankingIngredientes();
    GestionRankings.mostrarRankingDomiciliarios(panaderia);
    GestionRankings.mostrarRankingCocineros(panaderia);
    GestionRankings.mostrarRankingCanastas(panaderia);
    System.out.println("");
    System.out.println("Has encontrado lo mejor de PooBakery");
    System.out.println("Puedes agregar estos productos en el menu opcion 1");
    System.out.println("Puedes clonar nuestras canastas de compras mejor valoradas ingresando su respectivo id");
    System.out.println("Escriba 0 para salir");
    eleccion = input.nextLine();
    if (eleccion.equals("0")) {
    } else {
      while (true) {
        if (cliente.crearCanastaPorHistorial(eleccion) != null) {
          cliente.setCanastaOrden(cliente.crearCanastaPorHistorial(eleccion));
          System.out.println("La canasta ha sido clonada con exito, aqui esta su orden actual:");
          GestionCompra.mostrarCanasta(cliente.getCanastaOrden());

        }
      }
    }
  }

  // Este metodo imprime todos los recibos que se le haya entregado antes al
  // cliente
  public static void historialRecibos(Cliente cliente) {
    if (cliente.getRecibos().size() == 0) {
      System.out.println(" ");
      System.out.println("No tienes recibos");
    } else {
      for (Recibo recibo : cliente.getRecibos()) {
        imprimirFactura(recibo);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
      }
    }
  }

  // Metodo para que el cliente cambie su contraseña
  public static void cambiarClave(Cliente cliente) {
    System.out.println("");
    System.out.println("Ingrese su nueva clave: ");
    eleccion = input.nextLine();
    cliente.setContrasena(eleccion);
    System.out.println("");
    System.out.println("Clave cambiada con exito :D");
  }

  // Metodo para que el cliente pueda ingresar dinero a su cuenta (Aumentar el
  // presupuesto)
  public static void meterPlata(Cliente cliente) {

    double cantidad = 0;

    while (true) {
      System.out.println("");
      System.out.println("Por favor ingrese la cantidad de dinero que desea ingresar:");
      try {
        cantidad = input.nextDouble();
        System.out.println("Dinero actualizado con exito");
        break;
      } catch (Exception e) {
        System.out.println("Por favor ingrese un id valido (solo numeros)");
      }
    }

    cliente.setPresupuesto(cantidad);

  }

  // Metodo para que el cliente valide el tipo de cliente que es para aplicar a
  // descuentos especiales
  public static void validarTipoCliente(Cliente cliente) {

    System.out.println("");
    System.out.println("Los tipos de clientes a los cuales les damos un descuento especial son: ");
    System.out.println("");
    System.out.println("Estudiante");
    System.out.println("Profesor");
    System.out.println("Senior");
    System.out.println("Empleado");
    System.out.println("");
    System.out.println("Si no hace parte de ninguno de estos escriba ninguno");
    System.out.println("Por favor escriba su tipo de cliente: ");

    eleccion = input.nextLine();

    boolean suceso = cliente.establecerDescuentoPorTipoValido(eleccion);

    if (suceso == true) {

      System.out.println("Su descuento ha sido validado exitosamente");
    }

    else {

      System.out.println("El tipo de cliente que ingreso no tiene ningun descuento asignado");
      cliente.establecerDescuentoPorTipoValido("NINGUNO");
    }
  }

  public static void seleccionCanastasExistentes() {

  }

  // Aqui el cliente puede ver el historial de las cosas que ha pedido antes, para
  // pedirlas otra vez facilmente
  public static void historialOrdenes(Cliente cliente) {
    System.out.println("");
    System.out.println("Historial de ordenes:");
    ArrayList<Canasta> historial = cliente.getHistorialOrdenes();

    if (historial.size() == 0) {
      System.out.println("No tienes historial de ordenes");
      return;
    }

    if (historial.size() < 5) {
      for (Canasta canasta : historial) {
        System.out.println("Canasta de la factura con ID " + canasta.getIdentificador());
        GestionCompra.mostrarCanasta(canasta);
        System.out.println("");
      }
    }

    else {
      int startIndex = Math.max(historial.size() - 5, 0);
      for (int i = startIndex; i < historial.size(); i++) {
        Canasta canasta = historial.get(i);
        System.out.println("Canasta de la factura con ID " + canasta.getIdentificador());
        GestionCompra.mostrarCanasta(canasta);
        System.out.println("");
      }
    }
    System.out.println("");
    System.out.println(
        "Te recomendamos la canasta del dia con ID " + cliente.getPanaderia().getCanastaDelDia().getIdentificador());

    System.out.println("");
    System.out.println("Escriba el ID de la canasta que quiere agregar: ");
    eleccion = input.nextLine();
    int limitebajo = cliente.getCantidadOrdenes() - 5;
    int idEleccion;

    try {
    idEleccion = Integer.parseInt(eleccion); // Convertir la entrada a un entero
    } catch (NumberFormatException e) {
    System.out.println("La entrada no es un número válido.");
    return;
    } 

    if (idEleccion >= limitebajo && idEleccion < cliente.getCantidadOrdenes() && idEleccion >= 0) {
    // El ID de la canasta está dentro de los últimos 5 registros
    Canasta canasta = cliente.getHistorialOrdenes().get(idEleccion);
    cliente.setCanastaOrden(canasta);

    } else {
    System.out.println("La canasta que escogió no está en su historial o el ID no es válido.");
    }

  }

  public static void modificarDireccion(Cliente cliente) {

    Scanner input = new Scanner(System.in);
    boolean suceso = false;

    System.out.println("Por favor ingrese su direccion y la ciudad en la que esta ubicado");
    System.out.println("Tenga en cuenta que las ciudades para las cuales tenemos domicilio son: ");
    System.out.println("");
    System.out.println("Bogota");
    System.out.println("Medellin");
    System.out.println("Envigado");
    System.out.println("Itagui");
    System.out.println("");

    String direccion = input.nextLine();
    String ciudad = input.nextLine();

    suceso = cliente.establecerDomicilioValido(direccion, ciudad);

    while (true) {

      if (suceso == false) {

        System.out.println("Por favor ingrese una ciudad valida");
        ciudad = input.nextLine();
        suceso = cliente.establecerDomicilioValido(direccion, ciudad);

      }

      else {

        System.out.println("Direccion validada con exito");
        break;
      }

    }
  }
}
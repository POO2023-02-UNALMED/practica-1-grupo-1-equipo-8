import UIMain.Texto;
public class MainPruebas {
    public static void main (String[] args){
        //estos codigos ansi son para cambiar el color de la terminal

        String BLACK = "\u001B[30m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BLUE = "\u001B[34m";
        String PURPLE = "\u001B[35m";
        String CYAN = "\u001B[36m";
        String WHITE = "\u001B[37m";
        String RESET = "\u001B[0m";
        String mensaje = "";
        mensaje += GREEN+String.format("%s. %s", 3,3)+RESET+"\n";
        System.out.println(mensaje);
        

            /*Panaderia panaderia = new Panaderia();
    	FileOutputStream fileOutputStream = new FileOutputStream("panaderia.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(panaderia);
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("panaderia.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        objectInputStream.readObject();
        Panaderia panaderia2 = (Panaderia) objectInputStream.readObject();
        objectInputStream.close();*/

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
        System.out.println(Texto.centrar(String.format("Factura Nro: %s", 1012343)));
        System.out.println(Texto.centrar(String.format("Fecha y hora: ")));
        System.out.println(Texto.centrar(String.format("Panadero que atendio su pedido: Mateo")));
        System.out.println(Texto.centrar(String.format("Ciudad: Medellin")));
        System.out.println(Texto.centrar(String.format("Cliente: %s", "Richard")));
        System.out.println(Texto.centrar(String.format("Identificacion: %s", 1013457676)));
        System.out.println(Texto.centrar(String.format("")));
        System.out.println(Texto.centrar(String.format(Texto.centrar("DETALLE DE VENTA"))));
        System.out.println(" ");
        System.out.println("_".repeat(55));
        System.out.println(Texto.alinear("Nombre", "Cantidad", "Precio"));
        System.out.println("_".repeat(55));
        System.out.println(Texto.alinear("Pan de queso","2", "12500"));
        System.out.println(Texto.alinear("croasan","2", "7000"));
        System.out.println(Texto.alinear("Frijoles","2", "12000"));
        System.out.println(Texto.alinear("focache benovesa","35", "750000"));
        System.out.println(Texto.alinear("Pan de queso","2", "12500"));
        System.out.println(Texto.alinear("croasan","2", "7000"));
        System.out.println(Texto.alinear("Frijoles","2", "12000"));
        System.out.println(Texto.alinear("focache benovesa","35", "750000"));
        System.out.println("_".repeat(55));
        System.out.println(" ");
        System.out.println(Texto.centrar("POO Bakery"));
        System.out.println(Texto.centrar("solo calidad"));
        System.out.println(Texto.centrar("Gracias por elegirnos"));
        System.out.println(String.format(""));
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
        System.out.println(" ");

    }
}

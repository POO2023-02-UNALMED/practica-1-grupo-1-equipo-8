import UIMain.Texto;
public class MainPruebas {
    public static void main (String[] args){
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
        System.out.println(Texto.alinear("Nombre", "Cantidad", "Precio"));
        System.out.println("");
        System.out.println(Texto.alinear("Pan de queso","2", "12500"));
        System.out.println(Texto.alinear("croasan","2", "7000"));
        System.out.println(Texto.alinear("Frijoles","2", "12000"));
        System.out.println(Texto.alinear("focache benovesa","35", "750000"));
        System.out.println(Texto.alinear("Pan de queso","2", "12500"));
        System.out.println(Texto.alinear("croasan","2", "7000"));
        System.out.println(Texto.alinear("Frijoles","2", "12000"));
        System.out.println(Texto.alinear("focache benovesa","35", "750000"));
        System.out.println(Texto.centrar("POO Bakery"));
        System.out.println(Texto.centrar("Bienvenido a nuestra panaderia"));
        System.out.println("                                                                                                       d8'     ".length());
    }
}

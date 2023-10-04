package baseDatos;
import java.io.*;
import gestion.Panaderia;

public class EscritoLector {
    static File archivo = new File("panaderia.txt");

    public static void main(String[] args){
        try {
            Panaderia panaderia = new Panaderia();
            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(panaderia);
            objectOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream(archivo);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectInputStream.readObject();
            Panaderia panaderia2 = (Panaderia) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

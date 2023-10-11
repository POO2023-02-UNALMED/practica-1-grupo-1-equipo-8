package baseDatos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gestorAplicacion.gestion.Panaderia;

public class Serializador {
        public static void guardarPanaderia(Panaderia panaderia) {
        try (FileOutputStream fileOut = new FileOutputStream("src/main/java/baseDatos/temp/panaderia.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(panaderia);
            guardarValoresEstaticos(out);
            System.out.println("La informacion de la panaderia se ha guardado en panaderia.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Panaderia cargarPanaderia() {
        Panaderia panaderia = null;
        try (FileInputStream fileIn = new FileInputStream("src/main/java/baseDatos/temp/panaderia.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn)) {
            panaderia = (Panaderia) in.readObject();
            cargarValoresEstaticos(in);
            System.out.println("La informacion de la panaderia se ha cargado desde panaderia.ser");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return panaderia;
    }

    public static void guardarValoresEstaticos(ObjectOutputStream stream) throws IOException {
        //stream.writeBoolean();
    }

    public static void cargarValoresEstaticos(ObjectInputStream stream) throws IOException {
        //Panaderia.prueba2 = stream.readBoolean();
    }
}

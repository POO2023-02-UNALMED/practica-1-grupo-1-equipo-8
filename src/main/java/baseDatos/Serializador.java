package baseDatos;

import java.util.HashMap;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.comida.Ingrediente;
import gestorAplicacion.comida.Producto;
import gestorAplicacion.humanos.Cliente;
import gestorAplicacion.gestion.Canasta;
import gestorAplicacion.humanos.Trabajador;
import gestorAplicacion.humanos.Cocinero;
import gestorAplicacion.humanos.Domiciliario;


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
        stream.writeObject(Panaderia.getInvIngredientes());
        stream.writeObject(Panaderia.getInvProductos());
        stream.writeObject(Panaderia.getTrabajadores());
        stream.writeObject(Panaderia.getCocineros());
        stream.writeObject(Panaderia.getDomiciliarios());
        stream.writeObject(Panaderia.getClientes());
        stream.writeDouble(Panaderia.getDinero());
        stream.writeObject(Panaderia.getCanastaDelDia());
        stream.writeDouble(Panaderia.getValorDeudas());
        stream.writeBoolean(Panaderia.isEnQuiebra());
        stream.writeInt(Ingrediente.getCantidadIngredientes());
        stream.writeInt(Producto.getCantidadProductos());
    }

    public static void cargarValoresEstaticos(ObjectInputStream stream) throws IOException {
        try {
            Panaderia.setInvIngredientes((HashMap<Ingrediente, Integer>) stream.readObject());
            Panaderia.setInvProductos((HashMap<Producto, Integer>) stream.readObject());
            Panaderia.setTrabajadores((ArrayList<Trabajador>) stream.readObject());
            Panaderia.setCocineros((ArrayList<Cocinero>) stream.readObject());
            Panaderia.setDomiciliarios((ArrayList<Domiciliario>) stream.readObject());
            Panaderia.setClientes((ArrayList<Cliente>) stream.readObject());
            Panaderia.setDinero(stream.readDouble());
            Panaderia.setCanastaDelDia((Canasta) stream.readObject());
            Panaderia.setValorDeudas(stream.readDouble());
            Panaderia.setEnQuiebra(stream.readBoolean());
            Ingrediente.setCantidadIngredientes(stream.readInt());
            Producto.setCantidadProductos(stream.readInt());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
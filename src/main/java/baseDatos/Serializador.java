package baseDatos;

import java.util.HashMap;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gestorAplicacion.gestion.Panaderia;
import gestorAplicacion.gestion.Recibo;
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
        stream.writeObject(Panaderia.getCanastaDelDia());
        stream.writeObject(Ingrediente.getBaseDatosIngredientes());
        stream.writeInt(Ingrediente.getCantidadIngredientesUnicos());
        stream.writeObject(Ingrediente.getTopMasVendidos());
        stream.writeObject(Producto.getBaseDatosProductos());
        stream.writeInt(Producto.getCantidadProductosUnicos());
        stream.writeObject(Producto.getTopMasVendidos());
        stream.writeInt(Recibo.getTotalFacturas());
    }

    public static void cargarValoresEstaticos(ObjectInputStream stream) throws IOException {
        try {
            Panaderia.setCanastaDelDia((Canasta) stream.readObject());
            Ingrediente.setBaseDatosIngredientes((ArrayList<Ingrediente>) stream.readObject());
            Ingrediente.setCantidadIngredientesUnicos(stream.readInt());
            Ingrediente.setTopMasVendidos((ArrayList<Ingrediente>) stream.readObject());
            Producto.setBaseDatosProductos((ArrayList<Producto>) stream.readObject());
            Producto.setCantidadProductosUnicos(stream.readInt());
            Producto.setTopMasVendidos((ArrayList<Producto>) stream.readObject());
            Recibo.setTotalFacturas(stream.readInt());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
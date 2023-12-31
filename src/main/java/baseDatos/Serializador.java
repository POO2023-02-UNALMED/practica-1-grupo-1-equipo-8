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


/**
 * La clase Serializador se encarga de guardar y cargar la información de la panadería en un archivo serializado.
 * También se encarga de guardar y cargar los valores estáticos de las clases Panaderia, Ingrediente, Producto y Recibo.
 */
public class Serializador {
    /**
     * Guarda la información de la panadería en un archivo serializado.
     * @param panaderia La panadería a guardar.
     */
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

    /**
     * Carga la información de la panadería desde un archivo serializado.
     * @return La panadería cargada.
     */
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

    /**
     * Guarda los valores estáticos de las clases Panaderia, Ingrediente, Producto y Recibo en un ObjectOutputStream.
     * @param stream El ObjectOutputStream donde se guardarán los valores estáticos.
     * @throws IOException Si ocurre un error al escribir en el ObjectOutputStream.
     */
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

    /**
     * Carga los valores estáticos de las clases Panaderia, Ingrediente, Producto y Recibo desde un ObjectInputStream.
     * @param stream El ObjectInputStream donde se cargarán los valores estáticos.
     * @throws IOException Si ocurre un error al leer del ObjectInputStream.
     */
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
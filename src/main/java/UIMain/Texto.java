package UIMain;

import java.util.ArrayList;
import java.util.List;

public class Texto {

    /*Este metodo es para alinear 3 cadenas de texto en 3 columnas
    Esto le da un aspecto ordenado a las listas de compras, 
    y las listas de productos mostradas al cliente
    */
    public static String alinear(String a1, String a2, String a3){
        List <String> textos = new ArrayList<String>();
        List <Integer> espaciados = new ArrayList<Integer>();
        String s = "";
        espaciados.add(50);
        espaciados.add(30);
        espaciados.add(30);
        textos.add(a1);
        textos.add(a2);
        textos.add(a3);

        for(int i = 0 ; i<3; i++){
            s += textos.get(i)+" ".repeat(espaciados.get(i)-textos.get(i).length());
        }
        return s;
    }

    //Metodo alinear sobrecargado necesario para clase recibo
    public static String alinear(String s1, Integer i1, double d1){
        String s2 = i1.toString();
        String s3 = String.valueOf(d1);
        List <String> textos = new ArrayList<String>();
        List <Integer> espaciados = new ArrayList<Integer>();
        String s = "";
        espaciados.add(50);
        espaciados.add(30);
        espaciados.add(30);
        textos.add(s1);
        textos.add(s2);
        textos.add(s3);

        for(int i = 0 ; i<3; i++){
            s += textos.get(i)+" ".repeat(espaciados.get(i)-textos.get(i).length());
        }
        return s;
    }

    public static String alinear(String s1, double d1){
        return alinear(s1, " ", String.valueOf(d1));
    }

    //Este metodo sirve para centrar texto
    public static String centrar(String a1){
        String s = "";
        int espaciado = 111;
        s += " ".repeat((espaciado - a1.length())/2)+a1+" ".repeat((espaciado - a1.length())/2);
        return s;
    }

}

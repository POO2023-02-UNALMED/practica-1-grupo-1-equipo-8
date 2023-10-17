import UIMain.Texto;

public class testRichard {
    public static void main(String[] args){
        String m = String.format(""+
        " _______________________________________________________ \n"+
        "|"+Texto.centrar("Producto: ")+" |\n"+
        "| Porción: 2 galletas (30 g)                            |\n"+
        "| Porciones por envase: 10                              |\n"+
        "|_______________________________________________________| \n"+
        "| Información nutricional por porción                   |\n"+
        "|______________________ ________ _______________________| \n"+
        "| Energía              | 140 kcal| 7%% del valor diario  |\n"+
        "|______________________ ________ _______________________|\n"+
        "| Grasa total          | 6 g     | 9%% del valor diario  |\n"+
        "| _ Grasa saturada     | 3 g     | 15%% del valor diario |\n"+
        "| _ Grasa trans        | 0 g     |                      |\n"+
        "|______________________ ________ _______________________| \n"+
        "| Colesterol           | 0 mg    | 0%% del valor diario  |\n"+
        "|______________________ ________ _______________________| \n"+
        "| Sodio                | 75 mg   | 3%% del valor diario  |\n"+
        "|______________________ ________ _______________________| \n"+
        "| Carbohidratos totales| 20 g    | 7%% del valor diario  |\n"+
        "| _ Fibra dietética    | 1 g     | 4%% del valor diario  |\n"+
        "| _ Azúcares           | 10 g    |                      |\n"+
        "|______________________ ________ _______________________| \n"+
        "| Proteína             | 2 g     |                      |\n"+
        "|______________________ ________ _______________________| \n"+
        "| Vitamina A           | 0%% del valor diario            |\n"+
        "| Vitamina C           | 0%% del valor diario            |\n"+
        "| Calcio               | 2%% del valor diario            |\n"+
        "| Hierro               | 6%% del valor diario            |\n"+
        "|_______________________________________________________| \n"+
        "");
        System.out.println(m);
    }
}

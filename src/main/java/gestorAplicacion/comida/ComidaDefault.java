package gestorAplicacion.comida;

import UIMain.Texto;

/**
 * Interfaz que define las tarifas y métodos por defecto para una comida.
 */
public interface ComidaDefault {
    /**
     * Tarifa por defecto para el servicio a domicilio.
     */
    final int tarifaDomicilio = 2000;
    /**
     * Tarifa por defecto para las ganancias.
     */
    final double tarifaGanancias = 1.7;
    /**
     * Tarifa por defecto para los impuestos.
     */
    final double tarifaImputestos = 0.19;

    /**
     * Obtiene la descripción por defecto de la comida.
     * @return La descripción por defecto de la comida.
     */
    default public String getDescripcion(){
        return "Ahora mismo no tenemos una descripcion para este producto, pero te invitamos a probarlo.";
    }

    /**
     * Obtiene la información nutricional por porción de la comida.
     * @param nombre El nombre de la comida.
     * @return La información nutricional por porción de la comida.
     */
    default public String getNutrientes(String nombre){
        String m = String.format(""+
        " _______________________________________________________ \n"+
        "|"+Texto.centrar(String.format("Producto: ", nombre))+" |\n"+
        "| Porcion: 30 g                                         |\n"+
        "|_______________________________________________________| \n"+
        "| Informacion nutricional por porcion                   |\n"+
        "|______________________ ________ _______________________| \n"+
        "| Energia              | 140 kcal| 7%% del valor diario  |\n"+
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
        "| _ Fibra dietetica    | 1 g     | 4%% del valor diario  |\n"+
        "| _ Azucares           | 10 g    |                      |\n"+
        "|______________________ ________ _______________________| \n"+
        "| Proteina             | 2 g     |                      |\n"+
        "|______________________ ________ _______________________| \n"+
        "| Vitamina A           | 0%% del valor diario            |\n"+
        "| Vitamina C           | 0%% del valor diario            |\n"+
        "| Calcio               | 2%% del valor diario            |\n"+
        "| Hierro               | 6%% del valor diario            |\n"+
        "|_______________________________________________________| \n"+
        "");
        return m;
    }

}

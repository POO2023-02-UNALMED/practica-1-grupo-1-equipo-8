package gestorAplicacion.comida;

public interface ComidaDefault {
    final int tarifaDomicilio = 2000;
    final int tarifaGanancias = 3;
    final double tarifaImputestos = 0.19;

    default public String getDescripcion(){
        return "Ahora mismo no tenemos una descripcion para este producto, pero te invitamos a probarlo.";
    }

    default public String getNutrientes(){
        return
    }

}

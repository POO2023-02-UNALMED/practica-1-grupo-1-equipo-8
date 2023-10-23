package UIMain;

public class GestionConseguirIngredientes {

  public static void lecturaRobo(boolean suceso) {
	  
	  if (suceso == true) {
		  
		  System.out.println("Han robado al trabajador que iba por los ingredientes para su pedido, pedimos disculpas por el atraso, enviaremos a alguien mas por la compra");
		  
	  }
	  
	  else {
		  
		  System.out.println("El trabajador ha regresado con los ingredientes con su pedido, por favor espere a que preparemos su pedido");
		  
	  } 
  }
  
  public static void lecturaQuiebra(boolean suceso) {
	  
	  if (suceso == true) {
		  
		  System.out.println("La panadería no tenía dinero para realizar su pedido y ha entrado en quiebra :( , pero una franquicia más grande la ha comprado :D , podremos proseguir con su pedido");
		  
	  }
	  
	  else {
		  
		  System.out.println("La panadería no tenía dinero para realizar su pedido :( , pero ha pedido un prestamo y ya puede comprar lo que necesita para preparar su pedido :D");
		  
    } 
	  
  }
  
  public static void lecturaCompra(boolean suceso) {
	  
	  if (suceso == true) { 
		  
		  System.out.println("No tenemos suficientes ingredientes para realizar su compra, por favor espere unos minutos mientras nos encargamos de ello.");
		  
	  }
	  
	  else {
		  
		  System.out.println("Estamos en proceso de conseguir los ingredientes para su pedido, lamentamos los inconvenientes");
		  
	  }
		 
   }
  
}

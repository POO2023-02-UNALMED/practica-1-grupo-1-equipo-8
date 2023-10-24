package UIMain;

/**
 * La clase GestionConseguirIngredientes contiene métodos para manejar diferentes situaciones relacionadas con la adquisición de ingredientes para los pedidos de la panadería.
 */
public class GestionConseguirIngredientes {

	/**
	 * Imprime un mensaje en la consola dependiendo del valor del parámetro suceso.
	 * Si suceso es verdadero, significa que el trabajador que iba por los ingredientes ha sido robado y se enviará a alguien más por la compra.
	 * Si suceso es falso, significa que el trabajador ha regresado con los ingredientes y se procederá a preparar el pedido.
	 * @param suceso un valor booleano que indica si ha ocurrido un robo al trabajador o no.
	 */
	public static void lecturaRobo(boolean suceso) {
	  
	  if (suceso == true) {
		  
		  System.out.println("Han robado al trabajador que iba por los ingredientes para su pedido, pedimos disculpas por el atraso, enviaremos a alguien mas por la compra");
		  
	  }
	  
	  else {
		  
		  System.out.println("El trabajador ha regresado con los ingredientes con su pedido, por favor espere a que preparemos su pedido");
		  
	  } 
	}
	
	/**
	 * Imprime un mensaje en la consola dependiendo del valor del parámetro suceso.
	 * Si suceso es verdadero, significa que la panadería no tenía dinero para realizar el pedido y ha entrado en quiebra, pero una franquicia más grande la ha comprado y se podrá proseguir con el pedido.
	 * Si suceso es falso, significa que la panadería no tenía dinero para realizar el pedido, pero ha pedido un préstamo y ya puede comprar lo que necesita para preparar el pedido.
	 * @param suceso un valor booleano que indica si la panadería ha entrado en quiebra o no.
	 */
	public static void lecturaQuiebra(boolean suceso) {
	  
	  if (suceso == true) {
		  
		  System.out.println("La panadería no tenía dinero para realizar su pedido y ha entrado en quiebra :( , pero una franquicia más grande la ha comprado :D , podremos proseguir con su pedido");
		  
	  }
	  
	  else {
		  
		  System.out.println("La panadería no tenía dinero para realizar su pedido :( , pero ha pedido un prestamo y ya puede comprar lo que necesita para preparar su pedido :D");
		  
		} 
	  
	}
	
	/**
	 * Imprime un mensaje en la consola dependiendo del valor del parámetro suceso.
	 * Si suceso es verdadero, significa que no hay suficientes ingredientes para realizar el pedido y se le pide al cliente que espere unos minutos mientras se consiguen.
	 * Si suceso es falso, significa que se están consiguiendo los ingredientes para el pedido y se lamentan los inconvenientes.
	 * @param suceso un valor booleano que indica si hay suficientes ingredientes para el pedido o no.
	 */
	public static void lecturaCompra(boolean suceso) {
	  
	  if (suceso == true) { 
		  
		  System.out.println("No tenemos suficientes ingredientes para realizar su compra, por favor espere unos minutos mientras nos encargamos de ello.");
		  
	  }
	  
	  else {
		  
		  System.out.println("Estamos en proceso de conseguir los ingredientes para su pedido, lamentamos los inconvenientes");
		  
	  }
		 
	 }
	
}

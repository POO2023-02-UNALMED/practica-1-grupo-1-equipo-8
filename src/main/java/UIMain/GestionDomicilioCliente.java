package UIMain;

import java.util.Scanner;
import gestorAplicacion.gestion.Canasta;

public class GestionDomicilioCliente {
    public boolean enviarCanastasAFacturar(Canasta canastaTrabajar){
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Desea enviar la canasta a facturar? (S/N)");
        String respuesta = sc.nextLine();
        if (respuesta.equals("N")){
            return false;
        }
    }

    }

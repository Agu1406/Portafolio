package UT7ObjetosAvanzados.Ejercicio9UT7ProgramandoDesdeConsolaSinIntellij;

public class SumaDosNumeros {
public static void main (String[] args) {
    // Creación de las variables que utilizare en mi programa
    int numero1, numero2, resultado;
    
    /* Los números los obtengo directamente del Array llamado "args" y como este es del
    * tipo "String" debo "parsear" sus valores de "String" a "int", por lo que utilizo
    * los métodos de la clase "Integer", sabiendo que podrían haber fallos en el parse
    * encierro el "parseo" en un Try-Catch con mi excepción personalizada, además
    * utilizo un do-while para seguir pidiendo los números hasta que sean validos.*/
    
    // Intento parsear los números de "String" a "int" y realizo la suma.
    try {
        numero1 = Integer.parseInt(args[0]); // Intento parsear el "0"
        numero2 = Integer.parseInt(args[1]); // Intento parsear el "1"
        
        resultado = numero1 + numero2; // Almaceno el resultado de la suma.
        
        // Imprimo el resultado de la suma, no se almacena en ningún lugar.
        System.out.println("El resultado de sumar: " + numero1 + " + " + numero2 + " = " + resultado);
    }
    // Error si lo que se desea parsear a "int" no es un número.
    catch (NumberFormatException e){
        System.out.println("¡Error! Parseo invalido, no es un número entero.");
        System.out.println(e.getMessage());
    }
    // Error si los limites del Array se van fuera de los establecidos.
    catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("¡Error! Te has salido de los limites del Array.");
        System.out.println(e.getMessage());
        
    }
  }
}
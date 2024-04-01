package Ejercicio1UT7ClaseRectangulo;

import java.util.Scanner;

public class MainRectangulo {
/*
* Para comprobar que hemos creado correctamente la clase rectangulo, intentamos manipularla
* desde un Main, intentando modificar, visualizar, añadir o modificar sus estancias y por
* lo tanto sus atributos.*/
public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in);
    int x1, x2, y1, y2; // coordenadas que introducire o mediante teclado o mediante codigo.
    String nombrePersonalizado; // nombre personalizado que asignare a cada figura.

    // Primero, nos creamos una estancia rectangulo utilizando el teclado.
    System.out.println("Dime la coordenada X1");
    x1 = teclado.nextInt();
    System.out.println("Dime la coordenada Y1");
    y1 = teclado.nextInt();
    System.out.println("Dime la coordenada X2");
    x2 = teclado.nextInt();
    System.out.println("Dime la coordenada Y2");
    y2 = teclado.nextInt();

    teclado.nextLine(); // Consumimos un salto de linea para limpiar el teclado.
    System.out.println("¿Como deseas llamar a la figura?");
    nombrePersonalizado = teclado.nextLine();

    // Ahora que ya introduje datos mediante teclado, estancio un nuevo rectangulo con esos valores.
    Rectangulo nuevoRectangulo1 = new Rectangulo(nombrePersonalizado, x1, x2, y1, y2);

    // y creo otras dos estancias, pero con datos ingresados directamente desde el codigo.
    Rectangulo nuevoRectangulo2 = new Rectangulo("experimento2", 0, 25, 0, 25);
    Rectangulo nuevoRectangulo3 = new Rectangulo("experimento3", 30, 55, 0, 25);

    // uso mi metodo personalizado toString de la clase rectangulo para visualizar los atributos de las estancias.
    System.out.println(nuevoRectangulo1.toString());
    System.out.println(nuevoRectangulo2.toString());
    System.out.println(nuevoRectangulo3.toString());
}
}

package Examen1TerceraEvaluacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList ejemplaresExistentes = new ArrayList<Ejemplar>();
        ArrayList ejemplaresAlquilados = new ArrayList<Ejemplar>();
        ArrayList peliculas = new ArrayList<Pelicula>();
        ArrayList musicas = new ArrayList<Musica>();
        ArrayList libros = new ArrayList<Libro>();

        int opcionMenu = 0;
        // iba a hacer aqui el do-while
        mostrarMenuOpciones();
        System.out.println("Introduce una opcion del menu: ");

        darAltaNuevaPelicula(peliculas, ejemplaresExistentes);
        daraltaNuevaMusica(musicas, ejemplaresExistentes);
        darAltaNuevoLibro(libros, ejemplaresExistentes);
    }

    private static void mostrarMenuOpciones() {
        System.out.printf("Menú de opciones disponibles: %n" +
                "1.º - Mostrar ejemplares existentes. %n" +
                "2.º - Mostrar ejemplares alquilados. %n" +
                "3.º - Mostrar ejemplares existentes (ampliado). %n" +
                "4.º - Mostrar ejemaplres alquilados (ampliado). %n" +
                "5.º - Alquilar ejemplar (Dentro dices cual). %n" +
                "6.º - Dar de alta una nueva pelicula. %n" +
                "7.º - Dar de alta una nueva nusica. %n" +
                "8.º - Dar de alta un nuevo libro. %n" +
                "9.º - Salir. %n");
    }

    private static void darAltaNuevoLibro(ArrayList libros, ArrayList ejemplaresExistentes) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        Scanner teclado = new Scanner(System.in);
        String expRegFecha = "\\d{2}/\\d{2}/\\d{4}";
        String titulo, autor, intentoFecha = "X";
        LocalDate fechaPub;

        System.out.println("Introduce el titulo: ");
        titulo = teclado.nextLine();

        System.out.println("Introduce el autor: ");
        autor = teclado.nextLine();

        do {
            try {
                System.out.println("Introduce la fecha de publicación");
                intentoFecha = teclado.nextLine();

                fechaPub = LocalDate.parse(intentoFecha, formato);
            } catch (DateTimeParseException e) {
                System.out.println("¡Error! Fecha no valida, introduce una fecha en formato valido (dd/MM/yyyy)");
                teclado.next();
            }
        } while (!intentoFecha.matches(expRegFecha));

        System.out.println("Introduce el número de paginas que tiene: ");
        int numPaginas = teclado.nextInt();
    }

    private static void daraltaNuevaMusica(ArrayList musicas, ArrayList ejemplaresExistentes) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        Scanner teclado = new Scanner(System.in);
        String expRegFecha = "\\d{2}/\\d{2}/\\d{4}";
        String titulo, autor, intentoFecha = "X";
        LocalDate fechaPub;

        System.out.println("Introduce el titulo: ");
        titulo = teclado.nextLine();

        System.out.println("Introduce el autor: ");
        autor = teclado.nextLine();

        do {
            try {
                System.out.println("Introduce la fecha de publicación");
                intentoFecha = teclado.nextLine();

                fechaPub = LocalDate.parse(intentoFecha, formato);
            } catch (DateTimeParseException e) {
                System.out.println("¡Error! Fecha no valida, introduce una fecha en formato valido (dd/MM/yyyy)");
                teclado.next();
            }
        } while (!intentoFecha.matches(expRegFecha));

        System.out.println("Introduce el número de paginas que tiene: ");
        int numPaginas = teclado.nextInt();
    }

    private static void darAltaNuevaPelicula(ArrayList peliculas, ArrayList ejemplaresExistentes) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        Scanner teclado = new Scanner(System.in);
        String expRegFecha = "\\d{2}/\\d{2}/\\d{4}";
        String titulo, autor, intentoFecha = "X";
        LocalDate fechaPub;

        System.out.println("Introduce el titulo: ");
        titulo = teclado.nextLine();

        System.out.println("Introduce el autor: ");
        autor = teclado.nextLine();

        do {
            try {
                System.out.println("Introduce la fecha de publicación");
                intentoFecha = teclado.nextLine();

                fechaPub = LocalDate.parse(intentoFecha, formato);
            } catch (DateTimeParseException e) {
                System.out.println("¡Error! Fecha no valida, introduce una fecha en formato valido (dd/MM/yyyy)");
                teclado.next();
            }
        } while (!intentoFecha.matches(expRegFecha));

        System.out.println("Introduce el número de paginas que tiene: ");
        int numPaginas = teclado.nextInt();
    }
}

package sistema_gestion_biblioteca;

import java.util.Date;

public class SistemaBiblioteca {
    public static void main(String[] args) {
        // Crear biblioteca (usaremos biblioteca1)
        Biblioteca biblioteca1 = new Biblioteca("Angulo", 5, 5);
        
        // Crear tres libros físicos
        LibroFisico libroFisico1 = new LibroFisico("Don Quijote", "Miguel de Cervantes", "Bueno");
        LibroFisico libroFisico2 = new LibroFisico("Cien años de soledad", "Gabriel García Márquez", "Excelente");
        LibroFisico libroFisico3 = new LibroFisico("La Casa de los Espíritus", "Isabel Allende", "Regular");

        // Crear tres libros digitales
        LibroDigital libroDigital1 = new LibroDigital("El Principito", "Antoine de Saint-Exupéry", "PDF");
        LibroDigital libroDigital2 = new LibroDigital("1984", "George Orwell", "EPUB");
        LibroDigital libroDigital3 = new LibroDigital("Fahrenheit 451", "Ray Bradbury", "MOBI");

        // Añadir todos los libros al catálogo
        biblioteca1.agregarLibro(libroFisico1);
        biblioteca1.agregarLibro(libroFisico2);
        biblioteca1.agregarLibro(libroFisico3);
        biblioteca1.agregarLibro(libroDigital1);
        biblioteca1.agregarLibro(libroDigital2);
        biblioteca1.agregarLibro(libroDigital3);

        // Registrar varios usuarios
        Usuario usuario1 = new Usuario("Juan Pérez");
        Usuario usuario2 = new Usuario("María García");
        Usuario usuario3 = new Usuario("Carlos López");

        biblioteca1.agregarUsuario(usuario1);
        biblioteca1.agregarUsuario(usuario2);
        biblioteca1.agregarUsuario(usuario3);

        try {
            // Fechas para las reservas
            Date fecha1Inicio = new Date(123, 11, 21); // 21/12/2023
            Date fecha1Fin = new Date(123, 11, 24);    // 24/12/2023
            Date fecha2Inicio = new Date(112, 10, 26); // 26/11/2012
            Date fecha2Fin = new Date(112, 10, 30);    // 30/11/2012
            Date fecha3Inicio = new Date(113, 0, 2);   // 02/01/2013
            Date fecha3Fin = new Date(113, 0, 4);      // 04/01/2013

            // Reservar libro físico del 21/12/2023 hasta el 24/12/2023
            biblioteca1.reservarLibro(usuario1, libroFisico1, fecha1Inicio, fecha1Fin);

            // Reservar libro digital del 26/11/2012 al 30/11/2012
            biblioteca1.reservarLibro(usuario2, libroDigital1, fecha2Inicio, fecha2Fin);

            // Intentar reservar el mismo libro digital del 2/1/2013 al 4/1/2013
            biblioteca1.reservarLibro(usuario3, libroDigital1, fecha3Inicio, fecha3Fin);

        } catch (IllegalStateException e) {
            System.out.println("Error al reservar: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en los datos: " + e.getMessage());
        }

        // Mostrar listado de reservas activas
        System.out.println("\n=== LISTADO DE RESERVAS ACTIVAS ===");
        biblioteca1.mostrarReservasActivas();

        // Mostrar información completa de la biblioteca
        System.out.println("\n=== CATÁLOGO DE LA BIBLIOTECA ===");
        biblioteca1.imprimirCatalogo();
    }
} 
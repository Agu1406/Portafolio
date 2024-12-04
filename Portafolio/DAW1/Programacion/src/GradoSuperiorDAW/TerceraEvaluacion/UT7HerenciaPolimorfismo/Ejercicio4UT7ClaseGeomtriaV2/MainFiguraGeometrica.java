package GradoSuperiorDAW.TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio4UT7ClaseGeomtriaV2;

import java.util.ArrayList;

/**
 * <h1 style="text-align: center;">Clase MainFiguraGeometrica</h1><br>
 * <p style="text-align: justify;">
 * Esta versión actualizada de la clase principal gestiona una colección de figuras geométricas, incluyendo círculos, cuadrados,
 * y triángulos. Permite la creación de estas figuras, almacena en colecciones y proporciona métodos
 * para mostrar sus áreas y perímetros utilizando polimorfismo. Además, incorpora métodos de la clase "Padre"
 * que llaman a los métodos de las clases "Hija" dependiendo de la figura que se encuentre en la posición actual
 * de los bucles.
 * </p><br>
 *
 * <h2 style="text-align: center;">Características y Funcionalidades</h2><br>
 * <ul>
 *   <li>Crear y almacenar diferentes tipos de figuras geométricas en colecciones específicas y una colección general.</li>
 *   <li>Mostrar las áreas y los perímetros de las figuras almacenadas, utilizando llamadas polimórficas.</li>
 *   <li>Implementa mejoras en la interacción entre clases padre e hija para optimizar la gestión de las figuras geométricas.</li>
 * </ul><br>
 *
 * @version 2.0
 * @since 16/04/2024
 * @author Agu1406 (Agustín)
 */
public class MainFiguraGeometrica {
    public static void main(String[] args) {
        ArrayList<FiguraGeometrica> figurasGeomtricas = new ArrayList<>();
        /* ArrayList<Circulo> circulos = new ArrayList<>();
        ArrayList<Cuadrado> cuadrados = new ArrayList<>();
        ArrayList<Triangulo> triangulos = new ArrayList<>(); */

        Circulo nuevoCirculo = new Circulo(20);
        figurasGeomtricas.add(nuevoCirculo);
        // circulos.add(nuevoCirculo);

        Cuadrado nuevoCuadrado = new Cuadrado(40);
        figurasGeomtricas.add(nuevoCuadrado);
        // cuadrados.add(nuevoCuadrado);

        Triangulo nuevoTriangulo = new Triangulo(20, 20);
        figurasGeomtricas.add(nuevoTriangulo);
        // triangulos.add(nuevoTriangulo);

        mostrarAreas(figurasGeomtricas);
        mostrarPerimetros(figurasGeomtricas);
        mostrarAreasPerimetros(figurasGeomtricas);
    }

    /**
     * <h2 style="text-align: center;">Método mostrarAreas</h2><br>
     * <p style="text-align: justify;">
     * Recorre una lista de figuras geométricas y muestra el área de cada una mediante llamadas a sus respectivos métodos de área.
     * </p>
     *
     * @param figurasGeomtricas ArrayList de FiguraGeometrica, contiene diversas figuras geométricas.
     */
    private static void mostrarAreas(ArrayList<FiguraGeometrica> figurasGeomtricas) {
        for (FiguraGeometrica figura : figurasGeomtricas) {
            figura.mostrarArea();
        }
    }

    /**
     * <h2 style="text-align: center;">Método mostrarPerimetros</h2><br>
     * <p style="text-align: justify;">
     * Recorre una lista de figuras geométricas y muestra el perímetro de cada una mediante llamadas a sus respectivos métodos de perímetro.
     * </p>
     *
     * @param figurasGeomtricas ArrayList de FiguraGeometrica, contiene diversas figuras geométricas.
     */
    private static void mostrarPerimetros(ArrayList<FiguraGeometrica> figurasGeomtricas) {
        for (FiguraGeometrica figura : figurasGeomtricas) {
            figura.mostrarPerimetro();
        }
    }

    /**
     * <h2 style="text-align: center;">Método mostrarAreasPerimetros</h2><br>
     * <p style="text-align: justify;">
     * Recorre una lista de figuras geométricas y muestra tanto el área como el perímetro de cada una mediante llamadas a sus respectivos métodos.
     * Este método demuestra el uso de polimorfismo en la ejecución de métodos específicos de subclases desde una referencia de clase base.
     * </p>
     *
     * @param figurasGeomtricas ArrayList de FiguraGeometrica, contiene diversas figuras geométricas.
     */
    private static void mostrarAreasPerimetros(ArrayList<FiguraGeometrica> figurasGeomtricas) {
        for (FiguraGeometrica figura : figurasGeomtricas) {
            figura.mostrarArea();
            figura.mostrarPerimetro();
        }
    }
}

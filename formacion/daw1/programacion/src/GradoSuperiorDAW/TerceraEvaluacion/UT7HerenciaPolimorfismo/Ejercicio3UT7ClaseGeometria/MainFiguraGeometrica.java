package GradoSuperiorDAW.TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio3UT7ClaseGeometria;

import java.util.ArrayList;

public class MainFiguraGeometrica {
    public static void main(String[] args) {

        ArrayList <FiguraGeometrica> figurasGeomtricas = new ArrayList<>();
        ArrayList <Circulo> circulos = new ArrayList<>();
        ArrayList <Cuadrado> cuadrados = new ArrayList<>();
        ArrayList <Triangulo> triangulos = new ArrayList<>();

        Circulo nuevoCirculo = new Circulo(20);
        figurasGeomtricas.add(nuevoCirculo);
        circulos.add(nuevoCirculo);

        Cuadrado nuevoCuadrado = new Cuadrado(40);
        figurasGeomtricas.add(nuevoCuadrado);
        cuadrados.add(nuevoCuadrado);

        Triangulo nuevoTriangulo = new Triangulo(20, 20);
        figurasGeomtricas.add(nuevoTriangulo);
        triangulos.add(nuevoTriangulo);

        // Utilizando los métodos de cada clase.
        nuevoCirculo.mostrarPerimetro();
        nuevoCirculo.mostrarArea();

        nuevoCuadrado.mostrarPerimetro();
        nuevoCuadrado.mostrarArea();

        nuevoTriangulo.mostrarPerimetro();
        nuevoTriangulo.mostrarArea();

    }
}

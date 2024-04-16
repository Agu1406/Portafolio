package UT7HerenciaPolimorfismo.Ejercicio4UT7ClaseGeomtriaV2;

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

        /* Inhabilitamos temporalmente los métodos propios de las
        * clases para poder crear y probar nuevos métodos sin
        * gastar tiempo y recursos en los anteriormente ya
        * desarrollados y testeados.
        *
        * nuevoCirculo.mostrarPerimetro();
        * nuevoCirculo.mostrarArea();
        *
        * nuevoCuadrado.mostrarPerimetro();
        * nuevoCuadrado.mostrarArea();
        *
        * nuevoTriangulo.mostrarPerimetro();
        * nuevoTriangulo.mostrarArea();
        */

        mostrarAreas(figurasGeomtricas);
        mostrarPerimetros(figurasGeomtricas);
        mostrarAreasPerimetros(figurasGeomtricas);
    }

    private static void mostrarAreasPerimetros(ArrayList<FiguraGeometrica> figurasGeomtricas) {
    for (FiguraGeometrica figura : figurasGeomtricas) {
        figura.mostrarArea();
        figura.mostrarPerimetro();
    }
    }

    private static void mostrarPerimetros(ArrayList<FiguraGeometrica> figurasGeomtricas) {
        for (FiguraGeometrica figura : figurasGeomtricas) {
            figura.mostrarPerimetro();
        }
    }

    private static void mostrarAreas(ArrayList<FiguraGeometrica> figurasGeomtricas) {
        for (FiguraGeometrica figura : figurasGeomtricas) {
            figura.mostrarArea();
        }
    }
}

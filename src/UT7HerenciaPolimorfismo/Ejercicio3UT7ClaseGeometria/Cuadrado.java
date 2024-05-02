package UT7HerenciaPolimorfismo.Ejercicio3UT7ClaseGeometria;

public class Cuadrado implements FiguraGeometrica {

    int lado;

    Cuadrado (int lado) {
        this.lado = lado;
    }

    @Override
    public void mostrarArea() {
        int area = lado * lado; // lado^2
        System.out.println("El área del cuadrado es: " + area);
    }

    @Override
    public void mostrarPerimetro() {
        int perimetro = 4 * lado; // 4 * lado
        System.out.println("El perímetro del cuadrado es: " + perimetro);
    }
}

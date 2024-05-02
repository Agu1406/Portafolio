package TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio3UT7ClaseGeometria;

public class Triangulo implements FiguraGeometrica {

    private int base, altura;

    Triangulo (int base, int altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public void mostrarArea() {
        double area = (base * altura) / 2.0;
        System.out.println("El área del triángulo es: " + area);
    }

    @Override
    public void mostrarPerimetro() {
        int perimetro = base * 3;
        System.out.println("El perímetro del triángulo es: " + perimetro);
    }
}

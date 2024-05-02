package UT7HerenciaPolimorfismo.Ejercicio4UT7ClaseGeomtriaV2;

public class Circulo implements FiguraGeometrica {
    private int radio;

    public Circulo(int radio) {
        this.radio = radio;
    }

    @Override
    public void mostrarArea() {
        double area = Math.PI * Math.pow(radio, 2); // Pi * radio^2
        System.out.println("El área del círculo es: " + area);
    }

    @Override
    public void mostrarPerimetro() {
        double perimetro = 2 * Math.PI * radio; // 2 * Pi * radio
        System.out.println("El perímetro del círculo es: " + perimetro);
    }
}

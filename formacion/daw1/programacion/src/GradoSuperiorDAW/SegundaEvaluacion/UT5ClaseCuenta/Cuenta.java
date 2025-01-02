package GradoSuperiorDAW.SegundaEvaluacion.UT5ClaseCuenta;

import java.util.Objects;

public class Cuenta {
    //Atributos
    private Integer codigo;
    private Persona titular;
    private Float cantidad;

    //Constructores
    public Cuenta(Integer codigo, Persona titular, Float cantidad) {
        this.codigo = codigo;
        this.titular = titular;
        this.cantidad = cantidad;
    }

    //Getters
    public Integer getCodigo() {
        return codigo;
    }

    public Persona getTitular() {
        return titular;
    }

    public Float getCantidad() {
        return cantidad;
    }


    //Métodos
    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder("codigo=" + codigo +
                "\n titular= [" + titular + "]" );
        if (cantidad!=null){
            salida.append("\n cantidad='" + cantidad + '\'');
        }
        salida.append("\n");
        //return "Codigo=" + codigo + ", Titular=" + titular +", Cantidad=" + cantidad;
        return salida.toString();


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(codigo, cuenta.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    public void retirar (float cantidadRetirada){
        this.cantidad = cantidad - cantidadRetirada;
    }

    public void ingresar (float cantidadIngresada){
        this.cantidad = cantidad + cantidadIngresada;
    }
}

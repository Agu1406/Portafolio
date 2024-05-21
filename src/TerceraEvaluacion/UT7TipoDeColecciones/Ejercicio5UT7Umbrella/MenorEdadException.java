package TerceraEvaluacion.UT7TipoDeColecciones.Ejercicio5UT7Umbrella;

import java.util.InputMismatchException;

public class MenorEdadException extends InputMismatchException {        //EN CASO DE QUE EL EMPLEADO SEA MENOR DE EDAD LANZAMOS ESTA EXCEPCION
    public MenorEdadException(String s) {
        super(s);
    }
}

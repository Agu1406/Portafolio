package Madrid.entornos_desarrolllo.Usuarios;

import ord2.usuarios.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;

/**
 * Clase de prueba para la clase Usuario.
 * Verifica que el método validarDatos funcione correctamente
 * con diferentes combinaciones de datos.
 * 
 * @author agu1406
 */
@RunWith(Parameterized.class)
public class UsuarioTestDatos {
    // Variable para almacenar los parámetros
    private final Usuario usu;
    private final boolean salida;

    // Constructor que recibe los parámetros;
    public UsuarioTestDatos (Usuario usu, boolean salida) {
        this.usu = usu;
        this.salida = salida;
    }

    //Método que proporciona los datos de prueba
    @Parameterized.Parameters
    public static Collection<Object[]> datos() {
        // AMPLIACIÓN DE PRUEBAS: Se añadieron 19 casos de prueba para cubrir todos los escenarios posibles
        // DÓNDE: Método datos() que proporciona los parámetros para las pruebas parametrizadas
        // POR QUÉ: Para asegurar cobertura completa de caja blanca del método validarDatos()
        
        // CASOS VÁLIDOS (3 casos): Usuarios con datos completamente correctos
        // DÓNDE: Líneas 35-37
        // POR QUÉ: Para verificar que el método acepta datos válidos
        Usuario usu1 = new Usuario("Marta", "Montes", "mmontes@email.es", "Passwr0d", 25);
        Usuario usu2 = new Usuario("Juan", "García", "juan.garcia@empresa.com", "Secure123", 30);
        Usuario usu3 = new Usuario("Ana", "López", "ana.lopez@universidad.edu", "MyPass1", 22);
        
        // CASOS INVÁLIDOS - NOMBRE (3 casos): Pruebas para validación de nombre
        // DÓNDE: Líneas 40-42
        // POR QUÉ: Para verificar que se detectan errores en nombre (null, vacío, sin mayúscula)
        Usuario usu4 = new Usuario(null, "Pérez", "maria@email.com", "Password1", 25);
        Usuario usu5 = new Usuario("", "Pérez", "maria@email.com", "Password1", 25);
        Usuario usu6 = new Usuario("maria", "Pérez", "maria@email.com", "Password1", 25);
        
        // CASOS INVÁLIDOS - APELLIDO (3 casos): Pruebas para validación de apellido
        // DÓNDE: Líneas 45-47
        // POR QUÉ: Para verificar que se detectan errores en apellido (null, vacío, sin mayúscula)
        Usuario usu7 = new Usuario("María", null, "maria@email.com", "Password1", 25);
        Usuario usu8 = new Usuario("María", "", "maria@email.com", "Password1", 25);
        Usuario usu9 = new Usuario("María", "pérez", "maria@email.com", "Password1", 25);
        
        // CASOS INVÁLIDOS - EMAIL (5 casos): Pruebas para validación de email
        // DÓNDE: Líneas 50-54
        // POR QUÉ: Para verificar todas las validaciones de email (sin @, sin punto, empieza por @, dominio inválido)
        Usuario usu10 = new Usuario("María", "Pérez", "mariaemail.com", "Password1", 25);
        Usuario usu11 = new Usuario("María", "Pérez", "maria@email", "Password1", 25);
        Usuario usu12 = new Usuario("María", "Pérez", "@email.com", "Password1", 25);
        Usuario usu13 = new Usuario("María", "Pérez", "maria@email.c", "Password1", 25);
        Usuario usu14 = new Usuario("María", "Pérez", "maria@email.comm", "Password1", 25);
        
        // CASOS INVÁLIDOS - PASSWORD (3 casos): Pruebas para validación de contraseña
        // DÓNDE: Líneas 57-59
        // POR QUÉ: Para verificar validaciones de contraseña (muy corta, sin números, sin mayúsculas)
        Usuario usu15 = new Usuario("María", "Pérez", "maria@email.com", "Pass1", 25);
        Usuario usu16 = new Usuario("María", "Pérez", "maria@email.com", "Password", 25);
        Usuario usu17 = new Usuario("María", "Pérez", "maria@email.com", "password1", 25);
        
        // CASOS INVÁLIDOS - EDAD (2 casos): Pruebas para validación de edad
        // DÓNDE: Líneas 62-63
        // POR QUÉ: Para verificar validaciones de edad (menor de 18, mayor de 100)
        Usuario usu18 = new Usuario("María", "Pérez", "maria@email.com", "Password1", 17);
        Usuario usu19 = new Usuario("María", "Pérez", "maria@email.com", "Password1", 101);
        
        // RETORNO DE DATOS: Se organizan todos los casos en un array para pruebas parametrizadas
        // DÓNDE: Líneas 66-89
        // POR QUÉ: Para que JUnit ejecute cada caso de prueba automáticamente
        return Arrays.asList(new Object[][]{
            // Casos válidos
            {usu1, true},
            {usu2, true},
            {usu3, true},
            
            // Casos inválidos - Nombre
            {usu4, false},
            {usu5, false},
            {usu6, false},
            
            // Casos inválidos - Apellido
            {usu7, false},
            {usu8, false},
            {usu9, false},
            
            // Casos inválidos - Email
            {usu10, false},
            {usu11, false},
            {usu12, false},
            {usu13, false},
            {usu14, false},
            
            // Casos inválidos - Password
            {usu15, false},
            {usu16, false},
            {usu17, false},
            
            // Casos inválidos - Edad
            {usu18, false},
            {usu19, false}
        });
    }

    // Método de prueba que utiliza los parámetros
    // AMPLIACIÓN: Se mejoró el mensaje de error para identificar qué caso falló
    // DÓNDE: Líneas 92-95
    // POR QUÉ: Para facilitar la depuración cuando una prueba falla
    @Test
    public void testValidarDatos() {
        assertEquals("Prueba para usuario: " + usu.getNombre() + " " + usu.getApellido(), 
                     salida, usu.validarDatos());
    }
}
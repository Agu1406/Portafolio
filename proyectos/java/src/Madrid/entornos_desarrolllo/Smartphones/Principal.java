package Madrid.entornos_desarrolllo.Smartphones;

/**
 * Clase principal que demuestra el uso de la clase Smartphone.
 * 
 * REFACTORIZACIÓN REALIZADA:
 * - Eliminación segura del método compararRendimiento
 * - Actualización de referencias para usar getters
 * - Documentación Javadoc completa
 * 
 * @author agu1406
 * @version 1.0
 * @since 2024
 */
public class Principal {
    
    /**
     * Método principal que crea y muestra información de smartphones.
     * 
     * REFACTORIZACIÓN: Eliminación segura del método compararRendimiento
     * ANTES: Se llamaba a compararRendimiento(phone1, phone2) - método estático problemático
     * DESPUÉS: Lógica integrada directamente en main con uso de getters
     * DÓNDE: Líneas 15-22 del código original
     * POR QUÉ: El método compararRendimiento violaba principios OOP y era innecesario
     * BENEFICIO: Código más limpio y funcionalidad mantenida
     * 
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // REFACTORIZACIÓN: Uso del constructor refactorizado
        // ANTES: new Smartphone("Galaxy S23", 6.1, 0, "Negro", "Vidrio", 899.99) - parámetros confusos
        // DESPUÉS: Mismos parámetros pero con nombres descriptivos en el constructor
        // DÓNDE: Líneas 16-17 del código original
        // POR QUÉ: Para usar el constructor refactorizado con parámetros claros
        // BENEFICIO: Código más legible aunque los parámetros sean los mismos
        Smartphone phone1 = new Smartphone("Galaxy S23", 6.1, 0, "Negro", "Vidrio", 899.99);
        Smartphone phone2 = new Smartphone("iPhone 15", 6.2, 1, "Azul", "Titanio", 999.99);
        
        // REFACTORIZACIÓN: Método mostrarEspecificaciones actualizado internamente
        // ANTES: Usaba att1, att2, att4, att5, att6 - referencias confusas
        // DESPUÉS: Usa modelo, tamanoPantalla, color, material, precio - referencias claras
        // DÓNDE: Internamente en el método mostrarEspecificaciones
        // POR QUÉ: Para usar los nuevos nombres de atributos encapsulados
        // BENEFICIO: Salida más clara y código más mantenible
        phone1.mostrarEspecificaciones();
        System.out.println("***************************");
        phone2.mostrarEspecificaciones();
        System.out.println("\n***************************\n");
        
        // REFACTORIZACIÓN: Eliminación segura del método compararRendimiento
        // ANTES: compararRendimiento(phone1, phone2) - método estático problemático
        // DESPUÉS: Lógica integrada directamente en main
        // DÓNDE: Líneas 15-22 del código original
        // POR QUÉ: El método era innecesario y violaba principios OOP
        // BENEFICIO: Código más directo y funcionalidad mantenida
        System.out.println("Comparación de rendimiento:");
        
        // REFACTORIZACIÓN: Uso de getters en lugar de acceso directo a atributos
        // ANTES: phone1.att1, phone2.att1 - acceso directo a atributos públicos
        // DESPUÉS: phone1.getModelo(), phone2.getModelo() - uso de getters encapsulados
        // DÓNDE: Líneas 19-21 del código original
        // POR QUÉ: Para respetar la encapsulación y usar los nuevos getters
        // BENEFICIO: Código más robusto y que respeta principios OOP
        if (phone1.calcularPuntuacionRendimiento() > phone2.calcularPuntuacionRendimiento()) {
            System.out.println("El " + phone1.getModelo() + " tiene mejor rendimiento que el " + phone2.getModelo());
        } else {
            System.out.println("El " + phone2.getModelo() + " tiene mejor rendimiento que el " + phone1.getModelo());
        }
    }
}
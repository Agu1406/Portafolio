package Madrid.entornos_desarrolllo.Smartphones;

/**
 * Clase que representa un smartphone con sus características principales.
 * 
 * REFACTORIZACIÓN COMPLETA REALIZADA:
 * - Renombrado de atributos: att1->modelo, att2->tamanoPantalla, att3->sistemaOperativo, etc.
 * - Encapsulación: Cambio de public a private + getters/setters
 * - Documentación Javadoc completa
 * 
 * @author agu1406
 * @version 1.0
 * @since 2024
 */
public class Smartphone {
    
    // REFACTORIZACIÓN 1: Renombrado de atributos para mayor claridad
    // ANTES: public String att1; public double att2; public int att3; etc.
    // DESPUÉS: private String modelo; private double tamanoPantalla; etc.
    // DÓNDE: Líneas 8-13 del código original
    // POR QUÉ: Los nombres originales (att1, att2, etc.) no eran descriptivos
    // BENEFICIO: Código más legible y mantenible
    private String modelo;
    private double tamanoPantalla;
    private int sistemaOperativo;
    private String color;
    private String material;
    private double precio;

    /**
     * Constructor de la clase Smartphone.
     * 
     * REFACTORIZACIÓN: Renombrado de parámetros del constructor
     * ANTES: Smartphone(String var1, double var2, int var3, String var4, String var5, double var6)
     * DESPUÉS: Smartphone(String modelo, double tamanoPantalla, int sistemaOperativo, String color, String material, double precio)
     * DÓNDE: Constructor completo
     * POR QUÉ: Los nombres var1, var2, etc. no indicaban qué representaban
     * BENEFICIO: Constructor más claro y autodocumentado
     * 
     * @param modelo Nombre del modelo del smartphone
     * @param tamanoPantalla Tamaño de la pantalla en pulgadas
     * @param sistemaOperativo Sistema operativo (0=Android, 1=iOS, 2=Otro)
     * @param color Color del smartphone
     * @param material Material del smartphone
     * @param precio Precio del smartphone en euros
     */
    public Smartphone(String modelo, double tamanoPantalla, int sistemaOperativo, String color, String material, double precio) {
        this.modelo = modelo;
        this.tamanoPantalla = tamanoPantalla;
        this.sistemaOperativo = sistemaOperativo;
        this.color = color;
        this.material = material;
        this.precio = precio;
    }

    // REFACTORIZACIÓN 2: Encapsulación completa - Generación de getters y setters
    // ANTES: Atributos públicos accesibles directamente
    // DESPUÉS: Atributos privados con métodos get/set
    // DÓNDE: Todos los atributos de la clase
    // POR QUÉ: Seguir principios de encapsulación y ocultación de información
    // BENEFICIO: Mayor control sobre el acceso a los datos y mejor diseño OOP

    /**
     * Obtiene el modelo del smartphone.
     * 
     * @return el modelo del smartphone
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo del smartphone.
     * 
     * @param modelo el modelo del smartphone
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el tamaño de la pantalla.
     * 
     * @return el tamaño de la pantalla en pulgadas
     */
    public double getTamanoPantalla() {
        return tamanoPantalla;
    }

    /**
     * Establece el tamaño de la pantalla.
     * 
     * @param tamanoPantalla el tamaño de la pantalla en pulgadas
     */
    public void setTamanoPantalla(double tamanoPantalla) {
        this.tamanoPantalla = tamanoPantalla;
    }

    /**
     * Obtiene el sistema operativo.
     * 
     * @return el código del sistema operativo (0=Android, 1=iOS, 2=Otro)
     */
    public int getSistemaOperativo() {
        return sistemaOperativo;
    }

    /**
     * Establece el sistema operativo.
     * 
     * @param sistemaOperativo el código del sistema operativo (0=Android, 1=iOS, 2=Otro)
     */
    public void setSistemaOperativo(int sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    /**
     * Obtiene el color del smartphone.
     * 
     * @return el color del smartphone
     */
    public String getColor() {
        return color;
    }

    /**
     * Establece el color del smartphone.
     * 
     * @param color el color del smartphone
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Obtiene el material del smartphone.
     * 
     * @return el material del smartphone
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Establece el material del smartphone.
     * 
     * @param material el material del smartphone
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Obtiene el precio del smartphone.
     * 
     * @return el precio del smartphone en euros
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del smartphone.
     * 
     * @param precio el precio del smartphone en euros
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el nombre del sistema operativo basado en el código.
     * 
     * REFACTORIZACIÓN: Actualización de referencia a atributo
     * ANTES: switch(att3) - referencia a atributo con nombre no descriptivo
     * DESPUÉS: switch(sistemaOperativo) - referencia clara al atributo
     * DÓNDE: Línea 25 del código original
     * POR QUÉ: Para usar el nuevo nombre del atributo encapsulado
     * BENEFICIO: Código más consistente y legible
     * 
     * @return el nombre del sistema operativo
     */
    public String obtenerSistemaOperativo() {
        switch(sistemaOperativo) {
            case 0: return "Android";
            case 1: return "iOS";
            case 2: return "Otro";
            default: return "Desconocido";
        }
    }

    /**
     * Calcula la puntuación de rendimiento basada en el tamaño de pantalla y precio.
     * 
     * REFACTORIZACIÓN: Actualización de referencias a atributos
     * ANTES: (att2 * 10) + (att6 / 100) - referencias confusas
     * DESPUÉS: (tamanoPantalla * 10) + (precio / 100) - referencias claras
     * DÓNDE: Línea 30 del código original
     * POR QUÉ: Para usar los nuevos nombres de atributos encapsulados
     * BENEFICIO: Código más legible y mantenible
     * 
     * @return la puntuación de rendimiento (0-100)
     */
    public int calcularPuntuacionRendimiento() {
        double puntuacion = (tamanoPantalla * 10) + (precio / 100);
        return (int) Math.min(100, Math.max(0, puntuacion));
    }

    /**
     * Muestra las especificaciones completas del smartphone.
     * 
     * REFACTORIZACIÓN: Actualización de referencias a atributos
     * ANTES: att1, att2, att4, att5, att6 - referencias confusas
     * DESPUÉS: modelo, tamanoPantalla, color, material, precio - referencias claras
     * DÓNDE: Líneas 33-38 del código original
     * POR QUÉ: Para usar los nuevos nombres de atributos encapsulados
     * BENEFICIO: Método más legible y autodocumentado
     */
    public void mostrarEspecificaciones() {
        System.out.println("Especificaciones del modelo " + modelo + ":");
        System.out.println("- Tamaño de pantalla: " + tamanoPantalla + " pulgadas");
        System.out.println("- Sistema operativo: " + obtenerSistemaOperativo());
        System.out.println("- Color: " + color);
        System.out.println("- Material: " + material);
        System.out.println("- Precio: " + precio + "€");
        System.out.println("- Puntuación de rendimiento: " + calcularPuntuacionRendimiento() + "/100");
    }
}
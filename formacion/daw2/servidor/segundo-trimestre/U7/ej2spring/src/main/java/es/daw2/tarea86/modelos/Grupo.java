package es.daw2.tarea86.modelos;

import lombok.Data;
/*
 * dejamos comentadas las importaciones de "Getter" y "Setter"
 * porque con "Data" tenemos ambos en uno solo.
 * 
 * import lombok.Getter;
 * import lombok.Setter;
 */

/**
 * Normalmente tendríamos que escribir los Getters y Setters
 * manualmente, pero al usar lombok estos se generan de
 * forma automatica cuando ejecutamos el código, podriamos
 * elegir solo generar unos u otros usando "@Getter" o
 * "@Setter" pero si deseamos usar ambos nos vale usar la
 * etiqueta "@Data" que incluye ambos.
 */
@Data
public class Grupo {
    private String ies;
    private String ciclo;
    private String curso;

    public Grupo(String ies, String ciclo, String curso) {
        this.ies = ies;
        this.ciclo = ciclo;
        this.curso = curso;
    }
}
<?php 

    // Creamos todas las variables que usaremos en el script
    $base = 3;
    $exponente = 4;
    $resultado;

    /**
     * Función que calcula la potencia de un número tomando en cuenta su baser
     * y su exponente.
     * @param mixed $base
     * @param mixed $exponente
     * @return mixed el resultado de la potencia
     */
    function potencia ($base, $exponente) {

        $auxiliar = $base;

        // Tendra tantas iteraciones como sea el valor del exponente
        for ($i = 0; $i < $exponente; $i++) { 
            
            // Multiplica la base por si misma en cada iteración del bucle
            $auxiliar *= $base;
        }

        return $auxiliar;
    }

    $resultado = potencia($base, $exponente);

    echo "El resultado de " . $base . " elevado a " . $exponente . " es: " . $resultado . "<br>";
?>
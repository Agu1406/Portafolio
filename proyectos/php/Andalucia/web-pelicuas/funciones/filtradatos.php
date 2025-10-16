<?php

    function filtraDatos(int $año, array &$peliculas):int {
        // Creamos una variable para contar las peliculas.
        $contador = count($peliculas);
        
        // Creamos un Array de peliculas filtradas donde guardarlas.
        $peliculasFiltradas = [];

        // Recorre todas las peliculas del Array una por una.
        foreach ($peliculas as $pelicula) {

            /**
             * Si el año de la pelicula (convertido en un número entero) es igual
             * al año recibido como argumento significa que es valido, añado esa
             * pelicula al Arraylist de peliculas filtradas.
             */
            if((int) $pelicula["año"] === $año) {
                $peliculasFiltradas[] = $pelicula;
            }

        }

        /**
         * Como el Arraylist esta siendo pasado como un argumento si yo
         * lo modifico o hago cambios en el estos cambios seran visibles
         * en toda la aplicación incluso aunque no lo devuelva con un
         * return.
         */
        $peliculas = $peliculasFiltradas;

        /**
         * Le restamos al contador la cantidad de peliculas que han quedado
         * luego del filtrado, dando como lugar a la cantidad de elementos
         * eliminados.
         */
        $contador = $contador - count($peliculas);

        // Devuelvo la cantidad de peliculas eliminadas post-filtrado.
        return $contador;
    }
?>  

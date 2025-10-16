<?php

define ('CABECERAS',['título',
                     'género',
                     'año',
                     'dirección',
                     'duración',
                     'argumento']);

define ('GENEROS',['animación','drama','ciencia ficción/fantasía','comedia']);

function cargarCSV (string $archivo): array {    
    // Crea un Array donde guardar los datos leidos del archivo.
    $datos=[];

    // El String "archivo" será la dirección/ruta del archivo que hay que leer.
    $archivo = fopen($archivo,'r');

    // Por defecto aún no hemos leido el archivo, no tiene filas correctas.
    $filas_correctas = 0;

    // Por defecto aún no hemos leido el archivo, no tiene filas correctas.
    $filas_incorrectas = 0;

    /*El bucle funciona mientras existan lineas por leer*/
    /*Cada linea es un array indexado.*/
    while (($fila = fgetcsv($archivo))!==false) {
           
            //$datos[]=array_combine(CABECERAS,$fila);
            
            //Usamos una variable booleana como variable de control
            $fila_valida = true;

            //Comprobarmos que el número de elementos de la fila coincida con los elementos de la cabecera
        if (count($fila) !== count(CABECERAS)) {
            //En caso de no coincidir el dato la fila leida no es válida.
            $fila_valida = false;
        } else {
            /*
            Usando el método array_combine obtenemos un array asociativo
            Donde los elementos de la cabecera será las claves
            y los valores será los elementos de $fila
            */
            $fila_asociativa = array_combine(CABECERAS, $fila);

                //Comprobamos que exista el género. 
                if (!in_array($fila_asociativa['género'], GENEROS)) {
                    $fila_valida = false;
                }

                //Comprobamos el año de la película. 
                if (!is_numeric($fila_asociativa['año'])) {
                    $fila_valida = false;
                }


                // Verifica si es un número o no (de cualquier tipo, int, float, String).
                if (!is_numeric($fila_asociativa['duración'])) {
                    $fila_valida = false;
                    
                } else{                    
                    $duracion =$fila_asociativa['duración'];
                    settype($duracion, "integer");

                }
            }

            // Añadimos a datos solo si la fila es válida
            if ($fila_valida) {
                $datos[] = $fila_asociativa;
                $filas_correctas++;
            } else {
                $filas_incorrectas++;
            }
    }
    return [$filas_correctas, $filas_incorrectas, $datos];
}
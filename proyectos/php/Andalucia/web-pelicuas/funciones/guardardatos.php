<?php


function guardarCSV (string $archivo, array $peliculas): int
{
    // Abrimos en modo append para añadir al final del CSV
    $handle = fopen($archivo, 'a');
    $errors = 0;

    foreach ($peliculas as $pelicula) {
        // Aseguramos el orden de columnas conforme a CABECERAS
        $fila = [
            $pelicula['título'] ?? '',
            $pelicula['género'] ?? '',
            $pelicula['año'] ?? '',
            $pelicula['dirección'] ?? '',
            $pelicula['duración'] ?? '',
            $pelicula['argumento'] ?? '',
        ];

        if (fputcsv($handle, $fila) === false) {
            $errors++;
        }
    }

    fclose($handle);
    return $errors;
}
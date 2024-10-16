<?php

// Definir la clase piezaMusical
class piezaMusical {
    public $artista = "Desconocido";
    public $genero = "Desconocido";
    public $nombreCancion = "Desconocido";
    public $audio = "Sin definir";
    public $video = "Sin definir";

    public function __construct($artista, $genero, $nombreCancion, $audio, $video) {
        $this->artista = $artista;
        $this->genero = $genero;
        $this->nombreCancion = $nombreCancion;
        $this->audio = $audio;
        $this->video = $video;
    }

    // Función para obtener la información en formato array
    public function obtenerInformacion() {
        return array(
            "Artista" => $this->artista,
            "Género" => $this->genero,
            "Nombre Canción" => $this->nombreCancion,
            "Audio" => $this->audio,
            "Video" => $this->video
        );
    }
}

// Crear 10 objetos de tipo piezaMusical
$musicas = array(
    new piezaMusical("Artista 1", "Rock", "Canción 1", "audio1.mp3", "video1.mp4"),
    new piezaMusical("Artista 2", "Pop", "Canción 2", "audio2.mp3", "video2.mp4"),
    new piezaMusical("Artista 3", "Jazz", "Canción 3", "audio3.mp3", "video3.mp4"),
    new piezaMusical("Artista 4", "Reggae", "Canción 4", "audio4.mp3", "video4.mp4"),
    new piezaMusical("Artista 5", "Electrónica", "Canción 5", "audio5.mp3", "video5.mp4"),
    new piezaMusical("Artista 6", "Hip-hop", "Canción 6", "audio6.mp3", "video6.mp4"),
    new piezaMusical("Artista 7", "Salsa", "Canción 7", "audio7.mp3", "video7.mp4"),
    new piezaMusical("Artista 8", "Blues", "Canción 8", "audio8.mp3", "video8.mp4"),
    new piezaMusical("Artista 9", "Clásica", "Canción 9", "audio9.mp3", "video9.mp4"),
    new piezaMusical("Artista 10", "Country", "Canción 10", "audio10.mp3", "video10.mp4")
);

?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Piezas Musicales</title>
</head>
<body>
    <h1>Lista de Piezas Musicales</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Artista</th>
                <th>Género</th>
                <th>Nombre Canción</th>
                <th>Audio</th>
                <th>Video</th>
            </tr>
        </thead>
        <tbody>
            <?php
            // Mostrar la información de las 10 piezas musicales en la tabla
            foreach ($musicas as $musica) {
                echo "<tr>";
                echo "<td>" . $musica->artista . "</td>";
                echo "<td>" . $musica->genero . "</td>";
                echo "<td>" . $musica->nombreCancion . "</td>";
                echo "<td><audio controls><source src='" . $musica->audio . "' type='audio/mp3'></audio></td>";
                echo "<td><video width='320' height='240' controls><source src='" . $musica->video . "' type='video/mp4'></video></td>";
                echo "</tr>";
            }
            ?>
        </tbody>
    </table>
</body>
</html>

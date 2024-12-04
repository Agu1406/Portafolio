<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 4 de PHP</title>
</head>
<body>
    <h1>Tipos de variables en PHP</h1>
    <p>En PHP hay 3 tipos de variables, las cuales son "locales", "globales" y "estáticas". A continuación se explican sus diferencias:</p>
    <br>
    <ul>
        <li><strong>Local:</strong> 
            <p>Una variable local se define dentro de una función o un bloque de código, y solo es accesible dentro de ese ámbito específico. 
                Fuera de esa función, la variable deja de existir y no puede ser utilizada.</p>
        </li>
        <li><strong>Global:</strong> 
            <p>Las variables globales son aquellas que se definen fuera de cualquier función y están disponibles para todo el script. 
                Sin embargo, dentro de una función, las variables globales no son accesibles directamente, a menos que se utilice la 
                palabra clave <code>global</code> para importar esa variable dentro del ámbito de la función.</p>
        </li>
        <li><strong>Estática:</strong> 
            <p>Las variables estáticas en PHP se definen dentro de una función usando la palabra clave <code>static</code>. 
            Estas variables retienen su valor entre las llamadas a la función. Es decir, la primera vez que se ejecuta la función, 
            la variable estática se inicializa, y en ejecuciones posteriores conservará el valor anterior, en lugar de volver a 
            inicializarse.</p>
        </li>

        <?php 
        // Ejemplo de creación de una variable global
        $textoGlobal = "Hola mundo global.";

        // Ejemplo de función que utiliza una variable local
        function mostrarLocal() {
            // Variable local
            $textoLocal = "Hola desde una variable local.";
            echo "<p><strong>Local:</strong> $textoLocal</p>";
        }

        // Ejemplo de función que utiliza una variable global
        function mostrarGlobal() {
            // Para acceder a la variable global, usamos la palabra clave global
            global $textoGlobal;
            echo "<p><strong>Global:</strong> $textoGlobal</p>";
        }

        // Ejemplo de función que utiliza una variable estática
        function contarLlamadas() {
            // Variable estática, conserva su valor entre llamadas a la función
            static $contador = 0;
            $contador++;
            echo "<p><strong>Estática:</strong> La función ha sido llamada $contador veces.</p>";
        }

        // Llamamos a las funciones para mostrar los ejemplos
        mostrarLocal(); // Muestra el valor de una variable local
        mostrarGlobal(); // Muestra el valor de una variable global

        // Llamamos tres veces a la función contarLlamadas para mostrar cómo la variable estática conserva su valor
        contarLlamadas(); 
        contarLlamadas();
        contarLlamadas();

        // Si tratamos de acceder a una variable local o estática fuera de su función, obtendremos un error, ya que su ámbito es limitado.
    ?>

    <br>
    <p>En el ultimo caso para que se entienda mejor, si la variable no fuese estatica cada vez que llamaramos / utilizaramos el método
        / función de dicha variable, su valor se reiniciaria, pero al ser estatica, cada vez que se usa se guarda su valor para usos
        posterioress
    </p>
    </ul>
</body>
</html>
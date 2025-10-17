function categorias() {
    var xhttp = new XMLHttpRequest(); 

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            // Creamos la lista como un elemento DOM
            var lista = document.createElement("ul");

            // Extraemos los datos JSON de la respuesta y los guardamos en un array
            var categorias = JSON.parse(this.responseText); // Cambié this.responseText en lugar de this.response

            for (let i = 0; i < categorias.length; i++) {
                // Creamos cada elemento DOM li con el campo nombre de la categoria
                var e = document.createElement("li");
                e.innerHTML = categorias[i]["nombre"];

                // Añadimos cada elemento DOM li a la lista
                lista.appendChild(e);
            }

            let body = document.getElementById("principal");
            // Eliminamos el contenido actual de la sección principal
            body.innerHTML = "";
            body.appendChild(lista);
        }
    };

    xhttp.open("GET", "datos_categorias_json.php", true); 
    xhttp.send();

    // Para que no se siga el link que llama a esta función
    return false;
}
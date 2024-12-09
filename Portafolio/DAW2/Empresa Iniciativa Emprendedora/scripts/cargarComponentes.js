function cargarComponente(url, elemento) {
    fetch(url)
        .then(response => response.text())
        .then(data => {
            document.querySelector(elemento).innerHTML = data;
        })
        .catch(error => console.error('Error al cargar el componente:', error));
}

document.addEventListener('DOMContentLoaded', function() {
    cargarComponente('header.html', 'header');
    cargarComponente('footer.html', 'footer');
}); 
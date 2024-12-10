document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('.formulario');
    const fechaNacimiento = document.getElementById('fecha_nacimiento');
    const password = document.getElementById('password');
    const mostrarPassword = document.querySelector('.formulario__mostrar-password');

    // Validar edad mínima
    function validarEdad(fecha) {
        const hoy = new Date();
        const fechaNac = new Date(fecha);
        let edad = hoy.getFullYear() - fechaNac.getFullYear();
        const mes = hoy.getMonth() - fechaNac.getMonth();
        
        if (mes < 0 || (mes === 0 && hoy.getDate() < fechaNac.getDate())) {
            edad--;
        }
        
        return edad >= 65;
    }

    // Mostrar/ocultar contraseña
    mostrarPassword.addEventListener('click', function() {
        const tipo = password.getAttribute('type') === 'password' ? 'text' : 'password';
        password.setAttribute('type', tipo);
        this.textContent = tipo === 'password' ? '👁️' : '👁️‍🗨️';
    });

    // Validación del formulario
    form.addEventListener('submit', function(e) {
        e.preventDefault();

        if (!validarEdad(fechaNacimiento.value)) {
            alert('Debes ser mayor de 65 años para registrarte');
            return;
        }

        // Aquí iría la lógica para enviar el formulario al servidor
        console.log('Formulario válido, enviando datos...');
    });
}); 
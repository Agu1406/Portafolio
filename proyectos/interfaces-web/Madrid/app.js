// Datos de ejemplo para los negocios
const negocios = [
    {
        id: 1,
        nombre: "Uñas Glam",
        descripcion: "Manicura y pedicura profesional",
        ubicacion: "Avenida Luna 5",
        horario: "L-S: 10:00-21:00",
        imagen: "manicura.jpg"
    },
    {
        id: 2,
        nombre: "Bella Peluquería",
        descripcion: "Cortes y peinados modernos",
        ubicacion: "Calle Sol 12",
        horario: "L-V: 9:00-20:00",
        imagen: "peluqueria.jpg"
    },
    {
        id: 3,
        nombre: "Restaurante Sabor",
        descripcion: "Comida casera tradicional",
        ubicacion: "Plaza Mayor 1",
        horario: "L-D: 12:00-23:00",
        imagen: "restaurante.jpg"
    }
];

// Array para guardar reservas en memoria
let reservas = [];

// Referencias a los contenedores principales
const listaSection = document.getElementById('negocios-lista');
const detalleSection = document.getElementById('negocio-detalle');
const reservasSection = document.getElementById('reservas-lista');

// Referencias a los botones del menú
const btnInicio = document.getElementById('btn-inicio');
const btnVerReservas = document.getElementById('btn-ver-reservas');

// Navegación del menú superior
btnInicio.onclick = () => mostrarLista();
btnVerReservas.onclick = () => mostrarReservas();

// Mostrar la lista de negocios al cargar
mostrarLista();

// Función para mostrar la lista de negocios
function mostrarLista() {
    listaSection.innerHTML = '';
    detalleSection.style.display = 'none';
    reservasSection.style.display = 'none';
    listaSection.style.display = 'block';
    negocios.forEach(negocio => {
        // Crear la tarjeta de negocio
        const card = document.createElement('div');
        card.className = 'negocio-card';
        card.onclick = () => mostrarDetalle(negocio.id);
        card.innerHTML = `
            <img class="negocio-img" src="${negocio.imagen}" alt="${negocio.nombre}">
            <div class="negocio-info">
                <div class="negocio-nombre">${negocio.nombre}</div>
                <div class="negocio-desc">${negocio.descripcion}</div>
                <div class="negocio-ubicacion">📍 ${negocio.ubicacion}</div>
                <div class="negocio-horario">🕒 ${negocio.horario}</div>
            </div>
        `;
        listaSection.appendChild(card);
    });
}

// Función para mostrar el detalle de un negocio
function mostrarDetalle(id) {
    const negocio = negocios.find(n => n.id === id);
    if (!negocio) return;
    listaSection.style.display = 'none';
    reservasSection.style.display = 'none';
    detalleSection.style.display = 'block';
    let numPersonas = 1;
    // Renderizar el detalle y el formulario
    detalleSection.innerHTML = `
        <button class="volver-btn" onclick="mostrarLista()">&larr; Volver</button>
        <img class="detalle-img" src="${negocio.imagen}" alt="${negocio.nombre}">
        <div class="detalle-titulo">${negocio.nombre}</div>
        <div class="detalle-info">${negocio.descripcion}</div>
        <div class="detalle-info"><b>Ubicación:</b> ${negocio.ubicacion}</div>
        <div class="detalle-info"><b>Horario:</b> ${negocio.horario}</div>
        <form id="form-reserva">
            <input type="text" name="nombre" placeholder="Tu nombre" required>
            <input type="tel" name="telefono" placeholder="Teléfono" required>
            <input type="email" name="email" placeholder="Email" required>
            <div class="selector-personas">
                <label for="personas">Personas:</label>
                <button type="button" class="selector-btn" id="btn-restar">-</button>
                <span class="selector-num" id="num-personas">1</span>
                <button type="button" class="selector-btn" id="btn-sumar">+</button>
            </div>
            <textarea name="notas" placeholder="Notas adicionales"></textarea>
            <button type="submit">Reservar</button>
        </form>
    `;
    // Lógica para el selector de personas
    const numPersonasSpan = document.getElementById('num-personas');
    document.getElementById('btn-restar').onclick = () => {
        if (numPersonas > 1) {
            numPersonas--;
            numPersonasSpan.textContent = numPersonas;
        }
    };
    document.getElementById('btn-sumar').onclick = () => {
        numPersonas++;
        numPersonasSpan.textContent = numPersonas;
    };
    // Lógica para el formulario de reserva
    document.getElementById('form-reserva').onsubmit = function(event) {
        event.preventDefault();
        const form = event.target;
        const reserva = {
            id: Date.now(),
            negocioId: negocio.id,
            negocioNombre: negocio.nombre,
            nombre: form.nombre.value,
            telefono: form.telefono.value,
            email: form.email.value,
            personas: numPersonas,
            notas: form.notas.value
        };
        reservas.push(reserva);
        alert(`¡Reserva realizada con éxito en ${negocio.nombre}!`);
        mostrarReservas();
        return false;
    };
}

// Función para mostrar la lista de reservas
function mostrarReservas() {
    listaSection.style.display = 'none';
    detalleSection.style.display = 'none';
    reservasSection.style.display = 'block';
    reservasSection.innerHTML = '<h2>Mis Reservas</h2>';
    if (reservas.length === 0) {
        reservasSection.innerHTML += '<p>No tienes reservas realizadas.</p>';
        return;
    }
    reservas.forEach(reserva => {
        const negocio = negocios.find(n => n.id === reserva.negocioId);
        const card = document.createElement('div');
        card.className = 'reserva-card';
        card.innerHTML = `
            <div class="reserva-negocio">${reserva.negocioNombre}</div>
            <div class="reserva-info"><b>Nombre:</b> ${reserva.nombre}</div>
            <div class="reserva-info"><b>Teléfono:</b> ${reserva.telefono}</div>
            <div class="reserva-info"><b>Email:</b> ${reserva.email}</div>
            <div class="reserva-info"><b>Personas:</b> ${reserva.personas}</div>
            <div class="reserva-info"><b>Notas:</b> ${reserva.notas || '-'}</div>
            <button class="reserva-cancelar-btn">Cancelar</button>
        `;
        // Botón para cancelar la reserva
        card.querySelector('.reserva-cancelar-btn').onclick = () => {
            if (confirm('¿Seguro que quieres cancelar esta reserva?')) {
                reservas = reservas.filter(r => r.id !== reserva.id);
                mostrarReservas();
            }
        };
        reservasSection.appendChild(card);
    });
} 
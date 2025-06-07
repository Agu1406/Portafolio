// Datos de ejemplo (en una aplicación real, estos vendrían de una API)
const negocios = [
    {
        id: 1,
        nombre: "Restaurante La Terraza",
        descripcion: "Cocina mediterránea con vistas al mar",
        imagen: "https://picsum.photos/800/600?random=1",
        valoracion: 4.5,
        ubicacion: "Calle del Mar, 123",
        categoria: "restaurante",
        horario: "12:00-23:00"
    },
    {
        id: 2,
        nombre: "Peluquería Style",
        descripcion: "Estilistas profesionales para todos los estilos",
        imagen: "https://picsum.photos/800/600?random=2",
        valoracion: 4.8,
        ubicacion: "Avenida Principal, 45",
        categoria: "peluqueria",
        horario: "10:00-20:00"
    },
    {
        id: 3,
        nombre: "Salón de Uñas Glamour",
        descripcion: "Manicura y pedicura profesional",
        imagen: "https://picsum.photos/800/600?random=3",
        valoracion: 4.7,
        ubicacion: "Plaza Central, 7",
        categoria: "manicura",
        horario: "09:00-21:00"
    }
];

// Estado de la aplicación
let reservas = JSON.parse(localStorage.getItem('reservas')) || [];
let negocioSeleccionado = null;

// Funciones de utilidad
function formatDate(date) {
    return new Date(date).toLocaleString('es-ES', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    });
}

function generarId() {
    return Date.now().toString(36) + Math.random().toString(36).substr(2);
}

// Funciones de navegación
function navegarA(pagina) {
    document.querySelectorAll('.page').forEach(p => p.classList.remove('active'));
    document.querySelectorAll('.nav-link').forEach(l => l.classList.remove('active'));
    
    document.getElementById(`${pagina}-page`).classList.add('active');
    document.querySelector(`[data-page="${pagina}"]`).classList.add('active');
}

// Funciones de renderizado
function renderizarNegocios() {
    const contenedor = document.getElementById('negocios-list');
    contenedor.innerHTML = negocios.map(negocio => `
        <div class="col-md-4">
            <div class="card negocio-card">
                <img src="${negocio.imagen}" class="card-img-top" alt="${negocio.nombre}">
                <div class="card-body">
                    <h5 class="card-title">${negocio.nombre}</h5>
                    <div class="valoracion">
                        ${'★'.repeat(Math.floor(negocio.valoracion))}${negocio.valoracion % 1 ? '½' : ''} ${negocio.valoracion}
                    </div>
                    <p class="card-text">${negocio.descripcion}</p>
                    <p class="ubicacion">
                        <i class="icon ion-md-location"></i>${negocio.ubicacion}
                    </p>
                    <p class="horario">
                        <i class="icon ion-md-time"></i>${negocio.horario}
                    </p>
                    <button class="btn btn-primary btn-action" onclick="abrirModalReserva(${negocio.id})">
                        Reservar
                    </button>
                </div>
            </div>
        </div>
    `).join('');
}

function renderizarReservas() {
    const contenedor = document.getElementById('reservas-list');
    if (reservas.length === 0) {
        contenedor.innerHTML = `
            <div class="alert alert-info">
                No tienes reservas pendientes
            </div>
        `;
        return;
    }

    contenedor.innerHTML = reservas.map(reserva => {
        const negocio = negocios.find(n => n.id === reserva.negocioId);
        return `
            <div class="reserva-card">
                <div class="d-flex justify-content-between align-items-center mb-2">
                    <h5 class="mb-0">${negocio ? negocio.nombre : 'Negocio no encontrado'}</h5>
                    <span class="estado ${reserva.estado.toLowerCase()}">${reserva.estado}</span>
                </div>
                <p class="mb-1">
                    <i class="icon ion-md-calendar"></i>${formatDate(reserva.fecha)}
                </p>
                <p class="mb-1">
                    <i class="icon ion-md-person"></i>${reserva.nombre}
                </p>
                <p class="mb-1">
                    <i class="icon ion-md-people"></i>${reserva.personas} personas
                </p>
                ${reserva.notas ? `<p class="mb-1"><i class="icon ion-md-chatbubbles"></i>${reserva.notas}</p>` : ''}
                ${reserva.estado === 'PENDIENTE' ? `
                    <button class="btn btn-danger btn-sm mt-2" onclick="cancelarReserva('${reserva.id}')">
                        Cancelar Reserva
                    </button>
                ` : ''}
            </div>
        `;
    }).join('');
}

// Funciones de gestión de reservas
function abrirModalReserva(negocioId) {
    negocioSeleccionado = negocios.find(n => n.id === negocioId);
    document.getElementById('reserva-form').reset();
    new bootstrap.Modal(document.getElementById('reservaModal')).show();
}

function crearReserva(evento) {
    evento.preventDefault();
    
    const nuevaReserva = {
        id: generarId(),
        negocioId: negocioSeleccionado.id,
        nombre: document.getElementById('nombre').value,
        telefono: document.getElementById('telefono').value,
        email: document.getElementById('email').value,
        fecha: document.getElementById('fecha').value,
        personas: parseInt(document.getElementById('personas').value),
        notas: document.getElementById('notas').value,
        estado: 'PENDIENTE',
        fechaCreacion: new Date().toISOString()
    };

    reservas.push(nuevaReserva);
    localStorage.setItem('reservas', JSON.stringify(reservas));
    
    bootstrap.Modal.getInstance(document.getElementById('reservaModal')).hide();
    renderizarReservas();
    
    // Mostrar mensaje de éxito
    const alerta = document.createElement('div');
    alerta.className = 'alert alert-success alert-dismissible fade show';
    alerta.innerHTML = `
        Reserva realizada con éxito
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    document.querySelector('.container').insertBefore(alerta, document.querySelector('.page'));
}

function cancelarReserva(reservaId) {
    if (confirm('¿Estás seguro de que deseas cancelar esta reserva?')) {
        reservas = reservas.map(r => 
            r.id === reservaId ? {...r, estado: 'CANCELADA'} : r
        );
        localStorage.setItem('reservas', JSON.stringify(reservas));
        renderizarReservas();
    }
}

// Inicialización
document.addEventListener('DOMContentLoaded', () => {
    // Configurar navegación
    document.querySelectorAll('.nav-link').forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            navegarA(e.target.dataset.page);
        });
    });

    // Configurar formulario de reserva
    document.getElementById('confirmar-reserva').addEventListener('click', crearReserva);

    // Renderizar contenido inicial
    renderizarNegocios();
    renderizarReservas();
}); 
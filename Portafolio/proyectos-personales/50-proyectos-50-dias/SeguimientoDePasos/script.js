const progreso = document.getElementById('progreso')
const anterior = document.getElementById('anterior')
const siguiente = document.getElementById('siguiente')
const circulos = document.querySelectorAll('.circulo')

let actualActivo = 1

siguiente.addEventListener('click', () => {
    actualActivo++

    if(actualActivo > circulos.length) {
        actualActivo = circulos.length
    }

    actualizar()
})

anterior.addEventListener('click', () => {
    actualActivo--

    if(actualActivo < 1) {
        actualActivo = 1
    }

    actualizar()
})

function actualizar() {
    circulos.forEach((circulo, idx) => {
        if(idx < actualActivo) {
            circulo.classList.add('activo')
        } else {
            circulo.classList.remove('activo')
        }
    })

    const activos = document.querySelectorAll('.activo')

    progreso.style.width = (activos.length - 1) / (circulos.length - 1) * 100 + '%'

    if(actualActivo === 1) {
        anterior.disabled = true
    } else if(actualActivo === circulos.length) {
        siguiente.disabled = true
    } else {
        anterior.disabled = false
        siguiente.disabled = false
    }
}
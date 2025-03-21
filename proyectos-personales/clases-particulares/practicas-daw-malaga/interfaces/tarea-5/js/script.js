/**
 * script.js
 * Archivo principal de JavaScript para la página de animaciones
 * Contiene todas las funcionalidades de las diferentes secciones
 */

// Esperar a que el DOM esté completamente cargado
document.addEventListener('DOMContentLoaded', function() {
    // ==================================================
    // CONFIGURACIÓN GENERAL
    // ==================================================
    
    // Ya no necesitamos gestionar el menú de navegación con JavaScript,
    // ahora usamos el selector :target de CSS
    
    // ==================================================
    // EJERCICIO 2: TRANSFORMACIONES
    // ==================================================
    
    // Referencias a elementos
    const bolaElement = document.querySelector('.bola-billar');
    const numero = document.querySelector('.numero');
    const textoTransformacion = document.getElementById('transformacion-texto');
    
    // Botones de transformación
    const btnEscalar = document.getElementById('btn-escalar');
    const btnDesplazar = document.getElementById('btn-desplazar');
    const btnRotar = document.getElementById('btn-rotar');
    
    // Estado original de la bola
    let estadoOriginal = true;
    
    /**
     * Función para restablecer la bola a su estado original
     */
    function resetearBola() {
        bolaElement.style.transform = 'none';
        numero.style.transform = 'none';
        estadoOriginal = true;
    }
    
    // Evento para escalar
    btnEscalar.addEventListener('click', function() {
        resetearBola();
        if (estadoOriginal) {
            bolaElement.style.transform = 'scale(1.5)';
            textoTransformacion.textContent = 'Transformación: Escalado (scale)';
            estadoOriginal = false;
        } else {
            resetearBola();
        }
    });
    
    // Evento para desplazar
    btnDesplazar.addEventListener('click', function() {
        resetearBola();
        if (estadoOriginal) {
            bolaElement.style.transform = 'translateX(80px)';
            textoTransformacion.textContent = 'Transformación: Desplazamiento (translate)';
            estadoOriginal = false;
        } else {
            resetearBola();
        }
    });
    
    // Evento para rotar
    btnRotar.addEventListener('click', function() {
        resetearBola();
        if (estadoOriginal) {
            bolaElement.style.transform = 'translateX(50px) rotate(360deg)';
            numero.style.transform = 'rotate(360deg)';
            textoTransformacion.textContent = 'Transformación: Rotación y desplazamiento';
            estadoOriginal = false;
        } else {
            resetearBola();
        }
    });
    
    // ==================================================
    // EJERCICIO 3: TRANSICIONES
    // ==================================================
    
    // Referencia a la segunda tarjeta (la que se activa con clic)
    const secondCard = document.querySelectorAll('.card')[1];
    
    // Evento de clic para la segunda tarjeta
    secondCard.addEventListener('click', function() {
        this.classList.toggle('clicked');
    });
    
    // ==================================================
    // EJERCICIO 4: ANIMACIONES CSS
    // ==================================================
    
    // Referencias a los elementos de animación CSS
    const animationContainer = document.querySelector('.animation-container');
    const animatedElements = document.querySelectorAll('.sol, .luna, .estrella, .nube');
    
    // Botones de control de animación
    const btnPlayAnimation = document.getElementById('btn-play-animation');
    const btnPauseAnimation = document.getElementById('btn-pause-animation');
    const btnResetAnimation = document.getElementById('btn-reset-animation');
    
    // Evento para reproducir la animación
    btnPlayAnimation.addEventListener('click', function() {
        animatedElements.forEach(element => {
            element.classList.remove('paused');
        });
    });
    
    // Evento para pausar la animación
    btnPauseAnimation.addEventListener('click', function() {
        animatedElements.forEach(element => {
            element.classList.add('paused');
        });
    });
    
    // Evento para reiniciar la animación
    btnResetAnimation.addEventListener('click', function() {
        animatedElements.forEach(element => {
            // Detener la animación
            element.style.animation = 'none';
            
            // Forzar un reflow para que se aplique el cambio
            element.offsetHeight;
            
            // Reiniciar la animación
            element.style.animation = '';
            element.classList.remove('paused');
        });
    });
    
    // ==================================================
    // EJERCICIO 5: ANIMACIONES CANVAS
    // ==================================================
    
    // Referencia al canvas y su contexto
    const canvas = document.getElementById('miCanvas');
    const ctx = canvas.getContext('2d');
    
    // Botones de control del canvas
    const btnStartCanvas = document.getElementById('btn-start-canvas');
    const btnStopCanvas = document.getElementById('btn-stop-canvas');
    const btnResetCanvas = document.getElementById('btn-reset-canvas');
    
    // Variables para la animación de partículas
    let particulas = [];
    let animacionCanvas;
    let animacionActiva = false;
    
    /**
     * Clase para crear partículas en el canvas
     */
    class Particula {
        constructor() {
            this.x = Math.random() * canvas.width;
            this.y = 0;
            this.velocidad = 1 + Math.random() * 3;
            this.radio = 1 + Math.random() * 2;
            this.color = `rgba(255, 255, 255, ${0.5 + Math.random() * 0.5})`;
        }
        
        /**
         * Actualiza la posición de la partícula
         */
        actualizar() {
            this.y += this.velocidad;
            
            // Si la partícula sale del canvas, restablecerla
            if (this.y > canvas.height) {
                this.y = 0;
                this.x = Math.random() * canvas.width;
            }
        }
        
        /**
         * Dibuja la partícula en el canvas
         */
        dibujar() {
            ctx.beginPath();
            ctx.arc(this.x, this.y, this.radio, 0, Math.PI * 2);
            ctx.fillStyle = this.color;
            ctx.fill();
        }
    }
    
    /**
     * Inicializa las partículas
     */
    function inicializarParticulas() {
        particulas = [];
        for (let i = 0; i < 50; i++) {
            particulas.push(new Particula());
        }
    }
    
    /**
     * Función de animación para el canvas
     */
    function animarCanvas() {
        // Limpiar el canvas
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        
        // Actualizar y dibujar cada partícula
        particulas.forEach(particula => {
            particula.actualizar();
            particula.dibujar();
        });
        
        // Continuar la animación
        if (animacionActiva) {
            animacionCanvas = requestAnimationFrame(animarCanvas);
        }
    }
    
    // Inicializar las partículas
    inicializarParticulas();
    
    // Evento para iniciar la animación del canvas
    btnStartCanvas.addEventListener('click', function() {
        if (!animacionActiva) {
            animacionActiva = true;
            animarCanvas();
        }
    });
    
    // Evento para detener la animación del canvas
    btnStopCanvas.addEventListener('click', function() {
        animacionActiva = false;
        if (animacionCanvas) {
            cancelAnimationFrame(animacionCanvas);
        }
    });
    
    // Evento para reiniciar la animación del canvas
    btnResetCanvas.addEventListener('click', function() {
        animacionActiva = false;
        if (animacionCanvas) {
            cancelAnimationFrame(animacionCanvas);
        }
        inicializarParticulas();
        ctx.clearRect(0, 0, canvas.width, canvas.height);
    });
    
    // ==================================================
    // EJERCICIO 6: ANIMACIONES SVG
    // ==================================================
    
    // Referencias a elementos SVG
    const svgCirculo = document.getElementById('circulo');
    const svgRectangulo = document.getElementById('rectangulo');
    const svgTriangulo = document.getElementById('triangulo');
    const svgCamino = document.getElementById('camino');
    
    // Botones de control SVG
    const btnAnimarSVG = document.getElementById('btn-animar-svg');
    const btnDetenerSVG = document.getElementById('btn-detener-svg');
    const btnReiniciarSVG = document.getElementById('btn-reiniciar-svg');
    
    // Variables para las animaciones SVG
    let animacionesActivasSVG = false;
    
    /**
     * Función para animar un elemento SVG
     * @param {HTMLElement} elemento - El elemento SVG a animar
     * @param {string} propiedad - La propiedad CSS a animar
     * @param {string} valor - El valor final de la propiedad
     * @param {number} duracion - La duración de la animación en segundos
     */
    function animarElementoSVG(elemento, propiedad, valor, duracion) {
        elemento.style.transition = `${propiedad} ${duracion}s ease-in-out`;
        elemento.style[propiedad] = valor;
    }
    
    // Evento para animar los elementos SVG
    btnAnimarSVG.addEventListener('click', function() {
        if (!animacionesActivasSVG) {
            animacionesActivasSVG = true;
            
            // Animar el círculo
            animarElementoSVG(svgCirculo, 'transform', 'translateX(200px)', 2);
            
            // Animar el rectángulo
            animarElementoSVG(svgRectangulo, 'transform', 'scale(1.5) rotate(45deg)', 3);
            
            // Animar el triángulo
            animarElementoSVG(svgTriangulo, 'transform', 'translateY(-30px)', 2.5);
            
            // Animar el camino
            svgCamino.style.transition = 'stroke-dashoffset 4s linear';
            svgCamino.style.strokeDasharray = '200';
            svgCamino.style.strokeDashoffset = '0';
        }
    });
    
    // Evento para detener las animaciones SVG
    btnDetenerSVG.addEventListener('click', function() {
        if (animacionesActivasSVG) {
            // Pausar todas las animaciones
            [svgCirculo, svgRectangulo, svgTriangulo, svgCamino].forEach(elemento => {
                const computedStyle = window.getComputedStyle(elemento);
                const transform = computedStyle.getPropertyValue('transform');
                const strokeDashoffset = computedStyle.getPropertyValue('stroke-dashoffset');
                
                // Eliminar la transición pero mantener el estado actual
                elemento.style.transition = 'none';
                elemento.style.transform = transform;
                if (elemento === svgCamino) {
                    elemento.style.strokeDashoffset = strokeDashoffset;
                }
            });
            
            animacionesActivasSVG = false;
        }
    });
    
    // Evento para reiniciar las animaciones SVG
    btnReiniciarSVG.addEventListener('click', function() {
        animacionesActivasSVG = false;
        
        // Reiniciar todos los elementos
        [svgCirculo, svgRectangulo, svgTriangulo, svgCamino].forEach(elemento => {
            elemento.style.transition = 'none';
            elemento.style.transform = 'none';
            
            if (elemento === svgCamino) {
                elemento.style.strokeDasharray = 'none';
                elemento.style.strokeDashoffset = 'none';
            }
            
            // Forzar un reflow para que se apliquen los cambios
            elemento.offsetHeight;
        });
    });
}); 
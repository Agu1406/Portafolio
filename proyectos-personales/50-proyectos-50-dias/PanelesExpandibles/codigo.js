/**
 * Código JavaScrip que permite "añadir" o "remover" el estado "activo"
 * de los paneles para que el codigo sea responsivo y se adapte según
 * se haga click en un panel u otro.
 */

/** 
 * Creamos una constante llamada "paneles" que selecciona
 * todos los paneles y los guarda en "paneles" para su
 * uso o modificación.
 * */
const paneles = document.querySelectorAll('.panel')

/**
 * Utilizando un bucle "forEach" se recorren todos los paneles,
 * acto seguido, se crear un "EventListener", en caso de que se
 * haga "click" en un panel, se llamara al método/función llamado
 * "removerActivo" que removera la palabra "activo" de todos los
 * paneles, luego de que todos están "inactivos", al panel que
 * haya sido clickeado se le agragra el "activo", dejandolo como
 * el unico panel activo actualmente.
 */
paneles.forEach(panel => {
    panel.addEventListener('click', () => {
        removerActivo()
        panel.classList.add('activo')
    })
})

/**
 * Cuando este método es llamado, recorre todos los paneles existente
 * uno por uno removiendo de cada uno de ellos la palabra/atributo
 * "activo" si los tuviesen.
 */
function removerActivo() {
    paneles.forEach(panel => {
        panel.classList.remove('activo')
    })
}
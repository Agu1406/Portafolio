// Creamos las dos variables que utilizaremos en el script con sus valores predefinidos
const A = 6, B = 2;

// Llamamos al método / función que nos permite elegir un operador.
chooseOperator();


function chooseOperator() {
    // Enviamos un pop-up solicitando que se elija un operador.
    console.log('Eligiendo operador...');
    // Definimos el valor de la variabler "operator" solicitando está por pantalla.
    let operator = prompt('Indica la operación deseada escribiendo + , -, * o /', '+');

    // Indícamos que operación es está
    console.log('La operación es ' + operator);

    // Llamamos a un método externo llamado "operate" que en base a la elección realiza una operación
    operate(operator); // FALLARÁ
}


function operate(operator) {
    console.log('Operando...');
    switch (operator) {
        case '+':
            result = A + B;
            console.log('La suma es: ' + result);
            break;
        case '-':
            result = A - B;
            console.log('La diferencia es: ' + result);
            break;
        case '*':
            result = A * B;
            console.log('El producto es: ' + result);
            break;
        case '/':
            result = A / B;
            console.log('El cociente es: ' + result);
            break;
        default:
            console.log('Error, operador invilado, intentalo otra vez')
            chooseOperator();
            
    }
}

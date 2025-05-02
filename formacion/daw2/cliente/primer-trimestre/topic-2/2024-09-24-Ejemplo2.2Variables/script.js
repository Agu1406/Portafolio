const A = 6,
    B = 2;
chooseOperator();
operate(operator); // FALLARÁ


function chooseOperator() {
    console.log('Eligiendo operador...');
    let operator = prompt('Indica la operación deseada escribiendo + , -, * o /', '+');
    console.log('La operación es ' + operator);
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
            chooseOperator();
    }
}

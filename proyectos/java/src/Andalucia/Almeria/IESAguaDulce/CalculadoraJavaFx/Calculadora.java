package Andalucia.Almeria.IESAguaDulce.CalculadoraJavaFx;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.objecthunter.exp4j.ExpressionBuilder;
/**
 * La típica calculadora básica para realizar cálculos artitméticos como la
 * suma, resta, multiplicación y división, en la que se permite el cálculo de
 * expresiones combinadas.
 *
 * @author profesorado
 */
public class Calculadora extends Application {
    //----------------------------------------------
    //          Declaración de variables 
    //----------------------------------------------
    //Declaramos el área de texto y los botones que vamos a usar en la aplicación

    private TextField display;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button btnSuma, btnResta, btnMultiplicacion, btnDivision;
    private Button btnIgual, btnDecimal, btnLimpiar, btnBorrar, btnParentesisIzq, btnParentesisDer;

    //----------------------------------------------
    //          Declaración de variables auxiliares 
    //----------------------------------------------  
    private String textoActual;//La usamos para guardar el texto del display
    private String regexOperadores = "[+\\-*/]"; //Para verificar si es un operador
    private String regexNumero = "[0-9]"; //para verificar si es un número
    private String regexPermitidos = "[0-9+\\-*/.()]*"; //verificar la expresión.
    private String ultimoCaracter;//Para guardar el último carácter del display
    private String ultimoNumero;
    private String[] arrayNumeros;
    int parentesisAbiertos = 0;//Para contar el número de caracter abiertos o cerrados
    int parentesisCerrados = 0;
    char caracter;

    @Override
    public void start(Stage escenario) throws Exception {
        //Asigamos al escenario el título "Mi calculadora",
        escenario.setTitle("Mi calculadora");

        //Establecemos que no sea reajustable en tamaño,
        escenario.setResizable(false);

        // Asignamos el icono icono incorporado en el proyecto
        try {
            Image icono = new Image("file:src/tarea09/logoCalcu.png");
            escenario.getIcons().add(icono);
        } catch (Exception e) {
            System.out.println("Error al cargar el icono: " + e.getMessage());
        }

        // Creamos el contenedor raiz (AnchorPane)
        AnchorPane root = new AnchorPane();

        // Creamos la salida o display con TexField
        display = new TextField();
        //Hacemos que la salida no sea editable
        display.setEditable(false);

        //Instanciamos los botones y los inicializamos
        btn0 = new Button("0");
        btn1 = new Button("1");
        btn2 = new Button("2");
        btn3 = new Button("3");
        btn4 = new Button("4");
        btn5 = new Button("5");
        btn6 = new Button("6");
        btn7 = new Button("7");
        btn8 = new Button("8");
        btn9 = new Button("9");
        btnSuma = new Button("+");
        btnResta = new Button("-");
        btnMultiplicacion = new Button("*");
        btnDivision = new Button("/");
        btnIgual = new Button("=");
        btnDecimal = new Button(".");
        btnLimpiar = new Button("c");
        btnBorrar = new Button("<");
        btnParentesisIzq = new Button("(");
        btnParentesisDer = new Button(")");

        //Creamos un contenedor de tipo GridPane (4 filas x 5 columnas)
        GridPane teclado = new GridPane();
        teclado.setHgap(5); // Le damos un ancho horizontal a las celdas
        teclado.setVgap(5); // Le damos un ancho vertical a las celdas

        //Posicionamos los botones en el grid pane
        // Fila 0
        teclado.add(btn7, 0, 0);
        teclado.add(btn8, 1, 0);
        teclado.add(btn9, 2, 0);
        teclado.add(btnDivision, 3, 0);
        teclado.add(btnParentesisIzq, 4, 0);

        // Fila 1
        teclado.add(btn4, 0, 1);
        teclado.add(btn5, 1, 1);
        teclado.add(btn6, 2, 1);
        teclado.add(btnMultiplicacion, 3, 1);
        teclado.add(btnParentesisDer, 4, 1);

        // Fila 2
        teclado.add(btn1, 0, 2);
        teclado.add(btn2, 1, 2);
        teclado.add(btn3, 2, 2);
        teclado.add(btnResta, 3, 2);
        teclado.add(btnDecimal, 4, 2);

        // Fila 3
        teclado.add(btn0, 0, 3);
        teclado.add(btnLimpiar, 1, 3);
        teclado.add(btnBorrar, 2, 3);
        teclado.add(btnSuma, 3, 3);
        teclado.add(btnIgual, 4, 3);

        //añadimos el display y el teclado al contenedor raiz
        root.getChildren().addAll(display, teclado);

        //Definimos la distancia de los nodos hijos respecto a su contenedor
        AnchorPane.setTopAnchor(display, 15.0);
        AnchorPane.setLeftAnchor(display, 15.0);
        AnchorPane.setRightAnchor(display, 15.0);
        AnchorPane.setTopAnchor(teclado, 80.0);
        AnchorPane.setLeftAnchor(teclado, 15.0);
        AnchorPane.setRightAnchor(teclado, 15.0);
        AnchorPane.setBottomAnchor(teclado, 15.0);

        //Añadirmos el contenedor a la escena. Le damos un ancho y un alto
        Scene escena = new Scene(root, 360, 390);

        //Aplicamos estilos a la escena
        escena.getStylesheets().add("file:src/tarea09/calculadora.css");

        //Añadimos estilos individuales a algunos elementos
        btnSuma.getStyleClass().addAll("button", "operador");
        btnResta.getStyleClass().addAll("button", "operador");
        btnMultiplicacion.getStyleClass().addAll("button", "operador");
        btnDivision.getStyleClass().addAll("button", "operador");
        btnParentesisDer.getStyleClass().addAll("button", "operador");
        btnParentesisIzq.getStyleClass().addAll("button", "operador");
        btnDecimal.getStyleClass().addAll("button", "operador");
        btnIgual.getStyleClass().addAll("button", "igual");
        btnLimpiar.getStyleClass().addAll("button", "limpiar");
        btnBorrar.getStyleClass().addAll("button", "limpiar");

        // Asignamos el método procesoDeEntrada a los eventos de los botones
        btn0.setOnAction(event -> procesoDeEntrada("0"));
        btn1.setOnAction(event -> procesoDeEntrada("1"));
        btn2.setOnAction(event -> procesoDeEntrada("2"));
        btn3.setOnAction(event -> procesoDeEntrada("3"));
        btn4.setOnAction(event -> procesoDeEntrada("4"));
        btn5.setOnAction(event -> procesoDeEntrada("5"));
        btn6.setOnAction(event -> procesoDeEntrada("6"));
        btn7.setOnAction(event -> procesoDeEntrada("7"));
        btn8.setOnAction(event -> procesoDeEntrada("8"));
        btn9.setOnAction(event -> procesoDeEntrada("9"));

        btnSuma.setOnAction(event -> procesoDeEntrada("+"));
        btnResta.setOnAction(event -> procesoDeEntrada("-"));
        btnMultiplicacion.setOnAction(event -> procesoDeEntrada("*"));
        btnDivision.setOnAction(event -> procesoDeEntrada("/"));

        btnIgual.setOnAction(event -> procesoDeEntrada("="));
        btnDecimal.setOnAction(event -> procesoDeEntrada("."));
        btnLimpiar.setOnAction(event -> procesoDeEntrada("c"));
        btnBorrar.setOnAction(event -> procesoDeEntrada("<"));
        btnParentesisIzq.setOnAction(event -> procesoDeEntrada("("));
        btnParentesisDer.setOnAction(event -> procesoDeEntrada(")"));

        //añadimos la escena al escenario
        escenario.setScene(escena);
        //Mostramos el escenario
        escenario.show();
    }

    /**
     * El método procesoDeEntrada maneja la entrada de datos en una calculadora.
     * Toma una cadena de texto (entrada) y realiza diferentes acciones según el
     * contenido de esa cadena, agregando al campo de texto, estableciendo el
     * estado, controlando la adición de puntos decimales para evitar múltiples
     * decimales en un número o evaluando la expresión mátemática para mostrar
     * el resultado haciendo uso de la librería Exp4J.
     *
     * @param entrada Texto recogido de los diferentes textoBotones de la
     *                calculadora.
     */
    public void procesoDeEntrada(String entrada) {
        //Al pulsar un dígito, se acepta la entrada
        //Evaluamos si la entrada es un número
        if (entrada.matches(regexNumero)) {
            // Si lo es, guardamos en una variable el texto que aparece en el display actualmente
            textoActual = display.getText();
            // Verificamos si el texto actual comienza por 0 y la nueva entrada que le sigue no es un punto.
            if (textoActual.equals("0") && !entrada.equals(".")) {
                // Si es la nueva entrada no es un punto, reemplazamos el 0 con la nueva entrada.
                display.setText(entrada);
            } else {
                // En otros casos, concatenamos la nueva entrada al texto existente.
                display.setText(textoActual.concat(entrada));
            }
        }

        //Al pulsar  'C' la calculadora borra todo el contenido.
        if (entrada.equals("c")) {
            // Usamos el método clear() para borrar todo el display
            display.clear();
        }

        //Al pulsar '<' se borra el último carácter si existe.
        if (entrada.equals("<")) {
            textoActual = display.getText();
            //Verificamos si el textoActual no está vacío
            //Si está vacío no hacemos nada
            if (!textoActual.isEmpty()) {
                //Si no está vacío establecemos un nuevo texto que será un substring desde el índice 0
                //hasta el penúltimo índice
                display.setText(textoActual.substring(0, textoActual.length() - 1));
            }
        }

        //Al pulsar '.' se tiene en cuenta que es un número decimal.
        if (entrada.equals(".")) {
            textoActual = display.getText();
            //Queremos impedir que se escriba 2 o más puntos seguidos
            //pero queremos que haya 2 o más números decimales
            //Separamos los números siguiendo varios tipos de separadores
            //Todos los números se guardan en un array de String
            String[] arrayNumeros = textoActual.split("[+\\-*/()]");

            //Guardamos el último número.
            ultimoNumero = arrayNumeros[arrayNumeros.length - 1];

            //Si el display está vacío automáticamente añadirá "0."
            if (ultimoNumero.isEmpty()) {
                display.setText(textoActual + "0.");
                //En caso contrario se evalúa si es número es o no decimal
            } else if (!ultimoNumero.contains(".")) {
                display.setText(textoActual + ".");
            }
        }

        // Al pulsar '+', '-', '*' ó '/', se añadirá el operador si el último carácter es un dígito o
        //un caracter de cierre.
        //Verificamos si la entrada es un operador
        if (entrada.matches(regexOperadores)) {
            textoActual = display.getText();
            //Verificamos que el display no esté vacío
            if (!textoActual.isEmpty()) {
                //Guardamos el ultimo caracter del display actual
                ultimoCaracter = textoActual.substring(textoActual.length() - 1);
                //Verificamos que empieza por un número o termina por un caracter cerrado
                if (ultimoCaracter.matches("[0-9)]")) {
                    display.setText(textoActual.concat(entrada));
                }
            }
        }

        //Al pulsar '(', si el último carácter es un dígito, se inserta implícitamente un '*' antes del caracter.
        if (entrada.equals("(")) {
            String textoActual = display.getText();
            //Si el actual texto está vacío añadimos un caracter
            if (textoActual.isEmpty()) {
                display.setText("(");
            } else {
                //en caso contrario guardamos el último carácter
                ultimoCaracter = textoActual.substring(textoActual.length() - 1);
                // Permitir "(" después de operadores o al inicio
                if (ultimoCaracter.matches(regexOperadores) || textoActual.isEmpty()) {
                    display.setText(textoActual + "(");
                } // Si el último carácter es un dígito se inserta implícitamente un '*' antes del caracter.
                else if (ultimoCaracter.matches(regexNumero)) {
                    display.setText(textoActual + "*(");
                } // En cualquier otro caso añadimos el caracter
                else {
                    display.setText(textoActual + "(");
                }
            }
        }

        //Al pulsar ')', se agrega un caracter de cierre si existe uno abierto sin cerrar, validando que el
        //carácter previo sea un dígito o un ')'.
        if (entrada.equals(")")) {
            String textoActual = display.getText();

            //Reiniciamos los valores con cada nueva entrada
            parentesisAbiertos = 0;
            parentesisCerrados = 0;

            // Usamos un bucle for para contar los caracter de apertura y cierre
            for (int i = 0; i < textoActual.length(); i++) {
                caracter = textoActual.charAt(i);
                if (caracter == '(') {
                    parentesisAbiertos++;
                }
                if (caracter == ')') {
                    parentesisCerrados++;
                }
            }
            // Verficiamos que haya paréntesis abiertos sin cerrar
            if (parentesisAbiertos > parentesisCerrados) {
                // Verificamos que el display no esté vacío
                if (!textoActual.isEmpty()) {
                    ultimoCaracter = textoActual.substring(textoActual.length() - 1);
                    // Verificamos si el carácter previo es un dígito o un ')'
                    if (ultimoCaracter.matches(regexNumero) || ultimoCaracter.equals(")")) {
                        display.setText(textoActual + ")");
                    }
                }
            }
        }

        //Al pulsar '=', se realiza el cálculo y se muestra el resultado.
        if (entrada.equals("=")) {
            String textoActual = display.getText();

            //Que no evalue la expresión si ya hay un igual en el display
            //Esto significa que ya se hizo una operación con anterioridad
            if (!textoActual.contains("=")) {
                //Validamos que el display no esté vacío y que los caracteres sean los permitidos
                if (!textoActual.isEmpty() && textoActual.matches(regexPermitidos)) {
                    try {
                        // Creamos la expresión con exp4j
                        ExpressionBuilder builder = new ExpressionBuilder(textoActual);
                        net.objecthunter.exp4j.Expression expression = builder.build();

                        // Evaluamos la expresión
                        double resultado = expression.evaluate();

                        // Mostramos el resultado en el display
                        display.setText(textoActual + "=" + resultado);

                        //Con el try-catch atrapamos los errores que puedan suceder
                    } catch (ArithmeticException e) {
                        display.setText("Error!!División por cero");
                    } catch (IllegalArgumentException e) {
                        display.setText("Error!!Expresión inválida");
                    } catch (Exception e) {
                        display.setText("Error!!Cálculo fallido");
                    }
                } else {
                    // Si hay caracteres no permitidos
                    display.setText("Error: caracteres no válidos");
                }
            }
        }
    }

    /**
     * Programa principal, lanza la aplicación.
     *
     * @param args Argumentos o parámetros (no hay en este caso)
     */
    public static void main(String[] args) {
        launch(args);
    }
}

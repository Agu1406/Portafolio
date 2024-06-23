package TerceraEvaluacion.UT7RepasoExamen.Ejercicio1Cartas.EjemploColaPila;

import java.util.Iterator;
import java.util.LinkedList;

public class Pila {

    public static void main(String[] args) {
/*
* Operaciones especiales:
    - push(elemento): Añade un elemento a la pila.
    - pop(): Borra y devuelve el elemento superior de la pila.
    - peek(): Devuelve el elemento superior de la pila sin retirarlo.
    - isEmpty(): Verifica si la pila está vacía.
*
* */



        LinkedList<String> carrosCompra = new LinkedList();

        carrosCompra.push("Carro1");
        carrosCompra.push("Carro2");
        carrosCompra.push("Carro3");
        carrosCompra.push("Carro4");
        carrosCompra.push("Carro5");


        /*Mostramos todos*/
        System.out.println(carrosCompra);
        System.out.println();
        for(String cliente:carrosCompra){
            System.out.println("El nombre es: "+cliente);
        }

        System.out.println();
        Iterator it = carrosCompra.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        // carrosCompra.clear();

        /*pop  =>  get(ultimo) + remove(ultimo) */
        System.out.println("Pop");
        if (carrosCompra.isEmpty()){
            System.out.println("listaVacia");
        }
        else {
            String turno = carrosCompra.pop();
            System.out.println("Turno actual" + turno);
            System.out.println("Pila: " + carrosCompra);
        }

        /*peek => Toma el primero, pero no lo borra*/
        System.out.println("peek");
        if (carrosCompra.isEmpty()){
            System.out.println("listaVacia");
        }
        else {
            String turno = carrosCompra.peek();
            System.out.println("Turno actual" + turno);
            System.out.println("Pila: " + carrosCompra);
        }

        System.out.println("Añado otra vez el 5");
carrosCompra.push("Carro5");
        System.out.println(carrosCompra);










    }

}

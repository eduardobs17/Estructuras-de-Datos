package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.stack.LinkedListStack;
import ucr.ac.cr.ecci.ci1221.util.collections.stack.Stack;

/**
 * Clase para la prueba de LinkedListStack.
 * @author Eduardo Biazzetti.
 */
public class LinkedListStackTest {

    /**
     * Programa de prueba para LinkedListStack.
     * @param args parametros que recibe el main (si los hubieran).
     */
    public static void main(String[] args) {
        Stack<Integer> array = new LinkedListStack<>();
        System.out.println("El tamaño debe de ser cero: " + array.size() + " y estar vacio: " + array.isEmpty());
        System.out.println("array.peek() El objeto debe ser nulo: " + array.peek());
        System.out.println("array.pop() El objeto debe ser nulo: " + array.pop());
        System.out.println("Se agregan numeros del 0 al 5");
        array.push(0);
        array.push(1);
        array.push(2);
        array.push(3);
        array.push(4);
        array.push(5);
        System.out.println("El tamaño debe ser 6: " + array.size() + " y no estar vacio: " + array.isEmpty());
        System.out.println("array.pop() El objeto debe ser 5: " + array.pop() + " y el tamaño debe ser cinco: " + array.size());
        System.out.println("array.peek() El objeto debe ser 4: " + array.peek() + " y el tamaño debe ser cinco: " + array.size());
        System.out.println("Se hacen dos pop.");
        array.pop();
        array.pop();
        System.out.println("El tamaño debe ser 3: " + array.size() + "\nse limpia la pila.");
        array.clear();
        System.out.println("El tamaño debe de ser cero: " + array.size() + " y estar vacio: " + array.isEmpty());
        System.out.println("array.peek() El objeto debe ser nulo: " + array.peek());
        System.out.println("array.pop() El objeto debe ser nulo: " + array.pop());
        array.push(10);
        System.out.println("Se agrega un 10.");
        System.out.println("El tamaño debe ser 1: " + array.size() + " y no estar vacio: " + array.isEmpty());
        System.out.println("El objeto debe ser 10: " + array.peek());
    }
}
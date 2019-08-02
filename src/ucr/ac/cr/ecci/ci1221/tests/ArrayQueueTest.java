package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.queue.ArrayQueue;
import ucr.ac.cr.ecci.ci1221.util.collections.queue.Queue;

/**
 * Clase para la prueba de ArrayQueue.
 * @author Eduardo Biazzetti.
 */
public class ArrayQueueTest {

    /**
     * Programa de prueba para ArrayQueue.
     * @param args parametros que recibe el main (si los hubieran).
     */
    public static void main(String[] args) {
        Queue<Character> cola = new ArrayQueue<>();
        System.out.println("El tamaño debe de ser cero: " + cola.size() + " y estar vacio: " + cola.isEmpty());
        System.out.println("El objeto debe ser nulo: " + cola.dequeue());
        System.out.println("El objeto debe ser nulo: " + cola.peek());
        cola.enqueue('e');
        cola.enqueue('s');
        cola.enqueue(' ');
        cola.enqueue('p');
        cola.enqueue('r');
        cola.enqueue('u');
        cola.enqueue('e');
        cola.enqueue('b');
        cola.enqueue('a');
        System.out.println("El tamaño debe de ser nueve: " + cola.size() + " y no estar vacio: " + cola.isEmpty());
        System.out.println("El objeto debe ser e: " + cola.dequeue());
        System.out.println("El objeto debe ser s: " + cola.dequeue());
        System.out.println("El objeto debe ser ' ': '" + cola.dequeue() + "'");
        System.out.println("El tamaño debe de ser 6: " + cola.size());
        System.out.println("El objeto debe ser p: " + cola.peek() + " y el tamaño debe ser 6: " + cola.size());
        cola.clear();
        System.out.println("El tamaño debe de ser cero: " + cola.size() + " y estar vacio: " + cola.isEmpty());
        System.out.println("El objeto debe ser nulo: " + cola.dequeue());
        System.out.println("El objeto debe ser nulo: " + cola.peek());
        cola.enqueue('p');
        cola.enqueue('r');
        cola.enqueue('u');
        System.out.println("El tamaño debe de ser tres: " + cola.size() + " y no estar vacio: " + cola.isEmpty());
        System.out.println("El objeto debe ser p: " + cola.dequeue());
        System.out.println("El objeto debe ser r: " + cola.peek());
        System.out.println("El tamaño debe ser 2: " + cola.size());
    }
}
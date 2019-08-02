package ucr.ac.cr.ecci.ci1221.util.collections.queue;

/**
 * Linked based implementation of the {@link Queue} model.
 *
 * @param <E> the type of elements in the queue.
 * @author Eduardo Biazzetti.
 */
public class LinkedListQueue<E> implements Queue<E> {

    /** Apuntador al inicio de la cola */
    private Nodo primero;
    /** Apuntador al final de la cola */
    private Nodo ultimo;

    /**
     * Clase auxiliar o 'friend class' de la clase LinkedListQueue.
     * Representa cada uno de los nodos que componen la cola.
     */
    private class Nodo {

        /** Puntero hacia el siguiente Nodo. Se usa para enlazarlos. */
        private Nodo siguiente;
        /** El elemento que el Nodo va a guardar. */
        private E elemento;

        /**
         * Constructor de la clase Nodo.
         * @param objeto el objeto que va a poseer el nodo.
         */
        private Nodo(E objeto) {
            elemento = objeto;
            siguiente = null;
        }
    }

    /**
     * Agrega el elemento al final de la cola.
     * @param element El elemento que se va a agregar.
     */
    @Override
    public void enqueue(E element) {
        Nodo temp = new Nodo(element);
        if (primero == null) {
            primero = temp;
            ultimo = temp;
        } else {
            ultimo.siguiente = temp;
            ultimo = temp;
        }
    }

    /**
     * Saca el elemento que está en la cabeza de la cola.
     * Si la cola está vacía, retorna null;
     * @return cabeza de la cola; null si está vacía.
     */
    @Override
    public E dequeue() {
        if (primero != null) {
            Nodo retorno = primero;
            if (primero == ultimo) {
                primero = null;
                ultimo = null;
            } else {
                primero = primero.siguiente;
            }
            return retorno.elemento;
        } else {
            return null;
        }
    }

    /**
     * Muestra el elemento que está en la cabeza de la cola.
     * Si la cola está vacía, retorna nul.
     * @return cabeza de la cola; null si está vacía.
     */
    @Override
    public E peek() {
        if (primero != null) {
            return primero.elemento;
        } else {
            return null;
        }
    }

    /**
     * Retorna el numero de elementos de la cola.
     * @return tamaño de la cola.
     */
    @Override
    public int size() {
        Nodo it1 = primero;
        int contador = 0;
        while (it1 != null) {
            contador++;
            it1 = it1.siguiente;
        }
        if (contador < Integer.MAX_VALUE) {
            return contador;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Revisa si la cola está vacía, devolviendo true en caso de que suceda.
     * @return true si la cola está vacía; false, en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return primero ==  null;
    }

    /**
     * Vacía la cola, dejandola sin elementos.
     */
    @Override
    public void clear() {
        primero = null;
        ultimo = null;
    }
}
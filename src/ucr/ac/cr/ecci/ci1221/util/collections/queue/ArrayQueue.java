package ucr.ac.cr.ecci.ci1221.util.collections.queue;

/**
 * Array based implementation of the {@link Queue} model.
 *
 * @param <E> the type of elements in the queue.
 * @author Eduardo Biazzetti
 */
public class ArrayQueue<E> implements Queue<E> {

    /** Arreglo que simula la cola. */
    private E[] array = (E[]) new Object[25];
    /** Tamaño de la cola. */
    private int tamano;

    /**
     * Agrega el elemento al final de la cola.
     * @param element El elemento que se va a agregar.
     */
    @Override
    public void enqueue(E element) {
        if (tamano == array.length) {
            E[] temp = (E[]) new Object[array.length * 5];
            for (int x = 0; x < array.length; x++) {
                temp[x] = array[x];
            }
            array = temp;
        }
        int contador = tamano;
        while (contador > 0) {
            array[contador] = array[contador - 1];
            contador--;
        }
        array[0] = element;
        tamano++;
    }

    /**
     * Saca el elemento que está en la cabeza de la cola.
     * Si la cola está vacía, retorna null;
     * @return cabeza de la cola; null si está vacía.
     */
    @Override
    public E dequeue() {
        if (tamano > 0) {
            tamano--;
            return array[tamano];
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
        if (tamano > 0) {
            return array[tamano-1];
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
        if (tamano < Integer.MAX_VALUE) {
            return tamano;
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
        return tamano == 0;
    }

    /**
     * Vacía la cola, dejandola sin elementos.
     */
    @Override
    public void clear() {
        tamano = 0;
    }
}
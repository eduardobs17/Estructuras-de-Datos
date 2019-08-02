package ucr.ac.cr.ecci.ci1221.util.collections.queue;

/**
 * Circular array based implementation of the {@link Queue} model.
 *
 * @param <E> the type of elements in the queue.
 * @author Eduardo Biazzetti
 */
public class CircularArrayQueue<E> implements Queue<E> {

    /**  Arreglo que simula la cola. */
    private E[] array = (E[]) new Object[25];
    /** Tamaño de la cola. */
    private int tamano;
    /** Indice del final de la cola */
    private int write;
    /** Indice del inicio de la cola */
    private int read;

    /**
     * Agrega el elemento al final de la cola.
     * @param element El elemento que se va a agregar.
     */
    @Override
    public void enqueue(E element) {
        if (tamano == array.length) {
            E[] nuevo = (E[]) new Object[array.length * 5];
            int variable = 0;
            while (variable < array.length) {
                nuevo[variable] = array[read];
                variable++;
                read = (read + 1) % array.length;
            }
            read = 0;
            write = variable;
            array = nuevo;
        }
        array[write] = element;
        tamano++;
        write = (write + 1) % array.length;
    }

    /**
     * Saca el elemento que está en la cabeza de la cola.
     * Si la cola está vacía, retorna null;
     * @return cabeza de la cola; null si está vacía.
     */
    @Override
    public E dequeue() {
        if (tamano == 0) {
            return null;
        } else {
            tamano--;
            E temp = array[read];
            read = (read + 1) % array.length;
            return temp;
        }
    }

    /**
     * Muestra el elemento que está en la cabeza de la cola.
     * Si la cola está vacía, retorna nul.
     * @return cabeza de la cola; null si está vacía.
     */
    @Override
    public E peek() {
        if (tamano == 0) {
            return null;
        } else {
            return array[read];
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
        read = write;
    }
}
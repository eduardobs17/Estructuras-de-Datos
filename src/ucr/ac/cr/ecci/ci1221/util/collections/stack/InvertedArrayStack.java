package ucr.ac.cr.ecci.ci1221.util.collections.stack;

/**
 * Inverted Array based implementation of the {@link Stack} model.
 *
 * @param <E> the type of elements in the stack.
 * @author Rodrigo A. Bartels
 */
public class InvertedArrayStack<E> implements Stack<E> {

    /** Tamaño de la pila. */
    private int tamano;
    /** Array que va a simular la pila. */
    private E[] array = (E[]) new Object[25];

    /**
     * Metodo que mete el elemento en el tope de la pila.
     * @param element el elemento que será metido en la pila.
     */
    @Override
    public void push(E element) {
        if (tamano == array.length) {
            E[] temp = (E[]) new Object[array.length * 5];
            for (int x = 0; x < array.length; x++) {
                temp[x] = array[x];
            }
            array = temp;
        }

        array[tamano] = element;
        tamano++;
    }

    /**
     * Saca un elemento de la pila y lo retorna.
     * @return El elemento que se encuentra en el tope de la pila; null si la pila esta vacia.
     */
    @Override
    public E pop() {
        if (tamano > 0) {
            tamano--;
            return array[tamano];
        } else {
            return null;
        }
    }

    /**
     * Muestra el elemento que esta en el tope de la pila.
     * @return El elemento que se encuentra en el tope de la pila; null si la pila esta vacia.
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
     * Retorna el numero de elementos de la pila.
     * @return tamaño de la pila.
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
     * Revisa si la pila esta vacia, devolviendo true en caso de que suceda.
     * @return true si la pila esta vacia; false, en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return tamano == 0;
    }

    /**
     * Vacia la pila, dejandola sin elementos.
     */
    @Override
    public void clear() {
        try {
            tamano = 0;
        } catch (UnsupportedOperationException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
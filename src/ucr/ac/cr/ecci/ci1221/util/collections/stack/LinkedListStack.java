package ucr.ac.cr.ecci.ci1221.util.collections.stack;

/**
 * Linked based implementation of the {@link Stack} model.
 *
 * @param <E> the type of elements in the stack.
 * @author Rodrigo A. Bartels
 */
public class LinkedListStack<E> implements Stack<E> {

    /** Apuntador al inicio de la pila. */
    private Nodo tope;

    /**
     * Clase auxiliar o 'friend class' de la clase LinkedListStack.
     * Representa cada uno de los nodos que componen la pila.
     */
    private class Nodo {

        /** Enlaza el nodo actual con otro. */
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
     * Metodo que mete el elemento en el tope de la pila.
     * @param element el elemento que sera metido en la pila.
     */
    @Override
    public void push(E element) {
        Nodo nuevo = new Nodo(element);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    /**
     * Saca un elemento  de la pila y lo retorna.
     * @return El elemento que se encuentra en el tope de la pila; null si la pila esta vacia.
     */
    @Override
    public E pop() {
        if (tope != null) {
            Nodo devolver = tope;
            tope = tope.siguiente;
            return devolver.elemento;
        } else {
            return null;
        }
    }

    /**
     * Mustra el elemento que esta en el tope de la pila.
     * @return El elemento que se encuentra en el tope de la pila; null si la pila esta vacia.
     */
    @Override
    public E peek() {
        if (tope != null) {
            return tope.elemento;
        } else {
            return null;
        }
    }

    /**
     * Retorna el numero de elementos de la pila.
     * @return tamaño de la pila; 0 si esta vacia.
     */
    @Override
    public int size() {
        Nodo it1 = tope;
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
     * Revisa si la pila esta vacia, devolviendo true en caso de que suceda.
     * @return true si la pila esta vacia; false, en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return tope == null;
    }

    /**
     * Vacia la pila, dejandola sin elementos.
     */
    @Override
    public void clear() {
        try {
            tope = null;
        } catch (UnsupportedOperationException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
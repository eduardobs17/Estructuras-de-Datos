package ucr.ac.cr.ecci.ci1221.util.collections.set;

import java.util.Iterator;

/**
 * Clase que representa el set implementado mediante LL.
 * @author Eduardo Biazzetti.
 */
public class LinkedListSet<T> implements Set<T> {

    /** Apuntador al inicio de la lista. */
    private Nodo head;

    /**
     * Clase auxiliar o 'friend class' de la clase LinkedList.
     * Representa cada uno de los nodos que componen la lista.
     */
    private class Nodo {

        /** El elemento que el Nodo va a guardar. */
        private T elemento;
        /** Puntero hacia el siguiente Nodo. Se usa para enlazarlos. */
        private Nodo siguiente;

        /**
         * Constructor de la clase Nodo.
         * @param element el elemento que va a poseer esta instancia de Nodo.
         */
        private Nodo(T element) {
            siguiente = null;
            elemento = element;
        }
    }

    /**
     * Iterador para LinkedListSet.
     */
    private class LinkedListSetIterator implements Iterator<T> {

        /** Nodo donde se encuentra el iterador. */
        Nodo it1 = head;

        /**
         * Revisa si aún existen más elementos en la lista.
         * @return false si el iterador está en el ultimo elemento; true, en caso contrario.
         */
        public boolean hasNext() {
            return (it1 != null);
        }

        /**
         * Retorna el siguiente elemento del iterador.
         * @return Siguiente elemento; si no existiera, null.
         */
        public T next() {
            Nodo ret = it1;
            it1 = it1.siguiente;
            return ret.elemento;
        }
    }

    /**
     * Une los elementos de los dos sets en uno solo, el actual y el que se recibe como parametro.
     * @param set el set con el que se va a unir.
     * @return Un set con la union entre ambos.
     */
    @Override
    public Set<T> union(Set<T> set) {
        Iterator<T> it1 = set.iterator();
        Nodo it2 = head;
        Set<T> nuevoSet = new LinkedListSet<>();

        while (it1.hasNext()) {
            nuevoSet.put(it1.next());
        }

        while (it2 != null) {
            T aux = it2.elemento;
            if (!set.isMember(aux)) {
                nuevoSet.put(aux);
            }
        }
        return nuevoSet;
    }

    /**
     * Interseca los elementos de los dos sets en uno solo, el actual y el que se recibe como parametro.
     * @param set el set con el que se va a intersecar.
     * @return Un set con la interseccion entre ambos.
     */
    @Override
    public Set<T> intersection(Set<T> set) {
        Set<T> nuevoSet = new LinkedListSet<>();
        Nodo it1 = head;
        while (it1 != null) {
            if (set.isMember(it1.elemento)) {
                nuevoSet.put(it1.elemento);
            }
        }
        return nuevoSet;
    }

    /**
     * Aplica diferencia entre los dos sets. Es decir, guarda en
     * un nuevo set, los elementos del primer set que no estan en el segundo.
     * @param set el set con el que se va a calcular la diferencia.
     * @return Un set con la diferencia entre el set actual y el que se recibe de parametro.
     */
    @Override
    public Set<T> difference(Set<T> set) {
        Nodo it1 = head;
        Set<T> nuevoSet = new LinkedListSet<>();
        while (it1 != null) {
            T aux = it1.elemento;
            if (!set.isMember(aux)) {
                nuevoSet.put(aux);
            }
        }
        return nuevoSet;
    }

    /**
     * Revisa si key esta contenido en el set.
     * @param key el elemento que se va a buscar.
     * @return true si el elemento esta; false en el otro caso.
     */
    @Override
    public boolean isMember(T key) {
        Nodo it1 = head;
        while (it1 != null) {
            if (it1.elemento.equals(key)) {
                return true;
            } else {
                it1 = it1.siguiente;
            }
        }
        return false;
    }

    /**
     * Revisa si la lista esta vacia, devolviendo true en caso de que asi sea.
     * @return true si la lista esta vacia; false, en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Vacia la lista, dejandola sin elementos.
     */
    @Override
    public void clear() {
        head = null;
    }

    /**
     * Agrega un elemento a la lista.
     * @param key el elemento que se va a agregar.
     */
    @Override
    public void put(T key) {
        if (key == null) {
            throw new NullPointerException();
        }

        Nodo nuevo = new Nodo(key);
        if (head == null) {
            head = nuevo;
        } else {
            Nodo it1 = head;
            while (it1.siguiente != null) {
                it1 = it1.siguiente;
            }
            it1.siguiente = nuevo;
        }
    }

    /**
     * Elimina un elemento de la lista.
     * @param key El elemento que se va a borrar.
     */
    @Override
    public void remove(T key) {
        if ((head.elemento).equals(key)) {
            head = head.siguiente;
        } else {
            Nodo it1 = head;
            boolean seguir = true;
            while (seguir && it1.siguiente != null) {
                if ((it1.siguiente).elemento.equals(key)) {
                    it1.siguiente = (it1.siguiente).siguiente;
                    seguir = false;
                } else {
                    it1 = it1.siguiente;
                }
            }
        }
    }

    /**
     * Retorna el numero de elementos de la lista.
     * @return Cantidad de elementos en la lista.
     */
    @Override
    public int size() {
        int elementos = 0;
        Nodo it1 = head;
        while (it1 != null) {
            elementos++;
            it1 = it1.siguiente;
        }
        return elementos;
    }

    /**
     * Devuelve un iterador para recorrer los elementos que estan en la lista.
     * @return Un iterador de LLS.
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListSetIterator();
    }
}
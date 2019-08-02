package ucr.ac.cr.ecci.ci1221.util.collections.set;

import java.util.Iterator;

/**
 * Clase que representa BitVector.
 * @author Eduardo Biazzetti.
 */
public class BitVector<T extends Enumerable> implements EnumerableSet<T> {

    /** Arreglo de elementos, es del mismo tamaño que el de booleans y los elementos se encuentran
     * en la misma posicion en ambos arrays. Este se usa exclusivamente para el iterador. */
    private T[] arrayT = (T[]) new Object[100];
    /** Arreglo de booleanos, guarda los elementos mediante un indice unico. */
    private boolean[] array = new boolean[100];

    /**
     * Iterador para BitVector.
     */
    private class BitVectorIterator implements Iterator<T> {

        /** Posicion actual del iterador. */
        private int currentPos = 0;

        /**
         * Revisa si aún existen más elementos en la lista.
         * @return false si el iterador está en el ultimo elemento; true, en caso contrario.
         */
        public boolean hasNext() {
            return (size() > 0) && (currentPos < size());
        }

        /**
         * Retorna el siguiente elemento del iterador.
         * @return Siguiente elemento; si no existiera, null.
         */
        public T next() {
            while (arrayT[currentPos] == null) {
                currentPos++;
            }
            return arrayT[currentPos++];
        }
    }

    /**
     * Constructor de BitVector.
     */
    public BitVector() {}

    /**
     * Constructor de BitVector.
     * @param tam el tamaño que se quiere para los arrays.
     */
    public BitVector(int tam) {
        arrayT = (T[]) new Object[tam];
        array = new boolean[tam];
    }

    /**
     * Une los elementos de los dos sets en uno solo, el actual y el que se recibe como parametro.
     * @param set el set con el que se va a unir.
     * @return Un set con la union entre ambos.
     */
    @Override
    public EnumerableSet<T> union(EnumerableSet<T> set) {
        Iterator<T> it1 = set.iterator();
        Iterator<T> it2 = iterator();
        EnumerableSet<T> nuevoSet = new BitVector<>();
        while (it1.hasNext()) {
            nuevoSet.put(it1.next());
        }
        while (it2.hasNext()) {
            T aux = it2.next();
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
    public EnumerableSet<T> intersection(EnumerableSet<T> set) {
        EnumerableSet<T> nuevoSet = new BitVector<>();
        Iterator<T> it1 = iterator();
        while (it1.hasNext()) {
            T aux = it1.next();
            if (set.isMember(aux)) {
                nuevoSet.put(aux);
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
    public EnumerableSet<T> difference(EnumerableSet<T> set) {
        Iterator<T> it1 = iterator();
        EnumerableSet<T> nuevoSet = new BitVector<>();
        while (it1.hasNext()) {
            T aux = it1.next();
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
        int valor = key.getIndex();
        return array[valor];
    }

    /**
     * Revisa si el array esta vacio, devolviendo true en caso de que asi sea.
     * @return true si el array esta vacio; false, en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        for (int x = 0; x < array.length; x++) {
            if (array[x]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vacia los arrays, dejandolos sin elementos.
     */
    @Override
    public void clear() {
        for (int x = 0; x < array.length; x++) {
            array[x] = false;
            arrayT[x] = null;
        }
    }

    /**
     * Agrega un elemento al BitVector.
     * @param key el elemento que se va a agregar.
     */
    @Override
    public void put(T key) {
        int valor = key.getIndex();
        arrayT[valor] = key;
        array[valor] = true;
    }

    /**
     * Elimina un elemento del BitVector.
     * @param key El elemento que se va a borrar.
     */
    @Override
    public void remove(T key) {
        int valor = key.getIndex();
        arrayT[valor] = null;
        array[valor] = false;
    }

    /**
     * Retorna el numero de elementos en el BitVector.
     * @return Cantidad de elementos en el BitVector.
     */
    @Override
    public int size() {
        int contador = 0;
        for (int x = 0; x < array.length; x++) {
            if (array[x]) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve un iterador para recorrer los elementos que estan en el BitVector.
     * @return Un iterador de BitVector.
     */
    @Override
    public Iterator<T> iterator() {
        return new BitVectorIterator();
    }
}
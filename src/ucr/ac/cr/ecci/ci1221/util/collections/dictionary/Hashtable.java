package ucr.ac.cr.ecci.ci1221.util.collections.dictionary;

import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.set.LinkedListSet;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;
import java.util.Iterator;

/**
 * Clase que representa el diccionario implementado mediante tabla Hash.
 *
 * @author Eduardo Biazzetti
 */
public class Hashtable<K extends Comparable<? super K>, V> implements Dictionary<K, V> {

    /** Array que representa la tabla. Tiene un tamaño inicial pequeño, el cual puede aumentar. */
    private V[] array = (V[]) new Object[31];
    /** Set que contiene las llaves K que se usan en la tabla. */
    private Set<K> setKeys = new LinkedListSet<>();

    public Hashtable() {}

    public Hashtable(int tam) {
        array = (V[]) new Object[tam];
    }

    /**
     * Retorna el numero total de mapeos llave-valor en la tabla. Si la tabla contiene mas que
     * Integer.MAX_VALUE elementos, retorna Integer.MAX_VALUE.
     *
     * @return El numero de 'mapeos' llave-valor en la tabla.
     */
    @Override
    public int size() {
        int tam = 0;
        for (int x = 0; x < array.length; x++) {
            if (array[x] != null) {
                tam++;
            }
        }
        if (tam < Integer.MAX_VALUE) {
            return tam;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Retorna true si la tabla no contiene ningun mapeo llave-valor.
     *
     * @return true si la tabla no contiene 'pares'.
     */
    @Override
    public boolean isEmpty() {
        for (int x = 0; x < array.length; x++) {
            if (array[x] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retorna true si la llave esta asociada a algun valor en la tabla.
     * @param key llave cuya presencia en la tabla se va a evaluar.
     * @return true si existe alguna relacion entre la llave y un valor cualquiera; false en caso contrario.
     */
    @Override
    public boolean containsKey(Object key) {
        try {
            if (key == null) {
                throw new NullPointerException();
            }

            int casilla = funcionHash((K) key);
            if (array[casilla] != null) {
                return true;
            }
        } catch (NullPointerException exception) {
            System.out.println("Null Pointer: " + exception.toString());
        }
        return false;
    }

    /**
     * Retorna true si el valor esta asociado a alguna llave en la tabla.
     * @param value valor cuya presencia en la tabla se va a evaluar.
     * @return true si el valor esta relacionado a alguna llave cualquiera.
     */
    @Override
    public boolean containsValue(Object value) {
        try {
            if (value == null) {
                throw new NullPointerException();
            }

            for (int x = 0; x < array.length; x++) {
                if ((value).equals(array[x])) {
                    return true;
                }
            }
        } catch (NullPointerException exception) {
            System.out.println("Null Pointer: " + exception.toString());
        }
        return false;
    }

    /**
     * Retorna el valor al cual esta asociado una llave especifica, o null si en la tabla no existe
     * asociacion alguna.
     * @param key la llave cuyo valor asociado se va a retornar.
     * @return El valor asociado a una llave especifica. Null si no existe asociacion.
     */
    @Override
    public V get(K key) {
        try {
            if (key == null) {
                throw new NullPointerException();
            }

            int casilla = funcionHash(key);
            return array[casilla];
        } catch (NullPointerException exception) {
            System.out.println("Null Pointer: " + exception.toString());
        }
        return null;
    }

    /**
     * Agrega value a la tabla, dependiendo del valor de key.
     * @param key llave a la cual value se va a asociar.
     * @param value valor que va a ser asociado a una llave especifica.
     * @return
     */
    @Override
    public V put(K key, V value) {
        try {
            if ((key == null) || (value == null)) {
                throw new NullPointerException();
            }

            // Se hace que la tabla crezca.
            if (size() > (4 * array.length) / 5) {
                Set<V> temp = new LinkedListSet<>();
                Iterator<K> it1 = setKeys.iterator();
                while (it1.hasNext()) {
                    int posValue = funcionHash(it1.next());
                    temp.put(array[posValue]);
                }

                V[] aux = array;
                array = (V[]) new Object[aux.length * 2];
                Iterator<K> it2 = setKeys.iterator();
                Iterator<V> it3 = temp.iterator();
                while (it2.hasNext()) {
                    int nuevaPos = funcionHash(it2.next());
                    array[nuevaPos] = it3.next();
                }
            }

            int casilla = funcionHash(key);
            if (array[casilla] == null) {
                array[casilla] = value;
                setKeys.put(key);
                return null;
            } else {
                V valorRetorno = array[casilla];
                array[casilla] = value;
                return valorRetorno;
            }
        } catch (NullPointerException exception) {
            System.out.println("Null Pointer: " + exception.toString());
            return null;
        }
    }

    /**
     * Funcion que crea un numero unico para cada key que recibe.
     *
     * @param key la llave a la cual se le va a asociar un numero unico.
     * @return el numero al que key esta asociado.
     */
    private int funcionHash(K key) {
        int result = key.hashCode();
        result = 29 * result + (key.toString()).length();
        result = 29 * result + key.hashCode();
        result = result % array.length;
        return result;
    }

    /**
     * Elimina de la tabla el valor asociado a key.
     *
     * @param key llave cuyo mapeo o pareja va a ser removido de la tabla.
     * @return el valor asociado a key; null, si no existe.
     */
    @Override
    public V remove(K key) {
        try {
            if (key == null) {
                throw new NullPointerException();
            }

            setKeys.remove(key);
            int casilla = funcionHash(key);
            if (array[casilla] == null) {
                return null;
            } else {
                V valorRetorno = array[casilla];
                array[casilla] = null;
                return valorRetorno;
            }
        } catch (NullPointerException exception) {
            System.out.println("Null Pointer: " + exception.toString());
            return null;
        }
    }

    /**
     * Elimina todas las parejas de este diccionario, el cual queda vacio despues del llamado.
     */
    @Override
    public void clear() {
        try {
            for (int x = 0; x < array.length; x++) {
                array[x] = null;
            }
            setKeys.clear();
        } catch (UnsupportedOperationException exception) {
            System.out.println("Unsupported Operation: " + exception.toString());
        }
    }

    /**
     * Retorna un conjunto (set) con todas las llaves contenidas en el diccionario.
     * @return conjunto (set) de las llaves que se encuentran en el diccionario.
     */
    @Override
    public Set<K> keySet() {
        return setKeys;
    }

    /**
     * Retorna una lista con todos los values contenidos en el diccionario.
     * @return una lista con los values que se encuentran en el diccionario.
     */
    @Override
    public List<V> values() {
        List<V> listaValores = new LinkedList<>();
        for (int x = 0; x < array.length; x++) {
            if (array[x] != null) {
                listaValores.add(array[x]);
            }
        }
        return listaValores;
    }
}
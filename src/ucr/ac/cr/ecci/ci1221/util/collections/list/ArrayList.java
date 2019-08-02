package ucr.ac.cr.ecci.ci1221.util.collections.list;

import java.util.Iterator;

/**
 * Array based implementation of the {@link List} model.
 * 
 * @param <E> the type of elements in the list.
 * @author Eduardo Biazzetti.
 */
public class ArrayList<E> implements List<E> {

    /** Arreglo que simula la lista. */
    private E[] array = null;
    /** Tamaño de la lista. */
    private int tamano;

    /**
     * Iterador para la lista basada en array.
     */
    private class ArrayIterator implements Iterator<E> {

        /** Posicion actual del iterador. */
        int currentPos = 0;

        /**
         * Revisa si aún existen más elementos en la lista.
         * @return false si el iterador está en el ultimo elemento; true, en caso contrario.
         */
        public boolean hasNext() {
            return (tamano > 0) && (currentPos < tamano);
        }

        /**
         * Retorna el siguiente elemento del iterador.
         * @return Siguiente elemento; si no existiera, null.
         */
        public E next() {
            if (tamano > 0) {
                return array[currentPos++];
            } else {
                return null;
            }
        }
    }

    /**
     * Constructor de ArrayList.
     */
    public ArrayList() {
        this(10);
    }

    /**
     * Constructor de ArrayList.
     * @param tam Tamano del arreglo.
     */
    public ArrayList(int tam) {
        array = (E[]) new Object[tam];
        tamano = 0;
    }

    /**
     * Devuelve el elemento que se encuentra en la posicion 'index'.
     * @param index Indice del elemento que se va a retornar.
     * @return El elemento que se encuentra en la posicion requerida.
     */
    @Override
    public E get(int index) {
        try {
            if ((index < 1) || (index > tamano)) {
                throw new IndexOutOfBoundsException();
            }
            return array[index - 1];
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Invalid index: " + exception.toString());
            return null;
        }
    }

    /**
     * Retorna la posicion del primer elemento en el arreglo.
     * @return La posicion del primer elemento en el arreglo.
     */
    public int first() {
        return 1;
    }

    /**
     * Retorna el siguiente elemento de la lista, basado en una posicion dada.
     * @param index El indice usado para calcular el siguiente elemento.
     * @return EL elemento que se encuentra en la siguiente posicion de 'index';
     * si index es el ultimo elemento de la lista, retorna null.
     */
    @Override
    public int next(int index) {
        if ((index < 1) || (index > tamano) || (index == tamano)) {
            return -1;
        } else {
            return index + 1;
        }
    }

    /**
     * Retorna el elemento anterior de la lista, basado en una posicion dada.
     * @param index El indice usado para calcular el elemento anterior.
     * @return El elemento en la posicion anterior; null si index es el primer elemento de la lista.
     */
    @Override
    public int previous(int index) {
        if ((index == 1) || (index < 1) || (index > tamano)) {
            return -1;
        } else {
            return index - 1;
        }
    }

    /**
     * Modifica el elemento de una posicion especifica en la lista, sustituyendolo por otro nuevo.
     * @param index Indice del elemento que se va a reemplazar.
     * @param element El nuevo elemento que irá en la posición.
     * @return El elemento que se encontraba anteriormente en la posición dada.
     */
    @Override
    public E set(int index, E element) {
        try {
            if ((index < 1) || (index > tamano)) {
                throw new IndexOutOfBoundsException();
            }
            if (element == null) {
                throw new NullPointerException();
            }

            E temp = array[index - 1];
            array[index - 1] = element;
            return temp;
        } catch (UnsupportedOperationException exception) {
            System.out.println("Unsupported Operation: " + exception.toString());
        } catch (ClassCastException exception) {
            System.out.println("Invalid element class: " +  exception.toString());
        } catch (NullPointerException exception) {
            System.out.println("Null element: " + exception.toString());
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid argument: " + exception.toString());
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Invalid index: " + exception.toString());
        }
        return null;
    }

    /**
     * Inserta el elemento en una posicion específica.
     * Si hubieran mas elementos a la derecha, se realiza corrimiento.
     * @param index Indice en donde se va a insertar el nuevo elemento.
     * @param element Elemento que se va a insertar a la lista.
     */
    @Override
    public void add(int index, E element) {
        try {
            if ((index < 1) || (index > tamano + 1)) {
                throw new IndexOutOfBoundsException();
            }
            if (element == null) {
                throw new NullPointerException();
            }

            if (tamano == array.length) {
                E[] nuevo = (E[]) new Object[array.length * 5];
                for (int x = 0; x < array.length; x++) {
                    nuevo[x] = array[x];
                }
                array = nuevo;
            }

            int contador = tamano;
            while (contador >= index) {
                array[contador] = array[contador - 1];
                contador--;
            }
            array[index - 1] = element;
            tamano++;
        } catch (UnsupportedOperationException exception) {
            System.out.println("Unsupported Operation: " + exception.toString());
        } catch (ClassCastException exception) {
            System.out.println("Invalid element class: " +  exception.toString());
        } catch (NullPointerException exception) {
            System.out.println("Null element: " + exception.toString());
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid argument: " + exception.toString());
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Invalid index: " + exception.toString());
        }
    }

    /**
     * Inserta un elemento al final de la lista.
     * @param element El elemento que se va a insertar a la lista.
     * @return true si la lista se modificó; false en caso contrario.
     */
    @Override
    public boolean add(E element) {
        try {
            if (element == null) {
                throw new NullPointerException();
            }

            if (tamano == array.length) {
                E[] temp = (E[]) new Object[array.length * 5];
                for (int x = 0; x < array.length; x++) {
                    temp[x] = array[x];
                }
                array = temp;
            }
            array[tamano] = element;
            tamano++;
            return true;
        } catch (UnsupportedOperationException exception) {
            System.out.println("Unsupported Operation: " + exception.toString());
        } catch (ClassCastException exception) {
            System.out.println("Invalid element class: " +  exception.toString());
        } catch (NullPointerException exception) {
            System.out.println("Null element: " + exception.toString());
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid argument: " + exception.toString());
        }
        return false;
    }

    /**
     * Remueve un elemento específico de la lista, haciendo a su vez corrimiento para la izquierda de los demás elementos.
     * @param index El indice del elemento que se va a eliminar.
     * @return El elemento que se encontraba anteriormente en la posición dada.
     */
    @Override
    public E remove(int index) {
        try {
            if ((index < 1) || (index > tamano)) {
                throw new IndexOutOfBoundsException();
            }
            E temp = array[index - 1];
            for (int x = index - 1; x < tamano - 1; x++) {
                array[x] = array[x+1];
            }
            tamano--;
            return temp;
        } catch (UnsupportedOperationException exception) {
            System.out.println("Unsupported Operation: " + exception.toString());
        } catch(IndexOutOfBoundsException exception) {
            System.out.println("Invalid index: " + exception.toString());
        }
        return null;
    }

    /**
     * Retorna el numero de elementos de la lista.
     * @return Tamaño de la lista.
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
     * Revisa si la lista está vacía, devolviendo true en caso de que suceda.
     * @return true si la lista está vacía; false, en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return tamano == 0;
    }

    /**
     * Vacía la lista, dejandola sin elementos.
     */
    @Override
    public void clear() {
        tamano = 0;
    }

    /**
     * Devuelve un iterador para recorrer los elementos que estan en la lista.
     * @return Un iterador de lista basada en array.
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }
}
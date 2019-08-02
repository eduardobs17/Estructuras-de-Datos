package ucr.ac.cr.ecci.ci1221.util.collections.list;

import java.util.Iterator;

/**
 * Linked pointer based implementation of the {@link List} model.
 *
 * @param <E> the type of elements in the list.
 * @author Eduardo Biazzetti
 */
public class LinkedList<E> implements List<E> {

    /** Apuntador al inicio de la lista */
    private Nodo head;
    /** Tamaño de la lista. */
    private int tamano;

    /**
     * Clase auxiliar o 'friend class' de la clase LinkedList.
     * Representa cada uno de los nodos que componen la lista.
     */
    private class Nodo {

        /** El elemento que el Nodo va a guardar. */
        private E elemento;
        /** Puntero hacia el siguiente Nodo. Se usa para enlazarlos. */
        private Nodo siguiente;

        /**
         * Constructor de la clase Nodo.
         * @param element el elemento que va a poseer esta instancia de Nodo.
         */
        private Nodo(E element) {
            siguiente = null;
            elemento = element;
        }
    }

    private class LinkedListIterator implements Iterator<E> {

        /** Posicion donde se encuentra el iterador. */
        int currentPosition = 0;
        /** Nodo donde se encuentra el iterador. */
        Nodo it1 = head;

        /**
         * Revisa si aun existen mas elementos en la lista.
         * @return false si el iterador está en el ultimo elemento; true, en caso contrario.
         */
        public boolean hasNext() {
            return (tamano > 0) && (currentPosition < tamano);
        }

        /**
         * Retorna el siguiente elemento del iterador.
         * @return Siguiente elemento; si no existiera, null.
         */
        public E next() {
            if (tamano > 0) {
                currentPosition++;
                Nodo ret = it1;
                it1 = it1.siguiente;
                return ret.elemento;
            } else {
                return null;
            }
        }
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

            Nodo it1 = head;
            int contador = 1;
            while (contador < index) {
                it1 = it1.siguiente;
                contador++;
            }
            return it1.elemento;
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Invalid index: " + exception.toString());
            return null;
        }
    }

    /**
     * Retorna la posicion del primer elemento en la lista.
     * @return La posicion del primer elemento en la lista.
     */
    public int first() {
        return 1;
    }

    /**
     * Retorna el siguiente elemento de la lista, basado en una posicion dada.
     * @param index El indice usado para calcular el siguiente elemento.
     * @return El elemento que se encuentra en la siguiente posicion de 'index';
     * si index es el ultimo elemento de la lista, retorna null.
     */
    @Override
    public int next(int index) {
        if ((index < 1) || (index > tamano) || (index == tamano)) {
            return -1;
        } else {
            Nodo it1 = head;
            int contador = 1;
            while (contador < index) {
                it1 = it1.siguiente;
                contador++;
            }
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
     * @param element El nuevo elemento que ira en la posición.
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

            Nodo it1 = head;
            int contador = 1;
            while (contador < index) {
                it1 = it1.siguiente;
                contador++;
            }
            Nodo temp = it1;
            it1.elemento = element;
            return temp.elemento;
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
     * Inserta el elemento en una posicion especifica.
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

            Nodo nuevo = new Nodo(element);
            if (tamano == 0) {
                head = nuevo;
            } else {
                if (index == 1) {
                    nuevo.siguiente = head;
                    head = nuevo;
                } else {
                    Nodo it1 = head;
                    int contador = 2;
                    while (contador < index) {
                        it1 = it1.siguiente;
                        contador++;
                    }
                    nuevo.siguiente = it1.siguiente;
                    it1.siguiente = nuevo;
                }
            }
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
     * @return true si la lista se modifico; false en caso contrario.
     */
    @Override
    public boolean add(E element) {
        try {
            if (element == null) {
                throw new NullPointerException();
            }

            Nodo nuevo = new Nodo(element);
            if (tamano == 0) {
                head = nuevo;
            } else {
                Nodo it1 = head;
                while (it1.siguiente != null) {
                    it1 = it1.siguiente;
                }
                it1.siguiente = nuevo;
            }
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
     * Remueve un elemento especifico de la lista.
     * @param index El indice del elemento que se va a eliminar.
     * @return El elemento que se encontraba anteriormente en la posición dada.
     */
    @Override
    public E remove(int index) {
        try {
            if ((index < 1) || (index > tamano)) {
                throw new IndexOutOfBoundsException();
            }
            Nodo retorno;
            if (index == 1) {
                retorno = head;
                head = head.siguiente;
                tamano--;
                return retorno.elemento;
            } else {
                int contador = 2;
                Nodo aux = head;
                while (contador < index) {
                    aux = aux.siguiente;
                    contador++;
                }
                retorno = aux.siguiente;
                aux.siguiente = retorno.siguiente;
                tamano--;
                return retorno.elemento;
            }
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
     * Revisa si la lista esta vacia, devolviendo true en caso de que suceda.
     * @return true si la lista esta vacia; false, en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return tamano == 0;
    }

    /**
     * Devuelve un iterador para recorrer los elementos que estan en la lista.
     * @return Un iterador de LSE.
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Vacia la lista, dejandola sin elementos.
     */
    @Override
    public void clear() {
        tamano = 0;
        head = null;
    }
}
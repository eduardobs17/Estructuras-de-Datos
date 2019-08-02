package ucr.ac.cr.ecci.ci1221.util.collections.set;

import java.util.Iterator;
import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.queue.LinkedListQueue;

/**
 * Clase que representa el BST.
 * @author Eduardo Biazzetti.
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements Set<T> {

    /** Representa la raiz del arbol. */
    private Nodo raiz;

    /**
     * Clase auxiliar que representa cada uno de los nodos del arbol.
     */
    private class Nodo {

        /** Valor que guarda el nodo. */
        private T etiqueta;
        /** Puntero al hijo Izquierdo. */
        private BinarySearchTree hijoIzq;
        /** Puntero al hijo Derecho. */
        private BinarySearchTree hijoDer;

        /**
         * Constructor de la clase.
         * @param nuevo El valor que la etiqueta va a almacenar.
         */
        private Nodo(T nuevo) {
            etiqueta = nuevo;
            hijoIzq = null;
            hijoDer = null;
        }
    }

    /**
     * Clase que implementa el iterador de BST.
     */
    private class IteradorBST implements Iterator<T> {

        /** Lista donde se guardan las etiquetas de los nodos. */
        private List<T> lista = new LinkedList<>();
        /** Posicion actual del iterador. */
        private int posActual = 01;

        /** Revisa si aun quedan elementos de la estructura sin revisar.
         * @return true si aun hay elementos disponibles.
         */
        public boolean hasNext() { return (lista.size() > 0) && (posActual <= lista.size()); }

        /**
         * Retorna el siguiente elemento en el iterador.
         * @return siguiente elemento en el iterador.
         */
        public T next() {
            return lista.get(posActual++);
        }

        /** Constructor del iterador. */
        private IteradorBST() {
            preOrden(raiz);
        }

        /**
         * Realiza recorrido de pre-orden para agregar las etiquetas de los nodos a la lsta.
         * @param pNodo el nodo actual por el que va el recorrido.
         */
        private void preOrden(Nodo pNodo) {
            if (pNodo != null) {
                lista.add(pNodo.etiqueta);
                if (pNodo.hijoIzq != null) {
                    preOrden(pNodo.hijoIzq.raiz);
                }
                if (pNodo.hijoDer != null) {
                    preOrden(pNodo.hijoDer.raiz);
                }
            }
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
        Iterator<T> it2 = iterator();
        Set<T> nuevoSet = new BinarySearchTree<>();
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
    public Set<T> intersection(Set<T> set) {
        Set<T> nuevoSet = new BinarySearchTree<>();

        LinkedListQueue<Nodo> cola = new LinkedListQueue<>();
        cola.enqueue(raiz);
        while (!cola.isEmpty()) {
            Nodo aux = cola.dequeue();
            if (aux.hijoIzq != null) {
                cola.enqueue(aux.hijoIzq.raiz);
            }
            if (aux.hijoDer != null) {
                cola.enqueue(aux.hijoDer.raiz);
            }
            if (set.isMember(aux.etiqueta)) {
                nuevoSet.put(aux.etiqueta);
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
        Iterator<T> it1 = iterator();
        Set<T> nuevoSet = new BinarySearchTree<>();
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
        if ((raiz.etiqueta).equals(key)) {
            return true;
        } else {
            if ((raiz.etiqueta).compareTo(key) > 0) {
                if (raiz.hijoIzq == null) {
                    return false;
                } else {
                    return (raiz.hijoIzq).isMember(key);
                }
            } else {
                if (raiz.hijoDer == null) {
                    return false;
                } else {
                    return (raiz.hijoDer).isMember(key);
                }
            }
        }
    }

    /**
     * Revisa si el arbol esta vacio, devolviendo true en caso de que asi sea.
     * @return true si el arbol esta vacio; false, en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return raiz == null;
    }

    /**
     * Vacia el arbol, dejandolo sin elementos.
     */
    @Override
    public void clear() {
        raiz = null;
    }

    /**
     * Agrega un elemento en el BST.
     * @param key el elemento que se va a agregar.
     */
    @Override
    public void put(T key) {
        if (raiz == null) {
            raiz = new Nodo(key);
        } else {
            if ((raiz.etiqueta).compareTo(key) > 0) {
                if (raiz.hijoIzq == null) {
                    raiz.hijoIzq = new BinarySearchTree();
                }
                (raiz.hijoIzq).put(key);
            } else {
                if (raiz.hijoDer == null) {
                    raiz.hijoDer = new BinarySearchTree();
                }
                (raiz.hijoDer).put(key);
            }
        }
    }

    /**
     * Elimina un elemento del BST.
     * @param key El elemento que se va a borrar.
     */
    @Override
    public void remove(T key) {
        if (isMember(key)) {
            Nodo aBorrar = getSubTree(key);
            if (aBorrar != null) {

                // Se borra una hoja.
                if (aBorrar.hijoIzq == null && aBorrar.hijoDer == null) {
                    Nodo padre = getFather(aBorrar);
                    if (padre.hijoIzq != null) {
                        if (padre.hijoIzq.raiz == aBorrar) {
                            padre.hijoIzq = null;
                        } else {
                            padre.hijoDer = null;
                        }
                    }
                } else {
                    // Se borra un nodo con un solo hijo (izq en este caso).
                    // Se busca el nodo mas grande por la izq y se intercambian.
                    if (aBorrar.hijoIzq != null && aBorrar.hijoDer == null) {
                        boolean seguir = true;
                        boolean esHoja = false;
                        Nodo it1 = aBorrar.hijoIzq.raiz;
                        while (!esHoja) {
                            while (seguir) {
                                if (it1.hijoDer == null) {
                                    seguir = false;
                                } else {
                                    it1 = it1.hijoDer.raiz;
                                }
                            }
                            aBorrar.etiqueta = it1.etiqueta;
                            if (it1.hijoIzq == null && it1.hijoDer == null) {
                                esHoja = true;
                            } else {
                                aBorrar = it1;
                            }
                        }
                        Nodo padre = getFather(it1);
                        if (padre.hijoIzq.raiz == it1) {
                            padre.hijoIzq = null;
                        } else {
                            padre.hijoDer = null;
                        }
                    }
                    // Caso contario.
                    // Se borra un nodo con un solo hijo (der en este caso).
                    // O se borra un nodo con ambos hijos.
                    // Se busca el nodo mas pequeño por la der y se intercambian.
                    else {
                        boolean seguir = true;
                        boolean esHoja = false;
                        Nodo it1 = aBorrar.hijoDer.raiz;
                        while (!esHoja) {
                            while (seguir) {
                                if (it1.hijoIzq == null) {
                                    seguir = false;
                                } else {
                                    it1 = it1.hijoIzq.raiz;
                                }
                            }
                            aBorrar.etiqueta = it1.etiqueta;
                            if (it1.hijoIzq == null && it1.hijoDer == null) {
                                esHoja = true;
                            } else {
                                aBorrar = it1;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Devuelve al papa del nodo que se manda como parametro.
     * @param pRaiz el nodo al que se le va a buscar el papa.
     * @return el papa del nodo pRaiz.
     */
    private Nodo getFather(Nodo pRaiz) {
        if (pRaiz != raiz) {
            Nodo it1 = raiz;
            while (it1 != null) {
                if ((it1.hijoIzq != null && it1.hijoIzq.raiz == pRaiz) || (it1.hijoDer != null && it1.hijoDer.raiz == pRaiz)) {
                    return it1;
                } else {
                    if ((it1.etiqueta).compareTo(pRaiz.etiqueta) > 0) {
                        it1 = it1.hijoIzq.raiz;
                    } else {
                        it1 = it1.hijoDer.raiz;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Devuelve un sub-arbol con raiz cuya etiqueta es key (si existiese).
     * @param key la etiqueta de la raiz del sub-arbol que se quiere obtener.
     * @return El sub-arbol (nodo) con raiz key; null si no existiera.
     */
    private Nodo getSubTree(T key) {
        if (raiz != null) {
            if ((raiz.etiqueta).equals(key)) {
                return raiz;
            } else {
                if ((raiz.etiqueta).compareTo(key) > 0) {
                    if (raiz.hijoIzq != null) {
                        return (raiz.hijoIzq).getSubTree(key);
                    }
                } else {
                    if (raiz.hijoDer != null) {
                        return (raiz.hijoDer).getSubTree(key);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Retorna el numero de nodos en el arbol.
     * @return Cantidad de elementos (o nodos) en el arbol.
     */
    @Override
    public int size() {
        int contador = 0;
        if (raiz != null) {
            LinkedListQueue<Nodo> cola = new LinkedListQueue<>();
            cola.enqueue(raiz);
            while (!cola.isEmpty()) {
                Nodo aux = cola.dequeue();
                if (aux.hijoIzq != null) {
                    cola.enqueue(aux.hijoIzq.raiz);
                }
                if (aux.hijoDer != null) {
                    cola.enqueue(aux.hijoDer.raiz);
                }
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve un iterador para recorrer los elementos del arbol.
     * @return Un iterador para BST.
     */
    @Override
    public Iterator<T> iterator() {
        return new IteradorBST();
    }
}
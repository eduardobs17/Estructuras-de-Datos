package ucr.ac.cr.ecci.ci1221.util.collections.dictionary;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.set.LinkedListSet;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;
import ucr.ac.cr.ecci.ci1221.util.collections.queue.LinkedListQueue;

/**
 * Clase que representa el arbol rojinegro.
 * @author Eduardo Biazzetti.
 */
public class RedBlackTree<K extends Comparable<? super K>, V> implements Dictionary<K, V> {

    /** Representa la raiz del arbol. */
    private Nodo raiz;

    /**
     * Clase que representa los nodos del Red-Black Tree.
     * Contiene dos punteros hacia ambos hijos, un indicador del color,
     * un objeto de tipo K, que va a ser la llave y un objeto de tipo V,
     * que es el valor que guarda el nodo.
     */
    private class Nodo {

	    /** Puntero al hijo izquierdo del nodo. */
        private RedBlackTree hijoIzq;
		/** Puntero al hijo derecho del nodo. */
        private RedBlackTree hijoDer;
        /** Puntero al nodo padre. */
        private Nodo padre;
        /** Representa el color del nodo. true = rojo; false = negro. */
        private boolean color;
        /** Llave o key que va a almacenar el nodo.*/
        private K llave;
        /** Valor o value que va a guardar el nodo. */
        private V valor;
        /** Booleano que indica si el nodo actual es doble negro. Solamente se usa en el borrado. */
        private  boolean esDobleNegro;

        /**
         * Constructor de la clase Nodo.
         * @param pKey
         * @param pValue
         */
        private Nodo(K pKey, V pValue, Nodo pPadre) {
            llave = pKey;
            valor = pValue;
            color = true;
            padre = pPadre;
            hijoDer = null;
            hijoIzq = null;
            esDobleNegro = false;
        }
    }

    /**
     * Retorna el numero total de nodos en el arbol.
     * Hace recorrido por niveles para contar los nodos.
     * Si el arbol contiene mas que Integer.MAX_VALUE nodos, retorna Integer.MAX_VALUE.
     *
     * @return El numero de nodos del arbol.
     */
    @Override
    public int size() {
        int contador = 0;
        if (raiz.hijoIzq != null) {
            contador += raiz.hijoIzq.size();
        }
        contador++; // nodo raiz
        if (raiz.hijoDer != null) {
            contador += raiz.hijoDer.size();
        }

        if (contador < Integer.MAX_VALUE) {
            return contador;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Retorna true si el arbol no contiene ningun nodo.
     *
     * @return true si el arbol no contiene nodos.
     */
    @Override
    public boolean isEmpty() {
        return raiz == null;
    }

    /**
     * Retorna true si la llave esta asociada a algun nodo en el arbol.
     * @param key llave cuya presencia en el arbol se va a evaluar.
     * @return true si existe algun nodo que posea la llave; false en caso contrario.
     */
    @Override
    public boolean containsKey(Object key) {
        try {
            if (key == null) {
                throw new NullPointerException();
            }

            if ((raiz.llave).equals(key)) {
                return true;
            } else {
                if ((raiz.llave).compareTo((K) key) > 0) {
                    if (raiz.hijoIzq.raiz == null) {
                        return false;
                    } else {
                        return (raiz.hijoIzq).containsKey(key);
                    }
                } else {
                    if (raiz.hijoDer.raiz == null) {
                        return false;
                    } else {
                        return (raiz.hijoDer).containsKey(key);
                    }
                }
            }
        } catch (NullPointerException exception) {
            System.out.println("Null Pointer: " + exception.toString());
        }
        return false;
    }

    /**
     * Retorna true si el valor esta asociado a algun nodo del arbol.
     * @param value valor cuya presencia en el arbol se va a evaluar.
     * @return true si el valor esta relacionado a algun nodo.
     */
    @Override
    public boolean containsValue(Object value) {
        try {
            if (value == null) {
                throw new NullPointerException();
            }

            if ((raiz.valor).equals(value)) {
                return true;
            } else {
                boolean estaIzq = (raiz.hijoIzq).containsValue(value);
                if (!estaIzq) {
                    return raiz.hijoDer.containsValue(value);
                }
                return false;
                /*if ((raiz.llave).compareTo((K) key) > 0) {
                    if (raiz.hijoIzq.raiz == null) {
                        return false;
                    } else {
                        return (raiz.hijoIzq).containsKey(key);
                    }
                } else {
                    if (raiz.hijoDer.raiz == null) {
                        return false;
                    } else {
                        return (raiz.hijoDer).containsKey(key);
                    }
                }*/
            }
        } catch (NullPointerException exception) {
            System.out.println("Null Pointer: " + exception.toString());
        }
        return false;
    }

    /**
     * Retorna el valor asociado a una llave especifica.
     * @param key La llave asociada al valor que se quiere recibir.
     * @return el valor asociado a la llave.
     */
    @Override
    public V get(K key) {
        try {
            if (key == null) {
                throw new NullPointerException();
            }

            if ((raiz.llave).equals(key)) {
                return raiz.valor;
            } else {
                if ((raiz.llave).compareTo(key) > 0) {
                    if (raiz.hijoIzq == null) {
                        return null;
                    } else {
                        return (V) (raiz.hijoIzq).get(key);
                    }
                } else {
                    if (raiz.hijoDer == null) {
                        return null;
                    } else {
                        return (V) (raiz.hijoDer).get(key);
                    }
                }
            }
        } catch (NullPointerException exception) {
            System.out.println("Null Pointer: " + exception.toString());
        }
        return null;
    }

    /**
     * Agrega un nuevo nodo asociados a un valor K, y un valor V.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return Si la llave ya existe en el arbol, retorna el value asociado anterior.
     */
    @Override
    public V put(K key, V value) {
        try {
            if ((key == null) || (value == null)) {
                throw new NullPointerException();
            }

            int caso; // Para verificar cual en cual caso se agrega.

            Nodo it1 = raiz;
            Nodo padre = null;
            Nodo tio = null;

            if (raiz == null) { //Raiz vacia = se agrega en la raiz.
                raiz = new Nodo(key, value, null);
                it1 = raiz;
                caso = 1;
            } else {
                while (it1 != null) { // Se busca la posicion donde se va a agregar el nuevo nodo.
                    if (it1.llave.compareTo(key) == 0) {
                        V retorno = it1.valor;
                        it1.valor = value;
                        return retorno;
                    }
                    if ((it1.llave).compareTo(key) > 0) {
                        if (it1.hijoIzq == null) {
                            it1.hijoIzq = new RedBlackTree();
                        }
                        padre = it1;
                        it1 = it1.hijoIzq.raiz;
                    } else {
                        if (it1.hijoDer == null) {
                            it1.hijoDer = new RedBlackTree();
                        }
                        padre = it1;
                        it1 = it1.hijoDer.raiz;
                    }
                }

                // Se agrega el nodo en la posicion.
                if ((padre.hijoDer != null) && (padre.hijoDer.raiz == it1)) {
                    Nodo nuevo = new Nodo(key, value,padre);
                    padre.hijoDer.raiz = nuevo;
                    it1 = nuevo;
                }
                if ((padre.hijoIzq != null) && (padre.hijoIzq.raiz == it1)) {
                    Nodo nuevo = new Nodo(key, value,padre);
                    padre.hijoIzq.raiz = nuevo;
                    it1 = nuevo;
                }

                //Se revisa el color del padre.
                if (padre.color == false) { // Si padre es negro, caso 2 => no se hace nada.
                    caso = 2;
                } else { // padre es rojo.
                    K llavePadre = padre.llave;
                    K llaveAbuelo = padre.padre.llave;

                    if (llavePadre.compareTo(llaveAbuelo) < 0) { // tio es hijo dercho del abuelo
                        if ((padre.padre).hijoDer == null) {
                            tio = null;
                            caso = 4; // padre rojo y tio negro
                        } else {
                            tio = (padre.padre).hijoDer.raiz;
                            if (tio.color) { // padre y tio rojos.
                                caso = 3;
                            } else { // padre rojo y tio negro.
                                caso = 4;
                            }
                        }
                    } else { // tio es hijo izquierdo del abuelo.
                        if ((padre.padre).hijoIzq == null) {
                            tio = null;
                            caso = 4; // padre rojo y tio negro
                        } else {
                            tio = (padre.padre).hijoIzq.raiz;
                            if (tio.color) { // padre y tio rojos.
                                caso = 3;
                            } else { // padre rojo y tio negro.
                                caso = 4;
                            }
                        }
                    }
                }
            }
            revisar(caso, it1, padre, tio);
        } catch (NullPointerException exception) {
            System.out.println(exception.toString());
        }
        return null;
    }

    /**
     * Revisa cual caso ocurre a la hora de agregar un nuevo nodo.
     * @param pCaso Numero de caso. Estos numeros son respecto a los vistos en clase.
     * @param pX Nodo que se esta agregando.
     * @param pPadre Padre del nodo que se agrega.
     * @param pTio Tio del nodo que se agrega.
     */
    private void revisar(int pCaso, Nodo pX, Nodo pPadre, Nodo pTio) {
        switch (pCaso) {
            case 1: // Se agrega x en la raiz
                if (pX.padre == null) {
                    pX.color = false;
                } else {
                    if (pX.padre.color == false) {
                        revisar(2, pX, pPadre, pTio);
                    } else {
                        revisar(3, pX, pPadre, pTio);
                    }
                }
                break;
            case 2: // El padre de x es negro
                break;
            case 3: // El padre y el tio de x son rojos. Recoloreo: abuelo rojo, padre y tio negros. Recursivamente para arriba.
                Nodo abuelo = pPadre.padre;
                abuelo.color = true;
                pPadre.color = false;
                pTio.color = false;

                Nodo tio = null;
                if (abuelo.padre != null) {
                    if (abuelo.padre.hijoDer.raiz == abuelo) {
                        tio = abuelo.hijoIzq.raiz;
                    } else {
                        tio = abuelo.hijoDer.raiz;
                    }
                }
                revisar(1, abuelo, abuelo.padre, tio);
                break;
            case 4: // El padre de x es rojo y el tio de x es negro
                Nodo abuelo4 = pPadre.padre;
                K valorPadre = pPadre.llave;
                K valorAbuelo = abuelo4.llave;
                K valorNodo = pX.llave;
                if (valorPadre.compareTo(valorAbuelo) < 0) { // Papa esta a la izq del abuelo.
                    if (valorNodo.compareTo(valorPadre) < 0) { // Hijo Izq del padre => rotacion derecha
                        rotarDer(pX, pPadre, pPadre.padre);
                        if (pPadre.padre == null) {
                            raiz = pPadre;
                        } else {
                            abuelo4 = pPadre.padre;
                            valorAbuelo = abuelo4.llave;
                            if (valorAbuelo.compareTo(pPadre.llave) < 0) {
                                abuelo4.hijoIzq.raiz = pPadre;
                            } else {
                                abuelo4.hijoDer.raiz = pPadre;
                            }
                        }
                    } else { // Hijo Der del padre => rotacion doble izq-der
                        pPadre = rotarIzq(pX, pPadre, pPadre.padre);
                        pX = pPadre.hijoIzq.raiz;
                        pPadre = rotarDer(pX, pPadre, pPadre.padre);
                        if (pPadre.padre == null) {
                            raiz = pPadre;
                        } else {
                            abuelo4 = pPadre.padre;
                            valorAbuelo = abuelo4.llave;
                            if (valorAbuelo.compareTo(pPadre.llave) < 0) {
                                abuelo4.hijoIzq.raiz = pPadre;
                            } else {
                                abuelo4.hijoDer.raiz = pPadre;
                            }
                        }
                    }
                }

                if (valorPadre.compareTo(valorAbuelo) > 0) { // Papa esta a la der del abuelo.
                    if (valorNodo.compareTo(valorPadre) > 0) { // Hijo der del padre => rotacion izq
                        pPadre = rotarIzq(pX, pPadre, pPadre.padre);
                        if (pPadre.padre == null) {
                            raiz = pPadre;
                        } else {
                            abuelo4 = pPadre.padre;
                            valorAbuelo = abuelo4.llave;
                            if (valorAbuelo.compareTo(pPadre.llave) < 0) {
                                abuelo4.hijoDer.raiz = pPadre;
                            } else {
                                abuelo4.hijoIzq.raiz = pPadre;
                            }
                        }
                    } else { // Hijo Der del padre => rotacion doble der-izq
                        pPadre = rotarDer(pX, pPadre, pPadre.padre);
                        pX = pPadre.hijoDer.raiz;
                        pPadre = rotarIzq(pX, pPadre, pPadre.padre);
                        if (pPadre.padre == null) {
                            raiz = pPadre;
                        } else {
                            abuelo4 = pPadre.padre;
                            valorAbuelo = abuelo4.llave;
                            if (valorAbuelo.compareTo(pPadre.llave) > 0) {
                                abuelo4.hijoDer.raiz = pPadre;
                            } else {
                                abuelo4.hijoIzq.raiz = pPadre;
                            }
                        }
                    }
                }

                break;
        }
    }

    /**
     * Aplica la rotacion hacia la izquierda entre los nodos que se reciben.
     * @param pX El nodo recien agregado al arbol.
     * @param pPadre El papa del nodo recien agregado al arbol.
     * @param pAbuelo El abuelo del nodo recien agregado al arbol.
     * @return La raiz del subarbol ya balanceado.
     */
    private Nodo rotarIzq(Nodo pX, Nodo pPadre, Nodo pAbuelo) {
        K valorAbuelo = pAbuelo.llave;
        K valorPadre = pPadre.llave;
        if (valorPadre.compareTo(valorAbuelo) < 0) {
            // pPadre esta a la izq de abuelo. Significa que esto se llama como parte de una rotacion doble.
            pPadre.hijoDer = pX.hijoIzq;
            if (pPadre.hijoDer != null) {
                pPadre.hijoDer.raiz.padre = pPadre;
            }
            pX.hijoIzq.raiz = pPadre;
            pPadre.padre = pX;
            pAbuelo.hijoIzq.raiz = pX;
            pX.padre = pAbuelo;
            return pX;
        } else {
            // pPadre esta a la derecha del abuelo. Rotacion simple.
            pAbuelo.hijoDer = pPadre.hijoIzq;
            if (pAbuelo.hijoDer != null) {
                pAbuelo.hijoDer.raiz.padre = pAbuelo;
            }

            pPadre.padre = pAbuelo.padre;
            if (pPadre.padre != null) {
                if (valorPadre.compareTo(pPadre.padre.llave) > 0) {
                    pPadre.padre.hijoDer.raiz = pPadre;
                } else {
                    pPadre.padre.hijoIzq.raiz = pPadre;
                }
            }

            pAbuelo.padre = pPadre;
            if (pPadre.hijoIzq == null) {
                pPadre.hijoIzq = new RedBlackTree();
            }
            pPadre.hijoIzq.raiz = pAbuelo;


            pPadre.color = false;
            pX.color = true;
            pAbuelo.color = true;

            return pPadre;
        }
    }

    /**
     * Aplica la rotacion hacia la derecha entre los nodos que se reciben.
     * @param pX El nodo recien agregado al arbol.
     * @param pPadre El papa del nodo recien agregado al arbol.
     * @param pAbuelo El abuelo del nodo recien agregado al arbol.
     * @return La raiz del subarbol ya balanceado.
     */
    private Nodo rotarDer(Nodo pX, Nodo pPadre, Nodo pAbuelo) {
        K valorAbuelo = pAbuelo.llave;
        K valorPadre = pPadre.llave;
        if (valorPadre.compareTo(valorAbuelo) > 0) {
            // pPadre esta a la der de abuelo. Significa que esto se llama como parte de una rotacion doble.
            pPadre.hijoIzq = pX.hijoDer;
            if (pPadre.hijoIzq != null) {
                pPadre.hijoIzq.raiz.padre = pPadre;
            }

            if (pX.hijoDer == null) {
                pX.hijoDer = new RedBlackTree();
            }
            pX.hijoDer.raiz = pPadre;
            pPadre.padre = pX;
            pAbuelo.hijoDer.raiz = pX;
            pX.padre = pAbuelo;
            return pX;
        } else {
            // pPadre esta a la izquierda del abuelo. Rotacion simple.
            pAbuelo.hijoIzq = pPadre.hijoDer;
            if (pAbuelo.hijoIzq != null) {
                pAbuelo.hijoIzq.raiz.padre = pAbuelo;
            }

            pPadre.padre = pAbuelo.padre;
            if (pPadre.padre != null) {
                if (valorPadre.compareTo(pPadre.padre.llave) > 0) {
                    pPadre.padre.hijoDer.raiz = pPadre;
                } else {
                    pPadre.padre.hijoIzq.raiz = pPadre;
                }
            }

            pAbuelo.padre = pPadre;
            pPadre.hijoDer.raiz = pAbuelo;

            pPadre.color = false;
            pX.color = true;
            pAbuelo.color = true;

            return pPadre;
        }
    }

    /**
     * Elimina un nodo, y se hace el reacomodo.
     * @param key key whose mapping is to be removed from the dictionary
     * @return el valor asociado al nodo que se borra.
     */
    @Override
    public V remove(K key) {
        Nodo aBorrar = getSubTree(key);
        if (aBorrar != null) {
            // Se borra una hoja.
            if (aBorrar.hijoIzq == null && aBorrar.hijoDer == null) {
                Nodo padre = aBorrar.padre;
                if (padre.hijoIzq.raiz == aBorrar) {
                    padre.hijoIzq = null;
                } else {
                    padre.hijoDer = null;
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
                        aBorrar.llave = it1.llave;
                        if (it1.hijoIzq == null && it1.hijoDer == null) {
                            esHoja = true;
                        } else {
                            aBorrar = it1;
                        }
                    }
                    Nodo padre = it1.padre;
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
                        aBorrar.llave = it1.llave;
                        if (it1.hijoIzq == null && it1.hijoDer == null) {
                            esHoja = true;
                        } else {
                            aBorrar = it1;
                        }
                    }
                    Nodo padre = it1.padre;
                    if (padre.hijoIzq.raiz == it1) {
                        padre.hijoIzq = null;
                    } else {
                        padre.hijoDer = null;
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
    private Nodo getSubTree(K key) {
        if (raiz != null) {
            if ((raiz.llave).equals(key)) {
                return raiz;
            } else {
                if ((raiz.llave).compareTo(key) > 0) {
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
     * Elimina todos los nodos del arbol, el cual queda vacio despues del llamado.
     */
    @Override
    public void clear() {
        try {
            raiz = null;
        } catch (UnsupportedOperationException exception) {
            System.out.println("Unsupported Operation: " + exception.toString());
        }
    }

    /**
     * Retorna un conjunto (set) con las llaves contenidas en el diccionario.
     * @return conjunto (set) de las llaves que se encuentran en el diccionario.
     */
    @Override
    public Set<K> keySet() {
        Set<K> llaves = new LinkedListSet<>();
        LinkedListQueue<Nodo> cola = new LinkedListQueue<>();
        cola.enqueue(raiz);
        while (!cola.isEmpty()) {
            Nodo aux = cola.dequeue();
            llaves.put(aux.llave);
            if (aux.hijoIzq != null) {
                cola.enqueue(aux.hijoIzq.raiz);
            }
            if (aux.hijoDer != null) {
                cola.enqueue(aux.hijoDer.raiz);
            }
        }
        return llaves;
    }

    /**
     * Retorna una lista con todos los values contenidos en el diccionario.
     * @return una lista con todos los values que se encuentran en el diccionario.
     */
    @Override
    public List<V> values() {
        List<V> valores = new LinkedList<>();
        LinkedListQueue<Nodo> cola = new LinkedListQueue<>();
        cola.enqueue(raiz);
        while (!cola.isEmpty()) {
            Nodo aux = cola.dequeue();
            valores.add(aux.valor);
            if (aux.hijoIzq != null) {
                cola.enqueue(aux.hijoIzq.raiz);
            }
            if (aux.hijoDer != null) {
                cola.enqueue(aux.hijoDer.raiz);
            }
        }
        return valores;
    }
}
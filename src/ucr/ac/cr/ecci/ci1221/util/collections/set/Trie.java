package ucr.ac.cr.ecci.ci1221.util.collections.set;

import java.util.Iterator;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.stack.LinkedListStack;
import ucr.ac.cr.ecci.ci1221.util.collections.stack.Stack;

/**
 * Clase que representa el set implementado por Trie.
 * @author Eduardo Biazzetti.
 */
public class Trie implements Set<String> {

    /** Puntero al arreglo inicial. */
    private Nodo[] head = new Nodo[256];
    /** Tamano del trie (cantidad de palabras diferentes). */
    private int tamTrie = 0;

    /**
     * Clase auxiliar. Cada nodo representa una letra, la cual tiene un puntero hacia otro arrglo.
     */
    private class Nodo {

        /** Representa el array al que apunta el nodo. */
        private Nodo[] array2;
        /** Booleano que indica si la palabra llego a su ultimo caracter o no. */
        private boolean termino;

        /**
         * Constructor de la clase Nodo.
         * @param letra letra que representa el Nodo.
         */
        private Nodo(char letra) {
            if (letra == '$') {
                termino = true;
                array2 = null;
            } else {
                array2 = new Nodo[256];
                termino = false;
            }
        }

        /**
         * Constructor de la clase Nodo.
         */
        private Nodo() {
            array2 = new Nodo[256];
            termino = false;
        }
    }

    /**
     * Iterador para set implementado mediante Trie.
     */
    private class TrieIterator implements Iterator<String> {

        /** Lista que guarda cada una de las palabras contenidas en el trie. */
        private List<String> lista = new LinkedList<>();
        /** Posicion actual en la lista. */
        private int it1 = 0;

        /**
         * Constructor del iterador.
         */
        public TrieIterator() {
            recursivo(head, "");
            it1 = lista.first();
        }

        /**
         * Metodo recursivo. Se usa para recorrer cada una de las casillas y armar la casilla.
         * Una vez armada, se guarda en la lista.
         * @param pArray El arreglo de letras que se van a revisar.
         * @param pHilera La raiz de la palabra actual.
         */
        private void recursivo(Nodo[] pArray, String pHilera) {
            String hilera = pHilera;
            for (int x = 0; x < 256; x++) {
                if (pArray[x] != null) {
                    if ((char) x == '$' && pArray['$'] != null && pArray['$'].termino) {
                        lista.add(hilera);
                    } else {
                        hilera += (char) x;
                        recursivo(pArray[x].array2, hilera);
                        hilera = pHilera;
                    }
                }
            }
        }

        /**
         * Revisa si aún existen más elementos en la lista.
         * @return false si el iterador está en el ultimo elemento; true, en caso contrario.
         */
        public boolean hasNext() {
            return lista.size() > 0 && it1 <= lista.size();
        }

        /**
         * Retorna el siguiente elemento del iterador.
         * @return Siguiente elemento; si no existiera, null.
         */
        public String next() { return lista.get(it1++); }
    }

    /**
     * Une los elementos de los dos sets en uno solo, el actual y el que se recibe como parametro.
     * @param set el set con el que se va a unir.
     * @return Un set con la union entre ambos.
     */
    @Override
    public Set<String> union(Set<String> set) {
        Iterator<String> it1 = set.iterator();
        Iterator<String> it2 = iterator();
        Set<String> nuevoSet = new Trie();
        while (it1.hasNext()) {
            nuevoSet.put(it1.next());
        }
        while (it2.hasNext()) {
            String aux = it2.next();
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
    public Set<String> intersection(Set<String> set) {
        Set<String> nuevoSet = new Trie();
        Iterator<String> it1 = iterator();
        while (it1.hasNext()) {
			String aux = it1.next();
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
    public Set<String> difference(Set<String> set) {
        Set<String> nuevoSet = new Trie();
        Iterator<String> it1 = iterator();
        while (it1.hasNext()) {
			String aux = it1.next();
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
    public boolean isMember(String key) {
        if (tamTrie != 0) {
            Nodo[] it1 = head;
            for (int x = 0; x < key.length(); x++) {
                char letra = key.charAt(x);
                if (it1[letra] != null) {
                    it1 = it1[letra].array2;
                } else {
					return false;
				}
            }
            if (it1['$'] != null && it1['$'].termino) {
                return true;
            }
        }
        return false;
    }

    /**
     * Agrega un nuevo String en el trie.
     * @param key el string que se va a agregar.
     */
    @Override
    public void put(String key) {
        Nodo[] it1 = head;
        Nodo[] padre = null;
        int posLetraAnterior = 0;

        for (int x = 0; x < key.length(); x++) {
            char letra = key.charAt(x);

            if (x == 0) {
                if (head[letra] == null) {
                    head[letra] = new Nodo();
                }
            } else {
                if (it1[letra] == null) {
                    padre[posLetraAnterior].array2[letra] = new Nodo();
                }
            }
            padre = it1;
            it1 = it1[letra].array2;
            posLetraAnterior = letra;
        }

        if (padre != null) {
            padre[posLetraAnterior].array2['$'] = new Nodo('$');
        } else {
            head['$'] = new Nodo('$');
        }

        tamTrie++;
    }

    /**
     * Elimina un elemento de la lista.
     * @param key El elemento que se va a borrar.
     */
    @Override
    public void remove(String key) {
        if (isMember(key)) {
            Nodo[] it1 = head;
            Stack<Nodo[]> pilaNodos = new LinkedListStack<>();
            Stack<Integer> pilaNivel = new LinkedListStack<>();
            for (int x = 0; x < key.length(); x++) {
                char letra = key.charAt(x);
                if (x == 0) {
                    pilaNodos.push(head);
                } else {
                    pilaNodos.push(it1);
                }
                pilaNivel.push(x);
                it1 = it1[letra].array2;
            }

            if (it1['$'] != null && it1['$'].termino) {
                Nodo[] arrayPadre = pilaNodos.pop();
                int nivel = pilaNivel.pop();
                char c = key.charAt(nivel);
                arrayPadre[c].array2['$'] = null;
                boolean seguir = true;
                while (!pilaNivel.isEmpty() && seguir) {
                    int hijos = cantidadHijos(arrayPadre, key.charAt(nivel));
                    if (hijos == 0) {
                        seguir = false;
                    } else {
                        arrayPadre = pilaNodos.pop();
                        nivel = pilaNivel.pop();
                    }
                }
            }
            tamTrie--;
        }
    }

    /**
     * Calcula la cantidad de hijos para un nodo (o letra) especifica.
     * @param pArray el array padre, el cual tiene la letra a la que se le quiere calcular la cantidad de hijos.
     * @param pLetra la letra a la cual se le calculan la cantidad de hijos.
     * @return cantidad de hijos para una letra especifica.
     */
    private int cantidadHijos(Nodo[] pArray, char pLetra) {
        Nodo[] it1 = pArray[pLetra].array2;
        int counter = 0;
        for (int x = 0; x < 256; x++) {
            if (it1[x] != null) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Revisa si el trie esta vacio, devolviendo true en caso de que asi sea.
     * @return true si el trie esta vacio; false, en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return tamTrie == 0;
    }

    /**
     * Vacia el trie, dejandolo sin elementos.
     */
    @Override
    public void clear() {
        head = new Nodo[256];
        tamTrie = 0;
    }

    /**
     * Retorna el numero de elementos de la lista.
     * @return Cantidad de elementos en la lista.
     */
    @Override
    public int size() {
        return tamTrie;
    }

    /**
     * Devuelve un iterador para recorrer los elementos que estan en el trie.
     * @return Un iterador de Trie.
     */
    @Override
    public Iterator<String> iterator() {
        return new TrieIterator();
    }
}
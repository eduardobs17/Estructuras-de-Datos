package ucr.ac.cr.ecci.ci1221.util.graph;

import java.util.Iterator;
import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * Clase que representa el grafo implementado por Lista de Adyacencias.
 * @author Eduardo Biazzetti.
 */
public class AdjacencyList<V> implements Graph<V> {

    /** Indica si el grafo es dirigido o no. */
    private boolean esDirigido;
    /** Indica si las aristas del grafo tienen peso o no. */
    private boolean conPeso;
    /** Puntero al primer vertice de la lista. */
    private Vertice head;

    /**
     * Representa los vertices del grafo.
     */
    private class Vertice {

        /** Representa las adyacencias del vertice. */
        private List<Par> adyacencias;
        /** Puntero al siguiente vertice. */
        private Vertice siguiente;
        /** Valor que guarda el vertice. */
        private V valor;

        /**
         * Constructor de Vertice.
         *
         * @param pValor Valor que el vertice va a almacenar.
         */
        private Vertice(V pValor) {
            adyacencias = null;
            siguiente = null;
            valor = pValor;
        }
    }

    /**
     * Clase que representa las adyacencias de un vertice.
     * Guarda 2 valores (por eso el nombre): el peso de la arista (si hubiera) y el valor de la adyacencia.
     */
    private class Par {

        /** Valor del peso de la arista. */
        private double peso;
        /** Valor del verticce adyacente. */
        private V value;

        /**
         * Constructor de Trio.
         *
         * @param pValue Valor que se va a almacenar.
         * @param pPeso Peso que se va a almacenar.
         */
        private Par(V pValue, double pPeso) {
            peso = pPeso;
            value = pValue;
        }
    }

    /**
     * Clase que representa el iterador de AdjacencyList.
     */
    private class ALIterator implements Iterator<V> {

        /** Vertice actual del iterador. */
        private Vertice posActual = head;

        /**
         * Revisa si aun existen mas elementos sin revisar en el grafo.
         *
         * @return false si el iterador está en el ultimo elemento; true, en caso contrario.
         */
        public boolean hasNext() { return posActual != null; }

        /**
         * Retorna el siguiente elemento del iterador.
         * @return Siguiente elemento.
         */
        public V next() {
            V aRetornar = posActual.valor;
            posActual = posActual.siguiente;
            return aRetornar;
        }
    }

    /**
     * Constructor de AdjancencyList. Por defecto se crea no dirigido y con pesos.
     */
    public AdjacencyList() {
        esDirigido = false;
        conPeso = true;
        head = null;
    }

    /**
     * Constructor de AdjancencyList.
     *
     * @param grafoDirigido Indica si el grafo sera dirigido o no.
     * @param pPeso Indica si el grafo sera con o sin peso.
     */
    public AdjacencyList(boolean grafoDirigido, boolean pPeso) {
        esDirigido = grafoDirigido;
        conPeso = pPeso;
        head = null;
    }

    /**
     * Indica si el grafo es o no es dirigido.
     *
     * @return true si el grafo es dirigido; false, en caso contrario.
     */
    @Override
    public boolean isDirected() {
        return esDirigido;
    }

    /**
     * Agrega un vertice con el valor dado al grafo.
     *
     * @param value El nuevo valor que se va a agregar.
     */
    @Override
    public void addNode(V value) {
        if (head == null) {
            head = new Vertice(value);
        } else {
            Vertice it1 = head;
            boolean seguir = true;
            do {
                if ((it1.valor).equals(value)) {
                    it1 = null;
                    seguir = false;
                } else {
                    if (it1.siguiente != null) {
                        it1 = it1.siguiente;
                    } else {
                        seguir = false;
                    }
                }
            } while (seguir);

            if (it1 != null) {
                it1.siguiente = new Vertice(value);
            }
        }
    }

    /**
     * Agrega al grafo un vertice por cada valor dado y crea una arista entre ambos.
     *
     * @param value1 El primer valor que se va a agregar.
     * @param value2 El segundo valor que se va a agregar.
     */
    @Override
    public void addNodes(V value1, V value2) {
        addNode(value1);
        addNode(value2);

        addEdge(value1, value2);
    }

    /**
     * Agrega una arista entre 2 vertices, siempre y cuando existan en el grafo y no exista arista ya creada entre ambos.
     *
     * @param value1 El valor del primer vertice.
     * @param value2 El valor del segundo vertice.
     */
    @Override
    public void addEdge(V value1, V value2) {
        addEdge(value1, value2, 1);
    }

    /**
     * Agrega una arista con un peso especifico entre 2 vertices,
     * siempre y cuando existan en el grafo y no exista arista ya creada entre ambos.
     *
     * @param value1 El valor del primer vertice.
     * @param value2 El valor del segundo vertice.
     * @param weight El peso de la arista.
     */
    @Override
    public void addEdge(V value1, V value2, double weight) {
        Vertice it1 = head;
        Vertice vert1 = null;
        Vertice vert2 = null;

        while (it1 != null) {
            if ((it1.valor).equals(value1)) {
                vert1 = it1;
            }
            if ((it1.valor).equals(value2)) {
                vert2 = it1;
            }
            if (vert1 != null && vert2 != null) {
                it1 = null;
            } else {
                it1 = it1.siguiente;
            }
        }

        if (vert1 != null && vert2 != null) {
            if (!areLinked(value1, value2)) {
                if (vert1.adyacencias == null) {
                    vert1.adyacencias = new LinkedList<>();
                }
                if (conPeso) {
                    Par adyacencia12 = new Par(value2, weight);
                    vert1.adyacencias.add(adyacencia12);
                    if (!esDirigido) {
                        if (vert2.adyacencias == null) {
                            vert2.adyacencias = new LinkedList<>();
                        }
                        Par adyacencia21 = new Par(value1, weight);
                        vert2.adyacencias.add(adyacencia21);
                    }
                } else {
                    Par adyacencia12 = new Par(value2, 0);
                    vert1.adyacencias.add(adyacencia12);
                    if (!esDirigido) {
                        if (vert2.adyacencias == null) {
                            vert2.adyacencias = new LinkedList<>();
                        }
                        Par adyacencia21 = new Par(value1, 0);
                        vert2.adyacencias.add(adyacencia21);
                    }
                }
            }
        }
    }

    /**
     * Revisa si el grafo contiene el valor dado.
     *
     * @param value El valor que se va a buscar.
     * @return true si el valor esta en el grafo; false, en caso contrario.
     */
    @Override
    public boolean contains(V value) {
        Vertice it1 = head;
        while (it1 != null) {
            if ((it1.valor).equals(value)) {
                return true;
            } else {
                it1 = it1.siguiente;
            }
        }
        return false;
    }

    /**
     * Busca el vertice asociado a un valor dado y lo devuelve.
     *
     * @param value El valor del vertice que se quiere recuperar.
     * @return El vertice con el valor asociado.
     */
    private Vertice buscarVertice(V value) {
        Vertice it1 = head;
        while (it1 != null) {
            if ((it1.valor).equals(value)) {
                return it1;
            } else {
                it1 = it1.siguiente;
            }
        }
        return null;
    }

    /**
     * Retorna true si existe una arista entre dos valores dados.
     *
     * @param value1 El primer valor.
     * @param value2 El segundo valor.
     * @return true si existe una arista entre ambos valores.
     */
    @Override
    public boolean areLinked(V value1, V value2) {
        Vertice it1 = buscarVertice(value1);
        if (it1 != null && it1.adyacencias != null) {
            Iterator<Par> it2 = it1.adyacencias.iterator();
            while (it2.hasNext()) {
                Par aux = it2.next();
                if ((aux.value).equals(value2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Devuelve el peso de la arista entre dos valores dados (si existe).
     * Si no existe, se devuelve 0. Si el grafo es sin pesos, se devuelve 0.
     *
     * @param value1 El primer valor.
     * @param value2 El segundo valor.
     * @return El peso de la arista.
     */
    @Override
    public double getWeight(V value1, V value2) {
        Vertice it1 = buscarVertice(value1);

        if (it1 != null) {
            Iterator<Par> it2 = it1.adyacencias.iterator();
            while (it2.hasNext()) {
                Par aux = it2.next();
                if ((aux.value).equals(value2)) {
                    return aux.peso;
                }
            }
        }
        return 0;
    }

    /**
     * Remueve un valor del grafo.
     *
     * @param value El valor que se va a eliminar.
     */
    @Override
    public void removeValue(V value) {
        if (contains(value)) {

            Vertice it1 = head;
            Vertice anterior = null;
            while (it1 != null) {
                if (it1.valor.equals(value)) {
                    if (it1 == head) {
                        head = it1.siguiente;
                        it1 = head;
                    } else {
                        if (anterior != null) {
                            anterior.siguiente = it1.siguiente;
                            it1 = anterior.siguiente;
                        }
                    }
                } else {
                    if (it1.adyacencias != null) {
                        for (int x = 1; x <= it1.adyacencias.size(); x++) {
                            if ((it1.adyacencias.get(x).value).equals(value)) {
                                it1.adyacencias.remove(x);
                                x = Integer.MAX_VALUE - 1; // Para que termine el ciclo.
                            }
                        }
                    }
                    anterior = it1;
                    it1 = it1.siguiente;
                }
            }
        }
    }

    /**
     * Remueve la arista entre 2 valores dados, si existe.
     *
     * @param value1 El primer valor.
     * @param value2 El segundo valor.
     */
    @Override
    public void removeEdge(V value1, V value2) {
        Vertice it1 = buscarVertice(value1);
        boolean existeAdyacencia = false;
        if (it1 != null && it1.adyacencias != null) {
            int tamLista = it1.adyacencias.size();
            for (int x = 1; x <= tamLista; x++) {
                if ((it1.adyacencias.get(x).value).equals(value2)) {
                    it1.adyacencias.remove(x);
                    x = tamLista * 7;
                    existeAdyacencia = true;
                }
            }

            if (it1.adyacencias.size() == 0) {
                it1.adyacencias = null;
            }

            if (!esDirigido && existeAdyacencia) {
                Vertice it2 = buscarVertice(value2);
                if (it2 != null && it2.adyacencias != null) {
                    int tamLista2 = it2.adyacencias.size();
                    for (int x = 1; x <= tamLista2; x++) {
                        if ((it2.adyacencias.get(x).value).equals(value1)) {
                            it2.adyacencias.remove(x);
                            x = tamLista2 * 7;
                        }
                    }
                    if (it2.adyacencias.size() == 0) {
                        it2.adyacencias = null;
                    }
                }
            }
        }
    }

    /**
     * Retorna una lista con todos los valores adyacentes a un valor dado.
     *
     * @param value El valor que se quiere averiguar.
     * @return Lista de nodos adyacentes.
     */
    @Override
    public List<V> getAdjacentNodes(V value) {
        Vertice it1 = head;
        List<V> lista = new LinkedList<>();
        while (it1 != null) {
            if ((it1.valor).equals(value)) {
                if (it1.adyacencias != null) {
                    Iterator<Par> it2 = it1.adyacencias.iterator();
                    while (it2.hasNext()) {
                        lista.add(it2.next().value);
                    }
                }
                return lista;
            } else {
                it1 = it1.siguiente;
            }
        }
        return lista;
    }

    /**
     * Retorna una lista con todos los valores que posee el grafo.
     *
     * @return Todos los valores del grafo.
     */
    @Override
    public List<V> getValues() {
        List<V> lista = new LinkedList<>();
        Vertice it1 = head;
        while (it1 != null) {
            lista.add(it1.valor);
            it1 = it1.siguiente;
        }
        return lista;
    }

    /**
     * Retorna la cantidad de vertices en el grafo.
     * @return numero de vertices.
     */
    @Override
    public int V() {
        int counter = 0;
        Vertice it1 = head;
        while (it1 != null) {
            counter++;
            it1 = it1.siguiente;
        }
        return counter;
    }

    /**
     * Retorna la cantidad de aristas en el grafo.
     *
     * @return numero de aristas.
     */
    @Override
    public int E() {
        int cantidadAristas = 0;
        Vertice it1 = head;
        while (it1 != null) {
            if (it1.adyacencias != null) {
                Iterator<Par> it2 = it1.adyacencias.iterator();
                while (it2.hasNext()) {
                    it2.next();
                    cantidadAristas++;
                }
            }
            it1 = it1.siguiente;
        }

        if (!esDirigido) {
            cantidadAristas /= 2;
        }

        return cantidadAristas;
    }

    /**
     * Devuelve un iterador para recorrer los elementos que estan en el grafo.
     * @return Un iterador de AL.
     */
    @Override
    public Iterator<V> iterator() {
        return new ALIterator();
    }

    /**
     * Retorna el numero de vertices del grafo.
     *
     * @return tamaño del grafo.
     */
    @Override
    public int size() {
        int counter = 0;
        Vertice it1 = head;
        while (it1 != null) {
            counter++;
            it1 = it1.siguiente;
        }
        if (counter < Integer.MAX_VALUE) {
            return counter;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Calcula la suma de todas las aristas del grafo y las retorna.
     *
     * @return El peso del grafo.
     */
    @Override
    public double getWeight() {
        if (!conPeso) {
            return 0;
        } else {
            double counter = 0;
            Vertice it1 = head;
            while (it1 != null) {
                if (it1.adyacencias != null) {
                    Iterator<Par> it2 = it1.adyacencias.iterator();
                    while (it2.hasNext()) {
                        counter += it2.next().peso;
                    }
                }
                it1 = it1.siguiente;
            }
            if (!esDirigido) {
                counter /= 2;
            }
            return counter;
        }
    }

    /**
     * Revisa si el grafo tiene o no vertices, devolviendo true en caso de que no tenga.
     *
     * @return true si el esta vacio; false, en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Vacia el grafo, dejandolo sin ningun vertice.
     */
    @Override
    public void clear() {
        head = null;
    }

    /**
     * Devuelve un arreglo que representa la posicion de los valores en la estructura
     * interna usada en GetGraphStructureAsMatrix.
     *
     * @return Arreglo de valores.
     */
    @Override
    public V[] getValuesAsArray() {
        Vertice it1 = head;
        V[] array = (V[]) new Object[V()];
        int counter = 0;
        while (it1 != null) {
            array[counter] = it1.valor;
            counter++;
            it1 = it1.siguiente;
        }
        return array;
    }

    /**
     * Devuelve una matriz con el peso de la aristas entre cada par de valores.
     *
     * @return matriz de doubles.
     */
    @Override
    public double[][] getGraphStructureAsMatrix(){
        int tam = V();
        double[][] matriz = new double[tam][tam];
        for (int x = 0; x < tam; x++) {
            for (int y = 0; y < tam; y++) {
                matriz[x][y] = -1;
            }
        }

        Vertice it1 = head;
        int counter = 0;
        while (it1 != null) {
            List<Par> lista = it1.adyacencias;
            if (lista != null) {
                Iterator<Par> it2 = lista.iterator();
                while (it2.hasNext()) {
                    Par aux = it2.next();
                    int pos = posEnArray(aux.value);
                    matriz[counter][pos] = aux.peso;
                }
            }
            counter++;
            it1 = it1.siguiente;
        }
        return matriz;
    }

    /**
     * Busca la posicion del vertice que contiene el valor dado en la lista interna.
     *
     * @param pValue el valor que se va a buscar.
     * @return La posicion del valor.
     */
    private int posEnArray(V pValue) {
        Vertice it1 = head;
        int counter = 0;
        while (it1 != null) {
            if (it1.valor.equals(pValue)) {
                return counter;
            } else {
                it1 = it1.siguiente;
                counter++;
            }
        }
        return -1;
    }
}
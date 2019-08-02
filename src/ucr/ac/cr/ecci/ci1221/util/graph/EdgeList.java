package ucr.ac.cr.ecci.ci1221.util.graph;

import java.util.Iterator;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;

/**
 * Clase que representa el grafo implementado por Lista de Aristas.
 * @author Eduardo Biazzetti
 */
public class EdgeList<V> implements Graph<V> {

    /** Indica si el grafo es dirigido o no. */
    private boolean esDirigido;
    /** Indica si las aristas del grafo tienen peso o no. */
    private boolean conPeso;
    /** Lista que guarda las aristas del grafo. */
    private List<Par> aristas;
    /** Conjunto donde se guardan los valores del grafo. */
    private List<V> valores;

    /**
     * Clase que representa las aristas entre nodos.
     */
    private class Par {

        /** Valor desde donde sale la arista. */
        private V valorOrigen;
        /** Valor al cual llega la arista. */
        private V valorDestino;
        /** Peso de la arista. */
        private double peso;

        /**
         * Constructor de Par
         *
         * @param pOrigen Valor origen de la arista.
         * @param pDestino Valor destino de la arista.
         * @param pPeso Peso de la arista.
         */
        private Par(V pOrigen, V pDestino, double pPeso) {
            valorDestino = pDestino;
            valorOrigen = pOrigen;
            peso = pPeso;
        }
    }

    /**
     * Constructor de EdgeList. Por defecto se crea un grafo no dirigido y con pesos.
     */
    public EdgeList() {
        esDirigido = false;
        conPeso = true;
        aristas = null;
        valores = null;
    }

    /**
     * Constructor de EdgeList.
     *
     * @param grafoDirigido Indica si el grafo sera dirigido o no.
     * @param pPeso Indica si el grafo sera con o sin peso.
     */
    public EdgeList(boolean grafoDirigido, boolean pPeso) {
        esDirigido = grafoDirigido;
        conPeso = pPeso;
        aristas = null;
        valores = null;
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
     * Agrega el valor al grafo.
     *
     * @param value El nuevo valor que se va a agregar.
     */
    @Override
    public void addNode(V value) {
        if (valores == null) {
            valores = new LinkedList<>();
        }

        if (!contains(value)) {
            valores.add(value);
        }
    }

    /**
     * Agrega al grafo los valores dados y crea una arista entre ambos.
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
     * Agrega una arista entre 2 valores, siempre y cuando existan en el grafo y no exista arista ya creada entre ambos.
     *
     * @param value1 El primer valor.
     * @param value2 El segundo valor.
     */
    @Override
    public void addEdge(V value1, V value2) {
        addEdge(value1, value2, 1);
    }

    /**
     * Agrega una arista con un peso especifico entre 2 valores,
     * siempre y cuando existan en el grafo y no exista arista ya creada entre ambos.
     *
     * @param value1 El primer valor.
     * @param value2 El segundo valor.
     * @param weight El peso de la arista.
     */
    @Override
    public void addEdge(V value1, V value2, double weight) {
        if (valores != null) {
            if (contains(value1) && contains(value2)) { // Se revisa que ambos valores esen en el grafo
                if (!areLinked(value1, value2)) {
                    if (aristas == null) {
                        aristas = new LinkedList<>();
                    }
                    if (conPeso) {
                        Par nuevaArista12 = new Par(value1, value2, weight);
                        aristas.add(nuevaArista12);
                        if (!esDirigido) {
                            Par nuevaArista21 = new Par(value2, value1, weight);
                            aristas.add(nuevaArista21);
                        }
                    } else {
                        Par nuevaArista12 = new Par(value1, value2, 0);
                        aristas.add(nuevaArista12);
                        if (!esDirigido) {
                            Par nuevaArista21 = new Par(value2, value1, 0);
                            aristas.add(nuevaArista21);
                        }
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
        if (valores != null) {
            for (int x = 1; x <= valores.size(); x++) {
                if (valores.get(x).equals(value)) {
                    return true;
                }
            }
        }
        return false;
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
        if (aristas != null) {
            Iterator<Par> it1 = aristas.iterator();
            while (it1.hasNext()) {
                Par aux1 = it1.next();
                if (aux1.valorOrigen.equals(value1)) {
                    if (aux1.valorDestino.equals(value2)) {
                        return true;
                    }
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
        if (aristas != null) {
            Iterator<Par> it1 = aristas.iterator();
            while (it1.hasNext()) {
                Par aux1 = it1.next();
                if (aux1.valorOrigen.equals(value1)) {
                    if (aux1.valorDestino.equals(value2)) {
                        return aux1.peso;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * Remueve un valor del grafo, así como las aristas que entran y salen del valor.
     *
     * @param value El valor que se va a eliminar.
     */
    @Override
    public void removeValue(V value) {
        if (valores != null) {
            boolean encontrado = false;
            for (int x = 1; x <= valores.size(); x++) {
                if (valores.get(x).equals(value)) { // Se busca primero que exista el valor.
                    encontrado = true;
                    valores.remove(x);
                    x = valores.size() * 3;
                }
            }
            if (valores.size() == 0) {
                valores = null;
            }

            if (encontrado && aristas != null) {
                int counter = 1;
                int cantidadAristas = aristas.size();
                while (counter <= cantidadAristas) {
                    // Se buscan las aristas que tengan el valor.
                    if (aristas.get(counter).valorOrigen.equals(value) || aristas.get(counter).valorDestino.equals(value)) {
                        aristas.remove(counter);
                        cantidadAristas--;
                    } else {
                        counter++;
                    }
                }
                if (aristas.size() == 0) {
                    aristas = null;
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
        if (aristas != null) {
            int cantidadAristas = aristas.size();
            int counter = 1;
            while (counter <= cantidadAristas) {
                if (aristas.get(counter).valorOrigen.equals(value1) && aristas.get(counter).valorDestino.equals(value2)) {
                    aristas.remove(counter);
                    cantidadAristas--;
                } else {
                    if (aristas.get(counter).valorOrigen.equals(value2) && aristas.get(counter).valorDestino.equals(value1)) {
                        aristas.remove(counter);
                        cantidadAristas--;
                    } else {
                        counter++;
                    }
                }
            }
            if (aristas.size() == 0) {
                aristas = null;
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
        List<V> adyacentes = new LinkedList<>();
        if (aristas != null) {
            for (int x = 1; x <= aristas.size(); x++) {
                Par aux = aristas.get(x);
                if ((aux.valorOrigen).equals(value)) {
                    adyacentes.add(aux.valorDestino);
                }
            }
        }
        return adyacentes;
    }

    /**
     * Retorna una lista con todos los valores que posee el grafo.
     *
     * @return Todos los valores del grafo.
     */
    @Override
    public List<V> getValues() {
        return valores;
    }

    /**
     * Retorna la cantidad de valores en el grafo.
     *
     * @return numero de vertices.
     */
    @Override
    public int V() {
        return valores.size();
    }

    /**
     * Retorna la cantidad de aristas en el grafo.
     *
     * @return numero de aristas.
     */
    @Override
    public int E() {
        if (esDirigido) {
            return aristas.size();
        } else {
            return aristas.size() / 2;
        }
    }

    /**
     * Devuelve un iterador para recorrer los elementos que estan en el grafo.
     * @return Un iterador de EL.
     */
    @Override
    public Iterator<V> iterator() {
        return valores.iterator();
    }

    /**
     * Calcula la suma de todas las aristas del grafo y las retorna.
     *
     * @return El peso del grafo.
     */
    @Override
    public double getWeight() {
        if (aristas != null && conPeso) {
            double total = 0;
            for (int x = 1; x <= aristas.size(); x++) {
                total += aristas.get(x).peso;
            }
            if (!esDirigido) {
                total /= 2;
            }
            return total;
        } else {
            return 0;
        }
    }

    /**
     * Retorna el numero de vertices del grafo.
     *
     * @return tamaño del grafo.
     */
    @Override
    public int size() {
        if (valores == null) {
            return 0;
        } else {
            return valores.size();
        }
    }

    /**
     * Revisa si el grafo tiene o no valores, devolviendo true en caso de que no tenga.
     *
     * @return true si el esta vacio; false, en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return valores == null;
    }

    /**
     * Vacia el grafo, dejandolo sin ningun vertice.
     */
    @Override
    public void clear() {
        valores = null;
        aristas = null;
    }

    /**
     * Devuelve un arreglo que representa la posicion de los valores en la estructura
     * interna usada en GetGraphStructureAsMatrix.
     *
     * @return Arreglo de valores.
     */
    @Override
    public V[] getValuesAsArray() {
        Iterator<V> it1 = iterator();
        V[] array = (V[]) new Object[V()];
        int pos = 0;
        while (it1.hasNext()) {
            array[pos] = it1.next();
            pos++;
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
        V[] array = getValuesAsArray();
        int tam = array.length;
        double[][] matriz = new double[tam][tam];
        for (int x = 0; x < tam; x++) {
            for (int y = 0; y < tam; y++) {
                matriz[x][y] = -1;
            }
        }

        Iterator<Par> it1 = aristas.iterator();
        while (it1.hasNext()) {
            Par aux = it1.next();
            int pos1 = posEnLista(aux.valorOrigen);
            int pos2 = posEnLista(aux.valorDestino);
            matriz[pos1][pos2] = aux.peso;
        }
        return matriz;
    }

    /**
     * Busca la posicion del vertice que contiene el valor dado en la lista interna.
     *
     * @param pValue el valor que se va a buscar.
     * @return La posicion del valor.
     */
    private int posEnLista(V pValue) {
        Iterator<V> it1 = iterator();
        int counter = 0;
        while (it1.hasNext()) {
            if (it1.next().equals(pValue)) {
                return counter;
            } else {
                counter++;
            }
        }
        return -1;
    }
}
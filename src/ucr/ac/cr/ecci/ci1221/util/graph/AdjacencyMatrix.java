package ucr.ac.cr.ecci.ci1221.util.graph;

import java.util.Iterator;
import ucr.ac.cr.ecci.ci1221.util.collections.list.ArrayList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * Clase que representa el grafo implementado por Matriz de Adyacencias.
 * @author Eduardo Biazzetti.
 */
public class AdjacencyMatrix<V> implements Graph<V>{

    /** Indica si el grafo es dirigido o no. */
    private boolean esDirigido;
    /** Indica si las aristas del grafo tienen peso o no. */
    private boolean conPeso;
    /** Matriz que representa el grafo. */
    private double matriz[][];
    /** Lista auxiliar donde se almacenan los valores. */
    private List<V> listaValores;

    /**
     * Constructor de AdjancencyMatrix. Por defecto se crea no dirigido y con pesos, y con un tamaño de 0.
     * Este se aumentara si asi se requiere.
     */
    public AdjacencyMatrix() {
        this(false, true);
    }

    /**
     * Constructor de AdjancencyMatrix.
     *
     * @param grafoDirigido Indica si el grafo sera dirigido o no.
     * @param pPeso Indica si el grafo sera con o sin peso.
     */
    public AdjacencyMatrix(boolean grafoDirigido, boolean pPeso) {
        esDirigido = grafoDirigido;
        conPeso = pPeso;
        matriz = new double[0][0];
        listaValores = new ArrayList<>(1);

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
     * Agrega un valor al grafo.
     *
     * @param value El nuevo valor que se va a agregar.
     */
    @Override
    public void addNode(V value) {
        if (!contains(value)) {
            double[][] nuevo = new double[matriz.length + 1][matriz.length + 1];
            for (int x = 0; x < matriz.length + 1; x++) {
                for (int y = 0; y < matriz.length + 1; y++) {
                    if (x < matriz.length && y < matriz.length) {
                        nuevo[x][y] = matriz[x][y];
                    } else {
                        nuevo[x][y] = -1;
                    }
                }
            }
            matriz = nuevo;
            listaValores.add(value);
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
        int posValue1 = obtenerPosicion(value1);
        int posValue2 = obtenerPosicion(value2);
        if (posValue1 != -1 && posValue2 != -1) {
            if (matriz[posValue1][posValue2] == -1) { //Significa que no existe arista entre ambos.
                if (conPeso) {
                    matriz[posValue1][posValue2] = weight;
                    if (!esDirigido) {
                        matriz[posValue2][posValue1] = weight;
                    }
                } else {
                    matriz[posValue1][posValue2] = 0;
                    if (!esDirigido) {
                        matriz[posValue2][posValue1] = 0;
                    }
                }
            }
        }
    }

    /**
     * Recorre la lista de valores y devuelve la posicion deonde se ubica el valor dado.
     * Esta posicion es analoga a su fila y columna en la matriz.
     *
     * @param value El valor que se solicita.
     * @return La posicion donde se ubica el valor.
     */
    private int obtenerPosicion(V value) {
        for (int x = 1; x <= listaValores.size(); x++) {
            if (listaValores.get(x).equals(value)) {
                return x - 1;
            }
        }
        return -1;
    }

    /**
     * Revisa si el grafo contiene el valor dado.
     *
     * @param value El valor que se va a buscar.
     * @return true si el valor esta en el grafo; false, en caso contrario.
     */
    @Override
    public boolean contains(V value) {
        for (int x = 1; x <= listaValores.size(); x++) {
            if (listaValores.get(x).equals(value)) {
                return true;
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
        int posValue1 = obtenerPosicion(value1);
        int posValue2 = obtenerPosicion(value2);
        if (posValue1 != -1 && posValue2 != -1) {
            if (matriz[posValue1][posValue2] != -1) {
                return true;
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
        int posValue1 = obtenerPosicion(value1);
        int posValue2 = obtenerPosicion(value2);
        if (posValue1 != -1 && posValue2 != -1) {
            if (matriz[posValue1][posValue2] != -1) {
                return matriz[posValue1][posValue2];
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
        int pos = obtenerPosicion(value);
        if (pos != -1) {
            listaValores.remove(pos + 1);
            double[][] nuevo = new double[matriz.length - 1][matriz.length - 1];
            boolean hayCorrimientoX = false;
            boolean hayCorrimientoY = false;
            for (int x = 0; x < nuevo.length; x++) {
                if (x >= pos) {
                    hayCorrimientoX = true;
                }
                for (int y = 0; y < nuevo.length; y++) {
                    if (y >= pos) {
                        hayCorrimientoY = true;
                    }
                    if (!hayCorrimientoX) { // Valores mas arriba en la matriz que el valor borrado.
                        if (!hayCorrimientoY) { // Valores mas izquierda en la matriz que el valor borrado.
                            nuevo[x][y] = matriz[x][y];
                        } else { // Valores mas derecha en la matriz que el valor borrado.
                            nuevo[x][y] = matriz[x][y+1];
                        }
                    } else { // Valores mas abajo en la matriz que el valor borrado.
                        if (!hayCorrimientoY) { // Valores mas izquierda en la matriz que el valor borrado.
                            nuevo[x][y] = matriz[x+1][y];
                        } else { // Valores mas derecha en la matriz que el valor borrado.
                            nuevo[x][y] = matriz[x+1][y+1];
                        }
                    }
                }
                hayCorrimientoY = false;
            }
            matriz = nuevo;
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
        int posValue1 = obtenerPosicion(value1);
        int posValue2 = obtenerPosicion(value2);
        if (posValue1 != -1 && posValue2 != -1) {
            if (matriz[posValue1][posValue2] != -1) {
                matriz[posValue1][posValue2] = -1;
                if (!esDirigido) {
                    matriz[posValue2][posValue1] = -1;
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
        int pos = obtenerPosicion(value);
        List<V> lista = new LinkedList<>();
        if (pos != -1) {
            for (int x = 0; x < matriz.length; x++) {
                if (matriz[pos][x] != -1) {
                    lista.add(listaValores.get(x + 1));
                }
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
        return listaValores;
    }

    /**
     * Retorna la cantidad de valores en el grafo.
     *
     * @return numero de vertices.
     */
    @Override
    public int V() {
        return listaValores.size();
    }

    /**
     * Retorna la cantidad de aristas en el grafo.
     *
     * @return numero de aristas.
     */
    @Override
    public int E() {
        int counter = 0;
        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz.length; y++) {
                if (matriz[x][y] != -1) {
                    counter++;
                }
            }
        }
        if (!esDirigido) {
            counter /= 2;
        }
        return counter;
    }

    /**
     * Devuelve un iterador para recorrer los elementos que estan en el grafo.
     * @return Un iterador de AM.
     */
    @Override
    public Iterator<V> iterator() {
        return listaValores.iterator();
    }

    /**
     * Calcula la suma de todas las aristas del grafo y las retorna.
     *
     * @return El peso del grafo.
     */
    @Override
    public double getWeight() {
        if (conPeso) {
            int counter = 0;
            for (int x = 0; x < matriz.length; x++) {
                for (int y = 0; y < matriz.length; y++) {
                    if (matriz[x][y] != -1) {
                        counter += matriz[x][y];
                    }
                }
            }
            if (!esDirigido) {
                counter /= 2;
            }
            return counter;
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
        return listaValores.size();
    }

    /**
     * Revisa si el grafo tiene o no valores, devolviendo true en caso de que no tenga.
     *
     * @return true si el esta vacio; false, en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return listaValores.size() == 0;
    }

    /**
     * Vacia el grafo, dejandolo sin ningun vertice.
     */
    @Override
    public void clear() {
        listaValores.clear();
        double[][] nuevo = new double[5][5];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                nuevo[x][y] = -1;
            }
        }
        matriz = nuevo;
    }

    /**
     * Devuelve un arreglo que representa la posicion de los valores en la estructura
     * interna usada en GetGraphStructureAsMatrix.
     *
     * @return Arreglo de valores.
     */
    @Override
    public V[] getValuesAsArray() {
        V[] array = (V[]) new Object[matriz.length];
        Iterator<V> it1 = listaValores.iterator();
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
        return matriz;
    }
}
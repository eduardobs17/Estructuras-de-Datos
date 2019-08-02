package ucr.ac.cr.ecci.ci1221.util.algorithm;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Dictionary;
//import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.Dictionary; me da error al usarlo.
import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.set.LinkedListSet;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;
import ucr.ac.cr.ecci.ci1221.util.graph.AdjacencyMatrix;
import ucr.ac.cr.ecci.ci1221.util.graph.Graph;

/**
 * Algoritmos para grafos.
 * @author Rodrigo A. Bartels
 */
public class GraphAlgorithms {

    /**
     * Calcula el arbol de expansion minima usando el algoritmo de Prim.
     *
     * @param graph El grafo sobre el cual se va a hacer el calculo.
     * @param <V> E tipo de datos de los elementos.
     * @return Un grafo que representa el arbol.
     */
    public static <V> Graph<V> getMinimumSpanningTreePrim(Graph<V> graph) {
        V[] array = graph.getValuesAsArray();
        double[][] matriz = graph.getGraphStructureAsMatrix();

        double[][] matrizCopia = new double[array.length][array.length]; //Se hace una copia del array. Sino, se modificaria en el grafo original.
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array.length; y++) {
                matrizCopia[x][y] = matriz[x][y];
            }
        }

        boolean[] revisados = new boolean[array.length]; // Arreglo de booleanos que marca los valores por donde ya se paso.
        Set<V> conjuntoRevisados = new LinkedListSet<>(); //Conjunto donde se guardan los vertices por donde ya se paso
        Graph<V> nuevo = new AdjacencyMatrix<>(graph.isDirected(), true);

        if (array.length > 0) { // Grafo no vacio

            revisados[0] = true;
            conjuntoRevisados.put(array[0]);
            nuevo.addNode(array[0]);

            double menorArista = 50000;
            V verticeOrigen = null;
            V verticeDestino = null;

            while (conjuntoRevisados.size() != array.length) { // mientras hayan vertices sin revisar

                Iterator<V> it1 = conjuntoRevisados.iterator(); //Se recorren todos los vertices guardados
                while (it1.hasNext()) {

                    V aux = it1.next();
                    int posArray = buscarPosicion(aux, array);
                    for (int x = 0; x < array.length; x++) {

                        if (matrizCopia[posArray][x] != -1) { //Si existe arista
                            //Si los 2 vertices no estan en el arbol, para evitar un ciclo
                            if (!(conjuntoRevisados.isMember(aux) && conjuntoRevisados.isMember(array[x]))) {
                                if (matrizCopia[posArray][x] < menorArista) {
                                    menorArista = matrizCopia[posArray][x];
                                    if (!graph.isDirected()) {
                                        matrizCopia[x][posArray] = -1;
                                    }
                                    verticeOrigen = aux;
                                    verticeDestino = array[x];
                                }
                            }
                        }
                    }
                }

                if (verticeOrigen != null) {
                    if (!nuevo.contains(verticeDestino)) {
                        nuevo.addNode(verticeDestino);
                        if (!conjuntoRevisados.isMember(verticeDestino)) {
                            conjuntoRevisados.put(verticeDestino);
                        }
                        revisados[buscarPosicion(verticeDestino, array)] = true;
                    }
                    nuevo.addEdge(verticeOrigen, verticeDestino, menorArista);

                    verticeOrigen = null;
                    menorArista = 50000;
                } else {
                    for (int x = 0; x < array.length; x++) {
                        if (revisados[x] == false) {
                            conjuntoRevisados.put(array[x]);
                            nuevo.addNode(array[x]);
                        }
                    }
                }
            }
        }
        return nuevo;
    }

    /**
     * Busca el valor dado y devuelve su posicion en el array.
     *
     * @param pValue El valor que se va a buscar.
     * @param pArray El array donde se va a buscar.
     * @param <V> EL tipo de dato de los elementos.
     * @return La posicion en el array.
     */
    private static <V> int buscarPosicion(V pValue, V[] pArray) {
        for (int x = 0; x < pArray.length; x++) {
            if (pArray[x].equals(pValue)) {
                return x;
            }
        }
        return -1;
    }

    /**
     * Calcula el arbol de expansion minima usando el algoritmo de Kruskal.
     *
     * @param graph El grafo sobre el cual se va a hacer el calculo.
     * @param <V> E tipo de datos de los elementos.
     * @return Un grafo que representa el arbol.
     */
    public static <V> Graph<V> getMinimumSpanningTreeKruskal(Graph<V> graph) {
        class Arista {
            private V origen;
            private V destino;
            private double peso;

            private Arista(V pOrigen, double pPeso, V pDestino) {
                origen = pOrigen;
                peso = pPeso;
                destino = pDestino;
            }
        }

        V[] array = graph.getValuesAsArray();
        double[][] matrizPesos = graph.getGraphStructureAsMatrix();
        double[][] matrizCopia = new double[array.length][array.length];
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array.length; y++) {
                matrizCopia[x][y] = matrizPesos[x][y];
            }
        }

        List<Arista> aristas = new LinkedList<>();

        // Se buscan todas las aristas del grafo y se enlistan
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array.length; y++) {
                if (matrizCopia[x][y] != -1) {
                    if (!graph.isDirected()) {
                        matrizCopia[y][x] = -1;
                    }
                    Arista ar = new Arista(array[x], matrizCopia[x][y], array[y]);
                    aristas.add(ar);
                }
            }
        }

        //Se ordenan las listas de menor a mayor, aplicando el ordenamiento de burbuja.
        if (aristas.size() > 1) {
            int i = 0;
            for (int x = 2; x < aristas.size(); x++) {
                for (int y = 1; y < aristas.size() - i; y++) {
                    Arista temp = aristas.get(y);
                    Arista temp2 = aristas.get(y+1);
                    if (temp.peso > temp2.peso) {
                        aristas.set(y, temp2);
                        aristas.set(y+1, temp);
                    }
                }
                i++;
            }
        }

        List<Graph<V>> bosque = new LinkedList<>();
        for (int x = 0; x < array.length; x++) {
            Graph<V> temp = new AdjacencyMatrix<>();
            temp.addNode(array[x]);
            bosque.add(temp);
        }

        Iterator<Arista> it1 = aristas.iterator();
        while (it1.hasNext()) {
            Arista aux = it1.next();
            int arbolOrigen = buscarArbol(aux.origen, bosque);
            int arbolDestino = buscarArbol(aux.destino, bosque);
            if (arbolDestino < arbolOrigen) {
                int temp = arbolDestino;
                arbolDestino = arbolOrigen;
                arbolOrigen = temp;
            }

            if (arbolDestino != arbolOrigen) { // Significa que estan en diferentes arboles.
                Graph<V> grafoDestino = bosque.remove(arbolDestino);
                Graph<V> grafoTemp = unirGrafos(bosque.get(arbolOrigen), grafoDestino);
                grafoTemp.addEdge(aux.origen, aux.destino, aux.peso);
                bosque.set(arbolOrigen, grafoTemp);
            }
        }
        if (bosque.size() != 1) { // Significa que hay vertices aislados.
            for (int x = 2; x <= bosque.size(); x++) {
                Graph<V> grafoTemp = bosque.remove(x);
                Graph<V> grafosUnidos = unirGrafos(bosque.get(1), grafoTemp);
                bosque.set(1, grafosUnidos);
            }
        }

        return bosque.get(1);
    }

    /**
     * Devuelve la union entre grafo1 y grafo2.
     *
     * @param grafo1 El primer grafo.
     * @param grafo2 El segundo grafo.
     * @param <V> El tipo de datos de los elementos.
     * @return La union de los grafos.
     */
    private static <V> Graph<V> unirGrafos(Graph<V> grafo1, Graph<V> grafo2) {
        V[] array = grafo2.getValuesAsArray();
        double[][] matriz = grafo2.getGraphStructureAsMatrix();
        for (int x = 0; x < array.length; x++) {
            grafo1.addNode(array[x]);
            for (int y = 0; y < array.length; y++) {
                if (matriz[x][y] != -1) {
                    if (!grafo2.isDirected()) {
                        matriz[y][x] = -1;
                    }
                    grafo1.addNode(array[y]);
                    grafo1.addEdge(array[x], array[y], grafo2.getWeight(array[x], array[y]));
                }
            }
        }
        return grafo1;
    }

    /**
     * Busca el sub-arbol al cual pertenece el valor que se recibe.
     * Este metodo se usa en  Kruskal.
     *
     * @param pValor El valor que se va a buscar
     * @param pLista La lista en la cual se va a buscar.
     * @param <V> El tipo de datos de los elementos.
     * @return El indice de la lista donde esta el sub-arbol que contiene el valor dado.
     */
    private static <V> int buscarArbol(V pValor, List<Graph<V>> pLista) {
        for (int x = 1; x <= pLista.size(); x++) {
            Graph<V> arbol = pLista.get(x);
            Iterator<V> it1 = arbol.iterator();
            while (it1.hasNext()) {
                V aux = it1.next();
                if (aux.equals(pValor)) {
                    return x;
                }
            }
        }
        return 0;
    }

    /**
     * Revisa si el grafo dado tiene ciclos o no.
     *
     * @return false si el grafo tiene ciclos; true, el caso contrario.
     */
    public static <V> boolean isGraphAcyclic(Graph<V> graph) {
        V[] arrayValores = graph.getValuesAsArray();
        if (arrayValores.length > 1) { // Un grafo con solo un vertice no puede tener ciclos.
            Set<V> conjuntoRevisados = new LinkedListSet<>();
            return profPrimeroCiclos(graph, arrayValores[0], null, conjuntoRevisados);
        }
        return false;
    }

    /**
     * Aplica Prof. primero para buscar ciclos en el grafo.
     *
     * @param pGraph El grafo en donde se va a buscar ciclos.
     * @param pInicio El vertice actual por donde va el algoritmo.
     * @param vertAnterior El vertice del cual viene el algoritmo, para evitar un enciclamiento del algoritmo.
     * @param pConjunto Un conjunto donde se guardan los vertices ya recorridos.
     * @param <V> El tipo de datos de los elementos.
     * @return false si el grafo tiene ciclos; true, el caso contrario.
     */
    private static <V> boolean profPrimeroCiclos(Graph<V> pGraph, V pInicio, V vertAnterior, Set<V> pConjunto) {
        pConjunto.put(pInicio);
        List<V> adyacencias = pGraph.getAdjacentNodes(pInicio);
        for (int x = 1; x <= adyacencias.size(); x++) {
            V adyacencia = adyacencias.get(x);
            if (!adyacencia.equals(vertAnterior)) {
                if (!pConjunto.isMember(adyacencia)) {
                    return profPrimeroCiclos(pGraph, adyacencia, pInicio, pConjunto);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Imprime el camino mas corto del valor dado hacia los demas, usando el resultado de Dijkstra.
     *
     * @param value El valor en donde el recorrido empieza.
     * @param paths El resultado del algoritmo de Dijkstra.
     * @param <V> El tipo de los elementos elementos.
     */
    public static <V> void printShortestPath(V value, Dictionary<V, DijkstraResult<V>> paths) {
        Enumeration<DijkstraResult<V>> elementos = paths.elements();
        while (elementos.hasMoreElements()) {
            DijkstraResult<V> aux = elementos.nextElement();
            if (!aux.getVertex().equals(value)) {
                String hilera = "";
                DijkstraResult<V> temp = aux;
                while (!temp.getVertex().equals(value)) {
                    hilera = "-" + temp.getVertex() + hilera;
                    temp = paths.get(temp.getPrecursor());
                }
                hilera = value + hilera;
                if (aux.getDistance() == Integer.MAX_VALUE - 1) {
                    System.out.println("El camino mas corto entre " + value + " y " + aux.getVertex() + " es " + hilera + " con una distancia de infinito (no hay camino)");
                } else {
                    System.out.println("El camino mas corto entre " + value + " y " + aux.getVertex() + " es " + hilera + " con una distancia de: " + aux.getDistance());
                }
            } else {
                System.out.println("El camino mas corto entre " + value + " y " + value + " es " + value + " con una distancia de: " + aux.getDistance());
            }
        }
    }

    /**
     * Usa el algoritmo de Dijkstra para calcular la distancia minima de un valor dado a todos los demas.
     *
     * @param graph El grafo.
     * @param value El vertice inicial.
     * @param <V>   El tipo de datos de los elementos.
     * @return Un diccionario que contiene los predecesorres de cada nodo, asi como su distancia.
     */
    public static <V> Dictionary<V, DijkstraResult<V>> getShortestPathDijkstra(Graph<V> graph, V value) {
        V[] vertices = graph.getValuesAsArray();
        double[][] matriz = graph.getGraphStructureAsMatrix();

        double[] distancias = new double[vertices.length];
        V[] camino = (V[]) new Object[vertices.length];
        int pos = buscarPosicion(value, vertices);

        for (int x = 0; x < vertices.length; x++) { // Se llenan los arreglos con la informacion de sus adyacentes.
            if (pos == x) {
                distancias[x] = 0;
                camino[x] = null;
            } else {
                if (matriz[pos][x] == -1) {
                    distancias[x] = Integer.MAX_VALUE - 1;
                    camino[x] = vertices[pos];
                } else {
                    distancias[x] = matriz[pos][x];
                    camino[x] = vertices[pos];
                }
            }
        }

        Set<V> revisados = new LinkedListSet<>();
        revisados.put(value);
        while (revisados.size() < vertices.length) {
            double menor = Integer.MAX_VALUE;
            V actual = null;
            for (int x = 0; x < vertices.length; x++) {
                if (distancias[x] < menor) {
                    if (!revisados.isMember(vertices[x])) {
                        menor = distancias[x];
                        actual = vertices[x];
                    }
                }
            }

            pos = buscarPosicion(actual, vertices);
            for (int x = 0; x < vertices.length; x++) { // Se recorre la matriz para buscar adyacencias.
                if (matriz[pos][x] != -1) { // Si existe arist
                    if (menor + matriz[pos][x] < distancias[x]) {
                        distancias[x] = menor + matriz[pos][x];
                        camino[x] = actual;
                    }
                }
            }
            revisados.put(actual);
        }
        Dictionary<V, DijkstraResult<V>> diccionario = new Hashtable<>();
        for (int x = 0; x < vertices.length; x++) {
            DijkstraResult<V> aux = new DijkstraResult<>(vertices[x], camino[x], distancias[x]);
            diccionario.put(vertices[x], aux);
        }
        return diccionario;
    }

    /**
     * Usa el algoritmo de Floyd-Warshall's algorithm para imprimir el camino mas corto entre todos los
     * vertices del grafo.
     *
     * @param graph El grafo.
     * @param <V> El tipo de datos de los elementos.
     */
    public static <V> void printAllShortestPaths(Graph<V> graph) {
        double[][] matrizPesos = graph.getGraphStructureAsMatrix();
        double[][] pesos = new double[matrizPesos.length][matrizPesos.length];
        V[] vertices = graph.getValuesAsArray();
        V[][] caminos = (V[][]) new Object[matrizPesos.length][matrizPesos.length];
        for (int x = 0; x < matrizPesos.length; x++) {
            for (int y = 0; y < matrizPesos.length; y++) {
                if (x == y) {
                    pesos[x][y] = -1;
                } else {
                    if (matrizPesos[x][y] == -1) {
                        pesos[x][y] = Integer.MAX_VALUE; //Si no existe arista se pone infinito
                    } else {
                        pesos[x][y] = matrizPesos[x][y];
                    }
                }
                caminos[x][y] = vertices[y];
            }
        }
        for (int x = 0; x < vertices.length; x++) { // Para cada uno de los vertices del grafo
            for (int y = 0; y < vertices.length; y++) { // Recorre la fila correspondiente al vertice
                for (int z = 0; z < vertices.length; z++) { //Recorre la columna correspondiente al vertice
                    if (y != x && z != x) {
                        double tam2 = pesos[y][x];
                        double tam1 = pesos[x][z];
                        if (pesos[x][z] != -1 && pesos[y][x] != -1) {
                            double suma = pesos[x][z] + pesos[y][x];
                            if (suma < pesos[y][z]) {
                                pesos[y][z] = suma;
                                caminos[y][z] = vertices[x];
                            }
                        }
                    }
                }
            }
        }

        //Cuando se termina el algoritmo, se imprimen los caminos
        for (int x = 0; x < vertices.length; x++) {
            for (int y = 0; y < vertices.length; y++) {
                boolean seguir = true;
                int it1 = y;
                String hilera = "";
                while (seguir) {
                    if (it1 != y) {
                        hilera = vertices[it1] + "-" + hilera;
                    } else {
                        hilera += vertices[it1];
                    }
                    if (caminos[x][it1] != vertices[it1]) {
                        int m = 0;
                        boolean continuar = true;
                        while (continuar) {
                            if (vertices[m].equals(caminos[x][it1])) {
                                it1 = m;
                                continuar = false;
                            } else {
                                m++;
                            }
                        }
                    } else {
                        if (x == y) {
                            System.out.println("El camino entre " + vertices[x] + " y " + vertices[y] + " es: " + hilera);
                        } else {
                            hilera = vertices[x] + "-" + hilera;
                            if (pesos[x][y] == Integer.MAX_VALUE) {
                                System.out.println("El camino mas corto entre " + vertices[x] + " y " + vertices[y] + " es: infinito (no hay camino)");
                            } else {
                                System.out.println("El camino mas corto entre " + vertices[x] + " y " + vertices[y] + " es: " + hilera + " con distancia de: " + (pesos[x][y]));
                            }
                        }
                        seguir = false;
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * Aplica el algoritmo para obtener los componentes conectados de un grafo.
     *
     * @param graph El grafo en el cual se va a hacer el calculo.
     * @param <V> El tipo de datos de los elementos.
     * @return Una lista de grafos, donde cada grafo representa cada componente.
     */
    public static <V> List<Graph<V>> getConnectedComponents(Graph<V> graph) {
        List<Graph<V>> listaComponentes = new LinkedList<>();
        V[] listaVertices = graph.getValuesAsArray();
        Set<V> revisados = new LinkedListSet<>();
        while (revisados.size() != listaVertices.length) {
            int valor = 0;
            for (int x = 0; x < listaVertices.length; x++) {
                if (!revisados.isMember(listaVertices[x])) {
                    valor = x;
                    x = listaVertices.length * 2;
                }
            }
            Graph<V> nuevo = new AdjacencyMatrix<>(graph.isDirected(), true);
            recorridoProf(graph, listaVertices[valor], revisados, nuevo);
            listaComponentes.add(nuevo);
        }
        return listaComponentes;
    }

    /**
     * Aplica el algoritmo de Profundidad primero para obtener los componentes conectados de un grafo.
     *
     * @param graph El grafo original.
     * @param pInicio El vertice actual.
     * @param pRevisado Un conjunto que revisa si los vertices ya estan o no marcados.
     * @param grafoNuevo El grafo nuevo, que representa el componente conectado del vertice actual.
     * @param <V> El tipo de dato de los elementos.
     */
    private static <V> void recorridoProf(Graph<V> graph, V pInicio, Set<V> pRevisado, Graph<V> grafoNuevo) {
        grafoNuevo.addNode(pInicio);
        pRevisado.put(pInicio);
        List<V> adyacencias = graph.getAdjacentNodes(pInicio);
        for (int x = 1; x <= adyacencias.size(); x++) {
            V adyacencia = adyacencias.get(x);
            if (!pRevisado.isMember(adyacencia)) {
                recorridoProf(graph, adyacencia, pRevisado, grafoNuevo);
            }
            grafoNuevo.addEdge(pInicio, adyacencia, graph.getWeight(pInicio, adyacencia));
        }
    }
}
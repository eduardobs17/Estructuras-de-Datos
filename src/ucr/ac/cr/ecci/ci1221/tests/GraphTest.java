package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.algorithm.DijkstraResult;
import ucr.ac.cr.ecci.ci1221.util.algorithm.GraphAlgorithms;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.graph.AdjacencyMatrix;
import ucr.ac.cr.ecci.ci1221.util.graph.Graph;
import java.util.Dictionary;
import java.util.Iterator;

/**
 * Clase de prueba para los algoritmos de grafo.
 * @author Eduardo Biazzetti.
 */
public class GraphTest {

    /**
     * Programa de prueba para GraphAlgorithm.
     *
     * @param args los parametros que recibe el main.
     */
    public static void main(String[] args) {
        Graph<Integer> grafo = new AdjacencyMatrix<>(false, true);

        System.out.println("Se crea un grafo no dirigido, con pesos y con 9 vertices:");
        System.out.println("8-9 = 3; 8-7 = 5; 9-7 = 1; 3-2 = 8; 2-7 = 3; 2-1 = 2; 7-1 = 6; 1-6 = 9; 6-4 = 10 y 5 aislado" + '\n');

        grafo.addNode(8);
        grafo.addNodes(9,7);
        grafo.addNode(3);
        grafo.addNode(2);
        grafo.addNode(1);
        grafo.addNode(5);
        grafo.addNode(6);
        grafo.addNode(4);
        grafo.addNode(1);

        grafo.addEdge(7,9);
        grafo.addEdge(8,9,3);
        grafo.addEdge(8,7,5);
        grafo.addEdge(3,2,8);
        grafo.addEdge(7,2,3);
        grafo.addEdge(7,1,6);
        grafo.addEdge(2,3,8);
        grafo.addEdge(2,1,2);
        grafo.addEdge(1,6,9);
        grafo.addEdge(6,4,10);

        Graph<Integer> grafoPrim = GraphAlgorithms.getMinimumSpanningTreePrim(grafo);
        System.out.println("Se aplica algoritmo de Prim, el peso del arbol resultante debe ser 36: " + grafoPrim.getWeight());

        Graph<Integer> grafoKruskal = GraphAlgorithms.getMinimumSpanningTreeKruskal(grafo);
        System.out.println("Se aplica algoritmo de Kruskal, el peso del arbol resultante debe ser 36: " + grafoKruskal.getWeight() + '\n');

        System.out.println("Se revisa si el grafo es aciclico.");
        System.out.println("Debe ser false (si hay ciclos): " + GraphAlgorithms.isGraphAcyclic(grafo) + '\n');

        System.out.println("Se aplica el algoritmo de Dijkstra, desde el vertice 8.");
        Dictionary<Integer, DijkstraResult<Integer>> diccionario = GraphAlgorithms.getShortestPathDijkstra(grafo, 8);
        System.out.println("Se aplica el algoritmo de para imprimir el resultado de Dijkstra:" + '\n');
        GraphAlgorithms.printShortestPath(8, diccionario);

        System.out.println('\n' + "Ahora se aplica el algoritmo de Floyd y se imprimen todos los caminos entre todos los vertices:");
        GraphAlgorithms.printAllShortestPaths(grafo);

        List<Graph<Integer>> componentesConectados = GraphAlgorithms.getConnectedComponents(grafo);
        System.out.println("Se aplica algoritmo de Componentes Conectados");
        System.out.println("Tienen que haber 2 componentes: " + componentesConectados.size());
        String hilera = "";
        Iterator<Integer> it1 = componentesConectados.get(1).iterator();
        while (it1.hasNext()) {
            hilera += it1.next();
            hilera += "-";
        }
        System.out.println("El primero debe ser 8-9-7-2-3-1-6-4: " + hilera);

        hilera = "";
        it1 = componentesConectados.get(2).iterator();
        while (it1.hasNext()) {
            hilera += it1.next();
            hilera += "-";
        }
        System.out.println("El segundo debe ser solo 5: " + hilera + '\n');

        System.out.println("---------------------------------------");
        Graph<Character> grafo2 = new AdjacencyMatrix<>(true, true);
        grafo2.addNode('A');
        grafo2.addNode('B');
        grafo2.addNode('C');
        grafo2.addNode('D');
        grafo2.addEdge('A', 'B', 3);
        grafo2.addEdge('A', 'C', 4);
        grafo2.addEdge('B', 'D', 5);
        grafo2.addEdge('C', 'D', 3);
        grafo2.addEdge('D', 'A', 8);

        System.out.println(GraphAlgorithms.getMinimumSpanningTreeKruskal(grafo2).getWeight());
        System.out.println(GraphAlgorithms.getMinimumSpanningTreePrim(grafo2).getWeight());
        System.out.println(GraphAlgorithms.getConnectedComponents(grafo2).size());
        System.out.println(GraphAlgorithms.isGraphAcyclic(grafo2));
        GraphAlgorithms.printAllShortestPaths(grafo2);
    }
}
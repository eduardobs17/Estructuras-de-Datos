package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.graph.Graph;
import ucr.ac.cr.ecci.ci1221.util.graph.AdjacencyList;
import java.util.Iterator;

/**
 * Clase de prueba para AdjacencyList.
 * @author Eduardo Biazzetti.
 */
public class AdjacencyListTest {

    /**
     * Programa de prueba para AdjacencyList.
     *
     * @param args los parametros que recibe el main.
     */
    public static void main(String[] args) {
        Graph<Integer> grafo = new AdjacencyList<>();
        System.out.println("Prueba grafo no dirigido con pesos");

        System.out.println("El grafo debe tener 0 elementos: " + grafo.size() + " y estar vacio: " + grafo.isEmpty());
        System.out.println("El grafo debe ser no dirigido (false): " + grafo.isDirected());

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

        System.out.println("El grafo debe tener 9 elementos: " + grafo.size() + " y no estar vacio: " + grafo.isEmpty() + '\n');

        System.out.println("Se busca 9, debe ser true: " + grafo.contains(9));
        System.out.println("Se busca 8, debe ser true: " + grafo.contains(8));
        System.out.println("Se busca 4, debe ser true: " + grafo.contains(4));
        System.out.println("Se busca 11, debe ser false: " + grafo.contains(11));
        System.out.println("Se busca 0, debe ser false: " + grafo.contains(0));
        System.out.println("Se busca 20, debe ser false: " + grafo.contains(20) + '\n');

        System.out.println("Se busca adyacencia 9-7, debe ser true: " + grafo.areLinked(9,7));
        System.out.println("Se busca adyacencia 4-6, debe ser true: " + grafo.areLinked(4,6));
        System.out.println("Se busca adyacencia 3-2, debe ser true: " + grafo.areLinked(3,2));
        System.out.println("Se busca adyacencia 5-1, debe ser false: " + grafo.areLinked(5,1));
        System.out.println("Se busca adyacencia 10-7, debe ser false: " + grafo.areLinked(10,7));
        System.out.println("Se busca adyacencia 11-12, debe ser false: " + grafo.areLinked(11,12) + '\n');

        System.out.println("Adyacencia 8-7, debe pesar 5: " + grafo.getWeight(8,7));
        System.out.println("Adyacencia 3-2, debe pesar 8: " + grafo.getWeight(3,2));
        System.out.println("Adyacencia 1-6, debe pesar 9: " + grafo.getWeight(1,6));
        System.out.println("Adyacencia 9-5, debe pesar 0: " + grafo.getWeight(9,5));
        System.out.println("Adyacencia 8-11, debe pesar 0: " + grafo.getWeight(8,11));
        System.out.println("Adyacencia 15-31, debe pesar 0: " + grafo.getWeight(15,31) + '\n');

        grafo.removeValue(3);
        grafo.removeValue(15);
        System.out.println("Se elimina 3");
        System.out.println("El grafo debe tener 8 elementos: " + grafo.size() + " y no estar vacio: " + grafo.isEmpty() + '\n');

        System.out.println("Se busca 3, debe ser false: " + grafo.contains(3));
        System.out.println("Se busca adyacencia 3-2, debe ser false: " + grafo.areLinked(3,2));
        System.out.println("Adyacencia 3-2, debe pesar 0: " + grafo.getWeight(3,2) + '\n');

        grafo.removeEdge(9,7);
        grafo.removeEdge(5,1);
        grafo.removeEdge(1,5);
        grafo.removeEdge(0,0);
        System.out.println("Se elimina adyacencia 9-7");
        System.out.println("Se busca adyacencia 9-7, debe ser false: " + grafo.areLinked(9,7));
        System.out.println("Se busca adyacencia 7-9, debe ser false: " + grafo.areLinked(7,9) + '\n');

        List<Integer> listaAdyacentes6 = grafo.getAdjacentNodes(6);
        List<Integer> listaAdyacentes5 = grafo.getAdjacentNodes(5);

        System.out.println("Adyacentes de 5 deben ser 0: " + listaAdyacentes5.size());
        Iterator<Integer> it1 = listaAdyacentes6.iterator();
        String hilera = "";
        while (it1.hasNext()) {
            hilera += it1.next();
            hilera += ",";
        }
        System.out.println("Adyacentes de 6 deben ser 1,4: " + hilera + '\n');

        List<Integer> listaVertices = grafo.getValues();
        hilera = "";
        it1 = listaVertices.iterator();
        while (it1.hasNext()) {
            hilera += it1.next();
            hilera += ",";
        }
        System.out.println("Lista de vertices; debe ser 8,9,7,2,1,5,6,4: " + hilera + '\n');

        System.out.println("El grafo debe tener 8 vertices: " + grafo.V());
        System.out.println("El grafo debe tener 7 aristas: " + grafo.E() + '\n');

        hilera = "";
        it1 = grafo.iterator();
        while (it1.hasNext()) {
            hilera += it1.next();
            hilera += ",";
        }
        System.out.println("Lista de vertices; debe ser 8,9,7,2,1,5,6,4: " + hilera + '\n');

        System.out.println("El peso del grafo debe ser 38: " + grafo.getWeight() + '\n');

        grafo.clear();
        System.out.println("Se limpia el grafo, tamaño debe ser 0: " + grafo.size());
        System.out.println("Se busca 1, debe ser false: " + grafo.contains(1));
        System.out.println("-----------------------------------------" + '\n' + '\n');

        Graph<Integer> grafoDCP = new AdjacencyList<>(true, true); //Grafo dirigido con pesos
        grafoDCP.addNode(9);
        grafoDCP.addNode(1);
        grafoDCP.addNode(3);
        grafoDCP.addNode(5);

        grafoDCP.addEdge(9,1,2);
        grafoDCP.addEdge(9,3,3);
        grafoDCP.addEdge(1,3,1);
        grafoDCP.addEdge(1,5,5);
        grafoDCP.addEdge(3,5,4);

        System.out.println("Prueba grafo dirigido con pesos");
        System.out.println("Debe ser dirigido: " + grafoDCP.isDirected());
        System.out.println("Se busca adyacencia 9-1, debe ser true: " + grafoDCP.areLinked(9,1));
        System.out.println("Se busca adyacencia 1-9, debe ser false: " + grafoDCP.areLinked(1,9));
        System.out.println("Se busca adyacencia 3-5, debe ser true: " + grafoDCP.areLinked(3,5));
        System.out.println("Se busca adyacencia 5-3, debe ser false: " + grafoDCP.areLinked(5,3) + '\n');

        System.out.println("Adyacencia 9-1, debe pesar 2: " + grafoDCP.getWeight(9,1));
        System.out.println("Adyacencia 1-9, debe pesar 0: " + grafoDCP.getWeight(1,9) + '\n');

        System.out.println("El grafo debe tener 4 vertices: " + grafoDCP.V());
        System.out.println("El grafo debe tener 5 aristas: " + grafoDCP.E() + '\n');

        System.out.println("El peso del grafo debe ser 15: " + grafoDCP.getWeight());
        System.out.println("-----------------------------------------" + '\n' + '\n');

        Graph<Integer> grafoDSP = new AdjacencyList<>(true, false); //Grafo dirigido sin pesos
        grafoDSP.addNode(9);
        grafoDSP.addNode(1);
        grafoDSP.addNode(3);
        grafoDSP.addNode(5);

        grafoDSP.addEdge(9,1,2);
        grafoDSP.addEdge(9,3,3);
        grafoDSP.addEdge(1,3,1);
        grafoDSP.addEdge(1,5,5);
        grafoDSP.addEdge(3,5,4);

        System.out.println("Prueba grafo dirigido sin pesos");
        System.out.println("Debe ser dirigido: " + grafoDSP.isDirected());
        System.out.println("Se busca adyacencia 9-1, debe ser true: " + grafoDSP.areLinked(9,1));
        System.out.println("Se busca adyacencia 1-9, debe ser false: " + grafoDSP.areLinked(1,9));
        System.out.println("Se busca adyacencia 3-5, debe ser true: " + grafoDSP.areLinked(3,5));
        System.out.println("Se busca adyacencia 5-3, debe ser false: " + grafoDSP.areLinked(5,3) + '\n');

        System.out.println("Adyacencia 9-1, debe pesar 0: " + grafoDSP.getWeight(9,1));
        System.out.println("Adyacencia 1-9, debe pesar 0: " + grafoDSP.getWeight(1,9) + '\n');

        System.out.println("El grafo debe tener 4 vertices: " + grafoDSP.V());
        System.out.println("El grafo debe tener 5 aristas: " + grafoDSP.E() + '\n');

        System.out.println("El peso del grafo debe ser 0: " + grafoDSP.getWeight());
        System.out.println("-----------------------------------------" + '\n' + '\n');

        Graph<Integer> grafoNDSP = new AdjacencyList<>(false, false); //Grafo no dirigido sin pesos
        grafoNDSP.addNode(9);
        grafoNDSP.addNode(1);
        grafoNDSP.addNode(3);
        grafoNDSP.addNode(5);

        grafoNDSP.addEdge(9,1,2);
        grafoNDSP.addEdge(9,3,3);
        grafoNDSP.addEdge(1,3,1);
        grafoNDSP.addEdge(1,5,5);
        grafoNDSP.addEdge(3,5,4);

        System.out.println("Prueba grafo no dirigido sin pesos");
        System.out.println("Debe ser no dirigido: " + grafoNDSP.isDirected());
        System.out.println("Se busca adyacencia 9-1, debe ser true: " + grafoNDSP.areLinked(9,1));
        System.out.println("Se busca adyacencia 1-9, debe ser true: " + grafoNDSP.areLinked(1,9));
        System.out.println("Se busca adyacencia 3-5, debe ser true: " + grafoNDSP.areLinked(3,5));
        System.out.println("Se busca adyacencia 5-3, debe ser true: " + grafoNDSP.areLinked(5,3) + '\n');

        System.out.println("Adyacencia 9-1, debe pesar 0: " + grafoNDSP.getWeight(9,1));
        System.out.println("Adyacencia 1-9, debe pesar 0: " + grafoNDSP.getWeight(1,9) + '\n');

        System.out.println("El grafo debe tener 4 vertices: " + grafoNDSP.V());
        System.out.println("El grafo debe tener 5 aristas: " + grafoNDSP.E() + '\n');

        System.out.println("El peso del grafo debe ser 0: " + grafoNDSP.getWeight());
    }
}
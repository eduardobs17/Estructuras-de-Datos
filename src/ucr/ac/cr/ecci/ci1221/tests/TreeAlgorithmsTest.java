package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Node;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.PointerTree;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.PointerTreeNode;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Tree;
import ucr.ac.cr.ecci.ci1221.util.algorithm.TreeAlgorithms;
import java.util.Iterator;

/**
 * Clase de prueba para los alg. de arboles.
 * @author Eduardo Biazzetti.
 */
public class TreeAlgorithmsTest {

    /**
     * Programa de prueba para los alg. de arboles.
     * @param args parametros que recibe el main (si los hubieran).
     */
    public static void main(String[] args) {
        System.out.println("Se crea un arbol con raiz 1.");
        System.out.println("Al nodo raiz 1 se le agregan 4 hijos: 6, 3, 2, 7.");
        System.out.println("Al nodo raiz 3 se le agrega 1 hijo: 4.");
        System.out.println("Al nodo raiz 2 se le agregan 2 hijos: 8, 5.");
        System.out.println("Al nodo raiz 8 se le agrega 1 hijo: 9." + '\n');
        Tree<Integer> arbol1 = new PointerTree<>();
        arbol1.getRoot().setLabel(1);

        Node<Integer> nodo1 = new PointerTreeNode<>(2);
        Node<Integer> nodo2 = new PointerTreeNode<>(3);
        Node<Integer> nodo3 = new PointerTreeNode<>(4);
        Node<Integer> nodo4 = new PointerTreeNode<>(5);
        Node<Integer> nodo5 = new PointerTreeNode<>(6);
        Node<Integer> nodo6 = new PointerTreeNode<>(7);
        Node<Integer> nodo7 = new PointerTreeNode<>(8);
        Node<Integer> nodo8 = new PointerTreeNode<>(9);

        arbol1.getRoot().addChild(nodo1);
        arbol1.getRoot().addChildAt(1, nodo2);

        arbol1.getRoot().getChildAt(1).addChild(nodo3);
        arbol1.getRoot().getChildAt(2).addChild(nodo4);
        arbol1.getRoot().getChildAt(2).addChildAt(1, nodo7);

        arbol1.getRoot().addChildAt(1, nodo5);
        arbol1.getRoot().addChild(nodo6);

        arbol1.getRoot().getChildAt(3).getChildAt(1).addChild(nodo8);

        List<Node<Integer>> listaRecorridos = TreeAlgorithms.getLevelTraversal(arbol1);
        System.out.println("Se aplica recorrido por niveles. Debe salir: 1, 6, 3, 2, 7, 4, 8, 5, 9: ");
        String hilera = "";
        Iterator<Node<Integer>> it1 = listaRecorridos.iterator();
        while (it1.hasNext()) {
            hilera += it1.next().getLabel();
            hilera += ", ";
        }
        System.out.println(hilera + '\n');
        listaRecorridos.clear();

        listaRecorridos = TreeAlgorithms.getInDepthTraversal(arbol1);
        System.out.println("Se aplica recorrido por profundidad. Debe salir: 1, 6, 3, 4, 2, 8, 9, 5, 7: ");
        hilera = "";
        it1 = listaRecorridos.iterator();
        while (it1.hasNext()) {
            hilera += it1.next().getLabel();
            hilera += ", ";
        }
        System.out.println(hilera + '\n');
        listaRecorridos.clear();

        System.out.println("Se busca el camino mas largo para llegar a una hoja. Debe salir: 1, 2, 8, 9: ");
        listaRecorridos = TreeAlgorithms.getLongestPathFromRootToAnyLeaf(arbol1);
        hilera = "";
        it1 = listaRecorridos.iterator();
        while (it1.hasNext()) {
            hilera += it1.next().getLabel();
            hilera += ", ";
        }
        System.out.println(hilera + '\n');
        listaRecorridos.clear();

        System.out.println("La altura debe ser 3 (4 - 1): " + TreeAlgorithms.getHeight(arbol1) + '\n');

        List<List<Node<Integer>>> listaListas = TreeAlgorithms.getPathsFromRootToAnyLeaf(arbol1);
        System.out.println("Se buscan todos los caminos hacia las hojas. Deben ser 5 en total");
        System.out.println("El primero debe ser 1, 6.");
        System.out.println("El segundo debe ser 1, 7.");
        System.out.println("El segundo debe ser 1, 3, 4.");
        System.out.println("El segundo debe ser 1, 2, 5.");
        System.out.println("El segundo debe ser 1, 2, 8, 9.");

        Iterator<List<Node<Integer>>> it2 = listaListas.iterator();
        while (it2.hasNext()) {
            Iterator<Node<Integer>> it3 = it2.next().iterator();
            hilera = "";
            while (it3.hasNext()) {
                hilera += it3.next().getLabel();
                hilera += ", ";
            }
            System.out.println(hilera);
        }
    }
}
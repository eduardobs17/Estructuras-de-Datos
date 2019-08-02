package ucr.ac.cr.ecci.ci1221.util.algorithm;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.queue.Queue;
import ucr.ac.cr.ecci.ci1221.util.collections.queue.LinkedListQueue;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Node;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Tree;

import java.util.Iterator;

/**
 * Utilitary class that provides algorithms for trees.
 *
 * @author Eduardo Biazzetti.
 */
public class TreeAlgorithms {

    /**
     * Returns a list of the nodes of the tree obtained by traversing the tree by level.
     *
     * @param tree the tree.
     *
     * @return a list of the nodes of the tree in level order.
     */
    public static <T> List<Node<T>> getLevelTraversal(Tree<T> tree){
        Queue<Node<T>> cola = new LinkedListQueue<>();
        List<Node<T>> lista = new LinkedList<>();
        cola.enqueue(tree.getRoot());
        while (!cola.isEmpty()) {
            Node aux = cola.dequeue();
            lista.add(aux);
            List<Node> listaHijos = aux.getChildren();
            Iterator<Node> it1 = listaHijos.iterator();

            while (it1.hasNext()) {
                Node temp = it1.next();
                cola.enqueue(temp);
            }
        }
        return lista;
    }

    /**
     * Returns a lists of nodes ordered by doing a in depth traversal order of the given tree.
     *
     * @param tree the tree.
     *
     * @return a list of the nodes of the tree in traversal order.
     */
    public static <T> List<Node<T>> getInDepthTraversal(Tree<T> tree){
        return recorridoPreOrden(tree.getRoot());
    }

    /**
     * Aplica recorrido en Preorden en el nodo que se recibe y sus hijos.
     * @param pNode El nodo desde donde va a empezar el recorrido.
     * @param <T> El tipo de los elementos de la lista.
     * @return Una lista con los nodos ordenados por preOrden.
     */
    private static <T> List<Node<T>> recorridoPreOrden(Node<T> pNode) {
        List<Node<T>> lista = new LinkedList<>();
        lista.add(pNode);
        List<Node<T>> listaHijos = pNode.getChildren();
        List<Node<T>> listaTemporal;
        Iterator<Node<T>> it1 = listaHijos.iterator();
        while (it1.hasNext()) {
            Node temp = it1.next();
            listaTemporal = recorridoPreOrden(temp);
            Iterator<Node<T>> it2 = listaTemporal.iterator();
            while (it2.hasNext()) {
                Node aux = it2.next();
                lista.add(aux);
            }
        }
        return lista;
    }

    /**
     * Returns a list of nodes of the longest path from the root to the leaf.
     *
     * @param tree the tree.
     *
     * @param <T> the generic type.
     *
     * @return a list of nodes.
     */
    public static <T> List<Node<T>> getLongestPathFromRootToAnyLeaf(Tree<T> tree){
        Node<T> hojaMasBaja = null;
        int nivelMasBajo = 0;

        Queue<Node<T>> colaNodos = new LinkedListQueue<>();
        Queue<Integer> colaNiveles = new LinkedListQueue<>();

        colaNodos.enqueue(tree.getRoot());
        colaNiveles.enqueue(1);
        while (!colaNodos.isEmpty() && !colaNiveles.isEmpty()) {
            Node aux = colaNodos.dequeue();
            int nivelActual = colaNiveles.dequeue();

            List<Node> listaHijos = aux.getChildren();
            if (listaHijos.size() == 0) {
                if (nivelMasBajo < nivelActual) {
                    nivelMasBajo = nivelActual;
                    hojaMasBaja = aux;
                }
            } else {
                Iterator<Node> it2 = listaHijos.iterator();
                while (it2.hasNext()) {
                    colaNodos.enqueue(it2.next());
                    colaNiveles.enqueue(nivelActual + 1);
                }
            }
        }

        List<Node<T>> caminoHoja = new LinkedList<>();
        while (hojaMasBaja != null) {
            caminoHoja.add(1, hojaMasBaja);
            hojaMasBaja = hojaMasBaja.getParent();
        }
        return caminoHoja;
    }

    /**
     * Returns the heigh of the tree, meaning the length of the longest path from the root
     * to a leaf minus 1.
     *
     * @param tree the tree.
     * @param <T> the generic type.
     *
     * @return tree height.
     */
    public static <T> int getHeight(Tree<T> tree){
        LinkedListQueue<Node<T>> colaNodos = new LinkedListQueue<>();
        LinkedListQueue<Integer> colaNiveles = new LinkedListQueue<>();
        int alturaMayor = 0;
        colaNodos.enqueue(tree.getRoot());
        colaNiveles.enqueue(0);
        while (!colaNodos.isEmpty() && !colaNiveles.isEmpty()) {
            Node nodoActual = colaNodos.dequeue();
            int nivelActual = colaNiveles.dequeue();

            List<Node<T>> listaHijos = nodoActual.getChildren();
            if (listaHijos.size() != 0) {
                Iterator<Node<T>> it1 = listaHijos.iterator();
                while (it1.hasNext()) {
                    Node aux = it1.next();
                    colaNodos.enqueue(aux);
                    colaNiveles.enqueue(nivelActual + 1);
                }
            } else {
                if (alturaMayor < nivelActual) {
                    alturaMayor = nivelActual;
                }
            }
        }
        return alturaMayor;
    }

    /**
     * A list of list of nodes representing all the possible paths from the root to each node of the tree.
     *
     * @return list of list of nodes.
     */
    public static <T> List<List<Node<T>>> getPathsFromRootToAnyLeaf(Tree<T> tree){
        List<List<Node<T>>> listaListas = new LinkedList<>();
        Queue<Node<T>> cola = new LinkedListQueue<>();
        cola.enqueue(tree.getRoot());
        while (!cola.isEmpty()) {
            Node aux = cola.dequeue();
            List<Node> listaHijos = aux.getChildren();
            if (listaHijos.size() == 0) {
                List<Node<T>> listaAuxiliar = new LinkedList<>();
                Node it1 = aux;
                while (it1 != null) {
                    listaAuxiliar.add(1, it1);
                    it1 = it1.getParent();
                }
                listaListas.add(listaAuxiliar);
            } else {
                Iterator<Node> it2 = listaHijos.iterator();
                while (it2.hasNext()) {
                    cola.enqueue(it2.next());
                }
            }
        }
        return listaListas;
    }
}
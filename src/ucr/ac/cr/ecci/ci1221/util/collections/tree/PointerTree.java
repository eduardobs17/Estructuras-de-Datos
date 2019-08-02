package ucr.ac.cr.ecci.ci1221.util.collections.tree;

/**
 * Clase que representa el PointerTree.
 *
 * @author Eduardo Biazzetti.
 */
public class PointerTree<T> implements Tree<T> {

    /** Representa la raiz del arbol. */
    private Node<T> raiz = new PointerTreeNode<>();

    /**
     * Regresa el nodo raiz del arbol.
     * @return raiz del arbol.
     */
    @Override
    public Node<T> getRoot() {
        return raiz;
    }

    /**
     * Verifica si el arbol esta o no vacio.
     * @return true si el arbol esta vacio; falso en el caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return raiz == null;
    }
}
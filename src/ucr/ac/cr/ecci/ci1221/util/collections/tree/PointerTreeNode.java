package ucr.ac.cr.ecci.ci1221.util.collections.tree;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * Clase que representa al nodo del Pointer Tree.
 * @author Eduardo Biazzetti.
 */
public class PointerTreeNode<T> implements Node<T> {

    /** Etiqueta del nodo. */
    private T etiqueta = null;
    /** Puntero hacia el padre del nodo. */
    private Node<T> padre = null;
    /** Lista de hijos del nodo. */
    private List<Node<T>> listaHijos = null;

    /**
     * Constructor de PointerTree.
     */
    public PointerTreeNode() {}

    /**
     * Constructor de PointerTree.
     * @param data El valor que va a poseer la etiqueta del nuevo nodo.
     */
    public PointerTreeNode(T data) {
        etiqueta = data;
    }

    /**
     * Modifica la etiqueta del nodo.
     * @param data El nuevo valor de la etiqueta.
     */
    @Override
    public void setLabel(T data) { etiqueta = data; }

    /**
     * Retorna la etiqueta del nodo.
     * @return Etiqueta.
     */
    @Override
    public T getLabel() {
        return etiqueta;
    }

    /**
     * Retorna el padre del nodo.
     * @return Padre del nodo.
     */
    @Override
    public Node<T> getParent() { return padre; }

    /**
     * Agrega un padre al nodo.
     * @param pNodo El nodo al que se le va a poner un padre.
     * @return El nodo con un puntero hacia el padre.
     */
    private Node<T> setPadre(Node<T> pNodo) {
        PointerTreeNode nuevoNodo = new PointerTreeNode();
        nuevoNodo.padre = this;
        nuevoNodo.etiqueta = pNodo.getLabel();
        nuevoNodo.listaHijos = pNodo.getChildren();
        return nuevoNodo;
    }

    /**
     * Agrega un nuevo hijo en la lista.
     * @param child El hijo que se va a agregar.
     */
    @Override
    public void addChild(Node<T> child) {
        Node<T> nuevo = setPadre(child);
        if (listaHijos == null) {
            listaHijos = new LinkedList<>();
        }
        listaHijos.add(nuevo);
    }

    /**
     * Agrega un nuevo hijo en una posicion especifica de la lista.
     * @param index La posicion.
     * @param child El hijo que se va a agregar.
     */
    @Override
    public void addChildAt(int index, Node<T> child) {
        Node<T> nuevo = setPadre(child);
        if (listaHijos == null) {
            listaHijos = new LinkedList<>();
        }
        listaHijos.add(index, nuevo);
    }

    /**
     * Elimina todos los hijos del nodo, dejando la lista vacia.
     */
    @Override
    public void removeChildren() {
        listaHijos = null;
    }

    /**
     * Elimina un hijo en una posicion especifica de la lista.
     * @param index el indice del hijo que se va a remover.
     * @return el hijo eliminado.
     */
    @Override
    public Node<T> removeChildAt(int index) {
        return listaHijos.remove(index);
    }

    /**
     * Devuelve una lista con todos los hijos del nodo.
     * @return lista con todos los nodos hijos del nodo actual.
     */
    @Override
    public List<Node<T>> getChildren() {
        if (listaHijos == null) {
            return new LinkedList<>();
        } else {
            return listaHijos;
        }
    }

    /**
     * Regresa al hijo que se encuentra en una posicion especifica de la lista.
     * @param index la posicion del hijo.
     * @return El nodo hijo.
     */
    @Override
    public Node<T> getChildAt(int index) {
        return listaHijos.get(index);
    }
}
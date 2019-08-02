package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.tree.PointerTree;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.PointerTreeNode;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Node;

/**
 * Clase de prueba para PointerTree.
 * @author Eduardo Biazzetti.
 */
public class PointerTreetest {

    /**
     * Programa de prueba para Pointer Tree.
     * @param args parametros que recibe el main (si los hubieran).
     */
    public static void main(String[] args) {
        PointerTree<Integer> arbol = new PointerTree<>();

        Node<Integer> raiz = arbol.getRoot();
        raiz.setLabel(1);

        Node<Integer> hijo1 = new PointerTreeNode<>();
        hijo1.setLabel(2);
        Node<Integer> hijo2 = new PointerTreeNode<>();
        hijo2.setLabel(3);
        raiz.addChild(hijo1);
        raiz.addChild(hijo2);




    }
}
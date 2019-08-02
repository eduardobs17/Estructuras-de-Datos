package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.RedBlackTree;

/**
 * Clase de prueba para RB Tree.
 * @author Eduardo Biazzetti.
 */
public class RedBlackTreeTest {

    /**
     * Programa de prueba para RB Tree.
     * @param args parametros que recibe el main (si los hubieran).
     */
    public static void main(String[] args) {
        RedBlackTree<Integer,Character> arbol = new RedBlackTree<>();
        arbol.put(20, 'e');
        arbol.put(45, 'b');
        arbol.put(30, 'f');
        arbol.put(25, 'a');
        arbol.put(50, 'd');
        arbol.put(55, 'u');
        arbol.put(48, 'a');
        arbol.put(46, 'r');
        arbol.put(28, 'o');
        arbol.put(26, 'm');
        arbol.put(35, 'y');

        System.out.println("Se busca y, debe ser true: " + arbol.containsValue('y'));
        System.out.println("Se busca x, debe ser false: " + arbol.containsValue('x'));

        System.out.println("Se busca 35, debe ser true: " + arbol.containsKey(35));
        System.out.println("Se busca 100, debe ser false: " + arbol.containsKey(100));

        System.out.println("El tamaño debes ser 11: " + arbol.size());

    }

}

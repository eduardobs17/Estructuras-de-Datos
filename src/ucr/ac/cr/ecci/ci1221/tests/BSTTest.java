package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;
import ucr.ac.cr.ecci.ci1221.util.collections.set.BinarySearchTree;

import java.util.Iterator;

/**
 * Clase de prueba BST.
 * @author Eduardo Biazzetti.
 */
public class BSTTest {

    /**
     * Clase de prueba para BST.
     * @param args
     */
    public static void main(String[] args) {
        Set<Integer> arbol = new BinarySearchTree<>();

        arbol.put(15);
        arbol.put(6);
        arbol.put(10);
        arbol.put(25);
        arbol.put(11);
        arbol.put(8);
        arbol.put(31);
        arbol.put(3);
        arbol.put(20);
        arbol.put(1);
        arbol.put(5);
        arbol.put(9);
        arbol.put(2);
        arbol.put(17);
        arbol.put(30);

        System.out.println("Busca 15, debe ser true: " + arbol.isMember(15));
        System.out.println("Busca 1, debe ser true: " + arbol.isMember(1));
        System.out.println("Busca 30, debe ser true: " + arbol.isMember(30));
        System.out.println("Busca 22, debe ser false: " + arbol.isMember(22));
        System.out.println("Busca 4, debe ser false: " + arbol.isMember(8));

        System.out.println("El tamaño debe ser 15: " + arbol.size());

        System.out.println("Se elimina el 20");
        arbol.remove(20);
        System.out.println("Se elimina el 1");
        arbol.remove(1);
        System.out.println("Se elimina el 9");
        arbol.remove(9);

        Iterator<Integer> it1 = arbol.iterator();
        String hilera = "";
        while (it1.hasNext()) {
            hilera += it1.next();
            hilera += ", ";
        }
        System.out.println(hilera);
        arbol.clear();
        System.out.println("Tamaño debe ser 0: " + arbol.size() + " debe estar vacio: " + arbol.isEmpty());
    }
}
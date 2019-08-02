package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.set.LinkedListSet;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;

/**
 * Clase para la prueba de LinkedListSet.
 * @author Eduardo Biazzetti.
 */
public class LinkedListSetTest {

    /**
     * Programa de prueba para LinkedListSet.
     * @param args parametros que recibe el main (si los hubieran).
     */
    public static void main(String[] args) {
        Set<Integer> arbol = new LinkedListSet<>();

        System.out.println("El tamaño debe ser 0: " + arbol.size() + " y estar vacio: " + arbol.isEmpty());

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

        System.out.println("El tamaño debe ser 12: " + arbol.size() + " no estar vacio: " + arbol.isEmpty());

        arbol.clear();
        System.out.println("El tamaño debe ser 0: " + arbol.size() + " y estar vacio: " + arbol.isEmpty());
    }
}

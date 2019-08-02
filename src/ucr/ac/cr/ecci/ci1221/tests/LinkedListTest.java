package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import java.util.Iterator;

/**
 * Clase para la prueba de LinkedList.
 * @author Eduardo Biazzetti.
 */
public class LinkedListTest {

    /**
     * Programa de prueba para LinkedList.
     * @param args parametros que recibe el main (si los hubieran).
     */
    public static void main(String[] args) {
        List<Integer> lista = new LinkedList<>();
        System.out.println("El tamaño debe ser 0: " + lista.size() + " y estar vacio: " + lista.isEmpty());
        System.out.println("Debe ser nulo y tirar excepcion indexoutofbounds: " + lista.get(0));
        System.out.println("Debe dar error: " + lista.set(1,3));
        lista.add(1,3);
        lista.add(2,4);
        lista.add(5);
        lista.add(1,2);
        System.out.println("El tamaño debe ser 4: " + lista.size() + " y no estar vacio: " + lista.isEmpty());
        System.out.println("El objeto debe ser 2: " + lista.remove(1) + " y el tamaño debe ser 3: " + lista.size());
        lista.set(3,10);
        System.out.println("El objeto debe ser 10: " + lista.get(3) + " y el tamaño debe ser 3: " + lista.size());
        lista.clear();
        System.out.println("El tamaño debe ser 0: " + lista.size() + " y estar vacio: " + lista.isEmpty());
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.set(1,0);
        System.out.println("El objeto debe ser 0: " + lista.get(1) + " y dar error: " + lista.remove(0));

        LinkedList<String> nuevo = new LinkedList<>();
        nuevo.add("Nombres");
        nuevo.add("De");
        nuevo.add("Personas");
        nuevo.add("Raquel");
        nuevo.add("Eduardo");
        nuevo.add("Monica");
        nuevo.add("Sofia");
        nuevo.add("Emily");
        nuevo.add("Jose");
        nuevo.add("Juan");
        nuevo.add("ESTO");
        nuevo.add("ES");
        nuevo.add("UN");
        nuevo.add("CASO");
        nuevo.add("DE");
        nuevo.add("PRUEBA");
        System.out.println("El tamaño debe ser 16: " + nuevo.size() + " y no estar vacio: " + nuevo.isEmpty());
        System.out.println("El objeto debe ser Raquel: " + nuevo.previous(5));
        System.out.println("El objeto debe ser Eduardo: " + nuevo.next(4));
        System.out.println("El objeto debe ser Juan: " + nuevo.remove(10) + " y tener tamaño 15: " + nuevo.size());
        System.out.println("Prueba del Iterador:");
        Iterator<String> it1 = nuevo.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }
    }
}
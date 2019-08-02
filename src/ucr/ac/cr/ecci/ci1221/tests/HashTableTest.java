package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.Dictionary;
import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.Hashtable;
import java.util.Iterator;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * Clase para la prueba de HashTablle.
 * @author Eduardo Biazzetti.
 */
public class HashTableTest {

    /**
     * Programa de prueba para HashTable.
     * @param args parametros que recibe el main (si los hubieran).
     */
    public static void main(String[] args) {
        Dictionary<Integer,String> diccionario = new Hashtable<>();
        System.out.println("El tamaño debe ser 0: " + diccionario.size() + " y estar vacio: " + diccionario.isEmpty());
        System.out.println("Debe ser false: " + diccionario.containsKey(1));
        System.out.println("Debe ser false: " + diccionario.containsValue("a"));
        diccionario.put(1,"a");
        diccionario.put(2,"b");
        diccionario.put(3,"c");
        diccionario.put(4,"d");

        System.out.println("Debe ser nulo y tirar excepcion NullException:");
        diccionario.put(null,"a");
        diccionario.put(1,null);

        System.out.println("El tamaño debe ser 4: " + diccionario.size() + " y no estar vacio: " + diccionario.isEmpty());
        System.out.println("El objeto debe ser a: " + diccionario.remove(1) + " y el tamaño debe ser 3: " + diccionario.size());
        System.out.println("El objeto debe ser c: " + diccionario.get(3) + " y el tamaño debe ser 3: " + diccionario.size());
        diccionario.put(3,"a");
        System.out.println("El objeto debe ser a: " + diccionario.get(3) + " y el tamaño debe ser 3: " + diccionario.size());
        System.out.println("El objeto debe ser false: " + diccionario.containsKey(null));

        System.out.println("El objeto debe ser d: " + diccionario.get(4));
        System.out.println("El objeto debe ser null: " + diccionario.get(null));
        System.out.println("El objeto debe ser false y tirar excepcion: " + diccionario.containsValue(null));

        System.out.println("El objeto debe ser true: " + diccionario.containsKey(4));
        System.out.println("El objeto debe ser false y tirar excepcion: " + diccionario.containsKey(null));

        System.out.println("El objeto debe ser true: " + diccionario.containsValue("a"));
        System.out.println("El objeto debe ser false y tirar excepcion: " + diccionario.containsValue(null));

        Set<Integer> set = diccionario.keySet();
        Iterator<Integer> it1 = set.iterator();
        System.out.println("Lista de keys:");
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }

        System.out.println("Lista de values:");
        List<String> lista = diccionario.values();
        for (int x = 1; x <= lista.size(); x++) {
            System.out.println(lista.get(x));
        }

        diccionario.clear();
        System.out.println("El tamaño debe ser 0: " + diccionario.size() + " y estar vacio: " + diccionario.isEmpty());
    }
}
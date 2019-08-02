package ucr.ac.cr.ecci.ci1221.util.algorithm;

import java.util.Iterator;
import ucr.ac.cr.ecci.ci1221.util.collections.list.ArrayList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * Utilitary class that provides searching algorithms for looking for elements in lists.
 * En esta clase, hay dos algoritmos por implementar: busqueda lineal y busqueda binaria.
 *
 * @author Eduardo Biazzetti.
 */
public class SearchAlgorithms{

    /**
     * Aplica búsqueda lineal para buscar el elemento.
     * @param list La lista sobre la cual se va a buscar elemento.
     * @param key El elemento que se va a buscar
     * @param <T> El tipo de elemento de la lista.
     * @return el elemento; si no se encuentra, null.
     */
    public static <T extends Comparable<? super T>> T linearSearch(List<T> list, T key){
        Iterator<T> it1 = list.iterator();
        while (it1.hasNext()) {
            T elemento = it1.next();
            if (elemento.compareTo(key) == 0) {
                return elemento;
            }
        }
        return null;
    }

    /**
     * Aplica búsqueda binaria para buscar el elemento.
     * @param lista La lista en la cual se va a buscar el elemento.
     * @param key El elemento que se va a buscar.
     * @param <T> El tipo de elemento de la lista.
     * @return el elemento; null si no se encuentra.
     */
    public static <T extends Comparable<? super T>> T binarySearch(List<T> lista, T key){
        if (lista.size() == 0) {
            return null;
        }

        if(lista.size() == 1) {
            if (key.compareTo(lista.get(1)) == 0) {
                return key;
            } else {
                return null;
            }
        }


        List<T> list = new ArrayList<>();
        for (int x = 1; x <= lista.size(); x++) {
            list.add(lista.get(x));
        }
        SortingAlgorithms.quickSort(list,1,list.size());
        if ((key.compareTo(list.get(1)) >= 0) && (key.compareTo(list.get(list.size())) <= 0)) {
            T elemento = list.get(list.size()/2);

            if (key.compareTo(elemento) == 0) {
                return key;
            } else {
                if (key.compareTo(elemento) < 0) {
                    List<T> nuevo = new ArrayList<>();
                    for (int x = 1; x <= (list.size() / 2) - 1; x++) {
                        nuevo.add(list.get(x));
                    }
                    return binarySearch(nuevo,key);
                }
                if (key.compareTo(elemento) > 0) {
                    List<T> nuevo = new ArrayList<>();
                    for (int x = (list.size() / 2) + 1; x <= list.size(); x++) {
                        nuevo.add(list.get(x));
                    }
                    return binarySearch(nuevo,key);
                }
            }
        }
        return null;
    }
}
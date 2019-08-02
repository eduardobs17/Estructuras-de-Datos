package ucr.ac.cr.ecci.ci1221.util.algorithm;

import ucr.ac.cr.ecci.ci1221.util.collections.list.ArrayList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * Utilitary class that provides sorting algorithms for sorting elements in lists.
 *
 * @author Eduardo Biazzetti.
 */
public class SortingAlgorithms {

    /**
     * Ordena la lista ascendentemente aplicando el algoritmo de burbuja.
     * @param list La lista que se va a ordenar.
     * @param <T> El tipo de elemento de la lista.
     */
    public static <T extends Comparable<? super T>> void bubbleSort(List<T> list){
        if (list.size() > 1) {
            int i = 0;
            for (int x = 2; x < list.size(); x++) {
                for (int y = 1; y < list.size() - i; y++) {
                    T temp = list.get(y);
                    T temp2 = list.get(y+1);
                    if (temp.compareTo(temp2) > 0) {
                        list.set(y, temp2);
                        list.set(y+1, temp);
                    }
                }
                i++;
            }
        }
    }

    /**
     * Ordena la lista ascendentemente aplicando el algoritmo de inserción.
     * @param list La lista que se va a ordenar.
     * @param <T> El tipo de elemento de la lista.
     */
    public static <T extends Comparable<? super T>> void insertionSort(List<T> list){
        if (list.size() > 1) {
            for (int x = 2; x <= list.size(); x++) {
                T temp = list.get(x);
                int contador = x - 1;
                while ((contador > 0) && (list.get(contador).compareTo(temp) > 0)) {
                    list.set(contador + 1, list.get(contador));
                    contador--;
                }
                list.set(contador + 1, temp);
            }
        }
    }

    /**
     * Ordena la lista ascendentemente aplicando el algoritmo de selección.
     * @param list La lista que se va a ordenar.
     * @param <T> El tipo de elemento de la lista.
     */
    public static <T extends Comparable<? super T>> void selectionSort(List<T> list){
        for (int x = 1; x < list.size(); x++) {
            int posMenor = 1;
            T menor = list.get(x);
            for (int counter = x + 1; counter <= list.size(); counter++) {
                if (list.get(counter).compareTo(menor) < 0) {
                    menor = list.get(counter);
                    posMenor = counter;
                }
            }
            if (menor.compareTo(list.get(x)) != 0) {
                list.set(posMenor, list.get(x));
                list.set(x, menor);
            }
        }
    }

    /**
     * Ordena la lista ascendentemente aplicando el algoritmo de ordenamiento rápido, o quicksort.
     * @param list La lista que se va a ordenar.
     * @param inicio El indice desde dónde se va a ordenar la lista.
     * @param finalPos El indice hasta dónde se va a ordenar la lista.
     * @param <T> El tipo de elemento de la lista.
     */
    public static <T extends Comparable<? super T>> void quickSort(List<T> list, int inicio, int finalPos){
        if (finalPos - inicio > 0) {
            T pivote = list.get(finalPos);
            int j = finalPos;
            int i = inicio;

            while (i < j) {
                while ((list.get(i).compareTo(pivote) < 0) && (i < j)) {
                    i++;
                }
                while ((list.get(j).compareTo(pivote) >= 0) && (i < j)) {
                    j--;
                }
                if (i != j) {
                    T temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
                if (i < j) {
                    i++;
                    if (i < j) {
                        j--;
                    }
                }
            }
            while (list.get(i).compareTo(pivote) < 0) {
                i++;
            }
            T temp = list.get(i);
            list.set(i, list.get(finalPos));
            list.set(finalPos, temp);

            quickSort(list, inicio, i - 1);
            quickSort(list, i + 1, finalPos);
        }
    }

    /**
     * Ordena la lista ascendentemente aplicando el algoritmo de ordenamiento por mezcla, o mergesort.
     * Realiza la partición de la lista.
     *
     * @param list La lista que se va a ordenar.
     * @param inicio El indice desde dónde se va a dividir la lista.
     * @param finalPos El indice hasta dónde se va a dividir la lista.
     * @param <T> El tipo de elemento de la lista.
     */
    public static <T extends Comparable<? super T>> void mergeSort(List<T> list, int inicio, int finalPos){
        if (finalPos - inicio > 0) {
            int mitad = (finalPos + inicio) / 2;
            mergeSort(list, inicio, mitad);
            mergeSort(list, mitad + 1, finalPos);
            merge(list, inicio, mitad, finalPos);
        }
    }

    /**
     * Método auxiliar. Una vez particionada la lista, se encarga de unirlas, pero ya ordenadas.
     * @param list La lista que se va a ordenar.
     * @param inicio El indice desde donde se va a ordenar la lista.
     * @param medio  Indica la mitad de la lista. Sirve para que los contadores no se sobrepasen el límite.
     * @param finalPos El indice hasta dónde se va a ordenar la lista.
     * @param <T> El tipo de elemento de la lista.
     */
    private static <T extends Comparable<? super T>> void merge(List<T> list, int inicio, int medio, int finalPos) {
        List<T> arrayTemp = new ArrayList<>();
        int x = inicio;
        int y = medio + 1;

        while ((x <= medio) && (y <= finalPos)) {
            if (list.get(x).compareTo(list.get(y)) < 0) {
                arrayTemp.add(list.get(x));
                x++;
            } else {
                arrayTemp.add(list.get(y));
                y++;
            }
        }

        while (x <= medio) {
            arrayTemp.add(list.get(x));
            x++;
        }

        while (y <= finalPos) {
            arrayTemp.add(list.get(y));
            y++;
        }

        for (int contador = 1; contador <= arrayTemp.size(); contador++) {
            list.set(inicio + contador - 1, arrayTemp.get(contador));
        }
    }
}
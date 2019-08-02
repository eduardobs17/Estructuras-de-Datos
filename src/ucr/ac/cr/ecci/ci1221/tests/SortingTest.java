package ucr.ac.cr.ecci.ci1221.tests;

import java.util.Iterator;
import ucr.ac.cr.ecci.ci1221.util.algorithm.SortingAlgorithms;
import ucr.ac.cr.ecci.ci1221.util.collections.list.ArrayList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * Clase de prueba para los alg. de ordenamiento.
 * @author Eduardo Biazzetti.
 */
public class SortingTest {

    /**
     * Programa de prueba para los alg. de ordenamiento.
     * @param args parametros que recibe el main (si los hubieran).
     */
    public static void main(String[] args) {
        List<Integer> listaBS = new ArrayList<>();
        listaBS.add(22);
        listaBS.add(40);
        listaBS.add(4);
        listaBS.add(10);
        listaBS.add(12);
        listaBS.add(35);
        SortingAlgorithms.bubbleSort(listaBS);
        System.out.println("Lista antes del ordenamiento: 22,40,4,10,12,35");
        String hilera = "Luego de BubbleSort: ";
        Iterator<Integer> it = listaBS.iterator();
        while (it.hasNext()) {
            hilera += it.next();
            hilera += ",";
        }
        System.out.println(hilera + '\n');

        List<Integer> listaIS = new ArrayList<>();
        listaIS.add(5);
        listaIS.add(3);
        listaIS.add(7);
        listaIS.add(6);
        listaIS.add(2);
        listaIS.add(1);
        listaIS.add(4);
        SortingAlgorithms.insertionSort(listaIS);
        System.out.println("Lista antes del ordenamiento: 5,3,7,6,2,1,4");
        hilera = "Luego de InserionSort:";
        Iterator<Integer> it1 = listaIS.iterator();
        while (it1.hasNext()) {
            hilera += it1.next();
            hilera += ",";
        }
        System.out.println(hilera + '\n');

        List<Integer> listaSS = new ArrayList<>();
        listaSS.add(6);
        listaSS.add(8);
        listaSS.add(12);
        listaSS.add(11);
        listaSS.add(2);
        listaSS.add(10);
        listaSS.add(4);
        listaSS.add(7);
        listaSS.add(1);
        listaSS.add(14);
        listaSS.add(13);
        listaSS.add(3);
        listaSS.add(9);
        listaSS.add(5);
        SortingAlgorithms.selectionSort(listaSS);
        System.out.println("Lista antes del ordenamiento: 6,8,12,11,2,10,4,7,1,14,13,3,9,5");
        hilera = "Luego de SelectionSort: ";
        Iterator<Integer> it2 = listaSS.iterator();
        while (it2.hasNext()) {
            hilera += it2.next();
            hilera += ",";
        }
        System.out.println(hilera + '\n');

        List<Character> listaQS = new ArrayList<>();
        listaQS.add('x');
        listaQS.add('a');
        listaQS.add('m');
        listaQS.add('t');
        listaQS.add('s');
        listaQS.add('b');
        listaQS.add('e');
        listaQS.add('p');
        listaQS.add('o');
        listaQS.add('r');
        SortingAlgorithms.quickSort(listaQS, 1, listaQS.size());
        System.out.println("Lista antes del ordenamiento: x,a,m,t,s,b,e,p,o,r");
        hilera = "Luego de QuickSort: ";
        Iterator<Character> it3 = listaQS.iterator();
        while (it3.hasNext()) {
            hilera += it3.next();
            hilera += ",";
        }
        System.out.println(hilera + '\n');

        List<Integer> listaMS = new ArrayList<>();
        listaMS.add(4);
        listaMS.add(9);
        listaMS.add(99);
        listaMS.add(3);
        listaMS.add(71);
        listaMS.add(31);
        listaMS.add(13);
        listaMS.add(96);
        listaMS.add(2);
        listaMS.add(10);
        listaMS.add(95);
        listaMS.add(15);
        listaMS.add(193);
        listaMS.add(22);
        listaMS.add(58);
        listaMS.add(69);
        listaMS.add(21);
        listaMS.add(60);
        SortingAlgorithms.mergeSort(listaMS, 1, listaMS.size());
        System.out.println("Lista antes del ordenamiento: 4,9,99,3,71,31,3,96,2,10,95,15,193,22,58,69,21,60");
        hilera = "Luego de MergeSort: ";
        Iterator<Integer> it4 = listaMS.iterator();
        while (it4.hasNext()) {
            hilera += it4.next();
            hilera += ",";
        }
        System.out.println(hilera);
    }
}
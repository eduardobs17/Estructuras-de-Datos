package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.algorithm.SearchAlgorithms;
import ucr.ac.cr.ecci.ci1221.util.collections.list.ArrayList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * Clase de prueba para los alg. de busqueda.
 * @author Eduardo Biazzetti.
 */
public class SearchTest {

    /**
     * Programa de prueba para los alg. de busqueda.
     * @param args parametros que recibe el main (si los hubieran).
     */
    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>();
        lista.add(22);
        lista.add(40);
        lista.add(4);
        lista.add(10);
        lista.add(12);
        lista.add(35);
        System.out.println("En la lista 22,40,4,10,12,35");
        System.out.println("Aplicando Busqeda lineal, el elemento se debe encontrar ('40'): " + SearchAlgorithms.linearSearch(lista, 40) + '\n');

        List<Integer> lista2 = new ArrayList<>();
        lista2.add(5);
        lista2.add(3);
        lista2.add(7);
        lista2.add(6);
        lista2.add(2);
        lista2.add(1);
        lista2.add(4);
        System.out.println("En la lista 5,3,7,6,2,1,4");
        System.out.println("Aplicando Busqueda Lineal, el elemento no se debe encontrar ('10'): " + SearchAlgorithms.linearSearch(lista2, 10) + '\n');

        List<Integer> lista3 = new ArrayList<>();
        lista3.add(6);
        lista3.add(8);
        lista3.add(12);
        lista3.add(11);
        lista3.add(2);
        lista3.add(10);
        lista3.add(4);
        lista3.add(7);
        lista3.add(1);
        lista3.add(14);
        lista3.add(13);
        lista3.add(3);
        lista3.add(9);
        lista3.add(5);
        System.out.println("En la lista 6,8,12,11,2,10,4,7,1,14,13,3,9,5");
        System.out.println("Aplicando BusquedaBinaria, el elemento se debe encontrar ('1'): " + SearchAlgorithms.binarySearch(lista3,1));
        System.out.println("Aplicando BusquedaBinaria, el elemento se debe encontrar ('13'): " + SearchAlgorithms.binarySearch(lista3,13));
        System.out.println("Aplicando BusquedaBinaria, el elemento se debe encontrar ('7'): " + SearchAlgorithms.binarySearch(lista3,7) + '\n');

        List<Character> lista4 = new ArrayList<>();
        lista4.add('x');
        lista4.add('a');
        lista4.add('m');
        lista4.add('t');
        lista4.add('s');
        lista4.add('b');
        lista4.add('e');
        lista4.add('p');
        lista4.add('o');
        lista4.add('r');
        System.out.println("En la lista x,a,m,t,s,b,e,p,o,r");
        System.out.println("Aplicando BusquedaBinaria, el elemento no se debe encontrar ('z'): " + SearchAlgorithms.binarySearch(lista4,'z'));

    }
}
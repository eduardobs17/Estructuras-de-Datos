package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.algorithm.StringAlgorithms;

public class StringTest {

    public static void main(String[] args) {
        String text = "esta es una hilera de prueba";
        String pattern = "una";
        System.out.println("Se prueba el primer algoritmo: NaiveStringMatcher");
        System.out.println("Se busca '" + pattern + "' en '" + text + "'");
        System.out.println("El indice donde se encuentra debe ser 8: " + StringAlgorithms.naiveStringMatcher(text, pattern) + '\n');

        pattern = "no";
        System.out.println("Se busca '" + pattern + "' en '" + text + "'");
        System.out.println("El indice donde se encuentra debe ser -1: " + StringAlgorithms.naiveStringMatcher(text, pattern) + '\n');


        System.out.println("Se prueba el segundo algoritmo: Knuth-Morris_Pratt");
        pattern = "una";
        System.out.println("Se busca '" + pattern + "' en '" + text + "'");
        System.out.println("El indice donde se encuentra debe ser 8: " + StringAlgorithms.knuthMorrisPrattStringMatcher(text, pattern) + '\n');

        pattern = "no";
        System.out.println("Se busca '" + pattern + "' en '" + text + "'");
        System.out.println("El indice donde se encuentra debe ser -1: " + StringAlgorithms.knuthMorrisPrattStringMatcher(text, pattern) + '\n');


        System.out.println("Se prueba el tercer algoritmo: Boyer-Moore");
        pattern = "una";
        System.out.println("Se busca '" + pattern + "' en '" + text + "'");
        System.out.println("El indice donde se encuentra debe ser 8: " + StringAlgorithms.boyerMooreStringMatcher(text, pattern) + '\n');

        pattern = "no";
        System.out.println("Se busca '" + pattern + "' en '" + text + "'");
        System.out.println("El indice donde se encuentra debe ser -1: " + StringAlgorithms.boyerMooreStringMatcher(text, pattern) + '\n');


        text = "esta es una hilera de prueba mas larga, los 3 algoritmos se van a probar con esta nueva hilera, y se buscara no una palabra sino varias";
        System.out.println("Se buscara 'prueba mas' en '" + text + "'");
        System.out.println("Usando naive, el indice donde se encuentra debe ser 22: " + StringAlgorithms.naiveStringMatcher(text, "prueba mas") + '\n');

        System.out.println("Se buscara '3 algoritmos' en '" + text + "'");
        System.out.println("Usando KNP, el indice donde se encuentra debe ser 44: " + StringAlgorithms.knuthMorrisPrattStringMatcher(text, "3 algoritmos") + '\n');

        System.out.println("Se buscara 'buscara no una' en '" + text + "'");
        System.out.println("Usando BM, el indice donde se encuentra debe ser 101: " + StringAlgorithms.boyerMooreStringMatcher(text, "buscara no una") + '\n');
    }
}
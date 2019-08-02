package ucr.ac.cr.ecci.ci1221.util.algorithm;

/**
 * Algoritmos de busqueda en textos.
 * @author Rodrigo A. Bartels
 */
public class StringAlgorithms {

    /**
     * Aplica el metodo de fuerza bruta (naive) para buscar un patron en un texto dado.
     *
     * @param text El texto en donde se va a buscar.
     * @param pattern El patron que se va a buscar en el texto.
     * @return Si el patron se encuentra en el texto, se devuelve el indice donde aparece;
     *         sino, devuelve -1.
     */
    public static int naiveStringMatcher(String text, String pattern){
        if (text.length() > pattern.length()) {
            for (int x = 0; x < text.length() - pattern.length(); x++) {
                char charText = text.charAt(x);
                char charPattern = pattern.charAt(0);
                if (charText == charPattern) {
                    int counter = 1;
                    boolean palabraCompleta = true;
                    while (counter < pattern.length() && palabraCompleta) {
                        char charText2 = text.charAt(x + counter);
                        char charPattern2 = pattern.charAt(counter);
                        if (charText2 != charPattern2) {
                            palabraCompleta = false;
                        } else {
                            counter++;
                        }
                    }
                    if (palabraCompleta) {
                        return x;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Aplica el algoritmo de Knuth-Morris-Pratt(KMP) para buscar un patron en un texto dado.
     *
     * @param text El texto en donde se va a buscar.
     * @param pattern El patron que se va a buscar en el texto.
     * @return Si el patron se encuentra en el texto, se devuelve el indice donde aparece;
     *         sino, devuelve -1.
     */
    public static int knuthMorrisPrattStringMatcher(String text, String pattern) {
        if (pattern.length() < text.length()) { // texto mas grande que lo que se busca.
            //Primero se crea el array
            int[] array = new int[pattern.length()];
            int i = 0;
            int j = 1;
            array[0] = 0;
            while (j < array.length) {
                char charI = pattern.charAt(i);
                char charJ = pattern.charAt(j);
                if (charI == charJ) {
                    array[j] = i +  1;
                    j++;
                    i++;
                } else {
                    if (i != 0) {
                        i = array[i - 1];
                    } else {
                        array[j] = 0;
                        j++;
                    }
                }
            }

            int iteratorT = 0;
            int iteratorP = 0;
            boolean palabraEncontrada = false;
            while (iteratorT < text.length() && !palabraEncontrada) {
                char charT = text.charAt(iteratorT);
                char charP = pattern.charAt(iteratorP);
                if (charP == charT) {
                    iteratorP++;
                    iteratorT++;
                    if (iteratorP == pattern.length()) {
                        palabraEncontrada = true;
                    }
                } else {
                    if (iteratorP != 0) {
                        iteratorP = array[iteratorP - 1];
                    } else {
                        iteratorT++;
                    }
                }
            }

            if (palabraEncontrada) {
                return iteratorT - pattern.length();
            }
        }
        return -1;
    }

    /**
     * Aplica el algoritmo de Boyer-Moore para buscar un patron en un texto dado.
     *
     * @param text El texto en donde se va a buscar.
     * @param pattern El patron que se va a buscar en el texto.
     * @return Si el patron se encuentra en el texto, se devuelve el indice donde aparece;
     *         sino, devuelve -1.
     */
    public static int boyerMooreStringMatcher(String text, String pattern) {
        int tamTexto = text.length();
        int tamPatron = pattern.length();
        if (tamPatron < tamTexto) { // texto mas grande que lo que se busca.
            int iteratorP = tamPatron - 1;
            int iteratorT = tamPatron - 1;

            char[] letras = new char[tamPatron - 1];
            for (int x = 0; x < tamPatron - 1; x++) {
                letras[x] = pattern.charAt(x);
            }

            while (iteratorT <= tamTexto - 1 && iteratorP > -1) {
                char t = text.charAt(iteratorT);
                char p = pattern.charAt(iteratorP);
                if (text.charAt(iteratorT) == pattern.charAt(iteratorP)) {
                    iteratorT--;
                    iteratorP--;
                } else {
                    int pos = 0;
                    for (int x = 0; x < letras.length; x++) {
                        if (letras[x] == text.charAt(iteratorT)) {
                            pos = x + 1;
                            x = letras.length * 2;
                        }
                    }
                    iteratorT = iteratorT + tamPatron - pos;
                    iteratorP = tamPatron - 1;
                }
            }

            if (iteratorP == -1) {
                return iteratorT + 1;
            }
        }
        return -1;
    }
}
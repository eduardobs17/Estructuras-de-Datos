package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.set.Trie;

/**
 * Programa de prueba para Trie.
 * @author Eduardo Biazzetti.
 */
public class TrieTest {

    /**
     * Metodo de prueba para Trie.
     * @param args parametros de main.
     */
    public static void main(String[] args) {
        Trie set = new Trie();
        set.put("hola");
        set.put("hol");
        set.put("hols");
        set.put("holi");
        set.put("olas");
        set.put("olor");

        System.out.println("Se busca hola, debe ser true: " + set.isMember("hola"));
        set.iterator();
        set.remove("hola");

        System.out.println("Se elimina y busca hola, debe ser false: " + set.isMember("hola"));
    }
}
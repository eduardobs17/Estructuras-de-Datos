package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.set.BitVector;
import ucr.ac.cr.ecci.ci1221.util.collections.set.EnumerableSet;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Enumerable;

import java.util.Iterator;

/**
 * Programa de prueba para BitVector.
 * @author Eduardo Biazzetti.
 */
public class BitVectorTest {

    /** Método de prueba para Bitvector.
     * @param args parametros que recibe el main.
     */
    public static void main(String[] args) {

        class CharacterPrueba implements Enumerable {
            private char valor;
            public CharacterPrueba(char obj) {
                if (((int) obj < 32) || ((int) obj > 126)) {
                    valor = 0;
                } else {
                    valor = obj;
                }
            }

            public int getIndex() {
                if (valor == 0) {
                    return 0;
                } else {
                    return (int) valor - 31;
                }
            }
        }

        EnumerableSet<CharacterPrueba> set = new BitVector(9);

        System.out.println("Tamaño debe ser 0: " + set.size() + " debe estar vacio: " + set.isEmpty());

        set.put(new CharacterPrueba('a'));
        set.put(new CharacterPrueba('w'));
        set.put(new CharacterPrueba('b'));
        set.put(new CharacterPrueba('!'));
        set.put(new CharacterPrueba(' '));
        set.put(new CharacterPrueba('9'));
        set.put(new CharacterPrueba('?'));
        set.put(new CharacterPrueba('e'));
        set.put(new CharacterPrueba('d'));
        set.put(new CharacterPrueba('3'));
        set.put(new CharacterPrueba('m'));

        System.out.println("Tamaño debe ser 11: " + set.size() + " no debe estar vacio: " + set.isEmpty());

        System.out.println("Se busca ?, debe ser true: " + set.isMember(new CharacterPrueba('?')));
        System.out.println("Se busca x, debe ser false: " + set.isMember(new CharacterPrueba('x')));

        System.out.println("Se elimina a y m");
        set.remove(new CharacterPrueba('a'));
        set.remove(new CharacterPrueba('m'));

        System.out.println("Tamaño debe ser 9: " + set.size() + " no debe estar vacio: " + set.isEmpty());

        System.out.println("Se prueba iterador, los elementos deben ser w, b !, ' ', 9, ?, e, d, 3");
        Iterator<CharacterPrueba> it1 = set.iterator();
        String hilera = "";
        while (it1.hasNext()) {
            hilera += it1.next();
            hilera += ", ";
        }
        System.out.println(hilera);

        set.clear();
        System.out.println("Se limpia el set.");
        System.out.println("Tamaño debe ser 0: " + set.size() + " debe estar vacio: " + set.isEmpty());
    }
}
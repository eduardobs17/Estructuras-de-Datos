package ucr.ac.cr.ecci.ci1221.util.collections.dictionary;

import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;

/**
 * @author Eduardo Biazzetti.
 */
public class TwoThreeTree<K extends Comparable<? super K>, V> implements Dictionary<K, V> {

    private Nodo raiz;

    private class Nodo {}

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return raiz == null;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public void clear() {
        raiz = null;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public List<V> values() {
        return null;
    }
}

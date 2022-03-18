package Classes;

import Interfaces.IVector;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;

/*
This is a stateless extension
 */
public abstract class Vector<K,V> implements IVector<K,V> {

    private Set<Map.Entry<K, V>> entrySet;
    private transient int size;

    public Vector(Map<K, V> M) {
        entrySet = M.entrySet();
        size = M.size();
    }

    public Vector(K key, V val) {
        Entry<K,V> entry = new Entry<>(key, val);
        Set<Map.Entry<K,V>> set = new HashSet<>();
        set.add(entry);
        entrySet = set;
        size = 1;
    }

    static class Entry<K,V> implements Map.Entry<K,V>{
        final K key;
        final V value;

        Entry(K k, V v){
            this.key = k;
            this.value = v;
        }

        public final K getKey() { return key;}
        public final V getValue() { return value; }
        public final String toString() { return key + "=" + value; }
        public final V setValue(V value) { return null; }  // Since vectors are stateless, te value of the entries is not modifiable.
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return entrySet.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        for (Map.Entry<K,V> e : entrySet){
            if (e.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Map.Entry<K,V> e : entrySet){
            if (e.getValue().equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        for (Map.Entry<K,V> e : entrySet){
            if (e.getKey().equals(key)){
                return e.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException("Vectors are immutable, and cannot be altered");
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException("Vectors are immutable, and cannot be altered");
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException("Vectors are immutable, and cannot be altered");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Vectors are immutable, and cannot be cleared");
    }

    @Override
    public Set<K> keySet() {
        return entrySet.stream().map(e -> e.getKey()).collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return entrySet.stream().map(e -> e.getValue()).collect(Collectors.toSet());
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet(){
        return entrySet;
    }
}

package Classes;

import Interfaces.IVector;

import java.util.*;

/*
This is a stateless extension
 */
public class Vector<K,V> extends AbstractMap<K, V> implements IVector{

    private Set<Map.Entry<K, V>> entrySet;
    private transient int size;

    public Vector(K key, V val) {
        super(key, val);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return entrySet;
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
    public Vector zero() {
        return (Vector)Collections.EMPTY_MAP;
    }

    @Override
    public Vector add(IVector x, IVector y) {
        return null;
    }

    @Override
    public Vector mult(IVector x, double y) {
        return null;
    }
}

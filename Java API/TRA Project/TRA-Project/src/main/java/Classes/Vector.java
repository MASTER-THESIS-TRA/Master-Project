package Classes;

import Interfaces.IVector;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Vector<K,V> implements IVector {

    private Map<K,V> vector;
    private transient int size;

    public Vector(Map<? extends K, ? extends V> M) {
        this.vector = (Map<K, V>) M;
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
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
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}

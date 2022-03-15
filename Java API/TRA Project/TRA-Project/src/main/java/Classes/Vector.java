package Classes;

import Interfaces.IVector;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Vector implements IVector {
    @Override
    public IVector zero() {
        return (IVector)Collections.EMPTY_MAP;
    }

    @Override
    public IVector add(IVector x, IVector y) {
        return null;
    }

    @Override
    public IVector mult(IVector x, double y) {
        return null;
    }

    @Override
    public int size() {
        return 0;
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
        Collections.add
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

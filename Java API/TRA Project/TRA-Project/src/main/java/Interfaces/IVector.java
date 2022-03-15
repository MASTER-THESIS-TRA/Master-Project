package Interfaces;

import Classes.Vector;

import java.util.AbstractMap;
import java.util.Map;

/**
 * This is a stateless interface. Implementations of this interface should adhere to the statelesness.
 */
public interface IVector<K,V> {

    public IVector zero();
    public IVector add(IVector x, IVector y);
    public IVector mult(IVector x, double y);
}

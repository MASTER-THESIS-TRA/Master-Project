package Interfaces;

import Classes.Vector;
import org.apache.commons.lang3.NotImplementedException;

import java.util.AbstractMap;
import java.util.Map;

/**
 * This is a stateless interface. Implementations of this interface should adhere to the statelesness.
 */
public interface IVector<K,V> extends Map<K,V> {
    IVector zero();
    IVector add(IVector x, IVector y);
    IVector mult(IVector x, double y);
}

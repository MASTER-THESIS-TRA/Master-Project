package Interfaces;

import java.util.Map;

/**
 * This is a stateless interface. Implementations of this interface should adhere to the statelesness.
 */
public interface IVector<K,V> extends Map<K,V> {
    IVector Zero();
    IVector Add(IVector x, IVector y);
    IVector Mult(IVector x, Integer y);
}

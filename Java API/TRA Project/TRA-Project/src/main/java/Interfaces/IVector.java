package Interfaces;

import java.util.Map;

/**
 * This is a stateless interface. Implementations of this interface should adhere to the statelesness.
 */
public interface IVector extends Map {
    public IVector zero();
    public IVector add(IVector x, IVector y);
    public IVector mult(IVector x, double y);
}

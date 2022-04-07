package Classes;

import Interfaces.IVector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Weight extends Vector<String, Double> {
    public Weight(Map<String, Double> M){
        super(M);
    }
    public Weight(String resourceType, Double weight){
        super(resourceType,weight);
    }

    @Override
    public Vector Zero() {
        return zero();
    }

    @Override
    public Weight Add(IVector x, IVector y) {
        try{
            return add((Weight)x,(Weight)y);
        } catch(ClassCastException e){
            throw new ClassCastException("Could not cast vector to type Weight");
        }
    }

    @Override
    public Weight Mult(IVector x, Integer y) {
        try{
            return mult((Weight)x,y);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            throw new ClassCastException("Could not cast vector to type Weight");
        }
    }

    public static Weight add(Weight x, Weight y) {
        HashMap<String, Double> sum = new HashMap();
        sum.putAll(x);
        sum.putAll(y);
        return new Weight(sum);
    }

    public static Weight zero(){
        return new Weight(Collections.emptyMap());
    }

    public static Weight mult(Weight x, Integer y) {
        try{
            Map<String, Double> ret = new HashMap();
            for (Map.Entry<String, Double> e : x.entrySet()){
                ret.put(e.getKey(),e.getValue()*y);
            }
            return new Weight(ret);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            throw new ClassCastException("Could not cast vector to type Resource");
        }
    }

    // Utility functions:

    // It does not make sense to check which resource is "biggest", because of compact resources.
    // Equality is nice to be able to verify though.
    @Override
    public boolean equals(Object o){
        try {
            Weight cmp = (Weight) o;
            // Creating temporary HashMaps to be able to modify them.
            HashMap<String,Double> tmpA = new HashMap(cmp);
            HashMap<String,Double> tmpB = new HashMap(this);
            // Removing any keys that are zero, since any resource implicitly has 0 of all possible resource types in existence.
            tmpA.entrySet().removeIf(entry -> entry.getValue() == 0);
            tmpB.entrySet().removeIf(entry -> entry.getValue() == 0);

            // Verify that the remaining key are the same in both maps, then compare values.
            if (tmpB.keySet().equals(tmpA.keySet())){
                for (String k : tmpA.keySet()){
                    if (!tmpB.get(k).equals(tmpA.get(k))){
                        return false;
                    }
                }
                return true;
            }
            return false;
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Weight Sum(ArrayList<Weight> resourceList){
        Weight sum = Weight.zero();
        for (Weight t : resourceList){
            sum = Weight.add(sum, t);
        }
        return sum;
    }
}

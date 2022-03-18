package Classes;

import Interfaces.IVector;

import java.util.*;

public class Resource extends Vector {
    // Can we somehow define which types a resource has to be here??
    public Resource(String key, Integer val){ super(key,val); }
    public Resource(Map<String, Integer> M) { super(M); }

    @Override
    public Resource Add(IVector x, IVector y) {
        try{
            return add((Resource)x,(Resource)y);
        } catch(ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Vector Zero() {
        return zero();
    }

    public static Resource add(Resource x, Resource y) {
        try{
            HashMap sum = new HashMap();
            sum.putAll(x);
            for (Object k : y.keySet()){
                sum.computeIfPresent(k,
                        (key, val) -> sum.put(k,(Integer)val + (Integer)y.get(k)));
                sum.putIfAbsent(k, y.get(k));
            }
            return new Resource(sum);

        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Resource Mult(IVector x, Integer y) {
        try{
            return mult((Resource)x,y);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            throw new ClassCastException("Could not cast vector to type Resource");
        }
    }

    public static Resource zero(){
        return new Resource(Collections.emptyMap());
    }

    public static Resource mult(Resource x, Integer y) {
        try{
            Map<String, Integer> ret = new HashMap();
            x.entrySet().stream().map(e -> ret.put((String)((Entry)e).getKey(),(Integer)((Entry)e).getValue()*y));
            return new Resource(ret);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            throw new ClassCastException("Could not cast vector to type Resource");
        }
    }

    // It does not make sense to check which resource is "biggest", because of compact resources.
    // Equality is nice to be able to verify though.
    @Override
    public boolean equals(Object o){
        try {
            Resource cmp = (Resource) o;
            // Creating temporary HashMaps to be able to modify them.
            HashMap<String,Integer> tmpA = new HashMap(cmp);
            HashMap<String,Integer> tmpB = new HashMap(this);
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

    /*
         //////////////////////////// Alexanders initial add. //////////////////////////
        if(Stream.of(x, y).allMatch(a -> a.isEmpty())) { throw new IllegalArgumentException("Invalid: Both vectors were null"); }
        if(x.isEmpty()) { return new Resource(y); }
        if(y.isEmpty()) { return new Resource(x); }

        for(Object key : x.keySet()) {
            if(y.containsKey(key)) {
                sum.put(key, ((Integer) y.get(key)) + ((Integer) x.get(key)));
            } else {
                sum.put(key, x.get(key));
            }
        }
        for(Object key : y.keySet()) {
            if(!sum.containsKey(y.get(key))) {
                sum.put(key, y.get(key));
            }
        }
        // maybe check for the size of the sum map before returning?
        return new Resource(sum);
    */

}

package Classes;

import Interfaces.IVector;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Transfer extends Vector<Agent, Resource> {
    public Transfer () { super(Collections.emptyMap()); }
    public Transfer (Agent a, Resource r){
        super(a,r);
    }
    // Check if valid before returning.
    public Transfer (Map<Agent,Resource> M){
        super(M);
    }

    @Override
    public Vector Zero() {
        return zero();
    }

    public static Transfer zero(){
        return new Transfer(Collections.emptyMap());
    }

    @Override
    public IVector Add(IVector x, IVector y) {
        try{
            return add((Transfer)x,(Transfer)y);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public IVector Mult(IVector x, Integer y) {
        try{
            return mult((Transfer)x,y);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Transfer add(Transfer x, Transfer y) {
        try{
            HashMap<Agent,Resource> sum = new HashMap<>();
            sum.putAll(x);
            for (Agent k : y.keySet()){
                sum.computeIfPresent(k,
                        (key, val) -> Resource.add(val, y.get(k)));
                sum.putIfAbsent(k, y.get(k));
            }
            return new Transfer(sum);

        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Transfer mult(Transfer x, Integer y){
        try{
            Map<Agent, Resource> ret = new HashMap<>();
            for (Map.Entry<Agent, Resource> e : x.entrySet()){
                ret.put(e.getKey(),Resource.mult(e.getValue(),y));
            }
            return new Transfer(ret);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Boolean Valid(){
        Resource sum = Resource.zero();
        for (Object o : this.values()){
            sum = Resource.add(sum, (Resource)o);
        }
        return sum.equals(Resource.zero());
    }

 // testing
    @Override
    public boolean equals(Object o){
        try {
            Transfer cmp = (Transfer) o;
            // Creating temporary HashMaps to be able to modify them.
            HashMap<Agent,Resource> tmpA = new HashMap(cmp);
            HashMap<Agent,Resource> tmpB = new HashMap(this);
            // Removing any keys that are zero, since any transfer implicitly transfers 0 resources to all agents not mentioned in the transfer.
            tmpA.entrySet().removeIf(entry -> entry.getValue().equals(Resource.zero()));
            tmpB.entrySet().removeIf(entry -> entry.getValue().equals(Resource.zero()));

            // Verify that the remaining key are the same in both maps, then compare values.
            if (tmpB.keySet().equals(tmpA.keySet())){
                for (Agent k : tmpA.keySet()){
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
}

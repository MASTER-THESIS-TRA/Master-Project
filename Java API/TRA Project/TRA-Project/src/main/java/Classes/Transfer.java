package Classes;

import Interfaces.IVector;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Transfer extends Vector {
    public Transfer (Agent a, Resource r){
        super(a,r);
    }
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
            HashMap<Agent,Resource> sum = new HashMap<Agent, Resource>();
            sum.putAll(x);
            for (Object k : y.keySet()){
                sum.computeIfPresent((Agent)k,
                        (key, val) -> Resource.add((Resource)val, (Resource)y.get(k)));
                sum.putIfAbsent((Agent)k, (Resource)y.get(k));
            }
            return new Transfer(sum);

        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Transfer mult(Transfer x, Integer y){
        try{
            Map<Agent, Resource> ret = new HashMap<Agent, Resource>();
            x.entrySet().stream().map(e -> ret.put((Agent)((Entry)e).getKey(),Resource.mult((Resource)((Entry)e).getValue(),y)));
            return new Transfer(ret);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

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

package Classes;

import Interfaces.IVector;

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
    public IVector add(IVector x, IVector y) {
        try{
            return add((Transfer)x,(Transfer)y);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public IVector mult(IVector x, Integer y) {
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
                        (key, val) -> sum.put((Agent) k,Resource.add((Resource)val, (Resource)y.get(k))));
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
}

package Classes;

import Interfaces.IVector;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// A credit policy is simply a mapping from Agents to the amount of resources they can owe.
// Any agent not present in the mapping is assumed to have no credit.
public class CreditPolicy extends Vector<Agent, Resource>{

    public CreditPolicy(){
        super(Collections.emptyMap());
    }
    public CreditPolicy(Map<Agent,Resource> M){
        super(M);
    }
    public CreditPolicy(Agent a,Resource r){
        super(a,r);
    }

    @Override
    public CreditPolicy Zero() {
        return new CreditPolicy(Collections.EMPTY_MAP);
    }

    @Override
    public CreditPolicy Add(IVector x, IVector y) {
        try{
            return add((CreditPolicy) x,(CreditPolicy) y);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static CreditPolicy add(CreditPolicy x, CreditPolicy y){
        try{
            HashMap<Agent,Resource> sum = new HashMap<>();
            sum.putAll(x);
            for (Agent k : y.keySet()){
                sum.computeIfPresent(k,
                        (key, val) -> Resource.add(val, y.get(k)));
                sum.putIfAbsent(k, y.get(k));
            }
            return new CreditPolicy(sum);

        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public CreditPolicy Mult(IVector x, Integer y) {
        try{
            return mult((CreditPolicy) x,y);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static CreditPolicy mult (CreditPolicy x, Integer y){
        try{
            Map<Agent, Resource> ret = new HashMap<>();
            for (Map.Entry<Agent, Resource> e : x.entrySet()){
                ret.put(e.getKey(),Resource.mult(e.getValue(),y));
            }
            return new CreditPolicy(ret);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean Predicate(Transfer t, Transfer ownershipState){
        Transfer sum = Transfer.add(t,ownershipState);
        for (Agent a : sum.keySet()){
            if (this.get(a).containsKey("*")){continue;}
            boolean geq0 = Resource.add(sum.get(a),(this.get(a) != null ? this.get(a) : Resource.zero()))
                    .values().stream().allMatch(val -> val>=0);
            if (!geq0){
                return false;
            }
        }
        return true;
    }
}

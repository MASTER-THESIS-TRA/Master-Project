package dk.diku.TRA.Project.Classes;

import dk.diku.TRA.Project.Interfaces.IVector;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// A credit policy is simply a mapping from Agents to the amount of resources they can owe.
// Any agent not present in the mapping is assumed to have no credit.
public class Credit extends Vector<Agent, Resource>{

    public Credit(){
        super(Collections.emptyMap());
    }
    public Credit(Map<Agent,Resource> M){
        super(M);
    }
    public Credit(Agent a, Resource r){
        super(a,r);
    }

    @Override
    public Credit Zero() {
        return new Credit(Collections.EMPTY_MAP);
    }

    @Override
    public Credit Add(IVector x, IVector y) {
        try{
            return add((Credit) x,(Credit) y);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Credit add(Credit x, Credit y){
        try{
            HashMap<Agent,Resource> sum = new HashMap<>();
            sum.putAll(x);
            for (Agent k : y.keySet()){
                sum.computeIfPresent(k,
                        (key, val) -> Resource.add(val, y.get(k)));
                sum.putIfAbsent(k, y.get(k));
            }
            return new Credit(sum);

        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Credit Mult(IVector x, Integer y) {
        try{
            return mult((Credit) x,y);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Credit mult (Credit x, Integer y){
        try{
            Map<Agent, Resource> ret = new HashMap<>();
            for (Map.Entry<Agent, Resource> e : x.entrySet()){
                ret.put(e.getKey(),Resource.mult(e.getValue(),y));
            }
            return new Credit(ret);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean ValidateTransfer(Transfer t, Transfer ownershipState){
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

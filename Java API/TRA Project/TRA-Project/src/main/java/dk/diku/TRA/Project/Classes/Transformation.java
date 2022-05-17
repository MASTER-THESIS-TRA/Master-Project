package dk.diku.TRA.Project.Classes;

import dk.diku.TRA.Project.Exceptions.TRAException;
import dk.diku.TRA.Project.Interfaces.IVector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Transformation  extends Vector<Agent, Resource> {
    public Transformation() { super(Collections.emptyMap()); }
    // This constructor is solely for testing purposes only, and should not be used anywhere in the application.
    // It is not technically a transformation, as the sum of the resources is not zero.
    public Transformation (Agent a, Resource r){
        super(a,r);
    }

    public Transformation (Map<Agent,Resource> M) throws TRAException {
        super(M); // No check needed here. The resource manager needs to check for weights.
    }

    @Override
    public Vector Zero() {
        return zero();
    }

    public static Transformation zero() {
        Transformation ret;
        try{ret = new Transformation(Collections.emptyMap());}catch(TRAException e){ret = null;/*Do nothing, this can never happen*/}
        return ret;
    }

    @Override
    public Transformation Add(IVector x, IVector y) {
        try{
            return add((Transformation)x,(Transformation)y);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Transformation Mult(IVector x, Integer y) {
        try{
            return mult((Transformation)x,y);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Transformation add(Transformation x, Transformation y) {
        try{
            HashMap<Agent,Resource> sum = new HashMap<>();
            sum.putAll(x);
            for (Agent k : y.keySet()){
                sum.computeIfPresent(k,
                        (key, val) -> Resource.add(val, y.get(k)));
                sum.putIfAbsent(k, y.get(k));
            }
            return new Transformation(sum);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
        } catch (TRAException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Transformation mult(Transformation x, Integer y){
        try{
            Map<Agent, Resource> ret = new HashMap<>();
            for (Map.Entry<Agent, Resource> e : x.entrySet()){
                ret.put(e.getKey(),Resource.mult(e.getValue(),y));
            }
            return new Transformation(ret);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
        }catch (TRAException e){
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

    // Utility functions
    @Override
    public boolean equals(Object o){
        try {
            Transformation cmp = (Transformation) o;
            // Creating temporary HashMaps to be able to modify them.
            HashMap<Agent,Resource> tmpA = new HashMap(cmp);
            HashMap<Agent,Resource> tmpB = new HashMap(this);

            // Removing any keys that are zero.
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

    public static Transformation Sum(ArrayList<Transformation> transformationList){
        Transformation sum = Transformation.zero();
        for (Transformation t : transformationList){
            sum = Transformation.add(sum, t);
        }
        return sum;
    }
}

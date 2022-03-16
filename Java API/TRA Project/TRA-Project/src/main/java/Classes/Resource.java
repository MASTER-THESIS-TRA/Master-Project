package Classes;

import Interfaces.IVector;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.*;
import java.util.stream.Stream;

public class Resource extends Vector {

    public Resource(String key, Integer val){ super(key,val); }
    public Resource(Map<String, Integer> M) { super(M); }

    @Override
    public Resource add(IVector x, IVector y) {
        try{
            return add((Resource)x,(Resource)y);
        } catch(ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
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
    public Resource mult(IVector x, Integer y) {
        try{
            return mult((Resource)x,y);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            throw new ClassCastException("Could not cast vector to type Resource");
        }
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




    private HashMap<String, Float> resource;
    public float amount;  // So far, not used. 

    public Resource (){
        resource = new HashMap<>();
    }

    public Resource(HashMap<String, Float> resource) { this.resource = resource; }

    public Resource(String resourceName, float amount) throws TRAException {
        if (resourceName == null){
            throw new TRAException(ExceptionConstants.ILLEGAL_RESOURCE_INIT + " " +amount + " " + resourceName);
        }
        this.resource = new HashMap<>();
        this.resource.put(resourceName, amount);
        this.amount = amount;
    }

    public HashMap<String, Float> getResource() {
        return resource;
    }

    public void add(Resource b){
    //Resource ret = new Resource();
    //ret.resource.putAll(a.resource);
        for (String k : b.resource.keySet()){
            resource.computeIfPresent(k,
                                        (key, val) -> val + b.resource.get(k));
            resource.putIfAbsent(k, b.resource.get(k));
        }
    // Maybe we would like to clear the old resources??? To ensure no accidental duplication?
    // Seems like the way to go about it. All resources can be retrieved from the new one anyways.
    //b.resource.clear();
    }

    public void multItem(String key, float x){
        resource.computeIfPresent(key,
                        (k, value) -> value*x);
    }

    public void multAll(float x){
        resource.replaceAll((key, value)
                -> value * x);
    }

    // It does not make sense to check which resource is "biggest", because of compact resources.
    // Equality is nice to be able to verify though.
    @Override
    public boolean equals(Object o){
        // This is not sufficient! If one resource has a resource type that the other does not have, but it is zero, that is still okay.
        try {
            Resource cmp = (Resource) o;
            HashMap<String, Float> tmpA = (HashMap<String, Float>) resource.clone();
            HashMap<String, Float> tmpB = (HashMap<String, Float>) cmp.resource.clone();
            tmpA.entrySet().removeIf(entry -> entry.getValue() == 0);
            tmpB.entrySet().removeIf(entry -> entry.getValue() == 0);

            if (tmpB.keySet().equals(tmpA.keySet())){
                for (String k : tmpA.keySet()){
                    if (!tmpB.get(k).equals(tmpA.get(k))){
                        tmpA.clear();
                        tmpB.clear();
                        return false;
                    }
                }
                tmpA.clear();
                tmpB.clear();
                return true;
            }
            tmpA.clear();
            tmpB.clear();
            return false;
        } catch (ClassCastException e){
            System.out.println(ExceptionConstants.ILLEGAL_RESOURCE_COMPARISON + " " + o);
            return false;
        }
    }

    public boolean isZero(){
        for (String key : resource.keySet()){
            if (resource.get(key)!= 0){
                return false;
            }
        }
        return true;
    }

    public Resource breakOne(String key, float amount) throws TRAException {
        if(key == null || !this.resource.containsKey(key) || amount < 0) { throw new TRAException("Value is either null or did not exist in the hashmap"); }
        if(this.resource.get(key) < amount) { throw new TRAException(ExceptionConstants.ILLEGAL_BREAKOF + " Key: " + key + " Amount: " + amount); }

        this.resource.computeIfPresent(key, (k, val) -> val-amount);

        Resource breakOf = new Resource(key, amount);
        return breakOf;
    }

    public Resource breakMultiple(HashMap<String, Float> itemsToBreak) throws TRAException {
        Resource breakOff = new Resource();
        for(String key : itemsToBreak.keySet()) {
            try {
                Resource temp = breakOne(key, itemsToBreak.get(key));
                breakOff.resource.putAll(temp.resource);
            } catch (TRAException e) {
                throw new TRAException(ExceptionConstants.ILLEGAL_BREAKOF);
            }
        }
        return breakOff;
    }

    public boolean canAdd(Resource a){
        Resource tmp = new Resource();
        tmp.add(a);
        tmp.add(this);
        if(tmp.getResource().values().stream().anyMatch(x -> x<0)){
            return false;
        }
        return true;
    }*/
}

package Classes;

import Exceptions.ExceptionConstants;
import Exceptions.TRAException;
import org.apache.commons.lang3.NotImplementedException;

import java.io.Console;
import java.util.HashMap;

public class Resource {
    private HashMap<String, Float> resource;
    public float amount;

    public Resource (){
        resource = new HashMap<>();
    }

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
    public Resource breakOne(String key, float amount) throws TRAException {
        throw new NotImplementedException();
        /*if (amount < 0){
            throw new TRAException(ExceptionConstants.ILLEGAL_RESOURCE_ACTION);
        }
        return new Resource();*/
    }
    public Resource breakMultiple(HashMap<String, Float> itemsToBreak) throws TRAException {
        throw new NotImplementedException();
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
}

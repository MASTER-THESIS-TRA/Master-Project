package Classes;

import Exceptions.ExceptionConstants;
import Exceptions.TRAException;
import org.apache.commons.lang3.NotImplementedException;

import java.io.Console;
import java.util.HashMap;

public class Resource {
    public HashMap<String, Float> resource;
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
    public Resource breakOff(String key, float amount) throws TRAException {
        throw new NotImplementedException();
        /*if (amount < 0){
            throw new TRAException(ExceptionConstants.ILLEGAL_RESOURCE_ACTION);
        }
        return new Resource();*/
    }

    // It does not make sense to check which resource is "biggest", because of compact resources.
    // Equality is nice to be able to verify though.
    @Override
    public boolean equals(Object o){
        try {

            Resource cmp = (Resource) o;
            if (cmp.resource.keySet().equals(resource.keySet())){
                for (String k : resource.keySet()){
                    if (!cmp.resource.get(k).equals(resource.get(k))){
                        return false;
                    }
                }
                return true;
            }
            return false;
        } catch (ClassCastException e){
            System.out.println(ExceptionConstants.ILLEGAL_RESOURCE_COMPARISON + " " + o);
            return false;
        }
    }

    public boolean isZero(){
        throw new NotImplementedException();
    }
}

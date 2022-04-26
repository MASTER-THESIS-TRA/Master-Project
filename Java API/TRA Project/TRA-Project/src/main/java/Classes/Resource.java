package Classes;

import Exceptions.ExceptionConstants;
import Exceptions.TRAException;
import Interfaces.IVector;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;

import static java.lang.Integer.parseInt;

public class Resource extends Vector<String, Integer> {
    public Resource(String key, Integer val){ super(key,val); }
    public Resource(Map<String, Integer> M) { super(M); }

    @Override
    public Resource Add(IVector x, IVector y) {
        try{
            return add((Resource)x,(Resource)y);
        } catch(ClassCastException e){
            throw new ClassCastException("Could not cast vector to type Resource");
        }
    }

    @Override
    public Vector Zero() {
        return zero();
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

    public static Resource add(Resource x, Resource y) {
        try{
            HashMap<String, Integer> sum = new HashMap();
            sum.putAll(x);
            for (String k : y.keySet()){
                sum.computeIfPresent(k,
                        (key, val) -> val + y.get(k));
                sum.putIfAbsent(k, y.get(k));
            }
            return new Resource(sum);

        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Resource zero(){
        return new Resource(Collections.emptyMap());
    }

    public static Resource mult(Resource x, Integer y) {
        try{
            Map<String, Integer> ret = new HashMap();
            for (Map.Entry<String, Integer> e : x.entrySet()){
                ret.put(e.getKey(),e.getValue()*y);
            }
            return new Resource(ret);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            throw new ClassCastException("Could not cast vector to type Resource");
        }
    }

    // Utility functions:

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

    public static Resource Sum(ArrayList<Resource> resourceList){
        Resource sum = Resource.zero();
        for (Resource t : resourceList){
            sum = Resource.add(sum, t);
        }
        return sum;
    }


    /**
     * @param resourceString A resource of the format "{type1->amount1,..,typeN->amountN}".
     * @return The resource, represented by the input string.
     */
    public static Resource ParseStringToResource(String resourceString){
        if (resourceString.equals("{}")){
            return Resource.zero();
        }
        try {
            String noBrackets = removeBrackets(resourceString);
            List<String> resourceStrings = Arrays.asList(noBrackets.split(","));
            Resource ret = Resource.zero();
            for (String resource : resourceStrings) {
                String[] res = resource.split("->");
                String type = res[0];
                String amount = res[1];
                ret = add(ret, new Resource(type, parseInt(amount)));
            }
            return ret;
        }catch (Exception e){
            System.out.println("Unexpected exception. The provided string: \n\""+ resourceString+"\"\nis likely the wrong format for resource.");
            return null;
        }
    }

    /**
     *
     * @param inpStr Assumes a Resource String: "{...}".
     * @return The input string, without the surrounding brackets.
     * @throws TRAException If there are no surrounding brackets, it cannot be of the correct format.
     */
    private static String removeBrackets(String inpStr)throws TRAException{
        if (inpStr.charAt(0)=='{' && inpStr.charAt(inpStr.length()-1)=='}'){
            return inpStr.substring(1,inpStr.length()-1);
        }
        else{
            throw new TRAException(ExceptionConstants.GENERIC_ERROR);
        }
    }

    /**
     * @param res The resource to be written in string format.
     * @return The resource written in the format "{type1->amount1,..,typeN->amountN}"
     */
    public static String ToString(Resource res){
        if (res.equals(Resource.zero())){
            return "{}";
        }
        String ret = "{";
        for (String type : res.keySet()){
            ret = ret + type + "->"+res.get(type).toString() + ",";
        }

        return ret.substring(0,ret.length()-1)+"}";
    }
}

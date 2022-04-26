package Classes;

import Exceptions.ExceptionConstants;
import Exceptions.TRAException;
import Interfaces.IVector;

import java.util.*;

public class Transfer extends Vector<Agent, Resource> {
    public Transfer () { super(Collections.emptyMap()); }
    // This constructor is solely for testing purposes only, and should not be used anywhere in the application.
    // It is not technically a transfer, as the sum of the resources is not zero.
    public Transfer (Agent a, Resource r){
        super(a,r);
    }
    // Check if valid before returning.
    public Transfer (Map<Agent,Resource> M) throws TRAException {
        super(M);
        Resource sum = Resource.zero();
        for (Resource r : M.values()){
            sum = Resource.add(sum,r);
        }
        if (!sum.equals(Resource.zero())){
            throw new TRAException(ExceptionConstants.ILLEGAL_TRANSFER);
        }
    }

    @Override
    public Vector Zero() {
            return zero();
    }

    public static Transfer zero() {
        Transfer ret;
        try{ret = new Transfer(Collections.emptyMap());}catch(TRAException e){ret = null;/*Do nothing, this can never happen*/}
        return ret;
    }

    @Override
    public Transfer Add(IVector x, IVector y) {
        try{
            return add((Transfer)x,(Transfer)y);
        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Transfer Mult(IVector x, Integer y) {
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
        } catch (TRAException e){
            System.out.println(e.getMessage());
        }
        return null;
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

    public static Transfer Sum(ArrayList<Transfer> transferList){
        Transfer sum = Transfer.zero();
        for (Transfer t : transferList){
            sum = Transfer.add(sum, t);
        }
        return sum;
    }

    /**
     * @param transferString A resource of the format "{type1->amount1,..,typeN->amountN}".
     * @param manager The resource manager responsible for the transfer (i.e. the one who can find the agents involved in its ownership state).
     * @return The resource, represented by the input string.
     */
    public static Transfer ParseStringToTransfer(String transferString, ResourceManager manager){
        if (transferString.equals("{}")){
            return Transfer.zero();
        }
        try {
            String noBrackets = removeBrackets(transferString);
            List<String> transferStrings = Arrays.asList(noBrackets.split("},"));
            Map<Agent,Resource> m = new HashMap<>();
            for (String transfer : transferStrings) {
                String[] res = transfer.split("->\\{");
                String agentId = res[0];
                String resource = "{"+res[1];
                if (resource.charAt(resource.length()-1)!='}'){
                    resource = resource+"}";
                }
                m.put(manager.findAgentById(UUID.fromString(agentId)),Resource.ParseStringToResource(resource));
            }
            return new Transfer(m);
        }catch (Exception e){
            System.out.println(e.getMessage() +"\nThe provided string: \n\""+ transferString+"\"\nis likely the wrong format for transfer.");
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
     * @param trans The transfer to be written in string format.
     * @return The transfer written in the format "{agent1->resource1,..,agentN->resourceN}", where a resource is on the form "{type1->amount1,..,typeN->amountN}"
     */
    public static String ToString(Transfer trans){
        if (trans.equals(Transfer.zero())){
            return "{}";
        }
        String ret = "{";
        for (Agent a : trans.keySet()){
            ret = ret + a.getUuid() + "->" + Resource.ToString(trans.get(a)) + ",";
        }
        return ret.substring(0,ret.length()-1)+"}";
    }
}

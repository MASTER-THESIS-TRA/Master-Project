package Classes;

import Exceptions.ExceptionConstants;
import Exceptions.TRAException;

import java.util.Map;

public class ResourceManager extends Agent{
    Transfer Ownerships;
    public ResourceManager(String name){
        super(name);
    }
    public ResourceManager(String name, Map<Agent,Resource> M){
        super(name);
        Transfer debt = Transfer.zero();
        Ownerships = Transfer.add(new Transfer(M),new Transfer());
    }
    /*
    public HashMap<Agent,Resource> ownerships;

    public ResourceManager(){
        ownerships = new HashMap<>();
    }

    public void addAgent (Agent a) throws TRAException {
        if (a == null){
            throw new TRAException(ExceptionConstants.GENERIC_ERROR + " agent " + a + " is null.");
        }
        if (ownerships.containsKey(a)){
            throw new TRAException(ExceptionConstants.GENERIC_ERROR + " agent " + a + " already exists.");
        }
        ownerships.put(a, new Resource()); // When adding a new agent, do so with a fresh account (i.e. an empty resource).
    }

    public boolean verifyInternalTransfer(Transfer t){ // More complicated with other resource managers.
        for (Agent a : t.transfers.keySet()){
            if (!ownerships.get(a).canAdd(t.transfers.get(a))){
                return false;
            }
        }
        return true;
    }

    public void completeInternalTransfer(Transfer t) throws TRAException {
        if (!verifyInternalTransfer(t) || !(t.validTransfer())){
            throw new TRAException(ExceptionConstants.GENERIC_ERROR);
        }
        else{
            for (Agent a : t.transfers.keySet()){
                ownerships.get(a).add(t.transfers.get(a));
            }
        }
    }
*/
}



package Classes;

import Exceptions.ExceptionConstants;
import Exceptions.TRAException;

import java.util.HashMap;
import java.util.List;

public class Transfer {
    HashMap<Agent, Resource> transfers;

    public Transfer (List<Agent> parties, List<Resource> toTransfer) throws TRAException {
        if (parties.size() != toTransfer.size()){
            throw new TRAException(ExceptionConstants.ILLEGAL_TRANSFER + ", parties and resources must match in length");
        }
        transfers = new HashMap<>();
        for (int i = 0; i< parties.size(); i++){
            transfers.put(parties.get(i), toTransfer.get(i));
        }
    }

    public boolean Validtransfer(){
        boolean ret = false;
        Resource checker = new Resource();
        for (Agent a : transfers.keySet()){
            checker.add(transfers.get(a));
        }
        if (checker.equals(new Resource())){
            ret = true;
        }
        return ret;
    }
}

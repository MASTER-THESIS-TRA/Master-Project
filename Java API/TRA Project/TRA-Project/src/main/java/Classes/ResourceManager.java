package Classes;

import Exceptions.TRAException;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager extends Agent{
    private Transfer ownerships = new Transfer(); // Ownership state can be represented as a transfer, where the ResourceManager transfers everyone their resources.

    public ResourceManager(String name){
        super(name);
    }

    public ResourceManager(String name, Map<Agent,Resource> M){
        super(name);
        Resource debt = Resource.zero();

        for (Resource r : M.values()){
            debt = Resource.add(debt, Resource.mult(r,-1));
        }
        M.put(this,debt);
        try { ownerships = new Transfer(M); } catch (TRAException e){ }
    }

    public ResourceManager(ResourceManager a, ResourceManager b){
        super(a.name + " and " + b.name);
        // And then add the ownerships of the two ResourceManagers together.
    }

    public void AddAgent(Agent a){
        ownerships = Transfer.add(ownerships, new Transfer(a,Resource.zero()));
    }

    public boolean AddAgent(Agent a, Resource initialBalance) throws TRAException {
        if(this.ownerships.keySet().stream().anyMatch(x -> x == a)) {
            return false;
        }
        HashMap<Agent,Resource> balance = new HashMap<>();
        balance.put(a,initialBalance);
        balance.put(this,Resource.mult(initialBalance,-1));
        ownerships = Transfer.add(ownerships, new Transfer(balance));
        return true;
    }

    public boolean apply(Transfer t){
        // Enten smid en exception eller læg transfer til ownership.
        // Kun manageren må have negativ konto.
        // Check credit limit policy.

        throw new NotImplementedException();
    }

    public Transfer getOwnerships() {
        return ownerships;
    }

    private boolean checkCreditPolicy(){
        throw new NotImplementedException();
    }
}



package Classes;

import Exceptions.TRAException;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager extends Agent{
    private Transfer ownerships; // Ownership state can be represented as a transfer, where the ResourceManager transfers everyone their resources.
    private CreditPolicy CP;

    public ResourceManager(String name){
        super(name);
        CP = new CreditPolicy(this,new Resource("*",1));
    }

    public ResourceManager(String name, Map<Agent,Resource> M){
        super(name);
        Resource debt = Resource.zero();
        for (Resource r : M.values()){
            debt = Resource.add(debt, Resource.mult(r,-1));
        }
        M.put(this,debt);
        CP = new CreditPolicy(this,new Resource("*",1));
        try { ownerships = new Transfer(M); } catch (TRAException e){ }
    }

    // What Fritz??
    public ResourceManager(ResourceManager a, ResourceManager b){
        super(a.getName() + " and " + b.getName());
        throw new NotImplementedException();
        // And then add the ownerships of the two ResourceManagers together.
    }

    // Initially an agent has no
    public void AddAgent(Agent a){
        ownerships = Transfer.add(ownerships, new Transfer(a,Resource.zero()));
    }

    public boolean AddAgent(Agent a, Resource initialBalance) {
        if(this.ownerships.containsKey(a)){
            return false;
        }
        HashMap<Agent,Resource> balance = new HashMap<>();
        balance.put(a,initialBalance);
        balance.put(this,Resource.mult(initialBalance,-1));
        try{ ownerships = Transfer.add(ownerships, new Transfer(balance)); }
        catch (TRAException e) { System.out.println(e.getMessage()); return false;} // This will never happen.
        return true;
    }

    public boolean Apply(Transfer t){
        if (CP.ValidateTransfer(t, ownerships)){
            ownerships = Transfer.add(ownerships, t);
            return true;
        }
        return false;
    }

    @Async
    public boolean TwoPhaseCommit(Transfer t){
        throw new NotImplementedException();
    }

    public Transfer GetOwnerships() {
        return ownerships;
    }

    private void GiveCredit(Agent a, Resource r){
        CP = CreditPolicy.add(new CreditPolicy(a,r),CP);
    }
}



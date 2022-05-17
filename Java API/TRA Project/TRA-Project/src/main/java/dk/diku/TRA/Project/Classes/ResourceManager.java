package dk.diku.TRA.Project.Classes;

import dk.diku.TRA.Project.Exceptions.ExceptionConstants;
import dk.diku.TRA.Project.Exceptions.TRAException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Primary
public class ResourceManager extends Agent{
    private Transfer ownerships; // Ownership state can be represented as a transfer, where the ResourceManager transfers everyone their resources.
    private Credit CP;
    private Weight weights;

    public ResourceManager(String name){
        super();
        ownerships = Transfer.zero();
        CP = new Credit(this,new Resource("*",1));
    }

    public ResourceManager(String name, Map<Agent,Resource> M){
        super();
        Resource debt = Resource.zero();
        for (Resource r : M.values()){
            debt = Resource.add(debt, Resource.mult(r,-1));
        }
        M.put(this,debt);
        CP = new Credit(this,new Resource("*",1));
        try { ownerships = new Transfer(M); } catch (TRAException e){ }
    }
    public ResourceManager(String name, Map<Agent,Resource> M, Map<String, Double> W){
        super();
        Resource debt = Resource.zero();
        for (Resource r : M.values()){
            debt = Resource.add(debt, Resource.mult(r,-1));
        }
        M.put(this,debt);
        CP = new Credit(this,new Resource("*",1));
        weights = new Weight(W);
        try { ownerships = new Transfer(M); } catch (TRAException e){ }
    }

    // Initially an agent has no
    public boolean AddAgent(Agent a){
        if (ownerships.keySet().contains(a)){
            return false;
        }
        ownerships = Transfer.add(ownerships, new Transfer(a,Resource.zero()));
        return true;
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

    public boolean ApplyTransfer(Transfer t){
        if (CP.ValidateTransfer(t, ownerships)){ // Check if everyone involved can afford the transaction (per the credit policy).
            ownerships = Transfer.add(ownerships, t);
            return true;
        }
        return false;
    }

    public boolean ApplyTransform(Transformation t){
        if (checkWeightSum(t)){  // Check if the transaction does not introduce more weight.
            Transfer t_prime = transformToTransfer(t);
            if (CP.ValidateTransfer(t_prime, ownerships)){  // Check if everybody involved can afford the transaction.
                ownerships = Transfer.add(ownerships, transformToTransfer(t));
                return true;
            }
        }
        return false;
    }

    private boolean checkWeightSum(Transformation t){
        Double sum = 0.0;
        for (Resource r : t.values()){
            for (Map.Entry<String, Integer> e : r.entrySet()){
                sum += weights.get(e.getKey())*e.getValue();
            }
        }
        return sum == 0.0;
    }

    // This method handles the logic behind a manager "owing" the customers their resources.
    // In essence the manager is paying back the consumed resources and now owes the produces resources.
    private Transfer transformToTransfer(Transformation t){
        Resource toManager = Resource.zero();
        for (Resource r : t.values()){
            toManager = Resource.add(toManager,Resource.mult(r,-1));
        }
        HashMap M = new HashMap<>(t);
        M.put(this,toManager);
        try{
            return new Transfer(M);
        } catch (TRAException e){
            // This will not happen, as we are ensuring above, that the sum is always 0.
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Transfer GetOwnerships() {
        return ownerships;
    }

    private void GiveCredit(Agent a, Resource r){
        CP = Credit.add(new Credit(a,r),CP);
    }

    public Agent findAgentById(UUID id) throws TRAException {
        for (Agent a : ownerships.keySet()){
            if (a.getUuid().equals(id)){
                return a;
            }
        }
        throw new TRAException(ExceptionConstants.GENERIC_ERROR + ": Agent not found.");
    }
}




    /*// What Fritz??
    public ResourceManager(ResourceManager a, ResourceManager b){
        super(a.getName() + " and " + b.getName());
        throw new NotImplementedException();
        // And then add the ownerships of the two ResourceManagers together.
    }

    @Async
    public boolean TwoPhaseCommit(Transfer t){
        throw new NotImplementedException();
    }*/

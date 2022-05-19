package dk.diku.TRA.Project.Classes;

import dk.diku.TRA.Project.Dtos.CreditDto;
import dk.diku.TRA.Project.Dtos.OwnershipDto;
import dk.diku.TRA.Project.Dtos.keys.OwnershipKey;
import dk.diku.TRA.Project.Exceptions.ExceptionConstants;
import dk.diku.TRA.Project.Exceptions.TRAException;
import dk.diku.TRA.Project.repository.AgentRepository;
import dk.diku.TRA.Project.repository.CreditRepository;
import dk.diku.TRA.Project.repository.OwnershipRepository;
import net.bytebuddy.description.modifier.Ownership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Primary
public class ResourceManager extends Agent{
    @Autowired
    OwnershipRepository ownershipRepository;
    @Autowired
    CreditRepository creditRepository;
    @Autowired
    AgentRepository agentRepository;

    private Transfer ownerships; // Ownership state can be represented as a transfer, where the ResourceManager transfers everyone their resources.
    private Credit CP;
    private Weight weights;

    public ResourceManager (String name, String email, String password){
        this(name);
        setEmail(email);
        setPassword(password);
        // Need some fixing for these two. Probably configuration issue. Repos seem to be null.
        LoadOwnershipsFromDb();
        LoadCreditFromDb();
    }

    public ResourceManager(String name){
        super();
        super.setName(name);
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

    private void LoadCreditFromDb(){
        if (creditRepository == null) {
            return;
        }
        List<CreditDto> credits = creditRepository.findAll();
        if (credits.isEmpty()){return;}
        for (CreditDto c : credits){
            GiveCredit(agentRepository.findById(c.getAgentId()).get(),new Resource(c.getResourceType(),c.getAmount()));
        }
    }

    private void LoadOwnershipsFromDb(){
        if (ownershipRepository == null) {
            return;
        }
        List<OwnershipDto> ownerships = ownershipRepository.findAll();
        if (ownerships.isEmpty()){return;}
        Map<Agent,Resource> M = new HashMap<>();
        for (OwnershipDto o : ownerships){
            Agent a = agentRepository.findById(o.getAgentId()).get();
            if (M.containsKey(a)){
                M.put(a, Resource.add(M.get(a),new Resource(o.getResourceType(),o.getAmount())));
                M.put(this, Resource.add(M.get(this),new Resource(o.getResourceType(),-o.getAmount())));
            }
            else{
                M.put(a,new Resource(o.getResourceType(),o.getAmount()));
                M.put(this,new Resource(o.getResourceType(),-o.getAmount()));
            }
        }
    }
    public void getById(){
        ownershipRepository.getById(new OwnershipKey("agentid","Resourcetype"));
    }
}
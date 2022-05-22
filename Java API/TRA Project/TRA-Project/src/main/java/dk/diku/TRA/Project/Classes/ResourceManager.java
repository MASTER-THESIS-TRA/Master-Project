package dk.diku.TRA.Project.Classes;

import dk.diku.TRA.Project.Dtos.CreditDto;
import dk.diku.TRA.Project.Dtos.OwnershipDto;
import dk.diku.TRA.Project.Dtos.WeightDto;
import dk.diku.TRA.Project.Dtos.keys.OwnershipKey;
import dk.diku.TRA.Project.Exceptions.ExceptionConstants;
import dk.diku.TRA.Project.Exceptions.TRAException;
import dk.diku.TRA.Project.repository.AgentRepository;
import dk.diku.TRA.Project.repository.CreditRepository;
import dk.diku.TRA.Project.repository.OwnershipRepository;
import dk.diku.TRA.Project.repository.WeightRepository;
import net.bytebuddy.description.modifier.Ownership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Primary
public class ResourceManager extends Agent{
    OwnershipRepository ownershipRepository;
    CreditRepository creditRepository;
    AgentRepository agentRepository;
    WeightRepository weightRepository;
    /*  Need to persist information about weights as well.
    @Autowired
    ResourceTypeRepository resourceTypeRepository;
     */

    private Transfer ownerships; // Ownership state can be represented as a transfer, where the ResourceManager transfers everyone their resources.
    private Credit CP;
    private Weight weights;

    public ResourceManager (String name, String email, String password){
        this(name);
        setEmail(email);
        setPassword(password);
        // Need some fixing for these two. Probably configuration issue. Repos seem to be null.
    }

    public ResourceManager(String name){
        super();
        super.setName(name);
        ownerships = Transfer.zero();
        CP = new Credit(this,new Resource("*",1));
        weights = Weight.zero();
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
    public ResourceManager(String name, Map<Agent,Resource> M, Map<String, Integer> W){
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

    public void initRepos(AgentRepository a, OwnershipRepository o, CreditRepository c, WeightRepository w){
        agentRepository = a;
        ownershipRepository = o;
        creditRepository = c;
        weightRepository = w;
        LoadOwnershipsFromDb();
        LoadCreditFromDb();
        LoadWeightsFromDb();
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
            saveChangesToDb(t);
            return true;
        }
        return false;
    }

    public boolean ApplyTransform(Transformation t){
        if (checkWeightSum(t)){  // Check if the transaction does not introduce more weight.
            Transfer t_prime = transformToTransfer(t);
            if (CP.ValidateTransfer(t_prime, ownerships)){  // Check if everybody involved can afford the transaction.
                ownerships = Transfer.add(ownerships, t_prime);
                saveChangesToDb(t_prime);
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
    // In essence the manager is paying back the consumed resources and now owes the produced resources.
    private Transfer transformToTransfer(Transformation t){
        Resource toManager = Resource.zero();
        for (Resource r : t.values()){
            toManager = Resource.add(toManager,Resource.mult(r,-1));
        }
        HashMap<Agent,Resource> M = new HashMap<>(t);
        M.put(this,toManager);
        try{
            return new Transfer(M);
        } catch (TRAException e){
            // This will not happen, as we are ensuring above, that the sum is always 0.
            System.out.println(e.getMessage());
            return Transfer.zero();
        }
    }

    public Transfer GetOwnerships() {
        return ownerships;
    }

    private void GiveCredit(Agent a, Resource r){
        CP = Credit.add(new Credit(a,r),CP);
        creditRepository.save(new CreditDto(a.getUuid(),CP.get(a).toString()));
    }

    public Agent findAgentById(String id) {
        for (Agent a : ownerships.keySet()){
            if (a.getUuid().equals(id)){
                return a;
            }
        }
        return null;
    }

    private void LoadCreditFromDb(){
        List<CreditDto> credits = creditRepository.findAll();
        if (credits.isEmpty()){return;}
        for (CreditDto c : credits){
            GiveCredit(agentRepository.findById(c.getAgentId()).get(),new Resource(Resource.ParseStringToResource(c.getResource())));
        }
    }

    private void LoadWeightsFromDb(){
        List<WeightDto> weightDtos = weightRepository.findAll();
        weights = Weight.zero();
        if (weightDtos.isEmpty()){return;}
        for (WeightDto w : weightDtos){
            weights = Weight.add(weights,new Weight(w.getResourceType(),w.getWeight()));
        }
    }

    private void LoadOwnershipsFromDb(){
        List<OwnershipDto> owns = ownershipRepository.findAll();
        Resource bankDebt = Resource.zero();
        if (owns.isEmpty()){return;}
        Map<Agent,Resource> M = new HashMap<>();
        for (OwnershipDto o : owns){
            M.put(agentRepository.findById(o.getAgentId()).get(),Resource.ParseStringToResource(o.getResource()));
            bankDebt = Resource.add(bankDebt,Resource.mult(Resource.ParseStringToResource(o.getResource()),-1));
        }
        M.put(this,bankDebt);
        try {
            ownerships = new Transfer(M);
        } catch (TRAException e){
            ownerships = new Transfer();
        }
    }

    public boolean ValidateTransform(Resource r){
        double sum = 0;
        for (Map.Entry<String,Integer> e : r.entrySet()){
            sum += weights.get(e.getKey())*e.getValue();
        }
        return sum<=0;
    }
    public void AddWeight(Weight w){
        weights = Weight.add(weights,w);
        for (String type : w.keySet()){
            weightRepository.save(new WeightDto(type,(int)w.get(type)));
        }
    }

    public Resource GetBalance(Agent a){
        Agent agent = findAgentById(a.getUuid());
        if(agent == null) {
            return Resource.zero();
        }
        return ownerships.get(agent);
    }

    public void saveChangesToDb(Transfer t){
        // ownership repository
        for (Agent a : t.keySet()){
            if (a.getUuid().equals(this.getUuid())){
                continue;
            }
            saveAgentToDb(a,ownerships.get(a));
        }
    }

    // This should probably be a batchjob, to allow for rolling back on error.
    public void saveAgentToDb(Agent a, Resource r){
        ownershipRepository.save(new OwnershipDto(a.getUuid(),r.toString()));
    }
}
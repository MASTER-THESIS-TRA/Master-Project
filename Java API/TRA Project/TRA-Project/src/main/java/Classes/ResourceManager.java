package Classes;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager extends Agent{
    Transfer ownerships; // Ownership state can be represented as a transfer, where the ResourceManager transfers everyone their resources.
    public ResourceManager(String name){
        super(name);
    }
    public ResourceManager(String name, Map<Agent,Resource> M){
        super(name);
        Transfer debt = Transfer.zero();
        ownerships = new Transfer(M);
        for (Object r : ownerships.values()){
            debt = Transfer.add(debt,
                                new Transfer(this,Resource.mult((Resource) r,-1)));
        }
        ownerships = Transfer.add(ownerships,debt);
    }

    public void AddAgent(Agent a){
        ownerships = Transfer.add(ownerships, new Transfer(a,Resource.zero()));
    }
    public void AddAgent(Agent a, Resource initialBalance){
        HashMap<Agent,Resource> balance = new HashMap<>();
        balance.put(a,initialBalance);
        balance.put(this,Resource.mult(initialBalance,-1));
        ownerships = Transfer.add(ownerships, new Transfer(balance));
    }
}



package Classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ownership {
    public HashMap<Agent,Resource> ownerships;

    public Ownership(){
        ownerships = new HashMap<>();
    }
    public Ownership(Agent a, Resource r){
        ownerships = new HashMap<>();
        ownerships.put(a,r);
    }
    public Ownership(List<Ownership> ownershipList){
        for (Ownership o : ownershipList){
            ownerships.putAll(o.ownerships);
        }
    }

    public void updateResource (Agent a, Resource r){
        ownerships.get(a).add(r);
    }

}

package Classes;

import org.apache.commons.lang3.NotImplementedException;

public class Agent {

    public String name;

    public Agent(String name) {
        this.name = name;
    }

    public boolean canAfford(Resource r){
        throw new NotImplementedException();
    }
}

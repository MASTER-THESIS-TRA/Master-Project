package Classes;

import Interfaces.IAgent;

public class Agent implements IAgent {

    public String name;

    public Agent(String name) {
        this.name = name;
    }

    @Override
    public void sign(Event event) {
    }
}

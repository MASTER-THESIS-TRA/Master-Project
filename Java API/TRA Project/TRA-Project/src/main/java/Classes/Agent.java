package Classes;

import Exceptions.ExceptionConstants;
import Exceptions.TRAException;
import org.apache.commons.lang3.NotImplementedException;

import java.util.UUID;

public class Agent {

    public String name;
    private final UUID uuid;

    public Agent(String name){
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    // @Override
    public void sign(Event event) {
    }
}

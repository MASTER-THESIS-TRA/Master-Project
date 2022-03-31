package Classes;

import Exceptions.ExceptionConstants;
import Exceptions.TRAException;
import org.apache.commons.lang3.NotImplementedException;

import java.net.URL;
import java.util.UUID;

public class Agent {

    private String name;
    private final UUID uuid;
    private Address address;
    private String picture;
    private String email;
    private String agentType;

    public Agent(String name){
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public void sign(Event event) {
    }
}

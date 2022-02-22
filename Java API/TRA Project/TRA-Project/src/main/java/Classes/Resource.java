package Classes;

import Interfaces.IResource;

public class Resource implements IResource {
    public String resourceName;
    public Enum units;
    public float amount;
    public float weight;
    private Location location;

    public Resource(String resourceName, Enum units, float amount, float weight, Location location) {
        this.resourceName = resourceName;
        this.units = units;
        this.amount = amount;
        this.weight = weight;
        this.location = location;
    }

    public void transfer() {

    }

    public Location getLocation() { return this.location; }

}

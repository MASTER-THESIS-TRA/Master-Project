package Classes;

public class Location {

    public String locationName;
    public Address address;
    public GeoCoordinate geoCoordinate;

    public Location(String locationName, Address address, GeoCoordinate geoCoordinate) {
        this.locationName = locationName;
        this.address = address;
        this.geoCoordinate = geoCoordinate;
    }
}

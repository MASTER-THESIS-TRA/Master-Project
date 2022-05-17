package dk.diku.TRA.Project.Classes;

public class GeoCoordinate {

    public double longitude, latitude;

    public GeoCoordinate(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString(){
        return "("+longitude + ", " + latitude + ")";
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}

package dk.diku.TRA.Project.Services;


import dk.diku.TRA.Project.Classes.GeoCoordinate;
import dk.diku.TRA.Project.Classes.Location;
import dk.diku.TRA.Project.Dtos.LocationDto;
import dk.diku.TRA.Project.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<GeoCoordinate> GetAllLocations() {
        return locationRepository.findAll();
    }

    public String SaveLocaition(String name, double longitude, double latitude) {

        GeoCoordinate geoCoordinate = new GeoCoordinate();
        geoCoordinate.setName(name);
        geoCoordinate.setLongitude(longitude);
        geoCoordinate.setLatitude(latitude);

        locationRepository.save(geoCoordinate);
        return "success!";
    }
}

package dk.diku.TRA.Project.repository;

import dk.diku.TRA.Project.Classes.GeoCoordinate;
import dk.diku.TRA.Project.Dtos.LocationDto;
import dk.diku.TRA.Project.Dtos.OwnershipDto;
import dk.diku.TRA.Project.Dtos.keys.OwnershipKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LocationRepository  extends JpaRepository<GeoCoordinate, String> {

    @Query(value = "select count(name) from ResourceType where name = :name")
    int countByName(@Param("name") String name);
}

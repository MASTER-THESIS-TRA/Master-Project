package dk.diku.TRA.Project.repository;

import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Classes.Resource;
import dk.diku.TRA.Project.Dtos.ResourceTypeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResourceRepository extends JpaRepository<ResourceTypeDto, String> {

    /*
    @Modifying
    @Query(value = "INSERT ResourceTypeDto set name = :newName")
    void changeName(@Param("newName") String newName, @Param("id") String id);

     */
}

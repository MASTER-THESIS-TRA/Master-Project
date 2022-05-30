package dk.diku.TRA.Project.repository;

import dk.diku.TRA.Project.Classes.ResourceType;
import dk.diku.TRA.Project.Dtos.ResourceTypeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ResourceTypeRepository extends JpaRepository<ResourceType, String> {
    @Modifying
    @Query(value = "delete from ResourceType where name = :name")
    void deleteByName(@Param("name") String name);
    @Query(value = "select count(name) from ResourceType where name = :name")
    int countByName(@Param("name") String name);
}

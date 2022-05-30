package dk.diku.TRA.Project.repository;

import dk.diku.TRA.Project.Dtos.PersistStateless.TransformDefinitions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TransformRepository extends JpaRepository<TransformDefinitions,String> {
    @Query(value = "select * from transform where name = :name", nativeQuery = true)
    void getByName(@Param("name") String newName);
}

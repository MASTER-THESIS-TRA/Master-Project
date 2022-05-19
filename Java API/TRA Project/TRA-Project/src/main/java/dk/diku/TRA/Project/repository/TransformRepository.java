package dk.diku.TRA.Project.repository;

import ch.qos.logback.core.helpers.Transform;
import dk.diku.TRA.Project.Dtos.PersistStateless.PersistTransform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.processing.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface TransformRepository extends JpaRepository<PersistTransform,String> {
    @Query(value = "select t from PersistTransform t where t.name = :name")
    void getByName(@Param("name") String newName);
}

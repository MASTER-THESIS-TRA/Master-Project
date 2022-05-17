package dk.diku.TRA.Project.repository;

import dk.diku.TRA.Project.Classes.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AgentRepository extends JpaRepository<Agent, String> {

    @Modifying
    @Query(value = "INSERT INTO agents a (:name, :email)")
    void addAgent(@Param("name") String name, @Param("email") String email);
}

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
    @Query(value = "UPDATE Agent set name = :newName where uuid = :id")
    void changeName(@Param("newName") String newName, @Param("id") String id);

    @Query(value= "SELECT a FROM Agent a WHERE a.email = :email")
    Agent findAgentByEmail(@Param("email") String email);
}

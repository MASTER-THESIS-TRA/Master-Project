package dk.diku.TRA.Project.repository;

import dk.diku.TRA.Project.Dtos.CreditDto;
import dk.diku.TRA.Project.Dtos.OwnershipDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CreditRepository extends JpaRepository<CreditDto, String> {
    @Modifying
    @Query(value= "update CreditDto set resource = :resource where agentId = :agentId")
    void updateExistingById(@Param("agentId") String agentId, @Param("resource") String resource);

    @Modifying
    @Query(value = "INSERT INTO credit (agent_id, resource) VALUES (:agentId,:resource)", nativeQuery = true)
    void insertNewById(@Param("agentId") String agentId, @Param("resource") String resource);

    @Query(value = "SELECT * FROM credit WHERE agent_id = :agentId", nativeQuery = true)
    CreditDto getByAgentId(@Param("agentId") String agentId);
}

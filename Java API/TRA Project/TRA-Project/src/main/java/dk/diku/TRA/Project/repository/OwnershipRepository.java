package dk.diku.TRA.Project.repository;

import dk.diku.TRA.Project.Dtos.OwnershipDto;
import dk.diku.TRA.Project.Dtos.keys.OwnershipKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OwnershipRepository extends JpaRepository<OwnershipDto, OwnershipKey> {
    @Modifying
    @Query(value= "update OwnershipDto set amount = :val where agentId = :agentId and resourceType = :type")
    public void updateExistingById(@Param("agentId") String agentId, @Param("type") String type, @Param("val") int val);

    @Modifying
    @Query(value = "INSERT INTO ownerships (agent_id, resource_type, amount) VALUES (:agentId,:type,:val)", nativeQuery = true)
    public void insertNewById(@Param("agentId") String agentId, @Param("type") String type, @Param("val") int val);

    @Query(value = "SELECT * FROM ownerships WHERE agent_id = :agentId", nativeQuery = true)
    public List<OwnershipDto> getByAgentId(@Param("agentId") String agentId);
}

package dk.diku.TRA.Project.repository;

import dk.diku.TRA.Project.Classes.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface EventRepository extends JpaRepository<Event, String> {
    @Query(value="SELECT e from Event e where e.agentId = :agentId")
    public List<Event> FindAllByAgentId(@Param("agentId") String agentId);
}

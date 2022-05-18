package dk.diku.TRA.Project.repository;

import dk.diku.TRA.Project.Dtos.CreditDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CreditRepository extends JpaRepository<CreditDto, String> {
}

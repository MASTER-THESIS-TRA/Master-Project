package dk.diku.TRA.Project.repository;

import dk.diku.TRA.Project.Dtos.WeightDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface WeightRepository extends JpaRepository<WeightDto,String> {
}

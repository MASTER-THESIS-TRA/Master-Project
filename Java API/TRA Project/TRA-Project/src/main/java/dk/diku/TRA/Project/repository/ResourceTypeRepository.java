package dk.diku.TRA.Project.repository;

import dk.diku.TRA.Project.Classes.ResourceType;
import dk.diku.TRA.Project.Dtos.ResourceTypeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ResourceTypeRepository extends JpaRepository<ResourceType, String> {
}

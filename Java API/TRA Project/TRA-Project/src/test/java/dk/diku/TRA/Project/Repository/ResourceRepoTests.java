package dk.diku.TRA.Project.Repository;

import dk.diku.TRA.Project.Classes.ResourceType;
import dk.diku.TRA.Project.repository.ResourceTypeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;



@RunWith(SpringRunner.class)
@DataJpaTest
public class ResourceRepoTests {

    @Autowired
    private ResourceTypeRepository resourceTypeRepository;

    @BeforeEach
    public void DeleteDataInDatabase() {
        resourceTypeRepository.deleteAll();
    }

    @Test
    public void CreateNewResourceTypeInDatabase() {
        ResourceType resourceType = new ResourceType();
        resourceType.setName("Test");
        resourceType.setWeight(5);

        String id = resourceTypeRepository.save(resourceType).getId();
        Assert.assertNotNull(id);

        ResourceType resourceTypeDatabaseEntity = resourceTypeRepository.findById(id).orElse(null);
        Assert.assertNotNull(resourceTypeDatabaseEntity);
        Assert.assertEquals(resourceType, resourceTypeDatabaseEntity);
    }


    @Test
    public void TestFindAllResourceTypesInDatabase() {
        List<ResourceType> resourceTypeList = new ArrayList<>();

        ResourceType resourceType1 = new ResourceType();
        ResourceType resourceType2 = new ResourceType();
        ResourceType resourceType3 = new ResourceType();
        resourceType1.setName("test1");
        resourceType2.setName("test2");
        resourceType3.setName("test3");
        resourceType1.setWeight(1);
        resourceType2.setWeight(1);
        resourceType3.setWeight(1);

        resourceTypeList.add(resourceType1);
        resourceTypeList.add(resourceType2);
        resourceTypeList.add(resourceType3);

        resourceTypeRepository.save(resourceType1);
        resourceTypeRepository.save(resourceType2);
        resourceTypeRepository.save(resourceType3);

        List<ResourceType> resourceTypeListFromDatabase = resourceTypeRepository.findAll();
        Assert.assertNotNull(resourceTypeListFromDatabase);
        Assert.assertEquals(resourceTypeList, resourceTypeListFromDatabase);
    }


    @Test
    public void DeleteResourceTypeUsingNameFromDatabase() {
        ResourceType resourceType = new ResourceType();
        resourceType.setName("ResourceToDelete");
        resourceType.setWeight(10);

        String id = resourceTypeRepository.save(resourceType).getId();
        Assert.assertNotNull(id);

        resourceTypeRepository.deleteByName(resourceType.getName());

        Assert.assertFalse(resourceTypeRepository.existsById(id));
    }

    @Test
    public void DeleteResourceTypeThatDoesNotExist() {
        ResourceType resourceType = new ResourceType();
        resourceType.setName("ResourceToDelete");
        resourceType.setWeight(10);

        String id = resourceTypeRepository.save(resourceType).getId();
        Assert.assertNotNull(id);

        Long numberBeforeDelete = resourceTypeRepository.count();
        resourceTypeRepository.deleteByName("ThisNameDoesNotExist");

        Long numberAfterDelete = resourceTypeRepository.count();

        Assert.assertEquals(numberAfterDelete, numberBeforeDelete);
    }

}
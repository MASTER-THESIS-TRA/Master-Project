package Services;

import Classes.Resource;
import Classes.ResourceManager;
import Classes.Transfer;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

import javax.annotation.Resources;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ResourceService {


    public boolean SellResource(ResourceManager resourceManager, Transfer transfer) {
        throw new NotImplementedException();
    }

    public boolean BuyResource(ResourceManager resourceManager, Transfer transfer) {
        throw new NotImplementedException();
    }

    public List<Resource> GetAllResources() {
        List<Resource> resources = new ArrayList<>();
        resources.add(new Resource("Coffee", 10));
        return resources;
    }
}

package Services;

import Classes.ResourceManager;
import Classes.Transfer;

public interface IResourceService {
    boolean SellResource(ResourceManager resourceManager, Transfer transfer);
    boolean BuyResource(ResourceManager resourceManager, Transfer transfer);
}

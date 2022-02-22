package Classes;

import Interfaces.ITransformable;
import Interfaces.ITransformer;
import ch.qos.logback.core.helpers.Transform;
import org.apache.commons.lang3.NotImplementedException;

public class Transformer implements ITransformer {
    public Resource transform (ITransformable input){
        throw new NotImplementedException();
    }
}

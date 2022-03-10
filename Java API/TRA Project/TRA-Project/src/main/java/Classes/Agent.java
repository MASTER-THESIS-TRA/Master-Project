package Classes;

import Exceptions.ExceptionConstants;
import Exceptions.TRAException;
import org.apache.commons.lang3.NotImplementedException;

public class Agent {

    public String name;

    public Agent(String name) throws TRAException {
        if (name == null){
            throw new TRAException(ExceptionConstants.GENERIC_ERROR);
        }
        this.name = name;
    }
}

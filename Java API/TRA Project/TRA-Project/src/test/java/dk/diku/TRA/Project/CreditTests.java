package dk.diku.TRA.Project;

import dk.diku.TRA.Project.Classes.*;
import dk.diku.TRA.Project.Exceptions.TRAException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CreditTests {
    private Agent alice = new Agent("111","Alice", "alice@email.com","111");
    private Agent bob = new Agent("222","Bob", "bob@email.com","222");
    private Resource a = new Resource("a",1337);
    private Resource b = new Resource("b",42);
    private ResourceManager RM = new ResourceManager("Bank","bank@bank.com","bankMcBankerson");
    private Credit CP1 = new Credit();
    private Credit CP2 = new Credit(alice,a); // This credit gives alice 1337 'a' in credit.


    @Test
    public void TestValidateTransferNoCredit(){
        Map<Agent,Resource> M1 = new HashMap<>();
        Map<Agent,Resource> M2 = new HashMap<>();
        M1.put(RM,Resource.add(Resource.mult(a,-1),Resource.mult(b,-1)));
        M1.put(alice,a);
        M1.put(bob,b);
        M2.put(alice,Resource.mult(a,-1));
        M2.put(bob,a);
        try{
            Transfer ownership = new Transfer(M1);
            Transfer transfer = new Transfer(M2);
            assert(CP1.ValidateTransfer(transfer,ownership));
        }catch(TRAException e){
            assert(false);
        }
    }

    @Test
    public void TestValidateTransferCredit(){
        Map<Agent,Resource> M1 = new HashMap<>();
        Map<Agent,Resource> M2 = new HashMap<>();
        M1.put(RM,Resource.add(Resource.mult(a,-1),Resource.mult(b,-1)));
        M1.put(alice,a);
        M1.put(bob,b);
        M2.put(alice,Resource.mult(a,-2));
        M2.put(bob,Resource.mult(a,2));
        try{
            Transfer ownership = new Transfer(M1);
            Transfer transfer = new Transfer(M2);
            assert(CP2.ValidateTransfer(transfer,ownership));
        }catch(TRAException e){
            assert(false);
        }
    }
}

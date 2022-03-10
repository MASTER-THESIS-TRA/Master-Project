package dk.diku.TRA.Project;

import Classes.Agent;
import Classes.Resource;
import Classes.ResourceManager;
import Classes.Transfer;
import Exceptions.TRAException;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ResourceManagerTests {

    ////////////////////////////////////////////////////////////////
    /////////////////////// Init tests /////////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestInit (){
        try{
            ResourceManager manager = new ResourceManager();
            assert (true);
        } catch (Exception e){
            assert(false);
        }
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////// addAgent tests //////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestAddAgent(){
        try{
            ResourceManager manager = new ResourceManager();
            Agent alice = new Agent("Alice");
            Agent bob = new Agent("Bob");
            manager.addAgent(alice);
            manager.addAgent(bob);
            Set<Agent> agents = new HashSet<Agent>(Arrays.asList(alice, bob));
            assert(manager.ownerships.keySet().equals(agents));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestAddAgentFail1(){
        try{
            ResourceManager manager = new ResourceManager();
            manager.addAgent(null);
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }
    @Test
    void TestAddAgentFail2(){
        try{
            ResourceManager manager = new ResourceManager();
            Agent alice = new Agent("Alice");
            manager.addAgent(alice);
            manager.addAgent(alice);
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }

    ////////////////////////////////////////////////////////////////
    //////////////// verifyInternalTransfer tests //////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestVerifyInternalTransferSuccess(){
        try{
            ResourceManager manager = new ResourceManager();
            Agent alice = new Agent("Alice");
            Agent bob = new Agent("Bob");
            manager.addAgent(alice);
            manager.addAgent(bob);
            Resource a = new Resource("a", 1337);
            Resource b = new Resource("b", 42);
            manager.ownerships.get(alice).add(a);
            manager.ownerships.get(bob).add(b);
            a.multAll(-1);
            b.multAll(-1);
            Transfer t = new Transfer(Arrays.asList(new Agent[]{alice, bob}),
                                      Arrays.asList(new Resource[]{a, b}));
            assert(manager.verifyInternalTransfer(t));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestVerifyInternalTransferFail(){
        try{
            ResourceManager manager = new ResourceManager();
            Agent alice = new Agent("Alice");
            Agent bob = new Agent("Bob");
            manager.addAgent(alice);
            manager.addAgent(bob);
            Resource a = new Resource("a", 1337);
            Resource b = new Resource("b", 42);
            manager.ownerships.get(alice).add(a);
            manager.ownerships.get(bob).add(b);
            a.multAll(-2);
            b.multAll(-2);
            Transfer t = new Transfer(Arrays.asList(new Agent[]{alice, bob}),
                    Arrays.asList(new Resource[]{a, b}));
            assert(!manager.verifyInternalTransfer(t));
        } catch (TRAException e){
            assert(false);
        }
    }
}

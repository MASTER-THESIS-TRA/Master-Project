package dk.diku.TRA.Project;

import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Classes.Resource;
import dk.diku.TRA.Project.Classes.ResourceManager;
import dk.diku.TRA.Project.Classes.Transfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ResourceManagerTests {

    Agent Fritz;
    Agent Alexander;
    Agent Daniel;
    Resource knuts;
    Resource sickles;
    Resource galleons;
    ResourceManager bank;
    boolean setup = false;

    @BeforeEach
    void init(){
        if (setup){
            return;
        }
        else{
            setup = true;
            knuts = new Resource("Knuts",1);
            sickles = new Resource("Sickles",1);
            galleons = new Resource("Galleons", 1);
            Fritz = new Agent("1","Fritz","","");
            Alexander = new Agent("2","Alexander","","");
            Daniel = new Agent("3","Daniel","","");
            HashMap<Agent, Resource> M = new HashMap<>();
            M.put(Daniel,Resource.add(Resource.mult(knuts,50),Resource.add(Resource.mult(sickles,25),galleons)));
            M.put(Alexander,Resource.add(Resource.mult(knuts,75),Resource.add(Resource.mult(sickles,10),Resource.mult(galleons,2))));
            M.put(Fritz,Resource.add(Resource.mult(knuts,50),Resource.add(Resource.mult(sickles,42),Resource.mult(galleons,1337))));
            HashMap<String,Integer> W = new HashMap<>();
            W.put("Knuts",1);
            W.put("Sickles",100);
            W.put("Galleons",10000);
            bank = new ResourceManager("Gringotts", M, W);
        }
    }

    @Test
    void TestAddDuplicateAgent() {
        assert(!bank.AddAgent(Daniel));
    }

    @Test
    void TestGetOwnerships(){
        Transfer owns = bank.GetOwnerships();
        assert(owns.keySet().size()==4);
        assert(owns.keySet().contains(Daniel));
        assert(owns.keySet().contains(Alexander));
        assert(owns.keySet().contains(Fritz));
        assert(owns.keySet().contains(bank));
    }

    @Test
    void TestOwnershipStateIsTransfer(){
        Resource sum = Resource.zero();
        sum = Resource.add(sum,bank.GetOwnerships().get(bank.findAgentById(Daniel.getUuid())));
        sum = Resource.add(sum,bank.GetOwnerships().get(bank.findAgentById(Alexander.getUuid())));
        sum = Resource.add(sum,bank.GetOwnerships().get(bank.findAgentById(Fritz.getUuid())));
        Resource negSum = Resource.mult(sum,-1);
        Resource bankDebt = bank.GetOwnerships().get(bank);
        assert(bankDebt.equals(negSum));
    }

   /*
    @Test
    void TestResourceManagerCreation() {
        HashMap m = new HashMap();
        Resource a = new Resource("a", 42);
        Resource b = new Resource("b", 1337);
        Agent alice = new Agent("Alice");
        Agent bob = new Agent("Bob");
        m.put(alice, a);
        m.put(bob, b);
        Transfer t0 = new Transfer(m);
        ResourceManager rm = new ResourceManager("Bank", m);
        Agent bank = new Agent("Bank");
        Transfer t1 = new Transfer(bank, Resource.mult(a, -1));
        Transfer t2 = new Transfer(bank, Resource.mult(b, -1));
        Transfer t3 = Transfer.add(t1, t2);
        Transfer t4 = Transfer.add(t0, t3);
        assert(t4.equals(rm.getOwnerships()));
    }




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

    ////////////////////////////////////////////////////////////////
    /////////////// completeInternalTransfer tests /////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestCompleteInternalTransferSuccess(){
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
            Resource t1 = new Resource();
            Resource t2 = new Resource();
            t1.add(a);  // t1 = {a -> 1337}
            t2.add(b);  // t2 = {b -> 42}
            a.multAll(-1);
            b.multAll(-1);
            t1.add(b);  // t1 = {a -> 1337, b -> -42}
            t2.add(a);  // t2 = {a -> -1337, b -> 42}
            Transfer t = new Transfer(Arrays.asList(new Agent[]{alice, bob}),
                    Arrays.asList(new Resource[]{t2, t1}));
            manager.completeInternalTransfer(t);
            a.multAll(-1);
            b.multAll(-1);
            assert(manager.ownerships.get(alice).equals(b));
            assert(manager.ownerships.get(bob).equals(a));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestCompleteInternalTransferFail1(){
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
            // They both have enough for the transfer, but it is not a valid transfer.
            a.multAll(0.5f);
            b.multAll(0.5f);
            Transfer t = new Transfer(Arrays.asList(new Agent[]{alice, bob}),
                    Arrays.asList(new Resource[]{a, b}));
            manager.completeInternalTransfer(t);
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }
    @Test
    void TestCompleteInternalTransferFail2(){
        try{
            ResourceManager manager = new ResourceManager();
            Agent alice = new Agent("Alice");
            Agent bob = new Agent("Bob");
            manager.addAgent(alice);
            manager.addAgent(bob);
            Resource a = new Resource("a", 1337);
            Resource b = new Resource("b", 42);
            // not adding resource to Bobs account, so the transfer is valid, but Bob cannot afford it.
            manager.ownerships.get(alice).add(a);
            a.multAll(-1);
            b.multAll(-1);
            Transfer t = new Transfer(Arrays.asList(new Agent[]{alice, bob}),
                    Arrays.asList(new Resource[]{a, b}));
            manager.completeInternalTransfer(t);
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }
    @Test
    void TestCompleteInternalTransfer3Way(){
        try{
            ResourceManager manager = new ResourceManager();
            Agent alice = new Agent("Alice");
            Agent bob = new Agent("Bob");
            Agent charles = new Agent("Charles");
            manager.addAgent(alice);
            manager.addAgent(bob);
            manager.addAgent(charles);
            Resource a = new Resource("a", 1337);
            Resource b = new Resource("b", 42);
            Resource c = new Resource("c", 101);
            manager.ownerships.get(alice).add(a);
            manager.ownerships.get(bob).add(b);
            manager.ownerships.get(charles).add(c);
            Resource t1 = new Resource();
            Resource t2 = new Resource();
            Resource t3 = new Resource();
            t1.add(a);  // t1 = {a -> 1337}
            t2.add(b);  // t2 = {b -> 42}
            t3.add(c);  // tc = {c -> 101}
            a.multAll(-1);
            b.multAll(-1);
            c.multAll(-1);
            t1.add(c);  // t1 = {a -> 1337, c -> -101}
            t2.add(a);  // t2 = {a -> -1337, b -> 42}
            t3.add(b);  // t3 = {b -> -42, c -> 101}
            Transfer t = new Transfer(Arrays.asList(new Agent[]{alice, bob, charles}),
                    Arrays.asList(new Resource[]{t2, t3, t1}));
            manager.completeInternalTransfer(t);
            a.multAll(-1);
            b.multAll(-1);
            c.multAll(-1);
            assert(manager.ownerships.get(alice).equals(b));
            assert(manager.ownerships.get(bob).equals(c));
            assert(manager.ownerships.get(charles).equals(a));
        } catch (TRAException e){
            assert(false);
        }
    }*/
}

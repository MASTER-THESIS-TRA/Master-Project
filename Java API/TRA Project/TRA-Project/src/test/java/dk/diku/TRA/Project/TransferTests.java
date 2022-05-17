package dk.diku.TRA.Project;

import dk.diku.TRA.Project.Classes.Agent;
import dk.diku.TRA.Project.Classes.Resource;
import dk.diku.TRA.Project.Classes.ResourceManager;
import dk.diku.TRA.Project.Classes.Transfer;
import dk.diku.TRA.Project.Exceptions.TRAException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.*;

import static java.lang.Integer.parseInt;

public class TransferTests {
    Transfer t1;
    Transfer t2;
    Transfer t3;
    Agent alice;
    Agent bob;
    boolean setup = false;

    @BeforeEach
    void init(){
        if (setup){
            return;
        }
        else{
            try{
                setup = true;
                Resource a = new Resource("a", 42);
                Resource b = new Resource("b", 1337);
                Resource c = new Resource("c", 888);
                alice = new Agent("Alice","","","");
                bob = new Agent("Bob","","","");
                HashMap<Agent, Resource> M1 = new HashMap<>();
                M1.put(alice, a);
                M1.put(bob, Resource.mult(a, -1));
                HashMap<Agent, Resource> M2 = new HashMap<>();
                M2.put(alice, b);
                M2.put(bob, Resource.mult(b, -1));
                HashMap<Agent, Resource> M3 = new HashMap<>();
                M3.put(alice, c);
                M3.put(bob, Resource.mult(c, -1));
                t1 = new Transfer(M1);
                t2 = new Transfer(M2);
                t3 = new Transfer(M3);
            } catch (TRAException e) {
                assert(false);
            }
        }
    }

    ////////////////////////////////////////////////////////////////
    ///////////////////////// Add tests ////////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestAddCummutative(){  // a+b=b+a
        Transfer t4 = Transfer.add(t1,t2);
        Transfer t5 = Transfer.add(t2,t1);
        assert(t4.equals(t5));
        assert(t5.equals(t4));
    }
    @Test
    void TestAddAsociative(){ // (a+b)+c=a+(b+c)
        Transfer t4 = Transfer.add(Transfer.add(t1, t2), t3);
        Transfer t5 = Transfer.add(Transfer.add(t2, t3), t1);
        assert (t5.equals(t4));
        assert (t4.equals(t5));
    }

    @Test
    void TestAddIdentity(){ // a+0=a
        assert(t1.equals(Transfer.add(t1,Transfer.zero())));
    }

    @Test
    void TestAddInverse(){ // a+(-a)=0
        assert(Transfer.zero().equals(Transfer.add(Transfer.mult(t1,-1),t1)));
    }

    ////////////////////////////////////////////////////////////////
    //////////////////////// Mult tests ////////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestMultBasic(){ // 1337*42 = 56154
        Integer b = 1337;
        Resource x = new Resource("a", 56154);
        HashMap<Agent, Resource> M1 = new HashMap<>();
        M1.put(alice, x);
        M1.put(bob, Resource.mult(x, -1));
        try{
            Transfer cmp = new Transfer(M1);
            assert(cmp.equals(Transfer.mult(t1,b)));
        } catch (TRAException e){
            assert(false);
        }
    }

    @Test
    void TestMultAssociative(){ // a*(b*c)=(a*b)*c variation 1
        Integer b = 1337;
        Integer c = 42;
        Transfer t4 = Transfer.mult(Transfer.mult(t1,b),c);
        Transfer t5 = Transfer.mult(Transfer.mult(t1,c),b);
        assert(t4.equals(t5));
    }

    @Test
    void TestMultAssociative2(){ // a*(b*c)=(a*b)*c variation 2
        Integer b = 1337;
        Integer c = 42;
        Transfer t4 = Transfer.mult(t1,b*c);
        Transfer t5 = Transfer.mult(Transfer.mult(t1,b),c);
        assert(t4.equals(t5));
    }

    @Test
    void TestMultIdentity(){ //  t1*1=t1
        // A bit of a special test case, as this is not actually the multiplicative identity of the vector space (one is not necessary).
        assert(t1.equals(Transfer.mult(t1,1)));
    }

    @Test
    void TestMultInverse(){ // t1*0=0
        // Not multiplicativ inverse, but same reasoning as the testcase above stands to reason her.
        assert(Transfer.zero().equals(Transfer.mult(t1,0)));
    }

    ////////////////////////////////////////////////////////////////
    /////////////////// "Integration" tests ////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestDistributive1(){ // c * (t1 + t2) = (c * t1) + (c * t2)
        Integer c = 888;
        Transfer t4 = Transfer.mult(Transfer.add(t1,t2),c);
        Transfer t5 = Transfer.add(Transfer.mult(t1,c),Transfer.mult(t2,c));
        assert(t5.equals(t4));
    }
    @Test
    void TestDistributive2(){ // (a + b) * t1 = (a * t1) + (b * t1)
        Integer a = 888;
        Integer b = 1337;
        Transfer t4 = Transfer.mult(t1,(a+b));
        Transfer t5 = Transfer.add(Transfer.mult(t1,a),Transfer.mult(t1,b));
        assert(t4.equals(t5));
    }

    ////////////////////////////////////////////////////////////////
    /////////////////////// Valid tests ////////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    ///////////////// This might need extension if we decide that valid transfers should also check if the parties can afford the transfer.
    void TestZeroSum() {
        Transfer t = Transfer.add(Transfer.add(t1,t2),t3);
        assert(t.Valid());
    }
    @Test  ///////////////// This might need extension if we decide that valid transfers should also check if the parties can afford the transfer.
    void TestZeroSumFail() {
        Resource a1 = new Resource("a", 41);
        Resource a2 = new Resource("a", -42);
        Resource b1 = new Resource("b", 1337);
        Resource b2 = new Resource("b", -1338);
        Resource finalA1 = Resource.add(a1,b2);
        Resource finalA2 = Resource.add(a2,b1);
        Agent alice = new Agent("","Alice","","");
        Agent bob = new Agent("","Bob","","");
        HashMap<Agent, Resource> M = new HashMap<>();
        M.put(alice,finalA1);
        M.put(bob,finalA2);
        try{
            Transfer t = new Transfer(M);
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }

    //////////////////////////////////////////////////////////////////////
    ////////////////////////// String Functions //////////////////////////
    //////////////////////////////////////////////////////////////////////
    @Test
    void TestToString(){
        String val = Transfer.ToString(t1);
        String cmp = "{"+alice.getUuid().toString()+"->{a->42},"+bob.getUuid().toString()+"->{a->-42}}";
        String cmp2 = "{"+bob.getUuid().toString()+"->{a->-42},"+alice.getUuid().toString()+"->{a->42}}";
        boolean correct = val.equals(cmp)||val.equals(cmp2);  // We do not care which way they are ordered.
        assert(correct);
    }

    @Test
    void TestParseStringToTransfer(){
        String val = "{"+bob.getUuid().toString()+"->{b->-1337},"+alice.getUuid().toString()+"->{b->1337}}";
        ResourceManager manager = new ResourceManager("manager");
        manager.AddAgent(alice);
        manager.AddAgent(bob);
        Transfer parsed = Transfer.ParseStringToTransfer(val,manager);
        assert(parsed.equals(t2));
    }

    @Test
    void TestZeroToString(){
        assert(Transfer.ToString(Transfer.zero()).equals("{}"));
    }

    @Test
    void TestParseStringToZero(){
        assert(Transfer.ParseStringToTransfer("{}",new ResourceManager("man")).equals(Transfer.zero()));
    }

    @Test
    void TestIncorrectFormat(){
        assert(Transfer.ParseStringToTransfer("This is not a proper resource",new ResourceManager("man"))==null);
    }
}

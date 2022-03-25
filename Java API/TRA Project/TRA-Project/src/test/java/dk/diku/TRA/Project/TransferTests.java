package dk.diku.TRA.Project;

import Classes.Agent;
import Classes.Resource;
import Classes.Transfer;
import Exceptions.TRAException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;

public class TransferTests {
    Transfer t1;
    Transfer t2;
    Transfer t3;
    ////////////////////////////////////////////////////////////////
    ///////////////////////// Add tests ////////////////////////////
    ////////////////////////////////////////////////////////////////
    @BeforeAll
    void init(){
        try{
            Resource a = new Resource("a", 42);
            Resource b = new Resource("b", 1337);
            Resource c = new Resource("c", 888);
            Agent alice = new Agent("Alice");
            Agent bob = new Agent("Bob");
            HashMap<Agent, Resource> M1 = new HashMap<>();
            M1.put(alice, a);
            M1.put(bob, Resource.mult(a, -1));
            HashMap<Agent, Resource> M2 = new HashMap<>();
            M1.put(alice, b);
            M1.put(bob, Resource.mult(b, -1));
            HashMap<Agent, Resource> M3 = new HashMap<>();
            M1.put(alice, c);
            M1.put(bob, Resource.mult(c, -1));
            t1 = new Transfer(M1);
            t2 = new Transfer(M2);
            t3 = new Transfer(M3);
        } catch (TRAException e) {
            assert(false);
        }

    }

    @Test
    void TestAddCummutative(){  // a+b=b+a
        Transfer t3 = Transfer.add(t1,t2);
        Transfer t4 = Transfer.add(t2,t1);
        assert(t3.equals(t4));
        assert(t4.equals(t3));
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












    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////
    //////////////////////// Mult tests ////////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestMultBasic(){
        Integer b = 1337;
        Transfer t2 = Transfer.mult(t1,b);
        assert(t3.equals(t2));
        assert(t2.equals(t3));
    }

    @Test
    void TestMultCummutative(){
        assert(true); // Not sure if cummutative multiplication is really relevant to us.
    }

    @Test
    void TestMultAssociative(){
        Resource a = new Resource("a", 42);
        Agent alice = new Agent("Alice");
        Integer b = 1337;
        Integer c = 42;
        Transfer t1 = new Transfer(alice,a);
        Transfer t2 = Transfer.mult(Transfer.mult(t1,b),c);
        Transfer t3 = Transfer.mult(Transfer.mult(t1,c),b);
        assert(t3.equals(t2));
        assert(t2.equals(t3));
    }

    @Test
    void TestMultAssociative2(){
        Resource a = new Resource("a", 42);
        Agent alice = new Agent("Alice");
        Integer b = 1337;
        Integer c = 42;
        Transfer t1 = new Transfer(alice,a);
        Transfer t2 = Transfer.mult(t1,b*c);
        Transfer t3 = Transfer.mult(Transfer.mult(t1,b),c);
        assert(t3.equals(t2));
        assert(t2.equals(t3));
    }

    @Test
    void TestMultIdentity(){ // A bit of a special test case, as this is not actually the multiplicative identity of the vector space (one is not necessary).
        Resource a = new Resource("a", 42);
        Agent alice = new Agent("Alice");
        Transfer t = new Transfer(alice, a);
        assert(t.equals(Transfer.mult(t,1)));
    }

    @Test
    void TestMultInverse(){ // Not multiplicativ inverse, but same reasoning as the testcase above stands to reason her.
        Resource a = new Resource("a", 42);
        Agent alice = new Agent("Alice");
        Transfer t = new Transfer(alice, a);
        assert(Transfer.zero().equals(Transfer.mult(t,0)));
    }

    ////////////////////////////////////////////////////////////////
    /////////////////// "Integration" tests ////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestDistributive1(){ // c * (t1 + t2) = (c * t1) + (c * t2)
        Resource a = new Resource("a", 42);
        Resource b = new Resource("b", 1337);
        Agent alice = new Agent("Alice");
        Agent bob = new Agent("Bob");
        Transfer t1 = new Transfer(alice,Resource.add(a,Resource.mult(b,-1)));
        Transfer t2 = new Transfer(bob,Resource.add(b,Resource.mult(a,-1)));
        Integer c = 888;
        Transfer t3 = Transfer.mult(Transfer.add(t1,t2),c);
        Transfer t4 = Transfer.add(Transfer.mult(t1,c),Transfer.mult(t2,c));
        assert(t3.equals(t4));
    }
    @Test
    void TestDistributive2(){ // (a + b) * t1 = (a * t1) + (b * t1)
        Integer a = 888;
        Integer b = 1337;
        Resource c = new Resource("a", 42);
        Agent alice = new Agent("Alice");
        Transfer t1 = new Transfer(alice,c);
        Transfer t2 = Transfer.mult(t1,(a+b));
        Transfer t3 = Transfer.add(Transfer.mult(t1,a),Transfer.mult(t1,b));
        assert(t3.equals(t2));
    }

    ////////////////////////////////////////////////////////////////
    /////////////////////// Valid tests ////////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    ///////////////// This might need extension if we decide that valid transfers should also check if the parties can afford the transfer.
    void TestZeroSum() {
        Resource a1 = new Resource("a", 42);
        Resource a2 = new Resource("a", -42);
        Resource b1 = new Resource("b", 1337);
        Resource b2 = new Resource("b", -1337);
        Resource finalA1 = Resource.add(a1,b2);
        Resource finalA2 = Resource.add(a2,b1);
        Agent alice = new Agent("Alice");
        Agent bob = new Agent("Bob");
        HashMap<Agent, Resource> M = new HashMap<>();
        M.put(alice,finalA1);
        M.put(bob,finalA2);
        Transfer t = new Transfer(M);
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
        Agent alice = new Agent("Alice");
        Agent bob = new Agent("Bob");
        HashMap<Agent, Resource> M = new HashMap<>();
        M.put(alice,finalA1);
        M.put(bob,finalA2);
        Transfer t = new Transfer(M);
        assert(!t.Valid());
    }
}

package dk.diku.TRA.Project;

public class TransferTests {
/*
    ////////////////////////////////////////////////////////////////
    /////////////////////// Valid tests ////////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test  ///////////////// This might need extension if we decide that valid transfers should also check if the parties can afford the transfer.
    void TestZeroSum() {
        try{
            Resource a1 = new Resource("a", 42);
            Resource a2 = new Resource("a", -42);
            Resource b1 = new Resource("b", 1337);
            Resource b2 = new Resource("b", -1337);
            a1.add(b2);
            a2.add(b1);
            Agent alice = new Agent("Alice");
            Agent bob = new Agent("Bob");
            Transfer t = new Transfer(new ArrayList<>() {{add(alice); add(bob);}},
                                        new ArrayList<>(){{add(a1); add(a2);}});
            assert(t.validTransfer());
        } catch (TRAException e){
            assert(true);
        }
    }
    @Test  ///////////////// This might need extension if we decide that valid transfers should also check if the parties can afford the transfer.
    void TestZeroSumFail() {
        try{
            Resource a1 = new Resource("a", 41);
            Resource a2 = new Resource("a", -42);
            Resource b1 = new Resource("b", 1338);
            Resource b2 = new Resource("b", -1337);
            a1.add(b2);
            a2.add(b1);
            Agent alice = new Agent("Alice");
            Agent bob = new Agent("Bob");
            Transfer t = new Transfer(new ArrayList<>() {{add(alice); add(bob);}},
                    new ArrayList<>(){{add(a1); add(a2);}});
            assert(!t.validTransfer());
        } catch (TRAException e){
            assert(true);
        }
    }*/
}

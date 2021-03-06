package dk.diku.TRA.Project;

import dk.diku.TRA.Project.Classes.Weight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeightTest {
    Weight w1;
    Weight w2;
    Weight w3;
    boolean setup = false;

    @BeforeEach
    void init(){
        if (setup){
            return;
        }
        else{
            w1 = new Weight("a",25);
            w2 = new Weight("b",1337);
            w3 = new Weight("c",42);
            setup = true;
        }
    }

    @Test
    void TestWeightAdditionBasic() {
        Weight w = Weight.add(w1,w2);
        assert(w.containsKey("a") && w.containsKey("b") && w.entrySet().size()==2);
        assert(w.get("a")==25 && w.get("b")==1337);
    }

    @Test
    void TestWeightAdditionNoDuplicateWeight() {
        Weight w = Weight.add(w1,w1);
        assert(w.containsKey("a") && w.entrySet().size()==1);
        assert(w.get("a")==25);
    }


}

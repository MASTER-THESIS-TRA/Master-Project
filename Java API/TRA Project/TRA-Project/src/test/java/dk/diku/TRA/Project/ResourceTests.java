package dk.diku.TRA.Project;

import dk.diku.TRA.Project.Classes.Resource;
import org.junit.jupiter.api.Test;

import java.util.*;

class ResourceTests {



    @Test
    void TestResourceAdditionBasic() {
        try {
            HashMap temp = new HashMap<>();
            temp.put("a", 15);
            Resource a = new Resource(temp);
            Resource b = new Resource("a", 10);
            Resource c = new Resource("a", 25);
            Resource d = Resource.add(a, b);
            assert (c.size() == d.size());
            assert (c.keySet().equals(d.keySet()));
            assert (c.get("a") == (d.get("a")));
        } catch (ClassCastException e) {
            assert (false);
        }
    }

    @Test
    void TestAddAssosiative() {
        Resource a = new Resource("a", 42);
        Resource b = new Resource("b", 1337);
        Resource c = new Resource("c", 1010);
        Resource r1 = Resource.add(Resource.add(a, b), c);
        Resource r2 = Resource.add(a, Resource.add(b, c));
        assert(r1.equals(r2));
    }

    @Test
    void TestAddCommutative() {
        Resource a = new Resource("a", 42);
        Resource b = new Resource("b", 1337);
        Resource r1 = Resource.add(a, b);
        Resource r2 = Resource.add(b, a);
        assert(r1.equals(r2));
    }


    @Test
    void TestMultAssosiative() {
        Resource a = new Resource("a", 42);
        Integer b = 1337;
        Integer c = 1010;
        Resource r1 = Resource.mult(Resource.mult(a, b), c);
        Resource r2 = Resource.mult(Resource.mult(a, c), b);
        assert(r1.equals(r2));
    }

    @Test
    void TestMultAddCommutative() {
        Resource a = new Resource("a", 42);
        Resource b = new Resource("b", 1337);
        Resource r1 = Resource.add(a, Resource.mult(b, -1));
        Resource r2 = Resource.add(b, Resource.mult(a, -1));
        Resource r3 = Resource.add(r1, r2);
        Resource r4 = Resource.add(r2, r1);
        assert(r3.equals(r4));
    }

    @Test
    void TestAddIdentity() {
        Resource a = new Resource("a", 42);
        Resource r1 = Resource.add(a, Resource.zero());
        assert(a.equals(r1));
    }

    @Test
    void TestAddInverse() {
        Resource a = new Resource("a", 42);
        Resource r1 = Resource.add(Resource.mult(a, -1), a);
        assert(Resource.zero().equals(r1));
    }

    @Test
    void TestMultBasic() {
        Resource a = new Resource("a", 42);
        Integer b = 1337;
        Resource cmq = new Resource("a", 42*b);
        Resource c = Resource.mult(a, b);
        assert(c.equals(cmq));
    }

    @Test
    void TestMultIdentity() {
        Resource a = new Resource("a", 42);
        assert(a.equals(Resource.mult(a, 1)));
    }

    @Test
    void TestMultInverse() {
        Resource a = new Resource("a", 42);
        assert(Resource.zero().equals(Resource.mult(a, 0)));
    }

    @Test
    void TestDistributive() {
        Resource a = new Resource("a", 42);
        Resource b = new Resource("b", 1337);
        Resource r1 = Resource.mult(Resource.add(a, b), 1010);
        Resource r2 = Resource.add(Resource.mult(a, 1010), Resource.mult(b, 1010));
        assert(r1.equals(r2));
    }

    @Test
    void TestDistributive2() {
        Resource a = new Resource("a", 42);
        Integer b = 1337;
        Integer c = 1010;
        Resource r1 = Resource.mult(a, (b+c));
        Resource r2 = Resource.add(Resource.mult(a, b), Resource.mult(a, c));
        assert(r1.equals(r2));
    }

    //////////////////////////////////////////////////////////////////////
    ////////////////////////// String Functions //////////////////////////
    //////////////////////////////////////////////////////////////////////
    @Test
    void TestToString(){
        String val = Resource.ToString(Objects.requireNonNull(Resource.add(
                new Resource("a", 42),
                new Resource("b", 1337)
        )));
        String cmp = "{a->42,b->1337}";
        String cmp2 = "{b->1337,a->42}";
        boolean correct = val.equals(cmp)||val.equals(cmp2); // We do not care which way they are ordered, for the sake of equality in resources.
        assert(correct);
    }

    @Test
    void TestParseStringToResource(){
        String val = "{a->42,b->1337}";
        Resource comp = Resource.add(
                new Resource("a", 42),
                new Resource("b", 1337)
        );
        Resource parsed = Resource.ParseStringToResource(val);
        assert(comp.equals(parsed));
    }

    @Test
    void TestZeroToString(){
        assert(Resource.ToString(Resource.zero()).equals("{}"));
    }

    @Test
    void TestParseStringToZero(){
        assert(Resource.ParseStringToResource("{}").equals(Resource.zero()));
    }

    @Test
    void TestIncorrectFormat(){
        assert(Resource.ParseStringToResource("This is not a proper resource")==null);
    }
}

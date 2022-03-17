package dk.diku.TRA.Project;

import Classes.Resource;
import Exceptions.TRAException;
import org.junit.jupiter.api.Test;

import java.text.StringCharacterIterator;
import java.util.*;

class ResourceTests {



    @Test
    void TestResourceAddition() {
        try {
            HashMap temp = new HashMap<>();
            temp.put("a", 15);
            temp.put("b", 10);
            Resource a = new Resource(temp);
            Resource b = new Resource("a", 10);
            Resource c = new Resource("a", 25);
            Resource d = Resource.add(a, b);
            System.out.println(Resource.add(a, b).values());
            System.out.println("d: " + d.values());
            System.out.println("c " + c.get("a") + " d: " + d.get("a"));
            assert (c.size() == d.size());
            assert (c.keySet().equals(d.keySet()));
            assert (c.get("a") == (d.get("a")));
        } catch (ClassCastException e) {
            assert (false);
        }
    }
    /*
    @Test
    void TestResourceAdditionWhenOneIsNull() {
        try{
            Resource a = new Resource("a", 1337);
            Resource b = new Resource("b", null);
            Resource c = Resource.add(a, b);
            assert(false);
        } catch (ClassCastException e) {
            assert(true);
        }
    }




    ////////////////////////////////////////////////////////////////
    /////////////////////// Init tests /////////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestNoNullValue() {
        try{
            Resource R = new Resource(null, 42);
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }

    ////////////////////////////////////////////////////////////////
    /////////////////////// Add tests //////////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestAddDifferent() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("b",42);
            a.add(b);
            Set<String> keys = new HashSet<>(Arrays.asList("a", "b"));
            assert(a.getResource().keySet().equals(keys));
            assert(a.getResource().get("a")==1337);
            assert(a.getResource().get("b")==42);
        } catch (TRAException e){
            assert(false);
        }
    }

    @Test
    void TestAddSame() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("a",42);
            a.add(b);
            Set<String> keys = new HashSet<>(Arrays.asList("a"));
            assert(a.getResource().keySet().equals(keys));
            assert(a.getResource().get("a")==1379);
        } catch (TRAException e){
            assert(false);
        }
    }

    @Test
    void TestSubtract() {
        try{
            Resource a = new Resource("a",1379);
            Resource b = new Resource("a",-42);
            a.add(b);
            Set<String> keys = new HashSet<>(Arrays.asList("a"));
            assert(a.getResource().keySet().equals(keys));
            assert(a.getResource().get("a")==1337);
        } catch (TRAException e){
            assert(false);
        }
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////// Equals tests ////////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestEqualityCheck() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("a",1337);
            System.out.println("Value: " + a.equals(b));
            assert(a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestEqualityCheckFailValue() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("a",42);
            assert(!a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestEqualityCheckFailKeys() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("b",1337);
            assert(!a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestEqualityCheckFailType() {
        try{
            Resource a = new Resource("a",1337);
            Object b = new Object();
            assert(!a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestEqualityCheckZeroValuesInComparison() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("a",1337);
            Resource c = new Resource("b",0);
            b.add(c);
            assert(a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestEqualityCheckZeroValuesInThis() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("a",1337);
            Resource c = new Resource("b",0);
            a.add(c);
            assert(a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }

    ////////////////////////////////////////////////////////////////
    /////////////////// Mult Single tests //////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestMultiplySingleElementBy1() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("a",1337);
            a.multItem("a", 1);
            assert(a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestMultiplySingleElementBy3() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("a",4011);
            a.multItem("a", 3);
            assert(a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestMultiplySingleElementByNeg1() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("a",-1337);
            a.multItem("a", -1);
            assert(a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }

    @Test
    void TestMultiplySingleElementByZero() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("a",0);
            a.multItem("a", 0);
            assert(a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }

    ////////////////////////////////////////////////////////////////
    ///////////////////// Mult All tests ///////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestMultiplyAllBy1() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("a",1337);
            Resource c = new Resource("c", 42);
            a.add(c);
            b.add(c);
            a.multAll(1);
            assert(a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestMultiplyAllBy3() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("a",4011);
            Resource c = new Resource("c", 42);
            a.add(c);
            c.multItem("c", 3);
            b.add(c);
            a.multAll(3);
            assert(a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestMultiplyAllByNeg1() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("a",-1337);
            Resource c = new Resource("c", 42);
            a.add(c);
            c.multItem("c",-1);
            b.add(c);
            a.multAll(-1);
            assert(a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestMultiplyAllByZero() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("a",0);
            Resource c = new Resource("b", 42);
            a.add(c);
            c.multItem("b", 0);
            b.add(c);
            a.multAll(0);
            assert(a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }


    ////////////////////////////////////////////////////////////////
    /////////////////////// Zero tests /////////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestSingleResourceIsZeroPass() {
        try{
            Resource a = new Resource("a",0);
            assert(a.isZero());
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestSingleResourceIsZeroFail() {
        try{
            Resource a = new Resource("a",1);
            assert(!a.isZero());
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestMultipleResourcesIsZeroPass() {
        try{
            Resource a = new Resource("a",0);
            Resource b = new Resource("b", 0);
            a.add(b);
            assert(a.isZero());
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestMultipleResourcesIsZeroFail() {
        try{
            Resource a = new Resource("a",1);
            Resource b = new Resource("b", 2);
            a.add(b);
            assert(!a.isZero());
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestSomeResourcesIsZeroFail() {
        try{
            Resource a = new Resource("a",0);
            Resource b = new Resource("b", 1);
            a.add(b);
            assert(!a.isZero());
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestNotLessThanZero() {
        try{
            Resource a = new Resource("a",0);
            Resource b = new Resource("b", -1);
            a.add(b);
            assert(!a.isZero());
        } catch (TRAException e){
            assert(false);
        }
    }

    ////////////////////////////////////////////////////////////////
    /////////////////////// Break tests ////////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestCannotBreakOneIfNotEnough() {
        try{
            Resource a = new Resource("a",0);
            Resource b = a.breakOne("a", 1); // This should not be legal, so an exception is expected.
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }
    @Test
    void TestBasicBreakOne() {
        try{
            Resource a = new Resource("a",10);
            Resource b = a.breakOne("a", 1);
            assert(b.getResource().get("a")==1);
            assert(a.getResource().get("a")==9);
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestBreakOneKeyDoesNotExist() {
        try{
            Resource a = new Resource("a",10);
            Resource b = a.breakOne("", 1); // This should not be legal, so an exception is expected.
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }
    @Test
    void TestBreakOneNegativeValue() {
        try{
            Resource a = new Resource("a",10);
            Resource b = a.breakOne("a", -1); // This should not be legal, so an exception is expected.
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }

    ////////////////////////////////////////////////////////////////
    //////////////////// Break Multi tests /////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestCannotBreakIfOneNotEnough() {
        try{
            Resource a1 = new Resource("a",0);
            Resource a2 = new Resource("b", 2);
            a1.add(a2);
            HashMap<String, Float> toBreak = new HashMap<>();
            toBreak.put("a", 2f);
            toBreak.put("b", 2f);
            Resource b = a1.breakMultiple(toBreak); // This should not be legal, so an exception is expected.
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }
    @Test
    void TestCannotBreakNegative() {
        try{
            Resource a1 = new Resource("a",0);
            Resource a2 = new Resource("b", 2);
            a1.add(a2);
            HashMap<String, Float> toBreak = new HashMap<>();
            toBreak.put("a", -2f);
            toBreak.put("b", 2f);
            Resource b = a1.breakMultiple(toBreak); // This should not be legal, so an exception is expected.
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }
    @Test
    void TestBasicBreakMultiple() {
        try{
            Resource a1 = new Resource("a",2);
            Resource a2 = new Resource("b", 2);
            a1.add(a2);
            HashMap<String, Float> toBreak = new HashMap<>();
            toBreak.put("a", 2f);
            toBreak.put("b", 2f);
            Resource b = a1.breakMultiple(toBreak); // This should not be legal, so an exception is expected.
            assert(b.getResource().get("a")==2);
            assert(b.getResource().get("b")==2);
            assert(a1.getResource().get("a")==0);
            assert(a1.getResource().get("b")==0);
        } catch (TRAException e){
            assert(false);
        }
    }

    ////////////////////////////////////////////////////////////////
    //////////////////// Can Subtract tests ////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestBasicCanSubtract() {
        try{
            Resource a = new Resource("a",1379);
            Resource b = new Resource("a",-42);
            assert(a.canAdd(b));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestBasicCannotSubtract() {
        try{
            Resource a = new Resource("a",1379);
            Resource b = new Resource("a",-4200);
            assert(!a.canAdd(b));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestMultiCannotSubtract() {
        try{
            Resource a = new Resource("a",1379);
            Resource b = new Resource("a",-42);
            Resource c = new Resource("b", -1337);
            b.add(c);
            assert(!a.canAdd(b));
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestMultiCanSubtract() {
        try{
            Resource a = new Resource("a",1379);
            Resource b = new Resource("a",-42);
            Resource c = new Resource("b", 1337);
            b.add(c);
            assert(a.canAdd(b));
        } catch (TRAException e){
            assert(false);
        }
    }
    */
}

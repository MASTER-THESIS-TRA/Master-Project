package dk.diku.TRA.Project;

import Classes.Resource;
import Exceptions.TRAException;
import org.junit.jupiter.api.Test;

import java.io.Console;
import java.util.*;

class ResourceTests {

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
    /* *
    * This test is needed only if we decide to implement addition as a consumption of the added resource.
    * */
    /*@Test
    void TestAddedResourceIsConsumed() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("b",42);
            a.add(b);
            assert(b.getResource().isEmpty());
        } catch (TRAException e){
            assert(false);
        }
    }*/

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
    /////////////////////// Zero tests /////////////////////////////
    ////////////////////////////////////////////////////////////////
    @Test
    void TestCannotBreakOffIfNotEnough() {
        try{
            Resource a = new Resource("a",0);
            Resource b = a.breakOff("a", 1); // This should not be legal, so an exception is expected.
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }
    @Test
    void TestBasicBreakOff() {
        try{
            Resource a = new Resource("a",10);
            Resource b = a.breakOff("a", 1);
            assert(b.getResource().get("a")==1);
            assert(a.getResource().get("a")==9);
        } catch (TRAException e){
            assert(false);
        }
    }
    @Test
    void TestBreakOffKeyDoesNotExist() {
        try{
            Resource a = new Resource("a",10);
            Resource b = a.breakOff("", 1); // This should not be legal, so an exception is expected.
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }
    @Test
    void TestBreakOffNegativeValue() {
        try{
            Resource a = new Resource("a",10);
            Resource b = a.breakOff("a", -1); // This should not be legal, so an exception is expected.
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }

}

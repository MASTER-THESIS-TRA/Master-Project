package dk.diku.TRA.Project;

import Classes.Resource;
import Exceptions.TRAException;
import org.junit.jupiter.api.Test;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.*;

class ResourceTests {
    @Test
    void TestNoNullValue() {
        try{
            Resource R = new Resource(null, 42);
            assert(false);
        } catch (TRAException e){
            assert(true);
        }
    }

    @Test
    void TestAddDifferent() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("b",42);
            a.add(b);
            Set<String> keys = new HashSet<>(Arrays.asList("a", "b"));
            assert(a.resource.keySet().equals(keys));
            assert(a.resource.get("a")==1337);
            assert(a.resource.get("b")==42);
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
            assert(a.resource.keySet().equals(keys));
            assert(a.resource.get("a")==1379);
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
            assert(a.resource.keySet().equals(keys));
            assert(a.resource.get("a")==1337);
        } catch (TRAException e){
            assert(false);
        }
    }

    /* *
    * This test is needed if we decide to implement addition as a consumption of the added resource.
    * */
    /*@Test
    void TestAddedResourceIsConsumed() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("b",42);
            a.add(b);
            assert(b.resource.isEmpty());
        } catch (TRAException e){
            assert(false);
        }
    }*/

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


    @Test
    void TestMultiplyAllBy1() {
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
    void TestMultiplyAllByNeg1() {
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
    void TestMultiplyAllByZero() {
        try{
            Resource a = new Resource("a",1337);
            Resource b = new Resource("a",0);
            a.multItem("a", 0);
            assert(a.equals(b));
        } catch (TRAException e){
            assert(false);
        }
    }

}

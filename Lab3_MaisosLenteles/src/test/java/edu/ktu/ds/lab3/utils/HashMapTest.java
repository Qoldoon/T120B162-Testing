package edu.ktu.ds.lab3.utils;

import joptsimple.ArgumentAcceptingOptionSpec;
import junit.framework.TestCase;
import org.junit.Assert;

public class HashMapTest extends TestCase {

    public void testIsEmpty() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Assert.assertTrue(map.isEmpty());
        map.put(1,3);
        Assert.assertFalse(map.isEmpty());
    }

    public void testSize() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(0.6f, HashManager.HashType.MULTIPLICATION);
        Assert.assertEquals(0, map.size());
        map.put(1,3);
        map.put(2,3);
        Assert.assertEquals(2, map.size());
    }

    public void testClear() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1,3);
        map.put(2,3);
        map.clear();
        Assert.assertEquals(new HashMap<Integer, Integer>().table, map.table);
    }

    public void testContains() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1,3);
        map.put(2,3);
        Assert.assertTrue(map.contains(1));
        Assert.assertTrue(map.contains(2));
        Assert.assertFalse(map.contains(3));
    }

    public void testPutIfAbsent() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1,3);
        map.put(2,3);

        map.putIfAbsent(1,60);
        Assert.assertEquals((Integer) 3, map.get(1));
        map.putIfAbsent(3,70);
        Assert.assertEquals((Integer) 70, map.get(3));
    }

    public void testPut() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1,3);
        map.put(2,3);
        Assert.assertEquals((Integer) 3, map.get(1));
        Assert.assertEquals((Integer) 3, map.get(2));
        map.put(1,60);
        Assert.assertEquals((Integer) 60, map.get(1));
        map.put(3,70);
        Assert.assertEquals((Integer) 70, map.get(3));
    }

    public void testGet() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1,3);
        map.put(2,3);
        Assert.assertEquals((Integer) 3, map.get(1));
        Assert.assertEquals((Integer) 3, map.get(2));
    }

    public void testRemove() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(32,3);
        map.remove(32);
        map.put(64,3);
        map.put(128,3);
        map.put(256,3);
        map.remove(128);
        map.remove(3);
        Assert.assertNull(map.get(128));
        Assert.assertNull(map.get(32));
    }

    public void testTestToString() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1,3);
        map.put(2,3);
        Assert.assertEquals("1=3\n2=3\n",map.toString());
    }

    public void testReplace() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(HashManager.HashType.JCF);
        map.put(1,3);
        map.put(2,3);
        map.replace(1,3,6);
        map.replace(2,6,4);
        Assert.assertEquals((Integer) 6, map.get(1));

    }

    public void testContainsValue() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(HashManager.HashType.JCF7);
        map.put(1,3);

        Assert.assertTrue(map.containsValue(3));
        Assert.assertFalse(map.containsValue(1));
        Assert.assertFalse(map.containsValue("yes"));
    }

    public void testRehash() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1,3);
        map.put(2,3);
        map.put(3,3);
        map.put(4,3);
        map.put(5,3);
        map.put(6,3);
        map.put(7,3);
        map.put(8,3);
        map.put(9,3);
        map.put(10,3);
        Assert.assertEquals(1, map.rehashesCounter);
    }

    public void testRehashIfAbsent() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.putIfAbsent(1,3);
        map.putIfAbsent(2,3);
        map.putIfAbsent(3,3);
        map.putIfAbsent(4,3);
        map.putIfAbsent(5,3);
        map.putIfAbsent(6,3);
        map.putIfAbsent(7,3);
        map.putIfAbsent(8,3);
        map.putIfAbsent(9,3);
        map.putIfAbsent(10,3);
        Assert.assertEquals(1, map.rehashesCounter);
    }

    public void testPutNullException() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            map.put(1, null);
        });

        assertEquals("Key or value is null in put(K key, V value)", exception.getMessage());
    }
    public void testPutIfAbsentNullException() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            map.putIfAbsent(1, null);
        });

        assertEquals("Key or value is null in putIfAbsent(K key, V value)", exception.getMessage());
    }

    public void testGetNullException() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            map.get(null);
        });

        assertEquals("Key is null in get(K key)", exception.getMessage());
    }

    public void testContainsNullException() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            map.contains(null);
        });

        assertEquals("Key is null in contains(K key)", exception.getMessage());
    }
    public void testConstructorCapacityException() {
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(-4, HashManager.HashType.JCF7);
        });

        assertEquals("Illegal initial capacity: -4", exception.getMessage());
    }
    public void testConstructorLoadFactorException() {
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(0f, HashManager.HashType.JCF);
        });

        assertEquals("Illegal load factor: 0.0", exception.getMessage());
    }
    public void testUnknownHashType(){
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(HashManager.HashType.DINGUS);
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            map.put(1,3);
        });

        assertEquals("HashType is unknown", exception.getMessage());
    }

    public void testRemoveNullException(){
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            map.remove(null);
        });

        assertEquals("Key is null in remove(K key)", exception.getMessage());
    }
    public void testReplaceNullException(){
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            map.replace(null, null, null);
        });

        assertEquals("Key is null in replace()", exception.getMessage());
    }

    public void testGetMaxChainSize() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Assert.assertEquals(0, map.getMaxChainSize());
        map.put(1,3);
        Assert.assertEquals(1, map.getMaxChainSize());
    }

    public void testGetRehashesCounter() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Assert.assertEquals(0, map.getRehashesCounter());
        map.put(1,3);
        map.put(2,3);
        map.put(3,3);
        map.put(4,3);
        map.put(5,3);
        map.put(6,3);
        map.put(7,3);
        Assert.assertEquals(1, map.getRehashesCounter());
    }

    public void testGetTableCapacity() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Assert.assertEquals(8, map.getTableCapacity());
        map.put(1,3);
        map.put(2,3);
        map.put(3,3);
        map.put(4,3);
        map.put(5,3);
        map.put(6,3);
        map.put(7,3);
        Assert.assertEquals(16, map.getTableCapacity());
    }

    public void testGetLastUpdated() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Assert.assertEquals(0, map.getLastUpdated());
        map.put(0,3);
        Assert.assertEquals(0, map.getLastUpdated());
        map.put(7,3);
        Assert.assertEquals(7, map.getLastUpdated());
        map.put(8,3);
        Assert.assertEquals(0, map.getLastUpdated());
    }

    public void testGetNumberOfOccupied() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Assert.assertEquals(0, map.getNumberOfOccupied());
        map.put(1,3);
        Assert.assertEquals(1, map.getNumberOfOccupied());
        map.put(2,3);
        Assert.assertEquals(2, map.getNumberOfOccupied());
        map.put(9,5);
        Assert.assertEquals(2, map.getNumberOfOccupied());

    }
}
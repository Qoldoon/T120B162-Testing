package edu.ktu.ds.lab3.utils;

import junit.framework.TestCase;
import org.junit.Assert;

public class HashMapOaTest extends TestCase {

    public void testIsEmpty() {
        HashMapOa<Integer, Integer> map = new HashMapOa<>(8,0.5f, HashManager.HashType.DIVISION, HashMapOa.OpenAddressingType.DOUBLE_HASHING);
        Assert.assertTrue(map.isEmpty());
        map.put(1,3);
        Assert.assertFalse(map.isEmpty());
        map.remove(1);
        Assert.assertTrue(map.isEmpty());
    }

    public void testSize() {
        HashMapOa<Integer, Integer> map = new HashMapOa<>(8,0.5f, HashManager.HashType.DIVISION, HashMapOa.OpenAddressingType.QUADRATIC);
        Assert.assertEquals(0, map.size());
        map.put(1,3);
        Assert.assertEquals(1, map.size());
    }

    public void testClear() {
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        map.put(1,3);
        map.put(2,3);
        map.clear();
        Assert.assertEquals(new HashMapOa<Integer, Integer>().table, map.table);
    }

    public void testContains() {
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        map.put(1,3);
        map.put(2,3);
        Assert.assertTrue(map.contains(1));
        Assert.assertTrue(map.contains(2));
        Assert.assertFalse(map.contains(3));
    }

    public void testPutIfAbsent() {
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        map.put(1,3);
        map.put(2,3);

        map.putIfAbsent(1,60);
        Assert.assertEquals((Integer) 3, map.get(1));
        map.putIfAbsent(3,70);
        Assert.assertEquals((Integer) 70, map.get(3));
    }

    public void testPut() {
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>(1.0f, HashManager.HashType.DIVISION);
        map.put(1,3);
        map.put(2,3);
        Assert.assertEquals((Integer) 3, map.get(1));
        Assert.assertEquals((Integer) 3, map.get(2));
        map.put(1,60);
        Assert.assertEquals((Integer) 60, map.get(1));
        map.put(3,70);
        map.put(4,3);
        map.put(5,3);
        map.put(6,3);
        map.put(7,3);
        map.put(8,3);
        map.put(9,3);
        Assert.assertEquals((Integer) 70, map.get(3));
    }

    public void testGet() {
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        map.put(1,3);
        map.put(2,3);
        Assert.assertEquals((Integer) 3, map.get(1));
        Assert.assertEquals((Integer) 3, map.get(2));
    }

    public void testRemove() {
        HashMapOa<Integer, Integer> map = new HashMapOa<>(8,0.9f, HashManager.HashType.DIVISION, HashMapOa.OpenAddressingType.DOUBLE_HASHING);
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
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        map.put(1,3);
        map.put(2,3);
        Assert.assertEquals("1=3\n2=3",map.toString());
    }

    public void testGetRehashesCounter() {
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        Assert.assertEquals(0, map.getRehashesCounter());
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
        Assert.assertEquals(1, map.getRehashesCounter());
    }

    public void testGetTableCapacity() {
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        Assert.assertEquals(8, map.getTableCapacity());
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
        Assert.assertEquals(16, map.getTableCapacity());
    }

    public void testGetLastUpdated() {
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        Assert.assertEquals(0, map.getLastUpdated());
        map.put(1,3);
        Assert.assertEquals(1, map.getLastUpdated());
    }

    public void testGetNumberOfOccupied() {
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        Assert.assertEquals(0, map.getNumberOfOccupied());
        map.put(1,3);
        Assert.assertEquals(1, map.getNumberOfOccupied());
        map.remove(1);
        Assert.assertEquals(0, map.getNumberOfOccupied());
    }

    public void testReplace() {
        HashMapOa<Integer, Integer> map = new HashMapOa<>(8,0.9f, HashManager.HashType.DIVISION, HashMapOa.OpenAddressingType.QUADRATIC);
        map.put(1,3);
        map.put(9,3);
        map.replace(9,3,6);
        map.replace(2,3,6);
        Assert.assertEquals((Integer)6, map.get(9));
    }

    public void testContainsValue() {
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        map.put(1,3);
        Assert.assertTrue(map.containsValue(3));
        Assert.assertFalse(map.containsValue(4));
        Assert.assertFalse(map.containsValue("yes"));
    }
    public void testConstructorCapacityException() {
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>(-4, HashManager.HashType.JCF7);
        });

        assertEquals("Illegal initial capacity: -4", exception.getMessage());
    }
    public void testConstructorLoadFactorException() {
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>(0f, HashManager.HashType.JCF);
        });

        assertEquals("Illegal load factor: 0.0", exception.getMessage());
    }
    public void testContainsException(){
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            map.contains(null);
        });

        assertEquals("Key is null in contains(K key)", exception.getMessage());
    }
    public void testPutException(){
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            map.put(null, null);
        });

        assertEquals("Key or value is null in put(K key, V value)", exception.getMessage());
    }
    public void testRemoveException(){
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            map.remove(null);
        });

        assertEquals("Key is null in remove(K key)", exception.getMessage());
    }
    public void testGetException(){
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            map.get(null);
        });

        assertEquals("Key is null in get(K key)", exception.getMessage());
    }
    public void testReplaceException(){
        HashMapOa<Integer, Integer> map = new HashMapOa<Integer, Integer>();
        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            map.replace(null, 1,1);
        });

        assertEquals("Key is null in replace()", exception.getMessage());
    }
    public void testUnknownOaType(){
        HashMapOa<Integer, Integer> map = new HashMapOa<>(8,0.9f, HashManager.HashType.DIVISION, HashMapOa.OpenAddressingType.DINGUS);

        Throwable exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            map.put(1,3);
            map.put(9,3);
        });

        assertEquals("oaType is unknown", exception.getMessage());
    }

}
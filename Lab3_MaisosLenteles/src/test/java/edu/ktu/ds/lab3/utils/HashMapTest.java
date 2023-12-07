package edu.ktu.ds.lab3.utils;

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
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Assert.assertEquals(0, map.size);
        map.put(1,3);
        map.put(2,3);
        Assert.assertEquals(2, map.size);
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
        map.put(1,3);
        map.put(2,3);
        map.remove(2);
        Assert.assertNull(map.get(2));
    }

    public void testTestToString() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1,3);
        map.put(2,3);
        Assert.assertEquals("1=3\n2=3\n",map.toString());
    }

    public void testReplace() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1,3);
        map.put(2,3);

        map.replace(1,3,6);
        Assert.assertEquals((Integer) 6, map.get(1));

    }

    public void testContainsValue() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1,3);

        Assert.assertTrue(map.containsValue(3));
        Assert.assertFalse(map.containsValue(1));
    }
}
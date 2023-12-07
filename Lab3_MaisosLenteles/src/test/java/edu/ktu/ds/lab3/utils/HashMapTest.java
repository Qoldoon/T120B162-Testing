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
    }

    public void testClear() {
    }

    public void testContains() {
    }

    public void testPutIfAbsent() {
    }

    public void testPut() {
    }

    public void testGet() {
    }

    public void testRemove() {
    }

    public void testTestToString() {
    }

    public void testReplace() {
    }

    public void testContainsValue() {
    }

    public void testGetMaxChainSize() {
    }

    public void testGetRehashesCounter() {
    }

    public void testGetTableCapacity() {
    }

    public void testGetLastUpdated() {
    }

    public void testGetNumberOfOccupied() {
    }
}
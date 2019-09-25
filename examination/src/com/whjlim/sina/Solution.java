package com.whjlim.sina;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by BlackWatch on 2019/8/31 16:45
 */
public class Solution {

    private static final float loadFactory = 0.75f;
    private LinkedHashMap<Integer, Integer> map;
    private  int capacity;
    public Solution(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity, loadFactory, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > Solution.this.capacity;
            }
        };
    }

    public int get(int key) {
        Integer val = map.get(key);
        return val == null ? -1 : val.intValue();
    }

    public void put(int key, int value) {
        map.put(key, value);
    }
}

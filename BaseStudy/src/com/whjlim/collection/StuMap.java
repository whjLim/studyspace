package com.whjlim.collection;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BlackWatch on 2019/8/10 22:18
 */
public class StuMap {

    private static int MAXIMUM_CAPACITY = 1 << 30;

    static final int HASH_BITS = 0x7fffffff;

    static final int spread(int h) {
        return (h ^ (h >>> 16)) & HASH_BITS; //无符号右移动16位，那么返回的hash值必然小于2^16
    }

    int gg;

    public static void main(String[] args) {
        int[] ai = new int[]{1,2};
        int[] bi = Arrays.copyOf(ai, 10);
        bi[0] = 10;
        HashMap<String,String> hashMap = new HashMap<>();
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        List<Integer> list = new ArrayList<>();
        String a = null;
        String b = a;
        b = "123";
        b.hashCode();
        int c;
        StuMap stuMap = new StuMap();
        hashMap.put("whjLim","AAA");
        System.out.println(hashMap.put("whjLim","BBB"));
        System.out.println(b instanceof String);
        System.out.println(stuMap.gg);


    }

    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}

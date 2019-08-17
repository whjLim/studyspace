package com.whjlim.collection;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BlackWatch on 2019/8/10 22:18
 */
public class MyConcurrentMap<K,V> {

    /**
     * 获取Unsave类
     */
    private static Unsafe unsafe = null;
    private static Field getUnsafe = null;
    static {
        try {
            getUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            getUnsafe.setAccessible(true);
            unsafe = (Unsafe) getUnsafe.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static class Node<K,V> implements Map.Entry<K,V>{

        final int hash;
        final K key;
        volatile V val;
        volatile Node<K,V> next;

        Node(int hash, K key, V val, Node<K,V> next){
            this.hash = hash;
            this.key = key;
            this.val = val;
            this.next = next;
        }


        public K getKey() {return key;}

        public V getValue() { return val; }

        public V setValue(V value) {return null;}
    }

    int initCapacity = 16;
    static long SIZECTL;
    static long NUMS_BASIC;
    static long NUMS_ASHIFT;
    int sizectl;
    transient volatile Node<K,V>[] table; //直接new Node<K,V>[16] 是不行的
    transient volatile Integer[] nums = new Integer[20];
    void init(){
        int sc;
        while (table == null || table.length == 0){
            if((sc = sizectl) < 0)
                Thread.yield();
            else if(unsafe.compareAndSwapInt(this,SIZECTL,sc,-1)){
                int n = sc > 0 ? sc : 16;

            }
        }
    }

    //通过这个方法能得到i, 线程安全吗
    // 在获取头的时候，为何要用这个方法，不能直接数组取？
    Integer getNums(Integer[] nums, int i){
        return (Integer) unsafe.getObjectVolatile(nums, ((long)i<<NUMS_ASHIFT) + NUMS_BASIC);//((long)i << ASHIFT) + ABASE)
    }


    static {
        try {
            Class<?> k = MyConcurrentMap.class;
            SIZECTL = unsafe.objectFieldOffset
                    (k.getDeclaredField("sizectl"));
            Class<?> ak = Integer[].class;  //数组类型
            NUMS_BASIC = unsafe.arrayBaseOffset(ak);
            int scale = unsafe.arrayIndexScale(ak);//4
            NUMS_ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);//2
        }catch (Exception e){
            throw new Error(e);
        }

    }

    void isNotStatic(){
        System.out.println(this);
    }
    static void isStatic(){
        //static 方法不能使用this,想想也正常，static表面是类方法，自然不能有this表示的当前对象
        //System.out.println(this);
    }

    /**
    *@Author: whjLim
    *@Date: 0:46 2019/8/18
    *测试一下Unsave的使用，更方便理解ConcurrentHashMap
    */
    public void testUnsave(){
        MyConcurrentMap<String, String> myConcurrentMap = new MyConcurrentMap<>();
        Thread threadRead = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(unsafe.compareAndSwapInt(myConcurrentMap,SIZECTL,myConcurrentMap.sizectl,-1));
                }
            }
        });
        Thread threadWrite = new Thread(new Runnable() {
            int sc = 1;
            @Override
            public void run() {
                while (true){
                    System.out.println(myConcurrentMap.sizectl);
                    myConcurrentMap.sizectl = sc++;
                }
            }
        });

        threadRead.start();
        threadWrite.start();
    }


    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap<>();
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        MyConcurrentMap.Node<String,String> node = new MyConcurrentMap.Node<String,String>("whjLim".hashCode(),"whjLim","Good",null);
        MyConcurrentMap<String, String> myConcurrentMap = new MyConcurrentMap<>();
        //System.out.println(SIZECTL);
        //System.out.println(NUMS_BASIC);
        //System.out.println(unsafe.compareAndSwapInt(myConcurrentMap,SIZECTL,myConcurrentMap.sizectl,-1));
        //myConcurrentMap.sizectl = 12;
        //System.out.println(myConcurrentMap.sizectl);
        myConcurrentMap.nums[15] = 1024;
        System.out.println(myConcurrentMap.nums[15]);
        System.out.println(myConcurrentMap.getNums(myConcurrentMap.nums,15));
        for(int i = 0; i < 5; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){

                    }
                }
            });
            thread.start();
        }

    }
}

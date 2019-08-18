package com.whjlim.collection;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    static long TABLE_BASIC;
    static long TABLE_ASHIFT;
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
                table = (Node<K, V>[]) new Node<?,?>[n];
            }
        }
    }



    //通过这个方法能得到i, 线程安全吗
    // 在获取头的时候，为何要用这个方法，不能直接数组取？
    Integer getNums(Integer[] nums, int i){
        return (Integer) unsafe.getObjectVolatile(nums, ((long)i<<NUMS_ASHIFT) + NUMS_BASIC);//((long)i << ASHIFT) + ABASE)
    }

    static final <K,V>Node<K,V> tabAt(Node<K,V>[] tab, int i){
        return (Node<K, V>) unsafe.getObjectVolatile(tab,((long)i<<TABLE_ASHIFT)+TABLE_BASIC);
    }
    static final <K,V> boolean casTabAt(Node<K,V>[] tab, int i,
                                        Node<K,V> c, Node<K,V> v){
        return unsafe.compareAndSwapObject(tab, ((long)i << TABLE_ASHIFT)+TABLE_BASIC, c, v);
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

            Class<?> ac = Node[].class;
            TABLE_BASIC = unsafe.arrayBaseOffset(ac);
            scale = unsafe.arrayIndexScale(ac);
            TABLE_ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);

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

    public void testInitAndAdd(K key, V value){
        MyConcurrentMap<String, String> myConcurrentMap = new MyConcurrentMap<>();
        myConcurrentMap.init();
        int hash = key.hashCode();
        hash = (hash ^ (hash >>> 16))&0x7fffffff;
        MyConcurrentMap.Node<String,String> node = new MyConcurrentMap.Node<String,String>("whjLim".hashCode(),"whjLim","Good",null);
        casTabAt(table,4, null, new Node<K,V>(hash,key,value,null));
        System.out.println(tabAt(table, 4));
    }
    ConcurrentHashMap<Integer,Integer> intMap;
    /*
    * 测试1000万数据的插入, 无并发
    * */
    public void testBigNums(){
        intMap = new ConcurrentHashMap<>();
        long startTimes = System.currentTimeMillis();
        for(int i = 0; i < 10000000; i++){
            intMap.put(i,i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("单线程插入1000W，时间：" + (endTime - startTimes) + "ms");
    }

    /**
     * 小测试并发处理
     * @param currentNums
     */
    volatile int[] begin;
    volatile int[] end;
    public void testBigNums(int currentNums){
        intMap = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(currentNums);
        long startTimes = System.currentTimeMillis();
        begin = new int[currentNums];
        end = new int[currentNums];
        int segment = 10000000 / currentNums;
        int start = 0;
        int over = segment;
        for(int i = 0; i < currentNums; i++){
            begin[i] = start;
            end[i] = segment;
            start = segment;
            segment = segment*2;
        }
        end[currentNums-1] = 10000000;
        for(int i = 0; i < currentNums; i++){
            final int index = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    for(int j = begin[index], len = end[index]; j < len; j++){
                        intMap.put(j,j);
                    }
                }
            });
        }
        executor.shutdown();
        while (!executor.isTerminated()){
        }
        long endTime = System.currentTimeMillis();
        System.out.println(currentNums + "线程插入1000W，时间：" + (endTime - startTimes) + "ms");
        System.out.println(intMap.size());
    }



    public static void main(String[] args) {
        //MyConcurrentMap<String,String> myConcurrentMap = new MyConcurrentMap<>();
        //myConcurrentMap.testInitAndAdd("whjLim","Good");
        HashMap<String, String> hashMap = new HashMap<>();
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        MyConcurrentMap.Node<String,String> node = new MyConcurrentMap.Node<String,String>("whjLim".hashCode(),"whjLim","Good",null);
        MyConcurrentMap<String, String> myConcurrentMap = new MyConcurrentMap<>();
        myConcurrentMap.testBigNums(4);
        //System.out.println(SIZECTL);
        //System.out.println(NUMS_BASIC);
        //System.out.println(unsafe.compareAndSwapInt(myConcurrentMap,SIZECTL,myConcurrentMap.sizectl,-1));
        //myConcurrentMap.sizectl = 12;
        //System.out.println(myConcurrentMap.sizectl);
/*        myConcurrentMap.init();
        myConcurrentMap.table[0] = node;
        System.out.println(myConcurrentMap.table[0]);
        System.out.println(tabAt(myConcurrentMap.table,0));
        myConcurrentMap.testInitAndAdd("whjLim","good");
        myConcurrentMap.nums[15] = 1024;
        System.out.println(myConcurrentMap.nums[15]);
        System.out.println(myConcurrentMap.getNums(myConcurrentMap.nums,15));*/

    }
}

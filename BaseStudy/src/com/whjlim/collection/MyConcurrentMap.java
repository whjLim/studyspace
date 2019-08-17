package com.whjlim.collection;

import com.sun.javaws.exceptions.ErrorCodeResponseException;
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
    int sizectl;
    transient volatile Node<K,V>[] table;

    void init(){
        int sc;
        while (table == null || table.length == 0){
            if((sc = sizectl) < 0)
                Thread.yield();
            else if(unsafe.compareAndSwapInt(this,SIZECTL,sc,-1)){

            }
        }
    }

    static {
        try {
            Class<?> k = MyConcurrentMap.class;
            SIZECTL = unsafe.objectFieldOffset
                    (k.getDeclaredField("sizectl"));
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


    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap<>();
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        MyConcurrentMap.Node<String,String> node = new MyConcurrentMap.Node<String,String>("whjLim".hashCode(),"whjLim","Good",null);
        MyConcurrentMap<String, String> myConcurrentMap = new MyConcurrentMap<>();
        System.out.println(unsafe.compareAndSwapInt(myConcurrentMap,SIZECTL,myConcurrentMap.sizectl,-1));

    }
}

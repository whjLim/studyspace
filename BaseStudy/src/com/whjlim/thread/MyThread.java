package com.whjlim.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by BlackWatch on 2019/8/12 22:08
 *
 * 创建线程的方式
 */
public class MyThread {

    class TestThread extends Thread{
        @Override
        public void run() {
            System.out.println("这是通过继承 Thread线程类创建线程");
        }
    }

    class RunableTest implements Runnable{
        @Override
        public void run() {
            System.out.println("通过实现 Runnable 接口创建线程");
        }
    }

    class TestThreadPool{
        public void testThreadPool(){
            int count = 30;
            ExecutorService threadPool = Executors.newFixedThreadPool(10);
            while (count-->0){
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + "  is running ...");
                        try {
                            Thread.sleep(3000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
            threadPool.shutdown();
        }

    }

    public void createThreadTest(){
        TestThreadPool testThreadPool = new TestThreadPool();
        testThreadPool.testThreadPool();
    }



    public static void main(String[] args) {
        // private static final sun.misc.Unsafe U;
        //sun.misc.Unsafe U = sun.misc.Unsafe.getUnsafe();
       // U.compareAndSwapInt("String",6,1,1);
        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}

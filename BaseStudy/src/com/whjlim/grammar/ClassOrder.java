package com.whjlim.grammar;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * Created by BlackWatch on 2019/8/29 19:12
 */
public class ClassOrder{
    public static void main(String[] args) {
        Integer a = 123; // => Integar a = Intager.valueOf(123);
        Integer b = 123;
        Integer c = new Integer(123);
        Integer d = new Integer(123);

        System.out.println(a == b);//true
        System.out.println(c == d); //false
        System.out.println(1<<20);
    }
}
class Father{
    Father(){
        System.out.println("I am father...");
    }
    void FaFuncOver(){
        System.out.println("FaFuncOver()...");
    }
    void FaFunc(){
        System.out.println("FaFunc()...");
    }
}
class SanOne extends Father{
    SanOne(){
        //super();//必须写在开头
    }
    SanOne(int num){
        this();
        System.out.println(num);//不执行
    }
    void FaFunc(){
        System.out.println("我是子类，我重新了FaFunc..");
    }
}
/**
*@Author: whjLim
*@Date: 19:33 2019/8/29
*static 顺序验证
*/
class Parent{

    static int classNums = 0;
    static {
        System.out.println("我是父类静态代码块...");
    }
    {
        System.out.println("我是父类普通代码块...");
    }
    Parent(){
        System.out.println("我是父类构造方法...");
    }
}
class Sun extends Parent{
    static {
        System.out.println("我是子类静态代码块...");
    }
    {
        System.out.println("我是子类普通代码块...");
    }
    Sun(){
        System.out.println("我是子类构造方法...");
    }
}
package com.whjlim.grammar;

/**
 * Created by BlackWatch on 2019/8/29 18:55
 */
public class CommPackage {
    static TestName testName;
    TestName noStaticName;

    public static void testStatic() {
        testName = new TestName();
        System.out.println(testName);
    }

    public void testNotStatic(){
        testName = new TestName();
        noStaticName = new TestName();
    }

    static {
        System.out.println("我是静态代码块，我先执行...");
    }

    {
        System.out.println("我只是普通代码块，瑟瑟发抖，不知道啥时候执行...");
    }

    public CommPackage(){
        System.out.println("我是构造方法，我知道静态代码块先执行，但是我不知道普通代码块啥时候执行.");
    }

    public static void main(String[] args) {
        Parent parent = new Sun();
        Parent parent1 = new Sun();
        CommPackage commPackage = new CommPackage();
        CommPackage commPackage_B = new CommPackage();
    }
}

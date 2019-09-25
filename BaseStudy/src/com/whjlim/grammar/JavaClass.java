package com.whjlim.grammar;

/**
 * Created by BlackWatch on 2019/8/29 18:09
 */
public class JavaClass {
    public static void main(String[] args) {
        TestName testName = new TestName();

    }
}
class TestName{
    static {
        System.out.println("TestName => 我被加载了....");
    }
}

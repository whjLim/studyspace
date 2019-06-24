package com.whjlim.classload;

public class SupperClass {

    static {
        System.out.println("SuperClass Static init!");
    }



    public static int value = 123;

    public SupperClass(){
        System.out.println("SuperClass Builder init!");
    }

}




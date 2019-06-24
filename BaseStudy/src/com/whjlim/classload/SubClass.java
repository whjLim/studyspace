package com.whjlim.classload;

public class SubClass extends SupperClass{

    static {
        System.out.println("SubClass Static init!");
    }

    public SubClass(){
        System.out.println("SubClass Builder init!");
    }

}

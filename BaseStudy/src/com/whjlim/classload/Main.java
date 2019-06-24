package com.whjlim.classload;

public class Main {
    public static void main(String[] args) {
        //SupperClass[] sca = new SupperClass[5];
        //SupperClass supperClass = new SubClass();
        //SubClass subClass = new SubClass();

        final char[] value = {'a', 'b', 'c'};
        char[] temp = {'a', 't', 'm'};
        value[2] = 'd';
        System.out.println(value);
    }
}

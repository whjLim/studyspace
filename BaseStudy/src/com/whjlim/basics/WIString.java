package com.whjlim.basics;

/**
 * Created by BlackWatch on 2019/9/23 20:14
 * 探寻string 内存
 * jdk1.7之前， hotspot JVM中常量池位于方法区
 * jdk1.7之后，常量池从方法区移除，在堆中
 */
public class WIString {
    public static void main(String[] args) {

        String gg = "11";
        String s3 = new String("1") + new String("1");
        String beforIn = s3.intern();
        //String gg = "11";
        //System.out.println(s3 == gg);
       // String afterIn = s3.intern();
        // s3  beforIn gg afterIn
        System.out.println(s3 == beforIn);

        //System.out.println(s3 == afterIn);
        //System.out.println(beforIn == gg);
        //System.out.println(beforIn == afterIn);
        //System.out.println(gg == afterIn);
        /*

        * */

    }
}

package com.whjlim.basics;

/**
 * Created by BlackWatch on 2019/9/22 12:09
 */
public class Demo {

    final int NUMBER = 20;
    private static Student stuD = new Student();

    public static void main(String[] args) {
        Student stu = new Student();
        MidStudent mstu = new MidStudent();
        if(stu.name == mstu.name){
            System.out.println("同一个常量池中");
        }else{
            System.out.println("不同常量次中");
        }

        String s1 = "KNNDDD";
        String s2 = "KNN";
        String s3 = "DDD";
        String s4 = s2+s3;
        System.out.println(s1==s4);

    }

    public void test(){
        int WW = 99;
        String b = "字面量";

    }

}
class Teacher{
    String name = "teacher";
    int age = 30;
    boolean gender = true;
    Student stu;

    static boolean isHuman = true;

    public void say(){
        System.out.println("work hard !");
    }
}
class Student{
    String name = "student";
    int age = 18;
    boolean gender = false;
}

class MidStudent {
    String name = "student";
    int age = 18;
}






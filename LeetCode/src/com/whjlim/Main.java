package com.whjlim;

// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
import com.whjlim.string.LeetCodeString;

import java.text.Format;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

class LinkTree{
    final int MAX = 1100;
    int[] sum = new int[MAX<<2];
    int[] add = new int[MAX<<2];
    int[] num = new int[MAX];
    public void pushup(int rt){
        sum[rt] = sum[rt<<1]+sum[rt<<1|1];
    }
    public void build(int l, int r, int rt){
        if(l == r){
            sum[rt] = num[l];
            return;
        }
        int m = (l+r)>>1;
        build(l,m,rt<<1);
        build(m+1, r,rt<<1|1);
        pushup(rt);
    }
    public void pushdown(int rt, int l, int r){
        if(add[rt]!= 0){
            add[rt<<1] += add[rt];
            add[rt<<1|1] += add[rt];
            sum[rt<<1] += add[rt]*l;
            sum[rt<<1|1] += add[rt]*r;
            add[rt] = 0;
        }
    }
    public int query(int L, int R, int l, int r, int rt){
        if(L <= l && r <= R){
            return sum[rt];
        }
        int m = (l+r)>>1;
        pushdown(rt,m-l+1,r-m);
        int ans = 0;
        if(L <= m)ans += query(L,R,l,m,rt<<1);
        if(R >= m)ans += query(L,R,m+1,l,rt<<1|1);
        return ans;
    }
    public LinkTree(int[] num){
        this.num = num;
        build(1,1000,1);
    }
}

public class Main {

    private static Vector<Integer> vector = new Vector<Integer>();

    public static void main(String[] args) {


        LeetCodeString leetCodeString = new LeetCodeString();
        System.out.println(leetCodeString.buildString("2[m2[t]2[n]]"));

    }

    public void test(){
        while (true){

            for(int i = 0; i < 10; i++){
                System.out.println("加进去" + i);
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (vector){
                        System.out.println("*delete begin:" + vector.size());
                        for(int i = 0; i < vector.size(); i++){
                            System.out.println("delete->" + i);
                            vector.remove(i);
                        }
                        System.out.println("*delete end:" + vector.size());
                    }
                }
            });

            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized(vector){
                        for(int i = 0; i < vector.size(); i++){
                            System.out.println(vector.get(i));
                        }
                    }
                }
            });

            removeThread.start();
            printThread.start();
            while (Thread.activeCount()>20);

        }

    }
}
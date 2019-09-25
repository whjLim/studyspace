package com.whjlim.tencent;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by BlackWatch on 2019/9/1 19:56
 */
public class Main {

    //以最小值为划分点
    static class Position implements Comparable<Position>{
        int a;
        int b;
        public Position(int a, int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Position o) {
            if(a == o.a){
                return b - o.b;
            }else{
                return o.a - a;
            }
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//办公室
        Position[] positions = new Position[n];
        for(int i = 0; i < n; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            positions[i] = new Position(a,b);
        }
        Arrays.sort(positions);
        long res = 0;
        for(int i = 0; i < n; i++){
            res += (positions[i].a*i)+(positions[i].b*(n-i-1));
        }
        System.out.println(res);
  }
}

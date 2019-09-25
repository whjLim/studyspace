package com.whjlim.pinduoduo;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by BlackWatch on 2019/9/1 13:33
 */
public class Main {

    /*
    1<=M<=N<=8
    小梅N张
    小白M张
    * */

    public void winningPlan(String xm, String xb){
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        int S = sc.nextInt();
        String[] card = new String[S<<1];
        for(int i = 0,k = 0; i < S; i++){
            card[k++] = sc.nextLine();
            card[k++] = sc.nextLine();
        }

    }

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int n = sc.nextInt();
       int m = sc.nextInt();
       int k = sc.nextInt();
       int maxx = n, maxy = m;
       int minx = n, miny = m;
       while (k-->0){
           if(maxx*maxy > minx*miny){

           }else{

           }
       }

        /*
        有严格顺序的，
        * */
        /*
        n*m个数
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                dp[i][j] = i*j;
            }
        }
        第1大：n*m
        第2大: max(n*(m-1) or (n-1)*m)
        第3大: min(n*(n-1) or (n-1)*m)大剩下的
        第4大：
        * */

    }
    /*
    两枚骰子
    2： 1(1/2)  2(1/2)
    2： 1 (1/2) 2(1/2)
    结果：
    1：1/4
    2：3/4
    E = 1*1/4 + 2*3/4 = 7/4 = 1.75
    * */
}

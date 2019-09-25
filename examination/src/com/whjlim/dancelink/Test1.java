package com.whjlim.dancelink;

import java.util.Scanner;

/**
 * Created by BlackWatch on 2019/9/8 19:08
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        int[] robot = new int[n];
        int[] seeNums = new int[n];
        for(int i = 0; i < n; i++){
            robot[i] = sc.nextInt();
        }
        for(int i = 0; i < n; i++){
            int j = i-1;
            while (j >= 0 && robot[j] < robot[i])j--;
            if(j >= 0)
                seeNums[j]++;
        }
        //seeNums[n-1] = 0;
        int max = 0;
        for(int i = 1; i < n; i++){
            if(seeNums[i] > seeNums[max]){
                max = i;
            }
        }
        System.out.println(robot[max]);
    }
}

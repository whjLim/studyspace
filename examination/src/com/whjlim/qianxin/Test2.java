package com.whjlim.qianxin;

import java.util.Scanner;

/**
 * Created by BlackWatch on 2019/9/9 20:09
 */
public class Test2 {
    public static void main(String[] args) {
        System.out.println((1<<31)-1);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        n = (1<<n)-1;
        int[] values = new int[n];
        for(int i = 0; i < n; i++){
            values[i] = sc.nextInt();
        }
        int left = sc.nextInt();
        int right = sc.nextInt();
        int x = -1, y = -1;
        for(int i = 0; i < n; i++){
            if(values[i] == left){
                x = i;
            }
            if(values[i] == right){
                y = i;
            }
        }
        int res = -1;
        if(x != -1 && y != -1){//不在树上，值空
            /*
            0
            1 2
           3 4 5 6
           奇数：左  父节点 /2
           偶数：右  父节点 /2 -1
            0
            1 2
            3 4 5 6
            7 8 9 10 11 12 13 14
            * */
            int hl = hight(values, left);
            int hr = hight(values, right);
            while (hl > hr){
                if(x % 2 == 0){
                    x = x/2-1;
                }else{
                    x = x/2;
                }
                hl--;
            }
            while (hr > hl){
                if(y % 2 == 0){
                    y = y/2-1;
                }else{
                    y = y/2;
                }
                hr--;
            }
            if(hl == hr){
                while (values[x] != values[y]){
                    if(x % 2 == 0){
                        x = x/2-1;
                    }else{
                        x = x/2;
                    }
                    if(y % 2 == 0){
                        y = y/2-1;
                    }else{
                        y = y/2;
                    }
                    hl--;
                    hr--;
                }
                res = values[x];
            }
        }
        System.out.println(res);
    }
    static int hight(int[] vals, int v){
        int h = 1;
        int k = 0;
        while (vals[k] != v){
            if(vals[k] > v){
                k = k*2+1;
            }else{
                k = k*2+2;
            }
            h++;
        }
        return h;
    }
}

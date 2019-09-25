package com.whjlim.pinduoduo;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by BlackWatch on 2019/9/1 15:28
 */
public class Back {
     /*
    创建一个大根堆
     */
    /*int[] bigHeap;
    int headNums;
    int top;
    public void add(int val){
        if(top < headNums){
            bigHeap[top] = val;
        }else{

        }
    }*/

    public static int[] oldTopN(int[] nums, int n){
        int[] bigHeap = new int[n];
        int even = 0, old = 0;
        for(int i = 0, len = nums.length; i < len; i++){
            if((nums[i]&1) == 0){//偶数
                old++;
            }else{
                even++;
            }
        }
        int[] oldNums = new int[old];
        int[] evenNums = new int[even];
        for(int i = 0, j = 0, k = 0, len = nums.length; i < len; i++){
            if((nums[i]&1) == 0){//偶数
                oldNums[j++] = nums[i];
            }else{
                evenNums[k++] = nums[i];
            }
        }
        Arrays.sort(oldNums);
        Arrays.sort(evenNums);
        int k = 0;
        while (k < n && old > 0){
            bigHeap[k++] = oldNums[--old];
        }
        while (k < n && even > 0){
            bigHeap[k++] = evenNums[--even];
        }
        return bigHeap;
    }

    void print(){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String[] data = sc.nextLine().split(";");
            int n = Integer.valueOf(data[1]);
            String[] values = data[0].split(",");
            int[] nums = new int[values.length];
            for(int i = 0, len = values.length; i < len; i++){
                nums[i] = Integer.valueOf(values[i]);
            }
            int[] res = oldTopN(nums, n);
            System.out.print(res[0]);
            for(int i = 1; i < n; i++){
                System.out.print(',');
                System.out.print(res[i]);
            }
            System.out.println();
        }
    }



}

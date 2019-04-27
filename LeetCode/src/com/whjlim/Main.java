package com.whjlim;

import com.whjlim.string.LeetCodeString;

import java.util.Scanner;

/**
 * 黑白矩阵，是改变奇数，还是改变偶数
 */
public class Main {

    public static int n, m;
    public static int fx[] = new int[]{-1,1,0,0};
    public static int fy[] = new int[]{0,0,-1,1};

    public static boolean isOK(int[][] nums, int x, int y){
        boolean flag = true;
        boolean first = false;
        int befor = 0;
        for(int i = 0; i < 4; i++){
            int xi = x + fx[i];
            int yi = y + fy[i];
            if(xi >= n || xi < 0 || yi >= m || yi < 0)continue;
            if(nums[x][y] == nums[xi][yi]){
                flag =  false;
                break;
            }
            if(!first){
                first = true;
                befor = nums[xi][yi];
            }else if(befor != nums[xi][yi]){
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static boolean blackWhite(int[][] nums){
        boolean flag = true;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!isOK(nums, i, j)){
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
    public static int solveOld(int[][] nums, int x, int y){
        int res = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if((i+j)%2 == 0 && nums[i][j] != nums[x][y]){
                    res += 1;
                }
            }
        }
        return res;
    }
    public static int solveEven(int[][] nums, int x, int y){
        int res = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if((i+j)%2 != 0 && nums[i][j] != nums[x][y]){
                    res += 1;
                }
            }
        }
        return res;
    }

    public static int solve(int[][] nums){
        int res = n*m;
        int size = n*m;
        if(!blackWhite(nums)){
            for(int i  = 0; i < size; i+=2){
                int x1 = i / m;
                int y1 = i % m;
                for(int j = 1; j < size; j+=2){
                    int x2 = j / m;
                    int y2 = j % m;
                    System.out.println("("+x1 + "," + y1 + ")->"+"("+x2 + "," + y2 + ")");
                    if(nums[x1][y1] != nums[x2][y2]){
                        res = Math.min(res, solveOld(nums, x1, y1)+solveEven(nums, x2, y2));
                    }else{
                        int temp = nums[x2][y2];
                        nums[x2][y2] = nums[x1][y1]+1;
                        res = Math.min(res, solveOld(nums, x1, y1)+solveEven(nums, x2, y2)+1);
                        nums[x2][y2] = temp;
                    }

                }
            }
        }
        return res;
    }

    public void sodifferent(){
        Scanner cin = new Scanner(System.in);
        cin.nextLine();
        while(cin.hasNextInt())
        {
            int res = 0;
            n = cin.nextInt();
            m = cin.nextInt();
            int[][] nums = new int[n][m];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    nums[i][j] = cin.nextInt();
                }
            }
            System.out.println(solve(nums));
        }
    }

    public static void main(String[] args){
        LeetCodeString leetCodeString = new LeetCodeString();
        System.out.println(leetCodeString.periodOfString("byebyebye"));
    }
}

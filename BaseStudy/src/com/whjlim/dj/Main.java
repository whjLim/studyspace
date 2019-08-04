package com.whjlim.dj;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int p = in.nextInt();
            int c = in.nextInt();
            int a, b, t;
            int[][] matrix = new int[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0;  j< n; j++){
                    matrix[i][j] = 1<<29;
                }
            }
            for(int i = 0; i < n; i++)
                matrix[i][i] = 0;
            for(int i = 0; i < p; i++){
                a = in.nextInt();
                b = in.nextInt();
                t = in.nextInt();
                matrix[a][b] = t;
            }
            for(int k = 0; k < n; k++){
                for(int i = 0; i < n; i++){
                    for(int j= 0; j < n; j++){
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k]+matrix[k][j]);
                    }
                }
            }
            int res = 0;
            for(int i = 0; i < c; i++){
                int x = in.nextInt();
                res += matrix[0][x];
            }
            System.out.println(res);
        }
    }
}

/*
2 1 1
0 1 10
1
4 5 3
0 1 15
1 2 15
0 3 50
1 3 30
2 3 10
2
1
3
* */

package com.whjlim.dj;

import java.util.*;

public class Main {

    public static void sortArray(int[] boy, int[] goal, int m){
        for(int i = 0; i < m; i++){
            int k = i;
            for(int j = i+1; j < m; j++){
                if(boy[j] < boy[k]){
                    k = j;
                }
            }
            if(k != i){
                boy[i] = boy[i]^boy[k];
                boy[k] = boy[i]^boy[k];
                boy[i] = boy[i]^boy[k];

                goal[i] = goal[i]^goal[k];
                goal[k] = goal[i]^goal[k];
                goal[i] = goal[i]^goal[k];
            }
        }
    }

    public  static void sortArray(int[] boy, int[] goal, int l, int r){
        if (l < r){
            int mid = parment(boy, goal, l, r);
            sortArray(boy, goal, l, mid-1);
            sortArray(boy, goal, mid+1, r);
        }
    }

    public static int parment(int[] boy, int[] goal, int l, int r){
        int t1 = boy[l];
        int t2 = goal[l];
        while (l < r){
            while (r > l && boy[r] >= t1) r--;
            boy[l] = boy[r];
            goal[l] = goal[r];
            while (l < r && boy[l] <= t1)l++;
            boy[r] = boy[l];
            goal[r] = goal[l];
        }
        boy[l] = t1;
        goal[l] = t2;
        return l;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            int n = sc.nextInt();
            int[] hight = new int[n];
            int[] people = new int[n];
            for(int i = 0; i < n; i++){
                hight[i] = n-i;
                people[i] = i;
            }
            long startTimes = System.currentTimeMillis();
            sortArray(hight, people, n);
            long endTime = System.currentTimeMillis();
            System.out.println("排序时间：" + (endTime - startTimes) + "ms");
            int res = 0;
            for(int i = 0; i < n; i++){
                if(people[i] == i){
                    res++;
                    //System.out.print(hight[i]+"," + people[i] + "  ");
                }
                else{
                    res++;
                    i = people[i];
                }
                //System.out.print(hight[i]+"," + people[i] + "  ");*/
            }
            System.out.println(res);
        }
    }
}
/*
    public static void sortArray(int[] boy, int[] goal, int m){
        for(int i = 0; i < m; i++){
            int k = i;
            for(int j = i+1; j < m; j++){
                if(boy[j] < boy[k]){
                    k = j;
                }
            }
            if(k != i){
                boy[i] = boy[i]^boy[k];
                boy[k] = boy[i]^boy[k];
                boy[i] = boy[i]^boy[k];

                goal[i] = goal[i]^goal[k];
                goal[k] = goal[i]^goal[k];
                goal[i] = goal[i]^goal[k];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int m = sc.nextInt();
            if(n < 1){
                System.out.println(0);
                System.out.println();
            }
            if(n > 0 && m > 0){
                int[] boy = new int[m];
                int[] goal = new int[m];
                for(int i = 0; i < m; i++){
                    boy[i] = sc.nextInt();
                    goal[i] = sc.nextInt();
                    if(boy[i] > goal[i]){
                        int t = boy[i];
                        boy[i] = goal[i];
                        goal[i] = t;
                    }
                }
                Arrays.sort(boy);
                StringBuilder sb = new StringBuilder();
                sb.append(boy[0]);
                int res = 1;
                for(int i = 1; i < m; i++){
                    if(boy[i] != boy[i-1]){
                        res++;
                        sb.append(" ");
                        sb.append(boy[i]);
                    }
                }
                System.out.println(res);
                System.out.println(sb.toString());
            }
        }
    }
}
*/
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
10
69079936 236011312 77957850 653604087 443890802 277126428 755625552 768751840 993860213 882053548
【69,079,936】
【236,011,312 77,957,850】
【653,604,087 443,890,802 277,126,428】
【755,625,552】
【768,751,840】
【 993,860,213 882,053,548】
* */

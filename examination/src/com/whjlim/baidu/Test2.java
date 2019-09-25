package com.whjlim.baidu;

import java.util.Scanner;

/**
 * Created by BlackWatch on 2019/9/17 19:42
 */
public class Test2 {
    static class Edge{
        int u, v, next;
        public Edge(int u, int v, int next){
            this.u = u;
            this.v = v;
            this.next = next;
        }
    }
    static Edge[] e;
    static int[] head;
    static boolean visit[];
    static int top;
    static void init(int n, int m){
        top = 0;
        head = new int[n];
        for(int i = 0;i < n; i++){
            head[i] = -1;
        }
        e = new Edge[m];
        visit = new boolean[n];
    }
    static void add(int u, int v){
        e[top] = new Edge(u,v,head[u]);
        head[u] = top++;
    }
    static boolean dfs(int star, int end, int df){
        if(df > 2 || visit[star])return false;
        if(star == end)return true;
        visit[star] = true;
        boolean res = false;
        for(int i = head[star]; i != -1; i = e[i].next){
            res |= dfs(e[i].v, end, df+1);
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            init(n,m);
            for(int i = 0; i < m; i++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                add(a-1,b-1);
            }
            if(dfs(0, n-1,0)){
                System.out.println("POSSIBLE");
            }else{
                System.out.println("IMPOSSIBLE");
            }
        }
    }
}

package com.whjlim.baidu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by BlackWatch on 2019/9/17 20:20
 */
public class Test3 {
    static long Fa;
    static long Fb;
    static class Edge implements Comparable<Edge>{
        int u,v;
        long a,b;
        public Edge(int u, int v, long a, long b){
            this.u = u;
            this.v = v;
            this.a = a;
            this.b = b;
        }
        @Override
        public int compareTo(Edge o) {
            if(a*Fa+o.a*Fa < b*Fb+o.b*Fb){
                return -1;
            }else{
                return 1;
            }
        }
    }
    static int[] parent;
    static int find(int x){
        if(parent[x] == x){
            return x;
        }else{
            return parent[x] = find(parent[x]);
        }
    }
    static void union(int x,int y){
        parent[x] = y;
    }
    static void init(int n){
        parent = new int[n+1];
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m;
        n = sc.nextInt();
        m = sc.nextInt();
        Fa = sc.nextLong();
        Fb = sc.nextLong();
        Edge[] e = new Edge[m];
        for(int i = 0; i < m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            long a = sc.nextLong();
            long b = sc.nextLong();
            e[i] = new Edge(u,v,a,b);
        }
        Arrays.sort(e);
        Edge[] minTree = new Edge[n-1];
        int choos = 0;
        init(n);
        for(int i = 0, len = n-1; i < m && choos < len; i++){
            int x = parent[e[i].u];
            int y = parent[e[i].v];
            if(x != y){
                minTree[choos] = e[i];
                choos++;
            }
        }
        long res = 0;
        if(n!=0){
            long maxa = minTree[0].a;
            long maxb = minTree[0].b;
            for(int i = 1; i < choos; i++){
                if(minTree[i].a > maxa){
                    maxa = minTree[i].a;
                }
                if(minTree[i].b > maxb){
                    maxb = minTree[i].b;
                }
            }
            res = Fa*maxa + Fb*maxb;
        }
        System.out.println(res);


    }
}

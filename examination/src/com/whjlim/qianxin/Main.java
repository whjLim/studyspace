package com.whjlim.qianxin;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by BlackWatch on 2019/9/9 18:58
 */
public class Main {
    static class Edge{
        int u,v,next;
        Edge(int u, int v, int next){
            this.u = u;
            this.v = v;
            this.next = next;
        }
    }
    static Edge[] e;
    static int[] head;
    static int top;
    static boolean[] visit;
    static int res;

    static void init(int n){
        res = 0;
        top = 0;
        e = new Edge[n];
        head = new int[n];
        visit = new boolean[n];
        for(int i = 0; i < n; i++){
            head[i] = -1;
        }
    }

    static void add(int u, int v){
        e[top] = new Edge(u,v,head[u]);
        /*e[top].u = u;
        e[top].v = v;
        e[top].next = head[u];*/
        head[u] = top++;
    }

    static void dfs(int u){
        if(!visit[u]){
            visit[u] = true;
            res++;
        }else{
            return;
        }
        for(int i = head[u]; i != -1; i = e[i].next){
            dfs(e[i].v);
        }
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.nextLine();
        String B = sc.nextLine();
        String id = sc.nextLine();
        String[] pid = A.split(" ");
        Map<String, Integer> map = new HashMap<String, Integer>();
        int n = pid.length;
        for(int i = 0; i < n; i++){
            map.put(pid[i], i);
        }
        String[] ppid = B.split(" ");
        init(n);
        for(int i = 0; i < n; i++){
            if(!ppid[i].equals("0")){
                int u = map.get(pid[i]);
                int v = map.get(ppid[i]);
                add(v,u);
            }
        }
        dfs(map.get(id));
        System.out.println(res);


    }
}

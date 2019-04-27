package com.whjlim.string;

import java.util.ArrayList;
import java.util.List;

public class LeetCodeString {
    //
    public List<String> generateParenthesis(int n) {

        List<String> list = new ArrayList<>();
        return list;
    }

    //字符串最小正周期
    public int periodOfString(String string){
        int len = string.length();
        char[] str = string.toCharArray();
        int[] next = new int[len+1];
        int i = 0, j = -1;
        next[0] = -1;
        while (i < len){
            while (j != -1 && str[i] != str[j]){
                j = next[j];
            }
            next[++i] = ++j;
        }
        int t = len - next[len];
        int period = 0;
        if(len % t == 0){
            period = len / t;
        }else{
            period = 1;
        }
        return period;
    }

    /**
     * 684. 冗余连接
     *
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] res = new int[2];
        init(n);
        for(int i = 0; i < n; i++){
            int px = find(edges[i][0]);
            int py = find(edges[i][1]);
            if(px == py){
                res[0] = edges[i][0];
                res[1] = edges[i][1];
                break;
            }
            unit(px, py);
        }
        return res;
    }
    int[] parent = new int[1010];
    void init(int n){
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }
    }
    int find(int x){
        if(x != parent[x]){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    void unit(int x, int y){
        parent[x] = y;
    }


}

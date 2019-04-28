package com.whjlim.string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;




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
    /**
     *685. 冗余连接 II
     * 有向图，
     * 1. 某个点有两个入度，则必定是这个点的某一点需要删除
     * 2. 如果是形成环，这换上的变按顺序需要删掉一条
     * 3. 1和2刚好出现在同一图上,按条件1判断
     *    应该删掉，那个换种的一条
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] indegree = new int[n+1];
        int[] outdegree = new int[n+1];
        int[] res = new int[2];
        boolean loop = true;
        int towdegree = 0, ansIndex = 0;
        int[][] select = new int[n][2];
        int numSelect = 0;
        for(int i = 0; i < n; i++){
            indegree[edges[i][1]] += 1;
            outdegree[edges[i][0]] += 1;
            if(indegree[edges[i][1]] == 2){
                loop = false;
                towdegree = edges[i][1];
            }
        }
        Group group = new Group(n, n);
        for(int i = 0; i < n; i++){
            group.addEdge(edges[i][0], edges[i][1]);
        }
        group.initLoop();
        if(group.flag && !loop){// 有环有2度
            for(int i = 1; i < group.loopNodeNum; i++){
                if(group.loopNode[i] == towdegree){
                    res[0] = group.loopNode[i-1];
                    res[1] = group.loopNode[i];
                    return res;
                }
            }
        }else if(!loop){ //只有2度
            for(int i = 0; i < n; i++){
                if(edges[i][1] == towdegree){//这个边
                    if(indegree[edges[i][0]] == 0 && outdegree[edges[i][0]] == 1){

                    }else{
                        select[numSelect][0] = edges[i][0];
                        select[numSelect][1] = edges[i][1];
                        numSelect++;
                    }
                }
            }
            if(numSelect == 2){
                ansIndex = findLastConnection(edges, select);
            }else {
                res[0] = select[0][0];
                res[1] = select[0][1];
                return res;
            }
        }else {//只有环
            for(int i = 1; i < group.loopNodeNum; i++){
                select[numSelect][0] = group.loopNode[i-1];
                select[numSelect][1] = group.loopNode[i];
                numSelect++;
            }
            ansIndex = findLastConnection(edges, select);
        }

        res[0] = edges[ansIndex][0];
        res[1] = edges[ansIndex][1];
        return res;
    }
    /*
    * 都边满足，选择最后的那个序列
    * */
    public int findLastConnection(int[][] edges, int[][] select){
        int numEdge = edges.length;
        int numSelect = select.length;
        for(int i = 0; i < numSelect; i++){
            System.out.println(select[i][0] + "," + select[i][1]);
        }
        for(int i = numEdge-1; i >= 0; i--){
            for(int j = 0; j < numSelect; j++){
                if(edges[i][0] == select[j][0] && edges[i][1] == select[j][1])
                    return i;
            }
        }
        return -1;
    }
    class Group{
        int n, m;
        int[] loopNode;
        int loopNodeNum;
        public int[] head;
        public int total;
        public Edge[] edges;
        int step;
        int[] visit;
        boolean flag;
        class Edge{
            int u;
            int v;
            int next;
            Edge(){ }
            Edge(int u, int v, int next){
                this.u = u;
                this.v = v;
                this.next = next;
            }
        }
        public Group(int nodeNum, int edgeNum){

            n = nodeNum;
            m = edgeNum;
            total = 0;
            step = 0;
            head = new int[nodeNum+1];
            visit = new int[nodeNum+1];
            loopNode = new int[nodeNum+1];
            for(int i = 0; i <= nodeNum; i++){
                head[i] = -1;
            }
            flag = false;
            edges = new Edge[edgeNum];
        }
        public void addEdge(int u, int v){
            edges[total] = new Edge(u,v,head[u]);
            head[u] = total++;
            /*edges[total] = new Edge(v,u,head[v]);
            head[v] = total++;*/
        }

        void findLoop(int[] node, int s, int u, int index){
            node[index] = u;
            if(index != 0 && s == u){
                flag = true;
                loopNodeNum = index+1;
                for(int i = 0; i <= index; i++){
                    loopNode[i] = node[i];
                }
                return;
            }
            for(int i = head[u]; i != -1; i = edges[i].next){
                int v = edges[i].v;
                if(visit[v] == 0){
                    visit[v] = 1;
                    findLoop(node, s, v, index+1);
                }
            }
        }

        public void initLoop(){
            int[] node = new int[n];
            for(int i = 1; i <= n; i++){
                visit = new int[n+1];
                flag = false;
                findLoop(node, i, i, 0);
                if(flag)
                    break;
            }
        }
    };


}

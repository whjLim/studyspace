package com.whjlim.dancelink;

import java.util.*;

/**
 * Created by BlackWatch on 2019/9/8 19:32
 */
public class Test2 {
    public static class Cap{
        int x, y, z, step = 0;
        public Cap(int x, int y, int z, int step){
            this.x = x;
            this.y = y;
            this.z = z;
            this.step = step;
        }

        @Override
        public int hashCode() {
            Integer xx = x;
            Integer yy = y;
            Integer zz = z;
            int res = xx.hashCode();
            res = 31*res+yy.hashCode();
            res = 31*res+zz.hashCode();
            return res;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)return true;
            Cap cap = (Cap) obj;
            if(cap.x == this.x && cap.y == this.y && cap.z == this.z){
                return true;
            }else{
                return false;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        int k = sc.nextInt();
        /*
        动作:
        1: x加水
        2：y加水
        3：z加水
        4: x->y x->z, y->z
           y-x z-x, z- y
        * */
        Queue<Cap> que = new LinkedList<Cap>();
        Map<Cap,Integer> map = new HashMap<Cap, Integer>();
        boolean[][][] visit = new boolean[x+1][y+1][z+1];
        Cap cap = new Cap(0,0,0,0);
        que.offer(cap);
        map.put(cap,1);
        int res = -1;
        while (!que.isEmpty()){
            cap = que.poll();
            if(cap.x == k || cap.y == k || cap.z == k){
                res = cap.step;
                break;
            }
            if(cap.x == 0){ //1
                Cap next = new Cap(x, cap.y, cap.z, cap.step+1);
                if(!map.containsKey(next)){
                    que.offer(next);
                }
            }
            if(cap.y == 0){//2
                Cap next = new Cap(cap.x, y, cap.z, cap.step+1);
                if(!map.containsKey(next)){
                    que.offer(next);
                }
            }
            if(cap.z == 0){//3
                Cap next = new Cap(cap.x, cap.y, z, cap.step+1);
                if(!map.containsKey(next)){
                    que.offer(next);
                }
            }
            //x->y
            //x->z
            if(cap.x > 0){
                if(cap.y < y){
                    int dao = y - cap.y;
                    dao = dao > cap.x ? cap.x : dao;
                    Cap next = new Cap(cap.x-dao, cap.y+dao, cap.z, cap.step+1);
                    if(!map.containsKey(next)){
                        que.offer(next);
                    }
                }
                if(cap.z < z){
                    int dao = z - cap.z;
                    dao = dao > cap.x ? cap.x : dao;
                    Cap next = new Cap(cap.x-dao, cap.y, cap.z+dao, cap.step+1);
                    if(!map.containsKey(next)){
                        que.offer(next);
                    }
                }
            }
            //y-x
            //y-z
            if(cap.y > 0){
                if(cap.x < x){
                    int dao = x - cap.x;
                    dao = dao > cap.y ? cap.y : dao;
                    Cap next = new Cap(cap.x+dao, cap.y-dao, cap.z, cap.step+1);
                    if(!map.containsKey(next)){
                        que.offer(next);
                    }
                }
                if(cap.z < z){
                    int dao = z - cap.z;
                    dao = dao > cap.y ? cap.y : dao;
                    Cap next = new Cap(cap.x, cap.y-dao, cap.z+dao, cap.step+1);
                    if(!map.containsKey(next)){
                        que.offer(next);
                    }
                }
            }
            //z-x
            //z-y
            if(cap.z > 0){
                if(cap.x < x){
                    int dao = x - cap.x;
                    dao = dao > cap.z ? cap.z : dao;
                    Cap next = new Cap(cap.x+dao, cap.y, cap.z-dao, cap.step+1);
                    if(!map.containsKey(next)){
                        que.offer(next);
                    }
                }
                if(cap.y < y){
                    int dao = y - cap.y;
                    dao = dao > cap.z ? cap.z : dao;
                    Cap next = new Cap(cap.x, cap.y+dao, cap.z-dao, cap.step+1);
                    if(!map.containsKey(next)){
                        que.offer(next);
                    }
                }
            }
        }
        System.out.println(res);
    }
}

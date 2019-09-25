package com.whjlim.sina;

import java.util.Arrays;

/**
 * Created by BlackWatch on 2019/8/31 15:32
 */
public class Main {
    class Version implements Comparable<Version>{
        int[] version;
        public Version(String[] nums){
            version = new int[nums.length];
            for(int i = 0, len = nums.length; i < len; i++){
                version[i] = Integer.valueOf(nums[i]);
            }
        }
        @Override
        public int compareTo(Version o) {
            return compare(this.version, o.version, 0,0);
        }
        public int compare(int a[], int b[], int ai, int bi){
            if(ai == a.length && bi == b.length){
                return 0;
            }
            else if(ai == a.length && bi != b.length){
                return -1;
            }
            else if(ai != a.length && bi == b.length){
                return 1;
            }else{
                if(a[ai] == b[bi]){
                    return compare(a,b,ai+1,bi+1);
                }else{
                    return a[ai] - b[bi];
                }
            }
        }
    }
    public String numsToString(int[] nums){
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]);
        for(int i = 1, len = nums.length; i < len; i++){
            sb.append('.');
            sb.append(nums[i]);
        }
        return sb.toString();
    }
    public String getMinVersion(String[] list) {
        // 在这里编写代码
        Version[] versions = new Version[list.length];
        for(int i = 0, len = list.length; i < len; i++ ){
            String[] nums = list[i].trim().split("\\.");
            versions[i] = new Version(nums);
        }
        Arrays.sort(versions);
        return  numsToString(versions[0].version);
    }



    public static void main(String[] args) {
        Solution cache = new Solution(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
    }
}

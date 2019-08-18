package com.whjlim;

/**
 * Created by BlackWatch on 2019/8/14 0:01
 */
public class Dp {

    public static void main(String[] args) {
        Dp dp = new Dp();
        System.out.println(dp.rob(new int[]{1,2,3,1}));//166430
    }

    int res = 0;
    public int numDecodings(String s) {
        if(null == s || s.length() == 1)return 0;
        char[] c = s.toCharArray();
        int[] dp = new int[c.length+1];
        dp[c.length-1] = 1;
        dp[c.length] = 1;
        for(int i = c.length-1; i > 0; i--){
            if(c[i] == '0'){
                if(c[i-1] > '2' || c[i-1] == '0'){ // ==>无解
                    return 0;
                }
            }
        }
        dfs(c, dp, 0);
        return dp[0];
    }
    public int dfs(char[] c, int[] dp, int cur){
        //if(cur >= c.length)return 0;
        if(dp[cur] != 0)return dp[cur];
        if(cur+1 < c.length){
            if(c[cur+1]=='0'){ //下一个是0，一定要合，上面已经预判过了，不合法直接退出了
                //res++;
                dp[cur] = dfs(c,dp,cur+2);
            }else{

                if((c[cur]-'0')*10+(c[cur+1]-'0') < 27){ //当前可以跟下一个合
                    if(cur+2 < c.length && c[cur+2] == '0'){ //下一个等于0，木得办法, 不能合了
                        dp[cur] = dfs(c, dp, cur+1);
                    }else{
                        dp[cur] = dfs(c, dp, cur+2);
                        if(dp[cur] != 0){
                            //dp[cur] += 1;
                        }
                        res++;
                        dp[cur] += dfs(c, dp,cur+1);
                    }

                }else{
                    dp[cur] = dfs(c, dp, cur+1);
                }
            }
        }
        return dp[cur];
    }
    public int rob(int[] nums) {
        int odd = 0, even = 0;
        for(int i = 0, len = nums.length; i < len; i+=2){
            odd = nums[i];
        }
        for(int i = 1, len = nums.length; i < len; i+=2){
            even = nums[i];
        }
        System.out.println(odd + "," + even);
        return odd > even ? odd : even;
    }
}

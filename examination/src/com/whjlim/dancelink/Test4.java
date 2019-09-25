package com.whjlim.dancelink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BlackWatch on 2019/9/8 19:35
 */
public class Test4 {
    Map<Integer,String> map = new HashMap<Integer, String>();
    char[] code = "aABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public List<String> numCode(String s){
        if(s == null || s.length() == 0 || s.charAt(0) == '0'){
            return null;
        }
        char[] c = s.toCharArray();
        int[] dp = new int[c.length+1];
        dp[c.length] = dp[c.length-1] = 1;
        char[] str = new char[c.length+1];
        str[c.length] = '\\';
        str[c.length-1] = code[c[c.length-1]-'0'];
        for(int i = c.length-1; i > 0; i--){
            if (c[i] == '0'){
                if(c[i-1] > '2' || c[i-1] == 0){
                    return null;
                }
            }
        }
        dfs(c, dp,0);
        return null;
    }
    public int dfs(char[] c, int[] dp,int cur){
        if(dp[cur] != 0){
            return dp[cur];
        }
        if(cur+1 < c.length){
            if(c[cur+1] == '0'){
                dp[cur] = dfs(c, dp,cur+2);
            }else{
                if((c[cur]-'0')*10+(c[cur+1]-'0') < 27){
                    if(cur+2 < c.length && c[cur+2] == '0'){
                        dp[cur] = dfs(c, dp, cur+1);
                    }else{
                        dp[cur] = dfs(c, dp, cur+2);
                        dp[cur] += dfs(c, dp, cur+1);
                    }
                }else{
                    dp[cur] = dfs(c, dp, cur+1);
                }
            }
        }
        return dp[cur];
    }
    public static void main(String[] args) {

    }
}

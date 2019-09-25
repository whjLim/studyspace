package com.whjlim.pinduoduo;

/**
 * Created by BlackWatch on 2019/9/11 14:34
 */
public class Test3 {
    public int longSbuseq(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)return nums[0];
        int res = Integer.MIN_VALUE;
        int zeros = 0;
        for(int i = 0, len = nums.length; i < len; i++){
            if(zeros == 0){
                zeros++;
            }
        }
        int[] zeroIndex = new int[zeros];
        zeros = 0;
        for(int i = 0, len = nums.length; i < len; i++){
            zeroIndex[zeros++] = nums[i];
        }
        if(zeroIndex[0]== 0){
            res = 0;
        }else{
            int sub = nums[0];
            for(int i = 1; i < zeroIndex[0]; i++){
                res = Math.max(res, sub);
                sub *= nums[i];
            }
            res = Math.max(sub, res);
        }

        for(int i = 1, len = zeroIndex.length; i < len; i++){
            int sub = nums[zeroIndex[i]+1];
            for(int j = zeroIndex[i-1]+2; j < zeroIndex[i]; j++){
                res = Math.max(res, sub);
                sub *= nums[j];
            }
            res = Math.max(sub, res);
        }
        return res;
    }


}

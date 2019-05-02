package com.whjlim.array;

import java.util.PriorityQueue;
import java.util.Queue;

public class LeetCodeArray {
    /**
     * 买股票系列
     */
    /**
     * 121. 买卖股票的最佳时机
     * 1 次交易
     */
    public int maxProfit_121(int[] prices) {
        if(null == prices || prices.length == 0)return 0;
        int min = prices[0], res = 0;
        for(int i = 1, len = prices.length; i < len; i++){
            if(prices[i] > min){
                res = Math.max(res, prices[i]-min);
            }else{
                min = prices[i];
            }
        }
        return res;
    }
    /**
     * 122. 买卖股票的最佳时机 II
     */
    public int maxProfit_122(int[] prices) {
        if(null == prices || prices.length == 0)
            return 0;
        int buy = prices[0], res = 0;
        for(int i = 1, len = prices.length; i < len; i++){
            if(prices[i] < buy){
                buy = prices[i];
            }else if(prices[i] > buy){// 今天的价格大于买入的
                if(i+1 < len){//如果还有明天，看看明天的行情
                /*    if(prices[i+1] >= prices[i]){ // 涨，明天再卖

                    }else{ //会跌，今天赶紧卖了

                    }*/
                    if(prices[i+1] < prices[i]){// 明会跌，今天先卖掉，赚他一发，明天再买入
                        res += prices[i] - buy;
                        buy = prices[i+1];
                    }
                }else{
                    res += prices[i] - buy;
                }
            }

        }
        return res;
    }
    /**
     * 123. 买卖股票的最佳时机 III
     * 188. 188. 买卖股票的最佳时机 IV
     * 两道题差不多，一起搞了应该没有问题吧？
     * 思路错误，但是写的好像也不错，其他地方应该可以用到
     */
    public int maxProfit(int k, int[] prices) {
        if(k < 1 || null == prices || prices.length == 0)return 0;
        int buy = prices[0], index = 0;
        int[] sell = new int[prices.length];
        int res = 0;
        for(int i = 1, len = prices.length; i < len; i++){
            if(prices[i] < buy){// 今天更便宜，今天买
                buy = prices[i];
            }else{//涨了，看看明天情况
                if(i+1 < len){
                    if(prices[i+1] < prices[i]){//明天会跌，先卖出去在说
                        sell[index++] = prices[i] - buy;
                        buy = prices[i];
                    }
                }else{
                    sell[index++] = prices[i] - buy;
                }
            }
        }
        initBigHeap(sell, index);
        for(int i = 0; i < k; i++){
            if(index > 0){
                res += sell[0];
                sweap(sell, 0, index-1);
                index-=1;
                sweapHeap(sell,index,1);
            }
        }
       return res;
    }

    /**
     * 初始化大根堆
     *
     * @param nums
     * @param n
     */
    void initBigHeap(int[] nums, int n){
        for(int i = (n>>>1); i >= 1; i--){
            sweapHeap(nums, n, i);
        }
    }
    void sweapHeap(int[] nums, int n, int index){
        int left = index<<1;
        int right = (index<<1)+1;
        int temp = index;
        if(left <= n && nums[left-1] > nums[temp-1])
            temp = left;
        if(right <= n && nums[right-1] > nums[temp-1])
            temp = right;
        if(temp != index){
            sweap(nums, temp-1, index-1);
            sweapHeap(nums, n, temp);
        }
    }
    void sweap(int[] nums,int temp, int index){
        nums[temp] = nums[temp]^nums[index];
        nums[index] = nums[temp]^nums[index];
        nums[temp] = nums[temp]^nums[index];
    }
}

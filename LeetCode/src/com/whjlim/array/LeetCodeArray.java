package com.whjlim.array;

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
    public int maxProfit(int[] prices) {
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
}

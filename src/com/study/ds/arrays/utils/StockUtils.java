package com.study.ds.arrays.utils;

import java.util.Arrays;

public class StockUtils {
    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     *
     * Note that you cannot sell a stock before you buy one.
     *
     * Example 1:
     *
     * Input: [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     *              Not 7-1 = 6, as selling price needs to be larger than buying price.
     * Example 2:
     *
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices){
        if(prices.length == 0) return 0;

        int minStockPrice = Integer.MAX_VALUE;
        int profit = 0;

        for (int price : prices) {
            minStockPrice = Math.min(minStockPrice, price);
            profit = Math.max(profit, price - minStockPrice);
        }

        return profit;
    }

    /**
     * Say you have an array prices for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
     *
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     *
     * Example 1:
     *
     * Input: [7,1,5,3,6,4]
     * Output: 7
     * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
     *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
     *
     * Example 2:
     *
     * Input: [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
     *              engaging multiple transactions at the same time. You must sell before buying again.
     *
     * Example 3:
     *
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     *
     * Constraints:
     *
     * 1 <= prices.length <= 3 * 10 ^ 4
     * 0 <= prices[i] <= 10 ^ 4
     *
     * @param prices
     * @return
     */
    public static int maxProfit_II(int[] prices){
        if(prices.length == 0) return 0;

        int profit = 0;

        for(int i = 0; i < prices.length - 1; i ++){
            if(prices[i] < prices[i + 1])
                profit += prices[i + 1] - prices[i];
        }

        return profit;
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     *
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     *
     * Example 1:
     *
     * Input: prices = [3,3,5,0,0,3,1,4]
     * Output: 6
     * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
     *
     * Example 2:
     *
     * Input: prices = [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
     *
     * Example 3:
     *
     * Input: prices = [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     *
     * Example 4:
     *
     * Input: prices = [1]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= prices.length <= 105
     * 0 <= prices[i] <= 105
     *
     */
    public static int maxProfit_III(int[] prices){
        if(prices.length == 0) return 0;

        int leftMin = Integer.MAX_VALUE;
        int[] leftProfit = new int[prices.length];
        int currProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            leftMin = Math.min(leftMin, prices[i]);
            currProfit = Math.max(currProfit, prices[i] - leftMin);
            leftProfit[i] = currProfit;
        }

        int rightMax = Integer.MIN_VALUE;
        currProfit = 0;
        int[] rightProfit = new int[prices.length];
        for (int i = prices.length - 1; i >= 0; i--) {
            rightMax = Math.max(rightMax, prices[i]);
            currProfit = Math.max(currProfit, rightMax - prices[i]);
            rightProfit[i] = currProfit;
        }

        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            int currTotalMax = leftProfit[i] + rightProfit[i];
            maxProfit = Math.max(maxProfit, currTotalMax);
        }

        return maxProfit;
    }

    /**
     * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
     *
     * Design an algorithm to find the maximum profit. You may complete at most k transactions.
     *
     * Notice that you may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     *
     * Example 1:
     *
     * Input: k = 2, prices = [2,4,1]
     * Output: 2
     * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
     * Example 2:
     *
     * Input: k = 2, prices = [3,2,6,5,0,3]
     * Output: 7
     * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     *
     * Constraints:
     *
     * 0 <= k <= 109
     * 0 <= prices.length <= 104
     * 0 <= prices[i] <= 1000
     *
     */
    public static int maxProfit_IV(int k, int[] prices) {
        if(prices.length == 0) return 0;

        if(k > prices.length / 2) {
            int profit = 0;
            for(int i = 1; i < prices.length; i++) {
                if(prices[i] > prices[i-1]) {
                    profit += prices[i] - prices[i-1];
                }
            }
            return profit;
        }

        int[] buy = new int[k+1];
        int[] sell = new int[k+1];

        // Arrays.fill(buy, Integer.MIN_VALUE);
        for(int i = 0; i < buy.length; i ++){
            buy[i] = Integer.MIN_VALUE;
        }

        for(int price : prices) {
            for(int i = 0; i < k; i++) {
                sell[i] = Math.max(sell[i], buy[i] + price);
                buy[i] = Math.max(buy[i], sell[i+1] - price);
            }
        }
        return sell[0];
    }
}

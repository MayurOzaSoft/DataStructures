package com.study.ds.solution;

import java.util.Arrays;

/**
 * Unique Paths II
 *
 * {https://leetcode.com/problems/unique-paths-ii/}
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * Note: m and n will be at most 100.
 *
 * Example 1:
 *
 * Input:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 *
 */
public class Solution9 {
    public static void main(String[] args) {
        int[][] array = {   {0,0,0},
                            {0,1,0},
                            {0,0,0}
                        };

        System.out.println(uniquePathsWithObstacles(array));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if(obstacleGrid[0][0] == 1) return 0;

        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i ++){
            if(obstacleGrid[i][0] == 0){
                dp[i][0] = 1;
            } else
                break;
        }

        for(int i = 0; i < n; i ++){
            if(obstacleGrid[0][i] == 0){
                dp[0][i] = 1;
            } else
                break;
        }

        System.out.println(Arrays.deepToString(dp));

        for(int i = 1; i < m; i ++){
            for(int j = 1; j < n; j ++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                } else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}

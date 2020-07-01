/*
package com.ljw.acm.leetcode;

*/
/**
 * @author junwei.liang01@ucarinc.com
 * @date 2020/6/28 10:24
 *//*

public class Solution209 {
    public int minSubArrayLen(int s, int[] nums) {
        final int le = nums.length;
        int[][] a = new int[le + 1][le + 1];
        for (int i = 0; i < le; i++) {
            a[i][0] = nums[i];
            for (int j = i + 1; j < le; j++) {
                a[i][j] = a[i][j - 1] + nums[j];
                if (a[i][j]>7)
            }
        }
        return 0;
    }
}
*/

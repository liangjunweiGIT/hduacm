package com.ljw.acm.other;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * https://leetcode-cn.com/problems/edit-distance
 *
 * @author junwei.liang01@ucarinc.com
 * @date 2020/7/1 10:13
 */
public class ReplaceStr {
    public static void main(String[] args) {
        //intention execution horse ros
        System.out.println(replace("ab", "bc"));
    }

    /**
     * 动态规划
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int replace(String word1, String word2) {
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();
        int cl1 = c1.length + 1;
        int cl2 = c2.length + 1;
        int dp[][] = new int[cl1][cl2];
        for (int i = 0; i < cl1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j < cl2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < cl1; i++) {
            for (int j = 1; j < cl2; j++) {
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + (c1[i - 1] == c2[j - 1] ? 0 : 1));
            }
        }
        return dp[cl1 - 1][cl2 - 1];
    }
}

package com.ljw.acm.hdu1000_1099.hdu1011;

import java.util.Scanner;

/**
 * http://acm.hdu.edu.cn/showproblem.php?pid=1011
 * 有dfs内味了 dp一开始没有想到
 *
 * @author junwei.liang01
 * @date 2020/6/19 11:35
 */
public class Hdu1011 {
    private static int n, m;//房间数,士兵数
    private static final int MAX = 105;
    private static Room[] rooms = new Room[MAX];
    private static int[] flag = new int[MAX];
    private static int[][] dp = new int[MAX][MAX];

    static class Room {
        int bug;//虫子数
        int brain;//大脑
        int trooper;//士兵数
        int[] next = new int[MAX];//从当前房间可以直达的房间坐标
        int way;//连接的房间数 [0-num)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            n = sc.nextInt();
            m = sc.nextInt();
            if (n == -1 && m == -1) {
                return;
            }

            for (int i = 1; i <= n; i++) {
                if (rooms[i] == null) {
                    rooms[i] = new Room();
                }
                rooms[i].bug = sc.nextInt();
                rooms[i].brain = sc.nextInt();
                rooms[i].trooper = (rooms[i].bug + 19) / 20;
                rooms[i].way = 0;
                flag[i] = 0;
            }
            for (int i = 1; i <= n - 1; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                rooms[a].next[rooms[a].way++] = b;
                rooms[b].next[rooms[b].way++] = a;
            }
            if (m == 0) {
                System.out.println(0);
                continue;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    dp[i][j] = 0;
                }
            }
            dfs(1);
            System.out.println(dp[1][m]);
        }
    }

    /**
     * @param c 当前房间下标
     */
    private static void dfs(int c) {
        flag[c] = 1;
        for (int i = rooms[c].trooper; i <= m; i++) {
            dp[c][i] = rooms[c].brain;
        }
        for (int i = 0; i < rooms[c].way; i++) {
            if (flag[rooms[c].next[i]] == 0) {
                dfs(rooms[c].next[i]);
                for (int j = m; j >= rooms[c].trooper; j--) {
                    for (int k = 1; j + k <= m; k++) {
                        if (dp[rooms[c].next[i]][k] > 0) {
                            dp[c][j + k] = Math.max(dp[c][j + k], dp[c][j] + dp[rooms[c].next[i]][k]);
                        }
                    }
                }
            }
        }
    }
}

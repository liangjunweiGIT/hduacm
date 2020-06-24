package com.ljw.acm.hdu1400_1499.hdu1406;

import java.util.Scanner;

/**
 * @author junwei.liang
 * @date 2020/6/22 17:04
 */
public class Hdu1406 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int sz[] = new int[10001];//一个数组用于储存每个数的因子和
        for (int i = 1; i < 10001; i++) {
            for (int j = 2; i * j < 10001; j++) {
                sz[i * j] += i;
            }//每个数的倍数都加上这个数
        }
        int n = sc.nextInt();
        for (int pp = 0; pp < n; pp++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (x > y) {
                x ^= y;
                y ^= x;
                x ^= y;
            }//如果X>y就把两者换一下,这个运算式可以不用中间变量调换两者的值
            int sum = 0;//储存其中完数的个数
            for (; x <= y; x++) {
                if (sz[x] == x) {
                    sum++;
                }
            }//循环判断存在多少完数
            System.out.println(sum);
        }
    }
}

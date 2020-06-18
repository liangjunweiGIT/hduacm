package com.ljw.acm.hdu1000_1099.hdu1008;

import java.util.Scanner;

/**
 * 想多了 没考虑到路径
 *
 * @author junwei.liang
 * @date 2020/6/18 16:31
 */
public class Hdu1008 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        while (sc.hasNext()) {
            n = sc.nextInt();
            if (n == 0) {
                return;
            }
            int max = 0, min = 9999999, sum, curr = 0;
            for (int i = 0; i < n; i++) {
                curr = sc.nextInt();
                if (curr > max) {
                    max = curr;
                }
                if (curr < min) {
                    min = curr;
                }
            }
            sum = n * 5 + max * 6 + (max - curr) * 4;
            System.out.println(sum);
        }
    }
}

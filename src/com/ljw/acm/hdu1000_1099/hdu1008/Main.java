package com.ljw.acm.hdu1000_1099.hdu1008;

import java.util.Scanner;

/**
 * 才发现提交的时候不能带包名
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n, start = 0, end, t = 0;
            n = sc.nextInt();
            if (n == 0) {
                return;
            }
            for (int i = 0; i < n; i++) {
                end = sc.nextInt();
                if (end > start) {
                    t += (end - start) * 6;
                } else {
                    t += (start - end) * 4;
                }
                start = end;
                t += 5;
            }
            System.out.println(t);
        }
    }

}
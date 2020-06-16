package com.ljw.acm.hdu1000_1100.hdu1005;

import java.util.Scanner;

/**
 * http://acm.hdu.edu.cn/showproblem.php?pid=1005
 *
 * @author junwei.liang
 * @date 2020/6/15 16:45
 */
public class Hdu1005 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x, y, temp;
        while (scanner.hasNext()) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            int n = scanner.nextInt();
            if (a == 0 && b == 0 && n == 0) {
                return;
            }
            if (n == 1 || n == 2) {
                System.out.println(1);
                continue;
            }
            x = 1;
            y = 1;
            if (n > 48) {
                n %= 48;
            }
            for (int i = 3; i <= n; i++) {
                temp = y;
                y = (a * temp + b * x) % 7;
                x = temp;
            }
            System.out.println(y);
        }
    }
}

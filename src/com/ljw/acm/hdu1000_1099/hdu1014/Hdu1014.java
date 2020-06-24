package com.ljw.acm.hdu1000_1099.hdu1014;

import java.util.Scanner;

/**
 * @author junwei.liang01
 * @date 2020/6/22 16:22
 */
public class Hdu1014 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int step = scanner.nextInt();
            int mod = scanner.nextInt();
            if (gcd(step, mod) == 1) {
                System.out.printf("%10d%10d", step, mod);
                System.out.println("    Good Choice");
            } else {
                System.out.printf("%10d%10d", step, mod);
                System.out.println("    Bad Choice");
            }
            System.out.println();
        }
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

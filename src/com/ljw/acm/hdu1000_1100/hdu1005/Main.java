package com.ljw.acm.hdu1000_1100.hdu1005;

import java.util.Scanner;

/**
 * @author junwei.liang01
 * @date 2020/6/15 17:42
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int n = sc.nextInt();
            int[] arr = new int[48];
            arr[1] = 1;
            arr[2] = 1;
            for (int i = 3; i < 48; i++) {
                arr[i] = ((a * arr[i - 1] + b * arr[i - 2]) % 7);
            }
            System.out.println(arr[n % 48]);
        }
    }
}

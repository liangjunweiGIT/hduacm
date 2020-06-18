package com.ljw.acm.hdu1000_1099.hdu1004;

import java.util.Scanner;

/**
 * http://acm.hdu.edu.cn/showproblem.php?pid=1004
 * 一直是wrong
 *
 * @author junwei.liang01
 * @date 2020/6/15 15:15
 */
public class Hdu1004 {
    public static void main(String[] args) {
        int n;
        String[] arr;
        int[] arrn;
        String maxColor = null;
        Integer max;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            n = scanner.nextInt();
            if (n == 0) {
                scanner.close();
                break;
            }
            scanner.nextLine();
            arr = new String[n];
            arrn = new int[n];
            max = 0;
            maxColor = new String();
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextLine();
                arrn[i] = 1;
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[i].equals(arr[j])) {
                        arrn[i] = arrn[i] + arrn[j];
                        if (max < arrn[i]) {
                            max = arrn[i];
                            maxColor = arr[i];
                        }
                        break;
                    }
                }
            }
            System.out.println(maxColor);
        }
        scanner.close();
    }
}

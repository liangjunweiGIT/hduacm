package com.ljw.acm.hdu1000_1099.hdu1009;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author junwei.liang
 * @date 2020/6/18 17:51
 */
public class Hdu1009 {
    static class Pay {
        double j;
        double f;
        double x;
    }

    public static void main(String[] args) {
        int n;
        double m, sum;
        Pay[] pays = new Pay[1002];
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            m = sc.nextDouble();
            n = sc.nextInt();
            if (m == -1 && n == -1) {
                return;
            }
            for (int i = 0; i < n; i++) {
                if (pays[i] == null) {
                    pays[i] = new Pay();
                }
                pays[i].j = sc.nextDouble();
                pays[i].f = sc.nextDouble();
                if (pays[i].f == 0) {
                    pays[i].x = Double.MAX_VALUE;
                } else {
                    pays[i].x = pays[i].j / pays[i].f;
                }
            }
            Arrays.sort(pays, 0, n, new Comparator<Pay>() {
                @Override
                public int compare(Pay o1, Pay o2) {
                    if (o1.x > o2.x) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });
            sum = 0;
            for (int i = 0; i < n; i++) {
                if (m - pays[i].f > 0 || pays[i].f == 0) {
                    m -= pays[i].f;
                    sum += pays[i].j;
                } else {
                    sum += pays[i].x * m;
                    break;
                }
            }
            System.out.println(String.format("%.3f", sum));
        }
    }
}

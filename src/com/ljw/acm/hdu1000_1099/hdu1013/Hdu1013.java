package com.ljw.acm.hdu1000_1099.hdu1013;

import java.util.Scanner;

/**
 * @author junwei.liang
 * @date 2020/6/22 11:20
 */
public class Hdu1013 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        long s;//9位
        while (sc.hasNext()) {
            str = sc.next();
            if ("0".equals(str)) {
                return;
            }
            if (str.length() < 9) {
                System.out.println(roots(Long.valueOf(str)));
            } else {
                int sum = 0;
                int l = str.length() / 9;
                int last = str.length() % 9;
                for (int i = 0; i < l; i++) {
                    s = Long.valueOf(str.substring(i * 9, i * 9 + 9));
                    sum += roots(s);
                }
                if (last != 0) {
                    sum += Long.valueOf(str.substring(l * 9, l * 9 + last));
                }
                System.out.println(roots(sum));
            }
        }

    }

    private static long roots(long s) {
        if (s < 10) {
            return s;
        }
        int a = 0;
        while (s > 0) {
            a += s % 10;
            s /= 10;
        }
        return roots(a);
    }

}

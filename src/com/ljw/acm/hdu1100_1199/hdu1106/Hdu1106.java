package com.ljw.acm.hdu1100_1199.hdu1106;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author junwei.liang
 * @date 2020/6/22 16:35
 */
public class Hdu1106 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        while (sc.hasNext()) {
            str = sc.next();
            String[] split = str.split("5");
            Long[] ls = new Long[split.length];
            int le = 0;
            for (int i = 0; i < split.length; i++) {
                if ("".equals(split[i])) {
                    continue;
                }
                ls[le++] = Long.valueOf(split[i]);
            }
            Arrays.sort(ls, 0, le);
            for (int i = 0; i < le; i++) {
                System.out.print(ls[i]);
                if (i != le - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}

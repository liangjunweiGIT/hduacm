package com.ljw.acm.hdu1000_1099.hdu1006;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author junwei.liang01
 * //TODO
 * @date 2020/6/16 10:09
 */
public class Hdu1006 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.000");
        while (sc.hasNext()) {
            double d = sc.nextDouble();
            if (d == -1) {
                System.exit(1);
            }
            if (d == 0) {
                System.out.println(df.format(100));
                continue;
            }
            if (d >= 120) {
                System.out.println(df.format(0));
                continue;
            }
        }
    }
}

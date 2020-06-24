package com.ljw.acm.hdu1400_1499.hdu1402;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author junwei.liang01@ucarinc.com
 * @date 2020/6/22 17:58
 */
public class Main {

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        BigInteger a, b;
        while (cin.hasNext()) {
            a = cin.nextBigInteger();
            b = cin.nextBigInteger();
            System.out.println(a.multiply(b));
        }
    }
}

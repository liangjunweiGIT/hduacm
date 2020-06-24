package com.ljw.acm.leetcode;

/**
 * 67. 二进制求和
 */
public class Main67 {

    public static void main(String[] args) {
        System.out.println(addBinary("1", "111"));
    }

    public static String addBinary(String a, String b) {
        byte[] big;
        byte[] sm;
        if (a.length() > b.length()) {
            big = a.getBytes();
            sm = b.getBytes();
        } else {
            big = b.getBytes();
            sm = a.getBytes();
        }
        byte temp = 0;
        int i = big.length - 1, j = sm.length - 1;
        StringBuilder s = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int x = i >= 0 ? big[i] - 48 : 0;
            int y = j >= 0 ? sm[j] - 48 : 0;
            s.append((byte) ((x + y + temp) % 2));
            temp = (byte) ((x + y + temp) >> 1);
            i--;
            j--;
        }
        if (temp == 1) {
            s.insert(0, 1);
        }
        return s.toString();
    }
}

package com.ljw.acm.leetcode;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author liangjunwei
 * @date 2021-04-12 15:54
 */
public class Solution179 {

    public static void main(String[] args) {
        int[] arr = {999999998, 999999997,999999999};
        Solution179 solution179 = new Solution179();
        solution179.fastSort(arr);
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
            a.append(arr[i]);
        }
        System.out.print(a);

    }

    public String largestNumber(int[] nums) {
        sortJob2(nums, 0, nums.length - 1);
        StringBuilder a = new StringBuilder();
        for (int num : nums) {
            if (num ==0&&a.length()==0){
                continue;
            }
            a.append(num);
        }
        if (a.length()==0){
            return "0";
        }
        return a.toString();
    }

    private void fastSort(int[] array) {
        sortJob2(array, 0, array.length - 1);
    }

    private void sortJob2(int[] array, int low, int high) {
        int i = low, j = high, temp, key = array[low];
        while (i < j) {
            //交换key和右边应交换的值
            while (i < j && compare(array[j], key)) {
                j--;
            }
            temp = array[j];
            array[j] = array[i];
            array[i] = temp;
            //交换key和左边应交换的值
            while (i < j && !compare(array[i], key)) {
                i++;
            }
            temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
        if (low < i) {
            sortJob2(array, low, i - 1);
        }
        if (i < high) {
            sortJob2(array, i + 1, high);
        }
    }

    /**
     * @param a
     * @param b
     * @return a<b ?
     */
    private boolean compare(long a, long b) {
        if (a == b) {
            return true;
        }
        long i = a, j = b;
        int ai = 1, bj = 1;
        while (i >= 10) {
            i = i / 10;
            ai *= 10;
        }
        while (j >= 10) {
            j = j / 10;
            bj *= 10;
        }
        return a * bj*10 + b < b * ai*10 + a;
    }
}

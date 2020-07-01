package com.ljw.acm.leetcode;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author junwei.liang
 * @date 2020/6/24 10:54
 */
public class Solution16_2 {
    CountDownLatch latch = new CountDownLatch(4);
    AtomicInteger atomicInteger = new AtomicInteger(Integer.MIN_VALUE);

    class Worker implements Runnable {
        int begin;
        int end;
        int[] nums;
        int target;

        Worker(int begin, int end, int[] nums, int target) {
            this.begin = begin;
            this.end = end;
            this.nums = nums;
            this.target = target;

        }

        @Override
        public void run() {
            Integer call = call(begin, end, nums, target);
            if (call - target == 0) {
                atomicInteger.set(call);
                latch.countDown();
                latch.countDown();
                latch.countDown();
                latch.countDown();
                return;
            }
            if (Math.abs(atomicInteger.get() - target) > Math.abs(call - target)) {
                atomicInteger.set(call);
            }
            latch.countDown();
        }
    }

    private Integer call(int begin, int end, int[] nums, int target) {
        int minAbs = Integer.MAX_VALUE;
        int closestSum = 0;
        for (int i = begin; i < end; i++) {
            // 去除重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int currAbs = Math.abs(sum - target);
                if (currAbs < minAbs) {
                    minAbs = currAbs;
                    closestSum = sum;
                }
                if (sum < target) {
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;   // 跳过相同的数字
                    }
                } else if (sum > target) {
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;  // 跳过相同的数字
                    }
                } else {
                    return sum;   // sum == target
                }
            }
        }
        System.out.println(begin + "  " + end + "  " + closestSum);
        return closestSum;
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        if (n < 10) {
            return call(0, n - 2, nums, target);
        } else {
            int ci = n / 5;
            int yu = n % 5;
            for (int kk = 0; kk < 4; kk++) {
                new Thread(new Worker(kk * ci, (kk + 1) * ci, nums, target)).start();
            }
            new Worker(4 * ci, 5 * ci + yu - 2, nums, target).run();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return atomicInteger.get();
    }

    public static void main(String[] args) {
        final int[] ints = {-16, -2, 17, -16, 3, -7, -13, 20, -4, 12, 5, 13, -7, 0, 5, 4, -17, -16, 9, 1, 8, -6, 0, -8, 16, 10, -6, 9, -4, 12, 16, 5, 19, 2, -9, -17, -8, -17, 7, -10, 2, 20, -18, -20, -14, -6, 6, 17, -10, -8, 18, -15, 7, -9, 13, 13, -13, 3, 18, 10, 12, 16, -6, -19, -6, -13, 8, -5, 16, 5, 8, -3, -9, -9, -5, 14, 14, -13, -18, 13, 15, -3, 9, 14, 18, -14, -14, 1, 20, -4, -6, 0, 3, 15, 20, 20, 9, 13, -8, -1, -2, 6};
       /* final int[] ints1 = Arrays.copyOfRange(ints, 20, 30);
        for (int i = 0;i<ints1.length;i++){
            System.out.print(ints1[i]+" ");
        }
        System.out.println();*/
        System.out.println(new Solution16_2().threeSumClosest(ints, -58));
    }
}

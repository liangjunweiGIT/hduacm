package com.ljw.acm.leetcode;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author junwei.liang
 * @date 2020/6/24 10:54
 */
public class Solution16 {
    class Worker implements Callable<Integer> {
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
        public Integer call() {
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
            return closestSum;
        }
    }

    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.HOURS, new LinkedBlockingQueue());

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int closestSum = 0;
        int minAbs = Integer.MAX_VALUE;

        Future<Integer>[] futures = new Future[6];
        if (n < 10) {
            Future<Integer> submit = threadPoolExecutor.submit(new Worker(0, n, nums, target));
            try {
                closestSum = submit.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            int ci = n / 5;
            int yu = n % 5;
            for (int kk = 0; kk < 4; kk++) {
                futures[kk] = threadPoolExecutor.submit(new Worker(kk * ci, (kk + 1) * ci, nums, target));
            }
            futures[4] = threadPoolExecutor.submit(new Worker(4 * ci, 5 * ci + yu - 2, nums, target));
            for (int i = 0; i < 5; i++) {
                Future<Integer> future = futures[i];
                try {
                    final Integer integer = future.get();
                    final int abs = Math.abs(integer - target);
                    if (abs < minAbs) {
                        minAbs = abs;
                        closestSum = integer;
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        threadPoolExecutor.shutdown();
        return closestSum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution16().threeSumClosest(new int[]{-1, 2, 1, -4, -1, 2, 1, -4, -1, 2, 1, -4, -1, 2, 1, -4, -1, 2, 1, -4, -1, 2, 1, -4, -1, 2, 1, -4, -1, 2, 1, -4, -1, 2, 1, -4, -1, 2, 1, -4, -1, 2, 1, -4}, 1));
    }
}

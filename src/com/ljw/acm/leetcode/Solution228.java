package com.ljw.acm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的有序整数数组 nums 。
 * <p>
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * <p>
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * <p>
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/summary-ranges
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author liangjunwei
 * @date 2021-01-11 10:50
 */
public class Solution228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        int left = 0;
        int right;
        while (left < nums.length) {
            right = left;
            while (right < nums.length - 1 && nums[right + 1] - nums[right] == 1) {
                right++;
            }
            if (left == right) {
                res.add(nums[left] + "");
            } else {
                res.add(nums[left] + "->" + nums[right]);
            }
            left = right + 1;
        }
        return res;
    }

    public static void main(String[] args) {
    }


}

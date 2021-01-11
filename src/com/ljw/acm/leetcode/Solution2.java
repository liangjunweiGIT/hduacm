package com.ljw.acm.leetcode;

/**
 * 2. 两数相加
 * https://leetcode-cn.com/problems/add-two-numbers/
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 *
 * @author liangjunwei
 * @date 2021-01-10 15:07
 */
public class Solution2 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode re = new ListNode();
        int yu = 0;
        ListNode temp = re;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + yu;
            yu = sum / 10;
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l2 != null || l1 != null) {
            if (l2 != null) {
                l1 = l2;
            }
            while (l1 != null) {
                int sum = l1.val + yu;
                yu = sum / 10;
                temp.next = new ListNode(sum % 10);
                temp = temp.next;
                l1 = l1.next;
            }
        }
        if (yu != 0) {
            temp.next = new ListNode(yu);
        }
        return re.next;
    }

    public static void main(String[] args) {
        ListNode node3 = new ListNode(9);
        ListNode node2 = new ListNode(9, node3);
        ListNode l1 = new ListNode(9, node2);

        ListNode l2 = new ListNode(9);

        Solution2 solution2 = new Solution2();
        ListNode node = solution2.addTwoNumbers(l1, l2);
        while (node != null) {
            System.out.print(node.val + ",");
            node = node.next;
        }
    }
}

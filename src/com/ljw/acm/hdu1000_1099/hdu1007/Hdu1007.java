package com.ljw.acm.hdu1000_1099.hdu1007;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

/**
 * http://acm.hdu.edu.cn/showproblem.php?pid=1007
 * 单纯的剪枝 不能只比较相邻 x和y都是
 * @author junwei.liang01
 * @date 2020/6/16 15:14
 */
public class Hdu1007 {
    static class Node {
        Double x;
        Double y;

    }

    private static double calculation(Node node1, Node node2) {
        return Math.sqrt(Math.pow((node1.x - node2.x), 2) + Math.pow((node1.y - node2.y), 2));
    }

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");
        Node[] nodes = new Node[100005];
        Node[] nodes2 = new Node[100005];
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            for (int i = 0; i < n; i++) {
                if (nodes[i] == null) {
                    nodes[i] = new Node();
                }
                nodes[i].x = sc.nextDouble();
                nodes[i].y = sc.nextDouble();
            }
            Arrays.sort(nodes, 0, n, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if (Objects.equals(o1.x, o2.x)) {
                        return 0;
                    }
                    return o1.x > o2.x ? 1 : -1;
                }
            });
            double min = Double.MAX_VALUE;
            for (int i = 1; i < n; i++) {
                if (nodes[i].x - nodes[i - 1].x < min && Math.abs(nodes[i].y - nodes[i - 1].y) < min) {
                    double calculation = calculation(nodes[i], nodes[i - 1]);
                    if (calculation < min) {
                        min = calculation;
                    }
                }
            }
            int j = 0;
            for (int i = 0; i < n; i++) {
                if ((i != 0 && nodes[i].x - nodes[i - 1].x < min) || (i != n - 1 && nodes[i].x - nodes[i + 1].x < min)) {
                    nodes2[j++] = nodes[i];
                }
            }
            Arrays.sort(nodes2, 0, j, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if (Objects.equals(o1.y, o2.y)) {
                        return 0;
                    }
                    return o1.y > o2.y ? 1 : -1;
                }
            });
            for (int i = 1; i < j; i++) {
                if (Math.abs(nodes2[i].x - nodes2[i - 1].x) < min && nodes2[i].y - nodes2[i - 1].y < min) {
                    double calculation = calculation(nodes2[i], nodes2[i - 1]);
                    if (calculation < min) {
                        min = calculation;
                    }
                }
            }
            System.out.println(df.format(min / 2));
        }
    }
}

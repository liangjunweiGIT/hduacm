package com.ljw.acm.hdu1000_1100.hdu1007;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * http://acm.hdu.edu.cn/showproblem.php?pid=1007
 *
 * @author junwei.liang01
 * @date 2020/6/16 11:14
 */
public class Hdu1007 {
    static class Node {
        Double x;
        Double y;
    }

    private static double calculation(Node node1, Node node2) {
        return Math.sqrt(Math.pow((node1.x - node2.x), 2) + Math.pow((node1.y - node2.y), 2));
    }

    private static void clear(Node[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].x = null;
            nodes[i].y = null;
        }
    }

    public static void main(String[] args) {
        Node[] nodes = {new Node(), new Node(), new Node(), new Node()};
        Scanner sc = new Scanner(System.in);
        double max;
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            for (int i = 0; i < n; i++) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                if (nodes[1].x == null || nodes[1].x > x) {
                    nodes[1].x = x;
                    nodes[1].y = y;
                }
                if (nodes[2].x == null || nodes[2].x < x) {
                    nodes[2].x = x;
                    nodes[2].y = y;
                }
                if (nodes[3].y == null || nodes[3].y < y) {
                    nodes[3].x = x;
                    nodes[3].y = y;
                }
                if (nodes[0].y == null || nodes[0].y > y) {
                    nodes[0].x = x;
                    nodes[0].y = y;
                }
            }
            if (n == 1) {
                System.out.println(0);
                clear(nodes);
                continue;
            }
            max = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = i + 1; j < 4; j++) {
                    double calculation = calculation(nodes[i], nodes[j]);
                    if (max < calculation) {
                        max = calculation;
                    }
                }
            }
            clear(nodes);
            System.out.println(new BigDecimal(max / 2 + "").setScale(2, BigDecimal.ROUND_UP));
        }
    }
}

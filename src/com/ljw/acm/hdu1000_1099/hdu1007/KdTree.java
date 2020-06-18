package com.ljw.acm.hdu1000_1099.hdu1007;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author junwei.liang01
 * 方差优化KD树
 * @date 2020/6/17 15:59
 */
public class KdTree {
    static int MAX = 100005;
    private static Node[] tree = new Node[MAX], a = new Node[MAX];
    private static int[] dv = new int[MAX];
    private static int nowDv, n, mid;
    private static Node kdans, qu;

    static class Node {
        double[] p = new double[2];
        int id;
        double dis;
    }

    private static double getDis(Node a, Node b) {
        double re = 0;
        for (int i = 0; i < 2; ++i) {
            re += (a.p[i] - b.p[i]) * (a.p[i] - b.p[i]);
        }
        return re;
    }

    /**
     * 判断按哪个维度排序
     *
     * @param l
     * @param r
     * @return
     */
    private static int getD(int l, int r) {
        int d = 0;
        double now = -1;
        for (int i = 0; i < 2; ++i) {
            double ave = 0, sum = 0;
            for (int j = 0; j < r; ++j) {
                ave += tree[j].p[i];
            }
            ave /= r - l + 1;
            for (int j = 0; j < r; ++j) {
                sum += (tree[j].p[i] - ave) * (tree[j].p[i] - ave);
            }
            sum /= (r - l + 1) * 1.0;
            if (sum > now) {
                now = sum;
                d = i;
            }
        }
        return d;
    }

    /**
     * 构建kd树
     *
     * @param l
     * @param r
     * @param d 因做方差优化所以不是x,y交替分组
     */
    private static void builtTree(int l, int r, int d) {
        if (l > r) {
            return;
        }
        d = getD(l, r);
        nowDv = d;
        mid = l + r / 2;
        dv[mid] = d;
        Arrays.sort(tree, l, r, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.p[nowDv] == o2.p[nowDv]) {
                    return 0;
                }
                return o1.p[nowDv] > o2.p[nowDv] ? 1 : -1;
            }
        });
        builtTree(l, mid - 1, d ^ 1);
        builtTree(mid + 1, r, d ^ 1);
    }

    private static void searchTree(int l, int r, Node q) {
        if (l > r) {
            return;
        }
        double nowd = getDis(tree[mid], q);
        tree[mid].dis = nowd;
        if (nowd < kdans.dis && tree[mid].id != q.id) {
            kdans.dis = nowd;
            kdans.id = tree[mid].id;
        }
        double t = q.p[dv[mid]] - tree[mid].p[dv[mid]];
        if (t < 0) {
            searchTree(l, mid - 1, q);
            if (kdans.dis > t * t && l != r) {
                searchTree(mid + 1, r, q);
            }
        } else {
            searchTree(mid + 1, r, q);
            if (kdans.dis > t * t && l != r) {
                searchTree(l, mid - 1, q);
            }
        }
    }

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == 0) {
                return;
            }
            for (int i = 0; i < n; i++) {
                if (tree[i] == null) {
                    tree[i] = new Node();
                }
                tree[i].id = i;
                tree[i].p[0] = sc.nextDouble();
                tree[i].p[1] = sc.nextDouble();
                a[i] = tree[i];
            }
            builtTree(0, n, 0);
            kdans.dis = Double.MAX_VALUE;
            for (int i = 1; i <= n; ++i) {
                qu = a[i];
                qu.id = i;
                searchTree(1, n, qu);
            }
            kdans.dis = Math.sqrt(kdans.dis);
            kdans.dis /= 2.0;
            System.out.println(df.format(kdans.dis));
        }
    }
}

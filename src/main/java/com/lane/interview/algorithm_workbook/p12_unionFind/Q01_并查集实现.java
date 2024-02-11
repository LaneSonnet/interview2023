package com.lane.interview.algorithm_workbook.p12_unionFind;

/**
 * @ Author:  duenpu
 * @ Date  :  20:24 2024/2/11
 */
public class Q01_并查集实现 {
    // 数组实现
    public int MAXN = 1000001;

    public int[] father = new int[MAXN];

    public int[] size = new int[MAXN];

    public int[] help = new int[MAXN];

    public void init(int n) {
        for (int i = 0; i <= n; i++) {
            father[i] = i;
            size[i] = 1;
        }
    }

    public int findFather(int i) {
        int hi = 0;
        while (i != father[i]) {
            help[hi] = i;
            i = father[i];
            hi++;
        }
        for (hi--; hi >= 0; hi--) {
            father[help[hi]] = i;
        }
        return i;
    }

    public Boolean isSameSet(int a, int b) {
        return findFather(a) == findFather(b);
    }

    public void union(int a, int b) {
        int heada = findFather(a);
        int headb = findFather(b);
        if (heada != headb) {
            int sizeA = size[heada];
            int sizeB = size[headb];
            if (sizeA >= sizeB) {
                father[headb] = heada;
                size[heada] += size[headb];
            } else {
                father[heada] = headb;
                size[headb] += size[heada];
            }
        }
    }

}

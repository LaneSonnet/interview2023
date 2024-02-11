package com.lane.interview.algorithm_workbook.p12_unionFind;

/**
 * https://leetcode.cn/problems/number-of-provinces/
 *
 * @ Author:  duenpu
 * @ Date  :  20:46 2024/2/11
 */
public class Q02_省份问题 {
    public int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets;
    }

    public class UnionFind {
        int[] parents;
        int[] size;
        int[] help;
        int sets;

        public UnionFind(int N) {
            parents = new int[N];
            size = new int[N];
            help = new int[N];
            sets = 0;
            for (int i = 0; i < N; i++) {
                parents[i] = i;
                size[i] = 1;
                sets++;
            }
        }

        public int findFather(int i) {
            int hi = 0;
            while (i != parents[i]) {
                help[hi] = i;
                i = parents[i];
                hi++;
            }
            for (hi--; hi >= 0; hi--) {
                parents[help[hi]] = i;
            }
            return i;
        }

        public int getSets() {
            return sets;
        }

        public void union(int a, int b) {
            int ahead = findFather(a);
            int bhead = findFather(b);
            if (ahead != bhead) {
                int aSize = size[ahead];
                int bSize = size[bhead];
                if (aSize >= bSize) {
                    parents[bhead] = ahead;
                    size[ahead] += size[bhead];
                } else {
                    parents[ahead] = bhead;
                    size[bhead] += size[ahead];
                }
                sets--;
            }
        }
    }
}

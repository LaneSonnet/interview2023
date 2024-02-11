package com.lane.interview.algorithm_workbook.p12_unionFind;

import com.lane.interview.algorithm.day10.Demo5_NumberOfIslandsII;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  21:02 2024/2/11
 */
public class Q03_岛屿数量 {

    //https://leetcode.cn/problems/number-of-islands/description/
    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        UnionFind1 unionFind1 = new UnionFind1(grid);
        for (int i = 1; i < col; i++) {
            if (grid[0][i - 1] == '1' && grid[0][i] == '1') {
                unionFind1.union(0, i - 1, 0, i);
            }
        }
        for (int j = 1; j < row; j++) {
            if (grid[j - 1][0] == '1' && grid[j][0] == '1') {
                unionFind1.union(j - 1, 0, j, 0);
            }
        }
        // 注意外层row，内层col，要不然越界
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (grid[i][j - 1] == '1' && grid[i][j] == '1') {
                    unionFind1.union(i, j - 1, i, j);
                }
                if (grid[i - 1][j] == '1' && grid[i][j] == '1') {
                    unionFind1.union(i - 1, j, i, j);
                }
            }
        }
        return unionFind1.sets;
    }

    public class UnionFind1 {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int col;
        private int sets;

        public UnionFind1(char[][] board) {
            col = board[0].length;
            sets = 0;
            int row = board.length;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (board[r][c] == '1') {
                        int i = index(r, c);
                        parent[i] = i;
                        size[i] = 1;
                        sets++;
                    }
                }
            }
        }

        // (r,c) -> i
        private int index(int r, int c) {
            return r * col + c;
        }

        // 原始位置 -> 下标
        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int r1, int c1, int r2, int c2) {
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }

    }

    //https://leetcode.cn/problems/number-of-islands-ii/description/

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind2 uf = new UnionFind2(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    public class UnionFind2 {
        private int[] parent;
        private int[] size;
        private int[] help;
        private final int row;
        private final int col;
        private int sets;

        public UnionFind2(int m, int n) {
            row = m;
            col = n;
            sets = 0;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        private void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;
            }
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            if (size[i1] == 0 || size[i2] == 0) {
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int connect(int r, int c) {
            int index = index(r, c);
            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sets++;
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }
            return sets;
        }

    }
}

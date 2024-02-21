package com.lane.interview.algorithm_workbook.p14_dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  23:19 2024/2/19
 */
public class Q12_俄罗斯套娃 {
    // https://leetcode.cn/problems/russian-doll-envelopes
    class Solution {
        public int maxEnvelopes(int[][] envelopes) {
            if (envelopes.length == 0) {
                return 0;
            }

            int n = envelopes.length;
            Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

            List<Integer> f = new ArrayList<>();
            f.add(envelopes[0][1]);
            for (int i = 1; i < n; ++i) {
                int num = envelopes[i][1];
                if (num > f.get(f.size() - 1)) {
                    f.add(num);
                } else {
                    int index = binarySearch(f, num);
                    f.set(index, num);
                }
            }
            return f.size();
        }

        public int binarySearch(List<Integer> f, int target) {
            int low = 0, high = f.size() - 1;
            while (low < high) {
                int mid = (high - low) / 2 + low;
                if (f.get(mid) < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }

}

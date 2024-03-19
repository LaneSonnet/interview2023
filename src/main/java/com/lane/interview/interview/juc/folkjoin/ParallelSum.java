package com.lane.interview.interview.juc.folkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSum {

    public static int parallelSum(int[] nums, int k) {
        ForkJoinPool pool = new ForkJoinPool(k);
        return pool.invoke(new SumTask(nums, 0, nums.length - 1));
    }

    static class SumTask extends RecursiveTask<Integer> {
        private final int[] nums;
        private final int start;
        private final int end;

        public SumTask(int[] nums, int start, int end) {
            this.nums = nums;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start + 1 <= 1000) {
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += nums[i];
                }
                return sum;
            } else {
                int mid = start + (end - start) / 2;
                SumTask leftTask = new SumTask(nums, start, mid);
                SumTask rightTask = new SumTask(nums, mid + 1, end);
                leftTask.fork();
                int rightSum = rightTask.compute();
                int leftSum = leftTask.join();
                return leftSum + rightSum;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 4;
        int sum = parallelSum(nums, k);
        System.out.println("Total sum: " + sum);
    }
}

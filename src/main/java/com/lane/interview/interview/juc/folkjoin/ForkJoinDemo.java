package com.lane.interview.interview.juc.folkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author duenpu
 * @date 2023/8/14 16:50
 */
public class ForkJoinDemo extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public ForkJoinDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            System.out.println("start = " + start + ", end = " + end);
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinDemo leftTask = new ForkJoinDemo(start, middle);
            ForkJoinDemo rightTask = new ForkJoinDemo(middle + 1, end);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();
            // 等待子任务执行完，并得到其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            // 合并子任务
            sum = leftResult + rightResult;
        }
        System.out.println("sum = " + sum);
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务，负责计算1+2+3+4
        ForkJoinDemo task = new ForkJoinDemo(1, 10);
        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
        if (task.isCompletedAbnormally()) {
            System.out.println(task.getException());
        }
        // 等待任务执行完毕，并返回结果
        try {
            System.out.println("result = " + result.get());
        } catch (Exception e) {
            System.out.println("exception = " + e);
        }
    }
}

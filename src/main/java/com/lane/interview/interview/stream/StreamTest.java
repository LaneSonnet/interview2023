package com.lane.interview.interview.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ Author:  duenpu
 * @ Date  :  15:25 2024/2/16
 */
public class StreamTest {
    public static void main1(String[] args) {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce((a, b) -> {
                    System.out.println(String.format("%s: %d + %d = %d",
                            Thread.currentThread().getName(), a, b, a + b));
                    return a + b;
                })
                .ifPresent(System.out::println);
    }

    public static void main2(String[] args) {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .parallel()
                .reduce((a, b) -> {
                    System.out.println(String.format("%s: %d + %d = %d",
                            Thread.currentThread().getName(), a, b, a + b));
                    return a + b;
                })
                .ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(6);
        arr.parallelStream().reduce((a, b) -> {
                    System.out.println(String.format("%s: %d + %d = %d",
                            Thread.currentThread().getName(), a, b, a + b));
                    return a + b;
                })
                .ifPresent(System.out::println);
        List<Integer> arr1 = new ArrayList<>();
        arr1.add(1);
        arr1.add(2);
        arr1.add(3);
        arr1.add(4);
        arr1.add(5);
        arr1.add(6);
        AtomicInteger v = new AtomicInteger(1);
        arr1.parallelStream().sorted(Comparator.reverseOrder()).forEachOrdered(a -> {
            v.getAndIncrement();
            System.out.println(a);
            System.out.println(v.get());
        });
    }

    /**
     * mapToLong 转换
     * @author: 栈长
     * @from: 公众号Java技术栈
     */
    private static void mapToLong() {
        List<Integer> arr1 = new ArrayList<>();
        arr1.add(1);
        arr1.add(2);
        arr1.add(3);
        arr1.add(4);
        arr1.add(5);
        arr1.add(6);
        System.out.println("=====map to long list=====");
        List<Long> longList = arr1.stream().mapToLong(Long::valueOf).boxed().collect(Collectors.toList());
        longList.forEach(System.out::println);
        System.out.println("map to long list size: " + longList.size());
        System.out.println();
    }
}

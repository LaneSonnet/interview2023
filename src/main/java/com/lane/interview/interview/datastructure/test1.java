package com.lane.interview.interview.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ Author:  duenpu
 * @ Date  :  18:04 2024/2/16
 */
public class test1 {
    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        System.out.println(a == b);//true

        int p = 200;
        int q = 200;
        System.out.println(a == b);//true

        Integer x = 1;
        Integer y = 1;
        System.out.println(x.equals(y));//true
        System.out.println(x == y);//true

        Integer m = 200;
        Integer n = 200;
        System.out.println(m.equals(n));//true
        System.out.println(m == n);//false

        Hashtable<String,String> t = new Hashtable<>();
        t.put("a","abc");
        t.put("a","abcd");
        t.put("b","bbb");
        t.put("c","ccc");
        t.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });

        List<String> list = new ArrayList<>(10);
        for (int i = 0;i <=50;i++) {
            list.add(Integer.valueOf(i).toString());
        }
        list.forEach(e -> {
            System.out.println(e);
        });

        List<String> words = Arrays.asList("Hello", "World");

        // 使用flatMap将每个字符串拆分为单词，并将所有单词合并为一个流
        Stream<String> wordStream = words.stream().flatMap(str -> Arrays.stream(str.split("")));

        // 将流中的元素收集为一个列表
        List<String> letters = wordStream.collect(Collectors.toList());

        // 输出结果
        System.out.println(letters);
    }
}

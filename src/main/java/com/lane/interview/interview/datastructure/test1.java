package com.lane.interview.interview.datastructure;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

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

        List<String> list = new ArrayList<>();
        list.add(0,"000");
        list.add(1,"111");
        list.add(1,"222");
        list.forEach(e -> {
            System.out.println(e);
        });
    }
}

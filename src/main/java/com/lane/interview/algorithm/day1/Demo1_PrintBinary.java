package com.lane.interview.algorithm.day1;

public class Demo1_PrintBinary {

        public static void print(int num) {
            for (int i = 31; i >= 0; i--) {
                System.out.print((num & (1 << i)) == 0 ? "0" : "1");
            }
            System.out.println();
        }

        public static void main(String[] args) {
            //打印1-100的二进制
            for (int i = 1; i <= 100; i++) {
                print(i);
            }
        }
}

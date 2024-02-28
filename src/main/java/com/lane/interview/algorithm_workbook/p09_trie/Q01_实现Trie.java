package com.lane.interview.algorithm_workbook.p09_trie;

import java.util.HashMap;

/**
 * @author duenpu
 * @date 2024/2/28 17:48
 */
public class Q01_实现Trie {
    class Trie {

        class Node {
            public int pass;
            public int end;
            public HashMap<Integer, Node> nexts;

            public Node() {
                pass = 0;
                end = 0;
                nexts = new HashMap<>();
            }
        }

        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chs = word.toCharArray();
            Node node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    node.nexts.put(index, new Node());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }

        public boolean search(String word) {
            if (word == null) {
                return Boolean.FALSE;
            }
            char[] chs = word.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return Boolean.FALSE;
                }
                node = node.nexts.get(index);
            }
            return node.end > 0 ? Boolean.TRUE : Boolean.FALSE;
        }

        public boolean startsWith(String prefix) {
            if (prefix == null) {
                return Boolean.FALSE;
            }
            char[] chs = prefix.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return Boolean.FALSE;
                }
                node = node.nexts.get(index);
            }
            return node.pass > 0 ? Boolean.TRUE : Boolean.FALSE;
        }

        public int countWordsEqualTo(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        public int countWordsStartingWith(String prefix) {
            if (prefix == null) {
                return 0;
            }
            char[] chs = prefix.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }

        public void erase(String word) {
            if (countWordsEqualTo(word) != 0) {
                char[] chs = word.toCharArray();
                Node node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = (int) chs[i];
                    if (--node.nexts.get(index).pass == 0) {
                        node.nexts.remove(index);
                        return;
                    }
                    node = node.nexts.get(index);
                }
                node.end--;
            }
        }
    }
}

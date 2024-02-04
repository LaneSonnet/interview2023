package com.lane.interview.algorithm_workbook.p09_trie;

/**
 * 前缀树(prefix tree 或 Trie) https://zhuanlan.zhihu.com/p/340228499
 * 多叉树
 * 一个数组，元素都是字符串
 * ["abc","abd","bce","abcd","bcf"]
 * 每个字符串元素，就是一条路，从根节点开始，往下走
 * 字符串中的每个字符，就是节点与节点之间的连线
 * 1. 从根节点到某一节点，路径上经过的字符连接起来，为该节点对应的字符串
 * 2. 每个节点的所有子节点路径代表的字符都不相同
 * 3. 每个节点记录下来的信息有：
 * 		1. 有多少个字符串到达过这个节点(pass)
 * 		2. 有多少个字符串以这个节点结尾(end)
 *
 * 用途：
 * 1. 统计和排序大量的字符串
 * 2. 搜索引擎中的搜索关键词提示功能
 * 3. 字符串频次统计
 * 4. 最长公共前缀
 *
 * 相比于哈希表，前缀树的优势是什么？
 * 哈希表：
 * 1.如果放的是非基础类型，存的是内存地址，时间复杂度是O(1)
 * 2.如果放的是基础类型(非字符串)，时间复杂度是O(1)
 * 3.如果放的是字符串，时间复杂度是O(M)，M是总的字符数(哈希值需要遍历整个字符串才能计算出来)
 * 4.查询的话，时间复杂度是O(1)，但是如果要查询某个字符串的前缀，就不行了
 *
 * 前缀树
 * 1.不管放的是什么，时间复杂度都是O(M)，M是总的字符数
 * 2.查询的话，时间复杂度是O(k)，k是这个字符串的长度
 * 3.支持前缀查询，频次统计
 *
 */
public class Demo1_Trie {

	// 测试链接 : https://leetcode.cn/problems/implement-trie-ii-prefix-tree/
	// 提交Trie类可以直接通过
	// 原来代码是对的，但是既然找到了直接测试的链接，那就直接测吧
	// 这个链接上要求实现的功能和课上讲的完全一样
	// 该前缀树的路用数组实现
	class Trie {

		// 前提：只有26个小写字母
		// 每个节点先建出来26条路(nexts)，如果下面没元素，指向空即可
		class Node {
			public int pass;
			public int end;
			public Node[] nexts;

			// char tmp = "t"
			// tmp - 'a' = 19
			// nexts[19]有值 -> 有路
			public Node() {
				pass = 0;
				end = 0;
				// 0 -> 'a'
				// 1 -> 'b'
				// 2 -> 'c'
				// ...
				// 25 -> 'z'
				// nexts[i] == null     i方向的路不存在
				// nexts[i] != null     i方向的路存在
				nexts = new Node[26];
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
			char[] str = word.toCharArray();
			Node node = root;
			node.pass++;
			int path = 0;
			for (int i = 0; i < str.length; i++) { // 从左往右遍历字符
				path = str[i] - 'a'; // 由字符，对应成走向哪条路
				// 如果路不存在，建路
				if (node.nexts[path] == null) {
					node.nexts[path] = new Node();
				}
				// 路存在，往下走
				node = node.nexts[path];
				node.pass++;
			}
			node.end++;
		}

		public void erase(String word) {
			if (countWordsEqualTo(word) != 0) {
				char[] chs = word.toCharArray();
				Node node = root;
				node.pass--;
				int path = 0;
				// 从左往右遍历字符
				for (int i = 0; i < chs.length; i++) {
					path = chs[i] - 'a';
					// pass先--,如果路的pass为0，后面的路都不用看了，直接删掉
					if (--node.nexts[path].pass == 0) {
						node.nexts[path] = null;
						return;
					}
					// pass--之后不为0，那就往下走
					node = node.nexts[path];
				}
				node.end--;
			}
		}

		// word这个单词之前加入过几次
		public int countWordsEqualTo(String word) {
			if (word == null) {
				return 0;
			}
			char[] chs = word.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				// 路不存在，说明没有加入过
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			// 走完了，node来到了最后一个字符所在的节点,返回end（代表着出现过的次数）
			return node.end;
		}

		// 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
		public int countWordsStartingWith(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.pass;
		}
	}

}

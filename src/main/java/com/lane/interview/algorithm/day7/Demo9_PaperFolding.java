package com.lane.interview.algorithm.day7;

/**
 * 折纸问题
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折一次，压出折痕后展开，此时有一条折痕，突起的方向指向纸条的背面，这条折痕叫做“下”折痕，突起的方向指向纸条的正面，这条折痕叫做“上”折痕。
 * 如果每次都从下边向上方对折一次，对折N次，请从上到下打印所有折痕的方向。
 */
public class Demo9_PaperFolding {

	public static void printAllFolds(int N) {
		process(1, N, true);
		System.out.println();
	}

	// 当前你来了一个节点，脑海中想象的！
	// 这个节点在第i层，一共有N层，N固定不变的
	// 这个节点如果是凹的话，down = True
	// 这个节点如果是凸的话，down = False
	// 函数的功能：中序打印以你想象的节点为头的整棵树！
	public static void process(int i, int N, boolean down) {
		if (i > N) {
			return;
		}
		//左凹
		process(i + 1, N, true);
		//打印自己
		System.out.print(down ? "凹 " : "凸 ");
		//右凸
		process(i + 1, N, false);
	}

	public static void main(String[] args) {
		int N = 4;
		printAllFolds(N);
	}
}

package com.lane.interview.algorithm.day12.lesson5;


/*
	给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
	沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
	返回最小距离累加和

	动态规划的数组压缩技巧

	本质：不用二维dp表，就用一个数组来实现dp

	怎么做：一层一层扫

	适用题目：
	①格子依赖它的“左”和“上”，从第一行往下一行一行推
	②格子依赖它的“左上”和“上”，第一行算出来之后，从第二行开始，从右往左推
	③格子依赖它的“左上”和“上”和“左”
	首先第一行能算出来，假设是[a,b,c]
	我们要算第二行[a',b',c']
	首先a'通过a能算出来，注意！此时要用一个临时变量tmp记录下来a的值
	b‘怎么算，需要通过a',a,b算出来，a'已经有了,b还没更新,a的值被tmp记下来了，所以b'也能算出来，注意！这时候tmp就要改成b的值了，要用来计算c’
	周而复始……

	实践问题：
	以上的情况都是基于行数多，列数少的情况
	但是行数少(比如就4行)，但是列数多(比如100万列)，这个时候我们就要准备一个竖向的数组，从第一列开始，从左往右一列一列更新
*/
public class Demo14_MinPathSum {


	/*
	* 构建dp表
	* dp[i][j]的意思：从(0，0)到(i，j)这个位置，最小的路径和，就是dp[i][j]这个格子里的值
	* 所以最终答案，就是dp表右下角的格子的值
	*
	* */
	public static int minPathSum1(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[][] dp = new int[row][col];
		dp[0][0] = m[0][0];
		// 因为只能往右或者往下走，所以第一行的值都是可以算出来的
		for (int i = 1; i < row; i++) {
			dp[i][0] = dp[i - 1][0] + m[i][0];
		}
		// 因为只能往右或者往下走，所以第一列的值都是可以算出来的
		for (int j = 1; j < col; j++) {
			dp[0][j] = dp[0][j - 1] + m[0][j];
		}
		// 遍历其他位置
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				// 当前(i，j)这个格子  比较它的“上格子”和“左格子”，取小的，再加上(i，j)当前格子的值，就是dp[i][j]的值
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
			}
		}
		return dp[row - 1][col - 1];
	}

	/*
	* 优化思路：
	* 为什么优化？原数组是m*n,dp表也是m*n，太浪费空间
	*
	* 第一步优化：用两个数组来实现dp表，arr1和arr2
	* 因为dp表每个元素都只依赖于它的“左”和“上”，所以按理来说，我们可以从第一行开始，一行一行往下推
	* arr1先把第一行算出来，arr2根据arr1算出来第二行，arr1再根据arr2算出来第三行，周而复始……
	*
	* 第二步优化：用一个数组来实现，arr
	* arr要实现自我更新
	* ①arr先把第一行算出来，比如是[a,b,c,d,e]
	* ②我们现在要计算第二行的值，假设第二行的dp值应该是a',b'c',d',e'
	* 那么a'能由a得出来，显而易见
	* b’能够由a‘和b得出来
	* c’能够由b'和c得出来
	* 周而复始……
	*
	* */
	public static int minPathSum2(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[] dp = new int[col];
		dp[0] = m[0][0];
		// 先算出第一行
		for (int j = 1; j < col; j++) {
			dp[j] = dp[j - 1] + m[0][j];
		}
		// 然后遍历每一行
		for (int i = 1; i < row; i++) {
			// 把这一行的第一个格子算出来
			dp[0] += m[i][0];
			// 再遍历这一行
			for (int j = 1; j < col; j++) {
				// dp[j - 1]，这个格子已经更新过了，所以代表的是当前行 也就是(i,j)的左边格子的值
				// dp[j]，这个格子还没更新，所以代表的是上一行 也就是(i,j)的上边格子的值
				// 哪个小取哪个，然后加上本身的值
				dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
			}
		}
		return dp[col - 1];
	}














	// for test
	public static int[][] generateRandomMatrix(int rowSize, int colSize) {
		if (rowSize < 0 || colSize < 0) {
			return null;
		}
		int[][] result = new int[rowSize][colSize];
		for (int i = 0; i != result.length; i++) {
			for (int j = 0; j != result[0].length; j++) {
				result[i][j] = (int) (Math.random() * 100);
			}
		}
		return result;
	}

	// for test
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int rowSize = 10;
		int colSize = 10;
		int[][] m = generateRandomMatrix(rowSize, colSize);
		System.out.println(minPathSum1(m));
		System.out.println(minPathSum2(m));

	}
}

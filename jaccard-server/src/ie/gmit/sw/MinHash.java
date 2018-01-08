package ie.gmit.sw;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * @author weichenwang
 *
 */
public class MinHash {

	/**
	 * Get set 得到set
	 * 
	 * @param k
	 * @param str
	 * @return set
	 * @throws IOException
	 */
	protected static Set<String> getSet(int k, String str) throws IOException {
		String replacedStr = str;
		Set<String> set = KShingle.split(replacedStr, k);
		return set;
	}

	/**
	 * Construct the feature set matrix 构建特征集合矩阵
	 * 
	 * @param set
	 * @param set1
	 * @param set2
	 * @return matrix
	 */
	protected static String[][] characteristicMatrix(Set<String> set, Set<String> set1, Set<String> set2) {
		String[] a = new String[set.size()];
		set.toArray(a);
		String[] set1Array = new String[set1.size()];
		set1.toArray(set1Array);
		String[] set2Array = new String[set2.size()];
		set2.toArray(set2Array);

		String[][] matrix = new String[a.length][5];// Constructed here as 5 is
													// stored for the result of
													// two hash functions in
													// later minimum hash
													// signatures.
		int i, j, temp; // 此处构造为5是为了后面的最小哈希签名中的两个哈希函数的结果存放。
		for (i = 0; i < matrix.length; i++) {
			for (j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = "0";
			}
		}
		i = 0;
		for (Iterator<String> iter = set.iterator(); iter.hasNext();) {
			matrix[i++][0] = iter.next();
		}
		i = 0;
		temp = 0;
		for (j = i; j < a.length && temp < set1Array.length; j++) {
			if (matrix[j][0].equals(set1Array[temp])) {
				matrix[j][1] = "1";
				temp++;
			}
		}
		temp = 0;
		for (j = i; j < a.length && temp < set2Array.length; j++) {
			if (matrix[j][0].equals(set2Array[temp])) {
				matrix[j][2] = "1";
				temp++;
			}
		}
		return matrix;
	}

	//
	/**
	 * row disrupt 行打乱
	 * 
	 * @param matrix
	 * @return matrix
	 */
	protected static String[][] rowMess(String[][] matrix) {
		int rowNumber1, rowNumber2;
		int i, j;
		String temp;
		Random r = new Random();
		// Random disruptive ten times 随机进行行打乱十次
		for (i = 0; i < 9; i++) {
			rowNumber1 = r.nextInt(matrix.length);
			rowNumber2 = r.nextInt(matrix.length);
			for (j = 0; j < matrix[0].length; j++) {
				temp = matrix[rowNumber2][j];
				matrix[rowNumber2][j] = matrix[rowNumber1][j];
				matrix[rowNumber1][j] = temp;
			}
		}
		return matrix;
	}

	//
	/**
	 * Find the similarity based on the minimum hash value 根据最小hash值求相似度
	 * 
	 * @param k
	 * @param set
	 * @param str1
	 * @param str2
	 * @return df.format(result)
	 * @throws IOException
	 */
	public static String minHashJaccard(int k, Set<String> set, String str1, String str2) throws IOException {
		Set<String> set1 = getSet(k, str1);
		Set<String> set2 = getSet(k, str2);
		String[][] matrix = characteristicMatrix(set, set1, set2);
		matrix = rowMess(matrix);
		double result;
		// 已知定义：两个集合经随机排列转换之后得到的两个最小哈希值相等的概率等于这两个集合的jaccard相似度
		System.out.println(
				"Known definition：The probability that the two minimum hash values obtained after the random transposition of two sets are equal is equal to the jaccard similarity between the two sets.");
		int equalHashValue = 0;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][1].equals(matrix[i][2]) && matrix[i][1].equals("1")) {
				equalHashValue++;
			}
		}
		System.out.println("The total number of total items: " + set.size()); // 全集共有项的数目
		System.out.println("The number of 1's(this substring appears in both texts.)：" + equalHashValue); // 都为1(该子串在两段文本中均出现)的数目
		result = equalHashValue * 1.0 / set.size();
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println(
				"The probability that the first and second items get the same minimum hash is calculated as P = "
						+ equalHashValue + " / " + set.size() + " = " + df.format(result)); // 第一项与第二项得到最小哈希值相等的概率计算为P
		System.out.println(
				"The result of jaccard similarity between two texts calculated by MinHash is: " + df.format(result)); // 即MinHash算得的两段文本之间的jaccard相似度结果为
		return df.format(result);
	}
}

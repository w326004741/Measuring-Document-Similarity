package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author weichenwang
 *
 */
public class KShingle {
	/**
	 * Read the test text 读测试文本
	 * 
	 * @return sb.toString()
	 * @throws IOException
	 */
	protected static String ReadFile1() throws IOException {
		File file = new File("/Users/weichenwang/Documents/jaccard-server/text.txt");
		System.out.println(123);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		InputStream in = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Remove special symbols 删除特殊符号
	 * 
	 * @param str
	 * @return replaceStr
	 */
	protected static String deleteWord(String str) {
		String replaceStr = str.replaceAll("\\)", "").replaceAll("\\(", "").replaceAll(",", "").replaceAll("\\D\\.", "")
				.replaceAll("\\s", "");
		return replaceStr;
	}

	//
	/**
	 * Use k-shingle algorithm to separate （使用k-shingle算法分隔）
	 * 
	 * @param str
	 * @param k
	 * @return shingSet
	 */
	protected static Set<String> split(String str, int k) {
		Set<String> shingSet = new TreeSet<String>();// 使用TreeSet而不使用HashSet有利于在MinHash算法中降低算法复杂度
		for (int i = 0; i <= str.length() - k; i++) {
			shingSet.add(str.substring(i, i + k));
		}
		return shingSet;
	}

	//
	/**
	 * Get the similarity between two paragraphs of text （获得两段文本之间的相似度）
	 * 
	 * @param k
	 * @param str1
	 * @param str2
	 * @return allElementSet
	 * @throws IOException
	 */
	public static Set<String> jaccard(int k, String str1, String str2) throws IOException {
		String replacedStr1 = str1;
		String replacedStr2 = str2;
		Set<String> set1 = split(replacedStr1, k);
		Set<String> set2 = split(replacedStr2, k);
		Set<String> allElementSet = new TreeSet<String>();
		allElementSet.addAll(set1);
		allElementSet.addAll(set2);
		double jaccardValue = (set1.size() + set2.size() - allElementSet.size()) * 1.0 / allElementSet.size();
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("======================================Start====================================");
		System.out.println("Use " + k + "-shingle the similarity between the two paragraphs results as follows: "
				+ df.format(jaccardValue));
		return allElementSet;
	}
}

/****************************************************************************************
 *Project: Regularized Latent Semantic Indexing 1.0
Reference: Quan Wang, Jun Xu, Hang Li, and Nick Craswell. Regularized latent semantic indexing. SIGIR 2011: 685-694. 
 ****************************************************************************************
 *Laboratory of Intelligent Information Processing, Nankai University
 *Authors: Zhicheng He, Yingjie Xu, Jun Xu, MaoQiang xie, Yalou Huang
 *****************************************************************************************/
package nkiip.rlsi.util;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to strengthen the Java String.split() method
 */
public class StringSplitter {
	/**
	 * 
	 * @param separator
	 * @param original
	 * @return a string[] generated from string original 
	 * by split it with all characters in string separator
	 */
	public static String[] split(String separator, String original){
		char[] separator_char = separator.toCharArray();
		for(int i=1; i<separator_char.length; i++){
			original = original.replace(separator_char[i], separator_char[0]);
		}
		return original.split(separator.substring(0, 1));
	}
	/**
	 * 
	 * @param original
	 * @return original with all empty string deleted
	 */
	public static String[] RemoveEmptyEntries(String[] original){
		int len = original.length;
		List<String> list = new ArrayList<String>(len);
		for(int i=0; i<len; i++){
			if(original[i] != null && !original[i].equals("")){
				list.add(original[i]);
			}
		}
		String[] result = new String[list.size()];
		for(int i=0; i<list.size(); i++){
			result[i] = list.get(i);
		}
		return result;
	}
}

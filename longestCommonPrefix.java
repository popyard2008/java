import java.util.*;

public class longestCommonPrefix
{
        // Driver method
	public static void main(String args[])
	{
		// getLongestCommonPrefix longest = new getLongestCommonPrefix();

        // Considering inputs given are valid
		String[] str = {"LXXY", "LXXYVI", "LXXYONG", "LXXYY"};

		System.out.println("the longer common prefix is: " + getLongestCommonPrefix(str));
		System.out.println("the longer common prefix is: " + longestCommonPrefix_min(str));
		System.out.println("the longer common prefix is: " + longestCommonPrefixVertical(str));
		System.out.println("the longer common prefix is: " + longestCommonPrefix(str));
		System.out.println("the longer common prefix is: " + longestCommonPrefix_4(str));
		// System.out.println("the longer common prefix is: " + longest(str).prefix);

	}
/*
	public static String getLongestCommonPrefix(String[] strs) {
		if (strs.length == 0) return "";
		String prefix = strs[0];   //assign the first value to prefix
		for (int i = 1; i < strs.length; i++)  //next value
			while (strs[i].indexOf(prefix) != 0) {   				// indexOf
				prefix = prefix.substring(0, prefix.length() - 1);  // substring (start_pos, length); 
				System.out.println("for i =" + i + " prefix is: " + prefix);
				if (prefix.isEmpty()) return "";					// isEmpty  or use .length() = 0 
			}        
			return prefix;
		}
	}
*/
	public static String getLongestCommonPrefix(String[] strs) {  //THIS IS MY OWN WAY
		 String Prefix = "";
		 // String singlePrefix = "";
		 if (strs.length == 0) return "";

		 for (int i = 0; i < strs[0].length() ; i++ ) {

		 	//singlePrefix = strs[0].substring(i, i+1 ); //GET ONE CHAR ONLY ONE BY ONE
             char singlePrefix = strs[0].charAt(i);

		 	System.out.println("i = " + i + ", strs[0].length() is " 
		 				+ strs[0].length() + " singlePrefix is " + singlePrefix
		 				+ " Prefix is " + Prefix + " indexOf is " + 
		 				strs[0].indexOf(singlePrefix, i));
		 	
		 	for (int j = 1; j < strs.length ; j++) {
		 		System.out.println("j = " + j + " strs[j] = " + strs[j]);
		 		if (strs[j].length() >= i) {   //TO AVOID OVERFLOW 
		 			System.out.println("strs[j].indexOf(singlePrefix, i) is " + strs[j].indexOf(singlePrefix, i)
		 		+ ", singlePrefix is " + singlePrefix + ", j = " + j 
		 		);

		 		   if (strs[j].indexOf(singlePrefix, i) != i) //after position i, the INDEX OF STRING
		 			{System.out.println("returned j= " + j + " i = " + i + " Prefix is " + Prefix
		 				+ "strs[j].length() is " + strs[j].length() + " strs.length is " + strs.length);
		 			return Prefix;}
		 		} else {return Prefix;} //IF POSSILBE OVERFLOW, RETURN THE FINAL RESULTS
		 		}
		 		Prefix += singlePrefix;	 //CONCAT THE RESULTS ONLY BY THE END

		 	}

		 	
		 
		 return Prefix;


	}


	public static String longestCommonPrefix_min (String[] strs) {
		if (strs.length == 0) return "";
		String	prefix = strs[0];
		for (int i = 1; i < strs.length ; i++ ) { 
		/*Array uses length, String uses length() 
		and collections such as Arraylist uses size(). 
			Arrays are special in Java. ... They are one of the earliest primitives of Java. 
			length is a final public field in Array as length of an array is immutable. 
			length field cannot be accessed as a normal field.*/
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.length() == 0) return "";
			}
			}
			return prefix;
		}

	

	/*Approach #2 (Vertical scanning)

Algorithm

Imagine a very short string is at the end of the array. The above approach will still do 
S
S comparisons. One way to optimize this case is to do vertical scanning. We compare characters 
from top to bottom on the same column (same character index of the strings) 
before moving on to the next column.
*/
public static String longestCommonPrefixVertical(String[] strs) {
    if (strs == null || strs.length == 0) return "";
    for (int i = 0; i < strs[0].length() ; i++){
        char c = strs[0].charAt(i); //char function: char, .charAt
        for (int j = 1; j < strs.length; j ++) {
            if (i == strs[j].length() || strs[j].charAt(i) != c)
                return strs[0].substring(0, i);             
        }
    }
    return strs[0];
}

/**/

public static String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) return "";    
        return longestCommonPrefix(strs, 0 , strs.length - 1);
}

private static String longestCommonPrefix(String[] strs, int l, int r) {
    if (l == r) {
        return strs[l];
    }
    else {
        int mid = (l + r)/2;
        String lcpLeft =   longestCommonPrefix(strs, l , mid);
        String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
        return commonPrefix(lcpLeft, lcpRight);
   }
}

private static String commonPrefix(String left,String right) {
    int min = Math.min(left.length(), right.length());       
    for (int i = 0; i < min; i++) {
        if ( left.charAt(i) != right.charAt(i) )
            return left.substring(0, i);
    }
    return left.substring(0, min);
}

/*
The idea is to apply binary search method to find the string 
with maximum value L, which is common prefix of all of the strings. 
The algorithm searches space is the interval(0…minLen), 
where minLen is minimum string length and the maximum 
possible common prefix. Each time search space is divided in two 
equal parts, one of them is discarded, because it is sure that 
it doesn't contain the solution. There are two possible cases: 
S[1...mid] is not a common string. This means that for each j > i S[1..j] is 
not a common string and we discard the second half of the search space. 
S[1...mid] is common string. This means that for for each i < j S[1..i] is 
a common string and we discard the first half of the search space, 
because we try to find longer common prefix.
*/

public static String longestCommonPrefix_4(String[] strs) {
    if (strs == null || strs.length == 0)
        return "";
    int minLen = Integer.MAX_VALUE;
    for (String str : strs)
        minLen = Math.min(minLen, str.length());
    int low = 1;
    int high = minLen;
    while (low <= high) {
        int middle = (low + high) / 2;
        if (isCommonPrefix(strs, middle))
            low = middle + 1;
        else
            high = middle - 1;
    }
    return strs[0].substring(0, (low + high) / 2);
}

private static boolean isCommonPrefix(String[] strs, int len){
    String str1 = strs[0].substring(0,len);
    for (int i = 1; i < strs.length; i++)
        if (!strs[i].startsWith(str1))
            return false;
    return true;
}

}
/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.


*/
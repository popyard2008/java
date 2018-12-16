		import java.util.*;
		import java.text.NumberFormat;
		import java.text.DecimalFormat;
		import java.lang.*;

		public class implementStrStr_28_e
		{
		    // Driver method
		// public static void sleep(long millis) throws InterruptedException;

			public static void main(String args[])
			{

				final long start = System.currentTimeMillis();  //START TIME


				String haystack = "asdfsdfdlfsdf";//,3,3,4,5,6,10,10};
				String needle = "";

				System.out.println("haystack = " + haystack + " ; needle = " + needle + "; index number is : " +  strStr(haystack , needle));
				System.out.println("haystack = " + haystack + " ; needle = " + needle + "; index number is : " +  strStr2(haystack , needle));

				// SLEEP 

		// CALCULATE END TIME

				final long end = System.currentTimeMillis();

				NumberFormat formatter =  new DecimalFormat("#0.0000000000");

				System.out.println("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");

			}
				// if (A.isEmpty()) return 0;

			public static int strStr(String haystack, String needle){
				for (int i = 0; ; i++) {
					for (int j= 0; ; j++ ) {
						if (j == needle.length()) return i; 
						if (i + j == haystack.length()) return -1;
						if (needle.charAt(j) != haystack.charAt(i + j)) break;
					}
				}


			}

			public static int strStr_original(String haystack, String needle) {
				for (int i = 0; ; i++) {  //
					for (int j = 0; ; j++) {
						if (j == needle.length()) { // compared all the chars begining from position at i to i + j;
							System.out.println("return i = " + i + "; and j =" + j);
							return i;}
						if (i + j == haystack.length()) {    //no need to check more, if reach to the length of the string offset + needle length
							System.out.println("return -1" );
							return -1;}
							System.out.println("needle.charAt(" + j + ") = " + needle.charAt(j) + " ; i = " + i + " ; j = " + j);
						if (needle.charAt(j) != haystack.charAt(i + j)) {  //OFFSET FROM i, the #j of the chars are not the same
							System.out.println("return break");
							break;}
						}
					}
				}

		public static int strStr2(String s, String t) {
      		  if (t.isEmpty()) return 0; // edge case: "",""=>0  "a",""=>0
     			   for (int i = 0; i <= s.length() - t.length(); i++) {
     				   	for (int j = 0; j < t.length() && s.charAt(i + j) == t.charAt(j); j++)
      			  		if (j == t.length() - 1) return i;
        		}
        return -1;
    }
}
		/*

mplement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
		*/

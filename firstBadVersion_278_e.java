		import java.util.*;
		import java.text.NumberFormat;
		import java.text.DecimalFormat;
		import java.lang.*;

		public class firstBadVersion_278_e
		{
		    // Driver method
		// public static void sleep(long millis) throws InterruptedException;

			public static void main(String args[])
			{

				final long start = System.currentTimeMillis();  //START TIME


				int[]  nums = {1, 1, 1, 1, 2, 2, 2};
				int target = 7/2;
				System.out.println("array is  " + Arrays.toString(nums) + " , target is " + target +" ; the result is " +  firstBadVersion(nums , target));
				// System.out.println("haystack = " + haystack + " ; needle = " + needle + "; index number is : " +  strStr2(haystack , needle));

				// SLEEP 

		// CALCULATE END TIME

				final long end = System.currentTimeMillis();

				NumberFormat formatter =  new DecimalFormat("#0.0000000000");

				System.out.println("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");

			}
				// if (A.isEmpty()) return 0;

			public static int firstBadVersion(int[] nums, int target){
			// System.out.println("result is " + nums.length + " " + nums[1]);
							int start = 0,  end = nums.length - 1;
								while (start < end) {
									int mid = start + (end - start)/2;
									System.out.println("mid = " + mid );
									if (nums[mid] == 1  ) start = mid + 1 ;
										else end = mid;
									System.out.println("start =  " + start + " ; end = " + end );

								}
								return -1;
								// System.out.println(Arrays.toString(nums)); // print array 
					}

	
			}
		/*

Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2
Example 2:

Input: [1,3,5,6], 2
Output: 1
Example 3:

Input: [1,3,5,6], 7
Output: 4
Example 4:

Input: [1,3,5,6], 0
Output: 0

		*/

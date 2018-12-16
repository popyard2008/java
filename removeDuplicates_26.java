import java.util.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.lang.*;

public class removeDuplicates_26
{
        // Driver method
	// public static void sleep(long millis) throws InterruptedException;

	public static void main(String args[])
	{




			final long start = System.currentTimeMillis();  //START TIME


			int[] nums = {0,0,1,2,2,2,3,3};//,3,3,4,5,6,10,10};

			System.out.println("the unique number is : " +  removeDuplicates(nums ,nums.length));

			// SLEEP 

// CALCULATE END TIME

		final long end = System.currentTimeMillis();

		NumberFormat formatter =  new DecimalFormat("#0.0000000");

		System.out.println("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");


        // Considering inputs given are valid


		// System.out.println("the longer common prefix is: " + longest(str).prefix);

	}

		public static int removeDuplicates(int A[], int n) {
			if(n < 2) return n;
			int id = 1;
			for(int i = 1; i < n; ++i) {
				System.out.println("before, i is: " + i + " ; A[" + i+ "] = " + A[i] + "; A[" + (i-1) + "] = " + A[i-1] + "; id = " + id + " ; A[id++] = " + A[id]);
				if(A[i] != A[i-1]) { A[id] = A[i]; //for return the values
				//   A[id++] = A[i];
					 id ++;  // for count how many different uniq
				System.out.println("after, i is " + i + " ; A[" + i+ "] = " + A[i] + "; A[" + (i-1) + "] = " + A[i-1] + "; id = " + id + " ; A[id++] = " + A[id]);
			}
			}
			System.out.println(Arrays.toString(A)); // print array 
			return id;
		} 

}
/*
Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
*/

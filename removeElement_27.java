		import java.util.*;
		import java.text.NumberFormat;
		import java.text.DecimalFormat;
		import java.lang.*;

		public class removeElement_27
		{
		    // Driver method
		// public static void sleep(long millis) throws InterruptedException;

			public static void main(String args[])
			{

				final long start = System.currentTimeMillis();  //START TIME


				int[] nums = {0,0,1,2,2,2,3,3};//,3,3,4,5,6,10,10};
				System.out.println(Arrays.toString(nums)); // print array 

				System.out.println("the unique number is : " +  removeElement(nums ,2 ));

				// SLEEP 

		// CALCULATE END TIME

				final long end = System.currentTimeMillis();

				NumberFormat formatter =  new DecimalFormat("#0.0000000");

				System.out.println("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");


		    // Considering inputs given are valid


			// System.out.println("the longer common prefix is: " + longest(str).prefix);

			}

			public static int removeElement(int nums[], int val) {
				// if (A.isEmpty()) return 0;
				int pointerList = 1;
				int pointerTraverse = 1;

				int i = 0;
				for (int j = 0; j < nums.length; j++) {  
					if (nums[j] != val) {			//learing: considering != condition 
						System.out.println("before i = " + i);
						nums[i++] = nums[j];
						System.out.println("after i = " + i);

						// i++;
					}
				}
				System.out.println(Arrays.toString(nums)); // print array 
				return i;
			// }
				// for (int i = 0; i < A.length; i++ ) {
				// 	if ( A[i] == n ) {
				// 		// if = n then find next elememt that is != n and assign it to A[i]
				// 		while (true)
				// 		if (A) {

				// 		}
				// 	}
				// 	else {
				// 		// got 
				// 	}

				// 	while (A[pointer] == n ){
				// 		pointer --;

				// 		A[i++] = A[i];
		// }
		// System.out.println("pointer is " + pointerList + " and pointer 2 is " + pointerTraverse);
			}
				// return pointer;
			public int removeElement2(int[] nums, int val) {
				/*
						methoad two:
		Now consider cases where the array contains few elements to remove. For example, 
nums=[1,2,3,5,4],val=4. The previous algorithm will do unnecessary copy operation of the first four elements. Another example is 
nums=[4,1,2,3,5],val=4. It seems unnecessary to move elements 
[1,2,3,5] one step left as the problem description mentions that the order of elements could be changed.
		When we encounter nums[i]=val, we can swap the current element out with the last element and dispose the last one. This essentially reduces the array's size by 1.

Note that the last element that was swapped in could be the value you want to remove itself. But don't worry, in the next iteration we will still check this element.
;
				*/
				int i = 0;
				int n = nums.length;
				while (i < n) {
					if (nums[i] == val) {
						nums[i] = nums[n - 1];
            // reduce array size by one
						n--;
					} else {
						i++;
					}
				}
				return n;
			}

		}

		/*

		Given an array nums and a value val, remove all instances of that value in-place and return the new length.

		Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

		The order of elements can be changed. It doesn't matter what you leave beyond the new length.

		Example 1:

		Given nums = [3,2,2,3], val = 3,

		Your function should return length = 2, with the first two elements of nums being 2.

		It doesn't matter what you leave beyond the returned length.
		Example 2:

		Given nums = [0,1,2,2,3,0,4,2], val = 2,

		Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.

		Note that the order of those five elements can be arbitrary.

		It doesn't matter what values are set beyond the returned length.
		Clarification:

		Confused why the returned value is an integer but your answer is an array?

		Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

		Internally you can think of this:

		// nums is passed in by reference. (i.e., without making a copy)
		int len = removeElement(nums, val);

		// any modification to nums in your function would be known by the caller.
		// using the length returned by your function, it prints the first len elements.
		for (int i = 0; i < len; i++) {
		print(nums[i]);
		}
		}
		*/

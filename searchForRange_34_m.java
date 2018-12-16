		import java.util.*;
		import java.text.NumberFormat;
		import java.text.DecimalFormat;
		import java.lang.*;

		public class searchForRange_34_m
		{
		    // Driver method
		// public static void sleep(long millis) throws InterruptedException;

			public static void main(String args[])
			{

				final long start = System.currentTimeMillis();  //START TIME


				int[]  nums = {1,2,3, 5,5};
				int target = 5;
				int[] result = searchRange(nums , target);
				// System.out.println("haystack = " + haystack + " ; needle = " + needle + "; index number is : " +  strStr2(haystack , needle));
				System.out.println("array is  " + Arrays.toString(nums) + " , target is " + target +" ; correct result is " + Arrays.toString(result))	;
				int[] myResult = searchForRangeMyOwnTest(nums , target);

				System.out.println("array is  " + Arrays.toString(nums) + " , target is " + target +" ; my result is " + Arrays.toString(myResult) );
				int[] officialResult = searchRangeOfficial(nums , target);
				System.out.println("array is  " + Arrays.toString(nums) + " , target is " + target +" ; the official result is " + Arrays.toString(officialResult) );

				// SLEEP 

		// CALCULATE END TIME

				final long end = System.currentTimeMillis();

				NumberFormat formatter =  new DecimalFormat("#0.0000000000");

				System.out.println("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");

			}



			public static int[] searchRange(int[] nums, int target) {
				int[] targetRange = {-1, -1};

        // find the index of the leftmost appearance of `target`.
				for (int i = 0; i < nums.length; i++) {
					if (nums[i] == target) {
						targetRange[0] = i;
						break;
					}
				}

        // if the last loop did not find any index, then there is no valid range
        // and we return [-1, -1].
				if (targetRange[0] == -1) {
					return targetRange;
				}

        // find the index of the rightmost appearance of `target` (by reverse
        // iteration). it is guaranteed to appear.
				for (int j = nums.length-1; j >= 0; j--) {
					if (nums[j] == target) {
						targetRange[1] = j;
						break;
					}
				}

				return targetRange;
			}



// public static vector<int> searchRangeFromDiscussion(int A[], int n, int target) {
// 				int i = 0, j = n - 1;
// 				vector<int> ret(2, -1);
//     // Search for the left one
// 				while (i < j)
// 				{
// 					int mid = (i + j) /2;
// 					if (A[mid] < target) i = mid + 1;
// 					else j = mid;
// 				}
// 				if (A[i]!=target) return ret;
// 				else ret[0] = i;

//     // Search for the right one
//     j = n-1;  // We don't have to set i to 0 the second time.
//     while (i < j)
//     {
//         int mid = (i + j) /2 + 1;	// Make mid biased to the right
//         if (A[mid] > target) j = mid - 1;  
//         else i = mid;				// So that this won't make the search range stuck.
//     }
//     ret[1] = j;
//     return ret; 
// }

    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
			private static int extremeInsertionIndex(int[] nums, int target, boolean left) {
				int lo = 0;
				int hi = nums.length;

				while (lo < hi) {
					int mid = lo + (hi - lo)/2;
					if (nums[mid] > target || (left && target == nums[mid])) {
						hi = mid;
					}
					else {
						lo = mid+1;
					}
				}

				return lo;
			}

			public static int[] searchRangeOfficial(int[] nums, int target) {
				int[] targetRange = {-1, -1};

				int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
				if (leftIdx == nums.length || nums[leftIdx] != target) {
					return targetRange;
				}

				targetRange[0] = leftIdx;
				targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

				return targetRange;
			}


			public static int[] searchForRangeMyOwnTest(int[] nums, int target){
			// System.out.println("result is " + nums.length + " " + nums[1]);
				int startLow = 0,  endLow = nums.length - 1;
				int startHigh = 0,  endHigh = nums.length - 1;

				int[] targetRange = {-1, -1};
				int[] newNums = new int[num.length + 2] ;  //defensive coding : add extra elements.

				if (nums.length == 0) return targetRange;
				if (nums.length == 1 ) {
					if (nums[0] == target) {
						targetRange[0] = 0;
						targetRange[1] = 0;
						return targetRange;
					}
					else return targetRange;
				}

				// find the lower range by bianary search:
				while (startLow < endLow ) {
					int midLow = startLow + (endLow - startLow)/2;  //middle
					System.out.println("mid = " + midLow );
					if (nums[midLow] >= target  ) endLow = midLow ; // no need to consider right array 
									else startLow = midLow + 1;//mid + 1 is the critical part here, otherswise, the loop never ends. 
									System.out.println("start =  " + startLow + " ; end = " + endLow );
								}
			    	if (nums[startLow] == target)  targetRange[0] = startLow ; //the low and high will be the same eventually
				while (startHigh < endHigh) {
					int midHigh = startHigh + (endHigh - startHigh)/2;
				System.out.println("midHigh =  " + midHigh + ", startHigh = " + startHigh + " ; endHigh = " + endHigh );

					if (nums[midHigh] > target)  {
						System.out.println(" YES, startHigh =  " + startHigh + " ; endHigh = " + endHigh );
						endHigh = midHigh;
						System.out.println("YES, startHigh =  " + startHigh + " ; endHigh = " + endHigh );}
						else {startHigh = midHigh + 1;
							System.out.println("NO startHigh =  " + startHigh + " ; endHigh = " + endHigh );}
						}

				if (targetRange[0] != -1 )  {
				       if (nums[startHigh] > target ) 
				          targetRange[1] = startHigh - 1  ; //the low and high will be the same eventually, if low 
				//range point found, there would be a high range, it would be the last value - 1
					   else targetRange[1] = startHigh;
						}
						return targetRange;
								// System.out.println(Arrays.toString(nums)); // print array 
						}
						}
		/*

The problem can be simply broken down as two binary searches for the begining and end of the range, respectively:

First let’s find the left boundary of the range. We initialize the range to [i=0, j=n-1]. In each step, calculate the middle element 
[mid = (i+j)/2]. Now according to the relative value of A[mid] to target, there are three possibilities:

If A[mid] < target, then the range must begins on the right of mid (hence i = mid+1 for the next iteration)
If A[mid] > target, it means the range must begins on the left of mid (j = mid-1)
If A[mid] = target, then the range must begins on the left of or at mid (j= mid)
Since we would move the search range to the same side for case 2 and 3, we might as well merge them as one single case so that less code is needed:

2*. If A[mid] >= target, j = mid;

Surprisingly, 1 and 2* are the only logic you need to put in loop while (i < j). When the while loop terminates, the value of i/j is where the 
start of the range is. Why?

No matter what the sequence originally is, as we narrow down the search range, eventually we will be at a situation where there are only two 
elements in the search range. Suppose our target is 5, then we have only 7 possible cases:

case 1: [5 7] (A[i] = target < A[j])
case 2: [5 3] (A[i] = target > A[j])
case 3: [5 5] (A[i] = target = A[j])
case 4: [3 5] (A[j] = target > A[i])
case 5: [3 7] (A[i] < target < A[j])
case 6: [3 4] (A[i] < A[j] < target)
case 7: [6 7] (target < A[i] < A[j])
For case 1, 2 and 3, if we follow the above rule, since mid = i => A[mid] = target in these cases, then we would set j = mid. Now the loop terminates and i and j both point to the first 5.

For case 4, since A[mid] < target, then set i = mid+1. The loop terminates and both i and j point to 5.

For all other cases, by the time the loop terminates, A[i] is not equal to 5. So we can easily know 5 is not in the sequence if the comparison fails.

In conclusion, when the loop terminates, if A[i]==target, then i is the left boundary of the range; otherwise, just return -1;

For the right of the range, we can use a similar idea. Again we can come up with several rules:

If A[mid] > target, then the range must begins on the left of mid (j = mid-1)
If A[mid] < target, then the range must begins on the right of mid (hence i = mid+1 for the next iteration)
If A[mid] = target, then the range must begins on the right of or at mid (i= mid)
Again, we can merge condition 2 and 3 into:

2* If A[mid] <= target, then i = mid;
However, the terminate condition on longer works this time. Consider the following case:

[5 7], target = 5
Now A[mid] = 5, then according to rule 2, we set i = mid. This practically does nothing because i is already equal to mid. As a result, 
the search range is not moved at all!

The solution is by using a small trick: instead of calculating mid as mid = (i+j)/2, we now do:

mid = (i+j)/2+1
Why does this trick work? When we use mid = (i+j)/2, the mid is rounded to the lowest integer. In other words, mid is always biased 
towards the left. This means we could have i == mid when j - i == mid, but we NEVER have j == mid. So in order to keep the search 
range moving, you must make sure the new i is set to something different than mid, otherwise we are at the risk that i gets stuck. 
But for the new j, it is okay if we set it to mid, since it was not equal to mid anyways. Our two rules in search of the left boundary 
happen to satisfy these requirements, so it works perfectly in that situation. Similarly, when we search for the right boundary, 
we must make sure i won’t get stuck when we set the new i to i = mid. The easiest way to achieve this is by making mid biased to 
the right, i.e. mid = (i+j)/2+1.

All this reasoning boils down to the following simple code:

vector<int> searchRange(int A[], int n, int target) {
    int i = 0, j = n - 1;
    vector<int> ret(2, -1);
    // Search for the left one
    while (i < j)
    {
        int mid = (i + j) /2;
        if (A[mid] < target) i = mid + 1;
        else j = mid;
    }
    if (A[i]!=target) return ret;
    else ret[0] = i;
    
    // Search for the right one
    j = n-1;  // We don't have to set i to 0 the second time.
    while (i < j)
    {
        int mid = (i + j) /2 + 1;	// Make mid biased to the right
        if (A[mid] > target) j = mid - 1;  
        else i = mid;				// So that this won't make the search range stuck.
    }
    ret[1] = j;
    return ret; 
}

		*/

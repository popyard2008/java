import java.util.*;
public class rotarySearch2{
  public static void main(String[] args) {
    int[] numsO = {1};
    int target = 1;
    System.out.println(search(numsO, target));
  }

  public static boolean search(int[] nums, int target) {
    int lo = 0;
    int hi = nums.length - 1;
     // Boolean sameSide;

    // while ((lo + 1 ) < hi && nums[lo] == nums[lo + 1]) {lo ++;}
    // while ((hi - 1 ) >  0 && nums[hi - 1] == nums[hi]) {hi --;}

    // if (nums[lo] == nums[hi] && lo < hi ) lo ++;
          while (lo <= hi) {
            while (lo < hi && nums[lo] == nums[hi]) {hi --;}
            int mid = (lo + hi) >> 1; // = (lo+hi)/2
            // num = nums[mid];
            double num = (nums[mid] <  nums[0]) == (target < nums[0])
                    ? nums[mid]
                    : target < nums[0] ? Long.MIN_VALUE : Long.MAX_VALUE;
            System.out.println("num = " + num);
            // sameSide = (num > lo ) && (target > lo);
            if (num > target ) {
              hi = mid - 1;
              // if ( hi >= 0 && hi <= (nums.length - 1) && nums[hi] == target ) return true;

              System.out.println("hi = " + hi );
            } else if (num < target) {
              lo = mid + 1;
              // while ((lo + 1 ) < hi && nums[lo] == nums[lo + 1]) {lo ++;}
              // if (nums[lo] == nums[hi] && lo < hi ) lo ++;
              System.out.println("lo = " + lo);
              // if (lo <= hi && nums[lo] == target ) return true;
            } else {
              return true;
            }
    }

    return false;
}
}

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?
Seen this question in a real interview before?  YesNo

*/

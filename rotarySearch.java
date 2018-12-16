import java.util.*;
public class rotarySearch{
  public static void main(String[] args) {
    int[] numsO = {1};
    int target = 0;
    System.out.println(search(numsO, target));
  }

  public static int search(int[] nums, int target) {
    int lo = 0;
    int hi = nums.length - 1;
     // Boolean sameSide;

    while (lo <= hi) {
      int mid = (lo + hi) >> 1; // = (lo+hi)/2
      // num = nums[mid];
      double num = (nums[mid] < nums[0]) == (target < nums[0])
              ? nums[mid]
              : target < nums[0] ? Long.MIN_VALUE : Long.MAX_VALUE;
      // sameSide = (num > lo ) && (target > lo);
      if (num > target ) {
        hi = mid - 1;
      } else if (num < target) {
        lo = mid + 1;
      } else {
        return mid;
      }


    }

    return -1;
}
}
/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
*/

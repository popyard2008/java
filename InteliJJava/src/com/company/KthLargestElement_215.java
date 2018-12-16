package com.company;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElement_215 {

    public static void   runThem( ){

        int[] nums = {3, 4, 5, 6, 7, 8};
        System.out.println("the kth largest number is " + findKthLargest_queue(nums, 3));
        System.out.println("the kth largest number is " + findKthLargest_selection(nums, 3));
    }


    public  static int findKthLargest(int [] nums, int k) {
        final int N = nums.length;
        Arrays.sort(nums);
        return nums[N - k];
    }

    private static int findKthLargest_queue(int[] nums, int k){
        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums){

            System.out.println(" on the top before pop in: " + pq.peek() + " added number is " + val);
            pq.offer(val);
            System.out.println(" on the top: " + pq.peek());

            if (pq.size() > k) {
                System.out.println(" popout: " + pq.poll());
            }
        }
        return pq.peek();
    }

    private static int findKthLargest_selection (int[] nums, int k) {
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private static int partition(int[] a, int lo, int hi){
        int i = lo;
        int j = hi + 1;
        while (true){
            while(i < hi && less(a[++i], a[lo]));
            while (j > lo && less(a[lo], a[--j]));
                if(i >= j){
                    break;
                }
                exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void exch(int[] a, int i, int j){
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static boolean less ( int v, int w) {
        return v < w;
    }
}

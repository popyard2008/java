package com.company;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        int[] nums = {3, 4, 5, 6, 7, 8};
//        System.out.println("the kth largest number is " + KthLargestElement_215.findKthLargest(nums, 3));
//
//        System.out.println("the kth largest number is " + KthLargestElement_215.findKthLargest_queue(nums, 3));
//        System.out.println("the kth largest number is " + KthLargestElement_215.findKthLargest_selection(nums, 3));
//        KthLargestElement_215.runThem();

        wordBreakII_140 wb = new wordBreakII_140();
        Set set = new HashSet();
        set.add( "cat");
        set.add( "cats");
        set.add("and");
        set.add("sand");
        set.add("dog");
        List a = wb.wordBreaker("catsanddog", set);
        System.out.println(a.toString());
    }
}
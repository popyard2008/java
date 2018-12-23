package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
// ////////////////////////
//  triangle
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        List<List<Integer>> finList = new ArrayList<>();

        list1.add(2);
        list2.add(3);
        list2.add(4);
        list3.add(6);
        list3.add(5);
        list3.add(7);
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);

        finList.add(list1);
        finList.add(list2);
        finList.add(list3);
        finList.add(list4);

        triangle_120 tag = new triangle_120();

        System.out.println(tag.minimumTotal(finList));


        //longestConcectiveSequence_128
//         int[] nums = {100, 4, 200, 1, 3, 2};
//
//         longestConcectiveSequence_128 lcsnum = new longestConcectiveSequence_128();
//
//          System.out.println(lcsnum.longestConsecutive(nums));

//        List<String> wordList = new ArrayList<>();


// ////////////////////////
/*
//word ladder
        Set<String> wordList = new TreeSet<>() ;

        wordList.add("hot");
        wordList.add("dot");
        wordList.add("got");
        wordList.add("hdt");
//        wordList.add("dog");
        wordList.add("lot");
//        wordList.add("log");
//        wordList.add("cog");
        wordLadder_3_127 wl = new wordLadder_3_127();
        System.out.println(wl.ladderLength("hit", "cog", wordList));

*/
//        beginWord = "hit",
//                endWord = "cog",
//                wordList = ["hot","dot","dog","lot","log","cog"]
//        wordList = ["hot","dot","dog","lot","log"]
        //       KthLargestElement_215
//        int[] nums = {3, 4, 5, 6, 7, 8};
//        System.out.println("the kth largest number is " + KthLargestElement_215.findKthLargest(nums, 3));
//
//        System.out.println("the kth largest number is " + KthLargestElement_215.findKthLargest_queue(nums, 3));
//        System.out.println("the kth largest number is " + KthLargestElement_215.findKthLargest_selection(nums, 3));
//        KthLargestElement_215.runThem();

        //wordBreakII_140
        /*
        wordBreakII_140 wb = new wordBreakII_140();
        Set set = new HashSet();
        set.add( "cat");
        set.add( "cats");
        set.add("and");
        set.add("sand");
        set.add("dog");
        List a = wb.wordBreaker("catsanddog", set);
        System.out.println(a.toString());
        */

    }
}

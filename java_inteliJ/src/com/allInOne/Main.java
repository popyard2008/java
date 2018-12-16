package com.allInOne;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //
        wordBreakII_140 wb = new wordBreakII_140();
        Set set = new HashSet();
        set.add( "cat");
        set.add( "cats");
        set.add( "sand");
        set.add( "and");
        set.add( "dog");

        List a = wb.wordBreaker("catsanddogcat", set);
        System.out.println(a.toString());
        //        fibonacci.run();
/*
        findMedianFromDataStream_295 fm = new findMedianFromDataStream_295();
        fm.addNum(5);
        fm.addNum(6);
        fm.addNum(7);
        fm.addNum(8);
        fm.addNum(1000);

        System.out.println(fm.findMedian());
        //        fibonacci.run();
*/

    }
}

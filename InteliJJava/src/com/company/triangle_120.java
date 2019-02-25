package com.company;

import java.util.List;

import static java.lang.String.valueOf;

public class triangle_120 {
    public int minimumTotal(List<List<Integer>> triangle){
        for (List<Integer> lst : triangle){
            System.out.println(valueOf(lst));
//            for(int trig : lst){
//                System.out.println(trig);
//
//            }
        }
        System.out.println("  *************  ");

        int [] A = new int[triangle.size() + 1];
        for (int i = triangle.size()-1; i >= 0; i--){
            for (int j = 0; j < triangle.get(i).size(); j++){
                System.out.println("i = " + i + " ; j = " + j + " A[j = " + j + "] =" + A[j] +  " A[j+1 = " + (j + 1)  + "] = " + A[j+1] + " ; get : " + triangle.get(i).get(j));
                A[j] = Math.min(A[j], A[j+1]) + triangle.get(i).get(j);
                System.out.println("i = " + i + " ; j = " + j + " A[j = " + j + "] =" + A[j] +  " A[j+1 = " + (j + 1)  + "] = " + A[j+1] + " ; get : " + triangle.get(i).get(j));
                System.out.println("");

            }
        }

        return A[0];
    }

}
/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
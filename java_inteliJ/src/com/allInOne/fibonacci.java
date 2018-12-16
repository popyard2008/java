package com.allInOne;

public class fibonacci {

    public static void run() {
        int l = 100;
     int[] res = new int[l + 1];
     res[0] = 0; res[1] = 1;
   int k =  fibonacciCacu(res, l);
    System.out.println(" fibonacciCacu (" + l + ")= " + k );

    }
   private static int fibonacciCacu(int[] res, int n) {
        if (res[n] != 0) {
            System.out.println("no caculation ");
            return res[n];}
        if (n <= 1) {return n;}
        else {
            System.out.println("before " + res[n] );

            res[n] = fibonacciCacu(res, n -1 ) + fibonacciCacu(res, n - 2 );
            System.out.println(res[n] );
            return res[n];
        }

    }

}

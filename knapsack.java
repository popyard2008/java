class knapsack
{

    // A utility function that returns maximum of two integers
     static int max(int a, int b) { return (a > b)? a : b; }

     // Returns the maximum value that can be put in a knapsack of capacity W
     static int knapSack(int W, int wt[], int val[], int n)
     {
       System.out.println("n = " + n );
      if (idx[W][n] != -1)  {
        System.out.println("output from saved value: W = " + W + " n = " + n + " idx = " + inx[W][n]);
        return idx[W][n];
      }
        // Base Case
    if (n == 0 || W == 0)
      { System.out.println(" #1   return " );
        return 0;}

    // If weight of the nth item is more than Knapsack capacity W, then
    // this item cannot be included in the optimal solution
    if (wt[n-1] > W)
      { int k = knapSack(W, wt, val, n-1);
        System.out.println(" #2 wt[n-1] = " + wt[n-1] + " W = " + W + " knapSack(W, wt, val, n-1): " + k);
        return k ;}

    // Return the maximum of two cases:
    // (1) nth item included
    // (2) not included
    else {
      int o =  knapSack(W, wt, val, n-1);
      int m = knapSack(W-wt[n-1], wt, val, n-1);
      int p = val[n-1];
      System.out.println(" #3 n = " + n + " val[n-1] = " + p );
      System.out.print (" knapSack(W, wt, val, n-1): " + o);
      System.out.print (" knapSack(W-wt[n-1], wt, val, n-1): " + m);
      System.out.println(" max return " + max(m + p, o) );
      idx[W][n] = max(p + m, o);
      return idx[W][n];

    // return max(val[n-1] + knapSack(W-wt[n-1], wt, val, n-1),
    //                  knapSack(W, wt, val, n-1)
    //                   );
      }
    }
   // Driver program to test above function
   public static void main(String args[])
   {
        int val[] = new int[]{ 100, 120};
        int wt[] = new int[]{ 20, 30};

    int  W = 40;
    int n = val.length;
    int idx[][] = new int[n+1][W+1];
    for (int i = 0; i < n ; i ++ ) {
      for (int j = 0; j< n ; j++ ) {
        idx[i][j] = -1;
      }

    }

    System.out.println(knapSack(W, wt, val, n));
    }
}
/*This code is contributed by Rajat Mishra */

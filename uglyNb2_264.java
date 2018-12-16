// A PriorityQueue based Java program to maximize array 
// sum after k negations. 
import java.util.*; 

class uglyNb2_264 
{ 
	public static int nthUglyNumber(int k) 
	{ 
		if (k == 1) return 1;

		int nextUglyX2 = 1;  //next ugly number X 2
		int nextUglyX3 = 1;	 // next ugly nubmer X 3
		int nextUglyX5 = 1;  // next ugly number X 5
		int[] uglyNumber = new int[1691];    // the ugly number
		uglyNumber[1] = 1;

		for (int i = 2; i <= k; i ++ ) {
		uglyNumber[i] = Math.min(Math.min(uglyNumber[nextUglyX2] * 2 , uglyNumber[nextUglyX3] * 3),  uglyNumber[nextUglyX5] * 5);
		// System.out.println(uglyNumber[i]); 
		if (uglyNumber[i] % 2 == 0) nextUglyX2 ++;
		if (uglyNumber[i] % 3 == 0) nextUglyX3 ++;
		if (uglyNumber[i] % 5 == 0) nextUglyX5 ++;
	    }
		return uglyNumber[k];

	} 

	// Driver code 
	public static void main (String[] args) 
	{ 
		int k = 1690; 
		System.out.println("final ugly number is " + nthUglyNumber(k)); 
	} 
} 
  
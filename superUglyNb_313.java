// A PriorityQueue based Java program to maximize array 
// sum after k negations. 
import java.util.*; 

class superUglyNb_313 
{ 
		// Driver code 
	public static void main (String[] args) 
	{ 
		int n = 100; 
		int[] primes = {2, 3, 7 , 13, 19};
		System.out.println("final ugly number is " + superUglyNb(n, primes)); 
		System.out.println("final ugly number is " + superUglyNb_method2(n, primes)); 
	} 

	public static int superUglyNb(int n, int[] primes) 
	{ 
		if (n == 1) return 1;

		int[] uglyNumber = new int[n + 1];
		
		uglyNumber[1] = 1;
		
		int[] indxNexUglyX = new int[primes.length + 1];

		for (int i = 1; i <= primes.length ; i ++ ) {indxNexUglyX[i] = 1;} // reset initial value to 1 all start point nextMultiple
		
		for (int i = 2; i <= n; i ++ ) { // the # of ugly number

			PriorityQueue<Integer> pq = new PriorityQueue<>(); 

			for (int j = 1; j<= primes.length; j++) {

				int x = primes[j-1] * uglyNumber[indxNexUglyX[j]];
				// System.out.println("indxNexUglyX[" + j + "] = "+ indxNexUglyX[j] + " x = " + x + " uglyNumber[" + j + "]= " + uglyNumber[indxNexUglyX[j]] );
				pq.add(x); 
				}

			 uglyNumber[i] = pq.poll();

		    // System.out.println(uglyNumber[i]); 

			for (int l = 1; l <= primes.length ; l ++ ){
						    // System.out.println("primes = " + primes[l - 1 ]); 

				if (uglyNumber[i] % primes[l - 1 ] == 0 ) {

					indxNexUglyX[l]++; 
					// System.out.println("indxNexUglyX[" + l + "] = " + indxNexUglyX[l]); 

				}
			}    

		}

		return uglyNumber[n];

	} 

	public static int superUglyNb_method2(int n, int[] primes) 
	{ 
		if (n == 1) return 1;

		int[] uglyNumber = new int[n + 1];
		
		uglyNumber[1] = 1;
		
		int[] indxNexUglyX = new int[primes.length + 1];

		for (int i = 1; i <= primes.length ; i ++ ) {indxNexUglyX[i] = 1;} // reset initial value to 1 all start point nextMultiple
		
		for (int i = 2; i <= n; i ++ ) { // the # of ugly number

			int min = Integer.MAX_VALUE;
			int indx = 0;
			for (int j = 1; j<= primes.length; j++) {

				int x = primes[j-1] * uglyNumber[indxNexUglyX[j]];  // try all the next possible ugly numbers
				if (x < min ) {
					min = x;		//find the minimum ugly number
					indx = j;		// record the indx for the prime number used
				} else if (x == min) {
					indxNexUglyX[j] ++;  // if find the min again, it must be a duplicate if use later one, so indx move up too;
				}

			}
			indxNexUglyX[indx] ++;  // move the indx up for the ugly number that firstly created, not the duplicated
			uglyNumber[i] = min;   //the ugly number found
		}

		return uglyNumber[n];

	} 


} 
   
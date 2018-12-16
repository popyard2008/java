import java.util.Scanner;
public class bestTimeBuyandSale_122 {
 
 
	public static void main(String[] args){
	
		int[] a = {7,1,5,3,6,10};

		System.out.println("max profit is " + cal(a));
		System.out.println("max profit is " + splitdate(a));

	}

	public static int splitdate (int[] raw) {
	 int maxProfit0 = 0;
		int leftProfit = 0;
		int rightProfit = 0;

		for (int k = 2; k < raw.length-1 ; k++ ) {
			int[] newInput = new int[k]; 
			for (int j = 0; j <= k; j++) {newInput[j] = raw[j];}

			leftProfit = cal(newInput);

			System.out.println("max left profit is " + leftProfit);	
			if (leftProfit > maxProfit0) {maxProfit0 = leftProfit;}
		}

		return maxProfit0;
	}




	public static int cal ( int[] input) {
		int maxProfit = 0;
		int tmpProfit = 0;
		int sellingPoint = 0;
		int buyingPoint = 0;
		for (int i = 0 ; i < input.length - 1; i++) {
				// for (int j = i; j < input.length - 1; j ++ ){
			
					buyingPoint = input[i];

					for (int k = i + 1; 	k < input.length; k ++) {
						if (input[k] > buyingPoint) {
							// sellingPoint = input[k] - buyingPoint;
							tmpProfit = input[k]  - buyingPoint;
						if (tmpProfit > maxProfit) { maxProfit = tmpProfit;}
							
						}

			}				
			}
		return maxProfit;
		}

	}
 
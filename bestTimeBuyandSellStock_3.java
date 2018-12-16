import java.util.*;

public class bestTimeBuyandSellStock_3 {
	
	public int maxProfit ( int[] prices) {
		if (prices == null || prices.length == 0) return 0;
		int maximumProfit = 0;
		int n = prices.length;

		ArrayList<Profit> preMaxProfit = new ArrayList <Profit>(n); //this is a learning point
		ArrayList<Profit> postMaxProfit = new ArrayList<Profit>(n);
		for (int i = 0; i < n; i ++) {
			preMaxProfit.add(maxProfitHelper(prices, 0, i));
			postMaxProfit.add(maxProfitHelper(prices, i + 1, n-1));
		}

		for (int i = 0; i < n; i ++) {
			int profit = preMaxProfit.get(i).maxProfit + postMaxProfit.get(i).maxProfit;
			maximumProfit = Math.max(profit, maximumProfit);
		}
		return maximumProfit;
	}

private Profit maxProfitHelper(int[] prices, int startIndex, int endIndex){
	int minPrice = Integer.MAX_VALUE;
	int maxProfit = 0;
	for (int i = startIndex; i <= endIndex; i++){
		if (prices[i] < minPrice) minPrice = prices[i];
		if (prices[i] - minPrice > maxProfit) maxProfit = prices[i] - minPrice;
		// System.out.println("i = " + i + " maxProfit =" + maxProfit + " startIndex = " + startIndex + " endIndex = " + endIndex);
	}
	return new Profit(maxProfit) ;//, minPrice);
}

public static void main(String[] args) {
	int [] prices = new int[] {4, 4, 6 ,0 , 1,4, 2, 7};
	bestTimeBuyandSellStock_3 s = new bestTimeBuyandSellStock_3();
	System.out.println(s.maxProfit(prices));
	System.out.println(s.maxProfit2(prices));

}

class Profit {
	int maxProfit;//, minPrice;
	Profit(int maxProfit)//, int minPrice) 
	{
		this.maxProfit = maxProfit;
		// this.minPrice = minPrice;
	}
}

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        // DP from left to right;
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }

        //DP from right to left;
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(prices[i], max);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }

        int profit = 0;
        for (int i = 0; i < prices.length; i++){
            profit = Math.max(left[i] + right[i], profit);  
        }

        return profit;
    }

}
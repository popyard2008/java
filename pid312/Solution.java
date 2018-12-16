package pid312;
class Solution {
    public int maxCoins(int[] nums) {
    	int[] fullNums = new int[nums.length + 2];
    	fullNums[0] = 1;
    	fullNums[nums.length +1 ] = 1;
    	for ( int i = 1; i <= nums.length; ++i ) {
    		fullNums[i] = nums[i-1];
    	}

    	int[][] store = new int[fullNums.length][fullNums.length];
    	for (int i = 1; i < fullNums.length ; ++i ) {
    		for (int j = i; j < fullNums.length ; ++j ) {
    			store[i][j] = -1;
    		}
    	}
    	return getStore(1, fullNums.length - 2, store, fullNums); 
    }

    private int getStore(int begin, int end, int[][] store, int[] fullNums) {
    	if ( begin > end ) return 0;
    	if ( store[begin][end] != -1 ){
    		return store[begin][end];
    	}
    	for (int pos = begin; pos <= end; ++pos) {
    		int leftCoin = getStore(begin, pos-1, store, fullNums);
    		int midCoin = fullNums[begin - 1] * fullNums[pos] * fullNums[end + 1];
    		int rightCoin = getStore(pos+1, end, store, fullNums);
    		int coin = leftCoin + midCoin + rightCoin;
		System.out.println("begin =  " + begin + "; end = " + end + "; store[" + begin + "][" + end +"] = " + store[begin][end] + " ; coin = " + coin);    	    		
    		if (coin > store[begin][end]) {
    			store[begin][end] = coin;
    		}
    	}
    	return store[begin][end];
    }
}
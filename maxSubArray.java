public class maxSubArray {

         public static void main(String[] args) {
			
         		int[] input = {1, -3, -2,-1, 2, -1,5,-3, -2};

         		int result = maxSubArray(input);

         		System.out.println ( "the result is " + result);

		}

		private static int  maxSubArray (int[] nums) { 
				int[] f = new int[nums.length];
				f[0] = nums[0];
				int sum = nums[0];
				for (int i = 1;i < f.length; i++ ) {
				f[i] = Math.max(f[i-1] + nums[i], nums[i]);
				if (f[i] > sum) { sum = f[i];
				}
				System.out.println("i is " + i + " f[i] = " + f[i]);

				}
			return sum;


		}



}
import java.util.*; 

class degreeOfArray_697
{
		// Driver code
	public static void main (String[] args)
	{
		int[] nums = {2, 3, 3 , 13, 19, 3};
		System.out.println("degree of Array is " + degreeOfArray(nums));
	}

	public static int degreeOfArray(int[] nums)
	{
		Map<Integer, Integer> left = new HashMap(), right = new HashMap(), count = new HashMap();

		for (int i = 0; i < nums.length; i++) {
				int x = nums[i];

				if (left.get(x) == null) {
					left.put(x, i);
				}

				right.put(x, i);
				System.out.println(right.get(x));
				count.put(x, count.getOrDefault(x, 0) + 1);
		}

		int ans = nums.length;
		int degree = Collections.max(count.values());
		for (int x: count.keySet()){
			if (count.get(x) == degree) {
				ans = Math.min(ans, right.get(x) - left.get(x) + 1);
			}
		}
		return ans;
	}


}

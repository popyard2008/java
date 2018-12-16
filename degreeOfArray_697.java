import java.util.*;

class degreeOfArray_697 {

public static void main(String args[])
{
     int nums[] = new int[]{ 1, 1, 5, 5, 5, 120, 120};

     System.out.println(findShortestSubArray(nums));//two hashmap
     System.out.println(findShortestSubArray_method2(nums));//one hashmap with array as value of hashmap.

}

public static int findShortestSubArray(int[] nums){
  Map<Integer, Integer> count = new HashMap<>();//key is array element, value is count
  Map<Integer, Integer> pos = new HashMap<>();//key is array element, value is find position
  int maxCount = 0;
  int res = 0;

  for(int i = 0; i < nums.length; i ++){
      int n = count.getOrDefault(nums[i], 0) + 1;
      //get counts of each elements i = 0, n = 1, i=1, n = 2;
      count.put(nums[i], n);//i=0, count: (1, 1) ; i = 1, count:(1, 2)
      //save to count
      if (!pos.containsKey(nums[i])) pos.put(nums[i], i);//i=0, pos(1, 0), skip i = 1
      // saved for the first time
      if (n > maxCount) {
        // if maxCount of the number found
        maxCount = n; // i = 0, maxCount = 1; i = 1, maxCount = 2;
        res = i - pos.get(nums[i]) + 1; // i = 0; res = 1, res = 2
        // the length of the subarray is the last position - first position
      } else if (n == maxCount) {
          res = Math.min(res, i - pos.get(nums[i]) + 1);
        //if repeated count found then get the small one.
      }
  }

  return res;
}

public static int findShortestSubArray_method2(int[] nums){
  if (nums.length == 0 || nums == null) return 0;
  Map<Integer, int[]> map = new HashMap<>();
  for (int i = 0; i < nums.length; i++){
    if(!map.containsKey(nums[i])){
      map.put(nums[i], new int[]{1, i, i});
      //// the first element in array is degree,
      //second is first index of this key,
      // third is last index
      int[] temp = map.get(nums[i]);
      System.out.println("temp[0] = " + temp[0] + "; temp[1] = " + temp[1] + "; temp[2] = " + temp[2]);
    } else {
      int[] temp = map.get(nums[i]);
      //take the values out and do updates on it, cool!
      temp[0]++;
      temp[2] = i;
      System.out.println("temp[0] = " + temp[0] + "; temp[1] = " + temp[1] + "; temp[2] = " + temp[2]);
      int[] temp_2 = map.get(nums[i]);
      System.out.println("temp_2[0] = " + temp_2[0] + "; temp_2[1] = " + temp_2[1] + "; temp_2[2] = " + temp_2[2]);

    }
  }

  int degree = Integer.MIN_VALUE, res = Integer.MAX_VALUE;
    for (int[] value : map.values()){
      if (value[0] > degree){
        degree = value[0];
        res = value[2] - value[1] + 1;
      } else if (value[0] == degree) {
        res = Math.min(value[2] - value[1] + 1, res);
      }
    }

    return res;

}


}

import java.util.HashMap;
import java.util.Map;
import java.util.Enumeration;


public class twoSum2 {


public static void main(String[] args) {
    int[]  twoSumResutls ;
    int[]  inputSerials = {1,2,4,7};
    int    a = 5;

    twoSum(inputSerials, a);

    // for (int i = 0; i< inputSerials.length ; i++ ) {
    //         System.out.println(inputSerials[i]);

    // }
    // System.out.println(twoSumResutls);
}

// public int[] twoSum(int[] nums, int target) {
public static void twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        map.put(nums[i], i);
    }
                System.out.println(map);

    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement) && map.get(complement) != i) {
            // return new int[] { i, map.get(complement) };
            System.out.println(i);
            System.out.println(map.get(complement));
        }
    }
    // throw new IllegalArgumentException("No two sum solution");
}

// public static int[] twoSum(int[] nums, int target) {
// public static void twoSum(int[] nums, int target) {

//     for (int i = 0; i < nums.length; i++) {
//         for (int j = i + 1; j < nums.length; j++) {
//             if (nums[j] == target - nums[i]) {
//                 // return new int[] { i, j 
//             System.out.println(i);
//             System.out.println(j);
//             };
//             }
//         }
//     }
    // throw new IllegalArgumentException("No two sum solution");


}
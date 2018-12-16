public class twoSum1 {

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


// public static int[] twoSum(int[] nums, int target) {
public static void twoSum(int[] nums, int target) {

    for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] == target - nums[i]) {
                // return new int[] { i, j 
            System.out.println(i);
            System.out.println(j);
            };
            }
        }
    }
    // throw new IllegalArgumentException("No two sum solution");


}
public class isPalindrome {

    public static void main(String[] args) {
    
    // int input = 234554312;

    // System.out.println (" is " + input + " Palindrome or NOT? " + isPalindrome(input));
    // System.out.println(" is " + input + " Palindrome or NOT by 2nd? " + isPalindrome2(input));

        for (int i = 0 ; i < 20 ; i ++ ) {
          if (isPalindrome(i) != isPalindrome2(i)) {
                System.out.println("different results at " + i);               
            }  
        }

   }

   public static boolean isPalindrome2(int x) {

  if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        
        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        
        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123, 
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return x == revertedNumber || x == revertedNumber/10;
    }

   public static boolean isPalindrome(int x) {
      int result = 0;
      int orignalX = x;
      int tail = 0;
      int newResult = 0;
      if (x < 0) {
          return false;
      }

      while (x != 0)
      {
         tail = x % 10;//REMINDER AFTER DIVEDER BY 10
         newResult = result * 10 + tail;
        if ((newResult - tail)/10 != result) // FOR OVERFLOW CHECKING
           return false; 
        result = newResult;
        x = x / 10;
        // System.out.println("tail =" + tail + " , newResult = " + newResult + ", result = " + result + ", x = " + x );

    }

    return result == orignalX;
}


}

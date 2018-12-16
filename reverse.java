public class reverse {

    public static void main(String[] args) {
        int input = 1234567899;
        int reverseResult = reverse(input);

        // for (int i = 0; i <10000000 ; i++) {
         // int reverseResult2 = reverse2(input);
         // int reverseResult1 = reverse1(input);

           // if (reverse2(i) != reverse(i) )
               // System.out.println("different results on : " + i );
       // } ;
        System.out.println("final result is :" + reverseResult); 

        // int reverseResult2 = reverse2(input);
        // System.out.println("final result 2 is :" + reverseResult2); 
   }


   public static int reverse(int x) {
      int result = 0;

      while (x != 0)
      {
        int tail = x % 10;//REMINDER AFTER DIVEDER BY 10
        int newResult = result * 10 + tail;
        if ((newResult - tail)/10 != result) // FOR OVERFLOW CHECKING
           return 0; 
        result = newResult;
        x = x / 10;
        // System.out.println("tail =" + tail + " , newResult = " + newResult + ", result = " + result + ", x = " + x );

    }

    return result;
}

public static int reverse2(int x) {
        int lastReminder = 0; 
        int reversedResult = 0;
        int remainder = 0;
        while (x != 0) {
            remainder = x % 10;
            reversedResult = lastReminder * 10 + remainder;
            lastReminder = reversedResult;
            x /= 10;
        }
        return reversedResult;
            
}

/*
public static int reverse2(int x) {
        string str = to_string(x);
        int i,j,res,sign;
        if(str[0] == '-')
            sign = -1;
        else
            sign = 1;
        for(i = 0, j = str.size() - 1; i<j;i++,j--){
            str[i] ^= str[j];
            str[j] ^= str[i];
        }
        for(i = 0, j = str.size() - 1; i<j;i++,j--){
            str[i] ^= str[j];
        }
        // try {
            res = stoi(str);
        // }
        // catch(exception& e){
             // res = 0;
        // }
        return sign*res;
    }   
*/
}

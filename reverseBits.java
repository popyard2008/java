public class reverseBits {

  public static void main(String[] args) {

// public main class (String[] args){
int a = 9;
System.out.println("reversed " + a + " is " + reverseBits(a));

}
public static int reverseBits(int n) {
    int result = 0;
    for (int i = 0; i < 32; i++) {
        result += n & 1;
        n >>>= 1;   // CATCH: must do unsigned shift
        if (i < 31) // CATCH: for last digit, don't shift!
            result <<= 1;
    }
    return result;
}

}

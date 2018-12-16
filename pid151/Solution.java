package pid151;

public class Solution {

  public String reverseWords(String s) {
    if (s == null) return null;

    char[] a = s.toCharArray();
    int n = a.length;

    // step 1. reverse the whole string
    reverse(a, 0, n - 1);
    // System.out.println("the mediate results are: " + a );

    // step 2. reverse each word
    reverseWords(a, n);
    // System.out.println("the mediate results are: " + a );

    // step 3. clean up spaces
    return cleanSpaces(a, n);
  }

  void reverseWords(char[] c, int n) {
    int i = 0, j = 0;

    while (i < n) {
      while (i < j || i < n && c[i] == ' ') i++; // skip spaces
      while (j < i || j < n && c[j] != ' ') j++; // skip non spaces
      reverse(c, i, j - 1);                      // reverse the word
    }
  }

  // trim leading, trailing and multiple spaces
  String cleanSpaces(char[] a, int n) {
    int i = 0, j = 0;

    while (j < n) {
      while (j < n && a[j] == ' ') j++;             // skip spaces
      while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
      while (j < n && a[j] == ' ') j++;             // skip spaces
      if (j < n) a[i++] = ' ';                      // keep only one space
    }
    {
    System.out.println(a[i] + "end" + a[i+1] + "end");

    return new String(a).substring(0, i);}
  }

  // reverse a[] from a[i] to a[j]
  private void reverse(char[] b, int i, int j) {
    while (i < j) {
      char t = b[i];
      b[i++] = b[j];
      b[j--] = t;
    }
  }

}

package pid_385;
import java.util.*;

class Solution {
     public static NestedInteger deserialize(String s) {
            if(s == null || s.isEmpty() || s.length() == 0) return new NestedInteger();
            Stack<NestedInteger> workStack = new Stack<NestedInteger>();
            NestedInteger result = null;
            StringBuilder sb = new StringBuilder();
            int i = 0;
            //if it's just a single number, then we'll just return a nested integer with one integer
            if(s.charAt(i) != '['){
                sb.setLength(0);
                while(i < s.length() && ((Character.getNumericValue(s.charAt(i)) < 10 && Character.getNumericValue(s.charAt(i)) >= 0) || s.charAt(i) == '-')){
                  System.out.println("i = " +i + "; charAt(i) = " + s.charAt(i));
                    sb.append(s.charAt(i));
                    i++;
                }
                int num = Integer.parseInt(sb.toString());
                System.out.println("num = " + num);

                return new NestedInteger(num);
            }//all other cases, we'll return a nested integer with a list
            else{
                while (i < s.length()) {
                    if (s.charAt(i) == '[') {
                        NestedInteger ni = new NestedInteger();
                        // we'll put this one into its last one if there's one on the workStack
                        if (!workStack.isEmpty()) {
                            NestedInteger lastNi = workStack.pop();
                            lastNi.add(ni);
                            workStack.push(lastNi);// then push it back
                        }
                        workStack.push(ni);
                        i++;
                    } else if (s.charAt(i) == ',') {
                        i++;
                    } else if (s.charAt(i) == ']') {
                        NestedInteger completedNi = workStack.pop();
                        result = completedNi;
                        i++;
                    } else {
                        // then it must be a number
                        sb.setLength(0);
                        while (i < s.length()
                                && ((Character.getNumericValue(s.charAt(i)) < 10 && Character
                                        .getNumericValue(s.charAt(i)) >= 0) || s.charAt(i) == '-')) {
                            sb.append(s.charAt(i));
                            System.out.println("sb = " + sb);
                            System.out.println("sb-i = " + i);
                            i++;
                        }
                        int num = Integer.parseInt(sb.toString());
                        System.out.println("num = " + num);
                        NestedInteger ni = null;

                        if (!workStack.isEmpty())
                          {  ni = workStack.pop();

                        System.out.println(" ni = " + ni.printNi());}

                        System.out.println("i 0 = " + i + " ni = " + ni);}
                        else
                          {  ni = new NestedInteger();
                        System.out.println(" i 1 = " + i);}
                        // case 1: if this one contains one integer
                        if (ni.isInteger()) {
                            // we'll add it to this ni
                            ni.add(new NestedInteger(num));
                        System.out.println("  i 2 = " + i);
                            }
                        // case 2: if this one contains a nested integer
                        else if (ni.getList() != null && ni.getList().size() != 0) {
                            // we'll get the last nested integer and add this one to it
                          System.out.println("   i 3 = " + i);
                            ni.add(new NestedInteger(num));
                        } else {
                            // case 3: if this is an empty nested integer
                            if(i > 0) ni.add(new NestedInteger(num));
                            else ni.setInteger(num);
                            System.out.println("    i 4 = " + i);
                        }
                        workStack.push(ni);
                        if (i == s.length())
                            return ni;// this is for test cases like this: "324", there's no '[' or ']'
                    }
                }
            }
            return result;
        }
}

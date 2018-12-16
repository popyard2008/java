import java.util.*;
/*Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.
*/
public class stringIsValid
{
        // Driver method
	public static void main(String args[])
	{
		String str = "(){}[]";

		System.out.println("the result is "  + isValid(str));

	}

	public static boolean isValid(String s) {
		// Stack<character> stack = new Stack<character>();
		// for (char c : s.toCharArray() ){ 						//c: <> c : 
		// 	if (c == '(')
		// 		stack.push(')');
		// 	else if (c == '[')
		// 		stack.push(']');
		// 	else if (c == '{')
		// 		stack.push('}');
		// 	else if (stack.isEmpty() || stack.pop() != c)
		//    return false;
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {

		System.out.println("c is " + c + " and stack is " + stack);
			if (c == '(')
				{stack.push(')');
			System.out.println("stack of ( is  " + stack);}
			else if (c == '{')
				{stack.push('}');
			System.out.println("stack { is " + stack ) ;}
			else if (c == '[')
				{ stack.push(']');
			System.out.println("stack of [ is " + stack);}			
			else if (stack.isEmpty() || stack.pop() != c)  // stack.pop() remove the last one and return it
				{System.out.println("final stack() is " + stack);

			return false;}
		}
	
	return stack.isEmpty();

}

}

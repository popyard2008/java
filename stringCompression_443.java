import java.util.*;
/*
Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.


Follow up:
Could you solve it using only O(1) extra space?
*/

class stringCompression_443
{
	public static void main (String[] args){

		char[] chars = {'a', 'a', 'b','b', 'b' ,'b', 'b','b', 'b' ,'b','b','b', 'b' ,'b'};

		System.out.println(compress(chars));
	}
/*
We will use separate pointers read and write to mark where we are
reading and writing from. Both operations will be done left to
\right alternately: we will read a contiguous group of characters,
then write the compressed version to the array. At the end, the position
of the write head will be the length of the answer that was written.

Algorithm

Let's maintain anchor, the start position of the contiguous group of
characters we are currently reading.

Now, let's read from left to right. We know that we must be at the
end of the block when we are at the last character, or when the next
character is different from the current character.

When we are at the end of a group, we will write the result of that
group down using our write head. chars[anchor] will be the correct
character, and the length (if greater than 1) will be read - anchor + 1.
We will write the digits of that number to the array.
*/
		public static int compress(char[] chars){
			int anchor = 0, write =0;
			for (int read = 0; read < chars.length; read ++){
				 if(read + 1 == chars.length || chars[read + 1] != chars[read]){
				     chars[write++] = chars[anchor];
						 if (read > anchor){
							  for (char c: ("" + (read - anchor + 1)).toCharArray()){
									 chars[write++] = c;
									 System.out.println(c);

								 }
							 }
							 anchor = read + 1;
						 }
					 }
			return write;
		}

}

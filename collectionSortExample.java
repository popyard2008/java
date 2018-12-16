import java.util.*;

class collectionSortExample
{
		// Driver code
	public static void main (String[] args)
	{
			Integer init[] = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 11};
			List list = new ArrayList(Arrays.asList(init));

			System.out.println("List value before: " + list);

			Collections.sort(list);

			System.out.println("List valuse after sort: " + list);


}

}

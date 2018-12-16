import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.*;



public class iterationTest {

	public static void main(String[] args) throws IOException {

		Map<Integer, Integer> map = new HashMap<>();
		map.put(1, 10);
		map.put(2, 20);
		map.put(3, 30);

		// Iterating entries using a For Each loop
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println("for (Map.Entry<Integer, Integer> entry : map.entrySet()) :  + Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}

		for (Integer key : map.keySet()) {
			System.out.println("Key = " + key + "  value =" + map.get(key));
		}

		// Iterating over values
		for (Integer value : map.values()) {
			System.out.println("Value = " + value);
		}

		// Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
		// while (entries.hasNext()) {
			// Map.Entry<Integer, Integer> entry = entries.next();
			// System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		// }


		Map mapNG = new HashMap();
		mapNG.put(1, 10);
		mapNG.put(2, 20);

		Iterator<Map.Entry> entries = mapNG.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			Integer key = (Integer) entry.getKey();
			Integer value = (Integer) entry.getValue();
			System.out.println("Key = " + key + ", (Value + 10) = " + (value+10) );
		}

				map.forEach((k, v) -> System.out.println("key: " + k + " value:" + v));



      // Create an array list
      ArrayList al = new ArrayList();
      
      // add elements to the array list
      al.add("C");
      al.add("A");
      al.add("E");
      al.add("B");
      al.add("D");
      al.add("F");

      // Use iterator to display contents of al
      System.out.print("Original contents of al: ");
      Iterator itr = al.iterator();
      
      while(itr.hasNext()) {
         Object element = itr.next();
         System.out.print(element + " ");
      }
      System.out.println();
      
      // Modify objects being iterated
      ListIterator litr = al.listIterator();
      
      while(litr.hasNext()) {
         Object element = litr.next();
         litr.set(element + "+");
      }
      System.out.print("Modified contents of al: ");
      itr = al.iterator();
      
      while(itr.hasNext()) {
         Object element = itr.next();
         System.out.print(element + " ");
      }
      System.out.println();

      // Now, display the list backwards
      System.out.print("Modified list backwards: ");
      
      while(litr.hasPrevious()) {
         Object element = litr.previous();
         System.out.print(element + " ");
      }
      System.out.println();
 





	}
}
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
 
/**
 * This class is used to show the PriorityQueue functionality.
 * @author codesjava
 */
public class PriorityQueueTest {
	public static void main(String args[]){
		//Create PriorityQueue  object.
		Queue priorityQueue  = new PriorityQueue();
 
		//Add objects to the PriorityQueue .
		priorityQueue.add("Gourav");
		priorityQueue.add("Neeraj");
		priorityQueue.add("Deepak");
		priorityQueue.add("Mohan");
		priorityQueue.add("Parmender");
 
		//Print the PriorityQueue object.
		System.out.println("HasPriorityQueue elements:");
		System.out.println(priorityQueue);
 
		//Print the PriorityQueue elements using iterator.
		Iterator iterator1=priorityQueue.iterator();
		System.out.println("PriorityQueue elements " +
				"using iterator:");
		while(iterator1.hasNext()){  
		   System.out.println(iterator1.next());  
		}
 
		//Print the head element of the PriorityQueue
		System.out.println("Head element: " 
				+ priorityQueue.element());
		System.out.println("Head element: " 
				+ priorityQueue.peek());
 
		//Remove the head element of the PriorityQueue
		priorityQueue.poll();
		priorityQueue.remove();
 
		//Print the PriorityQueue object.
		System.out.println("HasPriorityQueue elements " +
				"after manipulation:");
		System.out.println(priorityQueue);
 
		//Print the PriorityQueue elements using iterator.
		x` iterator2=priorityQueue.iterator();
		System.out.println("PriorityQueue elements after " +
				"manipulation using iterator:");
		while(iterator2.hasNext()){  
		   System.out.println(iterator2.next());  
		}
	}
}
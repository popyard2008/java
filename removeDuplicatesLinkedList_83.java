import java.util.*;
public class removeDuplicatesLinkedList_83
{

	Node head; //head of list   Node is the head....

	public static class Node {
		int val;
		Node next;
		Node(int d) {val = d; next = null;}
	}


	public static void main(String[] args){
		removeDuplicatesLinkedList_83 llist = new removeDuplicatesLinkedList_83();
		removeDuplicatesLinkedList_83 result = new removeDuplicatesLinkedList_83();


		llist.head = new Node(1);
		Node second = new Node(2);
		Node third =  new Node(3);

		// llist.head.next = second;
		// second.next = third;

		llist.printList();

		System.out.println(" -- ");


		result = deleteDuplicates(llist.head); // usae two node to call


		while (result != null)
		{
			System.out.println(result.val + " " + result.next);
			result = result.next;
		}

	}

    public ListNode deleteDuplicates(ListNode head) {
    	if (head == null) {
    		return null;
    	}
    


    }




	public void printList() {
		Node n = head;
		while (n != null)
		{
			System.out.println(n.val + " " + n.next);
			n = n.next;
		}
	}

}


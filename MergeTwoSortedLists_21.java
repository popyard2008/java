import java.util.*;
/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:
THIS ONE USE OFFICIAL LISTNODE; THERE IS ANOTHER FILE USE USER DEFINED LIST NODE
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
*/
public class MergeTwoSortedLists_21 
{
        // Driver method
	public static void main(String args[])
	{

          LinkedList ll = new LinkedList();
          LinkedList ll2 = new LinkedList();
      
      // add elements to the linked list
      ll.add("F");
      ll.add("B");
      ll.add("D");
      ll.add("E");
      ll.add("C");
      ll.addLast("Z");
      ll.addFirst("A");
      ll.add(1, "A2");
      System.out.println("Original contents of ll: " + ll);
      // add elements to the linked list
      ll2.add("F");
      ll2.add("B");
      ll2.add("D");
      ll2.add("E");
      ll2.add("C");
      ll2.addLast("Z");
      ll2.addFirst("A");
      ll2.add(1, "A2");
      System.out.println("Original contents of ll: " + ll2);

		System.out.println("the result is "  + mergeTwoLists(ll, ll2));

	}

	
//Definition for singly-linked list.
  public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }



public static ListNode mergeTwoLists(ListNode l1, ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
}

// ListNode is a class for storing a single node of a linked list storing
// integer values.  It has two public data fields for the data and the link to
// the next node in the list and has three constructors:
//   public ListNode()
//     creates node with data 0, null link
//   public ListNode(int data)
//     creates node with given data, null link
//   public ListNode(int data, ListNode next)
//     creates node with given data and given link

public class ListNode {
    public int data;       // data stored in this node
    public ListNode next;  // link to next node in the list

    // post: constructs a node with data 0 and null link
    public ListNode() {
        this(0, null);
    }

    // post: constructs a node with given data and null link
    public ListNode(int data) {
        this(data, null);
    }

    // post: constructs a node with given data and given link
    public ListNode(int data, ListNode next) {
        this.data = data;
        this.next = next;
    }
}

}

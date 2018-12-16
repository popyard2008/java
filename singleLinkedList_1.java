public class singleLinkedList_1
{
     Node head; //head of list   Node is the head....
     
	// check this: https://www.geeksforgeeks.org/linked-list-set-1-introduction/

	public static class Node {
		int val;
		Node next;
		Node(int d) {val = d; next = null;}
	}


	public static void main(String[] args){



		singleLinkedList_1 llist = new singleLinkedList_1();
		singleLinkedList_1 llist2 = new singleLinkedList_1();
		singleLinkedList_1 resultsList = new singleLinkedList_1();


		llist.head = new Node(1);
		Node second = new Node(2);
		Node third =  new Node(3);

		llist2.head = new Node(1);
		Node second2 = new Node(2);
		Node third2 =  new Node(3);

		// llist.head.next = second;
		// second.next = third;

		llist2.head.next = second2;
		second2.next = third2;

		llist.printList();

		System.out.println(" -- ");

		llist2.printList();

		System.out.println(" -- ");

		Node result = mergeTwoLists(llist.head, llist2.head); // usae two node to call


		while (result != null)
		{
			System.out.println(result.val + " " + result.next);
			result = result.next;
		}

	}


	public static Node mergeTwoLists(Node l1, Node l2) {
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

	   //  public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    // ListNode dummy = new ListNode(0);
    // ListNode cur = dummy;
    // while (l1 != null && l2 != null) {
    //     if (l1.val < l2.val) {
    //         cur.next = new ListNode(l1.val);
    //         l1 = l1.next;
    //     }
    //     cur = cur.next;
    // }
    // if (l1 != null) {
    //     cur.next = l1;
    // }else {
    //     cur.next = l2;
    // }
    // return dummy.next;
    // }




	public void printList() {
		Node n = head;
		while (n != null)
		{
			System.out.println(n.val + " " + n.next);
			n = n.next;
		}
	}

}


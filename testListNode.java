
// Definition for singly-linked list.
 	 // public class ListNode {
 	 //     int val;
 	 //     ListNode next;
 	 //     ListNode(int x) {
 	 //         val = x;
 	 //         next = null;
 	 //     }z
 	 // }

 public class testListNode {

     public static void main(String[] args) {

        ListNode tn0 = new ListNode(0);
        ListNode tn1 = new ListNode(1);
        ListNode tn2 = new ListNode(2);
        ListNode tn3 = new ListNode(3);
        ListNode tn4 = new ListNode(4);
        ListNode tn5 = new ListNode(5);

        tn0.next = tn1;

        tn1.next = tn2;

        tn2.next = tn3;

        tn3.next = tn4;

        System.out.println(tn0.data + " " + tn1.data + " " + tn3.next.data);


    }

    }
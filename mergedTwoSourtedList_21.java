public class mergedTwoSourtedList_21
{

    // check this: https://www.geeksforgeeks.org/linked-list-set-1-introduction/

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int d) {val = d; next = null;}
    }


    public static void main(String[] args){



        ListNode llist = new ListNode(1);
        ListNode llist1 = new ListNode(2);
        ListNode llist11 = new ListNode(3);
        llist.next = llist1;
        llist1.next = llist11;


        ListNode llist2 = new ListNode(1);
        ListNode llist21 = new ListNode(2);
        ListNode llist22 = new ListNode(3);
        llist2.next = llist21;
        llist21.next = llist22;





        ListNode forPrintLN = mergeTwoLists(llist, llist2);


         while (forPrintLN != null)
        {
            System.out.println(forPrintLN.val + " " + forPrintLN.next);
            forPrintLN = forPrintLN.next;
        }

  

  }


    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
/*
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {
            cur.next = new ListNode(l1.val);
            l1 = l1.next;
        }
        cur = cur.next;
    }
    if (l1 != null) {
        cur.next = l1;
    }else {
        cur.next = l2;
    }
    return dummy.next;
    

    }
*/


}


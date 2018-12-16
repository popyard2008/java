public class videoQuestionsCodes {

//Reorder List
public static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x;}

}

 public static void main(String[] args) {
        ListNode ln0 = new ListNode(0);
        ListNode ln1 = new ListNode(1);
        ListNode ln2 = new ListNode(2);
        ListNode ln3 = new ListNode(3);
        ListNode ln4 = new ListNode(4);
        ListNode ln5 = new ListNode(5);
        ListNode ln6 = new ListNode(6);
        ListNode ln7 = new ListNode(7);
        ListNode ln8 = new ListNode(8);
        ListNode ln9 = new ListNode(9);
        ListNode ln10 = new ListNode(10);

        ln0.next = ln1;
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = ln5;
        ln5.next = ln6;
        ln6.next = ln7;
        ln7.next = ln8;
        ln8.next = ln9;
        ln9.next = ln10;

        // 0 -> 1 -> 2   
        // 0 <- 1

        ListNode resultKG = reverseKGroup(ln0, 2);



        System.out.println(resultKG);

        ListNode cur2 = resultKG;

    while (cur2 != null){

            System.out.println("current node is " + cur2.val);
            cur2 = cur2.next;
    }


            System.out.println("end ");

        ListNode pre = null;
        ListNode next = null;
        ListNode cur = ln0;



    while (cur != null) { //the condition is the iterate all the node, till empty
        next = cur.next; //before break the first one' link, record the next node
        cur.next = pre; //break the first one's link and point it to previous node
        pre = cur;  //move to next one, current one become previous
        cur = next; // move to next one, previous next one turn to be current for continus processing
    }
    cur = ln10;

    while (cur != null){

            System.out.println("current node is " + cur.val);
            cur = cur.next;
    }




System.out.println("head is " + ln0.val + " the tail is " + ln10.val);

int temp = (1 + 12 ) >>> 1;  // = (1+5)/2
ListNode mid0 = findMid(ln0); 
System.out.println("the middle is " + mid0.val );
}
/*
public ListNode reverse(ListNode head){
    ListNode pre=null;
    while(head!=null){
         ListNode next=head.next;//记录保留当前结点的下一个结点的地址
         head.next=pre;//当前结点指针域（原为下一个结点的地址）改为上一个节点的地址
         pre=head;//上一个结点变为当前结点，为之后的循环做准备
         head=next;//当前结点变为下一个结点，为之后的循环做准备
         }
     return pre;
    }
*/
public void reorderList(ListNode head) {  
        if (head == null || head.next == null || head.next.next == null) {
            return ;
        }
        //1. Find middle and cut
        ListNode mid = findMid(head);
        ListNode secondHead = mid.next;
        mid.next = null;

        //2. Reverse the second
        secondHead = reverse(secondHead);
        //3. Reconnect
        head = merge(head, secondHead);
    }
    /*
ListNode reverse(ListNode cur){  
  if(cur.next == null)return cur;   
  reverse(cur.next).next = cur;   
   cur.next = null;    return cur;}

*/

    private static ListNode reverse (ListNode head) {
        if (head == null || head.next == null)
        return head;
        ListNode newHead = null;
        while (head != null) {
          ListNode tmp = head.next; //
          head.next = newHead;
          newHead = head;
          head = tmp;
        }
        return newHead;
    }
    private static ListNode findMid (ListNode head) {
      //Key Point: finally slow is pointing to the last node of first half  

        //fast is one step ahead at the beginning
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            System.out.println("fast = " + fast.val + " slow: " + slow.val);

            //fast move two steps a time
            fast = fast.next.next;
            slow = slow.next;
        }
  
        System.out.println( "final slow: " + slow.val);

        return slow;

    }
    private static ListNode merge (ListNode head, ListNode secHead) {
    ListNode cur = head;
    while (secHead != null) {
        ListNode tmp = secHead.next;
        secHead.next = cur.next;
        cur.next =secHead;
        cur = cur.next.next;
        secHead = tmp;
    }
    return head;
    }

  
// Reverse Nodes in k-Group

public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        //Dummy node: unify the first subproblem with all others   
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;//pre.next points to head of sublist to be reversed
        int count = k;

        while (head != null) {
            count--;
            if (count == 0) {//Reverse every k steps
                pre = reverse(pre, head.next);
                head = pre.next;
                count = k;
            } else {
                head = head.next;//head: find head of next sublist
            }
        }
        return dummy.next;
    }

    private static ListNode reverse(ListNode preHead, ListNode nextHead) {
        ListNode tail = preHead.next;
        ListNode cur = tail.next;
        while (cur != nextHead) {
            ListNode tmp = cur.next;
            cur.next = preHead.next;
            preHead.next = cur;
            cur = tmp;
        }          
        tail.next = nextHead;  
        return tail;//Return tail reference after each subproblem
    }  
 /*     
// Three Sum -- Method 1

public List<List<Integer>> threeSum(int[] array) {   
   List<List<Integer>> res = new ArrayList<List<Integer>>();
   if(array == null || array.length < 3)
     return res;
   //Method: three pointers, one fixed,
   //one left to right from fixed, one right to left from rightmos
   Arrays.sort(array);
   int fixed = 0;
   while(fixed < array.length - 2) {
     int left = fixed + 1;
     int right = array.length - 1;
     int rest = 0 - array[fixed];
     while(left < right) {
       //A result found
       if(array[left] + array[right] == rest) {
         List<Integer> list = new ArrayList<Integer>();
         list.add(array[fixed]);
         list.add(array[left++]);
         list.add(array[right--]);
         res.add(list);
         //Skip Duplicates for left
         while(left < right && array[left] == array[left - 1]) {
           left++;
         }

         //Skip Duplicates for right
         while(left < right && array[right] == array[right + 1]) {
           right--;
         }         
       } else if (array[left] + array[right] < rest) {
         left++;
       } else {
         right--;
       }
     }
     fixed++;
     //Skip Duplicates for fixed
     while(fixed < array.length - 2 && array[fixed] == array[fixed - 1]) {
       fixed++;
     }
   }
   return res;
 }

//Three Sum -- Method 2

public List<List<Integer>> threeSum2(int[] array) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(array == null || array.length < 3) {
            return res;
        }

        Arrays.sort(array);
        //Use HashSet to remove duplicates
        Set<List<Integer>> set = new HashSet<List<Integer>>();

        for (int i = 0; i < array.length - 2; i++) {
            int left = i + 1;
            int right = array.length - 1;
            int rest = 0 - array[i];
            while (left < right) {
                if (array[left] + array[right] == rest)  
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(array[i]);
                    list.add(array[left++]);
                    list.add(array[right--]);
                    if (set.add(list)) {
                        res.add(list);
                    }
                } else if (array[left] + array[right] < rest) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
//Length of Longest substring -- Method 1

public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)  
            return 0;
        }
        //Queue: Maintain the substring
        Deque<Character> queue = new LinkedList<Character>();
        //Set: Remove duplicates
        Set<Character> set = new HashSet<Character>();
        int max = Integer.MIN_VALUE;
        for (char ch : s.toCharArray()) {
            //Keep pollFirst when duplicates found
            while (!set.add(ch)) {
                set.remove(queue.pollFirst());
            }  
            //offerLast when no duplicates
            queue.offerLast(ch);
            set.add(ch);
            max = Math.max(max, queue.size());
        }
        return max;
    }
// Length of Longest substring -- Method 2

public int lengthOfLongestSubstring2(String s) {
     if (s == null || s.length() == 0) {
         return 0;
     }
     int start = 0;
     int end = 0;
     //Array Map to remove duplicates
     int[] map = new int[128];
     int max = Integer.MIN_VALUE;
     char[] chS = s.toCharArray();
     while(end < chS.length){
         //PollFirst --> start++
         while (map[chS[end]] > 0) {
             map[chS[start++]]--;
         }
         //OfferLast --> end++
         map[chS[end++]]++;
         max = Math.max(max, end - start);
     }

     return max;
 }  
// Moving Average from Data Stream

public class MovingAverage {
    private Deque<Integer> queue;
    private int sum;
    private int size;
  //  Initialize your data structure here. 

    public MovingAverage(int size) {
        this.queue = new LinkedList<Integer>();
        this.sum = 0;
        this.size = size;
    }   

    public double next(int val) {
        if (queue.size() == size) {
            int last = queue.pollFirst();
            sum -= last
        }     
        queue.offerLast(val);
        sum += val;
        return sum * 1.0 / queue.size();
    }
}
//Simply Path Method 1

public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return new String();
        }
        path = path.trim();
        Deque<String> deque = new LinkedList<String>();
        for (String cur : path.split("/")) {        
            if (cur.equals("..")) {
                if (!deque.isEmpty()) {
                    deque.pollLast();
                }
            } else if (!cur.equals(".") && !cur.equals("")) {
                deque.offerLast(cur);
            }
        }       

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append("/").append(deque.pollFirst());
        }
        return sb.length() == 0 ? new String("/") : sb.toString();
    }
// Simply Path Method 2

public String simplifyPath2(String path) {
       if (path == null || path.length() == 0) {
           return new String();
       }
       path = path.trim();
       StringBuilder sb = new StringBuilder();
       String[] strs = path.split("/");
       int count = 0;
       for (int i = strs.length - 1; i >= 0; i--) {
           if (strs[i].equals(".."))  
               count++;
           } else if (!strs[i].equals(".") && !strs[i].equals("")) {
               if (count > 0) {
                   count--;
               } else {
                   sb.insert(0, "/" + strs[i]);
               }
           }
       }         
       return sb.length() == 0 ? new String("/") : sb.toString();
   }
// Largest Rectangle in Histogram

public int largestRectangleArea(int[] array) {         
    if (array == null || array.length == 0)
      return 0;
    Deque<Integer> stack = new LinkedList<Integer>();//store the index
    int max = 0;
    for(int i = 0; i <= array.length; i++) {
      //Each elem will be push once and poll once
      //1. Check whether this elem can be pushed into the stack
      int curVal = i == array.length ? 0 : array[i];
      while(!stack.isEmpty() && array[stack.peekLast()] >= curVal) {
          int height = array[stack.pollLast()];
          int leftBound = stack.isEmpty() ? 0 : stack.peekLast() + 1;
          int rightBound = i;
          max = Math.max(max, height * (rightBound - leftBound));
      }
      //2. Push the elem into the stack
      stack.addLast(i);
    }
    return max;
    }
// Trapping Water Method 1

public int trap(int[] height) {
    if(height.length == 0)
        return 0;
    Deque<Integer> stack = new LinkedList<Integer>();  
    int sum = 0;  

    for(int i = 0; i < height.length; i++) {
        int cur = height[i];

        while(!stack.isEmpty() && height[stack.peekLast()] < cur) {
            int index = stack.pollLast();
            if(!stack.isEmpty()) {
                int rightBound = stack.peekLast();
                int leftBound = i;
                sum += (leftBound - rightBound - 1) *
                        (Math.min(cur, height[rightBound]) - height[index])
            }
        }
            stack.offerLast(i);
    }
    return sum;
}
// Trapping Water -- Method 2

public int trap2(int[] arr) {
    if(arr == null || arr.length <= 2)
    return 0;
    //Two Scanners
    int left = 0;
    int right = arr.length - 1;
    int sum = 0;
    //Two Walls
    int leftMax = 0;
    int rightMax = 0;
    while(left <= right) {
      //Move lower wall first: Guarantee middle region can trap water
      if(leftMax <= rightMax) {
      leftMax = Math.max(leftMax, arr[left]);
      if(arr[left] < leftMax)
        sum += leftMax - arr[left];
        left++;
      }else {
        rightMax = Math.max(rightMax, arr[right]);
        if(arr[right] < rightMax)
        sum += rightMax - arr[right];
        right--;
      }
    }
    return sum;
  }

 */
}

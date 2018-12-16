import java.util.*;

public class sortedArrayToBST {
 public static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
 
public static void main(String[] args) {
 
   int[] input = {1, 3, 4, 7, 8, 9};

   System.out.println(sortedArrayToBST(input).val);

 System.out.println(sortedArrayToBST2(input).val);
 System.out.println(sortedArrayToBST3(input).val);


}

public static TreeNode sortedArrayToBST(int[] num) {
    if (num.length == 0) {
        return null;
    }
    TreeNode head = helper(num, 0, num.length - 1);
    return head;
}

public static TreeNode helper(int[] num, int low, int high) {
    if (low > high) { // Done
      System.out.println("returned by low > high = " + low + " | " + high);
        return null;
    }
    int mid = (low + high) / 2;
    System.out.println("mid = " + mid +  " ; num[mid] = " + num[mid]);
    TreeNode node = new TreeNode(num[mid]);
    node.left = helper(num, low, mid - 1);
    node.right = helper(num, mid + 1, high);
    if ( node != null)  System.out.println("node = " + node.val);
        if ( node.left != null)  System.out.println("node.left = " + node.left.val);
        if ( node.right != null)  System.out.println("node.right = " + node.right.val);
              System.out.println("returned by the last");

    return node;
}

 public static TreeNode sortedArrayToBST2(int[] nums) { //method 2
        
        int len = nums.length;
        if ( len == 0 ) { return null; }
        
        // 0 as a placeholder
        TreeNode head = new TreeNode(0); 
        
        Deque<TreeNode> nodeStack       = new LinkedList<TreeNode>() {{ push(head);  }};
        Deque<Integer>  leftIndexStack  = new LinkedList<Integer>()  {{ push(0);     }};
        Deque<Integer>  rightIndexStack = new LinkedList<Integer>()  {{ push(len-1); }};
        
        while ( !nodeStack.isEmpty() ) {
            TreeNode currNode = nodeStack.pop();
            int left  = leftIndexStack.pop();
            int right = rightIndexStack.pop();
            int mid   = left + (right-left)/2; // avoid overflow
            currNode.val = nums[mid];
            if ( left <= mid-1 ) {
                currNode.left = new TreeNode(0);  
                nodeStack.push(currNode.left);
                leftIndexStack.push(left);
                rightIndexStack.push(mid-1);
            }
            if ( mid+1 <= right ) {
                currNode.right = new TreeNode(0);
                nodeStack.push(currNode.right);
                leftIndexStack.push(mid+1);
                rightIndexStack.push(right);
            }
        }
        return head;
    }
//method 3
        public static TreeNode sortedArrayToBST3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        Queue<MyNode> queue = new LinkedList<>();
        int left = 0;
        int right = nums.length - 1;
        int val = nums[left + (right - left) / 2];
        TreeNode root = new TreeNode(val);
        queue.offer(new MyNode(root, left, right));
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                MyNode cur = queue.poll();
                
                int mid = cur.lb + (cur.rb - cur.lb) / 2;
                
                if (mid != cur.lb) {
                    TreeNode leftChild = new TreeNode(nums[cur.lb + (mid - 1 - cur.lb) / 2]);
                    cur.node.left = leftChild;
                    queue.offer(new MyNode(leftChild, cur.lb, mid - 1));
                }
                
                if (mid != cur.rb) {
                    TreeNode rightChild = new TreeNode(nums[mid + 1 + (cur.rb - mid - 1) / 2]);
                    cur.node.right = rightChild;
                    queue.offer(new MyNode(rightChild, mid + 1, cur.rb));
                }
            }
        }
        
        return root;
    }
    
    private static class MyNode {
        TreeNode node;
        int lb;
        int index;
        int rb;
        
        public MyNode(TreeNode n, int theLeft, int theRight) {
            this.node = n;
            this.lb = theLeft;
            this.rb = theRight;
        }
    }

}






/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of 
the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
*/
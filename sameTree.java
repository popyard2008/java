
/* An Iterative Java program to print levels line by line */

import java.util.*;

public class sameTree 
{
        // A Binary Tree Node
  static class Node
  {
    int data;
    Node left;
    Node right;

            // constructor
    Node(int data){
      this.data = data;
      left = null;
      right =null;
    }
  }
  public static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }    
        // Iterative method to do level order traversal line by line
  static void printLevelOrder(Node root)
  {
            // Base Case
    if(root == null)
      return;

            // Create an empty queue for level order tarversal
    Queue<Node> q =new LinkedList<Node>();

            // Enqueue Root and initialize height
    q.add(root);


    while(true)
    {

                // nodeCount (queue size) indicates number of nodes
                // at current level.
      int nodeCount = q.size();
      if(nodeCount == 0)
        break;

                // Dequeue all nodes of current level and Enqueue all
                // nodes of next level
      while(nodeCount > 0)
      {
        Node node = q.peek();
        System.out.print(node.data + " ");
        q.remove();
        if(node.left != null)
          q.add(node.left);
        if(node.right != null)
          q.add(node.right);
        nodeCount--;
      }
      System.out.println();
    }
  }

        // Driver program to test above functions
  public static void main(String[] args) 
  {
            // Let us create binary tree shown in above diagram
           /*               1
                       /     \
                      2       3
                    /   \       \
                   4     5       6
            */

                   Node root = new Node(1);
                   root.left = new Node(2);
                   root.right = new Node(3);
                   root.left.left = new Node(4);
                   root.left.right = new Node(5);
                   root.right.right = new Node(6);


                   TreeNode val = new TreeNode(1);
                   val.left  = new TreeNode(2);
                   val.right  = new TreeNode(3);

                   TreeNode val2 = new TreeNode(1);
                   val2.left  = new TreeNode(2);
                   val2.right  = new TreeNode(3);
                   val2.right.left = new TreeNode(1);

                   printLevelOrder(root);

                  int i = 0;

                   System.out.println(" -- ");
                   System.out.println(" " + val2 +" "+ val.left);
                   // printLevelOrder(val);

                   boolean result = isSameTree(val, val2);
                   System.out.println("result is " + result);
                 }

                 public static boolean  isSameTree2(TreeNode p, TreeNode q) {
                  if(p == null && q == null) {
                   System.out.println("returen position 1 : true");
                    return true;}
                  if(p == null || q == null) 
                    {
                     System.out.println("returen position 2 : false");

                      return false;
                    }

                  if(p.val == q.val)
                                        {
                     System.out.println("returen position 3 : unknown");
                    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
                       }                   
                    
                     System.out.println("returen position 4 : false");

                      return false;
                                    }
//$$ using stack and treenode class here 
                public static boolean isSameTree(TreeNode p, TreeNode q) {
                 Stack<TreeNode> stack_p = new Stack <> ();       
                 Stack<TreeNode> stack_q = new Stack <> ();
                 if (p != null) stack_p.push( p ) ;
                 if (q != null) stack_q.push( q ) ;
                 while (!stack_p.isEmpty() && !stack_q.isEmpty()) {

                  // pop the stack one by one through the end
                   TreeNode pn = stack_p.pop() ;  
                   TreeNode qn = stack_q.pop() ;
                   //current nodes comparision, it only check !=, the resuls
                   //could be equal 
                   if (pn.val != qn.val) 
                   {System.out.println("returen position 1 : false");
                    return false ;}
                   if (pn.right != null) stack_p.push(pn.right) ; //get rigth
                   if (qn.right != null) stack_q.push(qn.right) ;
                   if (stack_p.size() != stack_q.size()) 
                   {System.out.println("returen position 2 : false");
                    return false ;}
                   if (pn.left != null) stack_p.push(pn.left) ;                
                   if (qn.left != null) stack_q.push(qn.left) ;
                   if (stack_p.size() != stack_q.size()) 

                   {System.out.println("returen position 3 : false");
                    return false ;}
                                     }         
                 System.out.println("returen final result here");                    
                 return stack_p.size() == stack_q.size() ;   
               }
             }
      //This code is contributed by Sumit Ghosh
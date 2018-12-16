import java.util.*;

public class balancedBinaryTree {
	
public static void main(String[] args) {

		TreeNode tn0 = new TreeNode(0);

		TreeNode tn1 = new TreeNode(1);

		TreeNode tn2 = new TreeNode(2);

		TreeNode tn3 = new TreeNode(3);

		TreeNode tn4 = new TreeNode(4);

		TreeNode tn5 = new TreeNode(5);

		tn0.left = tn1;
		tn0.right = tn2;
		tn1.left = tn3;
		tn1.right = tn4;
		tn2.left = tn5;	

		System.out.println(tn0.left);


}




  public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}










}
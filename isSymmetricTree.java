import java.util.*;

public class isSymmetricTree{

public static class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) {val = x ; }

}

public static void main (String[] args) {

	TreeNode treeNode0 = new TreeNode(1);
	TreeNode treeNode1 = new TreeNode(2);
	TreeNode treeNode2 = new TreeNode(1);
	TreeNode treeNode3 = new TreeNode(3);
	TreeNode treeNode4 = new TreeNode(4);
	TreeNode treeNode5 = new TreeNode(4);
	TreeNode treeNode6 = new TreeNode(3);

	treeNode0.left = treeNode1;
	treeNode0.right = treeNode2;
	treeNode1.left = treeNode3;
	treeNode1.right = treeNode4;
	treeNode2.left = treeNode5;
	treeNode2.right = treeNode6;

	boolean result = isSymetric(treeNode0);
	boolean result2 = isSymetricIterative(treeNode0);

	System.out.println("Symetic? " + result );
	System.out.println("Symetic? " + result2 );
}

public static boolean isSymetric (TreeNode head){
	return isMirror(head, head);
}

public static boolean isMirror(TreeNode t1, TreeNode t2) {
	if (t1 == null && t2 == null) return true;
	if (t1 == null || t2 == null ) return false;
	return (t1.val == t2.val)
	&& isMirror(t1.left, t2.right)
	&& isMirror(t1.right, t2.left);

}
public static boolean isSymetricIterative(TreeNode root){
	Queue<TreeNode> q = new LinkedList<>();
	q.add(root);
	q.add(root);
	while (!q.isEmpty()) {
		TreeNode t1 = q.poll();
		TreeNode t2 = q.poll();
		if (t1 == null && t2 == null) continue;
		if (t1 == null || t2 == null) return false;
		if (t1.val != t2.val ) return false;
		q.add(t1.left);
		q.add(t2.right);
		q.add(t1.right);
		q.add(t2.left);
	}
	return true;
}


}
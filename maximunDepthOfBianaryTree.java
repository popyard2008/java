import java.util.*;

public class maximunDepthOfBianaryTree{

public static
 class TreeNode {
int val;
TreeNode left;
TreeNode right;
TreeNode(int x) {val = x;}
}

public static void main(String[] args) {

TreeNode tn0 = new TreeNode(1);
TreeNode tn1 = new TreeNode(1);
TreeNode tn2 = new TreeNode(2);
TreeNode tn3 = new TreeNode(2);
TreeNode tn4 = new TreeNode(2);

tn0.left = tn1;
tn0.right = tn2;
tn2.left = tn3;
tn2.right = tn4;

System.out.println("max depth is " + maxDepth(tn0));

}
//METHOD ONE:

public static int maxDepth3(TreeNode root) {
    if(root == null) {
        return 0;
    }
    
    Stack<TreeNode> stack = new Stack<>();
    Stack<Integer> value = new Stack<>();
    stack.push(root);
    value.push(1);
    int max = 0;
    while(!stack.isEmpty()) {
        TreeNode node = stack.pop();
        int temp = value.pop();
        max = Math.max(temp, max);
        if(node.left != null) {
            stack.push(node.left);
            value.push(temp+1);
        }
        if(node.right != null) {
            stack.push(node.right);
            value.push(temp+1);
        }
    }
    return max;
}

public static int maxDepth(TreeNode root) {
    if(root == null) {
        return 0;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int count = 0;
    while(!queue.isEmpty()) {
        int size = queue.size();
        while(size-- > 0) {
            TreeNode node = queue.poll();
            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
        }
        count++;
    }
    return count;
}

public static int maxDepth2 (TreeNode root){
	if (root == null) return 0;
	return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}


}
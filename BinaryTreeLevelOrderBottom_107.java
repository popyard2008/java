import java.util.*;
public class BinaryTreeLevelOrderBottom_107 {

public static
 class TreeNode {
int val;
TreeNode left;
TreeNode right;
TreeNode(int x) {val = x;}
}

public static void main(String[] args) {

TreeNode tn0 = new TreeNode(3);
TreeNode tn1 = new TreeNode(9);
TreeNode tn2 = new TreeNode(20);
TreeNode tn3 = new TreeNode(17);
TreeNode tn4 = new TreeNode(7);

tn0.left = tn1;
tn0.right = tn2;
tn2.left = tn3;
tn2.right = tn4;

List results = levelOrderBottom(tn0);
List results2 = levelOrderBottom2(tn0);
System.out.println("results are " + results + " and " + results2);

}

// System.out.println("max depth is " + levelOrderBottom(tn0));

// DFS solution:

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
        
        if(root == null) return wrapList;
        
        queue.offer(root);
        while(!queue.isEmpty()){
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            for(int i=0; i<levelNum; i++) {
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }
            wrapList.add(0, subList);
        }
        return wrapList;
    }
// BFS solution:

        public static List<List<Integer>> levelOrderBottom2(TreeNode root) {
            List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
            levelMaker(wrapList, root, 0);
            return wrapList;
        }
        
        public static void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
            if(root == null) return;
            if(level >= list.size()) {
                list.add(0, new LinkedList<Integer>());
            }
            levelMaker(list, root.left, level+1);
            levelMaker(list, root.right, level+1);
            list.get(list.size()-level-1).add(root.val);
        }
}
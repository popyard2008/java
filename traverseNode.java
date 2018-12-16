import java.util.*;

 class traverseNode {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {

        TreeNode tn0 = new TreeNode(0);
        TreeNode tn1 = new TreeNode(1);
        TreeNode tn2 = new TreeNode(2);
        TreeNode tn3 = new TreeNode(3);
        TreeNode tn4 = new TreeNode(4);
        TreeNode tn5 = new TreeNode(5);

        tn0.left = tn1;

        tn1.left = tn2;

        tn0.right = tn3;

        tn1.right = tn4;

        System.out.println("the final result is " + orderTraversal(tn0));


    }

    public static List<Integer> orderTraversal (TreeNode root){
        List<Integer> result = new ArrayList<>();
        Deque <Guide> path = new ArrayDeque<>();
        path.addFirst(new  Guide(0, root));

        while (!path.isEmpty()){
            Guide current = path.removeFirst();
            if(current.node == null) continue;

            if(current.ope == 1){
                result.add(current.node.val);
            } else {
                path.addFirst(new Guide (0, current.node.right));
                path.addFirst(new Guide (1, current.node));
                path.addFirst(new Guide (0, current.node.left));
            }
            System.out.println("the result in the middle is " + result + " current = " + current);
        }

        return result;

    }

    private  static class Guide {
        int ope; // 0, visit, 1, print;
        TreeNode node;
        public Guide (int ope, TreeNode node) {
            this.ope = ope;
            this.node = node;
        }
    }

}
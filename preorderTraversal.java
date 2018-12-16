Public class preorderTraversal {

public List<Integer> preorderTraversal (TreeNode root) {
	List<Integer> result = new ArrayList<>();
	Deque<Guider > path = new ArrayDeque<>();
	path.addFirst(new Guider(0, root));

	while (!path.isEmpty()){
		Guide current = path.removeFirst();
		if (current.node == null) continue;

		if(curent.ope == 1){
			result.add(current.node.val);
		}else{
			path.addFirst(new Guider(0, current.node.right));
			path.addFirst(new Guide(0, current.node.left));
			path.addFirst(new Guide(1, current.node));
		}
		}
		return result;
	}

private class Guide {
	int ope;
	TreeNode node;
	public Guide(int ope, TreeNode node) {
		this.ope = ope;
		this.node = node;
	}
}


}

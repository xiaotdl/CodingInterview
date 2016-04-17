class TreeNode {

    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public static void dfs(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            dfs(root.left);
            dfs(root.right);
        }
    }

    public static TreeNode build_binary_tree() {
        //     1
        //    / \
        //   2   3
        //  / \ / \
        // 4  5 6  7
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n1.left = n2; n1.right = n3;
        n2.left = n4; n2.right = n5;
        n3.left = n6; n3.right = n7;
        return n1;
    }

    public static void main(String [] args) {
        // Build a binary tree
        TreeNode root = build_binary_tree();

        // DFS Traversal
        dfs(root);
    }
}
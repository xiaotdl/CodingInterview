/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class BinaryTreeSerialization {

    // V1
    // DFS(Preorder traversal)
    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helper_serialize(root, sb);
        return sb.toString();
    }
    private void helper_serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("# ");
        } else {
            sb.append(root.val + " ");
            helper_serialize(root.left, sb);
            helper_serialize(root.right, sb);
        }
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        StringTokenizer st = new StringTokenizer(data, " ");
        return helper_deserialize(st);
    }
    private TreeNode helper_deserialize(StringTokenizer st) {
        if (!st.hasMoreTokens()) {
            return null;
        }
        String val = st.nextToken();
        if (val.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = helper_deserialize(st);
        root.right = helper_deserialize(st);
        return root;
    }

    // V2
    // Postorder traversal
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helper_serialize(root, sb);
        // System.out.println(sb.toString());
        return sb.toString();
    }
    private void helper_serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("# ");
        } else {
            helper_serialize(root.left, sb);
            helper_serialize(root.right, sb);
            sb.append(root.val + " ");
        }
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        // ' '.join(str.split().reverse())
        String[] parts = data.split(" ");
        Collections.reverse(Arrays.asList(parts));
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            sb.append(part + " ");
        }
        data = sb.toString();

        StringTokenizer st = new StringTokenizer(data, " ");
        return helper_deserialize(st);
    }
    private TreeNode helper_deserialize(StringTokenizer st) {
        if (!st.hasMoreTokens()) {
            return null;
        }
        String val = st.nextToken();
        if (val.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.right = helper_deserialize(st);
        root.left = helper_deserialize(st);

        return root;
    }

    // V3
    // BFS
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        StringBuilder sb = new StringBuilder();
        sb.append(root.val);

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();

                sb.append(" ");
                if (curr.left != null) {
                    queue.offer(curr.left);
                    sb.append(curr.left.val);
                } else {
                    sb.append("#");
                }

                sb.append(" ");
                if (curr.right != null) {
                    queue.offer(curr.right);
                    sb.append(curr.right.val);
                } else {
                    sb.append("#");
                }
            }
        }

        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == null || data == "#" || data.length() == 0) {
            return null;
        }

        StringTokenizer st = new StringTokenizer(data, " ");
        String val = st.nextToken();
        TreeNode root = new TreeNode(Integer.parseInt(val));

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty() && st.hasMoreTokens()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();

                val = st.nextToken();
                if (val.equals("#")) {
                    curr.left = null;
                } else {
                    curr.left = new TreeNode(Integer.parseInt(val));
                    queue.offer(curr.left);
                }

                val = st.nextToken();
                if (val.equals("#")) {
                    curr.right = null;
                } else {
                    curr.right = new TreeNode(Integer.parseInt(val));
                    queue.offer(curr.right);
                }
            }
        }

        return root;
    }
}


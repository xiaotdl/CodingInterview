package binary_tree_serialization;

import java.util.*;

/**
 * Created by Xiaotian on 2/7/18.
 */
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
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

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if (data == null || data == "#" || data.length() == 0) {
            return null;
        }

        StringTokenizer st = new StringTokenizer(data, " ");
        String val = st.nextToken();
        TreeNode root = new TreeNode(Integer.parseInt(val));

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

//        while (!queue.isEmpty() && st.hasMoreTokens()) {
        while (!queue.isEmpty()) {
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

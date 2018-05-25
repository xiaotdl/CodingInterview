package serialize_and_deserialize_binary_tree;

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
        if (data == null || data.length() == 0 || data.equals("#")) {
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

class CodecII {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "#";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (curr == null) {
                    sb.append("#,");
                    continue;
                }
                sb.append(curr.val+",");
                q.offer(curr.left);
                q.offer(curr.right);
            }
        }
        sb.deleteCharAt(sb.length() - 1); // remove trailing ","
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0 || data.equals("#")) return null;

        String[] tokens = data.split(",");
        Queue<TreeNode> q = new LinkedList<>();
        int i = 0;
        TreeNode root = new TreeNode(Integer.parseInt(tokens[i++]));
        q.offer(root);
        while (!q.isEmpty() && i < tokens.length) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                TreeNode curr = q.poll();

                if (i < tokens.length) {
                    if (tokens[i].equals("#")) {
                        curr.left = null;
                    }
                    else {
                        curr.left = new TreeNode(Integer.parseInt(tokens[i]));
                        q.offer(curr.left);
                    }
                    i++;
                }

                if (i < tokens.length) {
                    if (tokens[i].equals("#")) {
                        curr.right = null;
                    }
                    else {
                        curr.right = new TreeNode(Integer.parseInt(tokens[i]));
                        q.offer(curr.right);
                    }
                    i++;
                }
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

class CodecIII {
    // tag: dfs, pre-order

    private final static String DELIMITER = ",";
    private final static String NULL = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return NULL;
        StringBuilder sb = new StringBuilder();
        dfsSerialize(root, sb);
        sb.deleteCharAt(sb.length() - 1); // remove last comma
        return sb.toString();
    }

    private void dfsSerialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL + DELIMITER);
            return;
        }
        sb.append(root.val + DELIMITER);
        dfsSerialize(root.left, sb);
        dfsSerialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;

        String[] tokens = data.split(DELIMITER);
        Queue<String> q = new LinkedList<>();
        for (String token : tokens) q.add(token);
        return dfsDeserialize(q);
    }

    private TreeNode dfsDeserialize(Queue<String> q) {
        String token = q.poll();

        if (token.equals(NULL)) return null;

        TreeNode root = new TreeNode(Integer.parseInt(token));
        root.left = dfsDeserialize(q);
        root.right = dfsDeserialize(q);
        return root;
    }
}

package serialize_and_deserialize_bst;

/**
 * Created by Xiaotian on 4/8/18.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Codec {
    // credit: https://discuss.leetcode.com/topic/104009/solution-by-mengqyou-126-com

    private final static String DELIMITER = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        dfsSerialize(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void dfsSerialize(TreeNode root, StringBuilder sb) {
        if (root == null) return;

        sb.append(root.val + DELIMITER);
        dfsSerialize(root.left, sb);
        dfsSerialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;

        System.out.println(data);
        String[] tokens = data.split(DELIMITER);
        int[] pos = new int[]{0};
        return dfsDeserialize(tokens, pos, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private TreeNode dfsDeserialize(String[] tokens, int[] pos, long min, long max) {
        if (pos[0] >= tokens.length) return null;

        int val = Integer.parseInt(tokens[pos[0]]);
        if (val < min || val > max) return null;

        TreeNode root = new TreeNode(val);
        pos[0]++;
        root.left = dfsDeserialize(tokens, pos, min, val - 1);
        root.right = dfsDeserialize(tokens, pos, val + 1, max);
        return root;
    }
}


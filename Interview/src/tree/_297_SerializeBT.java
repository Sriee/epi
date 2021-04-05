package tree;

import java.util.*;

/**
 * This is the pattern for serializing and deserializing trees. This implementation could be extended to n-ary and BST.
 */
public class _297_SerializeBT {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if(node == null)
            sb.append("#").append(",");
        else {
            sb.append(node.val).append(",");
            serializeHelper(node.left, sb);
            serializeHelper(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        String token = queue.poll();
        if(token == null || token.equals("#"))
            return null;

        TreeNode node = new TreeNode(Integer.parseInt(token));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);

        return node;
    }
}

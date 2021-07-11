package bst;

import java.util.*;

/*
 * This is the general pattern for serialization / deserialization of Trees.
 *
 * The solution to this problem is same as src/tree/_297_SerializeBT.java
 */
public class _449_Codec {

    // Encodes a tree to a single string.
    public String serialize(BSTNode root) {
        StringBuilder sb = new StringBuilder();
        construct(root, sb);
        return sb.toString();
    }

    private void construct(BSTNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#").append(",");
            return;
        }

        sb.append(node.val).append(",");
        construct(node.left, sb);
        construct(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public BSTNode deserialize(String data) {
        return deserializeBST(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    private BSTNode deserializeBST(LinkedList<String> lst) {
        String token = lst.removeFirst();
        if (token.equals("#")) {
            return null;
        }

        BSTNode node = new BSTNode(Integer.parseInt(token));
        node.left = deserializeBST(lst);
        node.right = deserializeBST(lst);

        return node;
    }
}
package tree;

import java.util.*;

/**
 * Follows Serialization/Deserialization Format similar to
 *  297. Serialize and Deserialize Binary Tree
 *  449. Serialize and Deserialize Binary Search Tree
 */
public class _428_SerializeNT {

    // Encodes a tree to a single string.
    public String serialize(NaryNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(NaryNode node, StringBuilder sb) {
        if (node == null)
            sb.append("#").append(",");
        else {
            sb.append(node.val).append(",");
            sb.append(node.children.size()).append(",");

            for (NaryNode children: node.children) {
                serializeHelper(children, sb);
            }
        }
    }

    // Decodes your encoded data to tree.
    public NaryNode deserialize(String data) {
        Deque<String> deque = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(deque);
    }

    private NaryNode deserializeHelper(Deque<String> deque) {
        String val = deque.poll();
        if (val == null || val.equals("#"))
            return null;

        NaryNode node = new NaryNode(Integer.parseInt(val), new ArrayList<>());
        int childrenCount = Integer.parseInt(deque.poll());

        for (int i = 0; i < childrenCount; i++) {
            node.children.add(deserializeHelper(deque));
        }

        return node;
    }

    public static void main(String[] args) {
        _428_SerializeNT snt = new _428_SerializeNT();

        NaryNode root = new NaryNode(1), node;
        for (int i = 2; i < 6; i++)
            root.children.add(new NaryNode(i));

        node = root.children.get(1);
        node.children.add(new NaryNode(6));
        node.children.add(new NaryNode(7));

        node = root.children.get(2);
        node.children.add(new NaryNode(8));

        node = root.children.get(3);
        node.children.add(new NaryNode(9));
        node.children.add(new NaryNode(10));

        String data = snt.serialize(root);
        System.out.println(data);
    }
}

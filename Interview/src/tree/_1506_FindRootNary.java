package tree;

import java.util.*;

public class _1506_FindRootNary {

    /* ===========================================================================================
     * Approach 1: Using additional memory
     *
     * TC: O(2n) = O(n)
     * SC: O(n) = O(5 x 10^4)
     * ===========================================================================================
     */
    public NaryNode findRoot(List<NaryNode> tree) {
        int[] seen = new int[50001];

        for (NaryNode node : tree) {
            if (node.children != null) {
                for (NaryNode child : node.children) {
                    seen[child.val]++;
                }
            }
        }

        for (NaryNode node : tree) {
            if (seen[node.val] == 0)
                return node;
        }

        return null;
    }

    public static void main(String[] args) {
        _1506_FindRootNary frn = new _1506_FindRootNary();

        // Test Input: Serialized level order traversal of an N-ary tree
        // [1, null, 3, 2, 4, null, 5, 6]
        NaryNode root = new NaryNode(1);

        for (int i = 2; i <= 4; i++)
            root.children.add(new NaryNode(i));

        // 1->3
        NaryNode node13 = root.children.get(1);
        node13.children.add(new NaryNode(5));
        node13.children.add(new NaryNode(6));

        List<NaryNode> nodeList = new ArrayList<>();
        nodeList.add(node13.children.get(0));
        nodeList.add(node13);
        nodeList.add(root);
        nodeList.add(root.children.get(0));
        nodeList.add(node13.children.get(1));
        nodeList.add(root.children.get(1));
        nodeList.add(root.children.get(2));

        NaryNode foundRoot = frn.findRoot(nodeList);
        System.out.println(foundRoot);
    }
}

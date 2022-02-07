package tree;

import java.util.*;

public class _1506_FindRootNary {

    /* ===========================================================================================
     * Approach 1: You only look only once
     *
     * TC: O(2n) = O(n)
     * SC: O(1)
     * ===========================================================================================
     */
    public NaryNode findRoot(List<NaryNode> tree) {
        int sum = 0;

        for (NaryNode node : tree) {
            sum += node.val;

            for (NaryNode child : node.children) {
                sum -= child.val;
            }
        }

        for (NaryNode node : tree) {
            if (node.val == sum)
                return node;
        }

        return null;
    }

    /* ===========================================================================================
     * Approach 2: Using additional memory
     *
     * TC: O(2n) = O(n)
     * SC: O(n) = O(5 x 10^4)
     * ===========================================================================================
     */
    public NaryNode findRootSet(List<NaryNode> tree) {
        // int[] seen = new int[50001];
        HashSet<Integer> seen = new HashSet<>();
        for (NaryNode node : tree) {
            for(NaryNode child : node.children) {
                seen.add(child.val);
            }
        }

        for (NaryNode node : tree) {
            if (!seen.contains(node.val))
                return node;
        }

        return null;
    }

    /* ===========================================================================================
     * Approach 3: In-Degree Approach
     *
     * TC: O(2n) = O(n)
     * SC: O(n)
     * ===========================================================================================
     */
    public NaryNode findRootInDegree(List<NaryNode> tree) {
        // node = in-degree count
        Map<NaryNode, Integer> map = new HashMap<>();

        for (NaryNode node: tree) {
            map.put(node, 0);
        }

        for (NaryNode node: tree) {
            // Increment in-degree for child nodes
            for (NaryNode child : node.children) {
                map.put(child, map.get(child) + 1);
            }
        }

        for (NaryNode node : map.keySet()) {
            // A root node won't have any in-degree
            if (map.get(node) == 0)
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

        // Approach 1 - You look only once approach
        NaryNode foundRoot = frn.findRootInDegree(nodeList);
        System.out.println(foundRoot);

        // Approach 2 - Using auxiliary set
        foundRoot = frn.findRootSet(nodeList);
        System.out.println(foundRoot);

        // Approach 3 - Using auxiliary set
        foundRoot = frn.findRootInDegree(nodeList);
        System.out.println(foundRoot);
    }
}
